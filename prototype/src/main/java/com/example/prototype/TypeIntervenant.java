package com.example.prototype;
/**
 * Représente les différents types d'intervenants dans l'application MaVille.
 * <p>
 * Cette énumération définit les catégories d'intervenants pouvant être associés
 * aux projets de travaux, permettant de différencier les intervenants selon leur nature.
 * </p>
 *
 * <p>
 * Les types incluent :
 * <ul>
 *   <li>{@code ENTREPRISEPUBLIC} : Entreprise publique impliquée dans les travaux.</li>
 *   <li>{@code ENTREPRENEURPRIVE} : Entrepreneur privé chargé des travaux.</li>
 *   <li>{@code PARTICULIER} : Particulier participant aux travaux.</li>
 * </ul>
 */
public enum TypeIntervenant {
    ENTREPRISEPUBLIC,
    ENTREPRENEURPRIVE,
    PARTICULIER
    
}
