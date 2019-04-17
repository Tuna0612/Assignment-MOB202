package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
//    Bước 1: Khai báo thông tin DB
    public static final String DB_NAME = "QuanLyThuChi";
    public static final int DB_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KhoanThuDAO.SQL_KHOAN_THU);
        db.execSQL(KhoanChiDAO.SQL_KHOAN_CHI);
        db.execSQL(LoaiThuDAO.SQL_LOAI_THU);
        db.execSQL(LoaiChiDAO.SQL_LOAI_CHI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +KhoanChiDAO.TB_KHOAN_CHI);
        db.execSQL("DROP TABLE IF EXISTS " +KhoanThuDAO.TB_KHOAN_THU);
        db.execSQL("DROP TABLE IF EXISTS " +LoaiChiDAO.TB_LOAI_CHI);
        db.execSQL("DROP TABLE IF EXISTS " +LoaiThuDAO.TB_LOAI_THU);
        onCreate(db);
    }

//    update
//    public void updateKhoanThu(String tenKhoanThu, int id){
//        ContentValues values = new ContentValues();
//        values.put("tenKhoanThu", tenKhoanThu);
//        database.update(TB_KHOAN_THU, values, "_id = "+id,null);
//    }
//    public void updateLoaiThu(String tenLoaiThu, int id){
//        ContentValues values = new ContentValues();
//        values.put("tenLoaiThu", tenLoaiThu);
//        database.update(TB_LOAI_THU, values, "_id = "+id,null);
//    }
//    public void updateKhoanChi(String tenKhoanChi, int id){
//        ContentValues values = new ContentValues();
//        values.put("tenKhoanChi", tenKhoanChi);
//        database.update(TB_KHOAN_CHI, values, "_id = "+id,null);
//    }
//    public void updateLoaiChi(String tenLoaiChi, int id){
//        ContentValues values = new ContentValues();
//        values.put("tenLoaiChi", tenLoaiChi);
//        database.update(TB_LOAI_CHI, values, "_id = "+id,null);
//    }
//
////    Delete
//    public void deleteKhoanThu(int id){
//        database.delete(TB_KHOAN_THU, "_id = "+id, null);
//    }
//    public void deleteLoaiThu(int id){
//        database.delete(TB_LOAI_THU, "_id = "+id, null);
//    }
//    public void deleteKhoanChi(int id){
//        database.delete(TB_KHOAN_CHI, "_id = "+id, null);
//    }
//    public void deleteLoaiChi(int id){
//        database.delete(TB_LOAI_CHI, "_id = "+id, null);
//    }
////    select
//    public Cursor getKhoanThu(){
//        return database.query(TB_KHOAN_THU, null, null, null, null, null, null);
//    }
//    public Cursor getLoaiThu(){
//        return database.query(TB_LOAI_THU, null,null,null,null,null,null);
//    }
//    public Cursor getKhoanChi(){
//        return database.query(TB_KHOAN_CHI, null,null,null,null,null,null);
//    }
//    public Cursor getLoaiChi(){
//        return database.query(TB_LOAI_CHI, null,null,null,null,null,null);
//    }
}
