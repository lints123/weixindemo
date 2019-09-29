package com.example.weixindemo.utils;

import com.example.weixindemo.pojo.clisend.BaseMessage;
import com.example.weixindemo.pojo.clisend.Image;
import com.example.weixindemo.pojo.clisend.ImageMessage;
import com.example.weixindemo.pojo.clisend.TextMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConvertUtil {

    public static <T> T toBean(Object source,T target){
        if (null == source) {
            return target;
        }
        try {
            // 获取bean字段值
            Field[] fields = source.getClass().getDeclaredFields();

            // 获取父级属性
            Field[] declaredFields = source.getClass().getSuperclass().getDeclaredFields();
            // 合并属性
            Field[] beanFields = new Field[fields.length + declaredFields.length];
            System.arraycopy(fields, 0, beanFields, 0, fields.length);
            System.arraycopy(declaredFields, 0, beanFields, fields.length, declaredFields.length);
            for (Field field : beanFields) {
                // 私有属性设置为可访问
                boolean accessible = field.isAccessible();
                if(!accessible) {
                    field.setAccessible(true);
                }
                String fieldName = field.getName();
                Object fieldValue = field.get(source);


                if (null != fieldValue) {
                    Field[] fields1 = target.getClass().getDeclaredFields();
                    Field[] fields2 = target.getClass().getSuperclass().getDeclaredFields();

                    for (Field field1 : fields1) {
                        field1.setAccessible(true);
                        if (fieldName.equalsIgnoreCase(field1.getName())){

                            Method m1 = target.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldValue.getClass());
                            if (m1 != null) {
                                m1.invoke(target, fieldValue);
                            }
                        }
                    }

                    for (Field field2 : fields2) {
                        field2.setAccessible(true);
                        if (fieldName.equalsIgnoreCase(field2.getName())){
                            Method m1 = target.getClass().getSuperclass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldValue.getClass());
                            if (m1 != null) {
                                m1.invoke(target, field2.get(target) != null ? field2.get(target) : fieldValue);
                            }
                        }
                    }
                    /*Field[] beanFields3 = new Field[fields1.length+fields2.length];

                    // src表示源数组，srcPos表示源数组要复制的起始位置，desc表示目标数组，length表示要复制的长度。
                    System.arraycopy(fields1,0,beanFields3,0,fields1.length);
                    System.arraycopy(fields2,0,beanFields3,fields1.length,fields2.length);

                    for (Field field1 : beanFields3) {
                        field1.setAccessible(true);
                        if (fieldName.equalsIgnoreCase(field1.getName())) {
                            System.out.println(fieldName + "_________");
                            System.out.println(fieldValue.getClass());
                            System.out.println(fieldValue);

                            Method m1 = target.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fieldValue.getClass());
                            if (m1 != null) {
                                m1.invoke(target, fieldValue);
                            }
                        }
                    }*/
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return target;
    }

    public static void main(String[] args) {
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setFromUserName("1111");
        baseMessage.setMsgType("2222");
        baseMessage.setCreateTime(1212L);
        baseMessage.setToUserName("3333");
        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType("TEXT");
        toBean(baseMessage,textMessage);
        System.out.println(textMessage.getFromUserName()+","+textMessage.getToUserName()+","+textMessage.getMsgType()+","+textMessage.getCreateTime());
        /*ImageMessage imageMessage = new ImageMessage();
        Image image = new Image();
        image.setMediaId("12121");
        imageMessage.setImage(image);
        ConvertUtil.toBean(baseMessage,imageMessage);
        System.out.println(imageMessage.getImage().getMediaId());*/
    }

}