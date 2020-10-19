package com.example.rxjavatest.bean;

/**
 * @ClassName food
 * @Author fanzhengxi
 * @Date 2020/8/10 17:02
 * @Description： 水果盘
 */
public class Plate<T> {
    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
