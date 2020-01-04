package com.lucky_aqx.media.utils;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * 包名：com.common.util
 * 项目名称：TeamUnite
 * 作者：James
 * 创建时间：2019-06-11 17:29
 * 描述：描述信息
 * 版本：V1.0
 */
public class CommonContext {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void init(final Context context) {
        CommonContext.context = context;
    }

    private CommonContext() {

    }

    public static Context getContext() {
        if (null == context)
            throw new NullPointerException("Context can't be null");
        return context;
    }
}
