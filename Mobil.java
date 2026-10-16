public class Mobil extends Kendaraan {
    private int jumlahKursi;
    private String jenisTransmisi;

    public Mobil(String noPlat, String merk, long tarifPerHari, int jumlahKursi, String jenisTransmisi) {
        super(noPlat, merk, tarifPerHari);
        if (jumlahKursi <= 0) {
            throw new IllegalArgumentException("Jumlah kursi harus lebih dari nol.");
        }
        this.jumlahKursi = jumlahKursi;
        this.jenisTransmisi = jenisTransmisi;
    }

    public void ubahData(String merk, long tarifPerHari, int jumlahKursi, String jenisTransmisi) {
        if (jumlahKursi <= 0) {
            throw new IllegalArgumentException("Jumlah kursi harus lebih dari nol.");
        }
        if (jenisTransmisi == null || jenisTransmisi.trim().isEmpty()) {
            throw new IllegalArgumentException("Transmisi wajib diisi.");
        }
        super.ubahData(merk, tarifPerHari);
        this.jumlahKursi = jumlahKursi;
        this.jenisTransmisi = jenisTransmisi.trim();
    }

    @Override
    public String getJenis() { return "Mobil"; }

    @Override
    public String getDetailKhusus() {
        return jumlahKursi + " Kursi | Transmisi: " + jenisTransmisi;
    }

    @Override
    public String tampilkanInfo() {
        return "[MOBIL] Plat: " + getNoPlat() + " | Merk: " + getMerk() +  " | Spesifikasi: " + getDetailKhusus() + " | Tarif: Rp" + getTarifPerHari() + "/hari | Status: " + (isTersedia() ? "Tersedia" : "Sedang Disewa");
    }
}
