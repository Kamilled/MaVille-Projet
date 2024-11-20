package com.example.prototype;
import io.javalin.Javalin;
import io.javalin.http.Context;

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
            boolean supprimé = requetes.removeIf(r -> r.getId() == id);
            if (supprimé) {
                ctx.status(204).result("Requête supprimée !");
            } else {
                ctx.status(404).result("Requête non trouvée !");
            }
        });
    }

    public void start() {
        app.start(7000);
    }
}
