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

    public void hapusKendaraan(String noPlat) {
        Kendaraan kendaraan = cariKendaraan(noPlat);
        if (!kendaraan.isTersedia()) {
            throw new IllegalStateException("Kendaraan sedang disewa dan tidak dapat dihapus.");
        }
        for (TransaksiRental transaksi : daftarTransaksi) {
            if (transaksi.getKendaraan() == kendaraan) {
                throw new IllegalStateException("Kendaraan masih tercatat dalam transaksi. Hapus transaksi selesai terkait terlebih dahulu.");
            }
        }
        daftarKendaraan.remove(kendaraan);
    }

    public void hapusPelanggan(String idPelanggan) {
        Pelanggan pelanggan = cariPelanggan(idPelanggan);
        for (TransaksiRental transaksi : daftarTransaksi) {
            if (transaksi.getPelanggan() == pelanggan) {
                throw new IllegalStateException("Pelanggan masih tercatat dalam transaksi. Selesaikan dan hapus transaksi terkait terlebih dahulu.");
            }
        }
        daftarPelanggan.remove(pelanggan);
    }

    public TransaksiRental cariTransaksi(String idTransaksi) {
        for (TransaksiRental transaksi : daftarTransaksi) {
            if (transaksi.getIdTransaksi().equalsIgnoreCase(idTransaksi)) {
                return transaksi;
            }
        }
        throw new IllegalArgumentException("Transaksi tidak ditemukan.");
    }

    public void hapusTransaksi(String idTransaksi) {
        TransaksiRental transaksi = cariTransaksi(idTransaksi);
        if (!"SELESAI".equals(transaksi.getStatus())) {
            throw new IllegalStateException("Transaksi aktif tidak dapat dihapus. Proses pengembalian terlebih dahulu.");
        }
        daftarTransaksi.remove(transaksi);
    }

    public List<Kendaraan> getDaftarKendaraan() { return daftarKendaraan; }
    public List<Pelanggan> getDaftarPelanggan() { return daftarPelanggan; }
    public List<TransaksiRental> getDaftarTransaksi() { return daftarTransaksi; }
}
