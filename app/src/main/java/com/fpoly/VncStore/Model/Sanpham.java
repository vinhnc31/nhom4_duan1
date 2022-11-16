package com.fpoly.VncStore.Model;

public class Sanpham {
    private int maloai;
    private String tensanpham;
    private int soluongsanpham;
    private String hinhanh;
    private int gianiemyet;
    private int giakhuyenmai;
    private String thongtinsanpham;

    public Sanpham() {
    }

    public Sanpham(int maloai, String tensanpham, int soluongsanpham, String hinhanh, int gianiemyet, int giakhuyenmai, String thongtinsanpham) {
        this.maloai = maloai;
        this.tensanpham = tensanpham;
        this.soluongsanpham = soluongsanpham;
        this.hinhanh = hinhanh;
        this.gianiemyet = gianiemyet;
        this.giakhuyenmai = giakhuyenmai;
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

    public int getGianiemyet() {
        return gianiemyet;
    }

    public void setGianiemyet(int gianiemyet) {
        this.gianiemyet = gianiemyet;
    }

    public int getGiakhuyenmai() {
        return giakhuyenmai;
    }

    public void setGiakhuyenmai(int giakhuyenmai) {
        this.giakhuyenmai = giakhuyenmai;
    }

    public String getThongtinsanpham() {
        return thongtinsanpham;
    }

    public void setThongtinsanpham(String thongtinsanpham) {
        this.thongtinsanpham = thongtinsanpham;
    }
}
