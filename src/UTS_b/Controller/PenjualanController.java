/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTS_b.Controller;

import UTS_b.Controller.PenjualanController;
import UTS_b.Dao.PenjualanDao;
import UTS_b.Dao.PenjualanDaoImpl;
import UTS_b.Dao.Koneksi;
import UTS_b.Model.Penjualan;
import UTS_b.View.FormPenjualan;
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
public class PenjualanController {
    private FormPenjualan formPenjualan;
    private Penjualan penjualan;
    private PenjualanDao penjualanDao;
    private Connection con;
    private Koneksi koneksi;
    
    public PenjualanController(FormPenjualan formPenjualan){
        try {
            this.formPenjualan = formPenjualan;
            penjualanDao = new PenjualanDaoImpl();
            con = new Koneksi().getKoneksi();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bersihForm(){
        formPenjualan.getTxtKodePesanan().setText("");
        formPenjualan.getTxtTanggal().setText("");   
        formPenjualan.getTxtNamaPemesan().setText("");
        formPenjualan.getTxtTotalBayar().setText("");
        formPenjualan.getTxtOngkirKirim().setText("");
        formPenjualan.getTxtDiskon().setText("");
    }
    
    public void insert(){
        try {
            penjualan = new Penjualan();
            penjualan.setKodepesanan(formPenjualan.getTxtKodePesanan().getText());
            penjualan.setTanggal(formPenjualan.getTxtTanggal().getText());
            penjualan.setNamapemesan(formPenjualan.getTxtNamaPemesan().getText());
            penjualan.setTotal(formPenjualan.getTxtTotalBayar().getColumns());
            penjualan.setOngkir(formPenjualan.getTxtOngkirKirim().getColumns());
            penjualan.setDiskon(formPenjualan.getTxtDiskon().getColumns());
            penjualanDao.insert(con, penjualan);
            JOptionPane.showMessageDialog(formPenjualan, "Entri OK");
        } catch (Exception ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        try {
            penjualan.setKodepesanan(formPenjualan.getTxtKodePesanan().getText());
            penjualan.setTanggal(formPenjualan.getTxtTanggal().getText());
            penjualan.setNamapemesan(formPenjualan.getTxtNamaPemesan().getText());
           penjualan.setTotal(formPenjualan.getTxtTotalBayar().getColumns());
           penjualan.setOngkir(formPenjualan.getTxtOngkirKirim().getColumns());
           penjualan.setDiskon(formPenjualan.getTxtDiskon().getColumns());
            penjualanDao.update(con, penjualan);
            JOptionPane.showMessageDialog(formPenjualan, "Update Ok");
        } catch (Exception ex) {
           Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            penjualanDao.delete(con, penjualan);
            JOptionPane.showMessageDialog(formPenjualan, "Delete Ok");
        } catch (Exception ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cari(){
        try {
            String kode = formPenjualan.getTxtKodePesanan().getText();
            penjualan = penjualanDao.getPenjualan(con, kode);
            if(penjualan != null){
                formPenjualan.getTxtTanggal().setText(penjualan.getTanggal());
                formPenjualan.getTxtNamaPemesan().setText(penjualan.getNamapemesan());
               formPenjualan.getTxtTotalBayar().setColumns(penjualan.getTotal());
               formPenjualan.getTxtOngkirKirim().setColumns(penjualan.getOngkir());
               formPenjualan.getTxtDiskon().setColumns(penjualan.getDiskon());
            }else {
                JOptionPane.showMessageDialog(formPenjualan, "Data tidak ada");
            }
        } catch (Exception ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tampil(){
        try {
            DefaultTableModel tabel = (DefaultTableModel) formPenjualan.getTblPenjualan().getModel();
            tabel.setRowCount(0);
            List<Penjualan> list = penjualanDao.getAllPenjualan(con);
            for (Penjualan penjualan1 : list) {
                Object[] row = {
                    penjualan1.getKodepesanan(),
                    penjualan1.getTanggal(),
                    penjualan1.getNamapemesan(),
                    penjualan1.getTotal(),
                    penjualan1.getOngkir(),
                    penjualan1.getDiskon()
                };
                tabel.addRow(row);
            }
        } catch (Exception ex) {
            Logger.getLogger(PenjualanController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
