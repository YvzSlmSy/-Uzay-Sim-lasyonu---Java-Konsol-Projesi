package utils;

public class Zaman {
    private int saat;

    public Zaman() {
        this.saat = 0;
    }

    public void ileriSar() {
        saat++;
    }

    public int getSaat() {
        return saat;
    }
}
