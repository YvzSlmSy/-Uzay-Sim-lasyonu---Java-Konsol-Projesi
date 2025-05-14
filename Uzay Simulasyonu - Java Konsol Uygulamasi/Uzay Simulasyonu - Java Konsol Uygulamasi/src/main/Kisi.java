package main;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur;
    private String uzayAraciAdi;
    private boolean bildirildiMi = false; // Yeni eklendi

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public void yaslan() {
        if (kalanOmur > 0) kalanOmur--;
    }

    public boolean olduMu() {
        return kalanOmur <= 0;
    }

    public boolean isBildirildiMi() {
        return bildirildiMi;
    }

    public void setBildirildiMi(boolean bildirildiMi) {
        this.bildirildiMi = bildirildiMi;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }

    public String getIsim() {
        return isim;
    }

    @Override
    public String toString() {
        return isim + " (" + yas + " yaşında, kalan ömür: " + kalanOmur + " saat)";
    }
}
