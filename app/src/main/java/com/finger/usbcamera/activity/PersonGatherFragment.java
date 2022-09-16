package com.finger.usbcamera.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.adapter.PersonListAdapter;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.greendao.PersonDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员采集Fragment
 */
public class PersonGatherFragment extends Fragment {
    private String TAG = "PersonGatherFragment";
    private List<Person> personList = new ArrayList<Person>();
    private TextView title;
    private Button addBtn;
    private Button refreshBtn;
    private View view;
    private RecyclerView recyclerView;
    private PersonListAdapter personListAdapter;

    private PersonDao personDao;
    private Context mContext;
    public PersonGatherFragment(Context context){
        mContext = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        view = inflater.inflate(R.layout.fragment_person_gather, container, false);
        title = view.findViewById(R.id.person_gather_title);
        addBtn = view.findViewById(R.id.person_gather_add);
        refreshBtn = view.findViewById(R.id.person_gather_refresh);

        initRecyclerView();
        initData();

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IDCardActivity.class);
                startActivity(intent);
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                refreshPersonList();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void initData() {
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        personList.clear();
        personList.addAll(queryBuilder.list());
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
    }

    /**
     * 刷新数据
     */
    public void refreshPersonList(){
        initData();
        personListAdapter.notifyDataSetChanged();//刷新
    }

}