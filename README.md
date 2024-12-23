MaVille
MaVille est une application conçue pour améliorer la gestion et la communication autour des travaux publics et privés à Montréal. L'application permet aux résidents de consulter les informations sur les travaux en cours et à venir, de recevoir des notifications personnalisées, de soumettre des requêtes de travail et d’interagir avec les intervenants. L’objectif est de faciliter la coordination entre les différents acteurs (publics, privés et résidents) et de minimiser les perturbations causées par les travaux.

Organisation du Répertoire
Le projet est organisé comme suit :

prototype[MV]/
diagrammes/ : Contient les diagrammes de cas d'utilisation, d'activités, de classes et de séquence en format PNG.
src/
main/java/com.example.prototype/ : Contient le code source principal de l'application.
Main.java
RequeteDeTravail.java
RestApiServer.java
Utilisateur.java
Intervenant.java
Projet.java
Entrave.java
GestionnaireDonnees.java
PlageHoraire.java
TypeIntervenant.java
TypeTravaux.java
Statut.java
module-info.java
test/java/com.example.prototype/ : Contient les classes de tests unitaires utilisant JUnit.
GestionnaireDonneesTest.java
FiltreTravauxTest.java
EntraveTest.java
MainTest.java
RequeteDeTravailTest.java
RetourFonctionTest.java
UtilisateurTest.java
IntervenantTest.java
ProjetTest.java
rapport.html : Rapport complet du projet "MaVille" comprenant l'analyse des exigences, la conception et une première implémentation.
pom.xml : Fichier de configuration Maven pour la gestion des dépendances et la compilation du projet.


Installation
Prérequis
Pour exécuter ce projet, vous aurez besoin de Java JDK 16 ou supérieur, de Maven 3.6 ou supérieur, de Git (pour cloner le dépôt) et d'un accès Internet (pour télécharger les dépendances et accéder aux API de la Ville de Montréal).

Instructions
Cloner le dépôt GitHub du projet en exécutant la commande suivante dans votre terminal : git clone https://github.com/kamilled/IFT2255-Devoir1-Groupe-36.git.

Naviguer dans le répertoire du projet avec la commande : cd IFT2255-Devoir1-Groupe-36/prototype[MV].

Installer les dépendances et compiler le projet avec Maven en exécutant : mvn clean install.

Exécution de l'Application
Démarrer l'application
L'application est une application en ligne de commande. Pour la lancer, exécutez la commande suivante : mvn exec:java -Dexec.mainClass="com.example.prototype.Main".

Utilisation
Connexion : Une fois l'application lancée, vous serez invité à vous connecter en tant que résident ou intervenant en entrant votre adresse courriel et votre mot de passe.

Menu Principal : Après la connexion, un menu principal s'affichera avec les actions possibles selon votre rôle.

Navigation : Pour naviguer dans le menu, utilisez les numéros associés aux options. Vous pouvez revenir au menu principal à tout moment en sélectionnant l'option appropriée. Le menu est conçu pour ne pas dépasser trois niveaux d'imbrication afin de garantir une expérience utilisateur fluide.

Tests Unitaires
Exécution des tests
Pour exécuter les tests unitaires avec Maven, utilisez la commande : mvn test. Les résultats des tests seront affichés dans la console, et un rapport détaillé sera généré dans le dossier target/surefire-reports.

Couverture des tests
Chaque membre de l'équipe a écrit au moins trois tests unitaires avec JUnit. LEs tests se trouvent dans le répertoire src/test/java/com.example.test

Rapport de couverture 
Un rapport de couverture des tests est généré avec JaCoCo. Pour le générer, utilisez la commande suivante :
mvn jacoco:report
Le rapport HTML sera disponible dans le dossier target/site/jacoco/index.html

