package lib;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    private Integer[] deck = {1,2,3,4,5,6,7,8,9,10,11};

    private Integer[] player1 = new Integer[6];
    private Integer[] player2 = new Integer[6];

    private int positionInDeck = 4;
    private int endCounter = 2;
    private int whoWon = 0;
    private boolean thisIsTheEnd = false;

    private Scanner user = new Scanner(System.in);

    public BlackJack(){

        List<Integer> integerList = Arrays.asList(this.deck);
        Collections.shuffle(integerList);
        integerList.toArray(this.deck);

        player1[0] = deck[0];
        player2[0] = deck[1];
        player1[1] = deck[2];
        player2[1] = deck[3];

    }

    private int getSize(int player){
        int temp = 0;
        Integer[] playerSTR;

        if (player == 1){
            playerSTR = player1;
        }
        else if (player == 2) playerSTR = player2;
        else return -1;

        for (int i = 0; playerSTR[i]!=null; i++){
            temp++;
        }
        return temp;
    }


    private void Draw(int player){
        if(player == 1){
            player1[getSize(1)] = deck[positionInDeck];
        }
        else if (player == 2)
            player2[getSize(2)] = deck[positionInDeck];
        positionInDeck++;
    }

    private void printInvent(int player){
        Integer[] playerSTR;
        int smallI = 0;

        if (player == 1) {
            playerSTR = player1;
        }
        else if (player == 2){
            playerSTR = player2;
            smallI = 1;
        }
        else playerSTR = new Integer[0];

        if (player == 1)
        System.out.print("Your inventory:\t \t");

        else if (player == 2)
            System.out.print("Player 2 inventory:\t ?");

        for (int i = smallI; i < playerSTR.length; i++){
            if (playerSTR[i]==null) break;


            System.out.print(" "+playerSTR[i]);
        }
        if (player == 1)
        System.out.print(" = "+sumOfPlayer(player)+"\n");

        else System.out.print(" = ? + "+(sumOfPlayer(player)-player2[0])+"\n");

    }

    private int sumOfPlayer(int player){
        int temp = 0;
        Integer[] playerSTR;

        if (player == 1){
            playerSTR = player1;
        } else if (player == 2) {
            playerSTR = player2;
        } else {
            playerSTR = null;
        }

        for (int i = 0; playerSTR[i]!=null; i++){
            temp += playerSTR[i];
        }

        return temp;
    }

    private void setIfThisIsTheEnd(){
        if (endCounter == 0){
            thisIsTheEnd = true;
        }
    }

    private void check(){
        if (thisIsTheEnd){
            if ((sumOfPlayer(1)>sumOfPlayer(2) && sumOfPlayer(1) <22) || (sumOfPlayer(1)<sumOfPlayer(2) && sumOfPlayer(2) > 21)) {
                whoWon = 1;
            } else if ((sumOfPlayer(2)>sumOfPlayer(1) && sumOfPlayer(2) <22) || (sumOfPlayer(2)<sumOfPlayer(1) && sumOfPlayer(1) > 21)){
                whoWon = 2;
            } else whoWon = 3;
            return;
        }
    }


    private void turn(){

        boolean SomebodyDidSomething = false;

        printInvent(2);
        printInvent(1);

        System.out.println("\nYour turn\n");

        while (!user.hasNext()){

        }
        while (!SomebodyDidSomething){
            String input = user.next();

        if (input.equals("D")){
            if (sumOfPlayer(1)>20){
                System.out.println("Drawing now is an illegal move");
                } else {
                Draw(1);
                System.out.println("You draw a card");
                SomebodyDidSomething = true;
            }

        } else if (input.equals("N")){
            System.out.println("You don't draw a card");
            SomebodyDidSomething = true;
            endCounter--;
            setIfThisIsTheEnd();
        }
        }

        check();

        SomebodyDidSomething = false;

        System.out.println("\nPlayer2's turn\n");

        if (sumOfPlayer(2)<16){
            Draw(2);
            System.out.println("Player2 draws a card");
        } else {
            endCounter--;
            setIfThisIsTheEnd();
            System.out.println("Player2 doesn't draw a card");
        }

        check();



    }

    public void game(){


        while (whoWon == 0){
            turn();
        }

        System.out.println("Player 2: "+sumOfPlayer(2));
        System.out.println("Player 1: "+sumOfPlayer(1));

        System.out.println();

        if (whoWon == 1){
            System.out.println("You won");
        } else if (whoWon == 2) {
            System.out.println("You loose");
        } else if (whoWon == 3) {
            System.out.println("It's a draw");
        }

        System.out.println();

    }


    // Used for testing
    public void printDeck(){
        for (int i = 0; i < deck.length; i++){
            System.out.println(deck[i]);
        }
    }

}
