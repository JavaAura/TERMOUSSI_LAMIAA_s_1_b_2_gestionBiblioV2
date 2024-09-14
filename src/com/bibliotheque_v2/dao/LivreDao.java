package com.bibliotheque_v2.dao;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bibliotheque_v2.metier.Livre;
import com.bibliotheque_v2.metier.Magazine;

public interface LivreDAO {
	
	 void createLivre(Livre livre) throws SQLException;
	    void updateLivre(int documentId, String titre, String auteur, LocalDate datePub, int nbrPages, String isbn) throws SQLException;
	    void deleteLivre(int livreId) throws SQLException;
	    List<Livre> getAllLivres() throws SQLException;
	    Livre getLivreById(int documentId) throws SQLException;
}
