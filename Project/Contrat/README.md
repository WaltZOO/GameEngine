# Projet Moteur de Jeu
> G2
>Erwan PONCIN, Maxime BOSSANT, Lucas MARTINS, Victor BADIN, Enzo PERRIN, Maxence MAURY


## 1. Installation sur Eclipse :
- Cloner le repository ```git clone git@gricad-gitlab.univ-grenoble-alpes.fr:michael_perin_private_project/2023_info3_ple/g2.git```
- faire un ```make``` dans le dossier ```Project/GameEngine/info3.game.given.2021/src/gal/parser```
- En cas d'erreur d'import des librairies json, il faut ajouter les dépendances aux fichiers .jar présent dans le dossier ```resources/json```.


## 2. Structuration du code :

- 1 partie Graphique et Controller dans `/Project/GameEngine/info3.game.given.2021/game/info3/game/`
- 1 partie Model dans `/Project/GameEngine/info3.game.given.2021/game/info3/src/model`
- 1 partie Bot dans `/Project/GameEngine/info3.game.given.2021/game/info3/src/ai`


## 3. Instruction de lancement :
- Pour lancer un jeu, il faut éxécuter la méthode main dans le fichier `Game.java` situé dans le dossier `/Project/GameEngine/info3.game.given.2021/game/info3/game/`.
    
    Les 2 fichiers de configurations sont situés dans le dossier ```./resources/json/```. 

- Pour changer de jeu il suffit de modifier le champ `argument` dans game avec le chemin d'accès au fichier de configuration.
- Ajout de fichiers .gal dans `/Project/GameEngine/info3.game.given.2021/resources/gal`.
- Ajout d'images dans `/Project/GameEngine/info3.game.given.2021/resources/png `.

### Description du fichier de configurations

On peut séparer le fichier de configuration en 3 parties : 
#### Les Conditions de victoire : 

Les conditions de victoire permettent de définir à quel moment le jeu prend fin. 

Une condition de fin se décline sous 2 forme : une liste d'entité présente (ou non) dans un monde ou une limite de temps. 

Ces conditions permettent d'afficher en conséquence le message de victoire adapté.


L'exemple qui suit a été utilisé pour le jeu1. Le jeu se termine si le monde `"Bag1"` contient le `player2` ou 3000sec sont passées. 
```json
    "Win": {
        "Cond1": {
                "Entity": [
                    "Player2"
                ],
                "World": "Bag1",
            "present" : "true",
            "win_cond": "cond1",
            "win_msg": "VICTOIRE DU PLAYER 1 !"
        },
        "Timer": {
            "timer": 3000,
            "win_cond": "timer",
            "win_msg": "VICTOIRE DU PLAYER 2 !"
        },
        "cond_final" : "Cond1 || Timer"
    }
```
Le nom de la condition `Timer` est fixe pour ajouter une limite de temps condition.

Le champ `cond_final` a lui aussi un nom fixe et sert à réaliser les opérations booléenes entre toutes les conditions définies précédemment. Ce champ **doit** inclure toutes les conditions déclarer auparavant.

Le champ `present` permet d'indiquer si les entités de la condition doivent être présente ou non pour vérifier la condition.
    
Le message de victoire ```"win_msg"``` peut être propre à une condition (le champ msg est dans la condition) ou général pour la partie (le champ msg est déclaré au début). Dans ce cas, peu importe la condition remplie, si la partie se termine ce message s'affichera et pas les messages des conditions.

#### Les Entités :

Chaque entité possède un champ ```"pickable"``` qui correspond à la liste des types d'entités prenable (catégorie ```P``` en gal).

Une entité référence un monde ```"dest"```, le monde de destination d'un ```Pick()```.

Une entité possède un automate écrit en ```gal```.

#### Il y a 2 types d'entités :   les Blocs et les Personnages.

Un Bloc contrairement à un Personnage est une entité pasive. Un Bloc ne peut pas attacker et ne peux pas etre attacké mais peut ``` Move(), Turn(), Pick() et Wait() ```

```json
    "Tree": {
            "name": "Tree",
            "dest": null,
            "pickable": [],
            "position": [
                0,
                0
            ],
            "speed": 1,
            "direction": "N",
            "reach": 4,
            "FSM": "./resources/gal/rien.gal",
            "sprite": "./resources/png/Tree.png"
        }
```

Un Personnage est une entité mobile possèdant de la vie, une champ de vision, une zone de frape, des dégat, une vitesse, des ennemies et des alliés.

```json
    "Zombie": {
        "name": "Zombie",
        "dest": null,
        "position": [
            0,
            0
        ],
        "direction": "N",
        "speed": 1,
        "hp": 10,
        "damage": 5,
        "enemies": [
            "Player1",
            "Player2"
        ],
        "allies": [
            "Boss"
        ],
        "pickable": [],
        "FSM": "./resources/gal/cat.gal",
        "sprite": "./resources/png/Skeleton.png",
        "range": 10,
        "reach": 3
    }
```
Le champ ```"enemies"``` correspond à la liste des types d'entités attaquable (catégorie ```A``` en gal).
    
Le champ ```"allies"``` correspond à la liste des types d'entités alliées (catégorie ```T``` en gal).

