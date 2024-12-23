package com.example.prototype;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Gère la persistance des données de l'application MaVille.
 * <p>
 * Cette classe permet de sauvegarder et de charger les données relatives aux utilisateurs,
 * aux requêtes de travail, aux projets, et aux entraves dans un fichier JSON.

 *
 * @author
 *         Kamille Denault-Geoffroy
 */
public class GestionnaireDonnees {

    private static final String FICHIER_DONNEES = "donnees.json";
    /**
     * Sauvegarde toutes les données de l'application dans un fichier JSON.
     *
     * Cette méthode prend en paramètre les listes d'utilisateurs, de requêtes de travail,
     * de projets et d'entraves, les convertit en objets JSON structurés, et les écrit
     * dans le fichier {@code donnees.json}.
     *
     * @param listeUtilisateurs la liste des utilisateurs à sauvegarder.
     * @param listeRequetes la liste des requêtes de travail à sauvegarder.
     * @param listeProjets la liste des projets à sauvegarder.
     * @param listeEntraves la liste des entraves à sauvegarder.
     */
    public static void sauvegarderToutesDonnees(
            List<Utilisateur> listeUtilisateurs,
            List<RequeteDeTravail> listeRequetes,
            List<Projet> listeProjets,
            List<Entrave> listeEntraves
    ) {
        try {
            JSONObject racine = new JSONObject();

            JSONArray tableauUtilisateurs = new JSONArray();
            for (Utilisateur u : listeUtilisateurs) {
                JSONObject jsonUtilisateur = new JSONObject();
                jsonUtilisateur.put("nomComplet", u.getNomComplet());
                jsonUtilisateur.put("email", u.getEmail());
                jsonUtilisateur.put("motDePasse", u.getMotDePasse());
                if (u instanceof Resident r) {
                    jsonUtilisateur.put("type", "Resident");
                    jsonUtilisateur.put("dateNaissance", r.getDateNaissance());
                    jsonUtilisateur.put("adresse", r.getAdresse());
                } else if (u instanceof Intervenant i) {
                    jsonUtilisateur.put("type", "Intervenant");
                    jsonUtilisateur.put("typeIntervenant", i.getTypeIntervenant().toString());
                    jsonUtilisateur.put("identifiantVille", i.getIdentifiantVille());
                } else {
                    jsonUtilisateur.put("type", "Utilisateur");
                }
                tableauUtilisateurs.put(jsonUtilisateur);
            }
            racine.put("utilisateurs", tableauUtilisateurs);

            JSONArray tableauRequetes = new JSONArray();
            for (RequeteDeTravail r : listeRequetes) {
                JSONObject jsonRequete = new JSONObject();
                jsonRequete.put("id", r.getId());
                jsonRequete.put("titre", r.getTitre());
                jsonRequete.put("description", r.getDescription());
                jsonRequete.put("typeDeTravail", r.getTypeDeTravail().toString());
                jsonRequete.put("dureeEsperee", r.getDureeEsperee());
                jsonRequete.put("estFermee", r.estFermee());
                JSONArray candidatures = new JSONArray();
                for (Utilisateur cand : r.getCandidatures()) {
                    candidatures.put(cand.getEmail());
                }
                jsonRequete.put("candidatures", candidatures);
                if (r.getCandidatSelectionne() != null) {
                    jsonRequete.put("candidatSelectionne", r.getCandidatSelectionne().getEmail());
                }
                jsonRequete.put("message", r.getMessage());
                jsonRequete.put("confirmationIntervenant", r.isConfirmationIntervenant());
                tableauRequetes.put(jsonRequete);
            }
            racine.put("requetes", tableauRequetes);

            JSONArray tableauProjets = new JSONArray();
            for (Projet p : listeProjets) {
                JSONObject jsonProjet = new JSONObject();
                jsonProjet.put("titreProjet", p.getTitreProjet());
                jsonProjet.put("statut", p.getStatutPrevu().toString());
                jsonProjet.put("typeTravaux", p.getTypeTravaux().toString());
                jsonProjet.put("quartiersAffectes", new JSONArray(p.getQuartiersAffectes()));
                jsonProjet.put("ruesAffectees", new JSONArray(p.getRuesAffectees()));
                jsonProjet.put("dateDebut", p.getDateDebut());
                jsonProjet.put("dateFin", p.getDateFin());
                jsonProjet.put("descriptionProjet", p.getDescriptionProjet());
                jsonProjet.put("horaireTravaux", p.getHoraireTravaux());
                jsonProjet.put("intervenantId", p.getIntervenantId());
                tableauProjets.put(jsonProjet);
            }
            racine.put("projets", tableauProjets);

            JSONArray tableauEntraves = new JSONArray();
            for (Entrave e : listeEntraves) {
                JSONObject jsonEntrave = new JSONObject();
                jsonEntrave.put("travailAssocie", e.getTravailAssocié());
                jsonEntrave.put("rues", new JSONArray(e.getRues()));
                tableauEntraves.put(jsonEntrave);
            }
            racine.put("entraves", tableauEntraves);

            try (FileWriter writer = new FileWriter(FICHIER_DONNEES)) {
                writer.write(racine.toString(2));
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }
    /**
     * Charge toutes les données de l'application depuis le fichier JSON.
     *
     * Cette méthode lit le fichier {@code donnees.json}, parse son contenu,
     * et reconstruit les listes d'utilisateurs, de requêtes de travail,
     * de projets et d'entraves.
     *
     * @return un objet {@link RegistreDonnees} contenant toutes les données chargées.
     */
    public static RegistreDonnees chargerToutesDonnees() {
        RegistreDonnees registre = new RegistreDonnees();
        File fichier = new File(FICHIER_DONNEES);
        if (!fichier.exists()) {
            return registre;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            StringBuilder contenuJson = new StringBuilder();
            String ligne;
            while ((ligne = br.readLine()) != null) {
                contenuJson.append(ligne);
            }
            JSONObject racine = new JSONObject(contenuJson.toString());
            JSONArray utilisateursJson = racine.optJSONArray("utilisateurs");
            if (utilisateursJson != null) {
                for (int i = 0; i < utilisateursJson.length(); i++) {
                    JSONObject obj = utilisateursJson.getJSONObject(i);
                    String type = obj.optString("type", "Utilisateur");
                    String nomComplet = obj.optString("nomComplet", "");
                    String email = obj.optString("email", "");
                    String motDePasse = obj.optString("motDePasse", "");
                    if ("Resident".equals(type)) {
                        String dateNaissance = obj.optString("dateNaissance", "");
                        String adresse = obj.optString("adresse", "");
                        Resident r = new Resident(nomComplet, dateNaissance, email, motDePasse, adresse);
                        registre.listeUtilisateurs.add(r);
                    } else if ("Intervenant".equals(type)) {
                        String t = obj.optString("typeIntervenant", "ENTREPRISEPUBLIC");
                        int identifiant = obj.optInt("identifiantVille", 0);
                        TypeIntervenant ti = TypeIntervenant.valueOf(t);
                        Intervenant inter = new Intervenant(nomComplet, ti, email, motDePasse, identifiant);
                        registre.listeUtilisateurs.add(inter);
                    } else {
                        Utilisateur user = new Utilisateur(nomComplet, email, motDePasse);
                        registre.listeUtilisateurs.add(user);
                    }
                }
            }
            JSONArray requetesJson = racine.optJSONArray("requetes");
            if (requetesJson != null) {
                for (int i = 0; i < requetesJson.length(); i++) {
                    JSONObject obj = requetesJson.getJSONObject(i);
                    int id = obj.optInt("id", 0);
                    String titre = obj.optString("titre", "");
                    String description = obj.optString("description", "");
                    String typeTravaux = obj.optString("typeDeTravail", "TRAVAUXROUTIERS");
                    int duree = obj.optInt("dureeEsperee", 0);
                    boolean fermee = obj.optBoolean("estFermee", false);
                    RequeteDeTravail r = new RequeteDeTravail(titre, description, TypeTravaux.valueOf(typeTravaux), duree);
                    if (fermee) {
                        r.fermer();
                    }
                    JSONArray cands = obj.optJSONArray("candidatures");
                    if (cands != null) {
                        for (int j = 0; j < cands.length(); j++) {
                            String emailCand = cands.getString(j);
                            Utilisateur interv = retrouverUtilisateurParEmail(registre.listeUtilisateurs, emailCand);
                            if (interv != null) {
                                r.ajouterCandidature(interv);
                            }
                        }
                    }
                    String emailCandidat = obj.optString("candidatSelectionne", "");
                    if (!emailCandidat.isEmpty()) {
                        Utilisateur c = retrouverUtilisateurParEmail(registre.listeUtilisateurs, emailCandidat);
                        r.setCandidatSelectionne(c);
                    }
                    r.setMessage(obj.optString("message", ""));
                    boolean confirmation = obj.optBoolean("confirmationIntervenant", false);
                    if (confirmation) {
                        r.confirmerIntervenant();
                    }
                    registre.listeRequetes.add(r);
                }
            }
            JSONArray projetsJson = racine.optJSONArray("projets");
            if (projetsJson != null) {
                for (int i = 0; i < projetsJson.length(); i++) {
                    JSONObject obj = projetsJson.getJSONObject(i);
                    String titreProj = obj.optString("titreProjet", "");
                    String statut = obj.optString("statut", "PREVU");
                    String typeTravaux = obj.optString("typeTravaux", "TRAVAUXROUTIERS");
                    JSONArray arrQuartiers = obj.optJSONArray("quartiersAffectes");
                    JSONArray arrRues = obj.optJSONArray("ruesAffectees");
                    int dateDebut = obj.optInt("dateDebut", 0);
                    int dateFin = obj.optInt("dateFin", 0);
                    String descriptionProj = obj.optString("descriptionProjet", "");
                    String horaire = obj.optString("horaireTravaux", "");
                    String interId = obj.optString("intervenantId", "");
                    List<String> quartiers = transformerEnListe(arrQuartiers);
                    List<String> rues = transformerEnListe(arrRues);
                    Projet p = new Projet(
                            titreProj,
                            TypeTravaux.valueOf(typeTravaux),
                            quartiers,
                            rues,
                            dateDebut,
                            dateFin,
                            descriptionProj,
                            horaire,
                            interId
                    );
                    p.setStatutPrevu(Statut.valueOf(statut));
                    registre.listeProjets.add(p);
                }
            }
            JSONArray entravesJson = racine.optJSONArray("entraves");
            if (entravesJson != null) {
                for (int i = 0; i < entravesJson.length(); i++) {
                    JSONObject obj = entravesJson.getJSONObject(i);
                    String travailAssocie = obj.optString("travailAssocie", "");
                    JSONArray arr = obj.optJSONArray("rues");
                    List<String> rues = transformerEnListe(arr);
                    Entrave e = new Entrave(travailAssocie, rues);
                    registre.listeEntraves.add(e);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement des données : " + e.getMessage());
        }
        return registre;
    }
    /**
     * Classe interne représentant un registre de données chargé.
     * <p>
     * Cette classe encapsule les listes d'utilisateurs, de requêtes de travail,
     * de projets et d'entraves chargées depuis le fichier JSON.
     * </p>
     */
    public static class RegistreDonnees {
        /**
         * Liste des utilisateurs chargés.
         */
        public List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        /**
         * Liste des requêtes de travail chargées.
         */
        public List<RequeteDeTravail> listeRequetes = new ArrayList<>();
        /**
         * Liste des projets chargés.
         */
        public List<Projet> listeProjets = new ArrayList<>();
        /**
         * Liste des entraves chargées.
         */
        public List<Entrave> listeEntraves = new ArrayList<>();
    }
    /**
     * Transforme un objet {@link JSONArray} en une liste de chaînes de caractères.
     *
     * @param array le {@link JSONArray} à transformer.
     * @return une {@link List} contenant les éléments du tableau JSON.
     */
    private static List<String> transformerEnListe(JSONArray array) {
        List<String> resultat = new ArrayList<>();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                resultat.add(array.optString(i));
            }
        }
        return resultat;
    }
    /**
     * Recherche et retourne un utilisateur dans une liste en fonction de son email.
     *
     * @param liste la liste des utilisateurs dans laquelle effectuer la recherche.
     * @param email l'email de l'utilisateur à retrouver.
     * @return l'objet {@link Utilisateur} correspondant à l'email fourni, ou {@code null} si non trouvé.
     */
    private static Utilisateur retrouverUtilisateurParEmail(List<Utilisateur> liste, String email) {
        for (Utilisateur u : liste) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}
