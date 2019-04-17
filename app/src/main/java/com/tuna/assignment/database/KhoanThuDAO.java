package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tuna.assignment.model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDAO {
    private SQLiteDatabase db;
    private DatabaseManager databasemanager;
    public static final String TB_KHOAN_THU = "KhoanThu";
    public static final String SQL_KHOAN_THU = "CREATE TABLE IF NOT EXISTS KhoanThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenKhoanThu TEXT)";
    public static final String TAG = "KHOAN_THU_DAO";

    public KhoanThuDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertKhoanThu(KhoanThu khoanThu){
        ContentValues values = new ContentValues();
        values.put("_id", khoanThu.getMaKhoanThu());
        values.put("tenKhoanThu", khoanThu.getTenKhoanThu());
        try {
            if (db.insert(TB_KHOAN_THU, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateKhoanThu(String edMaKhoanThu, String edTenKhoanThu) {
        ContentValues values = new ContentValues();
        values.put("tenKhoanThu", edTenKhoanThu);
        int result = db.update(TB_KHOAN_THU, values, "_id=?", new String[]{edMaKhoanThu});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<KhoanThu> getAllKhoanThu() {
        List<KhoanThu> dsKhoanThu = new ArrayList<>();
        Cursor c = db.query(TB_KHOAN_THU, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            KhoanThu ee = new KhoanThu();
            ee.setMaKhoanThu(c.getString(0));
            ee.setTenKhoanThu(c.getString(1));
            dsKhoanThu.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsKhoanThu;
    }

    public int deleteKhoanThuByID(String maKhoanThu) {
        int result = db.delete(TB_KHOAN_THU, "_id=?", new String[]{maKhoanThu});
        if (result == 0)
            return -1;
        return 1;
    }
}
