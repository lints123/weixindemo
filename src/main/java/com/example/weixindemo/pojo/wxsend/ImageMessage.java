package com.example.weixindemo.pojo.wxsend;

/**
* 图片消息
* @author lints
* @date 2019-09-27
*/
public class ImageMessage extends BaseMessage {

    // 图片链接
    private String PicUrl;

    // 图片消息媒体id
    private String mediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
/**
 * <xml>
 *  <ToUserName><![CDATA[toUser]]></ToUserName>
 *  <FromUserName><![CDATA[fromUser]]></FromUserName>
 *  <CreateTime>1348831860</CreateTime>
 *  <MsgType><![CDATA[image]]></MsgType>
 *  <PicUrl><![CDATA[this is a url]]></PicUrl>
 *  <MediaId><![CDATA[media_id]]></MediaId>
 *  <MsgId>1234567890123456</MsgId>
 *  </xml>
 *
 * */