import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// 1. ABSTRACT CLASS: Kendaraan
// ==========================================
abstract class Kendaraan {
    private String noPlat;
    private String merk;
    private long tarifPerHari;
    private boolean tersedia;

    public Kendaraan(String noPlat, String merk, long tarifPerHari) {
        if (noPlat == null || noPlat.trim().isEmpty() || merk == null || merk.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor plat dan merk tidak boleh kosong.");
        }
        if (tarifPerHari <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari nol.");
        }
        this.noPlat = noPlat.trim();
        this.merk = merk.trim();
        this.tarifPerHari = tarifPerHari;
        this.tersedia = true;
    }

    public String getNoPlat() { return noPlat; }
    public String getMerk() { return merk; }
    public long getTarifPerHari() { return tarifPerHari; }
    public boolean isTersedia() { return tersedia; }

    public void setTarifPerHari(long tarif) {
        if (tarif <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari nol.");
        }
        this.tarifPerHari = tarif;
    }

    public void tandaiDisewa() { this.tersedia = false; }
    public void tandaiTersedia() { this.tersedia = true; }

    public abstract String tampilkanInfo();
}

// ==========================================
// 2. SUBCLASS: Motor
// ==========================================
class Motor extends Kendaraan {
    private int kapasitasMesin;
    private String jenisTransmisi;

    public Motor(String noPlat, String merk, long tarifPerHari, int kapasitasMesin, String jenisTransmisi) {
        super(noPlat, merk, tarifPerHari);
        this.kapasitasMesin = kapasitasMesin;
        this.jenisTransmisi = jenisTransmisi;
    }

    @Override
    public String tampilkanInfo() {
        return "[MOTOR] No. Plat: " + getNoPlat() + " | Merk: " + getMerk() + 
               " | Mesin: " + kapasitasMesin + " cc | Transmisi: " + jenisTransmisi + 
               " | Tarif: Rp" + getTarifPerHari() + "/hari | Status: " + (isTersedia() ? "Tersedia" : "Sedang Disewa");
    }
}

// ==========================================
// 3. SUBCLASS: Mobil
// ==========================================
class Mobil extends Kendaraan {
    private int jumlahKursi;
    private String jenisTransmisi;

    public Mobil(String noPlat, String merk, long tarifPerHari, int jumlahKursi, String jenisTransmisi) {
        super(noPlat, merk, tarifPerHari);
        this.jumlahKursi = jumlahKursi;
        this.jenisTransmisi = jenisTransmisi;
    }

    @Override
    public String tampilkanInfo() {
        return "[MOBIL] No. Plat: " + getNoPlat() + " | Merk: " + getMerk() + 
               " | Kursi: " + jumlahKursi + " Kursi | Transmisi: " + jenisTransmisi + 
               " | Tarif: Rp" + getTarifPerHari() + "/hari | Status: " + (isTersedia() ? "Tersedia" : "Sedang Disewa");
    }
}

// ==========================================
// 4. CLASS: Pelanggan
// ==========================================
class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String noHP;

    public Pelanggan(String idPelanggan, String nama, String noHP) {
        this.idPelanggan = idPelanggan.trim();
        this.nama = nama.trim();
        this.noHP = noHP.trim();
    }

    public String getIdPelanggan() { return idPelanggan; }
    public String getNama() { return nama; }
    public String getNoHP() { return noHP; }

    public void ubahKontak(String noHPBaru) {
        this.noHP = noHPBaru.trim();
    }

    public String tampilkanData() {
        return "ID: " + idPelanggan + " | Nama: " + nama + " | No. HP: " + noHP;
    }
}

// ==========================================
// 5. CLASS: TransaksiRental
// ==========================================
class TransaksiRental {
    private String idTransaksi;
    private Pelanggan pelanggan;
    private Kendaraan kendaraan;
    private LocalDate tanggalMulai;
    private int lamaSewa;
    private long tarifSaatSewa;
    private String status; // "AKTIF" atau "SELESAI"

    public TransaksiRental(String idTransaksi, Pelanggan pelanggan, Kendaraan kendaraan, LocalDate tanggalMulai, int lamaSewa) {
        this.idTransaksi = idTransaksi;
        this.pelanggan = pelanggan;
        this.kendaraan = kendaraan;
        this.tanggalMulai = tanggalMulai;
        this.lamaSewa = lamaSewa;
        this.tarifSaatSewa = kendaraan.getTarifPerHari();
        this.status = "AKTIF";
    }

