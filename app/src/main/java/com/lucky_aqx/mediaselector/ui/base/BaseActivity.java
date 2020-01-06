package com.lucky_aqx.mediaselector.ui.base;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lucky_aqx.media.utils.LogUtils;
import com.lucky_aqx.mediaselector.MyApplication;
import com.lucky_aqx.mediaselector.R;
import com.lucky_aqx.mediaselector.common.utils.UIHelper;
import com.lucky_aqx.mediaselector.ui.base.permission.PermissionActivity;
import com.lucky_aqx.mediaselector.ui.weight.CustomProgressDialog;
import com.trello.rxlifecycle3.android.ActivityEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * 描述：Activity基类
 * 版本：V1.0
 */
public abstract class BaseActivity extends PermissionActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected boolean isActive = true; //是否活跃

    protected InputMethodManager imm;

    protected static final int DISMISS = 1001;
    protected static final int SHOW = 1002;
    protected CustomProgressDialog progressDialog = null;

    protected ProgressDialog mProgressDialog;

    protected InputMethodManager inputMethodManager;

    protected Context context;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW:
                    if (progressDialog != null) {
                        progressDialog.setTouchAble((Boolean) msg.obj);
                        progressDialog.show();
                    }
                    break;
                case DISMISS:
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public <T> void StartActivity(Class<T> tClass) {
        Intent intent = new Intent();
        intent.setClass(context, tClass);
        context.startActivity(intent);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(() -> LogUtils.i(TAG, "Unsubscribing subscription from onCreate()"))
                .compose(this.<Long>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(num -> {
                    if (num < 3)
                        LogUtils.i(TAG, "Started in onCreate(), running until onDestroye(): " + num);
                });

        context = this;

        progressDialog = new CustomProgressDialog(this);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        MyApplication.addActivity(this);

        initView(savedInstanceState);
        initListener();

        if (!NetworkUtils.isConnected())
            ToastUtils.showShort(UIHelper.getString(R.string.net_error));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initListener();

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    @Override
    protected void onDestroy() {

        this.imm = null;

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;

        if (null != mHandler) {
            mHandler.removeMessages(DISMISS);
            mHandler.removeMessages(SHOW);
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }

        MyApplication.removeActivity(this);

        super.onDestroy();
    }

    @Override
    protected void onStop() {

        hideProgress();
        mProgressDialog = null;

        if (!isAppOnForeground()) {
            //app 进入后台
            //全局变量 记录当前已经进入后台
            isActive = false;
            LogUtils.i(TAG, "进入后台");
        }

        super.onStop();
    }

    @Override
    protected void onResume() {

        if (!isActive) {
            //app 从后台唤醒，进入前台
            isActive = true;
            LogUtils.i(TAG, "进入前台");
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        hideSoftKeyBoard();

        super.onPause();
    }

    /**
     * 显示加载视图
     * <p>
     * true:可点击 false:不可点击 默认是可点击
     */
    protected void showLoadingView(boolean isTouchAble) {
        if (null != mHandler && !isShowingLoadingView()) {
            Message m = mHandler.obtainMessage(SHOW, isTouchAble);
            mHandler.sendMessage(m);
        }
    }

    protected void showLoadingView() {
        if (!isShowingLoadingView())
            showLoadingView(true);
    }

    /**
     * 关闭加载视图
     */
    protected void dismissLoadingView() {
        if (null != mHandler)
            mHandler.sendEmptyMessage(DISMISS);
    }

    protected boolean isShowingLoadingView() {
        if (progressDialog != null) {
            return progressDialog.isShowing();
        } else {
            return false;
        }
    }


    public ProgressDialog showProgress(String title, String message) {
        return showProgress(title, message, -1);
    }

    public ProgressDialog showProgress(String title, String message, int theme) {
        if (mProgressDialog == null) {
            if (theme > 0)
                mProgressDialog = new ProgressDialog(this, theme);
            else
                mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setCanceledOnTouchOutside(false);// 不能取消
            mProgressDialog.setIndeterminate(true);// 设置进度条是否不明确
        }

        if (!TextUtils.isEmpty(title))
            mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
        return mProgressDialog;
    }

    public void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public void backFinish(View v) {
        hideSoftKeyBoard();
        MyApplication.removeActivity(this);
        finish();
    }

    @Override
    public void finish() {
        hideSoftKeyBoard();
        super.finish();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LogUtils.i("memory -- info -->", level + "");
    }

    /**
     * 程序是否在前台运行
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }
}
