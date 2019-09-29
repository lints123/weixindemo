package com.example.weixindemo.pojo.clisend;

/**
* 语音消息
* @author lints
* @date 2019-09-29
*/
public class VoiceMessage extends BaseMessage {

    // 语音
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
