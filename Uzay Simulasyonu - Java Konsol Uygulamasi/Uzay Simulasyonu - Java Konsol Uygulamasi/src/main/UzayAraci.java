package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String ad;
    private String cikisGezegen;
    private String varisGezegen;
    private String cikisTarihi;
    private boolean hareketeGecti; // Yeni alan eklendi
    private int mesafeSaat;
    private List<Kisi> yolcular;
    private boolean imhaEdildi;

    public UzayAraci(String ad, String cikisGezegen, String varisGezegen, String cikisTarihi, int mesafeSaat) {
        this.ad = ad;
        this.cikisGezegen = cikisGezegen;
        this.varisGezegen = varisGezegen;
        this.cikisTarihi = cikisTarihi;
        this.mesafeSaat = mesafeSaat;
        this.yolcular = new ArrayList<>();
        this.imhaEdildi = false;
    }
    
    public boolean isImhaEdildi() {
        return imhaEdildi;
    }

    
    public String getVarisTarihi(Gezegen gezegen) {
        int gunSayisi = mesafeSaat / gezegen.getGunSaat();
        int kalanSaat = mesafeSaat % gezegen.getGunSaat();

        String tarih = gezegen.getTarih(); // GG.AA.YYYY formatında

        // Tarihi ilerlet
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        LocalDate date = LocalDate.parse(tarih, formatter);
        date = date.plusDays(gunSayisi);
        String varisTarihi = date.format(formatter);

        return varisTarihi + (kalanSaat > 0 ? " +" + kalanSaat + "saat" : "");
    }


    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }
    
    public boolean isHareketeGecti() {
        return hareketeGecti;
    }
    
    public void kontrolEtVeBaslat(String gezegenTarihi) {
    	if (!hareketeGecti && cikisTarihi.equals(gezegenTarihi)) {
    		hareketeGecti = true;
    	}
    }

    public void saatIlerle() {
        if (!imhaEdildi && hareketeGecti) {
            mesafeSaat--;
            yolcular.removeIf(Kisi::olduMu); // Ölenleri çıkar
            if (yolcular.isEmpty()) {
                imhaEdildi = true;
            }
        }
    }

    public boolean varisTamamlandi() {
        return mesafeSaat <= 0;
    }

    // ✅ Eksik Metotlar Eklendi
    public String getAd() {
        return ad;
    }

    public String getCikisGezegen() {
        return cikisGezegen;
    }
    
    public String getVarisGezegen() {
        return varisGezegen;
    }


    public int getYolcuSayisi() {
        return yolcular.size();
    }

    @Override
    public String toString() {
        if (imhaEdildi) {
            return ad + " [İMHA]";
        }
        return ad + " - Hedefe kalan saat: " + mesafeSaat + " - Yolcu sayısı: " + yolcular.size();
    }
}
