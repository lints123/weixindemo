package com.example.weixindemo.pojo.clisend;

/**
* 视频消息
* @author lints
* @date 2019-09-29
*/
public class VideoMessage extends BaseMessage {

    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}
