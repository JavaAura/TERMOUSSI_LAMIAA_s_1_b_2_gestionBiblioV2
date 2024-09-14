package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bibliotheque_v2.metier.Magazine;

public interface MagazineDAO {
	 void createMagazine(Magazine magazine) throws SQLException;
	    void updateMagazine(int documentId, String nouveauTitre, String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages, String nouveauNumero) throws SQLException;
	    void deleteMagazine(int magazineId) throws SQLException;
	    List<Magazine> getAllMagazines() throws SQLException;
	    Magazine getMagazineById(int documentId) throws SQLException;
}
