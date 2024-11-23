package com.example.test;
import com.example.prototype.RequeteDeTravail;
import com.example.prototype.Utilisateur;
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
    }@Test
public void testAjouterCandidatureDejaExistante() {
    RequeteDeTravail requete = new RequeteDeTravail("Réparer le toit");
    Utilisateur intervenant = new Utilisateur("intervenant@exemple.com", "password", "intervenant");

    requete.ajouterCandidature(intervenant);
    requete.ajouterCandidature(intervenant);  // Tentative d'ajout d'un candidat déjà présent

    List<Utilisateur> candidatures = requete.getCandidatures();
    assertEquals(1, candidatures.size(), "L'intervenant ne devrait être ajouté qu'une seule fois.");
}

@Test
public void testFermerRequeteDejaFermee() {
    RequeteDeTravail requete = new RequeteDeTravail("Réparer la porte");
    requete.fermer();  // Ferme la requête une première fois

    requete.fermer();  // Essaye de fermer la requête de nouveau
    assertTrue(requete.estFermee(), "La requête devrait rester fermée après avoir été fermée une première fois.");
}

@Test
public void testRequeteSansCandidature() {
    RequeteDeTravail requete = new RequeteDeTravail("Peindre le mur");

    List<Utilisateur> candidatures = requete.getCandidatures();
    assertTrue(candidatures.isEmpty(), "La requête ne devrait pas avoir de candidatures.");
}

}
