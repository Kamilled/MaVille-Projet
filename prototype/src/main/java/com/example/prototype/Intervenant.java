package com.example.prototype;

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
