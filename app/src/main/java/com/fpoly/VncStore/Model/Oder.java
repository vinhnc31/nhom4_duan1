package com.fpoly.VncStore.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Oder implements Serializable {
    private String orderNo;
    private String diachi;
    private String tenkhachhang;
    private String phone;
    private String ngaymua;
    private int soluong;
    private int tongtien;
    private List<Hoadon> hoadonList;

    public Oder(String orderNo, String diachi, String tenkhachhang, String phone, String ngaymua, int soluong, int tongtien, List<Hoadon> hoadonList) {
        this.orderNo = orderNo;
        this.diachi = diachi;
        this.tenkhachhang = tenkhachhang;
        this.phone = phone;
        this.ngaymua = ngaymua;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.hoadonList = hoadonList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public Oder() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public List<Hoadon> getHoadonList() {
        return this.hoadonList;
    }

    public void setHoadonList(List<Hoadon> hoadonList) {
        this.hoadonList = hoadonList;
    }

    public void addListHoaDon(Hoadon hoadon) {
        if (this.hoadonList == null) {
            this.hoadonList = new ArrayList<>();
        }
        this.hoadonList.add(hoadon);
    }
}