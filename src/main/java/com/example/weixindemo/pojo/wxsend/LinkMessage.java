package com.example.weixindemo.pojo.wxsend;

/**
* 链接消息
* @author lints
* @date 2019-09-27
*
*/
public class LinkMessage {

    // 消息标题
    private String Title;

    // 消息描述
    private String Description;

    // 消息链接
    private String url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
