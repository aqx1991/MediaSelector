package com.lucky_aqx.mediaselector.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lucky_aqx.mediaselector.ui.weight.CustomProgressDialog;
import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 描述：Fragment基类
 * 版本：V1.0
 */
public abstract class BaseFragment extends RxFragment {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder bind;

    protected Context context;

    private static final int DISMISS = 1001;
    private static final int SHOW = 1002;
    private CustomProgressDialog progressDialog = null;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);

        bind = ButterKnife.bind(this, inflate);

        context = getActivity();

        if (progressDialog == null)
            progressDialog = new CustomProgressDialog(context);

        initView(savedInstanceState);

        initListener();

        return inflate;
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

    public abstract void initListener();

    @Override
    public void onResume() {
        super.onResume();
        if (progressDialog == null) {
            progressDialog = new CustomProgressDialog(context);
        }
    }

    /**
     * 显示加载视图
     *
     * @param isTouchAble true:可点击 false:不可点击 默认是可点击
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

    private boolean isShowingLoadingView() {
        if (progressDialog != null) {
            return progressDialog.isShowing();
        } else {
            return false;
        }
    }

    @Override
    public void onPause() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;

        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (bind != null) {
            bind.unbind();
        }
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
        super.onDestroy();
    }

}
