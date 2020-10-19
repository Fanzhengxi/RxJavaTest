package com.example.rxjavatest;

import android.annotation.SuppressLint;

import com.example.rxjavatest.bean.AuthorBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by fan.zhengxi on 2020/9/21
 * Describe:
 */
public class DataUtil {
    private static List<AuthorBean> authorBeanList= new ArrayList<>();

    public static List<AuthorBean> getAuthorList() {
        initAuthor("张三", 1);
        initAuthor("李四", 2);
        initAuthor("鲁迅", 3);
        return authorBeanList;
    }

    public static void initAuthor(String name, int size) {
        AuthorBean authorBean = new AuthorBean();
        authorBean.setName(name);
        List<String> articleList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            articleList.add("《" + name + "的第" + i + "本书" + "》");
        }
        authorBean.setArticleList(articleList);
        authorBeanList.add(authorBean);
    }

    @SuppressLint("CheckResult")
    public static List<AuthorBean> initAuthorList() {
        final int[] i = {0};
        Observable.just("张三", "李四", "鲁迅")
                .map(new Function<String, AuthorBean>() {
                    @Override
                    public AuthorBean apply(String s) throws Exception {
                        AuthorBean authorBean = new AuthorBean(s);
                        return authorBean;
                    }
                })
                .map(new Function<AuthorBean, AuthorBean>() {
                    @Override
                    public AuthorBean apply(AuthorBean authorBean) throws Exception {
                        authorBean.getArticleList().add(authorBean.getName()+"的书"+ i[0]++);
                        authorBean.getArticleList().add(authorBean.getName()+"的第"+i[0]++);
                        return authorBean;
                    }
                })
                .map(new Function<AuthorBean,List<AuthorBean>>() {
                    @Override
                    public List<AuthorBean> apply(AuthorBean authorBean) throws Exception {
                        authorBeanList.add(authorBean);
                        return authorBeanList;
                    }
                }).subscribe();
        return authorBeanList;

    }
    public static List<Integer> getIntegerList(){
        List<Integer> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(i);
            //模拟耗时操作
            try {
                new Thread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
