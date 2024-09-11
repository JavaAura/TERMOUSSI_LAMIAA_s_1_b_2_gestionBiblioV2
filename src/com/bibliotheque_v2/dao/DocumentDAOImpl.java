package com.bibliotheque_v2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bibliotheque_v2.db.DbConnection;

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
}
