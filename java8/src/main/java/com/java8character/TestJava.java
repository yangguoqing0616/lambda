package com.java8character;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TestJava {

    public static void main(String[] args) {
        List<Map<String,String>> dataList = Lists.newArrayList();
        Map<String,String> temp = null;
        for(int i = 0; i < 10; i++) {
            temp = Maps.newHashMap();
            temp.put("id", String.valueOf((int)(Math.random() * 10)));
            temp.put("name", String.valueOf((int)(Math.random() * 10)));
            dataList.add(temp);
        }
        System.out.println("before:::" + JSON.toJSONString(dataList));
        Collections.sort(dataList, new Comparator<Map<String,String>>(){
            public int compare(Map<String,String> o1, Map<String,String> o2) {
                if(Integer.parseInt(o1.get("id")) > Integer.parseInt(o2.get("id"))){
                    return -1;
                }
                if(Integer.parseInt(o1.get("id")) == Integer.parseInt(o2.get("id"))){
                    return 0;
                }
                return 1;
            }
        });
        System.out.println(JSON.toJSONString(dataList));
    }
}
