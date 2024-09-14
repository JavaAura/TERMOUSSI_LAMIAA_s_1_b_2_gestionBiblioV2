package com.bibliotheque_v2.dao;

import java.sql.SQLException;

public interface UtilisateurDAO {

	String getUtilisateurType(int utilisateurId) throws SQLException;
}
