package com.fpoly.VncStore.Model;

public class User {
    public User() {
    }

    private int so;
    private String ten,email,diachi;

    public int getSo() {
        return so;
    }

    public void setSo(int so) {
        this.so = so;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public User(int so, String ten, String email, String diachi) {
        this.so = so;
        this.ten = ten;
        this.email = email;
        this.diachi = diachi;
    }
}
