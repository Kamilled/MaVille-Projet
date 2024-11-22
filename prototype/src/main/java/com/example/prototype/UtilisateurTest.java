package com.example.prototype;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class UtilisateurTest {

    @Test
    void testauthentifier() {
        //test résident
        Utilisateur utilisateur1 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertTrue(utilisateur1.authentifier("1@hotmail.com", "123"));
        //test intervenant
        Utilisateur utilisateur2 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertTrue(utilisateur2.authentifier("1@gmail.com", "123"));
    }

    @Test
    void testauthentifierMauvaisMDP() {
        //test avec mot de passe incorrect
        //test résident
        Utilisateur utilisateur3 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertFalse(utilisateur3.authentifier("1@hotmail.com", "MauvaisMotDePasse"));
        //test intervenant
        Utilisateur utilisateur4 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertFalse(utilisateur4.authentifier("1@gmail.com", "MauvaisMotDePasse"));
    }

    @Test
    void testauthentifierMauvaisCourriel() {
        //test avec email incorrect
        //test résident
        Utilisateur utilisateur5 = new Utilisateur("1@hotmail.com", "123", "resident");
        assertFalse(utilisateur5.authentifier("mauvaisCourriel", "123"));
        //test intervenant
        Utilisateur utilisateur6 = new Utilisateur("1@gmail.com", "123", "intervenant");
        assertFalse(utilisateur6.authentifier("mauvaisCourriel", "123"));
    }
}