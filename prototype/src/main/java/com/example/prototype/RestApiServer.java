package com.example.prototype;
import io.javalin.Javalin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RestApiServer {
    private Javalin app;
    private List<RequeteDeTravail> requetes;

    public RestApiServer(List<RequeteDeTravail> requetes) {
        this.requetes = requetes;
        app = Javalin.create();

        app.get("/", ctx -> ctx.result("Bienvenue sur l'API MaVille !"));
        app.get("/requetes", ctx -> ctx.json(requetes));
        app.post("/requetes", ctx -> {
            String description = ctx.formParam("description");
            if (description == null || description.isEmpty()) {
                ctx.status(400).result("Description requise !");
                return;
            }
            RequeteDeTravail nouvelleRequete = new RequeteDeTravail(description);
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

    public void start() {
        app.start(7000);
    }
}
