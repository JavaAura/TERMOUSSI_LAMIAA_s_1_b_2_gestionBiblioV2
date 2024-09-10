package com.bibliotheque_v2.dao;

import java.util.List;
import java.sql.SQLException;
import com.bibliotheque_v2.metier.Document;

public interface DocumentDAO {
	
    void createDocument(Document document) throws SQLException;

    void updateDocument(Document document) throws SQLException;

    void deleteDocument(int documentId) throws SQLException;
    
    List<Document> findAll() throws SQLException;
}
