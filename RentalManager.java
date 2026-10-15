import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalManager {
    private List<Kendaraan> daftarKendaraan = new ArrayList<>();
    private List<Pelanggan> daftarPelanggan = new ArrayList<>();
    private List<TransaksiRental> daftarTransaksi = new ArrayList<>();

    public void tambahKendaraan(Kendaraan k) {
        for (Kendaraan existing : daftarKendaraan) {
            if (existing.getNoPlat().equalsIgnoreCase(k.getNoPlat())) {
                throw new IllegalArgumentException("Nomor plat kendaraan sudah terdaftar!");
            }
        }
        daftarKendaraan.add(k);
    }

    public void tambahPelanggan(Pelanggan p) {
        for (Pelanggan existing : daftarPelanggan) {
            if (existing.getIdPelanggan().equalsIgnoreCase(p.getIdPelanggan())) {
                throw new IllegalArgumentException("ID Pelanggan sudah terdaftar!");
            }
        }
        daftarPelanggan.add(p);
    }

    public void buatTransaksi(String idTrx, String idP, String noPlat, int lamaSewa) {
        for (TransaksiRental t : daftarTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTrx)) {
                throw new IllegalArgumentException("ID Transaksi sudah digunakan!");
            }
        }

        Pelanggan p = cariPelanggan(idP);
        Kendaraan k = cariKendaraan(noPlat);

        TransaksiRental trx = new TransaksiRental(idTrx, p, k, LocalDate.now(), lamaSewa);
        trx.mulaiRental();
        daftarTransaksi.add(trx);
    }

    public void prosesPengembalian(String idTrx) {
        for (TransaksiRental t : daftarTransaksi) {
            if (t.getIdTransaksi().equalsIgnoreCase(idTrx)) {
                t.prosesPengembalian();
                return;
            }
        }
        throw new IllegalArgumentException("Transaksi tidak ditemukan.");
    }

    public Kendaraan cariKendaraan(String noPlat) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNoPlat().equalsIgnoreCase(noPlat)) return k;
        }
        throw new IllegalArgumentException("Kendaraan tidak ditemukan.");
    }

    public Pelanggan cariPelanggan(String id) {
        for (Pelanggan p : daftarPelanggan) {
            if (p.getIdPelanggan().equalsIgnoreCase(id)) return p;
        }
        throw new IllegalArgumentException("Pelanggan tidak ditemukan.");
    }

    public List<Kendaraan> getDaftarKendaraan() { return daftarKendaraan; }
    public List<Pelanggan> getDaftarPelanggan() { return daftarPelanggan; }
    public List<TransaksiRental> getDaftarTransaksi() { return daftarTransaksi; }
}
