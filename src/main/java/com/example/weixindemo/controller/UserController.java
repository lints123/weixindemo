package com.example.weixindemo.controller;


import com.example.weixindemo.comment.AccessTokenServlet;
import com.example.weixindemo.utils.MessageUtil;
import com.example.weixindemo.utils.TokenUtil;
import com.example.weixindemo.utils.uploadMediaApiUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;


@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/validToken")
    public void validToken(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if ("GET".equals(request.getMethod())) {
            logger.info("小师叔  >>> 校验token开始");
            TokenUtil.checkSignature(request, response);

        } else {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            System.out.println("请求进入");
            String result = "";
            try {
                Map<String, String> map = MessageUtil.parseXml(request);
                if (map != null) {
                    System.out.println("开始构造消息");
                    result = MessageUtil.buildXml(map);
                    System.out.println(result);

                    if (result.equals("")) {
                        result = "未正确响应";
                    }
                    response.getWriter().println(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发生异常：" + e.getMessage());
            }
        }
    }

    /**
     * 验证微信
     *
     * @param
     * @return
     * @author lints
     * @date 2019-09-29
     */
    @RequestMapping(value = "toAppoin", method = RequestMethod.GET)
    public void valieToken(HttpServletRequest request, HttpServletResponse response) {
        logger.info("小师叔  >>> 校验token开始。。。");
        TokenUtil.checkSignature(request, response);
    }

    @RequestMapping(value = "toAppoin", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        logger.info("小师叔  >>> 处理微信发来的消息。。。");

        // 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 处理消息
            String respXml = MessageUtil.processRequest(request);
            logger.info("respXml = {}",respXml);
            // 响应消息
            response.getWriter().println(respXml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
     * 通过appid和appsecret请求accessToken，时效为2个小时
     */
    @RequestMapping(value = "/accessToken")
    public void getAccessToken() throws ServletException {
        AccessTokenServlet servlet = new AccessTokenServlet();
        servlet.init();
        System.out.println("小师叔  >>> 请求成功");
    }

    @RequestMapping(value = "/uploadMedia")
    public void uploadMedia() {
        uploadMediaApiUtil uploadMediaApiUtil = new uploadMediaApiUtil();
        String appId = "wxcb0b42bf43b4a153";
        String appSecret = "400d620b0f6f1370a5ad89228e342894";
        String accessToken = uploadMediaApiUtil.getAccessToken(appId, appSecret);

        String filePath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\2.jpg";
        File file = new File(filePath);
        String type = "IMAGE";
        JSONObject jsonObject = uploadMediaApiUtil.uploadMedia(file, accessToken, type);
        System.out.println(jsonObject.toString());

        /*
        *
{"access_token":"25_SoE3glPCosIcn5DAMwFBi8ixphWN1RQ4itdYGXwUzSEqPQ7DOdEo0KX46Z3lnncs3Loncmw_rOt6gFv9dEhsQmW-ogp6dQguZDxK-zonbJleez8S8k7oKE5JG2Ri5G31ly0AxTTFJeSK8aBCZUObAGAEZT","expires_in":7200}

{"media_id":"2kNml8Us7xn7qsXb2KlZ3WHQWidU6nriYOjCFnK5q1HG4ILQbq8wtjPGb5Gtt0xr","created_at":1568615698,"type":"image"}
        * */
    }
}
