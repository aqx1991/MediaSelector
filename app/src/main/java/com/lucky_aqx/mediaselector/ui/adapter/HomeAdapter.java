package com.lucky_aqx.mediaselector.ui.adapter;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lucky_aqx.media.config.PictureConfig;
import com.lucky_aqx.media.glideapp.GlideDisplay;
import com.lucky_aqx.mediaselector.R;
import com.lucky_aqx.mediaselector.common.bean.HomeMediaBean;

import java.util.List;

/**
 * 包名：com.lucky_aqx.mediaselector.ui.adapter
 * 项目名称：MediaSelector
 * 作者：James
 * 创建时间：2020-01-15 14:13
 * 描述：首页列表
 * 版本：V1.0
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMediaBean, BaseViewHolder> {

    private Activity activity;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(Activity activity, List<HomeMediaBean> data) {
        super(data);
        this.activity = activity;
        addItemType(PictureConfig.TYPE_IMAGE, R.layout.adp_home_img);
        addItemType(PictureConfig.TYPE_VIDEO, R.layout.adp_home_video);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomeMediaBean item) {
        helper.addOnClickListener(R.id.delIb);
        switch (helper.getItemViewType()) {
            case PictureConfig.TYPE_IMAGE:
                GlideDisplay.getInstance().displayImage(activity, item.getPath(), helper.getView(R.id.img));
                helper.addOnClickListener(R.id.img);
                break;
            case PictureConfig.TYPE_VIDEO:
                GlideDisplay.getInstance().displayImage(activity, item.getPath(), helper.getView(R.id.videoView), R.color.layout_bg, R.color.layout_bg);
                helper.addOnClickListener(R.id.icon_play);
                break;
            default:
                break;
        }
    }
}
