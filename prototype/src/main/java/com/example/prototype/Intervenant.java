package com.example.prototype;
/**
 * Représente un intervenant dans l'application MaVille.
 * <p>
 * Cette classe étend la classe {@link Utilisateur} et ajoute des attributs spécifiques
 * aux intervenants, tels que le type d'intervenant et l'identifiant de la ville.
 * </p>
 *
 * <p>
 * Les intervenants peuvent être des entreprises publiques, des entrepreneurs privés,
 * ou des particuliers, chacun ayant des attributs spécifiques pour gérer leurs
 * interactions avec les requêtes de travail.
 * </p>
 *
 * @author
 *         Aya Elbroumi
 */
public class Intervenant extends Utilisateur{

    private TypeIntervenant typeIntervenant;
    private int identifiantVille;

    public Intervenant(String nomComplet, TypeIntervenant typeIntervenant, String email, String motDePasse, int identifiantVille) {
        super(nomComplet, email, motDePasse);
        this.typeIntervenant = typeIntervenant;
        this.identifiantVille = identifiantVille;

    }

    public TypeIntervenant getTypeIntervenant() {
        return typeIntervenant;
    }

    public void setTypeIntervenant(TypeIntervenant typeIntervenant) {
        this.typeIntervenant = typeIntervenant;
    }

    public int getIdentifiantVille() {
        return identifiantVille;
    }

    public void setIdentifiantVille(int identifiantVille) {
        this.identifiantVille = identifiantVille;
    }

    
}
