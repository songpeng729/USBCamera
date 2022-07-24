package com.finger.usbcamera.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.finger.usbcamera.R;
import com.finger.usbcamera.db.entity.Person;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private LayoutInflater inflater;
    private Context mContext;
    private List<Person> personList;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.personList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Person getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = personList.get(position);
        PersonInfoViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new PersonInfoViewHolder();
            convertView = inflater.inflate(R.layout.person_list_item, parent,false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.person_name);
            viewHolder.sex= (TextView) convertView.findViewById(R.id.person_sex);
            viewHolder.idCardNo= (TextView) convertView.findViewById(R.id.person_idcardno);
            viewHolder.address= (TextView) convertView.findViewById(R.id.person_address);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (PersonInfoViewHolder) convertView.getTag();
        }

        if(person != null){
            viewHolder.name.setText(person.getName());
            viewHolder.sex.setText(person.getSex());
            viewHolder.idCardNo.setText(person.getIdCardNo());
            viewHolder.address.setText(person.getAddress());
        }
        return convertView;
    }
    class PersonInfoViewHolder{
        TextView name;
        TextView idCardNo;
        TextView sex;
        TextView address;
    }
}
