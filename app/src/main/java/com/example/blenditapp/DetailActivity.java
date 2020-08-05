package com.example.blenditapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blenditapp.model.DBHelper;
import com.example.blenditapp.model.HTTPHelper;
import com.example.blenditapp.model.PersonDAO;
import com.example.blenditapp.model.PersonEntity;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtPhone;
    EditText txtAddress;
    TextView txtAddressDetail;
    Button btnDelete;
    PersonEntity p = new PersonEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtName = (EditText)findViewById(R.id.txtName);
        txtPhone = (EditText)findViewById(R.id.txtPhone);
        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtAddressDetail = (TextView)findViewById(R.id.txtAddressDetail);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        txtAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    HTTPHelper http = new HTTPHelper(txtAddressDetail, txtAddress.getText().toString());
                    http.execute("");
                }
            }
        });



        Intent it = getIntent();

        if(it != null) {
            p.setId(it.getIntExtra("id", 0));
            p.setName(it.getStringExtra("name"));
            p.setPhone(it.getStringExtra("phone"));
            p.setAddress(it.getStringExtra("address"));
        }

        txtName.setText(p.getName());
        txtPhone.setText(p.getPhone());
        txtAddress.setText(p.getAddress());
        HTTPHelper http = new HTTPHelper(txtAddressDetail, txtAddress.getText().toString());
        http.execute("");

        if(p.getId() == 0){
            btnDelete.setVisibility(View.GONE);
        }

    }

    public void doSave(View view) throws IOException {
        Toast.makeText(this, "Dado salvo.", Toast.LENGTH_SHORT).show();
        DBHelper db = DBHelper.getAppDatabase(view.getContext());

        //init dao and perform operation
        PersonDAO dao = db.PersonDao();

        p.setName(txtName.getText().toString());
        p.setPhone(txtPhone.getText().toString());
        p.setAddress(txtAddress.getText().toString());

        if(p.getId() != 0){
            dao.update(p);
        }else{
            dao.insert(p);
        }

        onBackPressed();

    }

    public void doDelete(View view){
        Toast.makeText(this, "Removido.", Toast.LENGTH_SHORT).show();
        DBHelper db = DBHelper.getAppDatabase(view.getContext());

        //init dao and perform operation
        PersonDAO dao = db.PersonDao();

        dao.delete(p);

        onBackPressed();

    }
}