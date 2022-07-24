package com.finger.usbcamera.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.adapter.PersonAdapter;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.greendao.PersonDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 人员信息管理
 */
public class PersonListActivity extends AppCompatActivity {

    private ListView personListView;
    private PersonAdapter personAdapter;
    private List<Person> personList;
    private PersonDao personDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_person_list);
        personListView = findViewById(R.id.person_list);

        initData();
        personAdapter = new PersonAdapter(this, 1, personList);
        personListView.setAdapter(personAdapter);
    }

    private void initData(){
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        long count = queryBuilder.count();
        personList = queryBuilder.list();
    }
}
