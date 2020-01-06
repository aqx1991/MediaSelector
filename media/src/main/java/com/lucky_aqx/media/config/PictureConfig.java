package com.lucky_aqx.media.config;

/**
 * 常量
 */
public final class PictureConfig {
    public final static int APPLY_STORAGE_PERMISSIONS_CODE = 1;
    public final static int APPLY_CAMERA_PERMISSIONS_CODE = 2;
    public final static int APPLY_AUDIO_PERMISSIONS_CODE = 3;

    public final static String EXTRA_MEDIA_KEY = "mediaKey";
    public final static String EXTRA_AUDIO_PATH = "audioPath";
    public final static String EXTRA_VIDEO_PATH = "videoPath";
    public final static String EXTRA_PREVIEW_VIDEO = "isExternalPreviewVideo";
    public final static String EXTRA_PREVIEW_DELETE_POSITION = "position";
    public final static String EXTRA_FC_TAG = "picture";
    public final static String EXTRA_RESULT_SELECTION = "extra_result_media";
    public final static String EXTRA_PREVIEW_SELECT_LIST = "previewSelectList";
    public final static String EXTRA_SELECT_LIST = "selectList";
    public final static String EXTRA_COMPLETE_SELECTED = "isCompleteOrSelected";
    public final static String EXTRA_CHANGE_SELECTED_DATA = "isChangeSelectedData";
    public final static String EXTRA_CHANGE_ORIGINAL = "isOriginal";
    public final static String EXTRA_POSITION = "position";
    public final static String EXTRA_OLD_CURRENT_LIST_SIZE = "oldCurrentListSize";
    public final static String EXTRA_DIRECTORY_PATH = "directory_path";
    public final static String EXTRA_BOTTOM_PREVIEW = "bottom_preview";
    public final static String EXTRA_CONFIG = "PictureSelectorConfig";

    public final static String CAMERA_FACING = "android.intent.extras.CAMERA_FACING";

    public final static int CAMERA_BEFORE = 1;
    public final static int CAMERA_AFTER = 2;

    public final static int TYPE_ALL = 0;
    public final static int TYPE_IMAGE = 1;
    public final static int TYPE_VIDEO = 2;
    public final static int TYPE_AUDIO = 3;

    public final static int MAX_COMPRESS_SIZE = 100;

    public final static int TYPE_CAMERA = 1;
    public final static int TYPE_PICTURE = 2;

    public final static int SINGLE = 1;
    public final static int MULTIPLE = 2;

    public final static int PREVIEW_VIDEO_CODE = 166;
    public final static int CHOOSE_REQUEST = 188;
    public final static int REQUEST_CAMERA_VIDEO = 908;
    public final static int REQUEST_CAMERA_PIC = 909;
}
