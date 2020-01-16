package com.lucky_aqx.mediaselector.ui.adapter;

import androidx.fragment.app.Fragment;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lucky_aqx.media.config.PictureConfig;
import com.lucky_aqx.media.glideapp.GlideDisplay;
import com.lucky_aqx.mediaselector.R;
import com.lucky_aqx.mediaselector.common.bean.HomeMediaBean;
import com.lucky_aqx.mediaselector.common.utils.UIHelper;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.zhuoyuyingcai.teamlink.ui.mvp.currency
 * 项目名称：TeamLink
 * 作者：James
 * 创建时间：2019-05-05 11:28
 * 描述：图片视频选择
 * 版本：V1.0
 */
public class MediaAdapter extends BaseMultiItemQuickAdapter<HomeMediaBean, BaseViewHolder> {

    private Fragment fragment;

    public MediaAdapter(Fragment fragment, List<HomeMediaBean> data) {
        super(data);
        this.fragment = fragment;
        addItemType(PictureConfig.TYPE_IMAGE, R.layout.adapter_media);
        addItemType(PictureConfig.TYPE_VIDEO, R.layout.adapter_media);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMediaBean item) {

        helper.setVisible(R.id.surface, !item.isEnable());

        helper.setImageResource(R.id.checkIv, item.isChecked() ? R.mipmap.sign_interest_select : R.mipmap.sign_interest_unselected);

        GlideDisplay.getInstance().displayImage(fragment, item.getPath(), helper.getView(R.id.imageView), R.color.layout_bg, R.color.layout_bg);
        switch (helper.getItemViewType()) {
            case PictureConfig.TYPE_IMAGE:
                helper.setVisible(R.id.durationTv, false);
                break;
            case PictureConfig.TYPE_VIDEO:
                helper.setVisible(R.id.durationTv, true);
                helper.setText(R.id.durationTv, formatseconds(item.getDuration()));
                break;
            default:
                break;
        }
    }

    private String formatseconds(long duration) {
        return UIHelper.getString(R.string.hh_mm_ss,
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}
