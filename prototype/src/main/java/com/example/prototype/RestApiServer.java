package com.example.prototype;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.javalin.Javalin;
/**
 * Gère le serveur REST API pour l'application MaVille.
 * <p>
 * Cette classe initialise et configure le serveur REST API en utilisant Javalin. Elle
 * fournit des endpoints pour gérer les requêtes de travail, ainsi que pour interagir
 * avec une API publique de travaux à Montréal.
 * </p>
 *
 * <p>
 * Les endpoints incluent :
 * <ul>
 *   <li>GET "/" : Page d'accueil de l'API.</li>
 *   <li>GET "/requetes" : Récupère toutes les requêtes de travail.</li>
 *   <li>POST "/requetes" : Crée une nouvelle requête de travail.</li>
 *   <li>PUT "/requetes/{id}" : Met à jour une requête de travail existante.</li>
 *   <li>DELETE "/requetes/{id}" : Supprime une requête de travail.</li>
 *   <li>GET "/travaux-publics" : Récupère les travaux publics depuis une API externe.</li>
 * </ul>
 * </p>
 *
 * @author
 *         Kamille Denault-Geoffroy
 *         Aya Elbroumi
 */
public class RestApiServer {
    private Javalin app;
    private List<RequeteDeTravail> requetes;
    /**
     * Constructeur pour initialiser le serveur REST API avec une liste de requêtes de travail.
     *
     * @param requetes la liste initiale des requêtes de travail à gérer par l'API.
     */

    public RestApiServer(List<RequeteDeTravail> requetes) {
        this.requetes = requetes;
        app = Javalin.create();

        app.get("/", ctx -> ctx.result("Bienvenue sur l'API MaVille !"));
        app.get("/requetes", ctx -> ctx.json(requetes));
        app.post("/requetes", ctx -> {
            String titre = ctx.formParam("titre");
            if (titre == null || titre.isEmpty()) {
                ctx.status(400).result("Titre requis !");
                return;
            }
            String description = ctx.formParam("description");
            if (description == null || description.isEmpty()) {
                ctx.status(400).result("Description requise !");
                return;
            }
            TypeTravaux typeDeTravail = TypeTravaux.valueOf(ctx.formParam("typeDeTravail"));
            if (typeDeTravail == null) {
                ctx.status(400).result("Type de travail requis !");
                return;
            }
            int dureeEsperee = Integer.parseInt(ctx.formParam("dureeEsperee"));
            if (dureeEsperee <= 0) {
                ctx.status(400).result("Durée espérée du projet requise !");
                return;
            }
            RequeteDeTravail nouvelleRequete = new RequeteDeTravail(titre, description, typeDeTravail, dureeEsperee);
            requetes.add(nouvelleRequete);
            ctx.status(201).json(nouvelleRequete);
        });
        app.put("/requetes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            RequeteDeTravail requete = requetes.stream()
                    .filter(r -> r.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (requete == null) {
                ctx.status(404).result("Requête non trouvée !");
                return;
            }
            String nouvelleDescription = ctx.formParam("description");
            if (nouvelleDescription != null) {
                requete.setDescription(nouvelleDescription);
            }
            ctx.json(requete);
        });
        app.delete("/requetes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean supprimer = requetes.removeIf(r -> r.getId() == id);
            if (supprimer) {
                ctx.status(204).result("Requête supprimée !");
            } else {
                ctx.status(404).result("Requête non trouvée !");
            }
        });

        app.get("/travaux-publics",ctx -> {
            try{
                String apiUrlMontreal = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
                URL url = new URL(apiUrlMontreal);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                if (connection.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    // Extraction des travaux depuis l'API publique
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray travauxMontreal = jsonResponse.getJSONObject("result").getJSONArray("records");
                    // Renvoi des données au client via l'API locale
                    ctx.json(travauxMontreal);
                } else {
                    ctx.status(500).result("Erreur lors de l'appel à l'API publique : " + connection.getResponseCode());
                }
            } catch (Exception e) {
                ctx.status(500).result("Erreur interne : " + e.getMessage());
            }
        });
    }

    /**
     * Démarre le serveur REST API sur le port spécifié.
     * <p>
     * Cette méthode lance le serveur et le rend accessible via le port 8080.
     * </p>
     */
    public void start() {
        app.start(8080);
    }
}