package com.example.weixindemo.pojo.wxsend;

/**
* 视频消息
* @author lints
* @date 2019-09-27
*/
public class VideoMessage extends BaseMessage {

    // 视频消息媒体id
    private String MediaId;

    // 视频消息缩略图的媒体id
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
