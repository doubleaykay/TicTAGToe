/**
 * Allows a human player to take on the computer in a game of TicTAGToe
 * Randomly decides who goes first.
 */
public class Game{
    public static void main(String args[]){
        if(Math.random() >= 0.5) PC.main();
        else CP.main();
    }
}
