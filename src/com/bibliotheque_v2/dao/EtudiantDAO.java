package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.Etudiant;

public interface EtudiantDAO {

	  void createEtudiant(Etudiant etudiant) throws SQLException;

	    void updateEtudiant(int id, String name, String email, String numero) throws SQLException;

	    void deleteEtudiant(int id) throws SQLException;

	    List<Etudiant> getAllEtudiants() throws SQLException;
}
