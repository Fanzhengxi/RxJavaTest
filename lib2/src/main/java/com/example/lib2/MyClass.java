package com.example.lib2;

public class MyClass {
    public static void main(String[] args) {
        String str="01-010100-000010-000111-0000001-0000001-000011-0000000000001000-00000001";
        filterString(str,64);
    }
    public static String filterString(String param, int length) {
        String str = null;
        if (param!=null && param!="") {
            str = param.replaceAll("[\\p{Punct}\\p{Space}]+", "");
            //扩充长度，在前面补零
            while (str.length() < length) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("0").append(str);//在前面补零
                str = stringBuffer.toString();
            }
            System.out.println("str=" + str + ",str.length()=" + str.length());
        }
        return str;
    }

}