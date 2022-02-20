package fr.simlrx.themaster.game;

import fr.simlrx.themaster.clues.Clue;
import fr.simlrx.themaster.clues.MultiplyClue;
import fr.simlrx.themaster.clues.SumClue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Settings {

    /**
     * Déclaration des attributs de la classe Settings
     */
    private List<Clue> clues;
    private boolean isVisible;
    private boolean hardmode;

    /*+
     * Constructeur standard de la classe Settings
     */
    public Settings() {
        this.isVisible = false;
        this.hardmode = false;
    }

    /**
     * Fonction d'initialisation des paramètres
     */
    public void init() {
        this.clues = new ArrayList<>();
        this.clues.add(new MultiplyClue());
        this.clues.add(new SumClue());
    }

    /**
     * Accesseur en lecture de la liste de clue
     * @return liste de clue
     */
    public List<Clue> getClues() {
        return clues;
    }

    /**
     * Accesseur en écriture de la liste de clue
     * @param clue clue a ajouter a la liste
     */
    public void addClues(Clue clue) {
        this.clues.add(clue);
    }

    /**
     * Prend un indice aléatoire dans la liste des indices actifs
     * @return clue aléatoire contenue dans la liste
     */
    public Clue getRandomClue(){
        return this.clues.get(new Random().nextInt(this.clues.size()));
    }

    /**
     * Accesseur en lecture de isVisible
     * @return la valeur de isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Changer l'état de la visibilité des indices sur le plateau
     * @param isVisible true si les indices sont visible sur le plateau, false sinon
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Accesseur en lecture de hardmode
     * @return true si actif, false sinon
     */
    public boolean isHardmode() {
        return hardmode;
    }

    /**
     * Accesseur en écriture de hardmode
     * @param hardmode true si actif, false sinon
     */
    public void setHardmode(boolean hardmode) {
        this.hardmode = hardmode;
    }
}
