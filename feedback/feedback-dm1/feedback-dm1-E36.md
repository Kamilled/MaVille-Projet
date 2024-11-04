# Feedback DM1

## Glossaire

- Il manque quelques définitions importantes : "MaVille", "Code de la ville", "Notifications personnalisées", "Type de problèmes/signaler un problème".
- "Entrepreneur" n'est pas une définition assez pertinente ici car elle n'a en aucun point un lien avec le cadre de notre application. Et même si c'était le cas, votre définition n'inclut pas le contexte de l'application.
- "Service client" aurait pu être pertinent si vous avez aussi inclus un rapport avec le cadre de l'application MaVille.

## Diagramme de CUs

- "Inscription" comme étant un CU abstrait d'utilisateur ne marche pas, car on peut alors que c'est un CU distinct des deux possibilités d'inscription. Il était plus simple de distinguer les deux CUs d'inscription résident/intervenant et les relier directement à l'acteur correspondant.
- Les CUs ne sont pas une extension du CU "Se connecter". Au contraire, le CU "Se connecter" est une étape obligatoire avant chaque CU (donc utiliser <<include>> au lieu de <<extends>>).
- Il manque l'acteur secondaire "Service de la ville" ou "Ville" pour vérifier l'inscription de l'intervenant et recueillir les problèmes signalés par le résident.
- On ne peut pas juste mentionner "Requête de travail" comme étant un CU. "Créer une requête de travail" aurait été correct.
- Il est tout de même important de mentionner qu'un résident doit pouvoir accepter/refuser la candidature d'un intervenant à une de ses requêtes.

## Scénarios

- Si vous avez mis "Se connecter" comme étant un CU <<include>> à tous les autres CUs (ce que je suppose que vous avez fait même si vous avez mis <<extends>> à la place), alors "Se connecter" est une étape obligatoire au début de tous les scénarios et non une précondition.

## Diagramme d'activités

- En général, il faut toujours trouver un moyen de sortir d'une boucle possiblement infini créée par un noeud de décision en incluant une possibilité de fin de flot.
- Le diagramme n'inclut *aucun* noeud de fin d'activité.
- Le diagramme n'est pas consistant avec les scénarios écrits précédemment. Par exemple, quand le résident soumet une requête de travail, il n'est mentionné en aucun point par le scénario correspondant qu'il peut ensuite voir et accepter la candidature d'un intervenant sur une requête déjà créée.
- Le sens des flèches à la fin ne sont pas du tout claires. Où vont les flèches qui se rencontrent en angle droit?
- Un noeud de décision doit toujours être suivi d'un noeud de fusion, pas d'une barre de jonction. Inversement, une barre de jonction est toujours précédée d'une barre de bifurcation. Et ces deux séparations n'ont pas le même comportement.

## Analyse

### Risques 

Bien!

### Besoins non-fonctionnels

- La justification de chaque besoin non-fonctionnel doit inclure le cadre et le contexte de l'application, sinon elle est trop générale. 
- Le mot que vous cherchez pour le besoin n.4 est "scalabilité".

### Besoins matériels

- Il faut aussi parler de ce dont l'utilisateur a besoin pour lancer l'application (en l'occurence, un ordinateur avec Java), et inclure une analyse de coûts.

### Solution de stockage

- Vous choisirez quelle architecture ? Quel paradigme précis de SQL (NoSQL, PostgreSQL,...) ? Comment ces données seront sécurisées ? Il faut aussi illustrer quelle procédure vous utiliserez pour stocker les données avec SQL.

### Solution d'intégration

Bien!

## Prototype

- Le prototype n'est pas robuste face aux mauvais input (écrire une lettre au lieu d'un chiffre).
- Euh, je ne peux même pas me connecter à l'application. En regardant votre code, je vois que `resident.checkPassword(password)` retourne toujours `false`. Il faut changer ça.
- À part ça, les menus principaux sont simples et intuitifs.

## Git

Bien!

## Rapport

Bien!
