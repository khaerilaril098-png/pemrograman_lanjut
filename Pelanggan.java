public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String noHP;

    public Pelanggan(String idPelanggan, String nama, String noHP) {
        if (idPelanggan == null || idPelanggan.trim().isEmpty() || nama == null || nama.trim().isEmpty() || noHP == null || noHP.trim().isEmpty()) {
            throw new IllegalArgumentException("ID, Nama, dan Nomor HP Pelanggan wajib diisi.");
        }
        this.idPelanggan = idPelanggan.trim();
        this.nama = nama.trim();
        this.noHP = noHP.trim();
    }

    public String getIdPelanggan() { return idPelanggan; }
    public String getNama() { return nama; }
    public String getNoHP() { return noHP; }

    public void ubahKontak(String noHPBaru) {
        if (noHPBaru == null || noHPBaru.trim().isEmpty()) {
            throw new IllegalArgumentException("Nomor HP baru tidak boleh kosong.");
        }
        this.noHP = noHPBaru.trim();
    }

    public String tampilkanData() {
        return "ID: " + idPelanggan + " | Nama: " + nama + " | No. HP: " + noHP;
    }
}
