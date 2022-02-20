package fr.simlrx.themaster.game;

import fr.simlrx.themaster.TheMasterNumbers;
import fr.simlrx.themaster.utils.Color;
import fr.simlrx.themaster.utils.Console;

public class Round {

    /**
     * Déclaration des attributs de la classe Round
     */
    private Player player;
    private final Win win;
    private Board board;

    /**
     * Constructeur par defaut de la classe Round
     * @param player joueur a faire jouer
     * @param board plateau sur le quel on joue
     */
    public Round(Player player, Board board) {
        this.player = player;
        this.board = board;
        this.win = new Win();
    }

    /**
     * Choix des cases
     */
    public void nextRound(){
        System.out.println(" ");
        System.out.println("> C'est à " + player.getName() + " de jouer.");
        System.out.println(" ");
        System.out.println("+===== CHOIX DES CASES =====+");
        System.out.println(" ");
        System.out.println(board.toString(player));
        System.out.println(" ");

        int index = 0;
        int[] entry = {-1, -1, -1, -1};
        for(int k = 1; k <= 2; k++) {
            System.out.println("> Choix de la "+ (k == 1 ? "première" : "deuxième") + " case."); //opérateur conditionelle ternaire
            for(int j = 1; j <= 2; j++) {
                boolean message = false;
                do{
                    if(message) System.out.println("> Vous devez indiquer une valeur dans les bornes du plateau. [" + (board.getWidth()) + "x" + board.getHeight() + "]");
                    message = true;
                    entry[index] = Console.getInt("> " + (j == 1 ? "Ligne" : "Colonne")+": ");
                    entry[index]--;
                } while(entry[index] < 0 || entry[index] >= (j == 1 ? this.board.getHeight() : this.board.getWidth()));

                if(j == 2 && board.getCase(entry[index-1], entry[index]).isReveal()) {
                    System.out.println("> Vous ne pouvez pas jouer avec des cases déjà découverte ...");
                    entry[index] = -1;
                    j = 0;

                    if (k == 1) index = 0;
                    else index = 2;

                } else if(k == 2 && j == 2 && board.getCase(entry[0], entry[1]) == board.getCase(entry[2], entry[3])){
                    System.out.println("> Vous ne pouvez pas selectionner les deux mêmes cases.");
                    entry[index] = -1;
                    entry[index-1] = -1;
                    j = 0;

                    index = 2;
                } else index++;

            }
        }
        player.setSupposition(new Supposition(board.getCase(entry[0], entry[1]), board.getCase(entry[2], entry[3])));

        System.out.println(" ");
        System.out.println("+===== INDICES =====+");
        System.out.println(" ");
        System.out.println(board.toString(player));
        System.out.println(" ");
        for (int k = 1; k <= 2; k++){ //case1: 7+ case2: 2* i1: 7+2 i2: 2*7
            System.out.print("> Indice de la " + (k == 1 ? "première" : "deuxième") + " case: ");
            if(k == 1){
                System.out.println(Color.PURPLE + player.getSupposition().getCase1().getClue().get(player.getSupposition().getCase1(), player.getSupposition().getCase2()) + Color.RESET);
            }else{
                System.out.println(Color.PURPLE + player.getSupposition().getCase2().getClue().get(player.getSupposition().getCase2(), player.getSupposition().getCase1()) + Color.RESET);
            }
        }
        System.out.println(" ");
        System.out.println("+===== HYPOTHESES =====+");
        System.out.println(" ");

        String ask = Console.askToPlayer("> Voulez vous faire une hypothèse sur les valeurs des cases choisies ?", (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0 && response.compareTo("non") != 0 && response.compareTo("oui") != 0));

        if(ask.compareTo("o") == 0 || ask.compareTo("oui") == 0) checkSupposition(player);
        else player.setSupposition(null);

    }

