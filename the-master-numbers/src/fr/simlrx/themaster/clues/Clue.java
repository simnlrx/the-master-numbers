package fr.simlrx.themaster.clues;

import fr.simlrx.themaster.game.Case;

public abstract class Clue {

    /**
     * Déclaration des attributs de la classe Clue.
     */
    private String name;
    private char symbol;

    /**
     * Constructeur standard de la classe Clue
     * @param name nom des indices
     * @param symbol symbole des indices
     */
    public Clue(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    /**
     * Constructeur absrait de la classe Clue qui permet de créé des indices de different type pour les calculs indice1 x indice2.
     * @param case1 case1 du constructeur abstrait pour les calculs
     * @param case2 case2 du constructeur abstrait pour les calculs
     */
    public abstract int get(Case case1, Case case2);

    /**
     * Accesseur en lecture de name
     * @return nom de l'indice
     */
    public String getName() {
        return name;
    }

    /**
     * Accesseur en lecture de symbol
     * @return symbole de l'indice
     */
    public char getSymbol() {
        return symbol;
    }

}
