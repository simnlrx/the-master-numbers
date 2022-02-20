package fr.simlrx.themaster.game;

import fr.simlrx.themaster.TheMasterNumbers;
import fr.simlrx.themaster.utils.Color;

import java.util.Random;

public class Board {

    /**
     * Déclaration du tableau Case en deux dimension
     */
    private Case[][] cases;

    /**
     * Constructeur standard de la classe board
     * @param h hauteur du plateau
     * @param w largueur du plateau
     */
    public Board(int h, int w) {
        cases = new Case[h][w];
    }

    /**
     * Accesseur en lecture de la largeur du tableau
     * @return la largeur du tableau créé
     */
    public int getWidth() {
        if (cases == null || getHeight() == 0) return -1;
        return cases[0].length;
    }

    /**
     * Accesseur en lecture de la longueur du tableau
     * @return la longueur du tableau créé
     */
    public int getHeight() {
        if (cases == null) return -1;
        return cases.length;
    }

    /**
     * Accesseur en lecture de la case selectionner en paramètre
     * @param i permet de selectionner la ligne du tableau
     * @param j permet de selectionner la ligne du colonne
     * @return la case selectionnée
     */
    public Case getCase(int i, int j) {
        return cases[i][j];
    }

    /**
     * Accesseur en lecture de l'attribut cases
     * @return le tableau créé
     */
    public Case[][] getCases() {
        return cases;
    }

    /**
     * Genère des nombres aléatoire mit dans chaqune des cases du tableau avec un indice aléatoire actif
     * Ces nombres et ces indices sont créé par un objet case grâce a son constructeur.
     *
     * @param max valeur maximum d'une case
     */
    public void generateBoard(int max) {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                cases[i][j] = new Case(new Random().nextInt(max) + 1, TheMasterNumbers.getInstance().getSettings().getRandomClue());
            }
        }
    }

    /**
     * Affiche de tableau caché, avec les indices visibles si l'utilisateur l'a demander
     * @return plateau avec les cases découverte
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Case current = cases[i][j];
                if (current.isReveal()) {
                    res += String.valueOf(current.getValue()) + current.getClue().getSymbol() + " ";
                } else {
                    res += "#";
                    if (TheMasterNumbers.getInstance().getSettings().isVisible()) {
                        res += current.getClue().getSymbol();
                    } else {
                        res += "# ";
                    }
                }
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Affiche le plateau avec les cases et les indices visibles
     * @return le plateau visible
     */
    public String toStringVisble() {
        String res = "";
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Case current = cases[i][j];
                res += Color.BLUE + current.getValue() + current.getClue().getSymbol() + Color.RESET + " ";
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Affiche le plateau de supposition
     * @param player permet d'identifier le joueur qui est en train de jouer
     * @return plateau cacher avec les valeurs trouver afficher et les couleurs si le joueur a faire une supposition
     */
    public String toString(Player player) {
        String res = "";
        Settings settings = TheMasterNumbers.getInstance().getSettings();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Case current = cases[i][j];
                if (current.isReveal()) {
                    res += Color.CYAN + current.getValue() + current.getClue().getSymbol() + " " + Color.RESET;
                } else if (player.getSupposition() != null && (player.getSupposition().getCase1().equals(current) || player.getSupposition().getCase2().equals(current))) {
                    res += Color.YELLOW + "#" + Color.RESET;
                    if (settings.isVisible()) {
                        res += Color.YELLOW + current.getClue().getSymbol() + Color.RESET + " ";
                    } else {
                        res += Color.YELLOW + "# " + Color.RESET;
                    }
                } else {
                    res += Color.RESET + "#";
                    if (settings.isVisible()) {
                        res += current.getClue().getSymbol() + " ";
                    } else {
                        res += "# ";
                    }
                }
            }
            res += "\n";
        }
        return res;
    }

    /**
     * Affiche le plateau de fin de jeu
     * @return plateau du jeu en fin de partie avec les cases en bleu si découvert et rouge sinon non découvert
     */
    public String toStringEndGame() {
        String res = "";
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Case current = cases[i][j];
                if (current.isReveal()) {
                    res += Color.CYAN + current.getValue() + current.getClue().getSymbol() + " " + Color.RESET;
                } else {
                    res += Color.RED + current.getValue() + current.getClue().getSymbol() + " " + Color.RESET;
                }

            }
            res += "\n";
        }
        return res;
    }



}
