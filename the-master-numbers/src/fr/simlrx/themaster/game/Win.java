package fr.simlrx.themaster.game;

import fr.simlrx.themaster.TheMasterNumbers;
import fr.simlrx.themaster.clues.Clue;
import fr.simlrx.themaster.clues.LowerClue;
import fr.simlrx.themaster.clues.UpperClue;
import fr.simlrx.themaster.utils.Color;

import java.util.ArrayList;
import java.util.List;

public class Win {

    /**
     * Fonction pricipale qui permet de choisir automatique le type de victoire/defaite
     */
    public void tryWin() {
        Game game = TheMasterNumbers.getInstance().getGame();

        int u = 0;
        int l = 0;
        for (Clue clue : getTotalClues()) {
            if (clue instanceof UpperClue) {
                u++;

                if (u == getTotalClues().size()) {
                    loseByOneClue(game.getPlayers(), getTotalClues(), game.getBoard());
                    game.setStop(true);
                    break;
                }
            }if(clue instanceof LowerClue){
                l++;

                if(l == getTotalClues().size()){
                    loseByOneClue(game.getPlayers(), getTotalClues(), game.getBoard());
                    game.setStop(true);
                    break;
                }
            }
        }
        if(game.allReveal()){
            WinByAllReveal(game.getPlayers(), game.getBoard());
            game.setStop(true);
        }
    }

    /**
     * Affiche la victoire avec le plateau et les points des joueurs
     * @param players liste les joueurs qui jouent
     * @param board plateau sur le quel les joueurs jouent
     */
    private void WinByAllReveal(List < Player > players, Board board) {
        System.out.println(" ");
        System.out.println("+===== FIN DE PARTIE =====+");
        System.out.println(" ");
        System.out.println("> Vous avez fini la partie en ayant découvert toutes les cases.");
        System.out.println("> Félicitations !");
        System.out.println(" ");
        System.out.println("> Voici le plateau: ");
        System.out.println(" ");
        System.out.println(board.toStringEndGame());
        System.out.println("> Légende: BLEU -> découvert");
        System.out.println(" ");
        for (Player all : players) {
            System.out.println("> " + all.getName() + ", voici votre score: " + Color.YELLOW + all.getScore() + Color.RESET);
            if (players.size() > 1)
                System.out.println("> Vous avez découvert " + Color.PURPLE + all.getCases().size() + Color.RESET + " cases.");
        }
    }

    /**
     * Affiche la defaite avec le plateau et les points des joueurs
     * @param players liste les joueurs qui jouent
     * @param clues liste des indices restant de le jeu
     * @param board plateau sur le quel les joueurs jouent
     */
    public void loseByOneClue (List < Player > players, List < Clue > clues, Board board){
        System.out.println(" ");
        System.out.println("+===== FIN DE PARTIE =====+");
        System.out.println(" ");
        System.out.println("> Vous avez fini la partie sans décourvir toutes les cases.");
        System.out.println("> Il ne reste qu'un seul même indices pour toutes les cases: " + clues.get(0).getName());
        System.out.println(" ");
        System.out.println("> Voici le plateau: ");
        System.out.println(" ");
        System.out.println(board.toStringEndGame());
        System.out.println("> Légende: ROUGE -> non découvert | BLEU -> découvert");
        System.out.println(" ");
        for (Player all : players) {
            System.out.println("> " + all.getName() + ", voici votre score: " + Color.YELLOW + all.getScore() + Color.RESET);
            if (players.size() > 1)
                System.out.println("> Vous avez découvert " + Color.PURPLE + all.getCases().size() + Color.RESET + " cases.");
        }
    }

    /**
     * Stocke dans une liste les indices restant des cases non découverte par les joueurs
     * @return liste de clue
     */
    public List<Clue> getTotalClues () {
        List<Clue> res = new ArrayList<>();
        for (int i = 0; i < TheMasterNumbers.getInstance() .getGame().getBoard().getHeight();
             i++) {
            for (int j = 0; j < TheMasterNumbers.getInstance().getGame().getBoard().getWidth(); j++) {
                Case ca = TheMasterNumbers.getInstance().getGame().getBoard().getCase(i, j);
                if (!res.contains(ca.getClue()) && !ca.isReveal()) {
                    res.add(ca.getClue());
                }
            }
        }
        return res;
    }


}
