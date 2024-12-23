package com.example.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une requête de travail dans l'application MaVille.
 * <p>
 * Cette classe encapsule les informations relatives à une demande de travail,
 * y compris son identifiant unique, le titre, la description, le type de travaux,
 * la durée espérée, le statut de fermeture, les candidatures des utilisateurs,
 * le candidat sélectionné, les messages associés, et la confirmation de l'intervenant.
 * </p>
 *
 * <p>
 * Les instances de cette classe sont utilisées pour gérer et suivre les différentes
 * requêtes de travail soumises par les utilisateurs, facilitant ainsi le processus
 * de sélection et de gestion des intervenants.
 * </p>
 *
 * @author
 *          Kamille Denault-Geoffroy
 *          Aya Elbroumi
 */
public class RequeteDeTravail {
    private static int compteurId = 1;
    private int id;
    private String titre;
    private String description;
    private TypeTravaux typeDeTravail;
    private int dureeEsperee;
    private boolean estFermee;
    private List<Utilisateur> candidatures;
    private Utilisateur candidatSelectionne;
    private String message;
    private boolean confirmationIntervenant;

    /**
     * Constructeur pour créer une nouvelle instance de {@code RequeteDeTravail}.
     *
     * @param titre        le titre de la requête de travail.
     * @param description  la description détaillée de la requête de travail.
     * @param typeDeTravail le type de travaux associés à la requête.
     * @param dureeEsperee la durée espérée pour la réalisation des travaux en jours.
     */
    public RequeteDeTravail(String titre, String description, TypeTravaux typeDeTravail, int dureeEsperee) {
        this.id = compteurId++;
        this.titre = titre;
        this.description = description;
        this.typeDeTravail = typeDeTravail;
        this.dureeEsperee = dureeEsperee;
        this.estFermee = false;
        this.candidatures = new ArrayList<>();
        this.candidatSelectionne = null;
        this.message = "";
        this.confirmationIntervenant = false;
    }
    /**
     * Constructeur par défaut pour créer une instance de {@code RequeteDeTravail}.
     * <p>
     * Utilisé principalement pour des besoins de nettoyage ou d'initialisation sans paramètres.
     * </p>
     *
     * @param nettoyerLeParc une chaîne de caractères indicative
     */
    public RequeteDeTravail(String nettoyerLeParc) {
    }
    /**
     * Obtient la confirmation de l'intervenant pour cette requête.
     *
     * @return {@code true} si l'intervenant a confirmé, {@code false} sinon.
     */
    public boolean isConfirmationIntervenant() {
        return confirmationIntervenant;
    }
    /**
     * Confirme l'intervenant pour cette requête.
     * <p>
     * Cette méthode marque la requête comme confirmée par l'intervenant.
     * </p>
     */
    public void confirmerIntervenant() {
        this.confirmationIntervenant = true;
    }
    /**
     * Obtient l'identifiant unique de la requête de travail.
     *
     * @return l'identifiant de la requête.
     */
    public int getId() {
        return id;
    }
    /**
     * Obtient la description de la requête de travail.
     *
     * @return la description de la requête.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Vérifie si la requête de travail est fermée.
     *
     * @return {@code true} si la requête est fermée, {@code false} sinon.
     */
    public boolean estFermee() {
        return estFermee;
    }
    /**
     * Ferme la requête de travail.
     * <p>
     * Cette méthode marque la requête comme fermée, empêchant ainsi toute modification ultérieure.
     * </p>
     */
    public void fermer() {
        this.estFermee = true;
    }
    /**
     * Obtient la liste des candidatures pour cette requête de travail.
     *
     * @return une {@link List} contenant les utilisateurs ayant soumis une candidature.
     */
    public List<Utilisateur> getCandidatures() {
        return candidatures;
    }
    /**
     * Obtient le message associé à cette requête de travail.
     *
     * @return le message de la requête.
     */

    public String getMessage() {
        return message;
    }

    /**
     * Définit le message associé à cette requête de travail.
     *
     * @param message le nouveau message à associer à la requête.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Ajoute une candidature à cette requête de travail.
     *
     * @param intervenant l'utilisateur intervenant à ajouter en tant que candidature.
     */
    public void ajouterCandidature(Utilisateur intervenant) {
        if (!candidatures.contains(intervenant)) {
            candidatures.add(intervenant);
        }
    }
    /**
     * Retire une candidature de cette requête de travail.
     *
     * @param intervenant l'utilisateur intervenant à retirer de la liste des candidatures.
     */
    public void retirerCandidature(Utilisateur intervenant) {
        candidatures.remove(intervenant);
    }
    /**
     * Obtient le candidat sélectionné pour cette requête de travail.
     *
     * @return l'utilisateur candidat sélectionné, ou {@code null} si aucun candidat n'est sélectionné.
     */
    public Utilisateur getCandidatSelectionne() {
        return candidatSelectionne;
    }
    /**
     * Définit le candidat sélectionné pour cette requête de travail.
     *
     * @param candidat l'utilisateur candidat à sélectionner.
     */
    public void setCandidatSelectionne(Utilisateur candidat) {
        this.candidatSelectionne = candidat;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la requête de travail.
     *
     * @return une chaîne décrivant l'identifiant, la description et le statut de fermeture de la requête.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Fermée: " + estFermee;
    }

    /**
     * Définit une nouvelle description pour la requête de travail.
     *
     * @param nouvelleDescription la nouvelle description à attribuer à la requête.
     */
    public void setDescription(String nouvelleDescription) {
        this.description = nouvelleDescription;
    }

    /**
     * Définit une nouvelle durée espérée pour la réalisation des travaux.
     *
     * @param dureeEsperee la nouvelle durée espérée en jours.
     */
    public void setDureeEsperee(int dureeEsperee) {
        this.dureeEsperee = dureeEsperee;
    }

    /**
     * Obtient la durée espérée pour la réalisation des travaux.
     *
     * @return la durée espérée en jours.
     */
    public int getDureeEsperee() {
        return dureeEsperee;
    }
    /**
     * Définit le type de travaux associés à cette requête de travail.
     *
     * @param typeDeTravail le nouveau type de travaux à attribuer à la requête.
     */
    public void setTypeDeTravail(TypeTravaux typeDeTravail) {
        this.typeDeTravail = typeDeTravail;
    }
    /**
     * Obtient le type de travaux associés à cette requête de travail.
     *
     * @return le type de travaux.
     */
    public TypeTravaux getTypeDeTravail() {
        return typeDeTravail;
    }
    /**
     * Obtient le titre de la requête de travail.
     *
     * @return le titre de la requête.
     */
    public String getTitre() {
        return titre;
    }
    /**
     * Définit le titre de la requête de travail.
     *
     * @param titre le nouveau titre à attribuer à la requête.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
}