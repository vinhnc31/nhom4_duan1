package com.fpoly.VncStore.Model;

import java.io.Serializable;
import java.util.Objects;

public class Sanpham implements Serializable {
    private String name;
    private String image;
    private String Gia;
    private String khuyenmai;
    private String moTa;

    private int numProduct = 1;
    public Sanpham() {
    }

    public Sanpham(String name, String image, String gia, String khuyenmai, String moTa) {
        this.name = name;
        this.image = image;
        Gia = gia;
        this.khuyenmai = khuyenmai;
        this.moTa = moTa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String  gia) {
        Gia = gia;
    }

    public String getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(String khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }
}
