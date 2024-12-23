package com.example.prototype;

import java.util.Scanner;
/**
 * Représente les plages horaires disponibles pour chaque jour de la semaine.
 * <p>
 * Cette classe permet de définir, modifier et afficher les plages horaires
 * d'un utilisateur pour chaque jour de la semaine au format "HH:MM-HH:MM".
 * </p>
 *
 * @author
 *         Aya Elbroumi
 */

public class PlageHoraire {
    // "HH:MM-HH:MM" format
    private String lundi;
    private String mardi;
    private String mercredi;
    private String jeudi;
    private String vendredi;
    private String samedi;
    private String dimanche;
    /**
     * Constructeur pour créer une nouvelle instance de {@code PlageHoraire}.
     *
     * @param lundi      la plage horaire pour le lundi au format "HH:MM-HH:MM".
     * @param mardi      la plage horaire pour le mardi au format "HH:MM-HH:MM".
     * @param mercredi   la plage horaire pour le mercredi au format "HH:MM-HH:MM".
     * @param jeudi      la plage horaire pour le jeudi au format "HH:MM-HH:MM".
     * @param vendredi   la plage horaire pour le vendredi au format "HH:MM-HH:MM".
     * @param samedi     la plage horaire pour le samedi au format "HH:MM-HH:MM".
     * @param dimanche   la plage horaire pour le dimanche au format "HH:MM-HH:MM".
     */
    public PlageHoraire(String lundi, String mardi, String mercredi, String jeudi, String vendredi, String samedi, String dimanche) {
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.dimanche = dimanche;
    }
    /**
     * Modifie la plage horaire d'un jour spécifique.
     *
     * @param dayIndex      l'index du jour à modifier (1 = Lundi, 2 = Mardi, ..., 7 = Dimanche).
     * @param newTimeSlot   la nouvelle plage horaire au format "HH:MM-HH:MM".
     */
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
    /**
     * Permet à l'utilisateur de définir de nouvelles plages horaires pour chaque jour de la semaine.
     *
     * @param scanner le {@link Scanner} utilisé pour lire les entrées de l'utilisateur.
     */
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

    /**
     * Obtient la plage horaire pour le lundi.
     *
     * @return la plage horaire du lundi au format "HH:MM-HH:MM".
     */
    public String getLundi() {
        return lundi;
    }
    /**
     * Définit la plage horaire pour le lundi.
     *
     * @param lundi la nouvelle plage horaire du lundi au format "HH:MM-HH:MM".
     */
    public void setLundi(String lundi) {
        this.lundi = lundi;
    }
    /**
     * Obtient la plage horaire pour le mardi.
     *
     * @return la plage horaire du mardi au format "HH:MM-HH:MM".
     */
    public String getMardi() {
        return mardi;
    }
    /**
     * Définit la plage horaire pour le mardi.
     *
     * @param mardi la nouvelle plage horaire du mardi au format "HH:MM-HH:MM".
     */
    public void setMardi(String mardi) {
        this.mardi = mardi;
    }
    /**
     * Obtient la plage horaire pour le mercredi.
     *
     * @return la plage horaire du mercredi au format "HH:MM-HH:MM".
     */
    public String getMercredi() {
        return mercredi;
    }
    /**
     * Définit la plage horaire pour le mercredi.
     *
     * @param mercredi la nouvelle plage horaire du mercredi au format "HH:MM-HH:MM".
     */
    public void setMercredi(String mercredi) {
        this.mercredi = mercredi;
    }
    /**
     * Obtient la plage horaire pour le jeudi.
     *
     * @return la plage horaire du jeudi au format "HH:MM-HH:MM".
     */
    public String getJeudi() {
        return jeudi;
    }
    /**
     * Définit la plage horaire pour le jeudi.
     *
     * @param jeudi la nouvelle plage horaire du jeudi au format "HH:MM-HH:MM".
     */
    public void setJeudi(String jeudi) {
        this.jeudi = jeudi;
    }
    /**
     * Obtient la plage horaire pour le vendredi.
     *
     * @return la plage horaire du vendredi au format "HH:MM-HH:MM".
     */
    public String getVendredi() {
        return vendredi;
    }
    /**
     * Définit la plage horaire pour le vendredi.
     *
     * @param vendredi la nouvelle plage horaire du vendredi au format "HH:MM-HH:MM".
     */
    public void setVendredi(String vendredi) {
        this.vendredi = vendredi;
    }
    /**
     * Obtient la plage horaire pour le samedi.
     *
     * @return la plage horaire du samedi au format "HH:MM-HH:MM".
     */
    public String getSamedi() {
        return samedi;
    }
    /**
     * Définit la plage horaire pour le samedi.
     *
     * @param samedi la nouvelle plage horaire du samedi au format "HH:MM-HH:MM".
     */
    public void setSamedi(String samedi) {
        this.samedi = samedi;
    }
    /**
     * Obtient la plage horaire pour le dimanche.
     *
     * @return la plage horaire du dimanche au format "HH:MM-HH:MM".
     */
    public String getDimanche() {
        return dimanche;
    }
    /**
     * Définit la plage horaire pour le dimanche.
     *
     * @param dimanche la nouvelle plage horaire du dimanche au format "HH:MM-HH:MM".
     */
    public void setDimanche(String dimanche) {
        this.dimanche = dimanche;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la plage horaire.
     *
     * @return une chaîne décrivant les plages horaires pour chaque jour de la semaine.
     */
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