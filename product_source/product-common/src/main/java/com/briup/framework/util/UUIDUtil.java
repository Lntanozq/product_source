package com.briup.framework.util;

import java.util.UUID;

/**
 * @program: product_source
 * @description: uuid生成编码工具
 * @author: pgc
 * @create:2022-03-23
 **/
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static void main(String[] args) {
        System.out.println("格式前的UUID ： " + UUID.randomUUID().toString());
        System.out.println("格式化后的UUID ：" + getUUID());
    }
}
