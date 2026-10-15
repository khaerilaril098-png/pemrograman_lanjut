public class Motor extends Kendaraan {
    private int kapasitasMesin;
    private String jenisTransmisi;

    public Motor(String noPlat, String merk, long tarifPerHari, int kapasitasMesin, String jenisTransmisi) {
        super(noPlat, merk, tarifPerHari);
        if (kapasitasMesin <= 0) {
            throw new IllegalArgumentException("Kapasitas mesin harus lebih dari nol.");
        }
        this.kapasitasMesin = kapasitasMesin;
        this.jenisTransmisi = jenisTransmisi;
    }

    @Override
    public String getJenis() { return "Motor"; }

    @Override
    public String getDetailKhusus() {
        return kapasitasMesin + " cc | Transmisi: " + jenisTransmisi;
    }

    @Override
    public String tampilkanInfo() {
        return "[MOTOR] Plat: " + getNoPlat() + " | Merk: " + getMerk() + 
               " | Spesifikasi: " + getDetailKhusus() + " | Tarif: Rp" + getTarifPerHari() + 
               "/hari | Status: " + (isTersedia() ? "Tersedia" : "Sedang Disewa");
    }
}
