package com.example.weixindemo.pojo.wxevent;

/**
* 自定义菜单事件
* @author lints
* @date 2019-09-27
*/
public class MenuEvent extends BaseEvent{

    // 事件Key值，与自定义菜单接口的KEY值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
