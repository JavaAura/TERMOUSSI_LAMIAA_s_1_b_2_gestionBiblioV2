package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.Livre;

public class LivreDAOImpl implements LivreDAO  {
	
	 private Connection connection;

	    public LivreDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }

	    @Override
	    public void createLivre(Livre livre) throws SQLException {
	    	 String sql = "INSERT INTO livre (titre, auteur, datePub, nbrPages, type, bibliotheque_id, borrower_id, reserver_id, isbn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, livre.getTitre());
	            stmt.setString(2, livre.getAuteur());
	            stmt.setDate(3, java.sql.Date.valueOf(livre.getDatePub()));
	            stmt.setInt(4, livre.getNbrPages());
	            stmt.setString(5, livre.getType());
	            stmt.setInt(6, livre.getBibliothequeId());
	            stmt.setObject(7, livre.getBorrowerId(), java.sql.Types.INTEGER); // Allow nulls
	            stmt.setObject(8, livre.getReserverId(), java.sql.Types.INTEGER);  
	            stmt.setString(9, livre.getIsbn());
	            stmt.executeUpdate();
	        }catch (SQLException e) {
	            System.err.println("Error inserting Livre into database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public void updateLivre(Livre livre) throws SQLException {
	        //  update logic
	    }

	    @Override
	    public void deleteLivre(int livreId) throws SQLException {
	        //  delete logic
	    }

	    @Override
	    public Livre findLivreById(int livreId) throws SQLException {
	        //  find logic
	        return null;
	    }

	    @Override
	    public List<Livre> findAllLivres() throws SQLException {
	        List<Livre> livres = new ArrayList<>();
	        String sql = "SELECT * FROM livre";
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Livre livre = new Livre(
	                    rs.getString("titre"),
	                    rs.getString("auteur"),
	                    rs.getDate("datePub").toLocalDate(),
	                    rs.getInt("nbrPages"),
	                    rs.getString("type"),
	                    rs.getInt("bibliothequeId"),
	                    null, // borrowerId
	                    null, // reserverId
	                    rs.getString("isbn")
	                );
	                livres.add(livre);
	            }
	        }
	        return livres;
	    }
}
