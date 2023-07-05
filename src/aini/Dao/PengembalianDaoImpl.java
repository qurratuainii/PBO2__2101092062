/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aini.Dao;

import aini.Model.Pengembalian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class PengembalianDaoImpl implements PengembalianDao {
    @Override
    public void insert(Connection con, Pengembalian pengembalian) throws Exception {
        String sql = "insert into pengembalian values(?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengembalian.getKodeanggota());
        ps.setString(2, pengembalian.getKodebuku());
        ps.setString(3, pengembalian.getTglpinjam());
        ps.setString(4, pengembalian.getTglkembali());
        ps.setInt(5, pengembalian.getTerlambat());
        ps.setDouble(6, pengembalian.getDenda());
        ps.executeUpdate();
    }

    @Override
    public void update(Connection con, Pengembalian pengembalian) throws Exception {
        String sql = "update pengembalian set tgldikembalikan = ? and terlambat = ? and denda = ? "
                + "where kodeanggota = ? and kodebuku = ? and tglpinjam = ? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengembalian.getTglkembali());
        ps.setInt(2, pengembalian.getTerlambat());
        ps.setDouble(3, pengembalian.getDenda());
        ps.setString(2, pengembalian.getKodeanggota());
        ps.setString(3, pengembalian.getKodebuku());
        ps.setString(4, pengembalian.getTglpinjam());
        ps.executeUpdate();
    }

    @Override
    public void delete(Connection con, Pengembalian pengembalian) throws Exception {
        String sql = "delete from pengembalian "
                + "where kodeanggota = ? and kodebuku = ? and tglpinjam = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengembalian.getKodeanggota());
        ps.setString(2, pengembalian.getKodebuku());
        ps.setString(3, pengembalian.getTglpinjam());
        ps.executeUpdate();
    }

    @Override
    public Pengembalian getPengembalian(Connection con, String kodeanggota, String kodebuku, String tglpinjam) throws Exception {
        String sql = "select * from pengembalian "
               + "where kodeanggota=? and kodebuku=? and tglpinjam=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, kodeanggota);
        ps.setString(2, kodebuku);
        ps.setString(3, tglpinjam);
        ResultSet rs = ps.executeQuery();
        Pengembalian pengembalian = null;
        if (rs.next()) {
            pengembalian = new Pengembalian();
            pengembalian.setKodeanggota(rs.getString(1));
            pengembalian.setKodebuku(rs.getString(2));
            pengembalian.setTglpinjam(rs.getString(3));
            pengembalian.setTglkembali(rs.getString(4));
            pengembalian.setTerlambat(rs.getInt(5));
            pengembalian.setDenda(rs.getDouble(6));
        }
        return pengembalian;
    }

    @Override
    public List<Pengembalian> getAllPengembalian(Connection con) throws Exception {
        String sql = "select peminjaman.kodeanggota, anggota.namaanggota, buku.kodebuku, peminjaman.tglpinjam, "
                + "peminjaman.tglkembali, tgldikembalikan, terlambat, denda"
                + "FROM peminjaman"
                + "inner join anggota on peminjaman.kodeanggota = anggota.kodeanggota"
                + "inner join buku on peminjaman.kodebuku = buku.kodebuku"
                + "left join pengembalian on (peminjaman.kodeanggota = pengembalian.kodeanggota"
                + "and peminjaman.kodebuku = pengembalian.kodebuku"
                + "and peminjaman.tglpinjam = pengembalian.tglpinjam)";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pengembalian> list = new ArrayList<>();
        Pengembalian pengembalian = null;
        while (rs.next()) {
            pengembalian = new Pengembalian();
            pengembalian.setKodeanggota(rs.getString(1));
            pengembalian.setKodebuku(rs.getString(2));
            pengembalian.setTglpinjam(rs.getString(3));
            pengembalian.setTglkembali(rs.getString(4));
            pengembalian.setTerlambat(rs.getInt(5));
            pengembalian.setDenda(rs.getDouble(6));
            list.add(pengembalian);
        }
        return list;
    }

    @Override
    public int getSelisihTanggal(Connection con, String tgl, String tgl2) throws Exception {
        int hasil=0;
        String sql = "select datediff(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, tgl);
        ps.setString(2, tgl2);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            hasil = rs.getInt(1);
        }
        return hasil;
    }
    
    
    
}
