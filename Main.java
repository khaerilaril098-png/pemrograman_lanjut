import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalManager manager = new RentalManager();
        Scanner scanner = new Scanner(System.in);

        // Data Awal
        try {
            manager.tambahKendaraan(new Motor("DD 1234 XY", "Honda Vario 160", 85000, 160, "Matic"));
            manager.tambahKendaraan(new Mobil("DD 8899 ZZ", "Toyota Avanza", 350000, 7, "Manual"));
            manager.tambahPelanggan(new Pelanggan("PLG-01", "Budi Santoso", "081234567890"));
        } catch (Exception ignored) {}

        int pilihan = 0;
        do {
            System.out.println("\n==================================================");
            System.out.println("       APLIKASI RENTAL MOTOR DAN MOBIL            ");
            System.out.println("                 RENTAL BORMMM                    ");
            System.out.println("         Jl. Sungai Saddang Baru Makassar         ");
            System.out.println("==================================================");
            System.out.println("1. Tambah Kendaraan (Motor/Mobil)");
            System.out.println("2. Edit Kendaraan (Merk, Tarif, dan Spesifikasi)");
            System.out.println("3. Tampilkan Seluruh Kendaraan (Polymorphism)");
            System.out.println("4. Tambah Pelanggan");
            System.out.println("5. Tampilkan Daftar Pelanggan");
            System.out.println("6. Buat Transaksi Penyewaan");
            System.out.println("7. Proses Pengembalian Kendaraan");
            System.out.println("8. Tampilkan Ringkasan Transaksi");
            System.out.println("9. Edit Pelanggan (Nama dan No. HP)");
            System.out.println("10. Hapus Kendaraan");
            System.out.println("11. Hapus Pelanggan");
            System.out.println("12. Edit Lama Sewa Transaksi Aktif");
            System.out.println("13. Hapus Transaksi Selesai");
            System.out.println("14. Keluar");
            System.out.print("Pilih menu (1-14): ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1:
                        System.out.print("Pilih Jenis (1. Motor, 2. Mobil): ");
                        int jenis = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nomor Plat: ");
                        String plat = scanner.nextLine();
                        System.out.print("Merk: ");
                        String merk = scanner.nextLine();
                        System.out.print("Tarif per Hari (Rp): ");
                        long tarif = Long.parseLong(scanner.nextLine());

                        if (jenis == 1) {
                            System.out.print("Kapasitas Mesin (CC): ");
                            int cc = Integer.parseInt(scanner.nextLine());
                            System.out.print("Transmisi: ");
                            String trans = scanner.nextLine();
                            manager.tambahKendaraan(new Motor(plat, merk, tarif, cc, trans));
                        } else {
                            System.out.print("Jumlah Kursi: ");
                            int kursi = Integer.parseInt(scanner.nextLine());
                            System.out.print("Transmisi: ");
                            String trans = scanner.nextLine();
                            manager.tambahKendaraan(new Mobil(plat, merk, tarif, kursi, trans));
                        }
                        System.out.println(">> Kendaraan berhasil ditambahkan!");
                        break;

                    case 2:
                        System.out.print("Masukkan Nomor Plat Kendaraan: ");
                        String pPlat = scanner.nextLine();
                        Kendaraan k = manager.cariKendaraan(pPlat);
                        System.out.println("Data saat ini: " + k.tampilkanInfo());
                        System.out.print("Merk Baru: ");
                        String merkBaru = scanner.nextLine();
                        System.out.print("Tarif Baru per Hari (Rp): ");
                        long tBaru = Long.parseLong(scanner.nextLine());
                        if (k instanceof Motor) {
                            System.out.print("Kapasitas Mesin Baru (CC): ");
                            int ccBaru = Integer.parseInt(scanner.nextLine());
                            System.out.print("Transmisi Baru: ");
                            String transmisiBaru = scanner.nextLine();
                            ((Motor) k).ubahData(merkBaru, tBaru, ccBaru, transmisiBaru);
                        } else if (k instanceof Mobil) {
                            System.out.print("Jumlah Kursi Baru: ");
                            int kursiBaru = Integer.parseInt(scanner.nextLine());
                            System.out.print("Transmisi Baru: ");
                            String transmisiBaru = scanner.nextLine();
                            ((Mobil) k).ubahData(merkBaru, tBaru, kursiBaru, transmisiBaru);
                        }
                        System.out.println(">> Data kendaraan berhasil diperbarui!");
                        break;

                    case 3:
                        System.out.println("\n--- DAFTAR KENDARAAN (Polymorphism) ---");
                        for (Kendaraan item : manager.getDaftarKendaraan()) {
                            System.out.println(item.tampilkanInfo());
                        }
                        break;

                    case 4:
                        System.out.print("ID Pelanggan: ");
                        String idP = scanner.nextLine();
                        System.out.print("Nama: ");
                        String namaP = scanner.nextLine();
                        System.out.print("No HP: ");
                        String hpP = scanner.nextLine();
                        manager.tambahPelanggan(new Pelanggan(idP, namaP, hpP));
                        System.out.println(">> Pelanggan berhasil ditambahkan!");
                        break;

                    case 5:
                        System.out.println("\n--- DAFTAR PELANGGAN ---");
                        for (Pelanggan plg : manager.getDaftarPelanggan()) {
                            System.out.println(plg.tampilkanData());
                        }
                        break;

                    case 6:
                        System.out.print("ID Transaksi: ");
                        String idTrx = scanner.nextLine();
                        
                        System.out.println("Pelanggan Tersedia:");
                        for (Pelanggan plg : manager.getDaftarPelanggan()) {
                            System.out.println("- " + plg.getIdPelanggan() + " (" + plg.getNama() + ")");
                        }
                        System.out.print("Masukkan ID Pelanggan: ");
                        String selPlg = scanner.nextLine();

                        System.out.println("Kendaraan Tersedia:");
                        for (Kendaraan item : manager.getDaftarKendaraan()) {
                            if (item.isTersedia()) {
                                System.out.println("- " + item.getNoPlat() + " | " + item.getMerk() + " | Rp" + item.getTarifPerHari() + "/hari");
                            }
                        }
                        System.out.print("Masukkan Nomor Plat Kendaraan: ");
                        String selPlat = scanner.nextLine();

                        System.out.print("Lama Sewa (Hari): ");
                        int lama = Integer.parseInt(scanner.nextLine());

                        manager.buatTransaksi(idTrx, selPlg, selPlat, lama);
                        System.out.println(">> Transaksi berhasil dibuat!");
                        break;

                    case 7:
                        System.out.print("Masukkan ID Transaksi yang ingin dikembalikan: ");
                        String trsKembali = scanner.nextLine();
                        manager.prosesPengembalian(trsKembali);
                        System.out.println(">> Pengembalian berhasil diproses. Kendaraan kembali tersedia.");
                        break;

                    case 8:
                        System.out.println("\n--- RINGKASAN TRANSAKSI ---");
                        for (TransaksiRental trs : manager.getDaftarTransaksi()) {
                            System.out.println(trs.cetakTransaksi());
                        }
                        break;

                    case 9:
                        System.out.print("Masukkan ID Pelanggan: ");
                        String idEditPelanggan = scanner.nextLine();
                        Pelanggan pelangganEdit = manager.cariPelanggan(idEditPelanggan);
                        System.out.println("Data saat ini: " + pelangganEdit.tampilkanData());
                        System.out.print("Nama Baru: ");
                        String namaBaru = scanner.nextLine();
                        System.out.print("No. HP Baru: ");
                        String hpBaru = scanner.nextLine();
                        pelangganEdit.ubahData(namaBaru, hpBaru);
                        System.out.println(">> Data pelanggan berhasil diperbarui!");
                        break;

                    case 10:
                        System.out.print("Nomor Plat Kendaraan yang akan dihapus: ");
                        String platHapus = scanner.nextLine();
                        manager.cariKendaraan(platHapus);
                        System.out.print("Yakin ingin menghapus kendaraan? (y/n): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                            manager.hapusKendaraan(platHapus);
                            System.out.println(">> Kendaraan berhasil dihapus!");
                        } else {
                            System.out.println(">> Penghapusan dibatalkan.");
                        }
                        break;

                    case 11:
                        System.out.print("ID Pelanggan yang akan dihapus: ");
                        String pelangganHapus = scanner.nextLine();
                        manager.cariPelanggan(pelangganHapus);
                        System.out.print("Yakin ingin menghapus pelanggan? (y/n): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                            manager.hapusPelanggan(pelangganHapus);
                            System.out.println(">> Pelanggan berhasil dihapus!");
                        } else {
                            System.out.println(">> Penghapusan dibatalkan.");
                        }
                        break;

                    case 12:
                        System.out.print("ID Transaksi yang akan diedit: ");
                        String idEditTransaksi = scanner.nextLine();
                        TransaksiRental transaksiEdit = manager.cariTransaksi(idEditTransaksi);
                        System.out.println("Data saat ini: " + transaksiEdit.cetakTransaksi());
                        System.out.print("Lama Sewa Baru (Total Hari): ");
                        int lamaBaru = Integer.parseInt(scanner.nextLine());
                        transaksiEdit.ubahLamaSewa(lamaBaru);
                        System.out.println(">> Lama sewa berhasil diperbarui. Total: Rp" + transaksiEdit.hitungTotal());
                        break;

                    case 13:
                        System.out.print("ID Transaksi yang akan dihapus: ");
                        String transaksiHapus = scanner.nextLine();
                        manager.cariTransaksi(transaksiHapus);
                        System.out.print("Yakin ingin menghapus transaksi? (y/n): ");
                        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                            manager.hapusTransaksi(transaksiHapus);
                            System.out.println(">> Transaksi berhasil dihapus!");
                        } else {
                            System.out.println(">> Penghapusan dibatalkan.");
                        }
                        break;

                    case 14:
                        System.out.println("Terima kasih telah menggunakan sistem Rental Bormmm!");
                        break;

                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("TERJADI KESALAHAN: " + e.getMessage());
            }
        } while (pilihan != 14);
        scanner.close();
    }
}
