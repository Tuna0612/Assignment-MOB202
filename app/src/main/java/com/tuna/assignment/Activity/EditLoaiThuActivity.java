package com.tuna.assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tuna.assignment.R;
import com.tuna.assignment.database.KhoanChiDAO;
import com.tuna.assignment.database.LoaiChiDAO;
import com.tuna.assignment.database.LoaiThuDAO;

public class EditLoaiThuActivity extends AppCompatActivity {
    EditText edTenLoaiThu;
    LoaiThuDAO loaiThuDAO;
    String MaLoaiThu, TenLoaiThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_loai_thu);
        edTenLoaiThu = findViewById(R.id.edTenLoaiThu);
        loaiThuDAO = new LoaiThuDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        MaLoaiThu = b.getString("ID");
        TenLoaiThu = b.getString("NAME");
        edTenLoaiThu.setText(TenLoaiThu);
    }

    public void updateloaithu(View view) {
        if (loaiThuDAO.updateLoaiThu(MaLoaiThu,edTenLoaiThu.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void huyloaithu(View view) {
    }
}
