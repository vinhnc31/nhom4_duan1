package com.fpoly.VncStore.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChitietHoaDon implements Serializable {
    private String idChitietHoaDon;
    private String diachi;
    private String tenkhachhang;
    private String phone;
    private String ngaymua;
    private int soluong;
    private int tongtien;
    private List<Hoadon> hoadonList;

    public ChitietHoaDon(String idChitietHoaDon, String diachi, String tenkhachhang, String phone, String ngaymua, String trangthai, int soluong, int tongtien, List<Hoadon> hoadonList) {
        this.idChitietHoaDon = idChitietHoaDon;
        this.diachi = diachi;
        this.tenkhachhang = tenkhachhang;
        this.phone = phone;
        this.ngaymua = ngaymua;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.hoadonList = hoadonList;
    }

    public String getIdChitietHoaDon() {
        return idChitietHoaDon;
    }

    public void setIdChitietHoaDon(String idChitietHoaDon) {
        this.idChitietHoaDon = idChitietHoaDon;
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

    public ChitietHoaDon() {
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
