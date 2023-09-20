package com.finger.usbcamera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.finger.fpt.FPT5Object;
import com.finger.fpt.PackageHead;
import com.finger.fpt.tp.FingerprintPackage;
import com.finger.usbcamera.R;
import com.finger.usbcamera.USBCameraAPP;
import com.finger.usbcamera.adapter.PersonListAdapter;
import com.finger.usbcamera.db.entity.Face;
import com.finger.usbcamera.db.entity.Finger;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.db.entity.User;
import com.finger.usbcamera.db.greendao.FaceDao;
import com.finger.usbcamera.db.greendao.FingerDao;
import com.finger.usbcamera.db.greendao.PersonDao;
import com.finger.usbcamera.db.greendao.UserDao;
import com.finger.usbcamera.util.FPTConverter;
import com.finger.usbcamera.util.FileUtils;
import com.finger.usbcamera.util.GsonUtil;
import com.finger.usbcamera.util.SharedPreferencesUtil;
import com.finger.usbcamera.util.XmlUtil;
import com.finger.usbcamera.vo.UploadPersonRequest;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.finger.usbcamera.USBCameraAPP.EXTRA_IDCARDNO;
import static com.finger.usbcamera.USBCameraAPP.EXTRA_NAME;
import static com.finger.usbcamera.USBCameraAPP.EXTRA_PERSONID;

/**
 * 人员采集Fragment
 */
public class PersonGatherFragment extends Fragment {
    private static final int REQUEST_CODE_ADD_PERSON = 101;
    private static final int REQUEST_CODE_GATHER_FACE = 102;

    private String TAG = "PersonGatherFragment";
    private List<Person> personList = new ArrayList<Person>();
    private TextView title;
    private Button addBtn;
    private Button refreshBtn;
    private View mView;
    private RecyclerView recyclerView;
    private PersonListAdapter personListAdapter;

    private PersonDao personDao;
    private FingerDao fingerDao;
    private UserDao userDao;
    private FaceDao faceDao;

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personDao = USBCameraAPP.getInstances().getDaoSession().getPersonDao();
        fingerDao = USBCameraAPP.getInstances().getDaoSession().getFingerDao();
        userDao = USBCameraAPP.getInstances().getDaoSession().getUserDao();
        faceDao = USBCameraAPP.getInstances().getDaoSession().getFaceDao();
        mView = inflater.inflate(R.layout.fragment_person_gather, container, false);
        mContext = mView.getContext();
        title = mView.findViewById(R.id.person_gather_title);
        addBtn = mView.findViewById(R.id.person_gather_add);
        refreshBtn = mView.findViewById(R.id.person_gather_refresh);

        initRecyclerView();
        initData();

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IDCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_PERSON);
            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                refreshPersonList();
            }
        });
        return mView;
    }

    private void initData() {
        QueryBuilder<Person> queryBuilder = personDao.queryBuilder();
        personList.clear();
        personList.addAll(queryBuilder.list());
    }
    private void initRecyclerView () {
        recyclerView = mView.findViewById(R.id.recyclerView);
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
                            case R.id.person_list_menu_gather_finger:
                                //继续采集指纹
                                Intent intent = new Intent(mContext, FingerActivity.class);
                                intent.putExtra(EXTRA_NAME, person.getName());
                                intent.putExtra(EXTRA_IDCARDNO, person.getIdCardNo());
                                intent.putExtra(EXTRA_PERSONID, person.getId());
                                startActivity(intent);
                                break;
                            case R.id.person_list_menu_gather_face:
                                //继续采集指纹
                                Intent intent2 = new Intent(mContext, FaceActivity.class);
                                intent2.putExtra(EXTRA_NAME, person.getName());
                                intent2.putExtra(EXTRA_IDCARDNO, person.getIdCardNo());
                                intent2.putExtra(EXTRA_PERSONID, person.getId());
                                startActivity(intent2);
                                break;
                            case R.id.person_list_menu_export_fpt:
                                exportFpt(person);
                                break;
                            case R.id.person_list_menu_upload:
                                uploadPerson(person);
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

        User gatherUser = USBCameraAPP.getInstances().getLoginUser();
        if(person.getGatherUserId() != null){
            gatherUser = userDao.load(person.getGatherUserId());
        }
        List<Face> faceList = faceDao.queryBuilder().where(FaceDao.Properties.PersonId.eq(person.getId())).list();
        Face face = null;
        if(faceList != null && faceList.size() > 0){
            face = faceList.get(0);
        }
        FingerprintPackage fingerprintPackage = FPTConverter.convert2FingerprintPackage(person, fingerList, face, gatherUser);

        fingerprintPackageList.add(fingerprintPackage);
        fpt5Object.setFingerprintPackageList(fingerprintPackageList);

        String xml = XmlUtil.convertToXml(fpt5Object);

        Uri uri = FileUtils.saveTextFile(mContext, xml, person.getName()+"-"+person.getIdCardNo()+".fptx");
//        Boolean ok = FileUtils.save(new File(mContext.getDataDir()+"/fpt/"+person.getName()+"-"+person.getIdCardNo()+".fptx"), xml);
        Toast.makeText(mContext, "保存fpt数据成功"+uri, Toast.LENGTH_SHORT).show();
    }

    /**
     * 刷新数据
     */
    public void refreshPersonList(){
        initData();
        personListAdapter.notifyDataSetChanged();//刷新
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e(TAG, "onActivityResult: "+requestCode+" resultCode "+ resultCode);
        if (requestCode == REQUEST_CODE_ADD_PERSON && resultCode == Activity.RESULT_OK) {
            refreshPersonList();//添加，刷新列表
        }
    }

    private RequestQueue requestQueue = null;
    private void uploadPerson(Person person) {
        requestQueue = Volley.newRequestQueue(this.mContext);
        UploadPersonRequest request = new UploadPersonRequest();
        request.setPerson(person);
        List<Face> faceList = faceDao.queryBuilder().where(FaceDao.Properties.PersonId.eq(person.getId())).list();
        if(faceList != null && faceList.size() > 0){
            request.setFace(faceList.get(0));
        }
        List<Finger> fingerList = fingerDao.queryBuilder().where(FingerDao.Properties.PersonId.eq(person.getId())).list();
        request.setFingerList(fingerList);

        String url = SharedPreferencesUtil.getStringValue(SharedPreferencesUtil.KEY_UPLOAD_URL, "http://0.0.0.0:10000")+"/uploadPerson";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(GsonUtil.createJsonString(request));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, jsonObject,new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Toast.makeText(mContext, "上报数据成功"+ response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "上报数据失败"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}