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

public class EditKhoanThuActivity extends AppCompatActivity {
    EditText edTenKhoanThu;
    KhoanThuDAO khoanThuDAO;
    String MaKhoanThu, TenKhoanThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_khoan_thu);
        edTenKhoanThu = findViewById(R.id.edTenKhoanThu);
        khoanThuDAO = new KhoanThuDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        MaKhoanThu = b.getString("ID");
        TenKhoanThu = b.getString("NAME");
        edTenKhoanThu.setText(TenKhoanThu);
    }

    public void updatekhoanthu(View view) {
        if (khoanThuDAO.updateKhoanThu(MaKhoanThu,edTenKhoanThu.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.alertsuccessfully), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void huykhoanthu(View view) {
    }
}
