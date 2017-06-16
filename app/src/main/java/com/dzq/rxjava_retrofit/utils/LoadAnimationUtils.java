package com.dzq.rxjava_retrofit.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.dzq.rxjava_retrofit.R;

import static android.view.View.GONE;

/**
 * Created by dingzuoqiang on 2017/6/16.
 * Email: 530858106@qq.com
 */

public class LoadAnimationUtils {
    public Dialog processDialog;
    private ImageView view;
    private Activity mContext;

    public LoadAnimationUtils(Activity context) {
        mContext = context;
    }


    public Dialog showProcessAnimation(String content) {
        if (mContext == null) return null;
        if (processDialog == null) {
            processDialog = new Dialog(mContext, R.style.progress_dialog);
        }
        View layout = LayoutInflater.from(mContext).inflate(R.layout.custom_loading_view, null);
        view = (ImageView) layout.findViewById(R.id.custom_toast_iv);
        TextView tv = (TextView) layout.findViewById(R.id.custom_toast_tv);
        final AnimationDrawable loadingDw = ((AnimationDrawable) view
                .getDrawable());
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                loadingDw.start();
                return true; // 必须要有这个true返回
            }
        });
        if (!TextUtils.isEmpty(content)) {
            tv.setText(content);
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(GONE);
        }

        processDialog.setContentView(layout);
        processDialog.setCancelable(false);
        processDialog.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent arg2) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    processDialog.dismiss();
                }
                return false;
            }
        });
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.dismiss();
        }

        processDialog.show();
        return processDialog;
    }

    public void showProcessAnimation() {
        showProcessAnimation(null);
    }

    public void closeProcessAnimation() {
        if (view != null)
            ((AnimationDrawable) view.getDrawable()).stop();
        if (processDialog != null && processDialog.isShowing()) {
            processDialog.dismiss();
        }
    }

}
