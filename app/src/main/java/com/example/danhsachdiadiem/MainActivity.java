package com.example.danhsachdiadiem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISendPlace {

    Button btnSave, btnCancel;
    RecyclerView rcvData;
    EditText edtPlace;
    PlaceDatabase db;
    List<Place> lstPlace = new ArrayList<Place>();
    PlaceAdapter adapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    Place placeChon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectView();
        db = PlaceDatabase.getInstance(this);
        rcvData.setHasFixedSize(true);
        getAllPlace();
        insertPlace();
//        deletePlace();
//        updatePlace();
    }

    public void getAllPlace()
    {
        lstPlace.clear();
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lstPlace = db.mainDao().getAllUser();
        adapter = new PlaceAdapter(lstPlace, this);
        rcvData.setLayoutManager(layoutManager);
        rcvData.setAdapter(adapter);
    }

    public void updatePlace()
    {
        Context context = this;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.modal_update);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int hight = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, hight);
        if(placeChon == null)
        {
            Toast.makeText(context, "Vui lòng chọn nơi đến cần cập nhật", Toast.LENGTH_LONG).show();
            return;
        }
        dialog.show();
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);
        EditText edtNewPlace = dialog.findViewById(R.id.edtAddress);
        edtNewPlace.setText(placeChon.getPlace());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPlace.getText().toString().trim().length() == 0)
                    Toast.makeText(context, "Vui lòng nhập thông tin update", Toast.LENGTH_LONG).show();
                else
                {
                    db.mainDao().update(placeChon.getId(), edtNewPlace.getText().toString());
                    getAllPlace();
                    edtNewPlace.setText(null);
                    Clear();
                    dialog.dismiss();
                }
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNewPlace.setText(null);
                Clear();
                dialog.dismiss();
            }
        });
    }

    public void insertPlace()
    {
        Context context = this;
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtPlace.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
                else
                {
                    String place = edtPlace.getText().toString().trim();
                    Place pl = new Place(place);
                    db.mainDao().insert(pl);
                    Toast.makeText(context, "Thêm nơi đến thành công", Toast.LENGTH_LONG).show();
                    getAllPlace();
                    Clear();
                }
            }
        });
    }

    public void deletePlace()
    {
        Context context = this;
        if(placeChon == null)
            Toast.makeText(context, "Vui lòng chọn nơi đến cần xóa", Toast.LENGTH_LONG).show();
        else
        {
            db.mainDao().delete(placeChon);
            Toast.makeText(context, "Xóa nơi đến thành công", Toast.LENGTH_LONG).show();
            getAllPlace();
            Clear();
        }
    }

    void Clear()
    {
        placeChon = null;
        edtPlace.setText(null);
    }

    void ConnectView()
    {
        btnSave = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        rcvData = (RecyclerView)findViewById(R.id.rcvDSName);
        edtPlace = (EditText)findViewById(R.id.edtAddress);
    }

    @Override
    public void sendUser(Place place, int choice) { //choice = 1 => xóa, choice = 2: update
        placeChon = place;
        edtPlace.setText(place.getPlace());
        if(choice == 1)
            deletePlace();
        else
            updatePlace();
    }
}