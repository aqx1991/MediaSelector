package com.lucky_aqx.mediaselector.ui.mvp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.lucky_aqx.media.config.PictureConfig;
import com.lucky_aqx.media.config.PictureMimeType;
import com.lucky_aqx.media.entry.LocalMedia;
import com.lucky_aqx.media.utils.LogUtils;
import com.lucky_aqx.media.utils.MediaUtils;
import com.lucky_aqx.media.utils.PictureFileUtils;
import com.lucky_aqx.media.utils.PictureMediaScannerConnection;
import com.lucky_aqx.media.utils.StringUtils;
import com.lucky_aqx.media.utils.ValueOf;
import com.lucky_aqx.mediaselector.R;
import com.lucky_aqx.mediaselector.ui.base.BaseActivity;
import com.lucky_aqx.mediaselector.ui.base.permission.PermissionHelper;
import com.lucky_aqx.mediaselector.ui.base.permission.PermissionListener;
import com.lucky_aqx.mediaselector.ui.base.permission.Permissions;
import com.lucky_aqx.mediaselector.ui.mvp.pic.PicFragment;
import com.lucky_aqx.mediaselector.ui.mvp.video.VideoFragment;
import com.lucky_aqx.mediaselector.ui.weight.PhotoItemSelectedDialog;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 描述: 首页
 * 作者: James
 * 日期: 2020/1/6 14:03
 * 类名: HomeActivity
 */
