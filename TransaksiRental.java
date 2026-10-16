import java.time.LocalDate;

public class TransaksiRental {
    private String idTransaksi;
    private Pelanggan pelanggan;
    private Kendaraan kendaraan;
    private LocalDate tanggalMulai;
    private int lamaSewa;
    private long tarifSaatSewa;
    private String status; // "AKTIF" atau "SELESAI"

    public TransaksiRental(String idTransaksi, Pelanggan pelanggan, Kendaraan kendaraan, LocalDate tanggalMulai, int lamaSewa) {
        if (idTransaksi == null || idTransaksi.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Transaksi wajib diisi.");
        }
        if (lamaSewa <= 0) {
            throw new IllegalArgumentException("Lama sewa harus lebih dari 0 hari.");
        }
        if (!kendaraan.isTersedia()) {
            throw new IllegalArgumentException("Kendaraan sedang disewa!");
        }
        this.idTransaksi = idTransaksi.trim();
        this.pelanggan = pelanggan;
        this.kendaraan = kendaraan;
        this.tanggalMulai = tanggalMulai;
        this.lamaSewa = lamaSewa;
        this.tarifSaatSewa = kendaraan.getTarifPerHari();
        this.status = "AKTIF";
    }

    public void mulaiRental() {
        this.kendaraan.tandaiDisewa();
        this.status = "AKTIF";
    }

    public void ubahLamaSewa(int lamaSewa) {
        if (!"AKTIF".equals(this.status)) {
            throw new IllegalStateException("Hanya transaksi aktif yang dapat diedit.");
        }
        if (lamaSewa <= 0) {
            throw new IllegalArgumentException("Lama sewa harus lebih dari 0 hari.");
        }
        this.lamaSewa = lamaSewa;
    }

    public long hitungTotal() {
        return tarifSaatSewa * lamaSewa;
    }

    public LocalDate getTanggalRencanaKembali() {
        return tanggalMulai.plusDays(lamaSewa);
    }

    public void prosesPengembalian() {
        if (!"AKTIF".equals(this.status)) {
            throw new IllegalStateException("Transaksi sudah selesai.");
        }
        this.status = "SELESAI";
        this.kendaraan.tandaiTersedia();
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Kendaraan getKendaraan() { return kendaraan; }
    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public int getLamaSewa() { return lamaSewa; }
    public long getTarifSaatSewa() { return tarifSaatSewa; }
    public String getStatus() { return status; }

    public String cetakTransaksi() {
        return "ID Trx: " + idTransaksi + " | Penyewa: " + pelanggan.getNama() + 
               " | Kendaraan: " + kendaraan.getNoPlat() + " (" + kendaraan.getMerk() + ")" +
               " | Mulai: " + tanggalMulai + " | Rencana Kembali: " + getTanggalRencanaKembali() +
               " | Lama: " + lamaSewa + " hari | Tarif Saat Sewa: Rp" + tarifSaatSewa + 
               " | Total: Rp" + hitungTotal() + " | Status: " + status;
    }
}
