package com.example.test;
import com.example.prototype.Entrave;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour la classe {@link Entrave}.
 * <p>
 * Cette classe utilise JUnit pour tester les fonctionnalités de la classe {@link Entrave},
 * notamment l'ajout et la vérification des entraves.
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 */
public class EntraveTest {
    /**
     * Teste l'ajout de plusieurs entraves et vérifie leur intégrité.
     * <p>
     * Ce test crée une liste d'entraves, y ajoute deux entraves avec des rues affectées différentes,
     * puis vérifie que la liste contient bien les entraves ajoutées et que les rues sont correctement associées.
     * </p>
     */
    @Test
    public void testAjouterEtVerifierEntrave() {
        List<Entrave> entraves = new ArrayList<>();

        Entrave e1 = new Entrave("Travail 1", List.of("Rue A", "Rue B"));
        Entrave e2 = new Entrave("Travail 2", List.of("Rue C"));


        entraves.add(e1);
        entraves.add(e2);


        assertEquals(2, entraves.size());
        assertEquals("Travail 1", entraves.get(0).getTravailAssocié());
        assertTrue(entraves.get(0).getRues().contains("Rue B"));
        assertFalse(entraves.get(1).getRues().contains("Rue B"));
    }
}
