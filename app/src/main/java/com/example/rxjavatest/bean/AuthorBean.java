package com.example.rxjavatest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fan.zhengxi on 2020/9/21
 * Describe:
 */
public class AuthorBean {
    private String name;
    private List<String> articleList=new ArrayList<>();//每个作者有一个文章列表
    public AuthorBean(){}
    public AuthorBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<String> articleList) {
        this.articleList = articleList;
    }
}