    public void mulaiRental() {
        this.kendaraan.tandaiDisewa();
    }

    public long hitungTotal() {
        return tarifSaatSewa * lamaSewa;
    }

    public LocalDate getTanggalRencanaKembali() {
        return tanggalMulai.plusDays(lamaSewa);
    }

    public void prosesPengembalian() {
        this.status = "SELESAI";
        this.kendaraan.tandaiTersedia();
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Kendaraan getKendaraan() { return kendaraan; }
    public String getStatus() { return status; }

    public String cetakTransaksi() {
        return "ID Trx: " + idTransaksi + " | Penyewa: " + pelanggan.getNama() + 
               " | Kendaraan: " + kendaraan.getNoPlat() + " (" + kendaraan.getMerk() + ")" +
               " | Mulai: " + tanggalMulai + " | Rencana Kembali: " + getTanggalRencanaKembali() +
               " | Lama: " + lamaSewa + " hari | Tarif Saat Sewa: Rp" + tarifSaatSewa + 
               " | Total: Rp" + hitungTotal() + " | Status: " + status;
    }
}

// ==========================================
// 6. MAIN PROGRAM (CONSOLE INTERFACE)
// ==========================================
public class RentalConsoleApp {
    private static final List<Kendaraan> daftarKendaraan = new ArrayList<>();
    private static final List<Pelanggan> daftarPelanggan = new ArrayList<>();
    private static final List<TransaksiRental> daftarTransaksi = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Data Awal Sesuai Studi Kasus
        daftarKendaraan.add(new Motor("DD 1234 XY", "Honda Vario 160", 85000, 160, "Matic"));
        daftarKendaraan.add(new Mobil("DD 8899 ZZ", "Toyota Avanza", 350000, 7, "Manual"));
        daftarPelanggan.add(new Pelanggan("PLG-01", "Budi Santoso", "081234567890"));

