package com.tuna.assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tuna.assignment.R;
import com.tuna.assignment.database.KhoanChiDAO;
import com.tuna.assignment.database.KhoanThuDAO;
import com.tuna.assignment.database.LoaiChiDAO;

public class EditLoaiChiActivity extends AppCompatActivity {
    EditText edTenLoaiChi;
    LoaiChiDAO loaiChiDAO;
    String MaLoaiChi, TenLoaiChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_loai_chi);
        edTenLoaiChi = findViewById(R.id.edTenLoaiChi);
        loaiChiDAO = new LoaiChiDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        MaLoaiChi = b.getString("ID");
        TenLoaiChi = b.getString("NAME");
        edTenLoaiChi.setText(TenLoaiChi);
    }

    public void updateloaichi(View view) {
        if (loaiChiDAO.updateLoaiChi(MaLoaiChi,edTenLoaiChi.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void huyloaichi(View view) {
    }
}
