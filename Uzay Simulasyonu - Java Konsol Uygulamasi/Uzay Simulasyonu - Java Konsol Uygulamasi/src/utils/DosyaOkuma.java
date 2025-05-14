package utils;

import main.Kisi;
import main.UzayAraci;
import main.Gezegen;

import java.io.*;
import java.util.*;

public class DosyaOkuma {

    private static BufferedReader bufferedReaderFromResource(String path) throws IOException {
        InputStream input = DosyaOkuma.class.getClassLoader().getResourceAsStream(path);
        if (input == null) throw new FileNotFoundException("Kaynak bulunamadı: " + path);
        return new BufferedReader(new InputStreamReader(input));
    }

    public static List<Kisi> kisileriOku(String dosyaAdi) throws IOException {
        List<Kisi> kisiler = new ArrayList<>();
        BufferedReader br = bufferedReaderFromResource(dosyaAdi);
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            kisiler.add(new Kisi(parcalar[0], Integer.parseInt(parcalar[1]),
                                 Integer.parseInt(parcalar[2]), parcalar[3]));
        }
        br.close();
        return kisiler;
    }

    public static List<UzayAraci> araclariOku(String dosyaAdi) throws IOException {
        List<UzayAraci> araclar = new ArrayList<>();
        BufferedReader br = bufferedReaderFromResource(dosyaAdi);
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            araclar.add(new UzayAraci(parcalar[0], parcalar[1], parcalar[2], parcalar[3], Integer.parseInt(parcalar[4])));
        }
        br.close();
        return araclar;
    }

    public static List<Gezegen> gezegenleriOku(String dosyaAdi) throws IOException {
        List<Gezegen> gezegenler = new ArrayList<>();
        BufferedReader br = bufferedReaderFromResource(dosyaAdi);
        String satir;
        while ((satir = br.readLine()) != null) {
            String[] parcalar = satir.split("#");
            gezegenler.add(new Gezegen(parcalar[0], Integer.parseInt(parcalar[1]), parcalar[2], 0));
        }
        br.close();
        return gezegenler;
    }
}
