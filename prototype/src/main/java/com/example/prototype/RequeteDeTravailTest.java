package com.example.prototype;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class RequeteDeTravailTest {
    @Test
    public void testAjouterCandidature() {
        RequeteDeTravail requete = new RequeteDeTravail("Réparer le lampadaire");
        Utilisateur intervenant = new Utilisateur("intervenant@exemple.com","password","intervenant");

        requete.ajouterCandidature(intervenant);

        List<Utilisateur> candidatures = requete.getCandidatures();
        assertTrue(candidatures.contains(intervenant),"L'intervenant devrait être ajouté aux candidatures");
    }

    @Test
    public void testRetirerCandidature(){
        RequeteDeTravail requete = new RequeteDeTravail("Nettoyer le parc");
        Utilisateur intervant = new Utilisateur("intervenant@exemple.com", "password", "intervenant");
        requete.ajouterCandidature(intervant);
        requete.retirerCandidature(intervant);
        List<Utilisateur> candidatures = requete.getCandidatures();
        assertFalse(candidatures.contains(intervant),"L'intervenant ne devrait plus être dans les candidatures.");
    }

    @Test
    public void testFermerRequete() {
        RequeteDeTravail requete = new RequeteDeTravail("Réparer un trou dans la rue");
        requete.fermer();
        assertTrue(requete.estFermee(),"La requête devrait être marquée comme fermée.");
    }
}
