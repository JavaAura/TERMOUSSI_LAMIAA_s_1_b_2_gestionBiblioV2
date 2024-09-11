package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	    public void updateTheseUniversitaire(TheseUniversitaire these) throws SQLException {
	        //  update logic
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
	    public List<TheseUniversitaire> findAllTheseUniversitaires() throws SQLException {
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
