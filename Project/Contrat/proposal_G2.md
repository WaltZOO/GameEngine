# CONTRAT
> G2
>Erwan PONCIN, Maxime BOSSANT, Lucas MARTINS, Victor BADIN, Enzo PERRIN, Maxence MAURY


## Moteur de jeu STEVEN :

- Monde 2D vu du dessus
- Grande carte sur laquelle on se déplace
- Map plus grande que la vue
- Génération de map aléatoire : assemblage aléatoire de patterns. On retrouve dans le fichier de configuration, la description des patterns qui contient : une densité, un rayon (taille du pattern), le nombre de fois que le pattern apparait et une position dans la carte. 

- Deux joueurs (écran scindé)
- Système de coordonnées
  - On représente le monde comme une liste d’entités où chacune a des coordonnées x et y qui représente son placement dans le modèle. 
    La partie view permet d'afficher leur placement et la partie Model de les replacer.
  - Les hitboxs sont pour toutes les entités un cercle.
  - Toutes les entités ont la même hitbox (pour simplifier la recherche de voisinage)


- Système de voisinage :
    - On découpe le modèle comme une grille et chaque coordonnée est dans une case (quad tree non-dynamique).
    - Pour savoir quelles sont les entités avec qui il y a risque de collision, on regarde les entités dans la case dans laquelle on est et dans les cases adjacentes (ssi distance avec fin de case hitbox d’une entité), voir `Figure 2` .

- n mondes:
    - n mondes **non dynamiques** (n le nombre de catégorie d'entité.). Il existe un monde par type d'entité (ex : un monde pour chaque portail, un pour les arbres...).
    - Lorsqu'on emprunte un portail, on apparait au milieu du nouveau monde.  

    - Les players Pick sont envoyées dans le monde de l’entité
    - Les mondes sont générés grâce aux indications du fichier de configuration (1 type d’entité = 1 configuration)
    - Les mondes sont générés au lancement du jeu.

- Système de but : 
  - Un timer au bout duquel le jeu est fini.
  - Une condition de fin : on regarde la présence ou la non présence d'une liste d'items d'un monde
    - exemple de monde : inventaire ou carte
    - exemple de condition de fin : avoir récolté tous les items ou qu'il n'y ait plus de zombies sur la carte.
 


# Jeu 1 : 
## **ON THE RUN**
Jeu de type cache-cache
### But :
Pour gagner :

Le player1 doit pick le player2 avant la fin d'un timer (définit dans le fichier de configuration).

Le player2 doit lui échapper au player1 jusqu'à la fin du timer.

- 2 joueurs s'affrontent
- Bots : des chats
- Création dynamique d'entités : les chats se reproduisent

- Le player1 (qui cherche le player2) a un plus grand champ de vision et avance plus vite (que le player2).
  
- Système de sac à dos qui est un monde non affiché permettant de prendre l'autre player.

- 4 mondes reliés par des portails à sens unique (voir `Figure 4`)

- Description des mondes : le monde Tree contient énormément des arbres, le monde Rock plein de cailloux, le monde Cat pleins de chats et le monde Sand plein de sable

- Mob cap : on limite le nombre maximum d'entités (hors player) dans les mondes (ex : le nombre de chats dans le monde Cat).

- Déplacement dans les mondes en passant par des portails (plusieurs classes Portail pour pouvoir faire des mondes différents).





# Jeu 2 :
## **DONJON** 
Jeu d'extermination de mobs
### But :
Pour gagner : 
Les players doivent tuer tous les mobs dans chaque mondes. 

- Spawn dans un donjon avec plusieurs niveaux.

- Difficulté croissante. Plus on avance dans les mondes, plus il y a des mobs à tuer. La dernière salle contient un boss final qui va Egg des zombies tant qu'il est vivant.

- Mondes reliés linéairement (voir `Figure 3`).

- Changement de monde en passant par des portails.

- Nombre de mondes fixé dans le fichier de configuration.

- Système de respawn : chaque player peut respawn à l'infini

- Génération des mondes au lancement du jeu.

- Arrêt des ticks d’un monde quand aucun player n’y est.







## Annexes
### Figure 1 :
![Alt text](https://image.noelshack.com/fichiers/2024/24/1/1718022931-screenshot-from-2024-06-07-13-07-30.png)



<center>Explication de la gestion des collisions</center>


### Figure 2 :
![Alt text](https://image.noelshack.com/fichiers/2024/24/2/1718093002-screenshot-from-2024-06-11-10-02-22.png) 
<center>Gestion linéaire des salles</center>

### Figure 3 :
![Alt text](https://image.noelshack.com/fichiers/2024/24/2/1718110027-screenshot-from-2024-06-11-14-46-55.png)

