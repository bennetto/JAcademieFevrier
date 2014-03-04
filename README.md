Examen Fevrier 
================

Auteurs
-------
Benjamin BONNETTO
Jean-Michel POTHELUNE

Sujet
------
L’objectif est de réaliser une application Web Java permettant de visualiser et éventuellement d’éditer les données d’une base de données.

####Le domaine métier est le suivant :
*	Des Artistes 
    *	sont identifiés de manière unique par un CodeArtiste de type entier.
    *	Possèdent un nom
    *	Produisent des albums
*	Les Albums
    *	sont identifiés de manière unique par un CodeAlbum de type entier.
    *	Possèdent un nom
    *	Contiennent des chansons
*	Les Chansons
    *	Possèdent un numéro unique dans un album
    *	ont un titre
    *	ont une durée qui est un entier (nombre de secondes)

L’application web doit permettre sur une 1ère page de lister les Artistes.
En cliquant sur un Artiste on arrive sur la page de cette artiste qui liste ses albums.
En cliquant sur album on arrive sur la page de cet album qui liste les chansons de cet album.


Installation (Eclipse)
----------------------
### Clonage du repository git à partir de GitHub
git clone https://github.com/bennetto/JAcademieFevrier.git

### Importation dans Eclipse
Importez le projet maven (File -> Import -> Maven -> Existing Maven Projects)
Cliquez sur [Browse...] et sélectionnez le dossier dans lequel vous venez de cloner le repository.
Cliquez sur [Finish]

### Lancement de la base de données sous eclipse. (Nouvelle BDD)
Créez une nouvelle configuration (Run -> Run configuration...)
Dans le volet de gauche, effectuez un clic droit sur Java Application -> New.
Sélectionnez la configuration ainsi créée
Dans le volet de droite, remplissez le champ Name par 'LaunchDB'
Sous l'onglet Main:
* Sélectionnez le projet importé (Browse...)
* Remplissez le champ Main class par 'org.hsqldb.server.Server'
Sous l'onglet Arguments:
Remplissez le champ Program arguments avec: '--database.0 file:musicdb --dbname.0 musicdb'

Pour lancer la base de donnée, exécutez la configuration LaunchDB via la flèche verte située dans la barre d'action.
**La base de donnée doit être lancée pour que le programme fonctionne**
 
### Lancement du gestionnaire de bases de données sous eclipse. (Nouvelle BDD, facultatif)
Créez une nouvelle configuration (Run -> Run configuration...)
Dans le volet de gauche, effectuez un clic droit sur Java Application -> New.
Sélectionnez la configuration ainsi créee
Dans le volet de droite, remplissez le champ Name par 'LaunchDBManager'
Sous l'onglet Main:
* Sélectionnez le projet importé (Browse...)
* Remplissez le champ Main class par 'org.hsqldb.util.DatabaseManagerSwing'

Pour lancer le gestionnaire, exécutez la configuration LaunchDBManager via la flèche verte située dans la barre d'action.

Une fois le gestionnaire lancé, entrez les paramètres suivant pour vous connecter a la base de donnée:
* Setting Name: HSQL MusicDB Engine
* Type: HSQL Database Engine In-Memory
* Driver: org.hsqldb.jdbc.JDBCDriver
* URL: jdbc:hsqldb:hsql://localhost/musicdb
* User:  SA
* Password: (laisser vide)
 
### Initialisation de la base de données. (Nouvelle BDD)
Dans src/main/resources, ouvrez le fichier confCSV.properties avec un éditeur de texte.
dans inputPath, renseignez le chemin du dossier où se situent les fichiers de mise à jour
dans successOutputPath, renseignez le chemin du dossier où seront déplacés les fichiers correctement exécutés
dans failOutputPath, renseignez le chemin du dossier où seront déplacés les fichiers qui n'auront pas correctement été exécutés.
dans dateExtension, renseignez si nécessaire une extension de date qui s'ajoutera aux nom des dossier de sortie lors de l'execution du programme.

Ensuite il vous suffit de lancer le projet et d'acceder à la page http://localhost:8080/JAcademieFevrier/musicExtractor.do

### Configuration d'une base de données existante.
Dans src/main/resources, ouvrez le fichier hibernate.cfg.xml avec un éditeur de texte.
Si vous utilisez une base de donnée HSQLDB, vous n'avez qu'a personnaliser les champs 'hibernate.connection.url', 'hibernate.connection.username', 'hibernate.connection.password' avec vos informations
Si vous utilisez un autre type de base de données, changez le dialecte, et importez votre propre librairie dans le pom.xml (utilisateur avancés)



### Lancement de l'application
Placez vous dans la classe Main.java, et appuyer sur le bouton Run (Fleche verte).




TODO



Choix de la technologie
-----------------------------
### Hibernate
 Hibernate a été choisie pour réaliser la persistance des objets métiers.
Il s'occupe de la liaison avec la base de donnée et réalise du requetage simple tout seul. Il se subsititue au SQL pour la sauvegarde, la mise a jour, la suppression d'objet par exemple.
De plus il permet de changer simplement de type de base de donnée si nécessaire. 

