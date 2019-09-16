package com.example.weixindemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

public class tokenUtil {

    private static Logger logger = LoggerFactory.getLogger(tokenUtil.class);

    private static String token = "WnbVm6GTQj4BPmLliSday4K";

    public static void checkSignature(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        if ("".equals(signature) || null == signature) {
            logger.info("Xiaoshishu  >>> 签名校验失败");
        }
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 将token，timestamp,nonce 进行排序
        String sortStr = sort(token, timestamp, nonce);

        // 字符串进行shal加密
        String mySignature = shal(sortStr);

        if (!"".equals(mySignature) && mySignature.equals(signature)) {
            logger.info("Xiaoshishu  >>> 签名校验成功");
            try {
                response.getWriter().write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Xiaoshishu  >>> 签名校验失败");
        }
    }

    private static String sort(String token, String timestamp, String nonce) {
        String[] array = {token, timestamp, nonce};
        System.out.println(array.toString());
        Arrays.sort(array);
        System.out.println(array.toString());
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 字符串进行shal加密
     *
     * @param str
     * @return
     */
    private static String shal(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
