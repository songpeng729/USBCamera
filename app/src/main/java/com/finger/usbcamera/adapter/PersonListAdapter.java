package com.finger.usbcamera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.finger.usbcamera.R;
import com.finger.usbcamera.db.entity.Person;
import com.finger.usbcamera.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 人员列表Adapter
 */
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder>{
    private Context context;
    private List<Person> personList = new ArrayList<Person>();

    public PersonListAdapter(Context context, List<Person> personList){
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_list_item,parent,false);
        PersonListViewHolder holder = new PersonListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.name.setText(person.getName());
        holder.gender.setText(person.getGender());
        holder.idCardNo.setText(person.getIdCardNo());
        holder.address.setText(person.getAddress());
        holder.gatherTime.setText(DateUtils.date2DateTimeString(person.getGatherDate()));
//        holder.personListItemLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return personList != null ? personList.size() : 0;
    }

    public class PersonListViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        TextView name;
        TextView gender;
        TextView idCardNo;
        TextView gatherTime;
        TextView address;
        LinearLayout personListItemLayout;
        public PersonListViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.person_photo);
            name = itemView.findViewById(R.id.person_name);
            gender = itemView.findViewById(R.id.person_gender);
            idCardNo = itemView.findViewById(R.id.person_idcardno);
            gatherTime = itemView.findViewById(R.id.gather_time);
            address = itemView.findViewById(R.id.person_address);
            personListItemLayout = itemView.findViewById(R.id.person_list_item);
        }
    }

}