### log4j
Ce framework permet de journaliser tout ce qui ce passe dans le programme. 

### opencsv 
Cette librairie permet de lire des fichiers csv.

### junit
Ce framework permet de réaliser des test unitaire sur le projet. Il permet de tester de façon automatique des fonctions de classes importantes afin de vérifier après modification que tous marche correctement

### spring MVC
TODO


Structure du projet
-----------------------------
### Couches
* **Couche service** : Cette couche renferme l'intelligence du projet. C'est dans cette couche que vous pouvez trouvez les fonctions qui permette de manipuler les objets.
* **Couche d’accès au donnée** (DAO) : Cette couche contient toutes les fonctions pour communiquer avec le base de donnée ( dans ce projet via hibernate). 
* **Couche métier** (BO) : Cette couche contient les classes qui permettes de stocker les donnée métier.  Ce ne sont que des données brutes, et il n'y a aucune (ou très peu) d'intelligence.

TODO


### Main
La mise à jour à partir de fichier ".music" fait appel a deux services interfacés. Le premier gère la lecture et le controle du fichier ".music", et en extrait des données qui seront stockées temporairement dans le programme. Le second permet la mise à jour des objets métiers. 
Un dernier service permettera le déplacement du fichier traité dans un dossier indiqué en paramètre.

### Dépendance dans la projet
 Dans le projet toutes les classes ( en dehors des classes métiers) sont interfacées. Cela permet de limiter les dépendance entre les classes et permet de changer de technologie aisément (Par exemple si une nouvelle implémentation d'un service souhaite être faite, il suffira d’implémenter la bonne interface et de ne faire que quelques changement dans les classes qui les utilises. ).

Même s'il n'en implémente pas dans l'état actuel, le projet est prêt pour l'intégration d'un framework d'injection de dépendance (spring). ceci permettrait de rendre les classes encore plus indépendant. 



Modèle physique des données
------------------------------------
![Modele donnes](/doc/modele.jpg "Modèle physique des données")
### Artiste:   
* **CODEARTISTE** integer, clef primaire : identifiant unique pour chaque artiste.
* **NOM** varchar(50) : nom de l'artiste.

### Album : 
* **CODEALBUM** integer, clef primaire : identifiant unique pour chaque album.
* **NOM** varchar(50): nom de l'album.
* **ARTISTE_ID** integer : référence vers un artiste.

### Chanson: 
* **IDCHANSON** integer, clef primaire, auto-générer : identifiant unique pour chaque chanson.
* **TITRE** varchar(100): titre de la chanson.
* **ALBUM_ID** interger : référence vers un album.
* **NUMERO** integer : numéro de la chanson dans son album.
* **DUREE** integer : temp de la chanson.


Descriptif des packages
-----------------------

TODO


### org.jacademie.examenDecembre.bo (Buiseness object)
Contient les classes métiers.

### org.jacademie.examenDecembre.dao
Contient les interfaces des Data Access Objects

### org.jacademie.examenDecembre.dao.impl
Implementation des DAO (Hibernate)

### org.jacademie.examenDecembre.dao.impl.mapping
Fichiers de mapping Objets métiers - DB (Hibernate)

### org.jacademie.examenDecembre.services
Contient les interfaces des differents services:
* __FileMoverService :__  Permet le déplacement d'un fichier.
* **MusicDataExtractorService :** Permet l'extraction d'informations (MusicData) sur les musiques a partir d'un lecteur (Reader).
* **MusicDataUpdaterService :** Permet la mise a jour des objets métiers à partir d'informations (MusicData) extraites ou fournies.
* **MusicData :** Permet le stockage temporaire d'informations sur les musiques.
* **MusicDataException :** Soulevée par MusicDataExtractorService et MusicDataUpdaterService lorsque les informations lues ou enregistrées sont éronnées.

### org.jacademie.examenDecembre.services.impl
Implementation des services:

### org.jacademie.examenDecembre.utils
Rassemble diférents outils utilisée par les services.
* **PersistenceManager :** Interface les outils traditionnels de gestion de base SQL.
* **HibernateManage :** Implémentation de l'interface PersitenceManager pour hibernate.

### org.jacademie.examenDecembre.tests
Classes de test des Services et DAO de l'application.
* **DAOsTest :** Test la bonne sauvegarde et récupération de tous les objets.
* **MusicDataUpdaterService :** Test unitaire (rudimentaire) de la classe MusicDataUpdater


### Ressources:
* **confCSV.properties :** Configuration de la position des .music et des dossiers de déplacement pour la réussite ou l’échec de la lecture de ces fichiers.
* **hibernate.cfg.xml :** Configuration de la base de données et du mapping Hibernate
* **log4j.properties :** Configuration de la journalisation.

TODO

Librairies utilisées
--------------------

TODO

* **log4j :** Pour la journalisation.
* **org.hsqldb :** Gestion de base de données.
* **org.hibernate :** Gestion de la persistence (Connexion à la base de donnée).
* **net.sf.opencsv :** Lecteur et parseur de fichiers CSV.
* **junit (4.11) :** Gestion des tests unitaires.
* **commons-io :** Permet de gérer aisément les flux de donnée, comme la gestion de fichier.
