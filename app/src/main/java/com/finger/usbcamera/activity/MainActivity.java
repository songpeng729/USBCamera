package com.finger.usbcamera.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finger.usbcamera.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private Fragment personGatherFragment,personManagerFragment,settingFragment;
    private LinearLayout personGatherLayout, personManagerLayout, systemSettingLayout;
    private ImageView personGatherImg,personManagerImg,systemSettingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personGatherFragment = new PersonGatherFragment(this);
        personManagerFragment = new PersonManagerFragment();
        settingFragment = new SettingFragment();

        personGatherLayout = findViewById(R.id.person_gather);
        personManagerLayout = findViewById(R.id.person_manager);
        systemSettingLayout = findViewById(R.id.system_setting);

        fragmentManager = getSupportFragmentManager();

        initialfragment();
        personGatherImg = findViewById(R.id.person_gather_img);
        personManagerImg = findViewById(R.id.person_manager_img);
        systemSettingImg = findViewById(R.id.system_setting_img);
        personGatherLayout.setOnClickListener(this);
        personManagerLayout.setOnClickListener(this);
        systemSettingLayout.setOnClickListener(this);

    }
    private void initialfragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(androidx.appcompat.R.id.content, personGatherFragment);
        transaction.add(androidx.appcompat.R.id.content, personManagerFragment);
        transaction.add(androidx.appcompat.R.id.content, settingFragment);
        hide(transaction);
        transaction.show(personGatherFragment);//显示今日采集页面
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.person_gather: show(1); reset(); personGatherImg.setImageResource(R.drawable.person_gather_pressed); break;
            case R.id.person_manager: show(2); reset(); personManagerImg.setImageResource(R.drawable.person_manager_pressed); break;
            case R.id.system_setting: show(3); reset(); systemSettingImg.setImageResource(R.drawable.setting_pressed); break;
            default: break;
        }
    }

    private void show(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hide(transaction);
        switch (i){
            case 1:transaction.show(personGatherFragment);break;
            case 2:transaction.show(personManagerFragment);break;
            case 3:transaction.show(settingFragment);break;
            default:break;
        }
        transaction.commit();
    }

    private void hide(FragmentTransaction transaction) {
        transaction.hide(personGatherFragment);
        transaction.hide(personManagerFragment);
        transaction.hide(settingFragment);
    }

    private void reset(){
        personGatherImg.setImageResource(R.drawable.person_gather);
        personManagerImg.setImageResource(R.drawable.person_manager);
        systemSettingImg.setImageResource(R.drawable.setting);
    }
}