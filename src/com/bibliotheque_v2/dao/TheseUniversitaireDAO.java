package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bibliotheque_v2.metier.TheseUniversitaire;



public interface TheseUniversitaireDAO {
	 void createTheseUniversitaire(TheseUniversitaire these) throws SQLException;
	    void updateTheseUniversitaire(int documentId, String nouveauTitre, String nouvelAuteur,LocalDate nouvelleDatePub, int nouveauNbrPages, String nouvelleUniversite, String nouveauDomaine) throws SQLException;
	    void deleteTheseUniversitaire(int theseID) throws SQLException;
	    List<TheseUniversitaire> getAllThesesUniversitaires() throws SQLException;
	    TheseUniversitaire getTheseUniversitaireById(int documentId) throws SQLException;
	    
}
