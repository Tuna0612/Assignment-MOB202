package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tuna.assignment.model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDAO {
    private SQLiteDatabase db;
    private DatabaseManager databasemanager;
    public static final String TB_LOAI_THU = "LoaiThu";
    public static final String SQL_LOAI_THU = "CREATE TABLE IF NOT EXISTS LoaiThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenLoaiThu TEXT)";
    public static final String TAG = "LOAI_THU_DAO";

    public LoaiThuDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertLoaiThu(LoaiThu loaiThu){
        ContentValues values = new ContentValues();
        values.put("_id", loaiThu.getMaLoaiThu());
        values.put("tenLoaiThu", loaiThu.getTenLoaiThu());
        try {
            if (db.insert(TB_LOAI_THU, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateLoaiThu(String edMaLoaiThu, String edTenLoaiThu) {
        ContentValues values = new ContentValues();
        values.put("tenLoaiThu", edTenLoaiThu);
        int result = db.update(TB_LOAI_THU, values, "_id=?", new String[]{edMaLoaiThu});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<LoaiThu> getAllLoaiThu() {
        List<LoaiThu> dsLoaiThu = new ArrayList<>();
        Cursor c = db.query(TB_LOAI_THU, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            LoaiThu ee = new LoaiThu();
            ee.setMaLoaiThu(c.getString(0));
            ee.setTenLoaiThu(c.getString(1));
            dsLoaiThu.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsLoaiThu;
    }

    public int deleteLoaiThuByID(String maLoaiThu) {
        int result = db.delete(TB_LOAI_THU, "_id=?", new String[]{maLoaiThu});
        if (result == 0)
            return -1;
        return 1;
    }
}
