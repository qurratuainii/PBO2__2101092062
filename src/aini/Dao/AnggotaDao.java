/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aini.Dao;

import aini.Model.Anggota;
import java.sql.Connection;

/**
 *
 * @author USER
 */
public interface AnggotaDao {
    void insert(Connection con, Anggota anggota) throws Exception;
    
}