    /**
     * Vérifie si la supposition est juste en fonction des cases
     * @param player joueur a vérifier la supposition
     */
    public void checkSupposition(Player player){
            int index = 0;
            int[] entry = {-1, -1};
            for (int i = 1; i <= 2; i++) {
                boolean message = false;
                do{
                    if(message) System.out.println("> Vous devez indiquer une valeur entre 1 et 99.");
                    message = true;
                    entry[index] = Console.getInt("> Hypotèse de la " + (i == 1 ? "première" : "deuxième") + " case: ");
                } while(entry[index] < 0 || entry[index] >= 99);
                index++;
            }

            if (entry[0] == player.getSupposition().getCase1().getValue() && entry[1] == player.getSupposition().getCase2().getValue()) {
                System.out.println("> Bravo ! Le contenu des cases a été trouvé.");
                player.addCases(player.getSupposition().getCase1());
                player.addCases(player.getSupposition().getCase2());
                player.getSupposition().getCase1().setReveal(true);
                player.getSupposition().getCase2().setReveal(true);
                if(TheMasterNumbers.getInstance().getSettings().isHardmode()){
                    int s = (player.getSupposition().getCase1().getValue() + player.getSupposition().getCase2().getValue())/2;
                    player.addScore(s);
                    System.out.println("> Vous avez obtenu " + s + " points.");
                    System.out.println(" ");
                }else{
                    int s = (player.getSupposition().getCase1().getValue() + player.getSupposition().getCase2().getValue());
                    player.addScore(s);
                    System.out.println("> Vous avez obtenu " + s + " points.");
                }
                System.out.println("> Votre score est de " + player.getScore() + ".");
                System.out.println(" ");
                player.setSupposition(null);

            } else if (entry[0] == player.getSupposition().getCase1().getValue() || entry[1] == player.getSupposition().getCase2().getValue()){
                System.out.println("> Dommage, l'une des deux valeurs est juste.");
                if(TheMasterNumbers.getInstance().getSettings().isHardmode()) {
                    System.out.println("> Vous avez fait une erreur. Vous avez perdu un point.");
                    player.removeScore(1);
                }
                System.out.println("> Votre score: " + player.getScore());

                String ask = Console.askToPlayer("> Voulez vous refaire une hypothèse ?", (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0 && response.compareTo("non") != 0 && response.compareTo("oui") != 0));
                if(ask.compareTo("o") == 0 || ask.compareTo("oui") == 0) sameRound(player);
                else player.setSupposition(null);
            }else{
                System.out.println("> Les valeurs données sont fausses.");
                if (TheMasterNumbers.getInstance().getSettings().isHardmode()){
                    System.out.println("> Vous avez fait une erreur. Vous avez perdu deux points.");
                    player.removeScore(2);
                }
                System.out.println("> Votre score: " + player.getScore());
                System.out.println(" ");

                String ask = Console.askToPlayer("> Voulez vous rejouez avec les mêmes cases ?", (response) -> (response.compareTo("o") != 0 && response.compareTo("n") != 0 && response.compareTo("non") != 0 && response.compareTo("oui") != 0));
                if(ask.compareTo("o") == 0 || ask.compareTo("oui") == 0) sameRound(player);
                else player.setSupposition(null);
        }
        this.win.tryWin();
    }

    /**
     * Permet au joueur de rejouez avec les mêmes cases: donne les indices de nouveaux et propose de faire des hypothèses ...
     * @param player joueur qui refait son tour
     */
    public void sameRound(Player player){
        System.out.println(" ");
        System.out.println("+===== INDICES =====+");
        System.out.println(" ");
        System.out.println(board.toString(player));
        System.out.println(" ");
        for (int i = 1; i <= 2; i++){ //case1: 7+ case2: 2* i1: 7+2 i2: 2*7
            System.out.print("> Indice de la " + (i == 1 ? "première" : "deuxième") + " case: ");
            if(i == 1){
                System.out.println(player.getSupposition().getCase1().getClue().get(player.getSupposition().getCase1(), player.getSupposition().getCase2()));
            }else{
                System.out.println(player.getSupposition().getCase2().getClue().get(player.getSupposition().getCase2(), player.getSupposition().getCase1()));
            }
        }
        System.out.println(" ");
        System.out.println("+===== HYPOTHESES =====+");
        System.out.println(" ");

        checkSupposition(player);
    }

}
