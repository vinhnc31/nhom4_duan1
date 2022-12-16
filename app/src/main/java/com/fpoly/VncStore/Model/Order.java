package com.fpoly.VncStore.Model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String orderNo;
    private String diachi;
    private String tenkhachhang;
    private String phone;
    private String ngaymua;
    private int soluong;
    private int tongtien;
    private String trangthai;
    private String idemail;
    private List<Sanpham> sanphamList;

    public Order() {
    }

    public Order( String diachi, String tenkhachhang, String phone, String ngaymua, int soluong, int tongtien, String trangthai, String idemail, List<Sanpham> sanphamList) {
        this.diachi = diachi;
        this.tenkhachhang = tenkhachhang;
        this.phone = phone;
        this.ngaymua = ngaymua;
        this.soluong = soluong;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.idemail = idemail;
        this.sanphamList = sanphamList;
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

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getIdemail() {
        return idemail;
    }

    public void setIdemail(String idemail) {
        this.idemail = idemail;
    }

    public List<Sanpham> getSanphamList() {
        return sanphamList;
    }

    public void setSanphamList(List<Sanpham> sanphamList) {
        this.sanphamList = sanphamList;
    }
}