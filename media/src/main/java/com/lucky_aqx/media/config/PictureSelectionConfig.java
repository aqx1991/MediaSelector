package com.lucky_aqx.media.config;

import android.content.pm.ActivityInfo;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.ColorInt;
import androidx.annotation.StyleRes;

import com.lucky_aqx.media.utils.PictureFileUtils;

public final class PictureSelectionConfig implements Parcelable {
    public int chooseMode;
    public boolean camera;
    public String compressSavePath;
    public String suffixType;
    public boolean focusAlpha;
    public String renameCompressFileName;
    public String specifiedFormat;
    public int requestedOrientation;
    public boolean isCameraAroundState;
    public boolean isAndroidQTransform;
    @StyleRes
    public int videoQuality;
    public int videoMaxSecond;
    public int videoMinSecond;
    public int recordVideoSecond;
    public int minimumCompressSize;
    public int aspect_ratio_x;
    public int aspect_ratio_y;
    public int cropWidth;
    public int cropHeight;
    public int compressQuality;
    public int filterFileSize;
    public int language;
    public boolean zoomAnim;
    public boolean isCompress;
    public boolean isOriginalControl;
    public boolean isGif;
    public boolean checkNumMode;
    public boolean openClickSound;
    @ColorInt
    public int circleDimmedColor;
    @ColorInt
    public int circleDimmedBorderColor;
    public int circleStrokeWidth;
    public boolean synOrAsy;
    public boolean returnEmpty;
    public boolean isNotPreviewDownload;
    public boolean isWithVideoImage;
    public boolean isCheckOriginalImage;
    @Deprecated
    public int overrideWidth;
    @Deprecated
    public int overrideHeight;
    @Deprecated
    public float sizeMultiplier;
    @Deprecated
    public boolean isChangeStatusBarFontColor;
    @Deprecated
    public boolean isOpenStyleNumComplete;
    @Deprecated
    public boolean isOpenStyleCheckNumMode;
    @Deprecated
    public int titleBarBackgroundColor;
    @Deprecated
    public int pictureStatusBarColor;
    @Deprecated
    public int cropTitleBarBackgroundColor;
    @Deprecated
    public int cropStatusBarColorPrimaryDark;
    @Deprecated
    public int cropTitleColor;
    @Deprecated
    public int upResId;
    @Deprecated
    public int downResId;
    @Deprecated
    public String outputCameraPath;

    public String originalPath;
    public String cameraPath;

    private void initDefaultValue() {
        chooseMode = PictureMimeType.ofImage();
        camera = false;
        videoQuality = 1;
        language = -1;
        videoMaxSecond = 0;
        videoMinSecond = 0;
        filterFileSize = -1;
        recordVideoSecond = 60;
        compressQuality = 60;
        minimumCompressSize = PictureConfig.MAX_COMPRESS_SIZE;
        isCompress = false;
        isOriginalControl = false;
        aspect_ratio_x = 0;
        aspect_ratio_y = 0;
        cropWidth = 0;
        cropHeight = 0;
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        isCameraAroundState = false;
        isWithVideoImage = false;
        isAndroidQTransform = true;
        isGif = false;
        focusAlpha = false;
        isCheckOriginalImage = false;
        checkNumMode = false;
        isNotPreviewDownload = false;
        openClickSound = false;
        returnEmpty = false;
        synOrAsy = true;
        zoomAnim = true;
        circleDimmedColor = 0;
        circleDimmedBorderColor = 0;
        circleStrokeWidth = 1;
        compressSavePath = "";
        suffixType = PictureFileUtils.POSTFIX;
        specifiedFormat = "";
        renameCompressFileName = "";
        titleBarBackgroundColor = 0;
        pictureStatusBarColor = 0;
        cropTitleBarBackgroundColor = 0;
        cropStatusBarColorPrimaryDark = 0;
        cropTitleColor = 0;
        upResId = 0;
        downResId = 0;
        isChangeStatusBarFontColor = false;
        isOpenStyleNumComplete = false;
        isOpenStyleCheckNumMode = false;
        outputCameraPath = "";
        sizeMultiplier = 0.5f;
        overrideWidth = 0;
        overrideHeight = 0;
        originalPath = "";
        cameraPath = "";
    }

    public static PictureSelectionConfig getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static PictureSelectionConfig getCleanInstance() {
        PictureSelectionConfig selectionSpec = getInstance();
        selectionSpec.initDefaultValue();
        return selectionSpec;
    }

    private static final class InstanceHolder {
        private static final PictureSelectionConfig INSTANCE = new PictureSelectionConfig();
    }

