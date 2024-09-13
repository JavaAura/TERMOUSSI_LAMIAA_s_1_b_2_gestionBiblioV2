package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	    public void updateLivre(int documentId, String titre, String auteur, LocalDate datePub, int nbrPages, String isbn) throws SQLException {
	    	  String updateQuery = "UPDATE livre SET titre = ?, auteur = ?, datePub = ?, nbrPages = ?, isbn = ? WHERE id = ?";

	          try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

	              preparedStatement.setString(1, titre);
	              preparedStatement.setString(2, auteur);
	              preparedStatement.setDate(3, java.sql.Date.valueOf(datePub));
	              preparedStatement.setInt(4, nbrPages);
	              preparedStatement.setString(5, isbn);
	              preparedStatement.setInt(6, documentId);

	              int rowsAffected = preparedStatement.executeUpdate();
	              if (rowsAffected > 0) {
	                  System.out.println("Livre mis à jour avec succès.");
	              } else {
	                  System.out.println("Aucun livre trouvé avec cet ID.");
	              }
	          } catch (SQLException e) {
	              throw new SQLException("Erreur lors de la mise à jour du livre: " + e.getMessage());
	          }
	      }
	    

	    @Override
	    public void deleteLivre(int livreId) throws SQLException {
	    	String sql = "DELETE FROM livre WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, livreId); 
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Livre avec ID " + livreId + " est supprimé avec succés.");
	            } else {
	                System.out.println("Aucun livre trouvé avec ID " + livreId + ".");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error deleting Livre from database: " + e.getMessage());
	            throw e;
	        }
	    }

	    @Override
	    public Livre findLivreById(int livreId) throws SQLException {
	        //  find logic
	        return null;
	    }

	    @Override
	    public List<Livre> getAllLivres() throws SQLException {
	        List<Livre> livres = new ArrayList<>();
	        String sql = "SELECT * FROM livre";
	        try (PreparedStatement stmt = connection.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Livre livre = new Livre(
	                    rs.getString("titre"),
	                    rs.getString("auteur"),
	                    rs.getDate("datepub").toLocalDate(),
	                    rs.getInt("nbrpages"),
	                    rs.getString("type"),
	                    rs.getInt("bibliotheque_id"),
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
