package com.finger.usbcamera.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.adapter.PersonListAdapter;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.greendao.PersonDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * 人员管理页面
 */
public class PersonManagerFragment extends Fragment {
    private Context mContext;
    private List<Person> personList;
    private PersonDao personDao;

    private View view;
    private RecyclerView recyclerView;
    private PersonListAdapter personListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        view = inflater.inflate(R.layout.fragment_person_manager, container, false);
        mContext = view.getContext();

        initRecyclerView();
        initData();

        return view;
    }

    private void initData(){
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        personList = queryBuilder.list();
    }
    private void initRecyclerView () {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.getItemAnimator().setChangeDuration(500);
        recyclerView.getItemAnimator().setMoveDuration(500);
        personListAdapter = new PersonListAdapter(getActivity(), personList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(personListAdapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
    }
}