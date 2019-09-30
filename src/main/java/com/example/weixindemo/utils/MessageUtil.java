package com.example.weixindemo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.weixindemo.comment.weatherClient.WeatherInfo;
import com.example.weixindemo.constants.WeixinConstants;
import com.example.weixindemo.pojo.clisend.BaseMessage;
import com.example.weixindemo.pojo.clisend.Image;

import com.example.weixindemo.pojo.clisend.ImageMessage;
import com.example.weixindemo.pojo.clisend.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理消息的工具类
 */
public class MessageUtil {

    private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
    /**
     * 解析微信发送过来的请求（XML类型）
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception {

        // 将解析结果存储在HashMap中
        Map<String,String> map = new HashMap<>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        if (null != document) {
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                System.out.println(e.getName() + "|" + e.getText());
                map.put(e.getName(), e.getText());
            }

            // 释放资源
            inputStream.close();
            inputStream = null;

            return map;
        }
        return null;
    }

    /**
    * 扩展xstream，使其支持CDATA
    * @author lints
    * @date 2019-09-29
    */
    private static XStream xStream = new XStream(new XppDriver(){
       @Override
       public HierarchicalStreamWriter createWriter(Writer out) {
           return new PrettyPrintWriter(out) {
               // 对所有xml节点的转换都增加CDATA标记
               boolean cdata = true;

               @Override
               @SuppressWarnings("unchecked")
               public void startNode(String name, Class clazz) {
                   System.out.println("----"+name);
                   super.startNode(name, clazz);
               }

               @Override
               protected void writeText(QuickWriter writer, String text) {
                   if (cdata) {
                       writer.write("<![CDATA[");
                       writer.write(text);
                       writer.write("]]>");
                   } else {
                       writer.write(text);
                   }
               }
           };
       }
    });

/*
    public static String messageToXml(Object object){
        logger.info("小师叔 >>> 当前类 class = [{}]",object.getClass());

        xStream.alias("xml",object.getClass());
        logger.info("1");
        return xStream.toXML(object);
    }*/

    public static String messageToXml(Object object) {
        xStream.alias("xml", object.getClass());
        return xStream.toXML(object);
    }


