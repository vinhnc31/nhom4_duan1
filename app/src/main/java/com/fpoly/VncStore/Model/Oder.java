package com.fpoly.VncStore.Model;

import java.util.ArrayList;
import java.util.List;

public class Oder {
    private String OrderNo;
    private String diachi;
    private String name;
    private String phone;
    private String ngaymua;
    private String trangthai;
    private int soluong;
    private int tong;
    private List<Hoadon> hoadonList;

    public Oder(String orderNo, String diachi, String name, String phone, String ngaymua, String trangthai, int soluong, int tong, List<Hoadon> hoadonList) {
        OrderNo = orderNo;
        this.diachi = diachi;
        this.name = name;
        this.phone = phone;
        this.ngaymua = ngaymua;
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
