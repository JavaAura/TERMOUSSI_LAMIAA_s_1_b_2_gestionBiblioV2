package com.bibliotheque_v2.dao;

import java.sql.SQLException;

import com.bibliotheque_v2.metier.Document;

public interface DocumentDAO {
	
    String getDocumentType(int documentId) throws SQLException;
    Integer getDocumentIDByTitle(String title) throws SQLException;
    void updateBorrowerId(int documentId, int borrowerId) throws SQLException;
    void updateBorrowerIdNull(int documentId,int userID) throws SQLException;
}
