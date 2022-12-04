package com.fpoly.VncStore.Model;

import java.io.Serializable;

public class Hoadon implements Serializable{
    private String idOder;
    private String namesp;
    private String imge;
    private String trangthai;
    private int soluong;
    private int giasp;
    private String idHoadon;

    public Hoadon() {
    }

    public Hoadon(String idOder, String namesp, String imge, String trangthai, int soluong, int giasp, String idHoadon) {
        this.idOder = idOder;
        this.namesp = namesp;
        this.imge = imge;
        this.trangthai = trangthai;
        this.soluong = soluong;
        this.giasp = giasp;
        this.idHoadon = idHoadon;
    }

    public String getIdOder() {
        return idOder;
    }

    public String getIdHoadon() {
        return idHoadon;
    }

    public void setIdHoadon(String idHoadon) {
        this.idHoadon = idHoadon;
    }

    public void setIdOder(String idOder) {
        this.idOder = idOder;
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
