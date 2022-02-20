package fr.simlrx.themaster;

import fr.simlrx.themaster.game.Board;
import fr.simlrx.themaster.game.Game;
import fr.simlrx.themaster.game.Settings;

/**
 * @author LE ROUX Simon
 * @version 1.7
 */

public class TheMasterNumbers {

    /**
     * Déclaration des attributs de la classe principale TheMasterNumbers
     */
    private static TheMasterNumbers instance;
    private Settings settings;
    private Game game;


    /**
     * Constructeur principale du programme
     * @param args
     */
    public static void main(String[] args) {
        instance = new TheMasterNumbers();
        instance.init();
    }

    /**
     * Initialisation du jeu et des instances des autres classes nécessaire au jeu
     * @return la classe pour l'instance
     */
    public TheMasterNumbers init(){
        settings = new Settings();
        settings.init();
        Board board = new Board(2, 3); //Mettre des valeurs paires !
        System.out.println("> Taille du plateau: " + board.getHeight() + "x" + board.getWidth());
        game = new Game(board);
        game.run();

        return this;
    }

    /**
     * Accesseur en lecture de l'instance de la classe principale
     * @return cette instance
     */
    public static TheMasterNumbers getInstance() {
        return instance;
    }

    /**
     * Accesseur en lecture de l'instance de la classe settings
     * @return instance de settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Accesseur en lecture de l'instance de la classe game
     * @return instance de gale
     */
    public Game getGame() {
        return game;
    }
}
