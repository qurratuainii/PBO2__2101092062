/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTS_b.Model;

/**
 *
 * @author USER
 */
public class Penjualan {
    private String kodepesanan;
    private String tanggal;
    private String namapemesan;
    private String total;
    private String ongkir;
    private String diskon;
    
    public Penjualan (){
        
    }
    
    public Penjualan(String kodepesanan, String tanggal, String namapemesan, String total, String ongkir, String diskon) {
        this.kodepesanan = kodepesanan;
        this.tanggal = tanggal;
        this.namapemesan = namapemesan;
        this.total = total;
        this.ongkir = ongkir;
        this.diskon = diskon;
    }

    public String getKodepesanan() {
        return kodepesanan;
    }

    public void setKodepesanan(String kodepesanan) {
        this.kodepesanan = kodepesanan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamapemesan() {
        return namapemesan;
    }

    public void setNamapemesan(String namapemesan) {
        this.namapemesan = namapemesan;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOngkir() {
        return ongkir;
    }

    public void setOngkir(String ongkir) {
        this.ongkir = ongkir;
    }

    public String getDiskon() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon = diskon;
    }
    
    
}
