package com.example.prototype;

public class Resident extends Utilisateur {
    private String dateNaissance;
    private String adresse;

	public Resident(String nomComplet, String dateNaissance, String email, String motDePasse, String adresse) {
		super(nomComplet, email, motDePasse);
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
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
    
	
}
