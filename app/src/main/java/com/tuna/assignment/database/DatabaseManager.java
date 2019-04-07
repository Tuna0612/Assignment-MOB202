package com.tuna.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {
//    Bước 1: Khai báo thông tin DB
    public static final String DB_NAME = "QuanLyThuChi";
    public static final String TB_KHOAN_THU = "KhoanThu";
    public static final String TB_KHOAN_CHI = "KhoanChi";
    public static final String TB_LOAI_THU = "LoaiThu";
    public static final String TB_LOAI_CHI = "LoaiChi";
    public static final int DB_VERSION = 1;
    private SQLiteDatabase database;


//    Bước 4: Tạo constructor DatabaseManager


    public DatabaseManager(Context context) {
        OpenHelper helper = new OpenHelper(context);
        database = helper.getWritableDatabase();
    }

    //    Bước 3: Tạo các phương thức làm việc với database
//    insert
    public void insertKhoanThu(String tenKhoanThu){
        ContentValues values = new ContentValues();
        values.put("tenKhoanThu", tenKhoanThu);
        database.insert(TB_KHOAN_THU, null, values);
    }
    public void insertLoaiThu(String tenLoaiThu){
        ContentValues values = new ContentValues();
        values.put("tenLoaiThu", tenLoaiThu);
        database.insert(TB_LOAI_THU, null, values);
    }
    public void insertKhoanChi(String tenKhoanChi){
        ContentValues values = new ContentValues();
        values.put("tenKhoanChi", tenKhoanChi);
        database.insert(TB_KHOAN_CHI, null, values);
    }
    public void insertLoaiChi(String tenLoaiChi){
        ContentValues values = new ContentValues();
        values.put("tenLoaiChi", tenLoaiChi);
        database.insert(TB_LOAI_CHI, null, values);
    }

//    update
    public void updateKhoanThu(String tenKhoanThu, int id){
        ContentValues values = new ContentValues();
        values.put("tenKhoanThu", tenKhoanThu);
        database.update(TB_KHOAN_THU, values, "_id = "+id,null);
    }
    public void updateLoaiThu(String tenLoaiThu, int id){
        ContentValues values = new ContentValues();
        values.put("tenLoaiThu", tenLoaiThu);
        database.update(TB_LOAI_THU, values, "_id = "+id,null);
    }
    public void updateKhoanChi(String tenKhoanChi, int id){
        ContentValues values = new ContentValues();
        values.put("tenKhoanChi", tenKhoanChi);
        database.update(TB_KHOAN_CHI, values, "_id = "+id,null);
    }
    public void updateLoaiChi(String tenLoaiChi, int id){
        ContentValues values = new ContentValues();
        values.put("tenLoaiChi", tenLoaiChi);
        database.update(TB_LOAI_CHI, values, "_id = "+id,null);
    }

//    Delete
    public void deleteKhoanThu(int id){
        database.delete(TB_KHOAN_THU, "_id = "+id, null);
    }
    public void deleteLoaiThu(int id){
        database.delete(TB_LOAI_THU, "_id = "+id, null);
    }
    public void deleteKhoanChi(int id){
        database.delete(TB_KHOAN_CHI, "_id = "+id, null);
    }
    public void deleteLoaiChi(int id){
        database.delete(TB_LOAI_CHI, "_id = "+id, null);
    }
//    select
    public Cursor getKhoanThu(){
        return database.query(TB_KHOAN_THU, null, null, null, null, null, null);
    }
    public Cursor getLoaiThu(){
        return database.query(TB_LOAI_THU, null,null,null,null,null,null);
    }
    public Cursor getKhoanChi(){
        return database.query(TB_KHOAN_CHI, null,null,null,null,null,null);
    }
    public Cursor getLoaiChi(){
        return database.query(TB_LOAI_CHI, null,null,null,null,null,null);
    }


//    Bước 2: Tạo innerClass OpenHelper extends SQLiteOpenHelper
//    Sau đó tạo constructor và overide 2 phương thức onCreate và onUpgrade
    public class OpenHelper extends SQLiteOpenHelper {

    public OpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS KhoanThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenKhoanThu TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS LoaiThu(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenLoaiThu TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS KhoanChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenKhoanChi TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS LoaiChi(_id INTEGER PRIMARY KEY AUTOINCREMENT, tenLoaiChi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS KhoanThu");
        db.execSQL("DROP TABLE IF EXISTS LoaiThu");
        db.execSQL("DROP TABLE IF EXISTS KhoanChi");
        db.execSQL("DROP TABLE IF EXISTS LoaiChi");
        onCreate(db);
    }
}

}
