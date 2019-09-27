package com.example.weixindemo.pojo.menu;

/**
 * 菜单项的基类封装
 */
public class Button {

    // 所有一级菜单，二级菜单都有一个共同的属性，就是name
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
