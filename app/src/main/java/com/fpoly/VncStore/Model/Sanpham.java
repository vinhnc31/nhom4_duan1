package com.fpoly.VncStore.Model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private int maloai;
    private String tensanpham;
    private int soluongsanpham;
    private String hinhanh;
    private int giasanphamcu;
    private int giasamphammoi;
    private String thongtinsanpham;

    public Sanpham() {
    }

    public Sanpham(int maloai, String tensanpham, int soluongsanpham, String hinhanh, int giasanphamcu, int giasamphammoi, String thongtinsanpham) {
        this.maloai = maloai;
        this.tensanpham = tensanpham;
        this.soluongsanpham = soluongsanpham;
        this.hinhanh = hinhanh;
        this.giasanphamcu = giasanphamcu;
        this.giasamphammoi = giasamphammoi;
        this.thongtinsanpham = thongtinsanpham;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSoluongsanpham() {
        return soluongsanpham;
    }

    public void setSoluongsanpham(int soluongsanpham) {
        this.soluongsanpham = soluongsanpham;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getGiasanphamcu() {
        return giasanphamcu;
    }

    public void setGiasanphamcu(int giasanphamcu) {
        this.giasanphamcu = giasanphamcu;
    }

    public int getGiasamphammoi() {
        return giasamphammoi;
    }

    public void setGiasamphammoi(int giasamphammoi) {
        this.giasamphammoi = giasamphammoi;
    }

    public String getThongtinsanpham() {
        return thongtinsanpham;
    }

    public void setThongtinsanpham(String thongtinsanpham) {
        this.thongtinsanpham = thongtinsanpham;
    }
}