    public static String processRequest(HttpServletRequest request) {
        String respXml = null;

        Date date = new Date();
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型!";
        try{
            // 解析发送过来的xml方法
            Map<String,String> requestMap = parseXml(request);
            // 消息类型
            assert requestMap != null;
            String msgType = requestMap.get("MsgType");

            // 发送方账号
            String fromUserName = requestMap.get("FromUserName");

            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");

            BaseMessage baseMessage = new BaseMessage();
            baseMessage.setToUserName(fromUserName);
            baseMessage.setFromUserName(toUserName);
            baseMessage.setCreateTime(date.getTime());
            baseMessage.setMsgType(msgType);
            if (msgType.equalsIgnoreCase(WeixinConstants.RESP_MESSAGE_TYPE_TEXT)) {

                // 发送给公众号，toUserName存你的openId，FromUserName存公众号的openId
                TextMessage textMessage = new TextMessage();
                respContent = "当前发送的是文本内容";
                textMessage.setContent(respContent);
                ConvertUtil.toBean(baseMessage,textMessage);
                respXml = messageToXml(textMessage);

            } else if (msgType.equalsIgnoreCase(WeixinConstants.RESP_MESSAGE_TYPE_IMAGE)) {
                // 返回用户发送过来的图片
                String mediaId = requestMap.get("MediaId");

                ImageMessage imageMessage = new ImageMessage();
                Image image = new Image();
                image.setMediaId(mediaId);
                imageMessage.setImage(image);

                ConvertUtil.toBean(baseMessage,imageMessage);

                respXml = messageToXml(imageMessage);

            } else if (msgType.equalsIgnoreCase(WeixinConstants.REQ_MESSAGE_TYPE_EVENT)) {
                String event = requestMap.get("Event");
                if (event.equalsIgnoreCase(WeixinConstants.EVENT_TYPE_SUBSCRIBE)) {
                    // 是否被关注
                    logger.info("小师叔  >>> 关注公众号");
                    TextMessage textMessage = new TextMessage();
                    textMessage.setContent("你来了？久等了。欢迎来到小师叔的公众号");
                    ConvertUtil.toBean(baseMessage,textMessage);
                    respXml = messageToXml(textMessage);

                } else if (event.equalsIgnoreCase(WeixinConstants.EVENT_TYPE_CLICK)) {

                    // 菜单点击事件
                    logger.info("小师叔  >>> 菜单点击事件");

                    String eventKey = requestMap.get("EventKey");
                    if ("33".equals(eventKey)) {
                        logger.info("小师叔  >>> 幽默笑话");
                        TextMessage textMessage = new TextMessage();
                        textMessage.setContent("点击幽默笑话事件");
                        textMessage.setMsgType(WeixinConstants.RESP_MESSAGE_TYPE_TEXT);
                        ConvertUtil.toBean(baseMessage,textMessage);
                        respXml = messageToXml(textMessage);

                    } else if ("34".equalsIgnoreCase(eventKey)) {

                        logger.info("小师叔  >>> 天气预报，默认查询广州");

                        String cityName = "广州";
                        WeatherInfo weather = new WeatherInfo();
                        String weaInfo = weather.getWeatherInfo(cityName);
                        TextMessage textMessage = new TextMessage();
                        textMessage.setContent(weaInfo);
                        textMessage.setMsgType(WeixinConstants.RESP_MESSAGE_TYPE_TEXT);
                        ConvertUtil.toBean(baseMessage,textMessage);
                        respXml = messageToXml(textMessage);
                    }
                } else if (event.equalsIgnoreCase(WeixinConstants.EVENT_TYPE_SCANCODE_PUSH)) {
                    String eventKey = requestMap.get("EventKey");
                    if ("35".equalsIgnoreCase(eventKey)) {

                        logger.info("小师叔  >>> 扫码推事件");
                        TextMessage textMessage = new TextMessage();
                        textMessage.setContent("小师叔  >>> 扫码推事件。。。");
                        textMessage.setMsgType(WeixinConstants.RESP_MESSAGE_TYPE_TEXT);
                        ConvertUtil.toBean(baseMessage,textMessage);
                        respXml = messageToXml(textMessage);
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    public static void main(String[] args) {

    }

    /**
     * 根据消息类型 构造返回消息
     */
    public static String buildXml(Map<String, String> map) {

        String result;
        String msgType = map.get("MsgType").toString();
        String content = map.get("Content");

        EmojiUtil emojiUtil = new EmojiUtil();
        String unicodeEmoji = emojiUtil.filterEmoji(content); //unicode编码的Emoji
        System.out.println("MsgType:" + msgType);
        if ("TEXT".equals(msgType.toUpperCase())) {
            /*查询天气*/
            if(content.contains("天气") && !"".equals(content)){
                if(content.contains("，")){
                    String cityName = content.substring(content.lastIndexOf("，")+1,content.length());
                    WeatherInfo weather = new WeatherInfo();
                    String weaInfo = weather.getWeatherInfo(cityName);
                    result = buildTextMessage(map,weaInfo);
                }else{
                    String notice = "查询天气的正确姿势: 天气:城市名\n请客官输入正确的格式哟~";
                    result = buildTextMessage(map,notice);
                }
            } else {
                result = buildTextMessage(map, "Xiaoshishu的小小窝, 请问客官想要点啥?");
            }
        }else if (content.contains("/:")  || content.contains("\\:")  || content.contains("[") && content.contains("]") || unicodeEmoji.contains("\\")) {
            // 表情处理
            result = buildTextMessage(map,"客官发送的内容很特别哟~/:heart    " + content);
        }
        else if ("IMAGE".equals(msgType.toUpperCase())) {
            // 图片
            result = buildImageMessage(map,null);
        } else if ("VOICE".equals(msgType.toUpperCase())) {
            // 语音
            result = buildVoiceMessage(map);
        } else if("VIDEO".equals(msgType.toUpperCase())){
            // 视频
            result = buildVideoMessage(map);
        } else {
            String fromUserName = map.get("FromUserName");
            // 开发者微信号
            String toUserName = map.get("ToUserName");
            result = String
                    .format(
                            "<xml>" +
                                    "<ToUserName><![CDATA[%s]]></ToUserName>" +
                                    "<FromUserName><![CDATA[%s]]></FromUserName>" +
                                    "<CreateTime>%s</CreateTime>" +
                                    "<MsgType><![CDATA[text]]></MsgType>" +
                                    "<Content><![CDATA[%s]]></Content>" +
                                    "</xml>",
                            fromUserName, toUserName, getUtcTime(),
                            "请回复如下关键词：\n文本\n图片\n语音\n视频\n音乐\n图文");
        }
        return result;
    }

    /**
     *  构建图片消息
     * @param map
     * @param picUrl
     * @return
     */
    private static String buildImageMessage(Map<String, String> map, String picUrl) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        /*返回指定的图片(该图片是上传为素材的,获得其media_id)*/
        //String media_id = "UCWXNCogK5ub6YFFQf7QcEpvDIYLf3Zh0L5W9i4aEp2ehfnTrASeV59x3LMD88SS";

        /*返回用户发过来的图片*/
        String mediaId = map.get("MediaId");
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Image>" +
                        "</xml>",
                fromUserName,toUserName, getUtcTime(),mediaId
        );
    }

    /**
     * 构造语音消息
     * @param map
     * @return
     */
    private static String buildVoiceMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        /*返回用户发过来的语音*/
        String mediaId = map.get("MediaId");
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[voice]]></MsgType>" +
                        "<Voice>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Voice>" +
                        "</xml>",
                fromUserName,toUserName, getUtcTime(),mediaId
        );
    }

    /**
     * 回复视频消息
     * @param map
     * @return
     */
    private static String buildVideoMessage(Map<String, String> map) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String title = "客官发过来的视频哟~~";
        String description = "客官您呐,现在肯定很开心,对不啦 嘻嘻?";
        /*返回用户发过来的视频*/
        //String media_id = map.get("MediaId");
        String mediaId = "hTl1of-w78xO-0cPnF_Wax1QrTwhnFpG1WBkAWEYRr9Hfwxw8DYKPYFX-22hAwSs";
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[video]]></MsgType>" +
                        "<Video>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "   <Title><![CDATA[%s]]></Title>" +
                        "   <Description><![CDATA[%s]]></Description>" +
                        "</Video>" +
                        "</xml>",
                fromUserName,toUserName, getUtcTime(),mediaId,title,description);
    }


    private static String buildTextMessage(Map<String,String> map,String content) {

        // 发送方账号
        String fromUserName = map.get("FromUserName");

        // 开发者账号
        String toUserName = map.get("ToUserName");

        /**
         * 文本消息XML数据格式
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" + "</xml>",
                fromUserName, toUserName, getUtcTime(), content);

    }


    private static String getUtcTime() {
        Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
        String nowTime = df.format(dt);
        long dd = (long) 0;
        try {
            dd = df.parse(nowTime).getTime();
        } catch (Exception e) {

        }
        return String.valueOf(dd);
    }

}
