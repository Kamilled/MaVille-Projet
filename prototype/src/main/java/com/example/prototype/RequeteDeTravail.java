package com.example.prototype;

import java.util.ArrayList;
import java.util.List;

// Classe RequeteDeTravail, représentant une demande de travail
public class RequeteDeTravail {
    private static int compteurId = 1;
    private int id;
    private String titre;
    private String description;
    private TypeTravaux typeDeTravail;
    private int dureeEsperee; // EN JOURS
    private boolean estFermee;
    private List<Utilisateur> candidatures;
    private Utilisateur candidatSelectionne;
    private String message;
    private boolean confirmationIntervenant;

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

    public boolean isConfirmationIntervenant() {
        return confirmationIntervenant;
    }

    public void confirmerIntervenant() {
        this.confirmationIntervenant = true;
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

    public List<Utilisateur> getCandidatures() {
        return candidatures;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void ajouterCandidature(Utilisateur intervenant) {
        if (!candidatures.contains(intervenant)) {
            candidatures.add(intervenant);
        }
    }

    public void retirerCandidature(Utilisateur intervenant) {
        candidatures.remove(intervenant);
    }

    public Utilisateur getCandidatSelectionne() {
        return candidatSelectionne;
    }

    public void setCandidatSelectionne(Utilisateur candidat) {
        this.candidatSelectionne = candidat;
    }


    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Fermée: " + estFermee;
    }

    public void setDescription(String nouvelleDescription) {
        this.description = nouvelleDescription;
    }

    public void setDureeEsperee(int dureeEsperee) {
        this.dureeEsperee = dureeEsperee;
    }

    public int getDureeEsperee() {
        return dureeEsperee;
    }

    public void setTypeDeTravail(TypeTravaux typeDeTravail) {
        this.typeDeTravail = typeDeTravail;
    }

    public TypeTravaux getTypeDeTravail() {
        return typeDeTravail;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}