    public PictureSelectionConfig() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.chooseMode);
        dest.writeByte(this.camera ? (byte) 1 : (byte) 0);
        dest.writeString(this.compressSavePath);
        dest.writeString(this.suffixType);
        dest.writeByte(this.focusAlpha ? (byte) 1 : (byte) 0);
        dest.writeString(this.renameCompressFileName);
        dest.writeString(this.specifiedFormat);
        dest.writeInt(this.requestedOrientation);
        dest.writeByte(this.isCameraAroundState ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isAndroidQTransform ? (byte) 1 : (byte) 0);
        dest.writeInt(this.videoQuality);
        dest.writeInt(this.videoMaxSecond);
        dest.writeInt(this.videoMinSecond);
        dest.writeInt(this.recordVideoSecond);
        dest.writeInt(this.minimumCompressSize);
        dest.writeInt(this.aspect_ratio_x);
        dest.writeInt(this.aspect_ratio_y);
        dest.writeInt(this.cropWidth);
        dest.writeInt(this.cropHeight);
        dest.writeInt(this.compressQuality);
        dest.writeInt(this.filterFileSize);
        dest.writeInt(this.language);
        dest.writeByte(this.zoomAnim ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCompress ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOriginalControl ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isGif ? (byte) 1 : (byte) 0);
        dest.writeByte(this.checkNumMode ? (byte) 1 : (byte) 0);
        dest.writeByte(this.openClickSound ? (byte) 1 : (byte) 0);
        dest.writeInt(this.circleDimmedColor);
        dest.writeInt(this.circleDimmedBorderColor);
        dest.writeInt(this.circleStrokeWidth);
        dest.writeByte(this.synOrAsy ? (byte) 1 : (byte) 0);
        dest.writeByte(this.returnEmpty ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isNotPreviewDownload ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isWithVideoImage ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCheckOriginalImage ? (byte) 1 : (byte) 0);
        dest.writeInt(this.overrideWidth);
        dest.writeInt(this.overrideHeight);
        dest.writeFloat(this.sizeMultiplier);
        dest.writeByte(this.isChangeStatusBarFontColor ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOpenStyleNumComplete ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOpenStyleCheckNumMode ? (byte) 1 : (byte) 0);
        dest.writeInt(this.titleBarBackgroundColor);
        dest.writeInt(this.pictureStatusBarColor);
        dest.writeInt(this.cropTitleBarBackgroundColor);
        dest.writeInt(this.cropStatusBarColorPrimaryDark);
        dest.writeInt(this.cropTitleColor);
        dest.writeInt(this.upResId);
        dest.writeInt(this.downResId);
        dest.writeString(this.outputCameraPath);
        dest.writeString(this.originalPath);
        dest.writeString(this.cameraPath);
    }

    protected PictureSelectionConfig(Parcel in) {
        this.chooseMode = in.readInt();
        this.camera = in.readByte() != 0;
        this.compressSavePath = in.readString();
        this.suffixType = in.readString();
        this.focusAlpha = in.readByte() != 0;
        this.renameCompressFileName = in.readString();
        this.specifiedFormat = in.readString();
        this.requestedOrientation = in.readInt();
        this.isCameraAroundState = in.readByte() != 0;
        this.isAndroidQTransform = in.readByte() != 0;
        this.videoQuality = in.readInt();
        this.videoMaxSecond = in.readInt();
        this.videoMinSecond = in.readInt();
        this.recordVideoSecond = in.readInt();
        this.minimumCompressSize = in.readInt();
        this.aspect_ratio_x = in.readInt();
        this.aspect_ratio_y = in.readInt();
        this.cropWidth = in.readInt();
        this.cropHeight = in.readInt();
        this.compressQuality = in.readInt();
        this.filterFileSize = in.readInt();
        this.language = in.readInt();
        this.zoomAnim = in.readByte() != 0;
        this.isCompress = in.readByte() != 0;
        this.isOriginalControl = in.readByte() != 0;
        this.isGif = in.readByte() != 0;
        this.checkNumMode = in.readByte() != 0;
        this.openClickSound = in.readByte() != 0;
        this.circleDimmedColor = in.readInt();
        this.circleDimmedBorderColor = in.readInt();
        this.circleStrokeWidth = in.readInt();
        this.synOrAsy = in.readByte() != 0;
        this.returnEmpty = in.readByte() != 0;
        this.isNotPreviewDownload = in.readByte() != 0;
        this.isWithVideoImage = in.readByte() != 0;
        this.isCheckOriginalImage = in.readByte() != 0;
        this.overrideWidth = in.readInt();
        this.overrideHeight = in.readInt();
        this.sizeMultiplier = in.readFloat();
        this.isChangeStatusBarFontColor = in.readByte() != 0;
        this.isOpenStyleNumComplete = in.readByte() != 0;
        this.isOpenStyleCheckNumMode = in.readByte() != 0;
        this.titleBarBackgroundColor = in.readInt();
        this.pictureStatusBarColor = in.readInt();
        this.cropTitleBarBackgroundColor = in.readInt();
        this.cropStatusBarColorPrimaryDark = in.readInt();
        this.cropTitleColor = in.readInt();
        this.upResId = in.readInt();
        this.downResId = in.readInt();
        this.outputCameraPath = in.readString();
        this.originalPath = in.readString();
        this.cameraPath = in.readString();
    }

    public static final Creator<PictureSelectionConfig> CREATOR = new Creator<PictureSelectionConfig>() {
        @Override
        public PictureSelectionConfig createFromParcel(Parcel source) {
            return new PictureSelectionConfig(source);
        }

        @Override
        public PictureSelectionConfig[] newArray(int size) {
            return new PictureSelectionConfig[size];
        }
    };
}
