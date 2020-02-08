package com.lucky_aqx.mediaselector.ui.mvp.pic;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lucky_aqx.media.config.PictureConfig;
import com.lucky_aqx.media.config.PictureMimeType;
import com.lucky_aqx.media.config.PictureSelectionConfig;
import com.lucky_aqx.media.entry.LocalMedia;
import com.lucky_aqx.media.entry.LocalMediaFolder;
import com.lucky_aqx.media.model.LocalMediaLoader;
import com.lucky_aqx.media.utils.LogUtils;
import com.lucky_aqx.mediaselector.R;
import com.lucky_aqx.mediaselector.common.bean.HomeMediaBean;
import com.lucky_aqx.mediaselector.common.utils.UIHelper;
import com.lucky_aqx.mediaselector.ui.adapter.MediaMediaAdapter;
import com.lucky_aqx.mediaselector.ui.base.BaseFragment;
import com.lucky_aqx.mediaselector.ui.base.permission.PermissionHelper;
import com.lucky_aqx.mediaselector.ui.base.permission.PermissionListener;
import com.lucky_aqx.mediaselector.ui.base.permission.Permissions;
import com.lucky_aqx.mediaselector.ui.mvp.HomeActivity;
import com.lucky_aqx.mediaselector.ui.weight.PhotoItemSelectedDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 包名：com.lucky_aqx.mediaselector.ui.mvp.pic
 * 项目名称：MediaSelector
 * 作者：James
 * 创建时间：2020-01-06 14:23
 * 描述：全部图片
 * 版本：V1.0
 */
public class PicFragment extends BaseFragment {

    private static final int SHOW_DIALOG = 0;
    private static final int DISMISS_DIALOG = 1;

    private LocalMediaLoader mediaLoader;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_DIALOG:
                    showLoadingView(false);
                    break;
                case DISMISS_DIALOG:
                    dismissLoadingView();
                    break;
                default:
                    break;
            }
        }
    };

    private List<HomeMediaBean> images = new ArrayList<>();
    private MediaMediaAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new SpaceItemDecoration());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MediaMediaAdapter(this, images);
        recyclerView.setAdapter(mAdapter);

        PictureSelectionConfig config = PictureSelectionConfig.getCleanInstance();
        config.chooseMode = PictureMimeType.ofImage();
        mediaLoader = new LocalMediaLoader(context, config);

        PermissionHelper.requestPermissions(getActivity(), Permissions.PERMISSIONS_BIOSPSY_AUDIO,
                getResources().getString(R.string.app_name) + "需要相机、存储权限", new PermissionListener() {
                    @Override
                    public void onPassed() {
                        getAllPhotoInfo();
                    }
                });

    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener((adapters, view, position) -> {
            ((HomeActivity) context).setMedia(images.get(position));
        });
    }

    private void getAllPhotoInfo() {
        mediaLoader.loadAllMedia();
        mediaLoader.setCompleteListener(new LocalMediaLoader.LocalMediaLoadListener() {
            @Override
            public void loadComplete(List<LocalMediaFolder> folders) {
                mHandler.sendEmptyMessage(DISMISS_DIALOG);
                if (!folders.isEmpty()) {
                    LocalMediaFolder folder = folders.get(0);
                    List<LocalMedia> result = folder.getImages();
                    if (mAdapter != null) {
                        images.clear();
                        for (LocalMedia localMedia : result)
                            images.add(new HomeMediaBean(localMedia, PictureConfig.TYPE_IMAGE, true));
                        mAdapter.notifyDataSetChanged();
                    }
                }
                LogUtils.d("getAllPhotoInfo", new Gson().toJson(images));
            }

            @Override
            public void loadMediaDataError() {
                mHandler.sendEmptyMessage(DISMISS_DIALOG);
                if (mAdapter != null) {
                    images.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void addPic(HomeMediaBean mediaBean) {
        if (!isAdded())
            return;
        images.add(0, mediaBean);
        mAdapter.notifyDataSetChanged();
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int mSpace = UIHelper.getDimensionPixelSize(R.dimen.spacing_3dp);

        SpaceItemDecoration() {

        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.bottom = mSpace;
            outRect.right = mSpace;
            //由于每行都只有4个，所以第一个都是4的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 4 == 0) {
                outRect.left = mSpace;
            } else {
                outRect.left = 0;
            }
            if (parent.getChildLayoutPosition(view) < 5) {
                outRect.top = mSpace;
            } else {
                outRect.top = 0;
            }
        }
    }
}
