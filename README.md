                                                                        Gestion de Bibliothèque
Description du projet

Ce projet est une application console de gestion de bibliothèque évoluée. Il vise à intégrer une base de données relationnelle avec PostgreSQL et à utiliser des concepts Java avancés pour optimiser et étendre les fonctionnalités de l'application.
Objectif général de l'application

L'objectif de l'application est de gérer efficacement les documents et les utilisateurs d'une bibliothèque en fournissant des fonctionnalités de gestion avancées, y compris la gestion des emprunts et des réservations, le tout en intégrant une base de données pour la persistance des données.
Technologies utilisées

    Langage de programmation: Java 8
    Base de données: PostgreSQL
    Outils: JDBC, Git, JIRA
    Concepts Java: POO (Polymorphisme, Héritage), Java Time API, Stream API, Optionals, Expressions Lambda
    Modélisation: UML (Diagrammes de cas d'utilisation, Diagrammes de classes)

Structure du projet

    Couche de présentation:
        Interface console améliorée (ConsoleUI)

    Couche de métier:
        Logique de gestion des documents et des utilisateurs

    Couche de persistance:
        DAO (Data Access Objects) pour l'accès aux données

    Couche utilitaire:
        InputValidator: Validation améliorée des entrées utilisateur

Description brève de l'architecture adoptée

L'application est structurée en plusieurs couches pour séparer les responsabilités :

    Couche de présentation : Gère l'interaction avec l'utilisateur via une interface console.
    Couche de métier : Contient la logique de gestion des documents et des utilisateurs.
    Couche de persistance : Accède à la base de données à l'aide des DAO pour les opérations CRUD.
    Couche utilitaire : Fournit des outils pour la validation des entrées.

Instructions d'installation et d'utilisation
Prérequis

    Java 8 ou supérieur
    PostgreSQL
    Pilote JDBC pour PostgreSQL

Étapes pour configurer la base de données

    Créez une base de données PostgreSQL pour l'application.
    Exécutez les scripts SQL fournis pour créer les tables nécessaires.
    Configurez la connexion JDBC dans votre application.

Comment lancer l'application

    Clonez le dépôt Git contenant le projet.
    Compilez le projet avec Maven ou un autre outil de construction.
    Exécutez l'application  pour lancer l'interface console.

Améliorations futures possibles

    Ajouter une interface graphique pour une meilleure expérience utilisateur.
    Ajouter des fonctionnalités de recherche et de filtrage plus avancées.

Auteur et contact

    Auteur: Termoussi Lamiaa
    Email: lamiaa3105@gmail.com
