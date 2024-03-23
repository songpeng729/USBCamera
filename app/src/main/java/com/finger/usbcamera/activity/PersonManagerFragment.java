package com.finger.usbcamera.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 人员管理页面
 */
public class PersonManagerFragment extends Fragment {
    private Context mContext;
    private List<Person> personList = new ArrayList<>();
    private PersonDao personDao;

    private View view;
    private RecyclerView recyclerView;
    private PersonListAdapter personListAdapter;

    private Button dateBtn;
    private Calendar calendar = Calendar.getInstance();
    private TextView dateTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        view = inflater.inflate(R.layout.fragment_person_manager, container, false);
        mContext = view.getContext();
        dateBtn = view.findViewById(R.id.person_manager_date);
        dateTitle = view.findViewById(R.id.person_manager_title);

        initRecyclerView();
        refreshDateTitle();

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(mContext, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();//显示DatePickerDialog组件
            }
        });
        return view;
    }
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            refreshDateTitle();
        }
    };
    private void refreshDateTitle(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dateTitle.setText(sdf.format(calendar.getTime()));
        refreshData();
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

    private void refreshData(){
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        queryBuilder.where(PersonDao.Properties.GatherDate.ge(calendar.getTime()),PersonDao.Properties.GatherDate.lt(new Date(calendar.getTimeInMillis()+ 24*60*60*1000)));
        personList.clear();
        personList.addAll(queryBuilder.list());
        personListAdapter.notifyDataSetChanged();//刷新
    }
}