package com.example.weixindemo.pojo.menu;

/**
 * 父菜单项：包含有二级菜单项的一级菜单，这类菜单包含有两个属性，name和sub_button，而sub_button 是一个菜单数组
 */
public class ComplexButton extends Button {

    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
