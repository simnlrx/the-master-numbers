package fr.simlrx.themaster.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    /**
     * Déclaration des arributs de l'object Player
     */
    private final String name;
    private Supposition supposition;
    private int score;
    private List<Case> cases;

    /**
     * Constructeur standard de la classe Player
     * @param name nom du joueur
     * @param score score du joueur
     */
    private Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.supposition = null;
        this.cases = new ArrayList<Case>();
    }

    /**
     * Constructeur par défaut de la classe Player
     * @param name nom du joueur
     */
    public Player(String name){
        this(name, 0);
    }

    /**
     * Accesseur en lecture de name
     * @return nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Accesseur en lecture du score
     * @return score du joueur
     */
    public int getScore() {
        return score;
    }

    /**
     * Accesseur en écriture du score
     * @param score a addition au score du joueur
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Accesseur en écriture du score
     * @param score a soustraire au score du joueur
     */
    public void removeScore(int score) {
        this.score -= score;
    }

    /**
     * Accesseur en lecture de supposition
     * @return la supposition du joueur
     */
    public Supposition getSupposition() {
        return supposition;
    }

    /**
     * Accesseur en écriture de supposition
     * @param supposition supposition a ajouter au joueur
     */
    public void setSupposition(Supposition supposition) {
        this.supposition = supposition;
    }

    /**
     * Accesseur en lecture de la liste de case du joueur
     * @return liste de case
     */
    public List<Case> getCases() {
        return cases;
    }

    /**
     * Accesseur en ecriture de la liste de case
     * @param cases case a ajouter a la liste
     */
    public void addCases(Case cases) {
        this.cases.add(cases);
    }

}
