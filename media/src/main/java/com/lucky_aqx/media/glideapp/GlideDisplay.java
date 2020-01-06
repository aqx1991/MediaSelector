package com.lucky_aqx.media.glideapp;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lucky_aqx.media.GlideApp;
import com.lucky_aqx.media.R;
import com.lucky_aqx.media.utils.LogUtils;


/**
 * 描述：描述信息
 * 版本：V1.0
 */
public class GlideDisplay {

    private String TAG = this.getClass().getSimpleName();

    private GlideDisplay() {

    }

    private static class GlideDisplayHolder {
        private final static GlideDisplay INSTANCE = new GlideDisplay();
    }

    public static GlideDisplay getInstance() {
        return GlideDisplayHolder.INSTANCE;
    }

    //displayHeadImage  展示头像 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void displayHeadImage(Fragment fragment, String url, ImageView imageView) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentouxiang)
                    .error(R.mipmap.morentouxiang)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    public void displayHeadImage(Activity activity, String url, ImageView imageView) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentouxiang)
                    .error(R.mipmap.morentouxiang)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is Destroyed");
        }
    }

    public void displayHeadImage(Context context, String url, ImageView imageView) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentouxiang)
                    .error(R.mipmap.morentouxiang)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

    //displayHeadImage  展示图片 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void displayImage(Activity activity, String url, ImageView imageView) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is Destroyed");
        }
    }

    public void displayImage(Context context, String url, ImageView imageView) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

    public void displayImage(Fragment fragment, String url, ImageView imageView) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    //displayImageOverride  展示图片 重写宽高 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void displayImageOverride(Activity activity, String url, ImageView imageView, int width, int height) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(width, height)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is Destroyed");
        }
    }

    public void displayImageOverride(Fragment fragment, String url, ImageView imageView, int width, int height) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(width, height)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    public void displayImageOverride(Context context, String url, ImageView imageView, int width, int height) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(width, height)
                    .placeholder(R.mipmap.morentupian)
                    .error(R.mipmap.morentupian)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

    //displayImage  展示图片 设置占位图、失败展示图 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void displayImage(Activity activity, String url, ImageView imageView, int placeholder, int error) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is destroyed");
        }
    }

    public void displayImage(Fragment fragment, String url, ImageView imageView, int placeholder, int error) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    public void displayImage(Context context, String url, ImageView imageView, int placeholder, int error) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeholder)
                    .error(error)
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

    //XPopDisplayImage  展示图片 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void XPopDisplayImage(Activity activity, Object url, ImageView imageView) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentupian)
                            .error(R.mipmap.morentupian)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is destoryed");
        }
    }

    public void XPopDisplayImage(Fragment fragment, Object url, ImageView imageView) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentupian)
                            .error(R.mipmap.morentupian)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    public void XPopDisplayImage(Context context, Object url, ImageView imageView) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentupian)
                            .error(R.mipmap.morentupian)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

    //XPopDisplayHeadImage  展示头像 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void XPopDisplayHeadImage(Activity activity, Object url, ImageView imageView) {
        if (imageView != null && activity != null && !activity.isDestroyed()) {
            GlideApp
                    .with(activity)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentouxiang)
                            .error(R.mipmap.morentouxiang)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,activity or imageView is null or activity is destroyed");
        }
    }

    public void XPopDisplayHeadImage(Fragment fragment, Object url, ImageView imageView) {
        if (imageView != null && fragment != null && fragment.getActivity() != null) {
            GlideApp
                    .with(fragment)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentouxiang)
                            .error(R.mipmap.morentouxiang)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,fragment or imageView is null");
        }
    }

    public void XPopDisplayHeadImage(Context context, Object url, ImageView imageView) {
        if (imageView != null && context != null) {
            GlideApp
                    .with(context)
                    .load(url)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.morentouxiang)
                            .error(R.mipmap.morentouxiang)
                            .override(Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888))
                    .into(imageView);
        } else {
            LogUtils.i(TAG, "Picture loading failed,context or imageView is null");
        }
    }

}
