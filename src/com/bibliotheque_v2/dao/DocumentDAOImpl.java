package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bibliotheque_v2.db.DbConnection;
import com.bibliotheque_v2.metier.Document;
import com.bibliotheque_v2.metier.JournalScientifique;
import com.bibliotheque_v2.metier.Livre;
import com.bibliotheque_v2.metier.Magazine;
import com.bibliotheque_v2.metier.TheseUniversitaire;

public class DocumentDAOImpl implements DocumentDAO{

	 private Connection connection;

	    public DocumentDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }

	    @Override
	    public String getDocumentType(int documentId) throws SQLException {
	        String sql = "SELECT type FROM document WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, documentId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("type");
	                } else {
	                    throw new SQLException("Document not found with ID: " + documentId);
	                }
	            }
	        }
	    }
	   
	    @Override
	    public Integer getDocumentIDByTitle(String title) throws SQLException {
	        String sql = "SELECT * FROM document WHERE titre = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, title);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    Integer docID=rs.getInt("id");
	                        		return docID;  
	            }else {
	            	return null;
	            }
	        } catch (SQLException e) {
	            System.err.println("Error retrieving document with title " + title + ": " + e.getMessage());
	            throw e;
	        }
	       }
	      }
	    
	    @Override 
	    public void updateBorrowerIdNull(int documentId,int userID) throws SQLException{
	    	String sql = "UPDATE document SET borrower_id = ? WHERE id = ? AND borrower_id =?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        	 stmt.setNull(1, java.sql.Types.INTEGER); // Set borrower_id to NULL
	            stmt.setInt(2, documentId);
	            stmt.setInt(3, userID);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new SQLException("Either no document found with ID: " + documentId + " or the document is already borrowed or you are not the borrower !");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error updating borrower ID for document with ID " + documentId + ": " + e.getMessage());
	            throw e;
	        }
	    }
	    @Override
	    public void updateBorrowerId(int documentId, int borrowerId) throws SQLException {
	        String sql = "UPDATE document SET borrower_id = ? WHERE id = ? AND borrower_id IS NULL";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, borrowerId);
	            stmt.setInt(2, documentId);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected == 0) {
	                throw new SQLException("Either no document found with ID: " + documentId + " or the document is already borrowed.");
	            }
	        } catch (SQLException e) {
	            System.err.println("Error updating borrower ID for document with ID " + documentId + ": " + e.getMessage());
	            throw e;
	        }
	    }
}
