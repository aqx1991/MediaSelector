package com.lucky_aqx.mediaselector.common.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lucky_aqx.media.config.PictureConfig;
import com.lucky_aqx.media.entry.LocalMedia;

/**
 * 包名：com.lucky_aqx.mediaselector.common.bean
 * 项目名称：MediaSelector
 * 作者：James
 * 创建时间：2020-01-15 14:18
 * 描述：首页列表
 * 版本：V1.0
 */
public class HomeMediaBean extends LocalMedia implements MultiItemEntity {

    private int itemType = PictureConfig.TYPE_IMAGE;

    private boolean enable;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public HomeMediaBean(LocalMedia localMedia, int itemType, boolean enable) {
        this.setId(localMedia.getId());
        this.setPath(localMedia.getPath());
        this.setOriginalPath(localMedia.getOriginalPath());
        this.setCompressPath(localMedia.getCompressPath());
        this.setCutPath(localMedia.getCutPath());
        this.setAndroidQToPath(localMedia.getAndroidQToPath());
        this.setDuration(localMedia.getDuration());
        this.setChecked(localMedia.isChecked());
        this.setCut(localMedia.isCut());
        this.setPosition(localMedia.getPosition());
        this.setNum(localMedia.getNum());
        this.setMimeType(localMedia.getMimeType());
        this.setChooseModel(localMedia.getChooseModel());
        this.setCompressed(localMedia.isCompressed());
        this.setWidth(localMedia.getWidth());
        this.setHeight(localMedia.getHeight());
        this.setSize(localMedia.getSize());
        this.setOriginal(localMedia.isOriginal());
        this.setFileName(localMedia.getFileName());

        this.setEnable(enable);
        this.setItemType(itemType);
    }
}
