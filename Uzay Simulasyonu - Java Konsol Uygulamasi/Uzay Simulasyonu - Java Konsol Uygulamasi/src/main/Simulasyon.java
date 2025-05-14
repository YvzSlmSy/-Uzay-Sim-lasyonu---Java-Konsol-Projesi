package main;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import utils.DosyaOkuma;
import utils.Zaman;

public class Simulasyon {
    public static void main(String[] args) {
        System.out.println("🚀 Uzay Simülasyonu Başlatılıyor...\n");
        System.out.println("TEST: " + Simulasyon.class.getClassLoader().getResource("data/Kisiler.txt"));

        try {
            FileWriter fw = new FileWriter("log.txt", true);
            fw.write("Main metodu başlatıldı.\n");
            fw.flush();
            fw.close();
            // Dosyaları oku
            System.out.println("🔧 Dosyalar okunuyor...");

            List<Kisi> kisiler = DosyaOkuma.kisileriOku("data/Kisiler.txt");
            System.out.println("✅ Kisiler yüklendi: " + kisiler.size());

            List<UzayAraci> uzayAraclari = DosyaOkuma.araclariOku("data/Araclar.txt");
            System.out.println("✅ Uzay araçları yüklendi: " + uzayAraclari.size());

            List<Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("data/Gezegenler.txt");
            System.out.println("✅ Gezegenler yüklendi: " + gezegenler.size());

            Zaman zaman = new Zaman();

            for (Kisi kisi : kisiler) {
                for (UzayAraci arac : uzayAraclari) {
                    if (kisi.getUzayAraciAdi().equals(arac.getAd())) {
                        arac.yolcuEkle(kisi);
                        break;
                    }
                }
            }

            for (UzayAraci arac : uzayAraclari) {
                for (Gezegen gezegen : gezegenler) {
                    if (arac.getCikisGezegen().equals(gezegen.getAd())) {
                        gezegen.nufusAzalt(arac.getYolcuSayisi());
                    }
                }
            }

            //List<String> bildirilenOluler = new ArrayList<>();
            List<String> vardigiGezegenler = new ArrayList<>();

            while (true) {
               // temizleEkran(); // ⚠️ Ekranı temizliyor ama konsol sessiz olursa silebilir
                System.out.println("⏳ Saat: " + zaman.getSaat());

                for (UzayAraci arac : uzayAraclari) {
                    arac.saatIlerle();
                    if (arac.varisTamamlandi() && !vardigiGezegenler.contains(arac.getAd())) {
                        for (Gezegen g : gezegenler) {
                            if (g.getAd().equals(arac.getVarisGezegen())) {
                                g.nufusEkle(arac.getYolcuSayisi());
                                vardigiGezegenler.add(arac.getAd());
                            }
                        }
                    }
                }

                System.out.println("\n💀 Ölenler:");
                for (Kisi kisi : kisiler) {
                    if (!kisi.olduMu()) kisi.yaslan();

                    if (kisi.olduMu() && !kisi.isBildirildiMi()) {
                        System.out.println("- " + kisi.getIsim());
                        kisi.setBildirildiMi(true);
                    }
                }

                System.out.println("\n🌍 Gezegenler:");
                for (Gezegen gezegen : gezegenler) {
                    System.out.println(gezegen);
                    gezegen.saatIlerle();
                }

                for (UzayAraci arac : uzayAraclari) {
                    for (Gezegen gezegen : gezegenler) {
                        if (gezegen.getAd().equals(arac.getCikisGezegen())) {
                            arac.kontrolEtVeBaslat(gezegen.getTarih());
                        }
                    }

                    if (arac.isImhaEdildi()) {
                        System.out.println(" | Varış Tarihi: --");
                    } else {
                        for (Gezegen g : gezegenler) {
                            if (g.getAd().equals(arac.getVarisGezegen())) {
                                String varisTarihi = arac.getVarisTarihi(g);
                                System.out.println(" | Varış Tarihi: " + varisTarihi);
                                break;
                            }
                        }
                    }

                    arac.saatIlerle();
                }

                boolean tamamlandi = uzayAraclari.stream().allMatch(UzayAraci::varisTamamlandi);
                if (tamamlandi) {
                    System.out.println("\n🎉 Tüm uzay araçları hedeflerine ulaştı. Simülasyon tamamlandı!");
                    break;
                }

                zaman.ileriSar();
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println("⚠️ HATA: " + e.getMessage());
            e.printStackTrace(); // 🔧 Hata detaylarını mutlaka gör
        }
    }

    public static void temizleEkran() {
        try {
            // 🔧 Ekranı temizleme kodu bazen konsolu sessizleştirebilir.
            // test esnasında yorum satırı yaparak hataları daha rahat görebilirsin.
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
