package com.example.prototype;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static List<RequeteDeTravail> requetes = new ArrayList<>();
    private static List<Projet> projets = new ArrayList<>();
    private static List<Entrave> entraves = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Utilisateur utilisateurCourant;
    private static List<Notification> notifications = new ArrayList<>();


    public static void main(String[] args) {
        initialiserDonnees();
        RestApiServer server = new RestApiServer(requetes);
        server.start();

        int choixPrincipal;
        do {
            choixPrincipal = menuPrincipal();
            switch (choixPrincipal) {
                case 1:
                case 2:
                    if (connexion(choixPrincipal)){
                        if (utilisateurCourant instanceof Resident) {
                            menuResident();
                        } else if (utilisateurCourant instanceof Intervenant) {
                            gererRequetesIntervenant();
                        }
                    }else {
                        System.out.println("Échec de la connexion !");
                    }
                    break;
                case 3:
                    int inscription = inscription();
                    if (inscription == 0) {
                        System.out.println("Inscription échouée,"
                                + " veuillez réessayer.");
                    } else if (inscription ==1 || inscription ==2) {
                        if (inscription ==1) {menuResident();
                        } else {gererRequetesIntervenant();}
                    }
                    break;
                case 4:
                    System.out.println("Système terminé.");
                    break;
                default:
                    System.out.println("Option invalide, veuillez"
                            +" réessayer.");
            }
        } while (choixPrincipal != 4);

        System.out.println("Système terminé.");
    }

    // Affiche le menu principal
    public static int menuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Connexion en tant que résident");
        System.out.println("2. Connexion en tant qu'intervenant");
        System.out.println("3. S'inscrire");
        System.out.println("4. Quitter");
        System.out.print("Choisissez une option : ");
        return scanner.nextInt();
    }

    // Initialiser les données : 3 utilisateurs (résidents et intervenants) 
    // et 3 requêtes
    public static void initialiserDonnees() {

        Utilisateur resident1 = new Resident("nom1","aaaa/mm/jj",
                "1@hotmail.com", "123", "adresse1");
        Utilisateur resident2 = new Resident("nom2","aaaa/mm/jj",
                "2@hotmail.com", "123", "adresse2");
        Utilisateur resident3 = new Resident("nom3","aaaa/mm/jj",
                "3@hotmail.com", "123", "adresse3");
        Utilisateur resident4 = new Resident("nom4","aaaa/mm/jj",
                "4@hotmail.com", "123", "adresse4");
        Utilisateur resident5 = new Resident("nom5","aaaa/mm/jj",
                "5@hotmail.com", "123", "adresse5");

        Utilisateur intervenant1 = new Intervenant("nom1",
                TypeIntervenant.ENTREPRISEPUBLIC, "1@gmail.com", "123", 00000000);
        Utilisateur intervenant2 = new Intervenant("nom2",
                TypeIntervenant.ENTREPRENEURPRIVE, "2@gmail.com", "123", 11111111);
        Utilisateur intervenant3 = new Intervenant("nom3",
                TypeIntervenant.ENTREPRISEPUBLIC, "3@gmail.com", "123", 22222222);
        Utilisateur intervenant4 = new Intervenant("nom4",
                TypeIntervenant.PARTICULIER, "4@gmail.com", "123", 33333333);
        Utilisateur intervenant5 = new Intervenant("nom5",
                TypeIntervenant.ENTREPRENEURPRIVE, "5@gmail.com", "123", 44444444);

        RequeteDeTravail requete1 =
                new RequeteDeTravail("Réparer le lampadaire",
                        "Le lampadaire est tombé.", TypeTravaux.TRAVAUXROUTIERS, 5);

        RequeteDeTravail requete2 =
                new RequeteDeTravail("Réparer un trou dans la rue",
                        "Trou de 1 mètre de diamètre.", TypeTravaux.TRAVAUXROUTIERS, 2);

        RequeteDeTravail requete3 =
                new RequeteDeTravail("Nettoyer le parc",
                        "De nombreuse tâches brunes sont sur les modules.",
                        TypeTravaux.ENTRETIENURBAIN, 4);

        RequeteDeTravail requete4 =
                new RequeteDeTravail("Installer un banc",
                        "À l'intersection Ducharme et Beauchamp, plusieurs enfant attendent"
                                +" l'autobus scolaire et un banc leur permetteront de s'asseoir.",
                        TypeTravaux.ENTRETIENPAYSAGER, 1);

        RequeteDeTravail requete5 =
                new RequeteDeTravail("Peindre les clôtures",
                        "Peinture de la clotûre d'une coure de 8 mètres sur 9 mètre.",
                        TypeTravaux.ENTRETIENPAYSAGER, 4);

        Projet projet1 = new Projet("Projet 1", TypeTravaux.TRAVAUXROUTIERS,
                List.of("Quartier 1", "Quartier 2"), List.of("Rue 1", "Rue 2"),
                20260101, 202600430, "Description du projet 1", "Horaire 1",
                "Intervenant 1");
        Entrave entrave1 = new Entrave(projet1.getTitreProjet(),
                projet1.getRuesAffectees()); // Entrave associée au projet 1

        Projet projet2 = new Projet("Projet 2",
                TypeTravaux.TRAVAUXGAZELECTRICITE,
                List.of("Quartier 3", "Quartier 4"), List.of("Rue 3", "Rue 4"),
                20250101, 20251002, "Description du projet 2",
                "Horaire 2", "Intervenant 2");
        Entrave entrave2 = new Entrave(projet2.getTitreProjet(),
                projet2.getRuesAffectees()); // Entrave associée au projet 2

        Projet projet3 = new Projet("Projet 3",
                TypeTravaux.CONSTRUCTIONRENOVATION, List.of("Quartier 5",
                "Quartier 6"), List.of("Rue 5", "Rue 6"), 20250201,
                20250505, "Description du projet 3", "Horaire 3",
                "Intervenant 3");
        Entrave entrave3 = new Entrave(projet3.getTitreProjet(),
                projet3.getRuesAffectees()); // Entrave associée au projet 3

        Projet projet4 = new Projet("Projet 4", TypeTravaux.ENTRETIENPAYSAGER,
                List.of("Quartier 7", "Quartier 8"), List.of("Rue 7", "Rue 8"),
                20250708, 20260720, "Description du projet 4", "Horaire 4",
                "Intervenant 4");
        Entrave entrave4 = new Entrave(projet4.getTitreProjet(),
                projet4.getRuesAffectees()); // Entrave associée au projet 4

        Projet projet5 = new Projet("Projet 5",
                TypeTravaux.TRAVAUXTRANSPORTPUBLIC, List.of("Quartier 9",
                "Quartier 10"), List.of("Rue 9", "Rue 10"), 20270214,
                20300110, "Description du projet 5", "Horaire 5", "Intervenant 5");
        Entrave entrave5 = new Entrave(projet5.getTitreProjet(),
                projet5.getRuesAffectees()); // Entrave associée au projet 5

        utilisateurs.add(resident1);
        utilisateurs.add(resident2);
        utilisateurs.add(resident3);
        utilisateurs.add(resident4);
        utilisateurs.add(resident5);

        utilisateurs.add(intervenant1);
        utilisateurs.add(intervenant2);
        utilisateurs.add(intervenant3);
        utilisateurs.add(intervenant4);
        utilisateurs.add(intervenant5);

        requetes.add(requete1);
        requetes.add(requete2);
        requetes.add(requete3);
        requetes.add(requete4);
        requetes.add(requete5);

        projets.add(projet1);
        projets.add(projet2);
        projets.add(projet3);
        projets.add(projet4);
        projets.add(projet5);

        entraves.add(entrave1);
        entraves.add(entrave2);
        entraves.add(entrave3);
        entraves.add(entrave4);
        entraves.add(entrave5);
    }


    private static boolean connexion(int choixPrincipal) {
        scanner.nextLine(); // Vider le tampon d'entrée
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();

        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.authentifier(email, motDePasse)) {
                if (utilisateur instanceof  Resident
                        || utilisateur instanceof Intervenant) {
                    utilisateurCourant = utilisateur;

                    System.out.println("Bienvenue, " +
                            utilisateur.getNomComplet() + " !");

                    return true;
                } else {
                    System.out.println("Erreur : Vous avez utilisé un compte "
                            +"de rôle incorrect. Essayez à nouveau.");
                    return false;
                }
            }
        }

        System.out.println("Échec de la connexion : email ou "
                +"mot de passe incorrect.");
        return false;
    }

    private static int inscription() {
        scanner.nextLine();
        int role = 0;

        // Loop jusqu'à ce qu'un rôle valide soit entré
        while (true) {
            System.out.print("Êtes-vous un résident ou un intervenant ? ");
            System.out.print("Entrez 1 pour résident ou 2 pour intervenant :");

            try {
                role = scanner.nextInt();
                if (role == 1 || role == 2) {
                    break; // Valide, sortir de la boucle
                } else {
                    System.out.println("Rôle invalide, veuillez entrer 1 "
                            +"pour résident ou 2 pour intervenant");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide, veuillez entrer un "
                        +"nombre  entre 1 ou 2");
                scanner.next();
            }
        }

        scanner.nextLine();

        if (role == 1) {
            System.out.print("Entrez votre nom complet: ");
            String nomComplet = scanner.nextLine();

            System.out.print("Entrez votre date de naissance (aaaa/mm/jj): ");
            String dateNaissance = scanner.nextLine();

            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine();

            System.out.print("Entrez votre mot de passe : ");
            String motDePasse = scanner.nextLine();

            System.out.print("Entrez votre adresse résidentielle : ");
            String adresse = scanner.nextLine();

            Utilisateur nouvelUtilisateur =
                    new Resident(nomComplet, dateNaissance, email, motDePasse,adresse);
            utilisateurs.add(nouvelUtilisateur);
            utilisateurCourant = nouvelUtilisateur;



            System.out.println("Utilisateur créé avec succès!");
            return 1;



        } else if (role == 2) {
            System.out.print("Entrez votre nom complet: ");
            String nomComplet = scanner.nextLine();
            int typeIntervenantInt = 0;

            // Loop jusqu'à ce qu'un type d'intervenant valide soit entré
            while (true) {
                System.out.print("Quel type d'intervenant êtes-vous ? "
                        +"(entrez 1 pour entreprise public, 2 pour entrepreneur "
                        +"privé ou 3 pour particulier) : ");
                try {
                    typeIntervenantInt = scanner.nextInt();
                    if (typeIntervenantInt >= 1 && typeIntervenantInt <= 3) {
                        break; // Valide, sortir de la boucle
                    } else {
                        System.out.println("Type d'intervenant invalide,"
                                +" veuillez entrer 1, 2 ou 3");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide, veuillez entrer"
                            +" un nombre (1, 2 ou 3).");
                    scanner.next();
                }
            }

            TypeIntervenant typeIntervenant =
                    TypeIntervenant.values()[typeIntervenantInt - 1];

            scanner.nextLine();

            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine();

            System.out.print("Entrez votre mot de passe : ");
            String motDePasse = scanner.nextLine();

            int identifiantVille = 0;

            // Loop jusqu'à ce qu'un identifiant de ville valide soit entré
            while (true) {
                System.out.print("Entrez votre identifiant de la ville "
                        +"(code à 8 chiffres) : ");

                try {
                    identifiantVille = scanner.nextInt();
                    if (String.valueOf(identifiantVille).length() == 8) {
                        break; // Valide, sortir de la boucle
                    } else {
                        System.out.println("Identifiant de la ville invalide,"
                                +" veuillez entrer un code à 8 chiffres.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrée invalide. Veuillez "
                            +"entrer un nombre à 8 chiffres.");
                    scanner.next();
                }
            }

            Utilisateur nouvelUtilisateur =
                    new Intervenant(nomComplet, typeIntervenant,
                            email, motDePasse, identifiantVille);

            utilisateurs.add(nouvelUtilisateur);
            utilisateurCourant = nouvelUtilisateur;

            System.out.println("Utilisateur créé avec succès!");
            return 2;

        }
        return 0;
    }

    private static void menuResident() {
        int choix;
        do {
            System.out.println("\nMenu Résident :");
            System.out.println("1. Soumettre une requête de travail");
            System.out.println("2. Consulter l'état des travaux");
            System.out.println("3. Voir mes requêtes");
            System.out.println("4. Voir mes notifications");
            System.out.println("5. Modifier ma plage horaire");
            System.out.println("6. Déconnexion");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    creerRequeteTravail();
                    break;
                case 2:
                    consulterEtatTravaux();
                    break;
                case 3:
                    voirRequetesTravail();
                    break;
                case 4:
                    voirNotifications();
                    break;
                case 5:
                    menuPlageHoraire();
                    break;
                case 6:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 6);

    }

    public static void menuPlageHoraire() {
        int choix;
        Scanner scanner = new Scanner(System.in);


        do {
            System.out.println("\nMa plage horaire :");
            System.out.println("1. Modifier plage horaire");
            System.out.println("2. Consulter plage horaire");
            System.out.println("3. Ajouter une plage horaire");
            System.out.println("4. Retour");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    if (((Resident) utilisateurCourant).isPlageHoraireEmpty()) {
                        System.out.println("Aucune plage horaire définie.");
                        menuPlageHoraire();
                    } else {
                        System.out.println("Quelle plage horaire voulez-vous "
                                + "modifier? (1 pour Lundi, 2 pour Mardi, 3 pour "
                                +"Mercredi, 4 pour Jeudi, 5 pour Vendredi, 6 pour "
                                +"Samedi, 7 pour Dimanche) : ");
                        String choixJour = scanner.nextLine();
                        int jour = Integer.parseInt(choixJour);
                        if (jour >= 1 && jour <= 7) {
                            System.out.print("Entrez votre plage horaire "
                                    +"pour ce jour (format HH:MM-HH:MM) : ");

                            String horaire = scanner.nextLine();

                            ((Resident) utilisateurCourant).getPlageHoraire().
                                    modifierPlageHoraire(jour, horaire);

                            System.out.println("La plage horaire a été "
                                    +"modifiée avec succès.");

                            menuPlageHoraire();
                        } else {
                            System.out.println("Jour invalide, veuillez"
                                    +" réessayer.");
                            menuPlageHoraire();
                        }
                    }
                    break;
                case 2:

                    if (((Resident) utilisateurCourant).isPlageHoraireEmpty()) {
                        System.out.println("Aucune plage horaire définie.");
                        menuPlageHoraire();
                    } else {
                        System.out.println("\n" +
                                ((Resident) utilisateurCourant).
                                        getPlageHoraire().toString());

                        menuPlageHoraire();
                    }
                    break;
                case 3:
                    PlageHoraire tmpPlageHoraire =
                            new PlageHoraire("","","","","","","");
                    ((Resident) utilisateurCourant).setPlageHoraire(tmpPlageHoraire);

                    ((Resident) utilisateurCourant).getPlageHoraire().
                            nouvellePlageHoraire(scanner);

                    System.out.println("Plage horaire ajoutée avec succès.");
                    menuPlageHoraire();
                    break;
                case 4:
                    menuResident();
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 4);
    }

    private static void choisirCandidatureEtFermerRequete() {
        List<RequeteDeTravail> requetesAvecCandidatures = requetes.stream()
                .filter(r -> !r.estFermee() && !r.getCandidatures().isEmpty())
                .toList();

        if (requetesAvecCandidatures.isEmpty()){
            System.out.println("Aucune requête avec des candidatures"
                    +" disponibles");
            return;
        }

        System.out.println("Requêtes avec des candidatures : ");
        for (RequeteDeTravail requete : requetesAvecCandidatures) {
            System.out.println("ID: " + requete.getId() +
                    " , Description: " + requete.getDescription());

            System.out.println("Candidatures :");

            for (Utilisateur intervenant : requete.getCandidatures()) {
                System.out.println(" - " + intervenant.getEmail());
            }
            System.out.println("------------------------------");
        }

        System.out.print("Entrez l'ID de la requête que vous souhaitez "
                +"fermer : ");

        int idRequete = scanner.nextInt();
        scanner.nextLine();

        RequeteDeTravail requete = requetes.stream()
                .filter(r -> r.getId() == idRequete && !r.estFermee()
                        && !r.getCandidatures().isEmpty())

                .findFirst()
                .orElse(null);

        if (requete !=null) {
            System.out.println("Candidatures disponibles :");
            List<Utilisateur> candidatures = requete.getCandidatures();
            for (int i = 0; i < candidatures.size(); i++) {
                System.out.println((i + 1) + ". " + candidatures.
                        get(i).getEmail());
            }
            System.out.print("Choisissez le numéro de la candidature "
                    +"à sélectionner : ");
            int choixCandidature = scanner.nextInt();
            scanner.nextLine();

            if (choixCandidature < 1
                    || choixCandidature > candidatures.size()) {
                System.out.println("Choix invalide.");
                return;
            }

            Utilisateur candidat = candidatures.get(choixCandidature - 1);
            System.out.print("Voulez-vous ajouter un message ? (oui/non) : ");
            String reponse = scanner.nextLine().trim().toLowerCase();
            if (reponse.equals("oui") || reponse.equals("o")) {
                System.out.print("Entrez votre message : ");
                String message = scanner.nextLine().trim();
                requete.setMessage(message);
            }
            requete.setCandidatSelectionne(candidat);
            requete.fermer();
            System.out.println("La requête a été fermée avec le candidat : "
                    + candidat.getEmail());

            if (!requete.getMessage().isEmpty()) {
                System.out.println("Message : " + requete.getMessage());
            }
        } else {
            System.out.println("Requête non trouvée ou sans candidatures "
                    +"disponibles.");
        }
    }

    private static void voirNotifications() {
        System.out.println("\nVos Notifications :");
        notifications.stream()
                .filter(n -> n.getDestinataire().equals(utilisateurCourant))
                .forEach(notification -> {
                   
                    System.out.println(notification.getContenu());


                    if (notification.getContenu().contains("a été mis à jour")) {
                        String projectTitle = notification.getContenu().split(" ")[3]; // 提取项目标题
                        Projet project = getProjectByTitle(projectTitle); // 根据项目标题获取项目对象

                        if (project != null) {

                            System.out.println("\nDétails du projet :");
                            System.out.println("Titre: " + project.getTitreProjet());
                            System.out.println("Type: " + project.getTypeTravaux());
                            System.out.println("Quartiers: " + String.join(", ", project.getQuartiers()));
                            System.out.println("Rues Affectées: " + String.join(", ", project.getRuesAffectees()));
                            System.out.println("Date début: " + project.getDateDebut());
                            System.out.println("Date fin: " + project.getDateFin());
                            System.out.println("Description: " + project.getDescription());
                            System.out.println("Horaire: " + project.getHoraire());
                            System.out.println("Intervenant: " + project.getIntervenant());
                        }
                    }
                });
    }


    private static Projet getProjectByTitle(String title) {
        for (Projet projet : projets) {
            if (projet.getTitreProjet().equals(title)) {
                return projet;
            }
        }
        return null;
    }



    private static Statut getProjectStatus(String projectTitle) {
        for (Projet projet : projets) {
            if (projet.getTitreProjet().equals(projectTitle)) {
                return projet.getStatut();
            }
        }
        return null;
    }

    private static void consulterEtatTravaux() {
        int choix;
        do {
            System.out.println("\nConsulter l'état des travaux :");
            System.out.println("1. Voir les travaux en cours");
            System.out.println("2. Voir les entraves");
            System.out.println("3. Retour au menu Résident");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    consulterTravauxEnCours();
                    break;
                case 2:
                    consulterLesEntraves();
                    break;
                case 3:
                    System.out.println("Retour au menu Résident...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 3);
    }


    private static void soumettreCandidature() {
        voirRequetesTravail();
        System.out.print("Entrez l'ID de la requête à laquelle vous voulez "
                +"soumettre votre candidature: ");

        int idRequete=scanner.nextInt();
        scanner.nextLine();

        RequeteDeTravail requete = requetes.stream()
                .filter(r ->r.getId() == idRequete && !r.estFermee())
                .findFirst()
                .orElse(null);
        if (requete != null) {
            requete.ajouterCandidature(utilisateurCourant);
            System.out.println("Candidature soumise avec succès.");
        } else {
            System.out.println("Requête non trouvée ou déjà fermée.");
        }
    }

    private static void retirerCandidature() {
        List<RequeteDeTravail> canditatures = requetes.stream()
                .filter(r -> r.getCandidatures().contains(utilisateurCourant)
                        && !r.estFermee())
                .toList();

        if (canditatures.isEmpty()) {
            System.out.println("Vous n'avez aucune candidature active");
            return;
        }

        System.out.println("Vos candidatures actives :");
        for (RequeteDeTravail requete : canditatures) {
            System.out.println("ID: " + requete.getId() + "Description: "
                    + requete.getDescription());
        }

        System.out.print("Entrez l'ID de la requête dont vous voulez"
                +" retirez votre candidature : ");
        int idRequete = scanner.nextInt();
        scanner.nextLine();

        RequeteDeTravail requete = requetes.stream()
                .filter(r -> r.getId() == idRequete && r.getCandidatures().
                        contains(utilisateurCourant))
                .findFirst()
                .orElse(null);

        if (requete != null) {
            requete.retirerCandidature(utilisateurCourant);
            System.out.println("Candidature retirée avec succès.");
        } else {
            System.out.println("Requête non trouvée ou candidature "
                    +"inexistante.");
        }
    }
    private static void voirCandidaturesIntervenant(){
        System.out.println("\n--- Vos Candidatures Actives ---");
        List<RequeteDeTravail> candidatures = requetes.stream()
                .filter(r -> r.getCandidatures().
                        contains(utilisateurCourant) && !r.estFermee())
                .toList();

        if (candidatures.isEmpty()) {
            System.out.println("Vous n'avez aucune candidature active.");
            return;
        }

        for (RequeteDeTravail requete : candidatures) {
            System.out.println("ID: " + requete.getId() + ", Description: "
                    + requete.getDescription());
        }
    }


    private static void gererRequetesIntervenant() {
        int choix;
        do {
            System.out.println("\nGérer les requêtes :");
            System.out.println("1. Soumettre une candidature à une requête");
            System.out.println("2. Retirer une candidature");
            System.out.println("3. Voir mes candidatures actives");
            System.out.println("4. Confirmer une candidature");
            System.out.println("5. Soumettre un projet");
            System.out.println("6. Modifier le statut d'un projet");
            System.out.println("7. Déconnexion");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    soumettreCandidature();
                    break;
                case 2:
                    retirerCandidature();
                    break;
                case 3:
                    voirCandidaturesIntervenant();
                    break;
                case 4:
                    confirmerCandidature();
                    break;
                case 5:
                    soumettreProjet();
                    break;
                case 6:
                    modifierStatutProjet();
                    break;
                case 7:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 7);
    }
    private static void soumettreProjet() {
        System.out.println("\n--- Soumettre un Projet ---");
        System.out.print("Entrez le titre du projet : ");
        String titre = scanner.nextLine();
        System.out.print("Entrez la description : ");
        String description = scanner.nextLine();

        System.out.println("Entrez le type de travaux (options disponibles : ");
        for (TypeTravaux type : TypeTravaux.values()) {
            System.out.print(type.name() + " ");
        }
        System.out.println("):");
        String typeTravauxInput = scanner.nextLine();

        TypeTravaux typeTravaux;
        try {
            typeTravaux = TypeTravaux.valueOf(typeTravauxInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Type de travaux invalide. Veuillez réessayer.");
            return;
        }

        // Demander les nouveaux champs (Quartiers, Rues Affectées, Date début, Date fin, Horaire)
        System.out.print("Entrez les quartiers concernés (séparés par des virgules) : ");
        String quartiersInput = scanner.nextLine();
        List<String> quartiers = Arrays.asList(quartiersInput.split("\\s*,\\s*"));

        System.out.print("Entrez les rues affectées (séparées par des virgules) : ");
        String ruesAffecteesInput = scanner.nextLine();
        List<String> ruesAffectees = Arrays.asList(ruesAffecteesInput.split("\\s*,\\s*"));

        System.out.print("Entrez la date de début (format yyyyMMdd) : ");
        String dateDebutInput = scanner.nextLine();

        System.out.print("Entrez la date de fin (format yyyyMMdd) : ");
        String dateFinInput = scanner.nextLine();

        System.out.print("Entrez l'horaire (par exemple 9h-17h) : ");
        String horaire = scanner.nextLine();

        // Créer un projet avec les nouvelles informations
        Projet projet = new Projet(titre, typeTravaux, quartiers, ruesAffectees,
                Integer.parseInt(dateDebutInput), Integer.parseInt(dateFinInput),
                description, horaire, utilisateurCourant.getNomComplet());

        // Ajouter le projet à la liste des projets
        projets.add(projet);

        // Envoyer une notification à tous les résidents
        utilisateurs.stream()
                .filter(u -> u instanceof Resident)
                .forEach(resident -> notifications.add(new Notification("Nouveau Projet",
                        "Un nouveau projet a été soumis : " + titre, resident)));

        System.out.println("Projet soumis avec succès et notifications envoyées.");
    }


    private static void modifierStatutProjet() {
        System.out.println("\n--- Modifier le Statut d'un Projet ---");
        System.out.println("Projets disponibles :");

        // Affichage des projets avec leurs détails
        for (int i = 0; i < projets.size(); i++) {
            Projet projet = projets.get(i);
            System.out.println((i + 1) + ". " + projet.getTitreProjet());
            System.out.println("   Type: " + projet.getTypeTravaux());
            System.out.println("   Quartiers: " + String.join(", ", projet.getQuartiers()));
            System.out.println("   Rues Affectées: " + String.join(", ", projet.getRuesAffectees()));
            System.out.println("   Date début: " + projet.getDateDebut());
            System.out.println("   Date fin: " + projet.getDateFin());
            System.out.println("   Description: " + projet.getDescription());
            System.out.println("   Horaire: " + projet.getHoraire());
            System.out.println("   Intervenant: " + projet.getIntervenant());
        }

        System.out.print("Choisissez le numéro du projet : ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        if (choix < 1 || choix > projets.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        Projet projet = projets.get(choix - 1);

        // Afficher les détails du projet sélectionné
        System.out.println("\n--- Détails du Projet Choisi ---");
        System.out.println("Titre: " + projet.getTitreProjet());
        System.out.println("Type: " + projet.getTypeTravaux());
        System.out.println("Quartiers: " + String.join(", ", projet.getQuartiers()));
        System.out.println("Rues Affectées: " + String.join(", ", projet.getRuesAffectees()));
        System.out.println("Date début: " + projet.getDateDebut());
        System.out.println("Date fin: " + projet.getDateFin());
        System.out.println("Description: " + projet.getDescription());
        System.out.println("Horaire: " + projet.getHoraire());
        System.out.println("Intervenant: " + projet.getIntervenant());

        Statut nouveauStatut = null;
        boolean statutValide = false;
        while (!statutValide) {
            System.out.print("Entrez le nouveau statut (PREVU, ENCOURS, SUSPENDU, TERMINE) : ");
            String statutSaisi = scanner.nextLine().toUpperCase();

            try {
                nouveauStatut = Statut.valueOf(statutSaisi);
                statutValide = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Statut invalide. Veuillez entrer un statut valide parmi : PREVU, ENCOURS, SUSPENDU, TERMINE.");
            }
        }

        projet.setStatut(nouveauStatut); // Mise à jour du statut du projet

        // Créer un message avec les informations du projet et du statut
        String projectDetails = "Titre: " + projet.getTitreProjet() + "\n" +
                "Type: " + projet.getTypeTravaux() + "\n" +
                "Quartiers: " + String.join(", ", projet.getQuartiers()) + "\n" +
                "Rues Affectées: " + String.join(", ", projet.getRuesAffectees()) + "\n" +
                "Date début: " + projet.getDateDebut() + "\n" +
                "Date fin: " + projet.getDateFin() + "\n" +
                "Description: " + projet.getDescription() + "\n" +
                "Horaire: " + projet.getHoraire() + "\n" +
                "Intervenant: " + projet.getIntervenant();

        String notificationMessage = "Le projet " + projet.getTitreProjet() + " a été mis à jour.\n" +
                "Nouveau Statut: " + projet.getStatut() + "\n" + projectDetails;

        // Envoyer une notification à tous les résidents
        utilisateurs.stream()
                .filter(u -> u instanceof Resident)
                .forEach(resident -> notifications.add(new Notification("Mise à jour du Projet", notificationMessage, resident)));

        System.out.println("Statut du projet mis à jour et notifications envoyées.");
    }

    private static void creerRequeteTravail() {
        System.out.print("Entrez la description de la requête : ");
        String description = scanner.nextLine();
        try {
            URL url = new URL("http://localhost:7000/requetes");
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/X-www-form-urlencoded");
            connection.getOutputStream().write(("description="
                    + description).getBytes());

            if (connection.getResponseCode()==201){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                System.out.println("Requête créée avec succès : " +response);
                reader.close();
            } else {
                System.out.println("Erreur lors de la création de la requête :"
                        + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void voirRequetesTravail() {
        System.out.println("\nToutes les requêtes de travail :");
        for (RequeteDeTravail requete : requetes) {
            System.out.println(requete);
        }
    }


    private static void consulterTravauxEnCours() {
        // Consulter les travaux en cours ou à venir
        System.out.println("\nTravaux en cours ou à venir :");
        // Afficher les travaux internes
        System.out.println("\nTravaux internes :");
        for (RequeteDeTravail requete : requetes) {
            System.out.println("ID : " + requete.getId());
            System.out.println("Description : " + requete.getDescription());
            System.out.println("Fermée : " + requete.estFermee());
            System.out.println("------------------------");
        }

        // Ajouter les travaux publics
        System.out.println("\nTravaux publics :");
        try {

            String apiUrl = "http://localhost:7000/travaux-publics";
            URL url = new URL(apiUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode()== 200){
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON et afficher les travaux
                JSONArray travauxPublics = new JSONArray(response.toString());
                if (travauxPublics.length() > 0) {
                    for (int i = 0; i < travauxPublics.length(); i++) {
                        JSONObject travail = travauxPublics.getJSONObject(i);
                        System.out.println("ID : "
                                + travail.optString("id", "N/A"));

                        System.out.println("Arrondissement : "
                                + travail.optString("boroughid", "N/A"));

                        System.out.println("Statut : "
                                + travail.optString("currentstatus", "N/A"));

                        System.out.println("Motif : "
                                + travail.optString("reason_category", "N/A"));

                        System.out.println("Intervenant : "
                                + travail.optString("organizationname", "N/A"));

                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Aucun travail public trouvé.");
                }
            } else {
                System.out.println(
                        "Erreur lors de la récupération des travaux publics : "
                                + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }



    // Consulter les entraves
    private static void consulterLesEntraves() {
        System.out.println("\nEntraves routières causées par "
                +"les travaux en cours :");
        try {
            String apiUrl = "http://localhost:7000/entraves-publics";
            URL url = new URL(apiUrl);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();


                JSONArray entraves = new JSONArray(response.toString());
                if (entraves.length() > 0) {
                    for (int i = 0; i < entraves.length(); i++) {
                        JSONObject entrave = entraves.getJSONObject(i);
                        System.out.println("ID Travail : "
                                + entrave.optString("id_request", "N/A"));
                        System.out.println("Rue : "
                                + entrave.optString("shortname", "N/A"));
                        System.out.println("Impact : "
                                + entrave.optString("streetimpacttype", "N/A"));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Aucune entrave trouvée.");
                }
            } else {
                System.out.println("Erreur lors de la récupération des entraves : "
                        + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void confirmerCandidature() {
        System.out.println("\n--- Confirmer une Candidature ---");
        List<RequeteDeTravail> requetesSelectionnees = requetes.stream()
                .filter(r -> r.getCandidatSelectionne() != null &&
                        r.getCandidatSelectionne().equals(utilisateurCourant) &&
                        !r.isConfirmationIntervenant())
                .toList();
        if (requetesSelectionnees.isEmpty()) {
            System.out.println("Aucune candidature à confirmer.");
            return;
        }

        System.out.println("Candidatures à confirmer :");
        for (RequeteDeTravail requete : requetesSelectionnees) {
            System.out.println("ID: " + requete.getId() + ", Description: "
                    + requete.getDescription());
        }

        System.out.print("Entrez l'ID de la requête dont vous voulez "
                +"confirmer la candidature : ");
        int idRequete = lireEntier();
        scanner.nextLine();

        RequeteDeTravail requete = requetesSelectionnees.stream()
                .filter(r -> r.getId() == idRequete)
                .findFirst()
                .orElse(null);

        if (requete != null) {
            requete.confirmerIntervenant();
            System.out.println("Candidature confirmée pour la requête : "
                    + requete.getDescription());
        } else {
            System.out.println("Requête non trouvée ou candidature "
                    +"déjà confirmée.");
        }

    }
    private static int lireEntier() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez entrer un nombre valide.");
                // Vide le buffer pour éviter une boucle infinie
                scanner.nextLine();
            }
        }
    }

}