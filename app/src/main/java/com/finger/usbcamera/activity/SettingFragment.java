package com.finger.usbcamera.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.finger.usbcamera.R;

/**
 * 系统设置页面
 */
public class SettingFragment extends Fragment {
    private Context mContext;
    private View mView;
    private Button userSettingBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        mContext = mView.getContext();

        userSettingBtn = mView.findViewById(R.id.set_user_info_btn);
        userSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserSettingActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }
}