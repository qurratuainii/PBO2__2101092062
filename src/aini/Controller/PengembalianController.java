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
        formPengembalian.getTxtTglkembali().setText("");
        formPengembalian.getTxtTerlambat().setText("");
        formPengembalian.getTxtDenda().setText("");
    }
    
    public void isiCombo(){
        try {
            formPengembalian.getCboKodeanggota().removeAllItems();
            formPengembalian.getCboKodebuku().removeAllItems();
            formPengembalian.getCboTglpinjam().removeAllItems();
            formPengembalian.getCboTglkembali().removeAllItems();
            List<Anggota> anggotaList = anggotaDao.getAllAnggota(con);
            List<Buku> bukuList = bukuDao.getAllBuku(con);
            List<Peminjaman> peminjamanList = peminjamanDao.getAllPeminjaman(con);
            for (Anggota anggota : anggotaList) {
                formPengembalian.getCboKodeanggota()
                        .addItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
            }
            for (Buku buku : bukuList) { 
                formPengembalian.getCboKodebuku().addItem(buku.getKodebuku());
            }
            for (Peminjaman peminjaman : peminjamanList){
                formPengembalian.getCboTglpinjam().addItem(peminjaman.getTglpinjam());
                formPengembalian.getCboTglkembali().addItem(peminjaman.getTglkembali());
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(){
        try {
            pengembalian = new Pengembalian();
            pengembalian.setKodeanggota(formPengembalian.getCboKodeanggota()
                    .getSelectedItem().toString().split("-")[0]);
            pengembalian.setKodebuku(formPengembalian.getCboKodebuku().getSelectedItem().toString());
            pengembalian.setTglpinjam(formPengembalian.getCboTglkembali().getSelectedItem().toString());
            pengembalian.setTglkembali(formPengembalian.getCboTglkembali().getSelectedItem().toString());
            
            
            pengembalianDao.insert(con, pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPengembalian, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void update(){
        try {
            pengembalian.setKodeanggota(formPengembalian.getCboKodeanggota()
                    .getSelectedItem().toString().split("-")[0]);
            pengembalian.setKodebuku(formPengembalian.getCboKodebuku().getSelectedItem().toString());
            pengembalian.setTglpinjam(formPengembalian.getCboTglpinjam().getSelectedItem().toString());
            pengembalian.setTglkembali(formPengembalian.getCboTglkembali().getSelectedItem().toString());
            
            
            pengembalianDao.update(con, pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Update Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPengembalian, ex.getMessage());
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void delete(){
        try {
           pengembalianDao.delete(con, pengembalian);
            JOptionPane.showMessageDialog(formPengembalian, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPeminjaman(){
        try {
            String kodeanggota = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 0).toString();
            String kodebuku = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 1).toString();
            String tglpinjam = formPengembalian.getTblPengembalian()
                    .getValueAt(formPengembalian.getTblPengembalian().getSelectedRow(), 2).toString();
            pengembalian = pengembalianDao.getPengembalian(con, kodeanggota, kodebuku, tglpinjam);
            if(pengembalian!=null){
                Anggota anggota = anggotaDao.getAnggota(con, pengembalian.getKodeanggota());
                formPengembalian.getCboKodeanggota()
                        .setSelectedItem(anggota.getKodeanggota()+"-"+anggota.getNamaanggota());
                formPengembalian.getCboKodebuku().setSelectedItem(pengembalian.getKodebuku());
                formPengembalian.getCboTglpinjam().setSelectedItem(pengembalian.getTglpinjam());
                formPengembalian.getCboTglkembali().setSelectedItem(pengembalian.getTglkembali());
               
             }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPengembalian.getTblPengembalian().getModel();
            tabel.setRowCount(0);
            List<Pengembalian> list = pengembalianDao.getAllPengembalian(con);
            for (Pengembalian peminjaman1 : list) { 
                Object[] row = {
                    peminjaman1.getKodeanggota(),
                    peminjaman1.getKodebuku(),
                    peminjaman1.getTglpinjam(),
                    peminjaman1.getTglkembali(),
                    peminjaman1.getTerlambat(),
                    peminjaman1.getDenda()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
