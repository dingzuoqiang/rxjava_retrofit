package com.dzq.rxjava_retrofit.http;

import android.app.Activity;
import android.app.Dialog;

import com.dzq.rxjava_retrofit.application.Urls;
import com.dzq.rxjava_retrofit.utils.CommonUtil;
import com.dzq.rxjava_retrofit.utils.LoadAnimationUtils;
import com.dzq.rxjava_retrofit.utils.LogUtil;
import com.jkyeo.basicparamsinterceptor.BasicParamsInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class HttpUtils {
    public static HttpUtils mHttpUtils;
    private static AppApi api;

    private static OkHttpClient okHttpClient = null;
    private LoadAnimationUtils loadAnimationUtils = null;
    public static Dialog processDialog;

    public static HttpUtils HttpUtils() {
        if (null == mHttpUtils) {
            mHttpUtils = new HttpUtils();
        }
        return mHttpUtils;
    }


    public AppApi getPost(String text, boolean isShow, Activity activity) {
        if (isShow) {
            if (activity != null) {
                loadAnimationUtils = new LoadAnimationUtils(activity);
                processDialog = loadAnimationUtils.showProcessAnimation(text);
            }
        }
        BasicParamsInterceptor basicParamsInterceptor =
                new BasicParamsInterceptor.Builder()
                        .addHeaderParamsMap(CommonUtil.getHeaderParamsMap(activity))
                        .build();
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(basicParamsInterceptor)
                .build();
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Urls.dynamicBaseUrl())
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            api = retrofit.create(AppApi.class);
        }
        return api;
    }

    public static abstract class RxObserver<T> implements Observer<T> {

//        @Override
//        public void onStart() {
//            super.onStart();
//            LogUtil.LogE(HttpUtils.class, "onStart");
//        }

        @Override
        public void onCompleted() {
            if (null != processDialog) {
                processDialog.dismiss();
            }

        }

        @Override
        public void onError(Throwable e) {
            if (null != processDialog) {
                processDialog.dismiss();
            }
            LogUtil.LogE(HttpUtils.class, "error-->   " + e.getMessage());
            e.printStackTrace();
            //在这里做全局的错误处理
            if (e instanceof HttpException) {
                //ToastUtils.getInstance().showToast(e.getMessage());
            }
            //  ToastUtils.getInstance().showToast(e.getMessage());
//            LogUtil.LogE(HomeFragment.class, "福利----->" + e.getMessage());
        }

        @Override
        public void onNext(T t) {
            LogUtil.LogE(HttpUtils.class, "next");
            onSuccess(t);
        }

        public abstract void onSuccess(T t);


    }

}
