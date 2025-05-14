package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Gezegen {
    private String ad;
    private int gunSaat; // 1 gün kaç saat
    private String tarih; // GG.AA.YYYY
    private int nufus;
    private int saatSayaci = 0;

    public Gezegen(String ad, int gunSaat, String tarih, int nufus) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.tarih = tarih;
        this.nufus = nufus;
    }
    
    public int getGunSaat() {
        return gunSaat;
    }


    public void saatIlerle() {
        saatSayaci++;
        if (saatSayaci >= gunSaat) {
            tarihIlerle(); // gün atla
            saatSayaci = 0;
        }
    }

    private void tarihIlerle() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate date = LocalDate.parse(tarih, formatter);
        date = date.plusDays(1);
        tarih = date.format(formatter);
    }

    public String getAd() {
        return ad;
    }

    public String getTarih() {
        return tarih;
    }

    public void nufusEkle(int kisiSayisi) {
        this.nufus += kisiSayisi;
    }

    public void nufusAzalt(int kisiSayisi) {
        this.nufus -= kisiSayisi;
        if (this.nufus < 0) this.nufus = 0;
    }

    @Override
    public String toString() {
        return ad + " - Tarih: " + tarih + ", Nüfus: " + nufus;
    }
}
