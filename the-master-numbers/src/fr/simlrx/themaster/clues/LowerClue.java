package fr.simlrx.themaster.clues;

import fr.simlrx.themaster.game.Case;

public class LowerClue extends Clue {

    /**
     * Constructeur de la classe LowerClue
     */
    public LowerClue() {
        super("Inférieur", '<');
    }

    /**
     * Calcule de la valeur la plus petite entre case1 et case2
     * @param case1 case1 du constructeur abstrait pour les calculs
     * @param case2 case2 du constructeur abstrait pour les calculs
     * @return le plus petit nombre des deux
     */
    @Override
    public int get(Case case1, Case case2) {
        return Math.min(case1.getValue(), case2.getValue());
    }

}
