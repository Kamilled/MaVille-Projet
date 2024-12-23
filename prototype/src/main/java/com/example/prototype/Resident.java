package com.example.prototype;
/**
 * Représente un résident dans l'application MaVille.
 * <p>
 * Cette classe étend la classe {@link Utilisateur} et ajoute des attributs spécifiques
 * aux résidents, tels que la date de naissance, l'adresse et la plage horaire de disponibilité.
 * </p>
 *
 * <p>
 * Les résidents peuvent définir leurs disponibilités horaires pour chaque jour de la semaine
 * et fournir des informations personnelles supplémentaires pour faciliter les interactions
 * avec les requêtes de travail et les intervenants.
 * </p>
 *
 * @author
 *         Aya Elbroumi
 */

public class Resident extends Utilisateur {
    private String dateNaissance;
    private String adresse;
    private PlageHoraire plageHoraire;
    /**
     * Constructeur pour créer une nouvelle instance de {@code Resident}.
     *
     * @param nomComplet     le nom complet du résident.
     * @param dateNaissance  la date de naissance du résident au format "YYYY-MM-DD".
     * @param email          l'adresse email du résident.
     * @param motDePasse     le mot de passe du résident.
     * @param adresse        l'adresse complète du résident.
     */
	public Resident(String nomComplet, String dateNaissance, String email, String motDePasse, String adresse) {
		super(nomComplet, email, motDePasse);
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
	}
    /**
     * Vérifie si la plage horaire du résident est vide ou non définie.
     *
     * @return {@code true} si la plage horaire est {@code null} ou si toutes les plages horaires sont vides, {@code false} sinon.
     */
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
    /**
     * Obtient la date de naissance du résident.
     *
     * @return la date de naissance au format "YYYY-MM-DD".
     */
    public String getDateNaissance() {
        return dateNaissance;
    }
    /**
     * Définit la date de naissance du résident.
     *
     * @param dateNaissance la nouvelle date de naissance au format "YYYY-MM-DD".
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    /**
     * Obtient l'adresse complète du résident.
     *
     * @return l'adresse complète.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse complète du résident.
     *
     * @param adresse la nouvelle adresse complète.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    /**
     * Obtient la plage horaire de disponibilité du résident.
     *
     * @return l'objet {@link PlageHoraire} représentant les disponibilités horaires.
     */
    public PlageHoraire getPlageHoraire() {
        return plageHoraire;
    }

    /**
     * Définit la plage horaire de disponibilité du résident.
     *
     * @param plageHoraire le nouvel objet {@link PlageHoraire} représentant les disponibilités horaires.
     */
    public void setPlageHoraire(PlageHoraire plageHoraire) {
        this.plageHoraire = plageHoraire;
    }
    
}