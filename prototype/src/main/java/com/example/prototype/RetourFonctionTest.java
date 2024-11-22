package com.example.prototype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RetourFonctionTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        // Redirigez System.out vers un flux de sortie pour capturer les messages
        originalSystemOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Initialiser les données nécessaires pour les tests
        Main.initialiserDonnees();
    }

    @Test
    public void testRetourMenuResident() {
        // Simule l'entrée d'un résident pour se connecter et revenir au menu
        String input = "1\n1@hotmail.com\n123\n5\n"; // 1 -> Connexion résident, 5 -> Retour au menu
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Exécution du programme
        Main.menuPrincipal();

        // Vérifie si le message de retour au menu apparaît
        String output = outputStream.toString();
        assertTrue(output.contains("Retour au menu principal ..."));
    }

    @Test
    public void testRetourMenuIntervenant() {
        // Simule l'entrée d'un intervenant pour se connecter et revenir au menu
        String input = "2\n1@gmail.com\n123\n5\n"; // 2 -> Connexion intervenant, 5 -> Retour au menu
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Exécution du programme
        Main.menuPrincipal();

        // Vérifie si le message de retour au menu apparaît
        String output = outputStream.toString();
        assertTrue(output.contains("Retour au menu principal ..."));
    }

    @Test
    public void testSoumettreRequeteResident() {
        // Simule l'entrée d'un résident pour soumettre une requête et revenir au menu
        String input = "1\n1@hotmail.com\n123\n1\nTest description de la requête\n5\n"; // 1 -> Soumettre une requête, 5 -> Retour au menu
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Exécution du programme
        Main.menuPrincipal();

        // Vérifie si la requête a été soumise et si le message de retour au menu apparaît
        String output = outputStream.toString();
        assertTrue(output.contains("Requête créée avec succès"));
        assertTrue(output.contains("Retour au menu principal ..."));
    }
}
