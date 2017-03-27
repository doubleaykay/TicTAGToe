/**
 * Runs an arbitrary number of trials of various strategies against each other
 */
import java.util.Scanner;
public class Self{
    public static void main(){
        //System.out.print("\0");
        System.out.println(Board.SIZE + " by " + Board.SIZE + " board:\n");
        play("Rand");
    }

    public static void play(String strat){//computer vs itself
        int win = 0;
        int turns = 0;
        Board board = new Board();
        while(turns < 8 && win == 0){//while nobody has won and board is not full
            TTT.move('X', board);
            board.print();
            if(!board.check('X')){
                TTT.move('O', board);
                board.print();
            }
            else win = 1;
            turns++;
        }
    }
}