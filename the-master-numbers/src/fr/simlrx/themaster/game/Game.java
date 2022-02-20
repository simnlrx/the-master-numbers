package fr.simlrx.themaster.game;

import fr.simlrx.themaster.TheMasterNumbers;
import fr.simlrx.themaster.clues.*;
import fr.simlrx.themaster.utils.Console;

import java.util.ArrayList;

public class Game {

    /**
     * Déclaration des attributs de la classe Game
     */
    private Board board;
    private ArrayList<Player> players;
    private ArrayList<Clue> clues = new ArrayList<>();
    private boolean stop = false;

    /**
     * Constructeur standard de la classe Game
     * @param board plateau de la partie en cours
     * @param players liste des joueurs de la parties
     */
    private Game(Board board, ArrayList<Player> players) {
        this.board = board;
        this.players = players;
    }

    /**
     * Constructeur par défaut de la class Game. Le constructeur standard est privé
     * @param board plateau sur le quel se déroule la partie
     */
    public Game(Board board) {
        this(board, new ArrayList<>());
    }

    /**
     * Gère l'intégralité du jeu.
     * Ajoute les joueurs
     * Ajoute les indices
     * Active ou non la visibilité des indinces
     * Lance les tours des joueurs
     */
    public void run() {
        clues.add(new LowerClue());
        clues.add(new SubtractionClue());
        clues.add(new UpperClue());

        System.out.println(" ");
        System.out.println("> Pour répondre aux questions, il faut écrire \"o\" pour oui et \"n\" pour non.");
        System.out.println(" ");

        do {
            addPlayer();
        } while (addAnOtherPlayer());

        for(int i = 0; i < clues.size(); i++){
            String ask = Console.askToPlayer("> Voulez-vous activé l'indice " + clues.get(i).getName(), (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0));
            if(ask.compareTo("o") == 0){
                TheMasterNumbers.getInstance().getSettings().addClues(clues.get(i));
                System.out.println("> Indice ajouté.");
            }
        }

        String ask = Console.askToPlayer("> Voulez-vous rendre les indices visibles ?", (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0));
        if(ask.compareTo("o") == 0){
            TheMasterNumbers.getInstance().getSettings().setVisible(true);
            System.out.println("> Indices visibles.");
        }

        if(TheMasterNumbers.getInstance().getSettings().getClues().size() >= 4){
            TheMasterNumbers.getInstance().getSettings().setHardmode(true);
            System.out.println("> Le mode difficile a été activé car vous avez " + TheMasterNumbers.getInstance().getSettings().getClues().size() + " d'indices.");
        }

        board.generateBoard(9);
        do {
            if(!stop) {
                for(int i = 0; i < players.size(); i++) {
                    System.out.println(board.toStringVisble());
                    Player player = players.get(i);
                    Round round = new Round(player, board);
                    round.nextRound();
                }
            }
        }while (!stop);
    }

    /**
     * Accesseur en lecture du plateau de jeu
     * @return le plateau utiliser pour cette partie
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Accesseur en écriture de l'attribue stop
     * Si vrai: le jeu s'arrête
     * @param stop la valeur de stop
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Accesseur en lecture de la liste de joueur dans la partie
     * @return liste de joueur
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Pose une question a l'utilisateur
     * @return vrai si l'utilisateur veut ajouter un joueur
     */
    private boolean addAnOtherPlayer() {
        String ask = Console.askToPlayer("> Voulez-vous ajouter un joueur supplémentaire", (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0));
        return(ask.compareTo("o") == 0);
    }

    /**
     * Ajout d'un joueur avec son nom saisit par l'utilisateur
     */
    public void addPlayer(){
        System.out.println("> Quel est votre nom ?");
        String res = Console.getScanner().next();
        getPlayers().add(new Player(res));
    }

    /**
     * Parcours toutes les cases du plateaux pour voir si elles sont découvertes
     * @return vrai si le plateau a été entièrement découvert
     */
    public boolean allReveal(){
        for (Case[] ligne : board.getCases()) {
            for (Case cases : ligne) {
                if(!cases.isReveal()) return false;
            }
        }
        return true;
    }

}
