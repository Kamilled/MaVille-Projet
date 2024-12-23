package com.example.test;
import com.example.prototype.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe de test pour la classe {@link GestionnaireDonnees}.
 * <p>
 * Cette classe utilise JUnit pour tester les fonctionnalités de la classe {@link GestionnaireDonnees},
 * notamment la sauvegarde et le chargement des données utilisateur, requêtes de travail,
 * projets et entraves. Elle assure que les données sont correctement enregistrées dans
 * un fichier JSON et qu'elles peuvent être rechargées sans perte d'informations.
 * </p>
 *
 * <p>
 * Les tests incluent la création de données fictives, la sauvegarde de ces données,
 * leur rechargement et la vérification de l'intégrité des données rechargées.
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 */
public class GestionnaireDonneesTest {
    private File fichierDeTest;
    /**
     * Méthode exécutée avant chaque test pour configurer l'environnement de test.
     * <p>
     * Cette méthode prépare un fichier de test temporaire et s'assure que
     * tout fichier existant portant le même nom est supprimé avant le début du test.
     * </p>
     */
    @Before
    public void setUp() {

        fichierDeTest = new File("donnees_test.json");
        if (fichierDeTest.exists()) {
            fichierDeTest.delete();
        }

    }

    /**
     * Méthode exécutée après chaque test pour nettoyer l'environnement de test.
     * <p>
     * Cette méthode supprime le fichier de test temporaire utilisé pendant le test
     * afin de garantir que chaque test s'exécute dans un environnement propre.
     * </p>
     */
    @After
    public void tearDown() {
        // Nettoyage après test
        if (fichierDeTest.exists()) {
            fichierDeTest.delete();
        }
    }
    /**
     * Teste la sauvegarde et le chargement des données utilisateur, requêtes de travail,
     * projets et entraves.
     * <p>
     * Ce test crée des instances fictives de {@link Utilisateur}, {@link RequeteDeTravail},
     * {@link Projet} et {@link Entrave}, les sauvegarde en utilisant {@link GestionnaireDonnees},
     * puis les recharge pour vérifier que les données sauvegardées sont correctement restaurées.
     * </p>
     *
     * <p>
     * Les assertions vérifient que :
     * <ul>
     *   <li>Les listes de données ne sont pas vides après le chargement.</li>
     *   <li>Les informations des objets rechargés correspondent aux données initialement créées.</li>
     * </ul>
     * </p>
     */
    @Test
    public void testSauvegardeEtChargementDonnees() {

        List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(new Resident("Alice", "2000/01/01", "alice@test.com", "pass", "Adresse 1"));

        List<RequeteDeTravail> requetes = new ArrayList<>();
        RequeteDeTravail req = new RequeteDeTravail("Réparer X", "Détails", TypeTravaux.TRAVAUXROUTIERS, 3);
        requetes.add(req);

        List<Projet> projets = new ArrayList<>();
        Projet p = new Projet("ProjetTest", TypeTravaux.ENTRETIENURBAIN,
                List.of("Quartier A"), List.of("Rue A"), 20240101, 20240110,
                "Desc", "08:00-16:00", "Intervenant X");
        projets.add(p);

        List<Entrave> entraves = new ArrayList<>();
        Entrave e = new Entrave("ProjetTest", List.of("Rue A"));
        entraves.add(e);


        GestionnaireDonnees.sauvegarderToutesDonnees(utilisateurs, requetes, projets, entraves);


        GestionnaireDonnees.RegistreDonnees registre =
                GestionnaireDonnees.chargerToutesDonnees();


        assertFalse(registre.listeUtilisateurs.isEmpty(),
                "La liste des utilisateurs ne doit pas être vide après chargement");
        assertEquals("alice@test.com", registre.listeUtilisateurs.get(0).getEmail());

        assertFalse(registre.listeRequetes.isEmpty(),
                "La liste des requêtes ne doit pas être vide après chargement");
        assertEquals("Réparer X", registre.listeRequetes.get(0).getTitre());

        assertFalse(registre.listeProjets.isEmpty(),
                "La liste des projets ne doit pas être vide après chargement");
        assertEquals("ProjetTest", registre.listeProjets.get(0).getTitreProjet());

        assertFalse(registre.listeEntraves.isEmpty(),
                "La liste des entraves ne doit pas être vide après chargement");
        assertEquals("ProjetTest", registre.listeEntraves.get(0).getTravailAssocié());
    }
}

