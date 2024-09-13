package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.TheseUniversitaire;


public class TheseUniversitaireDAOmpl implements TheseUniversitaireDAO {
	
	private Connection connection;

	   public TheseUniversitaireDAOmpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }
	   
	   @Override
	    public void createTheseUniversitaire(TheseUniversitaire these) throws SQLException {
	    	 String sql = "INSERT INTO these_universitaire (titre, auteur, datePub, nbrPages, type, bibliotheque_id, borrower_id, reserver_id, universite,domaine) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, these.getTitre());
	            stmt.setString(2, these.getAuteur());
	            stmt.setDate(3, java.sql.Date.valueOf(these.getDatePub()));
	            stmt.setInt(4, these.getNbrPages());
	            stmt.setString(5, these.getType());
	            stmt.setInt(6, these.getBibliothequeId());
	            stmt.setObject(7, these.getBorrowerId(), java.sql.Types.INTEGER); // Allow nulls
	            stmt.setObject(8, these.getReserverId(), java.sql.Types.INTEGER);  
	            stmt.setString(9, these.getUniversite());
	            stmt.setString(10, these.getDomaine());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.err.println("Error inserting TheseUniversitaire into database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public void updateTheseUniversitaire(int documentId, String nouveauTitre, String nouvelAuteur,LocalDate nouvelleDatePub, int nouveauNbrPages, String nouvelleUniversite, String nouveauDomaine) throws SQLException {
	    	 String updateQuery = "UPDATE these_universitaire SET titre = ?, auteur = ?, datePub = ?, nbrPages = ?, universite = ? ,domaine=? WHERE id = ?";

	         try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

	             preparedStatement.setString(1, nouveauTitre);
	             preparedStatement.setString(2, nouvelAuteur);
	             preparedStatement.setObject(3, nouvelleDatePub); 
	             preparedStatement.setInt(4, nouveauNbrPages);
	             preparedStatement.setString(5, nouvelleUniversite);
	             preparedStatement.setString(6, nouveauDomaine);
	             preparedStatement.setInt(7, documentId);

	             int rowsUpdated = preparedStatement.executeUpdate();
	             if (rowsUpdated > 0) {
	                 System.out.println("La these universitaire a été mis à jour avec succès.");
	             } else {
	                 System.out.println("Aucune these universitaire trouvée avec l'ID spécifié.");
	             }

	         } catch (SQLException e) {
	             System.err.println("Erreur lors de la mise à jour de la these universitaire: " + e.getMessage());
	             throw e; 
	         }
	    }

	    @Override
	    public void deleteTheseUniversitaire(int theseId) throws SQLException {
	    	String sql = "DELETE FROM these_universitaire WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, theseId); 
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("these universitaire avec ID " + theseId + " est supprimé avec succés.");
	            } else {
	                System.out.println("Aucune these universitaire trouvé avec ID " + theseId + ".");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error deleting these universitaire from database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public TheseUniversitaire findTheseUniversitaireById(int theseId) throws SQLException {
	        //  find logic
	        return null;
	    }

	    @Override
	    public List<TheseUniversitaire> getAllThesesUniversitaires() throws SQLException {
	        List<TheseUniversitaire> theses = new ArrayList<>();
	        String sql = "SELECT * FROM these_universitaire";
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	TheseUniversitaire these = new TheseUniversitaire(
	                    rs.getString("titre"),
	                    rs.getString("auteur"),
	                    rs.getDate("datepub").toLocalDate(),
	                    rs.getInt("nbrpages"),
	                    rs.getString("type"),
	                    rs.getInt("bibliotheque_id"),
	                    null, // borrowerId
	                    null, // reserverId
	                    rs.getString("universite"),
	                    rs.getString("domaine")
	                );
	            	theses.add(these);
	            }
	        }
	        return theses;
	    }
}
