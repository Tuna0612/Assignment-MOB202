package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tuna.assignment.model.KhoanChi;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiDAO {
    private SQLiteDatabase db;
    private DatabaseManager databasemanager;
    public static final String TB_KHOAN_CHI = "KhoanChi";
    public static final String SQL_KHOAN_CHI = "CREATE TABLE IF NOT EXISTS KhoanChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenKhoanChi TEXT)";
    public static final String TAG = "KHOAN_CHI_DAO";

    public KhoanChiDAO(Context context) {
        databasemanager = new DatabaseManager(context);
        db = databasemanager.getWritableDatabase();
    }

    public int insertKhoanChi(KhoanChi khoanChi){
        ContentValues values = new ContentValues();
        values.put("_id", khoanChi.getMaKhoanChi());
        values.put("tenKhoanChi", khoanChi.getTenKhoanChi());
        try {
            if (db.insert(TB_KHOAN_CHI, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public int updateKhoanChi(String maKhoanChi, String edTenKhoanChi) {
        ContentValues values = new ContentValues();
        values.put("tenKhoanChi", edTenKhoanChi);
        int result = db.update(TB_KHOAN_CHI, values, "_id=?", new String[]{maKhoanChi});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public List<KhoanChi> getAllKhoanChi() {
        List<KhoanChi> dsKhoanChi = new ArrayList<>();
        Cursor c = db.query(TB_KHOAN_CHI, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            KhoanChi ee = new KhoanChi();
            ee.setMaKhoanChi(c.getString(0));
            ee.setTenKhoanChi(c.getString(1));
            dsKhoanChi.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsKhoanChi;
    }

    public int deleteKhoanChiByID(String maKhoanChi) {
        int result = db.delete(TB_KHOAN_CHI, "_id=?", new String[]{maKhoanChi});
        if (result == 0)
            return -1;
        return 1;
    }
}
