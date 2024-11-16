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

// Classe Utilisateur, représentant un résident ou un intervenant
class Utilisateur {
    private String email;
    private String motDePasse;
    private String role; // Rôle : "resident" ou "intervenant"

    public Utilisateur(String email, String motDePasse, String role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getRole() {
        return role;
    }

    public boolean authentifier(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }
}

// Classe RequeteDeTravail, représentant une demande de travail
class RequeteDeTravail {
    private static int compteurId = 1;
    private int id;
    private String description;
    private boolean estFermee;

    public RequeteDeTravail(String description) {
        this.id = compteurId++;
        this.description = description;
        this.estFermee = false;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean estFermee() {
        return estFermee;
    }

    public void fermer() {
        this.estFermee = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Fermée: " + estFermee;
    }
}

public class Main {
    private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static List<RequeteDeTravail> requetes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Utilisateur utilisateurCourant;

    public static void main(String[] args) {
        // Initialiser les données
        initialiserDonnees();

        int choixPrincipal;
        do {
            // Afficher le menu principal
            choixPrincipal = menuPrincipal();
            if (choixPrincipal == 1 || choixPrincipal == 2) {
                if (connexion(choixPrincipal)) {
                    // Afficher le menu selon le rôle
                    if ("resident".equals(utilisateurCourant.getRole())) {
                        menuResident();
                    } else if ("intervenant".equals(utilisateurCourant.getRole())) {
                        menuIntervenant();
                    }
                } else {
                    System.out.println("Échec de la connexion !");
                }
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

    // Fonction de connexion avec choix de rôle
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

    // Menu pour le résident
    private static void menuResident() {
        int choix;
        do {
            System.out.println("\nMenu Résident :");
            System.out.println("1. Soumettre une requête de travail");
            System.out.println("2. Consulter l'état des travaux");
            System.out.println("3. Voir mes requêtes");
            System.out.println("4. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();  // Vider le tampon d'entrée

            switch (choix) {
                case 1:
                    creerRequeteTravail();
                    break;
                case 2:
                    consulterEtatTravaux();  // Nouvelle méthode pour gérer les sous-options
                    break;
                case 3:
                    voirRequetesTravail();
                    break;
                case 4:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 4);
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

    // Menu pour l'intervenant
    private static void menuIntervenant() {
        int choix;
        do {
            System.out.println("\nMenu Intervenant :");
            System.out.println("1. Voir et gérer les requêtes");
            System.out.println("2. Retour au menu principal");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();  // Vider le tampon d'entrée

            if (choix == 1) {
                gererRequetesIntervenant(); // Sous-menu pour gérer les requêtes
            } else if (choix == 2) {
                System.out.println("Retour au menu principal...");
            } else {
                System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 2);
    }

    private static void gererRequetesIntervenant() {
        int choix;
        do {
            System.out.println("\nGérer les requêtes :");
            System.out.println("1. Voir toutes les requêtes");
            System.out.println("2. Choisir une requête à traiter");
            System.out.println("3. Retour au menu Intervenant");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    voirRequetesTravail();
                    break;
                case 2:
                    choisirRequeteTravail();
                    break;
                case 3:
                    System.out.println("Retour au menu Intervenant...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 3);
    }

    // Créer une nouvelle requête de travail
    private static void creerRequeteTravail() {
        System.out.print("Entrez la description de la requête : ");
        String description = scanner.nextLine();
        RequeteDeTravail nouvelleRequete = new RequeteDeTravail(description);
        requetes.add(nouvelleRequete);
        System.out.println("Requête de travail créée, ID : " + nouvelleRequete.getId());
    }

    // Voir toutes les requêtes de travail
    private static void voirRequetesTravail() {
        System.out.println("\nToutes les requêtes de travail :");
        for (RequeteDeTravail requete : requetes) {
            System.out.println(requete);
        }
    }

    // Choisir une requête de travail par un intervenant
    private static void choisirRequeteTravail() {
        System.out.print("Entrez l'ID de la requête à choisir : ");
        int idRequete = scanner.nextInt();
        scanner.nextLine();  // Vider le tampon d'entrée

        RequeteDeTravail requeteChoisie = null;
        for (RequeteDeTravail requete : requetes) {
            if (requete.getId() == idRequete && !requete.estFermee()) {
                requeteChoisie = requete;
                break;
            }
        }

        if (requeteChoisie != null) {
            System.out.println("Vous avez choisi la requête : " + requeteChoisie);
            System.out.print("Confirmez-vous votre choix ? (y/n) : ");
            String confirmation = scanner.nextLine();
            if ("y".equalsIgnoreCase(confirmation)) {
                requeteChoisie.fermer();
                System.out.println("La requête de travail a été terminée et fermée.");
            } else {
                System.out.println("La requête n'a pas été acceptée.");
            }
        } else {
            System.out.println("ID de requête invalide ou requête déjà fermée.");
        }
    }

    // Consulter les travaux en cours ou à venir
    private static void consulterTravauxEnCours() {
        // Consulter les travaux en cours ou à venir

        try {
            String apiUrl = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析 JSON 响应
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject result = jsonResponse.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");

            System.out.println("\nListe des travaux en cours ou à venir :");
            for (int i = 0; i < records.length(); i++) {
                JSONObject record = records.getJSONObject(i);

                String id = record.optString("id", "N/A");
                String boroughId = record.optString("boroughid", "N/A");
                String currentStatus = record.optString("currentstatus", "N/A");
                String reasonCategory = record.optString("reason_category", "N/A");
                String submitterCategory = record.optString("submittercategory", "N/A");
                String organizationName = record.optString("organizationname", "N/A");

                System.out.println("ID du travail: " + id +
                        ", Arrondissement: " + boroughId +
                        ", Statut actuel: " + currentStatus +
                        ", Motif du travail: " + reasonCategory +
                        ", Catégorie d'intervenant: " + submitterCategory +
                        ", Nom de l'intervenant: " + organizationName);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation des travaux : " + e.getMessage());
        }
    }



    // Consulter les entraves
    private static void consulterLesEntraves() {
        try {
            String apiUrl = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject result = jsonResponse.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");

            System.out.println("\nListe des entraves :");
            for (int i = 0; i < records.length(); i++) {
                JSONObject record = records.getJSONObject(i);

                String idRequest = record.optString("id_request", "N/A");
                String streetId = record.optString("streetid", "N/A");
                String shortName = record.optString("shortname", "N/A");
                String streetImpactType = record.optString("streetimpacttype", "N/A");

                System.out.println("ID du Travail: " + idRequest +
                        ", ID Rue: " + streetId +
                        ", Nom Rue: " + shortName +
                        ", Impact Rue: " + streetImpactType);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la consultation des entraves : " + e.getMessage());
        }
    }
}