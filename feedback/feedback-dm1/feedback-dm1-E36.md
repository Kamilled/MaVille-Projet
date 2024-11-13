# Feedback DM1

## Glossaire

- Il manque beaucoup trop de définitions. Il manque toutes les définitions importantes.
- Le peu de définitions données ne sont pas rapportées au contexte de l'application.

## Diagramme de CUs

- Le diagramme n'indique pas qui est acteur principal ou secondaire.
- Il manque l'acteur "Services de la ville" ou "Ville" pour gérer l'inscription de l'intervenant et la prise en charge des problèmes signalés par le résident.
- Il aurait fallu mettre un acteur "Utilisateur" et le généraliser au résident et à l'intervenant, puis lui donner le CU "Se connecter", qui est manquant dans le diagramme...
- Pourquoi "Signaler un problème à la ville" est une extension de "Permettre une planification participative"? L'un ne permet pas l'autre.
- Il manque plusieurs CUs importants comme "Soumettre une candidature à une requête", "Accepter/refuser une candidature",... Les CUs écrits en gros dans l'énoncé ne sont pas suffisants.

## Scénarios

- Il y a plusieurs soucis de formattage, comme dans "Créer un compte" où l'étape 1 est répétée 2 fois, l'étape 3 est répétée 3 fois, et l'étape alternative 3a ne fait pas de sens.
- Plusieurs des scénarios alternatifs proposés ne sont pas suffisamment claires sur les erreurs/bifurcations qui les causent. Quelles informations sont incorrectes? Que fait l'utilisateur pour régler ça?
- "Recevoir des notifications personnalisées" ne se limite pas à juste s'abonner à un travail. Il peut aussi décider dans quels quartiers et quelles rues il veut être notifié quand des travaux s'y déroulent.
- Pourquoi avoir combiné 3 CUs en même temps ? Permettre une planification participative, soumettre une demande de travail et signaler un problème à la ville sont 3 CUs complètement différents. 
- Soumettre un projet de travail est différent de mettre à jour les informations d'un chantier. 

## Diagramme d'activités

- Le diagramme 1, qui est censé être le diagramme principal, n'a non seulement pas de noeud de fin d'activité, mais les actions décrites dans les sous-diagrammes suivants n'y sont pas inclus alors qu'il est censé représenter le flux complet de l'application.
- Le titre des diagrammes ne sont pas représentatifs de l'action qu'ils décrivent.
- Une barre de jonction doit toujours être précédée d'une barre de bifurcation, pas d'un noeud de décision. Les deux ne s'utilisent pas de la même manière. 
- Il manque une grande partie des diagrammes d'activités requis. Parmi les scénarios que vous avez décrits, il n'y a que "Rechercher des travaux" qui est inscrit dans le diagramme avec un minimum de détails. Tout le reste est représenté comme une seule action dans le diagramme, sans détails sur leur flot.

## Analyse

### Risques

- La justification pour le risque 3 n'inclut pas du tout de contexte par rapport à notre application.

### Besoins non-fonctionnels

Bien!

### Besoins matériels

Bien!

### Solution de stockage

Bien!

### Solution d'intégration

Bien!

## Prototype

- Il n'y a pas de fichier .jar.
- Les menus sont distincts quand on se connecte en tant que résident et en tant qu'intervenant, mais... l'application se ferme directement après avoir affiché le menu.

## Git

Bien!

## Rapport

- Le lien vers le répertoire GitHub n'emmène nulle part.
- Les informations nécessaires pour lancer le prototype correctement ne sont pas mentionnés dans le rapport.
