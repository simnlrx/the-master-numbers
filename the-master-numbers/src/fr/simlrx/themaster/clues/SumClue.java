package fr.simlrx.themaster.clues;

import fr.simlrx.themaster.game.Case;

public class SumClue extends Clue{

    /**
     * Constructeur de la classe SumClue
     */
    public SumClue() {
        super("Somme", '+');
    }

    /**
     * Addition de deux cases
     * @param case1 case1 du constructeur abstrait pour les calculs
     * @param case2 case2 du constructeur abstrait pour les calculs
     * @return case1 + case2
     */
    @Override
    public int get(Case case1, Case case2) {
        return case1.getValue() + case2.getValue();
    }

}
