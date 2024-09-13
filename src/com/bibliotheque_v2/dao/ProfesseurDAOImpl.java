package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.Professeur;

public class ProfesseurDAOImpl implements ProfesseurDAO {

	 private Connection connection;

	    public ProfesseurDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }
	  @Override
	    public void createProfesseur(Professeur professeur) throws SQLException {
	        String sql = "INSERT INTO professeur (name, email, type, departement) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, professeur.getName());
	            stmt.setString(2, professeur.getEmail());
	            stmt.setString(3, professeur.getType());
	            stmt.setString(4, professeur.getDepartement());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            System.err.println("Erreur lors de l'insertion du professeur dans la base de données: " + e.getMessage());
	            throw e;
	        }
	    }
}
