package com.example.weixindemo.comment;


import com.example.weixindemo.pojo.AccessToken;
import com.example.weixindemo.pojo.AccessTokenInfo;
import com.example.weixindemo.utils.NetWorkUtil;
/*import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;*/
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AccessTokenServlet extends HttpServlet {

    static Logger logger = LoggerFactory.getLogger(AccessTokenServlet.class);

  /*  private static final String appId = "wxcb0b42bf43b4a153";//getInitParameter("appId");
    private static final String appSecret = "400d620b0f6f1370a5ad89228e342894";//getInitParameter("appSecret");
*/
   /* @Override
    public void init() throws ServletException {
        logger.info("小师叔  >>> 启动AccessTokenServlet  <<<");
        super.init();
    }*/

   /* public static AccessToken getAccessToken() {
        NetWorkUtil netWorkUtil = new NetWorkUtil();
        *//**
         * 接口地址为https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
         *//*
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId, appSecret);
        //此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
        System.out.println(Url);
        String result = netWorkUtil.getHttpsResponse(Url, "");
        System.out.println("获取到的access_token="+result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSONObject.fromObject(result);
        AccessToken token = new AccessToken();
        token.setTokenName(json.getString("access_token"));
        token.setExpireSecond(json.getInt("expires_in"));
        return token;

    }
*/
}
