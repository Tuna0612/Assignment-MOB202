package com.tuna.assignment.model;

public class KhoanChi {
    private String maKhoanChi;
    private String tenKhoanChi;

    public KhoanChi() {
    }

    public KhoanChi(String maKhoanChi, String tenKhoanChi) {
        this.maKhoanChi = maKhoanChi;
        this.tenKhoanChi = tenKhoanChi;
    }

    public String getMaKhoanChi() {
        return maKhoanChi;
    }

    public void setMaKhoanChi(String maKhoanChi) {
        this.maKhoanChi = maKhoanChi;
    }

    public String getTenKhoanChi() {
        return tenKhoanChi;
    }

    public void setTenKhoanChi(String tenKhoanChi) {
        this.tenKhoanChi = tenKhoanChi;
    }
}
