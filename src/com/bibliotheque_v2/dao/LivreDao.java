package com.bibliotheque_v2.dao;


import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.Livre;

public interface LivreDAO {
	
	 void createLivre(Livre livre) throws SQLException;
	    void updateLivre(Livre livre) throws SQLException;
	    void deleteLivre(int livreId) throws SQLException;
	    Livre findLivreById(int livreId) throws SQLException;
	    List<Livre> findAllLivres() throws SQLException;
}
