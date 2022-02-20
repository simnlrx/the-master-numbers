package fr.simlrx.themaster.game;

import fr.simlrx.themaster.clues.Clue;

public class Case {

    //Déclaration des attributs de l'objet Case
    private final int value;
    private Clue clue;
    private boolean reveal;

    //Constructeur standard de la classe case
    private Case(int value, Clue clue, boolean reveal) {
        this.value = value;
        this.clue = clue;
        this.reveal = reveal;
    }

    //Second constructeur de la classe case. C'est le constructeur par défaut. L'autre est inaccessible car de base, une case n'est pas révélée.
    public Case(int value, Clue clue) {
        this(value, clue, false);
    }

    /**
     * Accesseur en lecture de l'attribut value
     * @return la valeur de la case
     */
    public int getValue() {
        return value;
    }

    /**
     * Accesseur en lecture de l'attribut clue
     * @return l'indice de la case
     */
    public Clue getClue() {
        return clue;
    }

    /**
     * Accesseur en écriture de l'attribut clue
     * @param clue indice a attribuer a la case
     */
    public void setClue(Clue clue) {
        this.clue = clue;
    }

    /**
     * Accesseur en lecture de l'attribut reveal
     * @return vrai si la case a été découverte/faux si la case n'a pas été découverte
     */
    public boolean isReveal() {
        return reveal;
    }

    /**
     * Accesseur en écriture de l'attribut reveal
     * @param reveal vrai/faux a appliquer a l'attribut (si case découverte = vrai)
     */
    public void setReveal(boolean reveal) {
        this.reveal = reveal;
    }
}
