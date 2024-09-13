package com.bibliotheque_v2.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.bibliotheque_v2.metier.JournalScientifique;


public interface JournalScientifiqueDAO {
	 void createJournalScientifique(JournalScientifique journal) throws SQLException;
	    void updateJournalScientifique(int documentId,String nouveauTitre,String nouvelAuteur, LocalDate nouvelleDatePub, int nouveauNbrPages,String nouveauDomaineRecherche) throws SQLException;
	    void deleteJournalScientifique(int journalID) throws SQLException;
	    JournalScientifique findJournalScientifiqueById(int journalID) throws SQLException;
	    List<JournalScientifique> findAllJournalScientifiques() throws SQLException;
}
