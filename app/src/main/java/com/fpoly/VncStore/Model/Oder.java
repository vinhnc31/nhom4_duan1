package com.fpoly.VncStore.Model;

import java.util.ArrayList;
import java.util.List;

public class Oder {
    private String OrderNo;
    private String diachi;
    private String name;
    private String so;
    private String ngay;
    private String trangthai;
    private int soluong;
    private int tong;
    private List<Hoadon> hoadonList;

    public Oder(String orderNo, String diachi, String name, String so, String ngay, String trangthai, int soluong, int tong, List<Hoadon> hoadonList) {
        OrderNo = orderNo;
        this.diachi = diachi;
        this.name = name;
        this.so = so;
        this.ngay = ngay;
        this.trangthai = trangthai;
        this.soluong = soluong;
        this.tong = tong;
        this.hoadonList = hoadonList;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTong() {
        return tong;
    }

    public void setTong(int tong) {
        this.tong = tong;
    }

    public List<Hoadon> getHoadonList() {
        return hoadonList;
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
