package com.example.test;
import com.example.prototype.RequeteDeTravail;
import com.example.prototype.Utilisateur;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
/**
 * Classe de test pour la classe {@link RequeteDeTravail}.
 * <p>
 * Cette classe utilise JUnit pour tester les fonctionnalités de la classe {@link RequeteDeTravail},
 * notamment l'ajout et la suppression de candidatures, la fermeture des requêtes,
 * ainsi que la gestion des candidatures existantes et l'état des requêtes.
 * </p>
 *
 * <p>
 * Les tests incluent :
 * <ul>
 *   <li>Ajout d'une candidature et vérification de sa présence.</li>
 *   <li>Retrait d'une candidature et vérification de son absence.</li>
 *   <li>Fermeture d'une requête et vérification de son statut.</li>
 *   <li>Ajout d'une candidature déjà existante et vérification qu'elle n'est pas dupliquée.</li>
 *   <li>Fermeture d'une requête déjà fermée et vérification de son statut.</li>
 *   <li>Vérification d'une requête sans candidatures.</li>
 * </ul>
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 *         Yitian Chen
 */
public class RequeteDeTravailTest {
    /**
     * Teste l'ajout d'une candidature à une requête de travail et vérifie que l'intervenant
     * est bien ajouté à la liste des candidatures.
     */
    @Test
    public void testAjouterCandidature() {
        RequeteDeTravail requete = new RequeteDeTravail("Réparer le lampadaire");
        Utilisateur intervenant = new Utilisateur("intervenant@exemple.com","password","intervenant");

        requete.ajouterCandidature(intervenant);

        List<Utilisateur> candidatures = requete.getCandidatures();
        assertTrue(candidatures.contains(intervenant),"L'intervenant devrait être ajouté aux candidatures");
    }
    /**
     * Teste le retrait d'une candidature d'une requête de travail et vérifie que l'intervenant
     * est bien retiré de la liste des candidatures.
     */
    @Test
    public void testRetirerCandidature(){
        RequeteDeTravail requete = new RequeteDeTravail("Nettoyer le parc");
        Utilisateur intervant = new Utilisateur("intervenant@exemple.com", "password", "intervenant");
        requete.ajouterCandidature(intervant);
        requete.retirerCandidature(intervant);
        List<Utilisateur> candidatures = requete.getCandidatures();
        assertFalse(candidatures.contains(intervant),"L'intervenant ne devrait plus être dans les candidatures.");
    }

    /**
     * Teste la fermeture d'une requête de travail et vérifie que son statut est bien mis à jour.
     */
    @Test
    public void testFermerRequete() {
        RequeteDeTravail requete = new RequeteDeTravail("Réparer un trou dans la rue");
        requete.fermer();
        assertTrue(requete.estFermee(),"La requête devrait être marquée comme fermée.");
    }
    /**
     * Teste l'ajout d'une candidature déjà existante à une requête de travail
     * et vérifie qu'elle n'est pas dupliquée dans la liste des candidatures.
     */
    @Test
public void testAjouterCandidatureDejaExistante() {
    RequeteDeTravail requete = new RequeteDeTravail("Réparer le toit");
    Utilisateur intervenant = new Utilisateur("intervenant@exemple.com", "password", "intervenant");

    requete.ajouterCandidature(intervenant);
    requete.ajouterCandidature(intervenant);  // Tentative d'ajout d'un candidat déjà présent

    List<Utilisateur> candidatures = requete.getCandidatures();
    assertEquals(1, candidatures.size(), "L'intervenant ne devrait être ajouté qu'une seule fois.");
}
    /**
     * Teste la fermeture d'une requête de travail déjà fermée
     * et vérifie que son statut reste fermé sans erreurs.
     */
@Test
public void testFermerRequeteDejaFermee() {
    RequeteDeTravail requete = new RequeteDeTravail("Réparer la porte");
    requete.fermer();  // Ferme la requête une première fois

    requete.fermer();  // Essaye de fermer la requête de nouveau
    assertTrue(requete.estFermee(), "La requête devrait rester fermée après avoir été fermée une première fois.");
}
    /**
     * Teste une requête de travail sans aucune candidature et vérifie que la liste des candidatures est vide.
     */
@Test
public void testRequeteSansCandidature() {
    RequeteDeTravail requete = new RequeteDeTravail("Peindre le mur");

    List<Utilisateur> candidatures = requete.getCandidatures();
    assertTrue(candidatures.isEmpty(), "La requête ne devrait pas avoir de candidatures.");
}

}
