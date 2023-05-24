/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aini.Controller;

import aini.Dao.PeminjamanDao;
import aini.Dao.PeminjamanDaoImpl;
import aini.Dao.Koneksi;
import aini.Model.Peminjaman;
import aini.View.FormPeminjaman;
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
public class PeminjamanController {
     private FormPeminjaman formPeminjaman;
    private Peminjaman peminjaman;
    private PeminjamanDao peminjamanDao;
    private Connection con;
    private Koneksi koneksi;
    
    public PeminjamanController(FormPeminjaman formPeminjaman){
        try {
            this.formPeminjaman = formPeminjaman;
            peminjamanDao = new PeminjamanDaoImpl();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formPeminjaman.getTxtKodeAnggota().setText("");
        formPeminjaman.getTxtKodeBuku().setText("");   
        formPeminjaman.getTxtTglPinjam().setText("");
        formPeminjaman.getTxtTglKembali().setText("");
    }
    
    public void insert(){
        try {
            peminjaman = new Peminjaman();
            peminjaman.setKodeanggota(formPeminjaman.getTxtKodeAnggota().getText());
            peminjaman.setKodebuku(formPeminjaman.getTxtKodeBuku().getText());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglKembali().getText());
            peminjamanDao.insert(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            peminjaman.setKodeanggota(formPeminjaman.getTxtKodeAnggota().getText());
            peminjaman.setKodebuku(formPeminjaman.getTxtKodeBuku().getText());
            peminjaman.setTglpinjam(formPeminjaman.getTxtTglPinjam().getText());
            peminjaman.setTglkembali(formPeminjaman.getTxtTglKembali().getText());
            peminjamanDao.update(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Update Ok");
        } catch (Exception ex) {
           Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            peminjamanDao.delete(con, peminjaman);
            JOptionPane.showMessageDialog(formPeminjaman, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formPeminjaman.getTxtKodeAnggota().getText();
            String kode1 = formPeminjaman.getTxtKodeBuku().getText();
            String kode2 = formPeminjaman.getTxtTglPinjam().getText();
            peminjaman = peminjamanDao.getPeminjaman(con, kode);
            peminjaman = peminjamanDao.getPeminjaman(con, kode1);
            peminjaman = peminjamanDao.getPeminjaman(con, kode2);
            if(peminjaman != null){
                formPeminjaman.getTxtTglKembali().setText(peminjaman.getTglkembali());
            }else {
                JOptionPane.showMessageDialog(formPeminjaman, "Data tidak ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPeminjaman.getTblPeminjaman().getModel();
            tabel.setRowCount(0);
            List<Peminjaman> list = peminjamanDao.getAllPeminjaman(con);
            for (Peminjaman peminjaman : list) {
                Object[] row = {
                    peminjaman.getKodeanggota(),
                    peminjaman.getKodebuku(),
                    peminjaman.getTglpinjam(),
                    peminjaman.getTglkembali()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PeminjamanController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
