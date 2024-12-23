package com.example.test;
import com.example.prototype.Main;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Classe de test pour la méthode {@link Main#filtrerTravauxParBorough(JSONArray, String)}.
 * <p>
 * Cette classe utilise JUnit pour vérifier le bon fonctionnement de la méthode de filtrage des travaux
 * par borough. Elle teste notamment la capacité de la méthode à filtrer correctement les travaux
 * en fonction de l'identifiant du borough fourni.
 * </p>
 *
 * <p>
 * Les tests incluent la création de données fictives (mock) et la validation des résultats obtenus
 * après application du filtre.
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 */
public class FiltreTravauxTest {
    /**
     * Teste la méthode {@link Main#filtrerTravauxParBorough(JSONArray, String)} en vérifiant
     * qu'elle filtre correctement les travaux associés à un borough spécifique.
     * <p>
     * Ce test crée un ensemble de travaux fictifs avec différents identifiants de borough,
     * applique le filtre pour le borough "99", et vérifie que seuls les travaux
     * correspondant à ce borough sont retournés.
     * </p>
     */
    @Test
    public void testFiltrerTravauxParBorough() {
        JSONArray fauxTravaux = new JSONArray();

        JSONObject t1 = new JSONObject();
        t1.put("id", "111");
        t1.put("boroughid", "99");
        fauxTravaux.put(t1);

        JSONObject t2 = new JSONObject();
        t2.put("id", "222");
        t2.put("boroughid", "88");
        fauxTravaux.put(t2);

        JSONObject t3 = new JSONObject();
        t3.put("id", "333");
        t3.put("boroughid", "99");
        fauxTravaux.put(t3);

        List<JSONObject> result = Main.filtrerTravauxParBorough(fauxTravaux, "99");


        assertEquals("On devrait trouver 2 travaux pour borough 99", 2, result.size());
        assertEquals("111", result.get(0).optString("id"));
        assertEquals("333", result.get(1).optString("id"));
    }
}