Fonctionnalités Implémentées
Les fonctionnalités suivantes ont été implémentées :
Connexion en tant que résident et intervenant.
Consultation des travaux en cours ou à venir.
Consultation des entraves.
Filtrage des travaux par arrondissement (borough).
Filtrage des entraves par rue (shortname).
Soumission d'une requête de travail résidentiel.
Consultation de la liste des requêtes de travail.
Recherche de travaux par titre, type ou quartier.
Soumission d'un nouveau projet par un intervenant.
Mise à jour des informations d'un projet.
Persistance des données via la classe GestionnaireDonnees.
Gestion des candidatures (soumission, retrait, confirmation).
Intégration avec les API de la Ville de Montréal pour récupérer les données sur les travaux et les entraves en cours.

Persistance des données
L'application utilise la classe GestionnaireDonnees pour gérer la lecture et l'écriture des données. Les données (utilisateurs, requêtes, projets, entraves) sont sauvegardées dans un fichier JSON (donnees.json) à chaque fermeture de l'application rechargées au démarrage si le fichier existe.

Sauvegarde des Données
Les données sont sauvegardées en appelant la méthode suivante avant de quitter l'application :
GestionnaireDonnees.sauvegarderToutesDonnees(utilisateurs, requetes, projets, entraves);

Chargement des Données
Au démarrage de l'application, les données sont chargées avec la méthode suivante :
GestionnaireDonnees.RegistreDonnees registreCharge = GestionnaireDonnees.chargerToutesDonnees();
Si le fichier donnees.json existe, les données sont injectées dans les listes globales. Sinon, les données par défaut sont initialisées.


Conception
Architecture
L'application est conçue selon le modèle MVC (Modèle-Vue-Contrôleur) pour séparer les préoccupations et faciliter la maintenance. Une API REST a également été conçue pour supporter les requêtes HTTP (GET, POST, PUT, DELETE) en utilisant le framework Javalin, ce qui améliore l'interopérabilité avec d'autres systèmes.

Diagramme de Classes
Le diagramme de classes est disponible dans le dossier diagrammes/ et présente les classes principales, leurs attributs et leurs relations.

Diagrammes de Séquence
Trois diagrammes de séquence illustrent les cas d'utilisation clés, incluant les cas limites et les erreurs :
Consulter les entraves.
Soumettre une requête de travail.
Consulter la liste des requêtes de travail.

Justification des Choix de Design
Lors de la conception de l'application, nous avons pris en compte les principes d'abstraction, de couplage faible, de cohésion élevée et d'encapsulation pour favoriser l'évolutivité et l'intégration future de nouvelles fonctionnalités. L'utilisation du modèle MVC permet une séparation claire des responsabilités, ce qui facilite la maintenance et les tests. L'architecture REST a été adoptée pour améliorer l'interopérabilité avec d'autres systèmes et clients.

Notes sur l'Implémentation
Initialisation des données :
L'application est initialisée avec au moins cinq résidents, cinq intervenants, cinq requêtes de travail, cinq projets et cinq entraves pour faciliter les tests et démontrer les fonctionnalités.
Interface utilisateur :
L'application est en ligne de commande avec des menus clairs et une navigation intuitive.
Gestion des erreurs :
Les cas d'erreur et les cas limites sont traités et testés, assurant une robustesse de l'application.
Persistance des données :
Les données sont persistées dans un fichier JSON (donnees.json) pour conserver l'état de l'application entre les sessions.

Déploiement 
Dépot GitHub: https://github.com/Kamilled/IFT2255-Devoir1-Groupe-36

Instructions pour les Tests
Vérifiez que les prérequis sont installés (Java, Maven, Git).
Clonez le dépôt en exécutant :
git clone https:https://github.com/Kamilled/IFT2255-Devoir1-Groupe-36
Naviguez dans le répertoire du projet :
cd IFT2255-Devoir1-Groupe-36/prototype[MV]
Exécutez les tests avec la commande :
mvn test
Consultez les résultats des tests qui seront affichés dans la console et disponibles dans le dossier target/surefire-reports.
Générez le rapport de couverture des tests avec JaCoCo :
mvn jacoco:report
