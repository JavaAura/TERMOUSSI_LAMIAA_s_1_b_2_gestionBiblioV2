package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.Magazine;

public interface MagazineDAO {
	 void createMagazine(Magazine magazine) throws SQLException;
	    void updateMagazine(Magazine magazine) throws SQLException;
	    void deleteMagazine(int magazineId) throws SQLException;
	    Magazine findMagazineById(int magazineId) throws SQLException;
	    List<Magazine> findAllMagazines() throws SQLException;
}
