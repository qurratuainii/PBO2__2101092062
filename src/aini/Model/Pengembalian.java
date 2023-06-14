/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aini.Model;

/**
 *
 * @author USER
 */
public class Pengembalian {
    private String kodeanggota;
    private String kodebuku;
    private String tglpinjam;
    private String tgldikembalikan;
    private int terlambat;
    private double denda;
    
    public Pengembalian(){
        
    }
    
    public Pengembalian(String kodeanggota, String kodebuku, String tglpinjam, String tgldikembalikan, String terlambat, String denda){
        this.kodeanggota = kodeanggota;
        this.kodebuku = kodebuku;
        this.tglpinjam = tglpinjam;
        this.tgldikembalikan = tgldikembalikan;
    }
    
    public Pengembalian(int terlambat){
        this.terlambat = terlambat;
    }
    
    public Pengembalian(double denda){
        this.denda = denda;
    }

    public String getKodeanggota() {
        return kodeanggota;
    }

    public void setKodeanggota(String kodeanggota) {
        this.kodeanggota = kodeanggota;
    }

    public String getKodebuku() {
        return kodebuku;
    }

    public void setKodebuku(String kodebuku) {
        this.kodebuku = kodebuku;
    }

    public String getTglpinjam() {
        return tglpinjam;
    }

    public void setTglpinjam(String tglpinjam) {
        this.tglpinjam = tglpinjam;
    }

    public String getTgldikembalikan() {
        return tgldikembalikan;
    }

    public void setTgldikembalikan(String tgldikembalikan) {
        this.tgldikembalikan = tgldikembalikan;
    }

    public int getTerlambat() {
        return terlambat;
    }

    public void setTerlambat(int terlambat) {
        this.terlambat = terlambat;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }
    
    
}
