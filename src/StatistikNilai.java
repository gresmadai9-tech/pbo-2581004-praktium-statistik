import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;


public class StatistikNilai {

    // Sentinel static final sesuai ketentuan
    public static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Mengatur Locale agar format desimal menggunakan koma (contoh: 75,50)
        scanner.useLocale(new Locale("id", "ID"));

        // Nilai disimpan di ArrayList<Integer>, bukan dijumlahkan langsung
        ArrayList<Integer> daftar = new ArrayList<>();

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik -1 kalau sudah selesai.");

        // Pembacaan memakai do-while dengan sentinel SELESAI
        do {
            // Nomor pada prompt diambil dari size() + 1 (tanpa variabel pencacah terpisah)
            int nomorPrompt = daftar.size() + 1;
            System.out.print("Nilai ke-" + nomorPrompt + " : ");

            if (!scanner.hasNextInt()) {
                System.out.println("  Masukan harus berupa angka!");
                scanner.next(); // Membersihkan input yang tidak valid
                continue;
            }

            int input = scanner.nextInt();

            if (input == SELESAI) {
                break;
            }

            // Nilai di luar 0-100 ditolak dengan continue dan tidak di-add
            if (input < 0 || input > 100) {
                System.out.println("  Ditolak, harus 0-100");
                continue;
            }

            daftar.add(input);

        } while (true);

        scanner.close();

        // Jaga kasus tidak ada nilai sah sama sekali (langsung diketik -1)
        if (daftar.isEmpty()) {
            System.out.println("\nTidak ada nilai sah yang dimasukkan.");
            return;
        }

        // Menghitung total dan rata-rata menggunakan Enhanced for-each loop
        int total = 0;
        for (int nilai : daftar) {
            total += nilai;
        }
        double rataRata = (double) total / daftar.size();

        /*
         * TERTINGGI DAN TERENDAH:
         * Nilai awal dimulai dari elemen pertama daftar (daftar.get(0)).
         */
        int tertinggi = daftar.get(0);
        int terendah = daftar.get(0);

        for (int i = 1; i < daftar.size(); i++) {
            int nilai = daftar.get(i);
            if (nilai > tertinggi) {
                tertinggi = nilai;
            }
            if (nilai < terendah) {
                terendah = nilai;
            }
        }

        /*
         * JUMLAH DI ATAS RATA-RATA:
         * Dihitung dengan putaran kedua, sesudah rata-ratanya diketahui.
         */
        int jumlahDiAtasRata = 0;
        for (int nilai : daftar) {
            if (nilai > rataRata) {
                jumlahDiAtasRata++;
            }
        }

        /*
         * DISTRIBUSI GRADE:
         * Disimpan di array int[] jumlahGrade = new int[5];
         * Index: 0 -> A, 1 -> B, 2 -> C, 3 -> D, 4 -> E
         */
        int[] jumlahGrade = new int[5];
        for (int nilai : daftar) {
            if (nilai >= 85) {
                jumlahGrade[0]++; // A
            } else if (nilai >= 75) {
                jumlahGrade[1]++; // B
            } else if (nilai >= 65) {
                jumlahGrade[2]++; // C
            } else if (nilai >= 50) {
                jumlahGrade[3]++; // D
            } else {
                jumlahGrade[4]++; // E
            }
        }

        // Daftar terurut dibuat dari salinan agar daftar asli tidak ikut terurut
        ArrayList<Integer> terurut = new ArrayList<>(daftar);
        Collections.sort(terurut);

        // Cetak Hasil
        System.out.println("\nNilai tersimpan : " + daftar);
        System.out.println("Jumlah          : " + daftar.size());
        System.out.printf(new Locale("id", "ID"), "Rata-rata       : %.2f%n", rataRata);
        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);
        System.out.println("Di atas rata2   : " + jumlahDiAtasRata + " orang");

        // Distribusi grade dicetak dengan loop
        char[] labelGrade = {'A', 'B', 'C', 'D', 'E'};
        System.out.print("Distribusi      : ");
        for (int i = 0; i < jumlahGrade.length; i++) {
            System.out.print(labelGrade[i] + "=" + jumlahGrade[i]);
            if (i < jumlahGrade.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.println("Terurut         : " + terurut);
        System.out.println("Urutan asli     : " + daftar);
    }
}