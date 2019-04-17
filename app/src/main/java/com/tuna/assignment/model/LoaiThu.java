package com.tuna.assignment.model;

public class LoaiThu {
    private String maLoaiThu;
    private String tenLoaiThu;

    public LoaiThu() {
    }

    public LoaiThu(String maLoaiThu, String tenLoaiThu) {
        this.maLoaiThu = maLoaiThu;
        this.tenLoaiThu = tenLoaiThu;
    }

    public String getMaLoaiThu() {
        return maLoaiThu;
    }

    public void setMaLoaiThu(String maLoaiThu) {
        this.maLoaiThu = maLoaiThu;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }
}
