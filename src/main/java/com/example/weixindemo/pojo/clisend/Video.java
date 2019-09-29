package com.example.weixindemo.pojo.clisend;

/**
* 视频
* @author lints
* @date 2019-09-29
*/
public class Video {

    // 媒体文件Id
    private String MediaId;

    // 缩略图的媒体id
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
