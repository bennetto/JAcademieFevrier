Examen Fevrier 
================

Auteurs
-------
* Benjamin BONNETTO
* Jean-Michel POTHELUNE

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
 
### Lancement de l'application sous glassfish
Commencez par installer votre serveur glassfish 3.1, et integrez le dans eclipse. (http://blog.paumard.org/tutoriaux/eclipse-glassfish-ejb/)
Dans la vue Server (Window > show view > Servers), clic droit sur votre serveur Glassfish > Add and Remove... 

Ajoutez le projet JAcademieFevrier et appuyez sur Finish.

clic droit sur votre serveur Glassfish > Start... 

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
Spring est un conteneur léger permetant la gestion de dépendance. Il s'occupe du cycle de vie des différents objets dont il a la charge et injecte les éventuelles dépendences. Ces librairies web et mvc permettent la gestion de ces objets lors du requêtage web, en apportant un support aux services javaEE web. 

### glassfish
Glassfish est un serveur d'application web capable d'intégrer des projets javaEE web. Il représente l'environnement dans lequel évoluera notre application web.


Structure du projet
-----------------------------
### Couches
* **Couche service** : Cette couche renferme l'intelligence du projet. C'est dans cette couche que vous pouvez trouvez les fonctions qui permette de manipuler les objets.
* **Couche d’accès au donnée** (DAO) : Cette couche contient toutes les fonctions pour communiquer avec le base de donnée ( dans ce projet via hibernate). 
* **Couche métier** (BO) : Cette couche contient les classes qui permettes de stocker les donnée métier.  Ce ne sont que des données brutes, et il n'y a aucune (ou très peu) d'intelligence.
* **Couche controlleur** : Cette couche contient les classes en charge de la redirection et du traitement de réponses aux requetes html.


### Main
La mise à jour à partir de fichier ".music" fait appel a deux services interfacés. Le premier gère la lecture et le controle du fichier ".music", et en extrait des données qui seront stockées temporairement dans le programme. Le second permet la mise à jour des objets métiers. 
Un dernier service permettera le déplacement du fichier traité dans un dossier indiqué en paramètre.

### Dépendance dans la projet
Spring gère la gestion du cycle de vie des differents services, DAOs et objets mis en oeuvre lors d'une requete. La gestion par annotation combine la simplicité et la décorélation des objets injectés.


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



### org.jacademie.examenFevrier.bo (Buiseness object)
Contient les classes métiers.

### org.jacademie.examenFevrier.dao
Contient les interfaces des Data Access Objects

### org.jacademie.examenFevrier.dao.impl
Implementation des DAO (Hibernate)

### org.jacademie.examenFevrier.dao.impl.mapping
Fichiers de mapping Objets métiers - DB (Hibernate)

### org.jacademie.examenFevrier.services
Contient les interfaces des differents services:
* **MusicDataProcessService :** Permet l'execution du processus d'extraction d'information d'un fichier csv et la mise a jour de la base de donnée en conséquence.
* __FileMoverService :__  Permet le déplacement d'un fichier.
* **MusicDataExtractorService :** Permet l'extraction d'informations (MusicData) sur les musiques a partir d'un lecteur (Reader).
* **MusicDataUpdaterService :** Permet la mise a jour des objets métiers à partir d'informations (MusicData) extraites ou fournies.
* **MusicData :** Permet le stockage temporaire d'informations sur les musiques.
* **MusicDataException :** Soulevée par MusicDataExtractorService et MusicDataUpdaterService lorsque les informations lues ou enregistrées sont éronnées.
* Les autres services permettent la gestion directe des différents types d'objets.

### org.jacademie.examenFevrier.services.impl
Implementation des services:

### org.jacademie.examenFevrier.utils
Rassemble diférents outils utilisée par les services.
* **PersistenceManager :** Interface les outils traditionnels de gestion de base SQL.
* **HibernateManage :** Implémentation de l'interface PersitenceManager pour hibernate.

### org.jacademie.examenFevrier.tests
Classes de test des Services et DAO de l'application.
* **DAOsTest :** Test la bonne sauvegarde et récupération de tous les objets.
* **MusicDataUpdaterService :** Test unitaire (rudimentaire) de la classe MusicDataUpdater

### org.jacademie.examenFevrier.controller
Contient les controleurs qui s'occuperont de diriger et de répondre aux requetes HTML.

### Ressources:
* **confCSV.properties :** Configuration de la position des .music et des dossiers de déplacement pour la réussite ou l’échec de la lecture de ces fichiers.
* **hibernate.cfg.xml :** Configuration de la base de données et du mapping Hibernate
* **log4j.properties :** Configuration de la journalisation.

### webapp/WEB-INF
* **web.xml** Configuration du contexte du serveur web
* **aplicationContext.xml** Configuration du contexte Spring
* **mvc-dispatcher-servlet.xml** Configuration de la jonction web et spring

### webapp/WEB-INF/pages
Contient les diferents templates jsp du projet
* **list-artiste.jsp :** Template pour l'affichage de la liste des artistes enregistrés
* **artiste.jsp :** Description d'un artiste et édition de ses albums
* **album.jsp :* Description d'un album et édition des ses chansons
Librairies utilisées
--------------------


* **log4j :** Pour la journalisation.
* **org.hsqldb :** Gestion de base de données.
* **org.hibernate :** Gestion de la persistence (Connexion à la base de donnée).
* **net.sf.opencsv :** Lecteur et parseur de fichiers CSV.
* **junit (4.11) :** Gestion des tests unitaires.
* **commons-io :** Permet de gérer aisément les flux de donnée, comme la gestion de fichier.
* **spring-core** et **spring-context :** Conteneur léger permetant l'injection de dépendances
* **spring-web** et **spring-webmvc :** Librairie pour l'utilisation de spring au sein d'un serveur web.
