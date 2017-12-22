package com.example.classroom.classroom;

/**
 * Created by Fatih on 22.12.2017.
 */

public class Mesaj {
    private String gonderenMail,aliciMail,mesaj,tarih;
    public Mesaj(String gonderenIMail, String aliciIMail, String tarih,String mesaj) {
        this.gonderenMail = gonderenIMail;
        this.aliciMail = aliciIMail;
        this.tarih = tarih;
        this.mesaj=mesaj;
    }
    public String getGonderenMail() {
        return gonderenMail;
    }

    public void setGonderenMail(String gonderenId) {
        this.gonderenMail = gonderenId;
    }

    public String getAliciMail() {
        return aliciMail;
    }

    public void setAliciMail(String aliciId) {
        this.aliciMail = aliciId;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