Dans chacun de ces tableaux on doit spécifier le nom des entités choisies.

Le champ ```"range"``` correspond au rayon de vision de ce personnage.

Le champ ```"reach"``` correspond au rayon de frappe de ce personnage.


#### Les Mondes : 

Un monde possède une image de fond, une taille, un nombre maximum d'entités et une liste des entités présentes au lancement du jeu.
```json
    "RockWorld": {
        "background": "./resources/png/RockWorld.png",
        "size": [
            100,
            100
        ],
        "isLoaded" : "false",
        "max_entities" : 100,
        "entities": [
            {
                "type": "Rock",
                "density": 0.1
            },
            {
                "type": "PortaltoTree",
                "density": -1
            },
            {
                "type": "PortaltoCat",
                "density": -1
            }
        ]
    }
```
Le champ ```"isLoaded"``` permet de sprécifier si le monde est chargé en arrière plan.
    
Le tableau ```"entities"```permet de spécifier quel entités vont etre générées dans le monde. 
    
Pour chaque élément de ce tableau on a le ```"type"``` et la```"density"``` d'une entité. La densité est un nombre compris en 0 et 1. Il spécifie le taux d'appartion d'une certaine entité dans le monde.

NB : La somme des densités ne doit pas dépasser 1. Une densité de -1 permet de générer qu'une seule entité.
    

### Fichiers gal


- Voici les automates `gal` utilisés les plus pertinents selon nous :

Automate du joueur1 utilisé dans le jeu 2. 

```python
Player(Init) {
    *(Init) 
    | Key(z) & Key(q) & Key(a) ? Hit(NW);Move(NW) : (Init)
    | Key(z) & Key(d) & Key(a) ? Hit(NE);Move(NE) : (Init)
    | Key(s) & Key(q) & Key(a) ? Hit(SW);Move(SW) : (Init)
    | Key(s) & Key(d) & Key(a) ? Hit(SE);Move(SE) : (Init)
    
    | Key(z) & Key(a) ? Hit(N);Move(N) : (Init)
    | Key(q) & Key(a) ? Hit(W);Move(W) : (Init)
    | Key(s) & Key(a) ? Hit(S);Move(S) : (Init)
    | Key(d) & Key(a) ? Hit(E);Move(E) : (Init)

    | Key(a) & Cell(F) ? Hit(F) : (Init)

    | Key(z) & Key(q) ? Move(NW) : (Init)
    | Key(z) & Key(d) ? Move(NE) : (Init)
    | Key(s) & Key(q) ? Move(SW) : (Init)
    | Key(s) & Key(d) ? Move(SE) : (Init)

    | Key(z) ? Move(N) : (Init)
    | Key(q) ? Move(W) : (Init)
    | Key(s) ? Move(S) : (Init)
    | Key(d) ? Move(E) : (Init)
    
    | True ? Wait : (Init)
}
```
On voit que le joueur peut frapper tout en se déplacant.

Touches:
- jeu1:
     - player1
        - `z: move(N)`
        - `q: move(W)`
        - `s: move(S)`
        - `d: move(E)`
        - `a: throw(F)`
        - `e: pick(F)`
    - player2
        - `FU: move(N)`
        - `FR: move(W)`
        - `FD: move(S)`
        - `FL: move(E)`
        - `0: hit(F)`

- jeu2:
    - player1
        - `z: move(N)`
        - `q: move(W)`
        - `s: move(S)`
        - `d: move(E)`
        - `a: hit(F)`
    - player2
        - `FU: move(N)`
        - `FR: move(W)`
        - `FD: move(S)`
        - `FL: move(E)`
        - `0: hit(F)`


Automate du Boss utilisé dans le jeu 2. 

```python
Boss(Init){
	*(Init)

	| Cell(N,A) ? Hit(N) ; Egg(B,T) :(Init)
	| Cell(E,A) ? Hit(E) ; Egg(B,T) :(Init)
	| Cell(S,A) ? Hit(S) ; Egg(B,T) :(Init)
	| Cell(W,A) ? Hit(W) ; Egg(B,T) :(Init)

	| Closest(A,N) & Cell(V,N)? Move(N) / 80%Wait: (Init)
	| Closest(A,E) & Cell(V,E)? Move(E) / 80%Wait: (Init)
	| Closest(A,S) & Cell(V,S)? Move(S) / 80%Wait : (Init)
	| Closest(A,W) & Cell(V,W)? Move(W) / 80%Wait : (Init)
	
	| Closest(A,NE) & Cell(NE,V)? Move(NE) : (Init)
	| Closest(A,SE) & Cell(SE,V)? Move(SE) : (Init)
	| Closest(A,SW) & Cell(SW,V)? Move(SW) : (Init)
	| Closest(A,NW) & Cell(NW,V)? Move(NW) : (Init)

	| True() ? :(Init)
}
```

Le Boss est atiré par le personnage le plus proche, il frappe et fait apparaitre des personnage de son équipe dès qu'il peut.


## 4. Lien vers la vidéo :



## 5. Pourcentages de participation :

- Erwan PONCIN : 16%
- Maxime BOSSANT : 16%
- Lucas MARTINS : 16%
- Victor BADIN : 16%
- Enzo PERRIN : 16%
- Maxence MAURY : 16%

