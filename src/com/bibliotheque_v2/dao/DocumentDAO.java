package com.bibliotheque_v2.dao;

import java.sql.SQLException;

public interface DocumentDAO {
	
    String getDocumentType(int documentId) throws SQLException;
}
