package com.dzq.rxjava_retrofit.http;

import com.dzq.rxjava_retrofit.bean.OSSToken;
import com.dzq.rxjava_retrofit.bean.ResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public interface AppApi {

    /**
     * 发送短信验证码
     *
     * @param account
     * @return
     */
    @FormUrlEncoded
    @POST("loanapi/user/loan/sendcode")
    Observable<ResultBean> sendCode(@FieldMap Map<String, Object> account);


    /**
     * 获取 oss  token
     *
     * @return
     */
    @GET("loanapi/oss/ststoken")
    Observable<ResultBean<OSSToken>> ststoken();
}
