package com.lucky_aqx.mediaselector.ui.base;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public abstract class ForResultNestedCompatFragment extends BaseFragment {

    private ForResultNestedCompatFragment forResultChildFragment;

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof ForResultNestedCompatFragment) {
            ((ForResultNestedCompatFragment) parentFragment).startActivityForResultFromChildFragment(intent,
                    requestCode, this);
        } else {
            forResultChildFragment = null;
            super.startActivityForResult(intent, requestCode);
        }
    }

    private void startActivityForResultFromChildFragment(Intent intent, int requestCode,
                                                         ForResultNestedCompatFragment childFragment) {
        forResultChildFragment = childFragment;

        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment instanceof ForResultNestedCompatFragment) {
            ((ForResultNestedCompatFragment) parentFragment).startActivityForResultFromChildFragment(intent,
                    requestCode, this);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (forResultChildFragment != null) {
            forResultChildFragment.onActivityResult(requestCode, resultCode, data);
            forResultChildFragment = null;
        } else {
            onActivityResultNestedCompat(requestCode, resultCode, data);
        }
    }

    public void onActivityResultNestedCompat(int requestCode, int resultCode, Intent data) {

    }

}

