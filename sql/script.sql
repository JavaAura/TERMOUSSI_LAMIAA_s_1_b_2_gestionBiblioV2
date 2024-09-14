-- Database: biblio

-- DROP DATABASE IF EXISTS biblio;

CREATE DATABASE biblio
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


CREATE TABLE bibliotheque (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);
CREATE TABLE utilisateur (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)  NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    type VARCHAR(50) NOT NULL   
);

CREATE TABLE etudiant (
    numero VARCHAR(50)
) INHERITS (utilisateur);

CREATE TABLE professeur (
    departement VARCHAR(255)    
) INHERITS (utilisateur);

CREATE TABLE document (
    id SERIAL PRIMARY KEY,
titre VARCHAR(255) NOT NULL, //Todo :unique
    auteur VARCHAR(255) NOT NULL, 
    datePub DATE NOT NULL, 
   nbrPages INT,           
     type VARCHAR(50) NOT NULL,
    bibliotheque_id INT REFERENCES bibliotheque(id),
  borrower_id INT REFERENCES utilisateur(id),
      reserver_id INT REFERENCES utilisateur(id)
);
CREATE TABLE livre (
    isbn VARCHAR(255)         
) INHERITS (document);

CREATE TABLE magazine (
    numero VARCHAR(255)       
) INHERITS (document);

CREATE TABLE journal_scientifique (
    domaine_recherche VARCHAR(255)
) INHERITS (document);

CREATE TABLE these_universitaire (
    universite VARCHAR(255),        
    domaine VARCHAR(255)
) INHERITS (document);

