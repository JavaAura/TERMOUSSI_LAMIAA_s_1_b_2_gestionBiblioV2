package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque_v2.db.DbConnection;

import com.bibliotheque_v2.metier.Magazine;

public class MagazineDAOImpl implements MagazineDAO {
	
	 private Connection connection;

	   public MagazineDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }
	   
	   @Override
	    public void createMagazine(Magazine magazine) throws SQLException {
	    	 String sql = "INSERT INTO magazine (titre, auteur, datePub, nbrPages, type, bibliotheque_id, borrower_id, reserver_id, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, magazine.getTitre());
	            stmt.setString(2, magazine.getAuteur());
	            stmt.setDate(3, java.sql.Date.valueOf(magazine.getDatePub()));
	            stmt.setInt(4, magazine.getNbrPages());
	            stmt.setString(5, magazine.getType());
	            stmt.setInt(6, magazine.getBibliothequeId());
	            stmt.setObject(7, magazine.getBorrowerId(), java.sql.Types.INTEGER); // Allow nulls
	            stmt.setObject(8, magazine.getReserverId(), java.sql.Types.INTEGER);  
	            stmt.setString(9, magazine.getNumero());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.err.println("Error inserting Magazine into database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public void updateMagazine(Magazine magazine) throws SQLException {
	        //  update logic
	    }

	    @Override
	    public void deleteMagazine(int magazineId) throws SQLException {
	    	String sql = "DELETE FROM magazine WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, magazineId); 
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Magazine avec ID " + magazineId + " est supprimé avec succés.");
	            } else {
	                System.out.println("Aucun magazine trouvé avec ID " + magazineId + ".");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error deleting magazine from database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public Magazine findMagazineById(int magazineId) throws SQLException {
	        //  find logic
	        return null;
	    }

	    @Override
	    public List<Magazine> findAllMagazines() throws SQLException {
	        List<Magazine> magazines = new ArrayList<>();
	        String sql = "SELECT * FROM magazine";
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Magazine magazine = new Magazine(
	                    rs.getString("titre"),
	                    rs.getString("auteur"),
	                    rs.getDate("datepub").toLocalDate(),
	                    rs.getInt("nbrpages"),
	                    rs.getString("type"),
	                    rs.getInt("bibliotheque_id"),
	                    null, // borrowerId
	                    null, // reserverId
	                    rs.getString("numero")
	                );
	                magazines.add(magazine);
	            }
	        }
	        return magazines;
	    }
}
