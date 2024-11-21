package com.example.prototype;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static List<RequeteDeTravail> requetes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Utilisateur utilisateurCourant;


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
                        if ("resident".equals(utilisateurCourant.getRole())){
                             menuResident();
                        } else if ("intervenant".equals(utilisateurCourant.getRole())) {
                            gererRequetesIntervenant();
                        }
                    }else {
                        System.out.println("Échec de la connexion !");
                    }
                    break;
                case 3:
                    System.out.println("Système terminé.");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choixPrincipal != 3);

        System.out.println("Système terminé.");
    }

    // Affiche le menu principal
    private static int menuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Connexion en tant que résident");
        System.out.println("2. Connexion en tant qu'intervenant");
        System.out.println("3. Quitter");
        System.out.print("Choisissez une option : ");
        return scanner.nextInt();
    }

    // Initialiser les données : 3 utilisateurs (résidents et intervenants) et 3 requêtes
    private static void initialiserDonnees() {
        utilisateurs.add(new Utilisateur("1@hotmail.com", "123", "resident"));
        utilisateurs.add(new Utilisateur("2@hotmail.com", "123", "resident"));
        utilisateurs.add(new Utilisateur("3@hotmail.com", "123", "resident"));

        utilisateurs.add(new Utilisateur("1@gmail.com", "123", "intervenant"));
        utilisateurs.add(new Utilisateur("2@gmail.com", "123", "intervenant"));
        utilisateurs.add(new Utilisateur("3@gmail.com", "123", "intervenant"));

        requetes.add(new RequeteDeTravail("Réparer le lampadaire"));
        requetes.add(new RequeteDeTravail("Réparer un trou dans la rue"));
        requetes.add(new RequeteDeTravail("Nettoyer le parc"));
    }


    private static boolean connexion(int choixPrincipal) {
        String role = choixPrincipal == 1 ? "resident" : "intervenant";
        scanner.nextLine(); // Vider le tampon d'entrée
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();

        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.authentifier(email, motDePasse)) {
                if (utilisateur.getRole().equals(role)) {
                    utilisateurCourant = utilisateur;
                    System.out.println("Bienvenue, " + utilisateur.getRole() + " !");
                    return true;
                } else {
                    System.out.println("Erreur : Vous avez utilisé un compte de rôle incorrect. Essayez à nouveau.");
                    return false;
                }
            }
        }

        System.out.println("Échec de la connexion : email ou mot de passe incorrect.");
        return false;
    }


    private static void menuResident() {
        int choix;
        do {
            System.out.println("\nMenu Résident :");
            System.out.println("1. Soumettre une requête de travail");
            System.out.println("2. Consulter l'état des travaux");
            System.out.println("3. Voir mes requêtes");
            System.out.println("4. Choisir une candidature et fermer une requête");
            System.out.println("5. Retour au menu principal");
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
                    choisirCandidatureEtFermerRequete();
                    break;
                case 5:
                    System.out.println("Retour au menu principal ...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 5);
    }

    private static void choisirCandidatureEtFermerRequete() {
        List<RequeteDeTravail> requetesAvecCandidatures = requetes.stream()
                .filter(r -> !r.estFermee() && !r.getCandidatures().isEmpty())
                .toList();

        if (requetesAvecCandidatures.isEmpty()){
            System.out.println("Aucune requête avec des candidatures disponibles");
            return;
        }

        System.out.println("Requêtes avec des candidatures : ");
        for (RequeteDeTravail requete : requetesAvecCandidatures) {
            System.out.println("ID: " + requete.getId() + " , Description: " + requete.getDescription());
            System.out.println("Candidatures :");
            for (Utilisateur intervenant : requete.getCandidatures()) {
                System.out.println(" - " + intervenant.getEmail());
            }
            System.out.println("------------------------------");
        }

        System.out.print("Entrez l'ID de la requête que vous souhaitez fermer : ");
        int idRequete = scanner.nextInt();
        scanner.nextLine();

        RequeteDeTravail requete = requetes.stream()
                .filter(r -> r.getId() == idRequete && !r.estFermee() && !r.getCandidatures().isEmpty())
                .findFirst()
                .orElse(null);

        if (requete !=null) {
            System.out.println("Candidatures disponibles :");
            List<Utilisateur> candidatures = requete.getCandidatures();
            for (int i = 0; i < candidatures.size(); i++) {
                System.out.println((i + 1) + ". " + candidatures.get(i).getEmail());
            }
            System.out.print("Choisissez le numéro de la candidature à sélectionner : ");
            int choixCandidature = scanner.nextInt();
            scanner.nextLine();

            if (choixCandidature < 1 || choixCandidature > candidatures.size()) {
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
            System.out.println("La requête a été fermée avec le candidat : " + candidat.getEmail());
            if (!requete.getMessage().isEmpty()) {
                System.out.println("Message : " + requete.getMessage());
            }
        } else {
            System.out.println("Requête non trouvée ou sans candidatures disponibles.");
        }
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
        System.out.print("Entrez l'ID de la requête à laquelle vous voulez soumettre votre candidature: ");
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
                .filter(r -> r.getCandidatures().contains(utilisateurCourant) && !r.estFermee())
                .toList();

        if (canditatures.isEmpty()) {
            System.out.println("Vous n'avez aucune candidature active");
            return;
        }

        System.out.println("Vos candidatures actives :");
        for (RequeteDeTravail requete : canditatures) {
            System.out.println("ID: " + requete.getId() + "Description: " + requete.getDescription());
        }

        System.out.print("Entrez l'ID de la requête dont vous voulez retirez votre candidature : ");
        int idRequete = scanner.nextInt();
        scanner.nextLine();

        RequeteDeTravail requete = requetes.stream()
                .filter(r -> r.getId() == idRequete && r.getCandidatures().contains(utilisateurCourant))
                .findFirst()
                .orElse(null);

        if (requete != null) {
            requete.retirerCandidature(utilisateurCourant);
            System.out.println("Candidature retirée avec succès.");
        } else {
            System.out.println("Requête non trouvée ou candidature inexistante.");
        }
    }
    private static void voirCandidaturesIntervenant(){
        System.out.println("\n--- Vos Candidatures Actives ---");
        List<RequeteDeTravail> candidatures = requetes.stream()
                .filter(r -> r.getCandidatures().contains(utilisateurCourant) && !r.estFermee())
                .toList();

        if (candidatures.isEmpty()) {
            System.out.println("Vous n'avez aucune candidature active.");
            return;
        }

        for (RequeteDeTravail requete : candidatures) {
            System.out.println("ID: " + requete.getId() + ", Description: " + requete.getDescription());
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
            System.out.println("5. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choix = lireEntier();
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
                    System.out.println("Retour au menu principal ...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 5);
    }



    private static void creerRequeteTravail() {
        System.out.print("Entrez la description de la requête : ");
        String description = scanner.nextLine();
        try {
            URL url = new URL("http://localhost:7000/requetes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/X-www-form-urlencoded");
            connection.getOutputStream().write(("description=" + description).getBytes());

            if (connection.getResponseCode()==201){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = reader.readLine();
                System.out.println("Requête créée avec succès : " +response);
                reader.close();
            } else {
                System.out.println("Erreur lors de la création de la requête : " + connection.getResponseCode());
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
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode()== 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                        System.out.println("ID : " + travail.optString("id", "N/A"));
                        System.out.println("Arrondissement : " + travail.optString("boroughid", "N/A"));
                        System.out.println("Statut : " + travail.optString("currentstatus", "N/A"));
                        System.out.println("Motif : " + travail.optString("reason_category", "N/A"));
                        System.out.println("Intervenant : " + travail.optString("organizationname", "N/A"));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Aucun travail public trouvé.");
                }
            } else {
                System.out.println("Erreur lors de la récupération des travaux publics : " + connection.getResponseCode());
            }
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }



    // Consulter les entraves
    private static void consulterLesEntraves() {
        System.out.println("\nEntraves routières causées par les travaux en cours :");
        try {
            String apiUrl = "http://localhost:7000/entraves-publics";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                        System.out.println("ID Travail : " + entrave.optString("id_request", "N/A"));
                        System.out.println("Rue : " + entrave.optString("shortname", "N/A"));
                        System.out.println("Impact : " + entrave.optString("streetimpacttype", "N/A"));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Aucune entrave trouvée.");
                }
            } else {
                System.out.println("Erreur lors de la récupération des entraves : " + connection.getResponseCode());
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
            System.out.println("ID: " + requete.getId() + ", Description: " + requete.getDescription());
        }

        System.out.print("Entrez l'ID de la requête dont vous voulez confirmer la candidature : ");
        int idRequete = lireEntier();
        scanner.nextLine();

        RequeteDeTravail requete = requetesSelectionnees.stream()
                .filter(r -> r.getId() == idRequete)
                .findFirst()
                .orElse(null);

        if (requete != null) {
            requete.confirmerIntervenant();
            System.out.println("Candidature confirmée pour la requête : " + requete.getDescription());
        } else {
            System.out.println("Requête non trouvée ou candidature déjà confirmée.");
        }

    }
    private static int lireEntier() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.nextLine(); // Vide le buffer pour éviter une boucle infinie
            }
        }
    }

}