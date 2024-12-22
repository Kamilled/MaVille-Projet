package com.example.prototype;

public class Resident extends Utilisateur {
    private String dateNaissance;
    private String adresse;
    private PlageHoraire plageHoraire;

	public Resident(String nomComplet, String dateNaissance, String email, String motDePasse, String adresse) {
		super(nomComplet, email, motDePasse);
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
	}

    public boolean isPlageHoraireEmpty() {
        return plageHoraire == null || 
               plageHoraire.getLundi().isEmpty() &&
               plageHoraire.getMardi().isEmpty() &&
               plageHoraire.getMercredi().isEmpty() &&
               plageHoraire.getJeudi().isEmpty() &&
               plageHoraire.getVendredi().isEmpty() &&
               plageHoraire.getSamedi().isEmpty() &&
               plageHoraire.getDimanche().isEmpty();

    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public PlageHoraire getPlageHoraire() {
        return plageHoraire;
    }

    public void setPlageHoraire(PlageHoraire plageHoraire) {
        this.plageHoraire = plageHoraire;
    }
    
}