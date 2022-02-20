package fr.simlrx.themaster.utils;

import java.util.Scanner;
import java.util.function.Predicate;

public class Console {

    /**
     * Déclaration de l'attribut de la classe Console
     */
    private static Scanner scanner;

    /**
     * Pose un énoncer simple au joueur
     * @param question question a envoyer au joueur
     * @param predicate prédica poser au joueur, souvent o/n
     * @return true si réponse du joueur répond a l'énoncer
     */
    public static String askToPlayer(String question, Predicate<String> predicate) {
        String res = "";

        do {
            System.out.println(question);
            if(getScanner().hasNext()) {
                res = getScanner().next();
            }
        } while (predicate.test(res));
        return res;
    }

    /**
     * Equivalent au Lire.i();
     * @param question question poser au joueur
     * @return la valeur du nombre entré par le joueur
     */
    public static int getInt(String question){
        String s = null;
        boolean ok = false;
        int res = -1;

        do {
            System.out.print(question);

            if(getScanner().hasNext()){
                s = getScanner().next();
            }

            try {
                res = Integer.parseInt(s);
                ok = true;
            }catch (Exception e){
                System.out.println("> Vous devez entrer une valeur numérique.");
            }

        } while(!ok);

        return res;
    }

    /**
     * regarder le contenue de la console
     * @return contenue que le joueur a écrit dans la console
     */
    public static Scanner getScanner() {
        if(scanner == null) scanner = new Scanner(System.in);
        return scanner;
    }

}
