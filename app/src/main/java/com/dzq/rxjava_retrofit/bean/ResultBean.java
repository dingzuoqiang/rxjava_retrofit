package com.dzq.rxjava_retrofit.bean;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class ResultBean<T> {
    public int status;
    public String msg;
    public T data;
}
