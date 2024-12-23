package com.example.prototype;
/**
 * Représente les différents types de travaux dans l'application MaVille.
 * <p>
 * Cette énumération définit les catégories de travaux pouvant être associées
 * aux projets, permettant de classifier et de gérer les divers types d'interventions.
 * </p>
 *
 * <p>
 * Les types de travaux incluent :
 * <ul>
 *   <li>{@code TRAVAUXROUTIERS} : Travaux liés aux routes et infrastructures routières.</li>
 *   <li>{@code TRAVAUXGAZELECTRICITE} : Travaux liés aux installations de gaz et d'électricité.</li>
 *   <li>{@code CONSTRUCTIONRENOVATION} : Travaux de construction ou de rénovation de bâtiments.</li>
 *   <li>{@code ENTRETIENPAYSAGER} : Travaux d'entretien paysager et de jardins.</li>
 *   <li>{@code TRAVAUXTRANSPORTPUBLIC} : Travaux liés aux infrastructures de transport public.</li>
 *   <li>{@code TRAVAUXSIGNALISATIONECLAIRAGE} : Travaux de signalisation et d'éclairage public.</li>
 *   <li>{@code TRAVAUXSOUTERRAINS} : Travaux réalisés sous terre, tels que les réseaux souterrains.</li>
 *   <li>{@code TRAVAUXRESIDENTIELS} : Travaux effectués dans des zones résidentielles.</li>
 *   <li>{@code ENTRETIENURBAIN} : Travaux d'entretien des espaces urbains.</li>
 *   <li>{@code ENTRETIENRESEAUXTELECOMMUNICATION} : Travaux liés à l'entretien des réseaux de télécommunication.</li>
 * </ul>
 * </p>
 */
public enum TypeTravaux {
    TRAVAUXROUTIERS,
    TRAVAUXGAZELECTRICITE,
    CONSTRUCTIONRENOVATION,
    ENTRETIENPAYSAGER,
    TRAVAUXTRANSPORTPUBLIC,
    TRAVAUXSIGNALISATIONECLAIRAGE,
    TRAVAUXSOUTERRAINS,
    TRAVAUXRESIDENTIELS,
    ENTRETIENURBAIN,
    ENTRETIENRESEAUXTELECOMMUNICATION
}
