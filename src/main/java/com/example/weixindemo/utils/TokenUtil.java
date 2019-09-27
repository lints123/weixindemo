package com.example.weixindemo.utils;


import com.example.weixindemo.constants.WeixinConstants;
import com.example.weixindemo.pojo.AccessToken;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

public class TokenUtil {

    private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static String token = "WnbVm6GTQj4BPmLliSday4K";

    /**
     * 校验签名
     *
     * @param
     * @return
     * @author lints
     * @date 2019-09-27
     */
    public static void checkSignature(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        if ("".equals(signature) || null == signature) {
            logger.info("小师叔  >>> 签名校验失败");
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
            logger.info("小师叔  >>> 签名校验成功");
            try {
                response.getWriter().write(echostr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("小师叔  >>> 签名校验失败");
        }
    }

    // 排序
    private static String sort(String token, String timestamp, String nonce) {
        String[] array = {token, timestamp, nonce};
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 字符串进行shal加密
     */
    private static String shal(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
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

    /**
     * 通过appId和appSecret获取accessToken
     *
     * @param
     * @return
     * @author lints
     * @date 2019-09-27
     */
    public static AccessToken getAccessToken(String appId,String appSecret) {

        String url = WeixinConstants.WX_URL_ACCESSTOKEN.replace("APPID", appId);
        url = url.replace("APPSECRET", appSecret);
        logger.info("小师叔 >>> 当前请求的URL = [{}]", url);

        // 此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        JSONObject jsonObject = HttpUtil.httpRequest(url, "GET", "");
        System.out.println("获取到的access_token=" + jsonObject);

        AccessToken token = new AccessToken();
        token.setTokenName(jsonObject.getString("access_token"));
        token.setExpireSecond(jsonObject.getInt("expires_in"));
        return token;
    }

}
