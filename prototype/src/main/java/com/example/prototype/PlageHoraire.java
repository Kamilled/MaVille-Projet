package com.example.prototype;

import java.util.Scanner;

public class PlageHoraire {
    // "HH:MM-HH:MM" format
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String samedi;
    private String dimanche;

    public PlageHoraire(String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, String dimanche) {
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.dimanche = dimanche;
    }

    public void modifierPlageHoraire(int dayIndex, String newTimeSlot) {
        switch (dayIndex) {
            case 1:
                setLundi(newTimeSlot);
                break;
            case 2:
                setMardi(newTimeSlot);
                break;
            case 3:
                setMercredi(newTimeSlot);
                break;
            case 4:
                setJeudi(newTimeSlot);
                break;
            case 5:
                setVendredi(newTimeSlot);
                break;
            case 6:
                setSamedi(newTimeSlot);
                break;
            case 7:
                setDimanche(newTimeSlot);
                break;
            default:
                System.out.println("Jour invalide.");
        }
    }

    public void nouvellePlageHoraire(Scanner scanner) {
        System.out.print("Entrez votre plage horaire pour Lundi (format HH:MM-HH:MM) : ");
        setLundi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Mardi (format HH:MM-HH:MM) : ");
        setMardi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Mercredi (format HH:MM-HH:MM) : ");
        setMercredi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Jeudi (format HH:MM-HH:MM) : ");
        setJeudi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Vendredi (format HH:MM-HH:MM) : ");
        setVendredi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Samedi (format HH:MM-HH:MM) : ");
        setSamedi(scanner.nextLine());
        System.out.print("Entrez votre plage horaire pour Dimanche (format HH:MM-HH:MM) : ");
        setDimanche(scanner.nextLine());
    }


    public String getLundi() {
        return lundi;
    }

    public void setLundi(String lundi) {
        this.lundi = lundi;
    }

    public String getMardi() {
        return mardi;
    }

    public void setMardi(String mardi) {
        this.mardi = mardi;
    }

    public String getMercredi() {
        return mercredi;
    }

    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }

    public String getJeudi() {
        return jeudi;
    }

    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }

    public String getVendredi() {
        return vendredi;
    }

    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }

    public String getSamedi() {
        return samedi;
    }

    public void setSamedi(String samedi) {
        this.samedi = samedi;
    }

    public String getDimanche() {
        return dimanche;
    }

    public void setDimanche(String dimanche) {
        this.dimanche = dimanche;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plage Horaire:\n");
        sb.append("Lundi: ").append(lundi.isEmpty() ? "Pas disponible" : lundi).append("\n");
        sb.append("Mardi: ").append(mardi.isEmpty() ? "Pas disponible" : mardi).append("\n");
        sb.append("Mercredi: ").append(mercredi.isEmpty() ? "Pas disponible" : mercredi).append("\n");
        sb.append("Jeudi: ").append(jeudi.isEmpty() ? "Pas disponible" : jeudi).append("\n");
        sb.append("Vendredi: ").append(vendredi.isEmpty() ? "Pas disponible" : vendredi).append("\n");
        sb.append("Samedi: ").append(samedi.isEmpty() ? "Pas disponible" : samedi).append("\n");
        sb.append("Dimanche: ").append(dimanche.isEmpty() ? "Pas disponible" : dimanche).append("\n");
        return sb.toString();
    }
    
}