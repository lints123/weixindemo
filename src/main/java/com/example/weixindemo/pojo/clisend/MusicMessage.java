package com.example.weixindemo.pojo.clisend;

/**
* 音乐消息
* @author lints
* @date 2019-09-29
*/
public class MusicMessage extends BaseMessage {

    // 音乐
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}
