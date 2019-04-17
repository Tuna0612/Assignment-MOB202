package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tuna.assignment.model.LoaiChi;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiDAO {
    private SQLiteDatabase db;
    private DatabaseManager databasemanager;
    public static final String TB_LOAI_CHI = "LoaiChi";
    public static final String SQL_LOAI_CHI = "CREATE TABLE IF NOT EXISTS LoaiChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenLoaiChi TEXT)";
    public static final String TAG = "LOAI_CHI_DAO";

    public LoaiChiDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertLoaiChi(LoaiChi loaiChi){
        ContentValues values = new ContentValues();
        values.put("_id", loaiChi.getMaLoaiChi());
        values.put("tenLoaiChi", loaiChi.getTenLoaiChi());
        try {
            if (db.insert(TB_LOAI_CHI, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateLoaiChi(String edMaLoaiChi, String edTenLoaiChi) {
        ContentValues values = new ContentValues();
        values.put("tenLoaiChi", edTenLoaiChi);
        int result = db.update(TB_LOAI_CHI, values, "_id=?", new String[]{edMaLoaiChi});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<LoaiChi> getAllLoaiChi() {
        List<LoaiChi> dsLoaiChi = new ArrayList<>();
        Cursor c = db.query(TB_LOAI_CHI, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            LoaiChi ee = new LoaiChi();
            ee.setMaLoaiChi(c.getString(0));
            ee.setTenLoaiChi(c.getString(1));
            dsLoaiChi.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsLoaiChi;
    }

    public int deleteLoaiChiByID(String maLoaiChi) {
        int result = db.delete(TB_LOAI_CHI, "_id=?", new String[]{maLoaiChi});
        if (result == 0)
            return -1;
        return 1;
    }
}
