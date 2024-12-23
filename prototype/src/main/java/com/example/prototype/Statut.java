package com.example.prototype;
/**
 * Représente les différents statuts qu'un projet peut avoir dans l'application MaVille.
 * <p>
 * Cette énumération définit les états possibles d'un projet, permettant de suivre et de
 * gérer l'avancement des travaux associés.
 * </p>
 *
 * <p>
 * Les états incluent :
 * <ul>
 *   <li>{@code PREVU} : Le projet est prévu et n'a pas encore commencé.</li>
 *   <li>{@code ENCOURS} : Le projet est en cours de réalisation.</li>
 *   <li>{@code SUSPENDU} : Le projet est suspendu temporairement.</li>
 *   <li>{@code TERMINE} : Le projet est terminé.</li>
 * </ul>
 */
public enum Statut {
    PREVU,
    ENCOURS,
    SUSPENDU,
    TERMINE

}