public class HomeActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rb_pic)
    RadioButton rbPic;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public String cameraPath = "";

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private PicFragment picFragment = null;
    private VideoFragment videoFragment = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        picFragment = new PicFragment();
        mFragments.add(picFragment);
        videoFragment = new VideoFragment();
        mFragments.add(videoFragment);

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        viewPager.setOffscreenPageLimit(2);

        radioGroup.check(R.id.rb_pic);
    }

    @Override
    public void initListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_pic:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.rb_video:
                    viewPager.setCurrentItem(1, true);
                    break;
                default:
                    break;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.rb_pic);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_video);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.cameraIv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.cameraIv:
                PermissionHelper.requestPermissions(this, Permissions.PERMISSIONS_BIOSPSY_AUDIO,
                        getResources().getString(R.string.app_name) + "需要相机、存储权限", new PermissionListener() {
                            @Override
                            public void onPassed() {
                                //启动相机
                                PhotoItemSelectedDialog selectedDialog = PhotoItemSelectedDialog.newInstance();
                                selectedDialog.setOnItemClickListener(position -> {
                                    switch (position) {
                                        case PhotoItemSelectedDialog.IMAGE_CAMERA:
                                            // 拍照
                                            startOpenCamera();
                                            break;
                                        case PhotoItemSelectedDialog.VIDEO_CAMERA:
                                            // 录视频
                                            startOpenCameraVideo();
                                            break;
                                        default:
                                            break;
                                    }
                                });
                                selectedDialog.show(getSupportFragmentManager(), "PhotoItemSelectedDialog");
                            }
                        });
                break;
            default:
                break;
        }
    }

    protected void startOpenCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            Uri imageUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imageUri = MediaUtils.createImageUri(getApplicationContext());
                if (imageUri != null) {
                    cameraPath = imageUri.toString();
                } else {
                    ToastUtils.showShort("open is camera error，the uri is empty ");
                    return;
                }
            } else {
                int chooseMode = PictureConfig.TYPE_IMAGE;
                String cameraFileName = System.currentTimeMillis() + StringUtils.getRandomString(10) + PictureMimeType.JPEG;
                if (!TextUtils.isEmpty(cameraFileName)) {
                    boolean isSuffixOfImage = PictureMimeType.isSuffixOfImage(cameraFileName);
                    cameraFileName = !isSuffixOfImage ? StringUtils.renameSuffix(cameraFileName, PictureMimeType.JPEG) : cameraFileName;
                }
                File cameraFile = PictureFileUtils.createCameraFile(getApplicationContext(),
                        chooseMode, cameraFileName, PictureMimeType.JPEG);
                cameraPath = cameraFile.getAbsolutePath();
                imageUri = PictureFileUtils.parUri(this, cameraFile);
            }
            cameraIntent.putExtra(PictureConfig.CAMERA_FACING, PictureConfig.CAMERA_BEFORE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(cameraIntent, PictureConfig.REQUEST_CAMERA_PIC);
        }
    }

    protected void startOpenCameraVideo() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            Uri imageUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                imageUri = MediaUtils.createVideoUri(getApplicationContext());
                if (imageUri != null) {
                    cameraPath = imageUri.toString();
                } else {
                    ToastUtils.showShort("open is camera error，the uri is empty ");
                    return;
                }
            } else {
                int chooseMode = PictureConfig.TYPE_VIDEO;
                String cameraFileName = System.currentTimeMillis() + StringUtils.getRandomString(10) + PictureMimeType.MP4;
                if (!TextUtils.isEmpty(cameraFileName)) {
                    boolean isSuffixOfImage = PictureMimeType.isSuffixOfImage(cameraFileName);
                    cameraFileName = isSuffixOfImage ? StringUtils.renameSuffix(cameraFileName, PictureMimeType.MP4) : cameraFileName;
                }
                File cameraFile = PictureFileUtils.createCameraFile(getApplicationContext(),
                        chooseMode, cameraFileName, PictureMimeType.MP4);
                cameraPath = cameraFile.getAbsolutePath();
                imageUri = PictureFileUtils.parUri(this, cameraFile);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntent.putExtra(PictureConfig.CAMERA_FACING, PictureConfig.CAMERA_BEFORE);
            cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 12);//录制视频秒数 默认60s
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);// 视频录制质量 0 or 1
            startActivityForResult(cameraIntent, PictureConfig.REQUEST_CAMERA_VIDEO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.REQUEST_CAMERA_VIDEO:
                        //拍照后处理结果
                        requestCamera(PictureConfig.TYPE_VIDEO);
                        break;
                    case PictureConfig.REQUEST_CAMERA_PIC:
                        //拍照后处理结果
                        requestCamera(PictureConfig.TYPE_PICTURE);
                        break;
                    default:
                        break;
                }
                break;
            case RESULT_CANCELED:
                //
                break;
            default:
                break;
        }
    }

    private void requestCamera(int chooseMode) {
        // 图片视频处理
        String mimeType = null;
        long duration = 0;
        LocalMedia media = new LocalMedia();
        if (TextUtils.isEmpty(cameraPath)) {
            return;
        }
        long size = 0;
        int[] newSize = new int[2];
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            new PictureMediaScannerConnection(context, cameraPath,
                    () -> {
                    });
//            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(cameraPath))));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String path = PictureFileUtils.getPath(getApplicationContext(), Uri.parse(cameraPath));
            File file = new File(path);
            size = file.length();
            mimeType = PictureMimeType.getMimeType(file);
            if (PictureMimeType.eqImage(mimeType)) {
                newSize = MediaUtils.getLocalImageSizeToAndroidQ(this, cameraPath);
            } else {
                newSize = MediaUtils.getLocalVideoSize(this, Uri.parse(cameraPath));
                duration = MediaUtils.extractDuration(context, true, cameraPath);
            }
            int lastIndexOf = cameraPath.lastIndexOf("/") + 1;
            media.setId(lastIndexOf > 0 ? ValueOf.toLong(cameraPath.substring(lastIndexOf)) : -1);
        } else {
            File file = new File(cameraPath);
            mimeType = PictureMimeType.getMimeType(file);
            size = file.length();
            if (PictureMimeType.eqImage(mimeType)) {
                int degree = PictureFileUtils.readPictureDegree(this, cameraPath);
                PictureFileUtils.rotateImage(degree, cameraPath);
                newSize = MediaUtils.getLocalImageWidthOrHeight(cameraPath);
            } else {
                newSize = MediaUtils.getLocalVideoSize(cameraPath);
                duration = MediaUtils.extractDuration(context, false, cameraPath);
            }
            // 拍照产生一个临时id
            media.setId(System.currentTimeMillis());
        }
        media.setDuration(duration);
        media.setWidth(newSize[0]);
        media.setHeight(newSize[1]);
        media.setPath(cameraPath);
        media.setMimeType(mimeType);
        media.setSize(size);
        media.setChooseModel(chooseMode);
        LogUtils.d("相机返回文件", new Gson().toJson(media));

    }
}
