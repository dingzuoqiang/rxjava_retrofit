package com.dzq.rxjava_retrofit.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dzq.rxjava_retrofit.R;
import com.dzq.rxjava_retrofit.bean.OSSToken;
import com.dzq.rxjava_retrofit.bean.ResultBean;
import com.dzq.rxjava_retrofit.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class LoginActivity extends FragmentActivity {


    private String phoneNo;
    private String code = null;
    private String captchaCode = null;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    /**
     * POST
     * 发送验证码
     */
    public void sendGetVerificationCode() {

        Map<String, Object> map = new HashMap<>();
        map.put("userPhone", phoneNo);
        map.put("captchaCode", captchaCode);
        map.put("code", code);
        subscription = new HttpUtils().getPost("", true, this).sendCode(map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    // 回调
    Observer<ResultBean> observer = new HttpUtils.RxObserver<ResultBean>() {
        @Override
        public void onSuccess(ResultBean resultBean) {
            if (resultBean == null) return;
            if (resultBean.status == 0) {
//
            } else {

            }

        }

    };


    // GET
    private void ststoken() {
//        Map<String, Object> map = new HashMap<>();
//        map = CommonUtil.initTokenParams(map);
        subscription = new HttpUtils().getPost("", false, this).ststoken().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(ststokenObserver);
    }

    // 回调
    Observer<ResultBean<OSSToken>> ststokenObserver = new HttpUtils.RxObserver<ResultBean<OSSToken>>() {
        @Override
        public void onSuccess(ResultBean<OSSToken> resultBean) {
            if (resultBean == null || resultBean.data == null || resultBean.data.credentials == null)
                return;
        }
    };

}
