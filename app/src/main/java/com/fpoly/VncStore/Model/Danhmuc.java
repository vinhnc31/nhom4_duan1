package com.fpoly.VncStore.Model;

public class Danhmuc {
    private String ten;
    private int anh;

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Danhmuc(String ten, int anh) {
        this.ten = ten;
        this.anh = anh;
    }
}
