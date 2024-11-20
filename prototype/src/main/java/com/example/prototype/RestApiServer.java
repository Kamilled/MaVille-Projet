package com.example.prototype;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class RestApiServer {
    private Javalin app;
    private static List<RequeteDeTravail> requetes = new ArrayList<>();

    public RestApiServer(){
        app=Javalin.create();
        app.get("/", ctx -> ctx.result("Bienvenue sur l'API MaVille !"));
        app.get("/requetes", ctx -> getToutesLesRequetes(ctx));
        app.post("/requetes", ctx -> creerRequete(ctx));
        app.put("/requetes/{id}", ctx -> mettreAJourRequete(ctx));
        app.delete("/requetes/{id}", ctx -> supprimerRequete(ctx));
    }
    public void start(){
        app.start(7000);
    }

    private static void getToutesLesRequetes(Context ctx) {
        ctx.json(requetes);
    }

    private static void creerRequete(Context ctx) {
        String description = ctx.formParam("description");
        if (description == null || description.isEmpty()) {
            ctx.status(400).result("Description requise !");
            return;
        }
        RequeteDeTravail nouvelleRequete = new RequeteDeTravail(description);
        requetes.add(nouvelleRequete);
        ctx.status(201).json(nouvelleRequete);
    }

    private static void mettreAJourRequete(Context ctx) {
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
    }

    private static void supprimerRequete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean supprime=requetes.removeIf(r -> r.getId()==id);
        if (supprime) {
            ctx.status(204).result("Requête supprimée !");
        }else {
            ctx.status(404).result("Requête non trouvée !");
        }
    }
}
