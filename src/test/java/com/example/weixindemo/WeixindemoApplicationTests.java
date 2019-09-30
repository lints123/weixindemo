package com.example.weixindemo;

import com.example.weixindemo.constants.WeixinConstants;
import com.example.weixindemo.pojo.AccessToken;
import com.example.weixindemo.pojo.menu.*;
import com.example.weixindemo.utils.TokenUtil;
import com.example.weixindemo.utils.WeixinUtil;
import org.apache.commons.httpclient.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeixindemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {

		// 创建菜单：参考网址
		// AccessToken accessToken = TokenUtil.getAccessToken(WeixinConstants.appId,WeixinConstants.appSecret);
		AccessToken accessToken = new AccessToken();
		accessToken.setTokenName("25_2dS8y-JimyNs61uwxr_kXyuS87I7rusw8zcfnGr2G6RxSAle0r3NJSAyn-VGJ9mClXAP1jMNHFU8Qdmhi6xz6QG4PzQ4r7QXN93BtyYy2Tbfoty4nhNgih7fV0kHKCeAHAORJ");
		if (null != accessToken) {
			int result = WeixinUtil.createMenu(getMenu(),accessToken.getTokenName());

			if (0 == result) {
				System.out.println("菜单创建成功");
			} else {
				System.out.println("菜单创建失败，错误码：" + result);
			}
		}

		// 删除菜单
		// WeixinUtil.deleteMenu(accessToken.getTokenName());

		// 查询菜单
		// WeixinUtil.selectMenu(accessToken.getTokenName());

        WeixinUtil.getUserInfo(accessToken.getTokenName(),WeixinConstants.openId);
	}

	/**
	 * 组装菜单数据
	 *
	 * @return
	 */
	private static Menu getMenu() {

		ViewButton btn21 = new ViewButton();
		btn21.setName("百度");
		btn21.setType("view");
		btn21.setUrl("http://www.baidu.com");

		ViewButton btn22 = new ViewButton();
		btn22.setName("官方链接");
		btn22.setType("view");
		btn22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

		ViewButton btn23 = new ViewButton();
		btn23.setName("扫码");
		btn23.setType("view");
		btn23.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx44eb1076428bc798&redirect_uri=http://992nkr.natappfree.cc/appointment/wechat/toAppoin/oauth&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

		ViewButton btn24 = new ViewButton();
		btn24.setName("查询2");
		btn24.setType("view");
		btn24.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx44eb1076428bc798&redirect_uri=http%3a%2f%2fgb4xrs.natappfree.cc%2fappointment%2fwechat%2ftoAppoin%2foauth&response_type=code&scope=snsapi_base&state=123#wechat_redirect");

		ViewButton btn25 = new ViewButton();
		btn25.setName("登录");
		btn25.setType("view");
		btn25.setUrl("http://gb4xrs.natappfree.cc/appointment/sys/login.view");

      /*  ViewButton btn26 = new ViewButton();
        btn26.setName("测试");
        btn26.setType("view");
        btn26.setUrl("http://ytuwpp.natappfree.cc/appointment/wechat/toAppoin/toAppointInit");
        */


		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");

		CommonButton btn34 = new CommonButton();
		btn34.setName("天气预报");
		btn34.setType("click");
		btn34.setKey("34");

		CommonButton btn35 = new CommonButton();
		btn35.setName("扫码推事件");
		btn35.setType("scancode_push");
		btn35.setKey("35");

		/**
		 * 微信：  mainBtn1,mainBtn2,mainBtn3底部的三个一级菜单。
		 */

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("违章查询");
		//一级下有4个子菜单
		mainBtn1.setSub_button(new ViewButton[] { btn21 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("菜单");
		mainBtn2.setSub_button(new ViewButton[] {btn22,btn23,btn24,btn25 });


		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[] {  btn33,btn34,btn35 });



		/**
		 * 封装整个菜单
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}

}
