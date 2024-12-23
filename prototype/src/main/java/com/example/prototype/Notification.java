package com.example.prototype;

public class Notification {
    private String titre;
    private String contenu;
    private Utilisateur destinataire;

    public Notification(String titre, String contenu, Utilisateur destinataire) {
        this.titre = titre;
        this.contenu = contenu;
        this.destinataire = destinataire;
    }

    // Getter pour le titre
    public String getTitre() {
        return titre;
    }

    // Getter pour le contenu
    public String getContenu() {
        return contenu;
    }

    // Getter pour le destinataire
    public Utilisateur getDestinataire() {
        return destinataire;
    }

    @Override
    public String toString() {
        return "Titre: " + titre + "\nContenu: " + contenu;
    }
}
