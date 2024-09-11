package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.TheseUniversitaire;



public interface TheseUniversitaireDAO {
	 void createTheseUniversitaire(TheseUniversitaire these) throws SQLException;
	    void updateTheseUniversitaire(TheseUniversitaire these) throws SQLException;
	    void deleteTheseUniversitaire(int theseID) throws SQLException;
	    TheseUniversitaire findTheseUniversitaireById(int theseID) throws SQLException;
	    List<TheseUniversitaire> findAllTheseUniversitaires() throws SQLException;
}
