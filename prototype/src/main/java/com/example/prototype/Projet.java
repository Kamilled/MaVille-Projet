package com.example.prototype;

import java.util.List;

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

    // Getters et Setters
    public Statut getStatutPrevu() {
        return statut;
    }

    public void setStatutPrevu(Statut statutPrevu) {
        this.statut = statutPrevu;
    }

    public String getTitreProjet() {
        return titreProjet;
    }

    public void setTitreProjet(String titreProjet) {
        this.titreProjet = titreProjet;
    }

    public TypeTravaux getTypeTravaux() {
        return typeTravaux;
    }

    public void setTypeTravaux(TypeTravaux typeTravaux) {
        this.typeTravaux = typeTravaux;
    }


    public List<String> getQuartiersAffectes() {
        return quartiersAffectes;
    }

    public void setQuartiersAffectes(List<String> quartiersAffectes) {
        this.quartiersAffectes = quartiersAffectes;
    }

    public List<String> getRuesAffectees() {
        return ruesAffectees;
    }

    public void setRuesAffectees(List<String> ruesAffectees) {
        this.ruesAffectees = ruesAffectees;
    }

    public int getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(int dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDateFin() {
        return dateFin;
    }

    public void setDateFin(int dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public String getHoraireTravaux() {
        return horaireTravaux;
    }

    public void setHoraireTravaux(String horaireTravaux) {
        this.horaireTravaux = horaireTravaux;
    }

    public String getIntervenantId() {
        return intervenantId;
    }

    public void setIntervenantId(String intervenantId) {
        this.intervenantId = intervenantId;
    }

    public void misAJourProjetDescription(String description) {
        this.descriptionProjet = description;
    }

    public void misAJourProjetDate(int date) {
        this.dateDebut = date;
    }

    public void misAJourProjetStatut(Statut statut) {
        this.statut = statut;
    }
}

