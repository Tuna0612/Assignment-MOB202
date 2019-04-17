package com.tuna.assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tuna.assignment.R;
import com.tuna.assignment.database.KhoanChiDAO;

public class EditKhoanChiActivity extends AppCompatActivity {
    EditText  edTenKhoanChi;
    KhoanChiDAO khoanChiDAO;
    String MaKhoanChi, TenKhoanChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_khoan_chi);
        edTenKhoanChi = findViewById(R.id.edTenKhoanChi);
        khoanChiDAO = new KhoanChiDAO(this);
        Intent in = getIntent();
        Bundle b_khoan_chi = in.getExtras();
        MaKhoanChi = b_khoan_chi.getString("ID_khoan_chi");
        TenKhoanChi = b_khoan_chi.getString("NAME_khoan_chi");
        edTenKhoanChi.setText(TenKhoanChi);
    }

    public void updatekhoanthu(View view) {
        if (khoanChiDAO.updateKhoanChi(MaKhoanChi,edTenKhoanChi.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void huykhoanthu(View view) {
    }
}
