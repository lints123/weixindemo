package com.example.weixindemo.constants;

public class WeixinConstants {

    /** --- URL ---- */
    // 通过appId和appSecret获取accessToken（两小时有效，一天2000次）
    public static final String WX_URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    // 创建菜单
    public static final String WX_URL_CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 删除所有菜单
    public static final String WX_URL_DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";



    // 公众号appId
    public static final String appId = "wxb403973927b47a2a";//getInitParameter("appId");

    public static final String appSecret = "e964303502b605785dd66a7c66870d21";//getInitParameter("appSecret");
}
