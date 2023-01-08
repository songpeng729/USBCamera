package com.finger.usbcamera.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.finger.usbcamera.R;

/**
 * 系统设置页面
 */
public class SettingFragment extends Fragment {
    private Context mContext;
    private View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = mView.getContext();
        return mView;
    }
}