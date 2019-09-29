package com.example.weixindemo.pojo.clisend;

/**
* 消息基类（公众站号 -> 普通用户）
* @author lints
* @date 2019-09-29
*/
public class BaseMessage {

    // 接收方账号（收到的openId）
    private String ToUserName;

    // 开发者微信号
    private String FromUserName;

    // 消息创建时间（整形）
    private Long CreateTime;

    // 消息类型
    private String MsgType;

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

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
