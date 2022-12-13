package com.fpoly.VncStore.Model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    private String idSanPham;
    private String name;
    private String image;
    private int Gia;
    private String khuyenmai;
    private String moTa;
    private int numProduct = 1;
    public Sanpham() {
    }

    public Sanpham(String idSanPham, String name, String image, int gia, String khuyenmai, String moTa, int numProduct) {
        this.idSanPham = idSanPham;
        this.name = name;
        this.image = image;
        Gia = gia;
        this.khuyenmai = khuyenmai;
        this.moTa = moTa;
        this.numProduct = numProduct;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int  gia) {
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
