package com.example.weixindemo.utils;
;
import com.example.weixindemo.constants.WeixinConstants;
import com.example.weixindemo.pojo.menu.Menu;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinUtil {

    private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);


    /**
    * 创建菜单
    * @author lints
    * @date 2019-09-27
    */
    public static int createMenu(Menu menu, String accessToken) {

        // 获取accessToken
        /*AccessToken accessToken1 = TokenUtil.getAccessToken(WeixinConstants.appId,WeixinConstants.appSecret);
        if (null == accessToken) {
            accessToken = accessToken1.getTokenName();
        }
*/
        int result = 0;
        String url = WeixinConstants.WX_URL_CREATE_MENU.replace("ACCESS_TOKEN",accessToken);

        // 将菜单对象转换为JSON字符串
        String jsonMemu = JSONObject.fromObject(menu).toString();

        logger.info("小师叔 >>> 转换后的JsonMenu对象 = [{}]", jsonMemu);

        JSONObject jsonObject = HttpUtil.httpRequest(url, "POST", jsonMemu);
        if (null != jsonObject && 0 != jsonObject.getInt("errcode")) {
            result = jsonObject.getInt("errcode");
            logger.error("小师叔 >>> 创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getInt("errmsg"));

        }
        return result;
    }

    /***
     * 删除菜单
     * @param accessToken
     * @return
     */
    public static int deleteMenu(String accessToken){

        int result = 0;
        String url = WeixinConstants.WX_URL_DELETE_MENU.replace("ACCESS_TOKEN",accessToken);

        JSONObject jsonObject = HttpUtil.httpRequest(url,"GET",null);

        System.out.println(jsonObject);

        return result;
    }

    public static int selectMenu(String accessToken){

        int result = 0;
        String url = WeixinConstants.WX_URL_SELECT_MENU.replace("ACCESS_TOKEN",accessToken);

        JSONObject jsonObject = HttpUtil.httpRequest(url,"GET",null);

        System.out.println(jsonObject);

        return result;

    }

}
