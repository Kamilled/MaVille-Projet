package com.example.prototype;

import java.util.List;
/**
 * Représente une entrave causée par un travail public ou privé.
 * <p>
 * Cette classe contient des informations sur le travail associé à l'entrave
 * et les rues affectées par celle-ci.
 * </p>
 *
 * <p>
 * Elle permet de suivre les entraves routières liées aux projets de travaux,
 * facilitant ainsi la gestion et la communication des perturbations causées
 * par ces travaux.
 * </p>
 *
 * @author
 *         Aya Elbroumi
 */

public class Entrave {
    /**
     * Le travail associé à cette entrave.
     */
    private String travailAssocié;
    /**
     * La liste des rues affectées par cette entrave.
     */
    private List<String> rues;

    /**
     * Constructeur pour créer une nouvelle entrave.
     *
     * @param travailAssocié le travail associé à cette entrave.
     * @param rues la liste des rues affectées par cette entrave.
     */
    public Entrave(String travailAssocié, List<String> rues) {
        this.travailAssocié = travailAssocié;
        this.rues = rues;
    }
    /**
     * Obtient le travail associé à cette entrave.
     *
     * @return le travail associé.
     */
    public String getTravailAssocié() {
        return travailAssocié;
    }
    /**
     * Obtient la liste des rues affectées par cette entrave.
     *
     * @return la liste des rues affectées.
     */
    public List<String> getRues() {
        return rues;
    }
    /**
     * Définit le travail associé à cette entrave.
     *
     * @param travailAssocié le nouveau travail associé.
     */
    public void setTravailAssocié(String travailAssocié) {
        this.travailAssocié = travailAssocié;
    }
    /**
     * Définit la liste des rues affectées par cette entrave.
     *
     * @param rues la nouvelle liste des rues affectées.
     */
    public void setRues(List<String> rues) {
        this.rues = rues;
    }
}
