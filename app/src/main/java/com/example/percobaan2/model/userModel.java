package com.example.percobaan2.model;
public class userModel {
    private int id;
    private String fullname;
    private String username;
    private String tanggal_lahir;
    private String email;
    private String password;
    private String gender;
    private String nohp;
    private String alamat;


    public  userModel(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public userModel(int id, String fullname, String username, String tanggal_lahir, String email, String password, String gender, String nohp, String alamat) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.tanggal_lahir = tanggal_lahir;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.nohp = nohp;
        this.alamat = alamat;
    }
}