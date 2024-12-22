package com.example.prototype;


public class Utilisateur {
    private String nomComplet;
    private String email;
    private String motDePasse;


    public Utilisateur(String nomComplet, String email, String motDePasse) {
        this.nomComplet = nomComplet;
        this.email = email;
        this.motDePasse = motDePasse;
    }


    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }


    public boolean authentifier(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }
}