package com.dzq.rxjava_retrofit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.dzq.rxjava_retrofit.application.MyApplicaion;

import org.json.JSONObject;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class CommonUtil {

    private static long lastClickTime;
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

    /**
     * 防止频繁点击
     *
     * @returna
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    private static long lastClickTimeWithCustom;

    /**
     * 防止频繁点击
     *
     * @returna
     */
    public static boolean isFastDoubleClick(double second) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTimeWithCustom;
        if (0 < timeD && timeD < second * 1000) {
            return true;
        }
        lastClickTimeWithCustom = time;
        return false;
    }

    public static String date2String(Date time, String format) {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat(format);
            return dateformat.format(time);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * String  pattern   ;"yyyy-MM-dd HH:mm:ss"
     * srcDate 格式要与 srcDatePattern 一致
     */
    public static String getDateFormat(String srcDate, String srcDatePattern, String pattern) {
        if (TextUtils.isEmpty(srcDate))
            return "";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.format(new SimpleDateFormat(srcDatePattern).parse(srcDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 判断网络是否可用
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断手机号格式是否正确
     */
    public static boolean isMobileNO(String str) {
        try {
            String regExp = "^[1][0-9]{10}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(str);
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 接口头参数
     */
    public static Map getHeaderParamsMap(Context context) {
        Map<String, Object> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");
        map.put("Accept-Charset", "utf-8");
        map.put("plat", "Android");// 用户平台
        map.put("platform", android.os.Build.VERSION.RELEASE);// 手机版本号
        map.put("model", android.os.Build.MODEL);// 手机型号
        map.put("versionName", getVersionName(MyApplicaion.getContext()));// app版本名称
        map.put("version", String.valueOf(getVersionCode(MyApplicaion.getContext())));//app版本号
        return map;
    }


    /**
     * 获取版本名称
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 加载H5页面时需要post的参数
     */
    public static byte[] getPostParams(JSONObject jsonObject) {
        Iterator<String> sIterator = jsonObject.keys();
        StringBuilder sb = new StringBuilder();
        try {
            while (sIterator.hasNext()) {
                String key = sIterator.next();
                // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
                String value = jsonObject.optString(key);
                sb.append(key + "=" + URLEncoder.encode(value, "UTF-8") + "&");
            }
            return sb.deleteCharAt(sb.length() - 1).toString().getBytes("UTF-8");
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取渠道名
     *
     * @param ctx 此处习惯性的设置为activity，实际上context就可以
     * @return 如果没有获取成功，那么返回值为空
     */
    public static String getChannelName(Activity ctx) {
        if (ctx == null) {
            return null;
        }
        String channelName = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                //注意此处为ApplicationInfo 而不是 ActivityInfo,因为友盟设置的meta-data是在application标签中，而不是某activity标签中，所以用ApplicationInfo
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        //此处这样写的目的是为了在debug模式下也能获取到渠道号，如果用getString的话只能在Release下获取到。
                        channelName = applicationInfo.metaData.get("UMENG_CHANNEL") + "";
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelName;

    }


    /**
     * 获取屏幕的宽度
     *
     * @param mContext
     * @return
     */
    public static int getScreenWidth(Context mContext) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param mContext
     * @return
     */
    public static int getScreenHeight(Context mContext) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void openActicity(Context context, Class<?> class1,
                                    Bundle pBundle) {
        Intent intent = new Intent(context, class1);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        context.startActivity(intent);

    }

    /**
     * 跳转到新的activity
     *
     * @param activity      现在的Activity
     * @param class1        目标Activity
     * @param pBundle
     * @param closeActivity 是否关闭当前Activity
     */
    public static void openActicity(Activity activity, Class<?> class1,
                                    Bundle pBundle, boolean closeActivity) {
        Intent intent = new Intent(activity, class1);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        activity.startActivity(intent);
        // activity.overridePendingTransition(R.anim.slide_in_right,
        // R.anim.slide_out_left);
        if (closeActivity) {
            activity.finish();
        }

    }


    /**
     * 对double数据进行取精度, 保留2位小数, 精度取值方式 BigDecimal.ROUND_HALF_UP
     *
     * @param value double数据
     * @return 精度计算后的数据
     */
    public static double round2(double value) {
        return round(value, 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 对double数据进行取精度
     *
     * @param value        double数据
     * @param scale        精度位数(保留的小数位数)
     * @param roundingMode 精度取值方式
     * @return 精度计算后的数据
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }

//    roundingMode的取值有一下几个：
//
//    ROUND_CEILING
//            大于等于该数的那个最近值
//    ROUND_DOWN
//    正数是小于等于该数的那个最近数，负数是大于等于该数的那个最近数
//            ROUND_FLOOR
//    小于等于该数的那个值
//            ROUND_HALF_DOWN
//    五舍六入
//            ROUND_HALF_EVEN
//    向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP ，如果是偶数，使用ROUND_HALF_DOWN
//            ROUND_HALF_UP
//    四舍五入
//            ROUND_UNNECESSARY
//    计算结果是精确的，不需要舍入模式
//            ROUND_UP
//    和ROUND_DOWN相反


    /**
     * 获取网落图片资源
     *
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;

    }

}
