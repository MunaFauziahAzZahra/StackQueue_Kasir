import java.util.Scanner;

// Program utama: Sistem Kasir Toko (Queue + Stack)
public class Main {
    static final int MAKS_ANTRIAN = 5;   // validasi jumlah data <= 5

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue antrian = new Queue(MAKS_ANTRIAN);
        Stack riwayat = new Stack();
        int pilih = 0;

        do {
            System.out.println();
            System.out.println("=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            String teks = input.nextLine().trim();
            try {
                pilih = Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                pilih = 0;
            }

            switch (pilih) {
                case 1:
                    // ---- Enqueue ----
                    if (antrian.isFull()) {
                        System.out.println("Antrian penuh! Maksimal " + MAKS_ANTRIAN
                                + " pelanggan, layani pelanggan terlebih dahulu.");
                        break;
                    }
                    String kode;
                    while (true) {
                        System.out.print("Masukkan Nomor Antrian: ");
                        kode = input.nextLine().trim();
                        if (kode.isEmpty()) {
                            System.out.println("Nomor antrian tidak boleh kosong!");
                        } else if (antrian.adaKode(kode)) {
                            System.out.println("Nomor antrian sudah ada di antrian!");
                        } else {
                            break;
                        }
                    }
                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = input.nextLine().trim();
                    double total;
                    while (true) {
                        System.out.print("Masukkan Total Belanja: ");
                        try {
                            total = Double.parseDouble(input.nextLine().trim());
                            if (total < 0) {
                                System.out.println(
                                        "Total belanja tidak boleh negatif!");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Total belanja harus berupa angka!");
                        }
                    }
                    antrian.enqueue(kode, nama, total);
                    System.out.println("Data pelanggan ditambahkan ke antrian!");
                    break;

                case 2:
                    // ---- Dequeue + Push ----
                    Node dilayani = antrian.dequeue();
                    if (dilayani == null) {
                        System.out.println("Tidak ada pelanggan dalam antrian.");
                    } else {
                        System.out.println("Melayani pelanggan " + dilayani.kode
                                + " (" + dilayani.nama + ")");
                        riwayat.push(dilayani.kode, dilayani.nama, dilayani.total);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    antrian.display();
                    break;

                case 4:
                    riwayat.display();
                    break;

                case 5:
                    System.out.println("Terima kasih, program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak valid, pilih 1-5!");
            }
        } while (pilih != 5);

        input.close();
    }
}
