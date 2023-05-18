/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTS_b.Dao;

import UTS_b.Model.Penjualan;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PenjualanDao {
    public void insert(Connection con, Penjualan penjualan) throws Exception;
    public void update(Connection con, Penjualan penjualan) throws Exception;
    public void delete(Connection con, Penjualan penjualan) throws Exception;
    public Penjualan getPenjualan(Connection con, String kode) throws Exception;
    public List<Penjualan> getAllPenjualan(Connection con) throws Exception;
}
