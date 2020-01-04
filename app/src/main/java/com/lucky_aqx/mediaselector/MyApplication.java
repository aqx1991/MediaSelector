package com.lucky_aqx.mediaselector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Gravity;

import androidx.multidex.MultiDexApplication;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.lucky_aqx.media.utils.CommonContext;
import com.lucky_aqx.media.utils.LogUtils;
import com.lucky_aqx.mediaselector.common.utils.UIHelper;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends MultiDexApplication {

    public static boolean DEBUG_MODE = true;//是否是DEBUG模式

    public static List<Activity> activityList = new ArrayList<>();

    public static MyApplication applicationContext;

    public static int Screen_H, Screen_W;

    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();

        DEBUG_MODE = BuildConfig.DEBUG_MODE;

        applicationContext = this;

        //耗时操作放在子线程执行
        initApplication();
    }

    private void initApplication() {

        LogUtils.setIsDebug(DEBUG_MODE);

        Utils.init(applicationContext);
        ToastUtils.setMsgTextSize(UIHelper.getDimensionPixelSize(R.dimen.textsize_4));
        ToastUtils.setMsgColor(UIHelper.getColor(R.color.text_color_black));
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 480);
        CommonContext.init(applicationContext);

        getScreen();//获取屏幕宽高
    }

    public static MyApplication getInstance() {
        if (null == applicationContext)
            applicationContext = new MyApplication();
        return applicationContext;
    }

    public static Handler getHandler() {
        if (null == handler)
            handler = new Handler(Looper.getMainLooper());
        return handler;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName() {
        String versionName = "";
        try {
            PackageManager pm = applicationContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(applicationContext.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            LogUtils.e("VersionInfo Exception", e);
        }
        return versionName;
    }

    /**
     * 返回当前程序版本号
     */
    public static int getAppVersionCode() {
        int versioncode = 0;
        try {
            PackageManager pm = applicationContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(applicationContext.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            LogUtils.e("VersionInfo Exception", e);
        }
        return versioncode;
    }

    public static void addActivity(Activity activity) {
        if (null != activity && !activityList.contains(activity))
            activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        //判断当前集合中存在该Activity
        if (null != activity && activityList.contains(activity)) {
            activityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    public static void exitActivity() {
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
            activityList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeApp(Context context) {

        exitActivity();

        if (null != handler)
            handler.removeCallbacksAndMessages(null);
        handler = null;

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

    public static void getScreen() {
        DisplayMetrics dm = applicationContext.getResources().getDisplayMetrics();
        Screen_H = dm.heightPixels;
        Screen_W = dm.widthPixels;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
