package com.example.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.prototype.Utilisateur;


/**
 * Classe de test pour la classe {@link Utilisateur}.
 * <p>
 * Cette classe utilise JUnit pour tester les fonctionnalités de la classe {@link Utilisateur},
 * notamment l'authentification des utilisateurs avec des emails et mots de passe corrects
 * ou incorrects.
 * </p>
 *
 * <p>
 * Les tests incluent :
 * <ul>
 *   <li>Authentification réussie avec des informations valides.</li>
 *   <li>Échec de l'authentification avec un mot de passe incorrect.</li>
 *   <li>Échec de l'authentification avec un email incorrect.</li>
 * </ul>
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 *         Aya Elbroumi
 */
public class UtilisateurTest {
    /**
     * Teste la méthode {@link Utilisateur#authentifier(String, String)} avec des informations d'authentification valides.
     * <p>
     * Ce test crée deux utilisateurs avec des rôles différents ("resident" et "intervenant") et vérifie
     * que la méthode d'authentification retourne {@code true} lorsque l'email et le mot de passe fournis
     * correspondent aux informations de l'utilisateur.
     * </p>
     */
    @Test
    void testauthentifier() {

        Utilisateur utilisateur1 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertTrue(utilisateur1.authentifier("1@hotmail.com", "123"));

        Utilisateur utilisateur2 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertTrue(utilisateur2.authentifier("1@gmail.com", "123"));
    }
    /**
     * Teste la méthode {@link Utilisateur#authentifier(String, String)} avec un mot de passe incorrect.
     * <p>
     * Ce test crée deux utilisateurs avec des rôles différents ("resident" et "intervenant") et vérifie
     * que la méthode d'authentification retourne {@code false} lorsque le mot de passe fourni ne correspond
     * pas au mot de passe de l'utilisateur.
     * </p>
     */
    @Test
    void testauthentifierMauvaisMDP() {

        Utilisateur utilisateur3 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertFalse(utilisateur3.authentifier("1@hotmail.com", "MauvaisMotDePasse"));

        Utilisateur utilisateur4 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertFalse(utilisateur4.authentifier("1@gmail.com", "MauvaisMotDePasse"));
    }
    /**
     * Teste la méthode {@link Utilisateur#authentifier(String, String)} avec un email incorrect.
     * <p>
     * Ce test crée deux utilisateurs avec des rôles différents ("resident" et "intervenant") et vérifie
     * que la méthode d'authentification retourne {@code false} lorsque l'email fourni ne correspond pas
     * à l'email de l'utilisateur.
     * </p>
     */
    @Test
    void testauthentifierMauvaisCourriel() {

        Utilisateur utilisateur5 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertFalse(utilisateur5.authentifier("mauvaisCourriel", "123"));

        Utilisateur utilisateur6 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertFalse(utilisateur6.authentifier("mauvaisCourriel", "123"));
    }
}