package com.example.prototype;

import java.util.List;
/**
 * Représente un projet de travaux dans l'application MaVille.
 * <p>
 * Cette classe encapsule les informations relatives à un projet, y compris son titre,
 * le type de travaux, les quartiers et rues affectés, les dates de début et de fin,
 * ainsi que la description et l'horaire des travaux. Elle permet également de gérer
 * le statut du projet et d'associer un intervenant via son identifiant.
 * </p>
 *
 * <p>
 * Les instances de cette classe sont utilisées pour suivre et gérer les différents
 * projets de travaux au sein de l'application, facilitant ainsi la planification
 * et le suivi des interventions.
 * </p>
 *
 * @author
 *         Aya Elbroumi
 */
public class Projet {

    private Statut statut;
    private String titreProjet;
    private TypeTravaux typeTravaux;
    private List<String> quartiersAffectes;
    private List<String> ruesAffectees;
    private int dateDebut;
    private int dateFin;
    private String descriptionProjet;
    private String horaireTravaux;
    private String intervenantId;

    /**
     * Constructeur pour créer une nouvelle instance de {@code Projet}.
     *
     * @param titreProjet        le titre du projet.
     * @param typeTravaux        le type de travaux effectués dans le cadre du projet.
     * @param quartiersAffectes  la liste des quartiers affectés par le projet.
     * @param ruesAffectees      la liste des rues affectées par le projet.
     * @param dateDebut          la date de début des travaux (format YYYYMMDD).
     * @param dateFin            la date de fin des travaux (format YYYYMMDD).
     * @param descriptionProjet  une description détaillée du projet.
     * @param horaireTravaux     les horaires prévus pour les travaux.
     * @param intervenantId      l'identifiant de l'intervenant associé au projet.
     */
    public Projet( String titreProjet, TypeTravaux typeTravaux, List<String> quartiersAffectes, List<String> ruesAffectees, int dateDebut, int dateFin,
                  String descriptionProjet, String horaireTravaux, String intervenantId) {
        this.statut = Statut.PREVU;
        this.titreProjet = titreProjet;
        this.typeTravaux = typeTravaux;
        this.quartiersAffectes = quartiersAffectes;
        this.ruesAffectees = ruesAffectees;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.descriptionProjet = descriptionProjet;
        this.horaireTravaux = horaireTravaux;
        this.intervenantId = intervenantId;
    }

    /**
     * Obtient le statut actuel du projet.
     *
     * @return le statut du projet.
     */
    public Statut getStatutPrevu() {
        return statut;
    }
    /**
     * Définit le statut prévu du projet.
     *
     * @param statutPrevu le nouveau statut à attribuer au projet.
     */
    public void setStatutPrevu(Statut statutPrevu) {
        this.statut = statutPrevu;
    }
    /**
     * Obtient le titre du projet.
     *
     * @return le titre du projet.
     */
    public String getTitreProjet() {
        return titreProjet;
    }
    /**
     * Définit le titre du projet.
     *
     * @param titreProjet le nouveau titre à attribuer au projet.
     */
    public void setTitreProjet(String titreProjet) {
        this.titreProjet = titreProjet;
    }
    /**
     * Obtient le type de travaux effectués dans le cadre du projet.
     *
     * @return le type de travaux.
     */
    public TypeTravaux getTypeTravaux() {
        return typeTravaux;
    }

    /**
     * Définit le type de travaux effectués dans le cadre du projet.
     *
     * @param typeTravaux le nouveau type de travaux à attribuer au projet.
     */
    public void setTypeTravaux(TypeTravaux typeTravaux) {
        this.typeTravaux = typeTravaux;
    }

    /**
     * Obtient la liste des quartiers affectés par le projet.
     *
     * @return la liste des quartiers affectés.
     */
    public List<String> getQuartiersAffectes() {
        return quartiersAffectes;
    }
    /**
     * Définit la liste des quartiers affectés par le projet.
     *
     * @param quartiersAffectes la nouvelle liste de quartiers affectés.
     */
    public void setQuartiersAffectes(List<String> quartiersAffectes) {
        this.quartiersAffectes = quartiersAffectes;
    }
    /**
     * Obtient la liste des rues affectées par le projet.
     *
     * @return la liste des rues affectées.
     */
    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    /**
     * Définit la liste des rues affectées par le projet.
     *
     * @param ruesAffectees la nouvelle liste de rues affectées.
     */
    public void setRuesAffectees(List<String> ruesAffectees) {
        this.ruesAffectees = ruesAffectees;
    }
    /**
     * Obtient la date de début des travaux.
     *
     * @return la date de début au format YYYYMMDD.
     */
    public int getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début des travaux.
     *
     * @param dateDebut la nouvelle date de début au format YYYYMMDD.
     */
    public void setDateDebut(int dateDebut) {
        this.dateDebut = dateDebut;
    }
    /**
     * Obtient la date de fin des travaux.
     *
     * @return la date de fin au format YYYYMMDD.
     */
    public int getDateFin() {
        return dateFin;
    }

    /**
     * Définit la date de fin des travaux.
     *
     * @param dateFin la nouvelle date de fin au format YYYYMMDD.
     */
    public void setDateFin(int dateFin) {
        this.dateFin = dateFin;
    }
    /**
     * Obtient la description détaillée du projet.
     *
     * @return la description du projet.
     */
    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    /**
     * Définit la description détaillée du projet.
     *
     * @param descriptionProjet la nouvelle description du projet.
     */
    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    /**
     * Obtient les horaires prévus pour les travaux.
     *
     * @return les horaires des travaux.
     */
    public String getHoraireTravaux() {
        return horaireTravaux;
    }
    /**
     * Définit les horaires prévus pour les travaux.
     *
     * @param horaireTravaux les nouveaux horaires des travaux.
     */
    public void setHoraireTravaux(String horaireTravaux) {
        this.horaireTravaux = horaireTravaux;
    }
    /**
     * Obtient l'identifiant de l'intervenant associé au projet.
     *
     * @return l'identifiant de l'intervenant.
     */
    public String getIntervenantId() {
        return intervenantId;
    }

    /**
     * Définit l'identifiant de l'intervenant associé au projet.
     *
     * @param intervenantId le nouvel identifiant de l'intervenant.
     */
    public void setIntervenantId(String intervenantId) {
        this.intervenantId = intervenantId;
    }
    /**
     * Met à jour la description du projet.
     *
     * @param description la nouvelle description à attribuer au projet.
     */
    public void misAJourProjetDescription(String description) {
        this.descriptionProjet = description;
    }
    /**
     * Met à jour la date de début des travaux du projet.
     *
     * @param date la nouvelle date de début au format YYYYMMDD.
     */
    public void misAJourProjetDate(int date) {
        this.dateDebut = date;
    }
    /**
     * Met à jour le statut du projet.
     *
     * @param statut le nouveau statut à attribuer au projet.
     */
    public void misAJourProjetStatut(Statut statut) {
        this.statut = statut;
    }
}

