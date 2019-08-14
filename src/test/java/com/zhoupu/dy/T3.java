package com.zhoupu.dy;

public class T3 {

    public static void main(String[] args) {

        String dd = "123";
        //1
        String a = "abc" + "efg";
        //2
        String b = "123";
        b += "456";
        //3
        String reslut = "";
        for (int i = 0; i < 5; i++) {
            reslut += "字符串连接操作";
        }

        String e ="舟谱数据技术";
        String f ="舟谱数据技术";
        System.out.println(e==f);

        String g ="舟谱数据"+"技术";
        String h ="舟谱"+"数据技术";
        System.out.println(g==h);

        System.out.println(f==g);

    }

}
