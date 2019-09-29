package com.example.weixindemo.pojo.clisend;

/**
* 文本消息
* @author lints
* @date 2019-09-29
*/
public class TextMessage extends BaseMessage {

    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
