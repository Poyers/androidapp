package com.example.blenditapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.blenditapp.model.DBHelper;
import com.example.blenditapp.model.PersonDAO;
import com.example.blenditapp.model.PersonEntity;
import com.example.blenditapp.model.MainListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    View view;
    List<PersonEntity> personList = new ArrayList<PersonEntity>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);


        return v;
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;

    }

    @Override
    public void onResume(){
        super.onResume();
        ListView MainList = view.findViewById(R.id.MainList);

        DBHelper db = DBHelper.getAppDatabase(this.getActivity());
        PersonDAO dao = db.PersonDao();
        personList = dao.getAll();

        MainListAdapter MainListAdapter = new MainListAdapter(getActivity(), personList);
        MainList.setAdapter(MainListAdapter);
        MainListAdapter.notifyDataSetChanged();

        MainList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3){
                Intent it = new Intent(view.getContext(), DetailActivity.class);
                PersonEntity p = new PersonEntity();
                Bundle params = new Bundle();
                p = personList.get(position);
                params.putInt("id", p.getId());
                params.putString("name", p.getName());
                params.putString("phone", p.getPhone());
                params.putString("address", p.getAddress());
                it.putExtras(params);
                startActivity(it);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), DetailActivity.class);
                PersonEntity p = new PersonEntity();
                Bundle params = new Bundle();
                params.putInt("id", -1);
                it.putExtras(params);
                startActivity(it);
            }
        });
    }


}