package com.finger.usbcamera.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.finger.fpt.FPT5Object;
import com.finger.fpt.PackageHead;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.adapter.PersonListAdapter;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.greendao.FingerDao;
import com.finger.usbcamera.db.greendao.PersonDao;
import com.finger.usbcamera.util.FPTConverter;
import com.finger.usbcamera.util.FileUtils;
import com.finger.usbcamera.util.XmlUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.finger.usbcamera.activity.FingerActivity.EXTRA_IDCARDNO;
import static com.finger.usbcamera.activity.FingerActivity.EXTRA_NAME;
import static com.finger.usbcamera.activity.FingerActivity.EXTRA_PERSONID;

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
    FingerDao fingerDao;
    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        fingerDao = USBCameraAPP.getInstances().getDaoSession().getFingerDao();
        view = inflater.inflate(R.layout.fragment_person_gather, container, false);
        mContext = view.getContext();
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
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));

        personListAdapter.setOnItemClickListener(new PersonListAdapter.OnItemClickListener() {
            @Override
            public void onItemLongClick(View view, int pos) {
                Person person = personList.get(pos);
                PopupMenu popupMenu = new PopupMenu(mContext,view);
                popupMenu.getMenuInflater().inflate(R.menu.person_list_menu,popupMenu.getMenu());
                //弹出式菜单的菜单项点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.person_list_menu_delete:
                                //删除
                                personList.remove(pos);
                                personListAdapter.notifyItemRemoved(pos);
                                break;
                            case R.id.person_list_menu_continue:
                                //继续采集
                                Intent intent = new Intent(mContext, FingerActivity.class);
                                intent.putExtra(EXTRA_NAME, person.getName());
                                intent.putExtra(EXTRA_IDCARDNO, person.getIdCardNo());
                                intent.putExtra(EXTRA_PERSONID, person.getId());
                                startActivity(intent);
                                break;
                            case R.id.person_list_menu_export_fpt:
                                exportFpt(person);
                                break;
                            case R.id.person_list_menu_edit:
                                startActivity(new Intent(mContext, MosaicActivity.class));
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private void exportFpt(Person person){
        FPT5Object fpt5Object = new FPT5Object();
        PackageHead packageHead = new PackageHead();
        fpt5Object.setPackageHead(packageHead);
        List<FingerprintPackage> fingerprintPackageList = new ArrayList<>();

        List<Finger> fingerList = fingerDao.queryBuilder().where(FingerDao.Properties.PersonId.eq(person.getId())).list();
        FingerprintPackage fingerprintPackage = FPTConverter.convert2FingerprintPackage(person, fingerList);

        fingerprintPackageList.add(fingerprintPackage);
        fpt5Object.setFingerprintPackageList(fingerprintPackageList);

        String xml = XmlUtil.convertToXml(fpt5Object);

        FileUtils.saveTextFile(mContext, xml, person.getName()+"("+person.getIdCardNo()+").fptx");
        Toast.makeText(mContext, "保存fpt数据成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 刷新数据
     */
    public void refreshPersonList(){
        initData();
        personListAdapter.notifyDataSetChanged();//刷新
    }

}