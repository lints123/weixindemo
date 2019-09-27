package com.example.weixindemo.pojo.menu;

/**
 * 子菜单项：没有子菜单的菜单项，有可能是二级菜单项，也有可能不含二级菜单项
 */
public class CommonButton extends Button {

    // 事件类型
    private String type;

    //
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
