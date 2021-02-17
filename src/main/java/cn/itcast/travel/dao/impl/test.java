package cn.itcast.travel.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        String sql = "select count(*) from tab_route where 1 = 1 ";
        List params = new ArrayList();
        // 遍历
        StringBuffer sb = new StringBuffer(sql);
        int cid = 12;
        params.add(cid);
        System.out.println(params.toArray()); // 012345

        // 字符串转数组  java.lang.String
        String str = "0,1,2,3,4,5";
        String[] arr = str.split(","); // 用,分割
        System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4, 5]

    }
}
