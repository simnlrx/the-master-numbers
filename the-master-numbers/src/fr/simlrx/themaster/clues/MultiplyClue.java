package fr.simlrx.themaster.clues;

import fr.simlrx.themaster.game.Case;

public class MultiplyClue extends Clue {

    /**
     * Constructeur de la classe MultiplyClue
     */
    public MultiplyClue() {
        super("Multiplication", '*');
    }

    /**
     * Multiplication de deux cases
     * @param case1 case1 du constructeur abstrait pour les calculs
     * @param case2 case2 du constructeur abstrait pour les calculs
     * @return le résultat de case2 * case1
     */
    @Override
    public int get(Case case1, Case case2) {
        return case1.getValue() * case2.getValue();
    }

}
