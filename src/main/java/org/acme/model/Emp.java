package org.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emp {
    private int id_departemen;
    private String nama;
    private String npp;
    private String alamat;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Bangkok")
    private Date tgl_gabung;


    public int getId_departemen() {
        return id_departemen;
    }

    public void setId_departemen(int id_departemen) {
        this.id_departemen = id_departemen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Date getTgl_gabung() {
        return tgl_gabung;
    }

    public void setTgl_gabung(Date tgl_gabung) {
        this.tgl_gabung = tgl_gabung;
    }

    public void setTgl_gabung(String tgl_gabung) throws ParseException {
        this.tgl_gabung = new SimpleDateFormat("yyyy-MM-dd").parse(tgl_gabung);
    }
}
