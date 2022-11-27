package com.fpoly.VncStore.Model;

public class Hoadon {
    private String OrderNo;
    private String namesp;
    private String imge;
    private String trangthai;
    private int soluong;
    private int giasp;

    public Hoadon() {
    }

    public Hoadon(String orderNo, String namesp, String imge, String trangthai, int soluong, int giasp) {
        OrderNo = orderNo;
        this.namesp = namesp;
        this.imge = imge;
        this.trangthai = trangthai;
        this.soluong = soluong;
        this.giasp = giasp;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getNamesp() {
        return namesp;
    }

    public void setNamesp(String namesp) {
        this.namesp = namesp;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
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

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }
}