        int pilihan = 0;
        do {
            System.out.println("\n==================================================");
            System.out.println("       APLIKASI RENTAL MOTOR DAN MOBIL            ");
            System.out.println("                 RENTAL BORMMM                    ");
            System.out.println("         Jl. Sungai Saddang Baru Makassar         ");
            System.out.println("==================================================");
            System.out.println("1. Tambah Kendaraan (Motor/Mobil)");
            System.out.println("2. Ubah Tarif Harian Kendaraan");
            System.out.println("3. Tampilkan Seluruh Kendaraan (Polymorphism)");
            System.out.println("4. Tambah Pelanggan");
            System.out.println("5. Tampilkan Daftar Pelanggan");
            System.out.println("6. Buat Transaksi Penyewaan");
            System.out.println("7. Proses Pengembalian Kendaraan");
            System.out.println("8. Tampilkan Ringkasan Transaksi");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu (1-9): ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1: tambahKendaraan(); break;
                    case 2: ubahTarifKendaraan(); break;
                    case 3: tampilkanKendaraan(); break;
                    case 4: tambahPelanggan(); break;
                    case 5: tampilkanPelanggan(); break;
                    case 6: buatTransaksi(); break;
                    case 7: prosesPengembalian(); break;
                    case 8: tampilkanTransaksi(); break;
                    case 9: System.out.println("Terima kasih telah menggunakan sistem Rental Bormmm!"); break;
                    default: System.out.println("Pilihan tidak valid!");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan input: " + e.getMessage());
            }
        } while (pilihan != 9);
    }

    private static void tambahKendaraan() {
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
            System.out.print("Jenis Transmisi (Matic/Manual): ");
            String transmisi = scanner.nextLine();
            daftarKendaraan.add(new Motor(plat, merk, tarif, cc, transmisi));
        } else {
            System.out.print("Jumlah Kursi: ");
            int kursi = Integer.parseInt(scanner.nextLine());
            System.out.print("Jenis Transmisi (Matic/Manual): ");
            String transmisi = scanner.nextLine();
            daftarKendaraan.add(new Mobil(plat, merk, tarif, kursi, transmisi));
        }
        System.out.println(">> Kendaraan berhasil ditambahkan!");
    }

    private static void ubahTarifKendaraan() {
        System.out.print("Masukkan Nomor Plat Kendaraan: ");
        String plat = scanner.nextLine();
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNoPlat().equalsIgnoreCase(plat)) {
                System.out.print("Tarif Baru per Hari (Rp): ");
                long tarifBaru = Long.parseLong(scanner.nextLine());
                k.setTarifPerHari(tarifBaru);
                System.out.println(">> Tarif berhasil diubah! (Catatan: Transaksi lama tetap menggunakan tarif saat awal sewa).");
                return;
            }
        }
        System.out.println(">> Kendaraan tidak ditemukan.");
    }

    private static void tampilkanKendaraan() {
        System.out.println("\n--- DAFTAR KENDARAAN (Menerapkan Polymorphism) ---");
        for (Kendaraan k : daftarKendaraan) {
            System.out.println(k.tampilkanInfo()); // Method polymorphism dijalankan di sini
        }
    }

    private static void tambahPelanggan() {
        System.out.print("ID Pelanggan: ");
        String id = scanner.nextLine();
        System.out.print("Nama Lengkap: ");
        String nama = scanner.nextLine();
        System.out.print("Nomor HP: ");
        String noHP = scanner.nextLine();
        daftarPelanggan.add(new Pelanggan(id, nama, noHP));
        System.out.println(">> Pelanggan berhasil ditambahkan!");
    }

    private static void tampilkanPelanggan() {
        System.out.println("\n--- DAFTAR PELANGGAN ---");
        for (Pelanggan p : daftarPelanggan) {
            System.out.println(p.tampilkanData());
        }
    }

    private static void buatTransaksi() {
        System.out.print("ID Transaksi: ");
        String idTrx = scanner.nextLine();
        
        System.out.println("Pilih Pelanggan:");
        for (Pelanggan p : daftarPelanggan) {
            System.out.println("- " + p.getIdPelanggan() + " (" + p.getNama() + ")");
        }
        System.out.print("Masukkan ID Pelanggan: ");
        String idP = scanner.nextLine();
        Pelanggan pelanggan = null;
        for (Pelanggan p : daftarPelanggan) {
            if (p.getIdPelanggan().equalsIgnoreCase(idP)) pelanggan = p;
        }

        System.out.println("Kendaraan Tersedia:");
        for (Kendaraan k : daftarKendaraan) {
            if (k.isTersedia()) {
                System.out.println("- " + k.getNoPlat() + " | " + k.getMerk() + " | Rp" + k.getTarifPerHari() + "/hari");
            }
        }
        System.out.print("Masukkan Nomor Plat Kendaraan: ");
        String plat = scanner.nextLine();
        Kendaraan kendaraan = null;
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNoPlat().equalsIgnoreCase(plat) && k.isTersedia()) kendaraan = k;
        }

        if (pelanggan == null || kendaraan == null) {
            System.out.println(">> Pelanggan atau Kendaraan tidak valid / sedang disewa!");
            return;
        }

        System.out.print("Lama Sewa (Hari): ");
        int lama = Integer.parseInt(scanner.nextLine());

        TransaksiRental trx = new TransaksiRental(idTrx, pelanggan, kendaraan, LocalDate.now(), lama);
        trx.mulaiRental();
        daftarTransaksi.add(trx);
        System.out.println(">> Transaksi berhasil dibuat! Total Biaya: Rp" + trx.hitungTotal());
    }

    private static void prosesPengembalian() {
        System.out.print("Masukkan ID Transaksi yang ingin dikembalikan: ");
        String idTrx = scanner.nextLine();
        for (TransaksiRental t : daftarTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTrx) && "AKTIF".equals(t.getStatus())) {
                t.prosesPengembalian();
                System.out.println(">> Kendaraan berhasil dikembalikan. Status transaksi menjadi SELESAI.");
                return;
            }
        }
        System.out.println(">> Transaksi aktif tidak ditemukan.");
    }

    private static void tampilkanTransaksi() {
        System.out.println("\n--- RINGKASAN TRANSAKSI ---");
        for (TransaksiRental t : daftarTransaksi) {
            System.out.println(t.cetakTransaksi());
        }
    }
}