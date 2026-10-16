public abstract class Kendaraan {
    private String noPlat;
    private String merk;
    private long tarifPerHari;
    private boolean tersedia;

    public Kendaraan(String noPlat, String merk, long tarifPerHari) {
        if (noPlat == null || noPlat.trim().isEmpty() || merk == null || merk.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor plat dan merk wajib diisi.");
        }
        if (tarifPerHari <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari nol.");
        }
        this.noPlat = noPlat.trim().toUpperCase();
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

    public void ubahData(String merk, long tarifPerHari) {
        if (merk == null || merk.trim().isEmpty()) {
            throw new IllegalArgumentException("Merk wajib diisi.");
        }
        if (tarifPerHari <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari nol.");
        }
        this.merk = merk.trim();
        this.tarifPerHari = tarifPerHari;
    }

    public void tandaiDisewa() { this.tersedia = false; }
    public void tandaiTersedia() { this.tersedia = true; }

    public abstract String getJenis();
    public abstract String getDetailKhusus();
    public abstract String tampilkanInfo();
}
