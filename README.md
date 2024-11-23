MaVille
MaVille est une application conçue pour améliorer la gestion et la communication autour des travaux publics et privés à Montréal. L'application permet aux résidents de consulter les informations sur les travaux en cours et à venir, de recevoir des notifications personnalisées et de soumettre des suggestions ou des requêtes. L'objectif est de faciliter la coordination entre les différents acteurs (publics, privés et résidents) et de minimiser les perturbations causées par les travaux.

Organisation du Répertoire
Le projet est organisé comme suit :

Le dossier prototype[MV] contient un dossier diagrammes/ qui renferme les diagrammes de cas d'utilisation, d'activités, de classes et de séquence en format PNG. Il contient également un dossier src/ qui héberge le code source du projet. Dans src/main/java/, vous trouverez le code source principal de l'application, organisé en packages. Le package com/example/prototype/ contient les classes principales : Main.java, RequeteDeTravail.java, RestApiServer.java, Utilisateur.java, Intervenant.java, Projet.java et le fichier module-info.java.

Le dossier src/test/java/ contient les classes de tests unitaires utilisant JUnit. Le package com/example/prototype/ comprend les classes de test : MainTest.java, RequeteDeTravailTest.java, RetourFonctionTest.java, UtilisateurTest.java, IntervenantTest.java, ProjetTest.java et éventuellement un module-info.java si nécessaire.

Le fichier rapport.html est le rapport complet du projet "MaVille" comprenant l'analyse des exigences, la conception et une première implémentation. Le fichier pom.xml est le fichier de configuration Maven pour la gestion des dépendances et la compilation du projet. Enfin, le fichier README.md (ce fichier) contient les instructions pour installer, exécuter et tester l'application.

Installation
Prérequis
Pour exécuter ce projet, vous aurez besoin de Java JDK 16 ou supérieur, de Maven 3.6 ou supérieur, de Git (pour cloner le dépôt) et d'un accès Internet (pour télécharger les dépendances et accéder aux API de la Ville de Montréal).

Instructions
Cloner le dépôt GitHub du projet en exécutant la commande suivante dans votre terminal : git clone https://github.com/votre-utilisateur/MaVille.git.

Naviguer dans le répertoire du projet avec la commande : cd MaVille/prototype[MV].

Installer les dépendances et compiler le projet avec Maven en exécutant : mvn clean install.

Exécution de l'Application
Démarrer l'application
L'application est une application en ligne de commande. Pour la lancer, exécutez la commande suivante : mvn exec:java -Dexec.mainClass="com.example.prototype.Main".

Utilisation
Une fois l'application lancée, vous serez invité à vous connecter en tant que résident ou intervenant en entrant votre adresse courriel et votre mot de passe. Après la connexion, un menu principal s'affichera avec les actions possibles selon votre rôle.

Pour naviguer dans le menu, utilisez les numéros associés aux options. Vous pouvez revenir au menu principal à tout moment en sélectionnant l'option appropriée. Le menu est conçu pour ne pas dépasser trois niveaux d'imbrication afin de garantir une expérience utilisateur fluide.

Tests Unitaires
Exécution des tests
Pour exécuter les tests unitaires avec Maven, utilisez la commande : mvn test. Les résultats des tests seront affichés dans la console, et un rapport détaillé sera généré dans le dossier target/surefire-reports.

Couverture des tests
Chaque membre de l'équipe a écrit au moins trois tests unitaires avec JUnit. Les tests couvrent les méthodes clés de l'application, à l'exception des constructeurs, getters, setters et méthodes privées. Les tests se trouvent dans le répertoire src/test/java/com/example/prototype/.

Fonctionnalités Implémentées
Les fonctionnalités suivantes ont été implémentées :

Connexion en tant que résident et intervenant.
Consultation des travaux en cours ou à venir.
Consultation des entraves.
Soumission d'une requête de travail résidentiel.
Consultation de la liste des requêtes de travail.
Recherche de travaux par titre, type ou quartier.
Soumission d'un nouveau projet par un intervenant.
Mise à jour des informations d'un projet.
Intégration avec les API de la Ville de Montréal
L'application utilise les API fournies par la Ville de Montréal pour récupérer les données sur les travaux et les entraves en cours :

Liste des travaux en cours : https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b.
Liste des entraves causées par les travaux en cours : https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd.
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
Initialisation des données : L'application est initialisée avec au moins trois résidents, trois intervenants et trois requêtes de travail pour faciliter les tests.
Interface utilisateur : L'application est en ligne de commande avec des menus clairs et une navigation intuitive.
Gestion des erreurs : Les cas d'erreur et les cas limites sont traités et testés, assurant une robustesse de l'application.
Participation et Gestion de Projet
Commits réguliers : Chaque membre de l'équipe a participé activement au développement du projet avec des commits réguliers (au moins un par semaine).
Branches Git : L'utilisation de branches Git pour le développement du code source a été adoptée, permettant un développement parallèle et une intégration continue.
Gestion de projet avec issues : La gestion du projet a été réalisée en utilisant les issues de GitHub pour suivre les tâches, les fonctionnalités à implémenter et les bugs.
Déploiement
Release : Une release a été créée lors de la remise du projet, disponible sur le dépôt GitHub.
Dépôt GitHub : Lien vers le dépôt GitHub : https://github.com/votre-utilisateur/MaVille.
Instructions pour les Tests
Vérifiez que les prérequis sont installés (Java, Maven, Git).
Clonez le dépôt en exécutant : git clone https://github.com/votre-utilisateur/MaVille.git.
Naviguez dans le répertoire du projet : cd MaVille/prototype[MV].
Exécutez les tests avec la commande : mvn test.
Consultez les résultats des tests qui seront affichés dans la console et disponibles dans le dossier target/surefire-reports.

