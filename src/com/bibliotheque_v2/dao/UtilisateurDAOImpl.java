package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bibliotheque_v2.db.DbConnection;

public class UtilisateurDAOImpl implements UtilisateurDAO{

	 private Connection connection;

	    public UtilisateurDAOImpl() {
	        this.connection = DbConnection.getInstance().getConnection();
	    }

	    @Override
	    public String getUtilisateurType(int userId) throws SQLException {
	        String sql = "SELECT type FROM utilisateur WHERE id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, userId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("type");
	                } else {
	                    throw new SQLException("Utilisateur non trouvé avec l'ID: " + userId);
	                }
	            }
	        }
	    }
	    
	    @Override
	    public String getUserTypeByEmail(String email) throws SQLException {
	        String sql = "SELECT type FROM utilisateur WHERE email = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, email);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("type"); 
	                } else {
	                    return null; 
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error retrieving user type by email: " + e.getMessage());
	            throw e;
	        }
	    }
	
	    @Override
	    public int getCurrentUserId(String email) throws SQLException {
	        String sql = "SELECT id FROM utilisateur WHERE email = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, email);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt("id");
	                } else {
	                    return -1;
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error retrieving user ID by email: " + e.getMessage());
	            throw e;
	        }
	    }
}
