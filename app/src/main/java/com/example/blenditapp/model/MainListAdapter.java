package com.example.blenditapp.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blenditapp.R;
import com.example.blenditapp.model.PersonEntity;

import java.util.List;

/**
 * Created by Junior on 05/11/2016.
 */
public class MainListAdapter extends ArrayAdapter<PersonEntity> {
    private final Activity context;
    List<PersonEntity> MainList;

    public MainListAdapter(Activity context, List<PersonEntity> MainList){
        super(context, R.layout.main_list, MainList);
        this.context = context;
        this.MainList = MainList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.main_list, null, true);

        TextView txtName = (TextView) rowView.findViewById(R.id.txtName);
        TextView txtPhone = (TextView) rowView.findViewById(R.id.txtPhone);
        TextView txtAddress = (TextView) rowView.findViewById(R.id.txtAddress);

        PersonEntity p = new PersonEntity();
        p = MainList.get(position);
        txtName.setText(p.getName());
        txtPhone.setText(p.getPhone());
        txtAddress.setText(p.getAddress());

        return rowView;
    }

}
