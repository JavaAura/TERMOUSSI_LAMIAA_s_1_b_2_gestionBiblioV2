package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque_v2.metier.JournalScientifique;


public interface JournalScientifiqueDAO {
	 void createJournalScientifique(JournalScientifique journal) throws SQLException;
	    void updateJournalScientifique(JournalScientifique journal) throws SQLException;
	    void deleteJournalScientifique(int journalID) throws SQLException;
	    JournalScientifique findJournalScientifiqueById(int journalID) throws SQLException;
	    List<JournalScientifique> findAllJournalScientifiques() throws SQLException;
}
