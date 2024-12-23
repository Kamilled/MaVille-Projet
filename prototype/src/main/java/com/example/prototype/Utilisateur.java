package com.example.prototype;
/**
 * Représente un utilisateur dans l'application MaVille.
 * <p>
 * Cette classe encapsule les informations de base d'un utilisateur, telles que le nom complet,
 * l'email et le mot de passe. Elle fournit des méthodes pour accéder et modifier ces informations,
 * ainsi qu'une méthode d'authentification.
 * </p>
 *
 * <p>
 * Les instances de cette classe sont utilisées pour gérer les utilisateurs du système,
 * facilitant ainsi les opérations d'authentification et la gestion des données utilisateur.
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 *         Aya Elbroumi
 */
public class Utilisateur {
    private String nomComplet;
    private String email;
    private String motDePasse;

    /**
     * Constructeur pour créer une nouvelle instance de {@code Utilisateur}.
     *
     * @param nomComplet   le nom complet de l'utilisateur.
     * @param email        l'adresse email de l'utilisateur.
     * @param motDePasse   le mot de passe de l'utilisateur.
     */
    public Utilisateur(String nomComplet, String email, String motDePasse) {
        this.nomComplet = nomComplet;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    /**
     * Obtient le nom complet de l'utilisateur.
     *
     * @return le nom complet.
     */
    public String getNomComplet() {
        return nomComplet;
    }
    /**
     * Définit le nom complet de l'utilisateur.
     *
     * @param nomComplet le nouveau nom complet à attribuer à l'utilisateur.
     */
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    /**
     * Obtient l'adresse email de l'utilisateur.
     *
     * @return l'adresse email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Obtient le mot de passe de l'utilisateur.
     *
     * @return le mot de passe.
     */
    public String getMotDePasse() {
        return motDePasse;
    }


    /**
     * Authentifie l'utilisateur en vérifiant l'email et le mot de passe fournis.
     *
     * @param email        l'email fourni pour l'authentification.
     * @param motDePasse   le mot de passe fourni pour l'authentification.
     * @return {@code true} si l'email et le mot de passe correspondent, {@code false} sinon.
     */
    public boolean authentifier(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }
}