package com.zhoupu.dy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Test3 {

    public static ArrayList<Integer> mergeArray(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        // 因为给定升序数组，所以不考虑异常情况
        int arr1_index = 0;// 用于索引arr1
        int arr2_index = 0;// 用于索引arr2
        int count_all = arr1.size() + arr2.size();
        ArrayList<Integer> arrAll = new ArrayList<Integer>();// 用于存储处理后返回的数据
        for (int i = 0; i < count_all; i++) {
            if (arr1_index < arr1.size() && arr2_index < arr2.size()) {// 保证数组不越界
                if (arr1.get(arr1_index) < arr2.get(arr2_index)) {// 找出最小值
                    arrAll.add(arr1.get(arr1_index++));
                } else {
                    arrAll.add(arr2.get(arr2_index++));
                }
            } else if (arr1_index < arr1.size()) {// 此种情况说明 arr2已经超出数组
                arrAll.add(arr1.get(arr1_index++));
            } else {// 数组1已经超出，只处理数组2
                arrAll.add(arr2.get(arr2_index++));
            }
        }
        return arrAll;
    }

    public static void main(String[] args) {
        // 添加有序数组1
        // 添加有序数组1
        ArrayList<Integer> arr1=new ArrayList<Integer>();
        arr1.add(6);
        arr1.add(26);
        arr1.add(36);
        ArrayList<Integer> arr2=new ArrayList<Integer>();
        arr2.add(2);
        arr2.add(7);
        arr2.add(48);
        ArrayList<Integer> rsArr = mergeArray(arr1,arr2);
        System.out.println("test3开始输出结果:");
        for(Integer s:rsArr){
            System.out.print(s+",");
        }
    }
}
