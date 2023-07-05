/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aini.Controller;

import aini.Dao.AnggotaDao;
import aini.Dao.AnggotaDaoImpl;
import aini.Dao.BukuDao;
import aini.Dao.BukuDaoImpl;
import aini.Dao.Koneksi;
import aini.Dao.PeminjamanDao;
import aini.Dao.PengembalianDao;
import aini.Dao.PeminjamanDaoImpl;
import aini.Dao.PengembalianDaoImpl;
import aini.Model.Anggota;
import aini.Model.Buku;
import aini.Model.Pengembalian;
import aini.Model.Peminjaman;
import aini.View.FormPengembalian;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PengembalianController {
    FormPengembalian formPengembalian;
    Pengembalian pengembalian;
    PengembalianDao pengembalianDao;
    PeminjamanDao peminjamanDao;
    AnggotaDao anggotaDao;
    BukuDao bukuDao;
    Connection con;

    public PengembalianController(FormPengembalian formPengembalian) {
        try {
            this.formPengembalian = formPengembalian;
            pengembalianDao = new PengembalianDaoImpl();
            anggotaDao = new AnggotaDaoImpl();
            bukuDao = new BukuDaoImpl();
            peminjamanDao = new PeminjamanDaoImpl();
            Koneksi k = new Koneksi();
            con = k.getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearForm(){
        formPengembalian.getTxtKodeanggota().setText("");
        formPengembalian.getTxtKodebuku().setText("");
        formPengembalian.getTxtTglpinjam().setText("");
        formPengembalian.getTxtTglkembali().setText("");
        formPengembalian.getTxtTgldikembalikan().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
    }
    
    public void getPengembalian(){
        try {
           String kodeAnggota = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian()
                            .getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian()
                            .getSelectedRow(), 2).toString();
            String tglpinjam = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian()
                            .getSelectedRow(), 3).toString();
            pengembalian = new Pengembalian();
            Peminjaman peminjaman = peminjamanDao
                    .getPeminjaman(con, kodeAnggota, kodebuku, tglpinjam);
            int terlambat = pengembalianDao
                    .selisihTanggal(con, pengembalian.getTgldikembalikan(),
                            peminjaman.getTglkembali());
            pengembalian.setTerlambat(terlambat);
            double denda = pengembalian.getDenda();
            formPengembalian.getTxtKodeanggota().setText(kodeAnggota);
            formPengembalian.getTxtKodebuku().setText(kodebuku);
            formPengembalian.getTxtTglpinjam().setText(tglpinjam);
            formPengembalian.getTxtTglkembali().setText(peminjaman.getTglkembali()); 
            formPengembalian.getTxtTgldikembalikan().setText(pengembalian.getTgldikembalikan());
            formPengembalian.getTxtTerlambat().setText(terlambat+""); 
            formPengembalian.getTxtDenda().setText(denda+"");
       } catch (Exception ex) {
            Logger.getLogger(PengembalianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPengembalian.getTblPengembalian().getModel();
            tabel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(con);
            for (Pengembalian p : list) { 
                Anggota anggota = anggotaDao.getAnggota(con, p.getKodeanggota());
                Peminjaman pinjam = peminjamanDao.getPeminjaman(con, p.getKodeanggota(), p.getKodebuku(), p.getTglpinjam());
                Object[] row = {
                    p.getKodeanggota(),
                    anggota.getNamaanggota(),
                    p.getKodebuku(),
                    pinjam.getTglpinjam(),
                    pinjam.getTglkembali(),
                    p.getTgldikembalikan(),
                    p.getTerlambat(),
                    p.getDenda()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
