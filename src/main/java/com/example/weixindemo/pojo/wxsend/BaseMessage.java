package com.example.weixindemo.pojo.wxsend;

/**
 * 请求消息的基类
 */
public class BaseMessage {

    // 开发者微信号
    private String ToUserName;

    // 发送者账号（你的OpenId）
    private String FromUserName;

    // 消息的创建时间
    private long CreateTime;

    // 消息类型
    private String MsgType;

    // 消息ID
    private long MsgId;


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
