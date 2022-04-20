package org.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "memployee")
public class Employee {

//    @Id
//    private int mEmployeePk;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "mdepartemenfk")
//    private Departemen departemen;
//    private int mDepartemenFk;
//    private String employeeName;
//    private String npp;
//    private String address;
//
//    private Departemen getDepartemen() {
//        return departemen;
//    }
//
//    private void setDepartemen(Departemen departemen) {
//        this.departemen = departemen;
//    }
//
//    public int getmDepartemenFk() {
//        return mDepartemenFk;
//    }
//
//    public void setmDepartemenFk(int mDepartemenFk) {
//        this.mDepartemenFk = mDepartemenFk;
//    }
//    public int getmEmployeePk() {
//        return mEmployeePk;
//    }
//
//    public void setmEmployeePk(int mEmployeePk) {
//        this.mEmployeePk = mEmployeePk;
//    }
//
//
//    public String getEmployeeName() {
//        return employeeName;
//    }
//
//    public void setEmployeeName(String employeeName) {
//        this.employeeName = employeeName;
//    }
//
//    public String getNpp() {
//        return npp;
//    }
//
//    public void setNpp(String npp) {
//        this.npp = npp;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
    @Id
    @SequenceGenerator(name = "seq_generator_memployeepk", sequenceName = "memployee_seq",initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seq_generator_memployeepk", strategy = GenerationType.SEQUENCE)
    @Column(name = "memployeepk")
    private int id_pegawai;

    @Column(name = "mdepartemenfk")
    private int id_departemen;

    @Column(name = "employeename")
    private String nama;
    @Column(name = "npp")
    private String npp;
    @Column(name = "address")
    private String alamat;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Bangkok")
    @Column(name = "join_at")
    @Temporal(TemporalType.DATE)
    private Date tgl_gabung;

    public int getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(int id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mdepartemenfk", insertable = false, updatable = false)
    private Departemen departemen;
    private Departemen getDepartemen() {
        return departemen;
    }

    private void setDepartemen(Departemen departemen) {
        this.departemen = departemen;
    }
}
