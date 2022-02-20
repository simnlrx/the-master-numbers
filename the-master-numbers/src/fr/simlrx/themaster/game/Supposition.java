package fr.simlrx.themaster.game;

public class Supposition {

    /**
     * Déclaration des attributs de la classe Clue.
     */
    private Case case1;
    private Case case2;

    /**
     * Constructeur par defaut de la classe Clue
     * @param case1 case1 de la supposition
     * @param case2 case2 de la supposition
     */
    public Supposition(Case case1, Case case2) {
        this.case1 = case1;
        this.case2 = case2;
    }

    /**
     * Accesseur en lecture de case1
     * @return case1
     */
    public Case getCase1() {
        return case1;
    }

    /**
     * Accesseur en lecture de case2
     * @return case2
     */
    public Case getCase2() {
        return case2;
    }

}
