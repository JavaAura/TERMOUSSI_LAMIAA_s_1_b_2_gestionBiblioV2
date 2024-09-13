package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.Professeur;

public interface ProfesseurDAO {

	  void createProfesseur(Professeur professeur) throws SQLException;

	    void updateProfesseur(int id, String name, String email, String departement) throws SQLException;

	    void deleteProfesseur(int id) throws SQLException;

	    List<Professeur> getAllProfesseurs() throws SQLException;
}
