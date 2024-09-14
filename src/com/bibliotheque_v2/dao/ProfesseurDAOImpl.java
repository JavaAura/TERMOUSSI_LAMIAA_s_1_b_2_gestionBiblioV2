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

	  @Override
	  public void updateProfesseur(int id, String nouveauNom, String nouvelEmail, String nouveauDepartement) throws SQLException {
	      String updateQuery = "UPDATE professeur SET name = ?, email = ?, departement = ? WHERE id = ?";

	      try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

	          stmt.setString(1, nouveauNom);
	          stmt.setString(2, nouvelEmail);
	          stmt.setString(3, nouveauDepartement); 
	          stmt.setInt(4, id);

	          int rowsUpdated = stmt.executeUpdate();

	          if (rowsUpdated > 0) {
	              System.out.println("Le professeur a été mis à jour avec succès.");
	          } else {
	              System.out.println("Aucun professeur trouvé avec l'ID spécifié.");
	          }

	      } catch (SQLException e) {
	          System.err.println("Erreur lors de la mise à jour du professeur: " + e.getMessage());
	          throw e;
	      }
	  }
	  
	  @Override
	  public void deleteProfesseur(int professeurId) throws SQLException {
		    String sql = "DELETE FROM professeur WHERE id = ?";
		    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
		        stmt.setInt(1, professeurId);
		        int rowsAffected = stmt.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Professeur avec ID " + professeurId + " est supprimé avec succès.");
		        } else {
		            System.out.println("Aucun professeur trouvé avec ID " + professeurId + ".");
		        }
		    } catch (SQLException e) {
		        System.err.println("Erreur lors de la suppression du professeur: " + e.getMessage());
		        throw e;
		    }
		}
}
