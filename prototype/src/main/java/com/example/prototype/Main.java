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

    public void setDescription(String nouvelleDescription) {
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
        // Démarrage du serveur REST
        RestApiServer server = new RestApiServer(requetes);
        server.start();

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
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 5);
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
            scanner.nextLine();

            switch (choix) {
                case 1:
                    gererRequetesIntervenant();
                    break;
                case 2:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        } while (choix != 3);
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




}