package com.lucky_aqx.media;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.lucky_aqx.media.utils.FileUtils;

import java.io.InputStream;

@GlideModule
public final class MyGlideModule extends AppGlideModule {

    // 默认存放图片的路径
    public static final String DEFAULT_SAVE_IMAGE_PATH = FileUtils.getDir("Images");

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);

        if (BuildConfig.DEBUG_MODE)
            builder.setLogLevel(Log.DEBUG);

        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB

        builder.setDiskCache(new DiskLruCacheFactory(DEFAULT_SAVE_IMAGE_PATH, diskCacheSizeBytes));

//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, DEFAULT_SAVE_IMAGE_PATH, diskCacheSizeBytes));

        //设置Glide内存缓存大小
//        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
//        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一

        //设置手机默认推荐缓存大小。MemorySizeCalculator类通过考虑设备给定的可用内存和屏幕大小想出合理的默认大小.也可以自定义大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .setBitmapPoolScreens(3)
                .build();
        builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));

        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(calculator.getBitmapPoolSize()));

        // 全局设置图片格式为RGB_565(设置为RGB_565会导致加载GIF图片卡顿)
//        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//        super.registerComponents(context, glide, registry);
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        //设置清单解析，设置为false，避免添加相同的modules两次
//        return super.isManifestParsingEnabled();
        return false;
    }
}
