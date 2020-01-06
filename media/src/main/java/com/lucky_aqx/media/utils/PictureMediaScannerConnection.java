package com.lucky_aqx.media.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;

/**
 * 刷新相册
 */
public class PictureMediaScannerConnection implements MediaScannerConnection.MediaScannerConnectionClient {
    public interface ScanListener {
        void onScanFinish();
    }

    private MediaScannerConnection mMs;
    private String mPath;
    private ScanListener mListener;

    public PictureMediaScannerConnection(Context context, String path, ScanListener l) {
        mListener = l;
        mPath = path;
        mMs = new MediaScannerConnection(context.getApplicationContext(), this);
        mMs.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        if (!TextUtils.isEmpty(mPath)) {
            mMs.scanFile(mPath, null);
        }
    }

    @Override
    public void onScanCompleted(String path, Uri uri) {
        mMs.disconnect();
        if (mListener != null) {
            mListener.onScanFinish();
        }
    }
}
