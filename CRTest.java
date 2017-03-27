/**
 * Runs an arbitrary number of trials of various strategies against each other
 */
import java.util.Scanner;
public class CRTest{
    public static int argument;

    public static void main(int arg){
        //System.out.print("\0");
        System.out.println(Board.SIZE + " by " + Board.SIZE + " board:\n");
        argument = arg;
        testA(arg,"Rand");
        testB(arg,"Rand");
        System.out.println();
    }

    public static int[] testA(int arg, String strat){//computer vs strat, computer first
        int rWins = 0;
        int cWins = 0;
        int ties = 0;
        int win = 0;
        int turns = 0;
        Board board = new Board();
        if(!(strat.equals("TTT")||strat.equals("WWC")||strat.equals("Rand")||strat.equals("HumanSim"))) throw new IllegalArgumentException("Dan,you broke it.");
        while(arg > 0){
            while(turns < 8 && win == 0){//while nobody has won and board is not full
                TTT.move('X', board);
                if(!board.check('X')){
                    if(strat.equals("WWC"))WWC.move('O', board);
                    else if(strat.equals("HumanSim"))HumanSim.move('O', board);
                    else if(strat.equals("Rand"))TTT.rand('O', board);
                    else if(strat.equals("TTT"))TTT.move('O', board);
                    if(board.check('O')) win = 1;
                }
                else win = 2;
                turns++;
            }

            if(win == 1) rWins++;
            else if(win == 2) cWins++;
            else ties++;
            win = 0;
            turns = 0;
            board.clear();
            arg--;
        }
        System.out.println("Cv" + strat.charAt(0) + ":");
        System.out.println(cWins/(argument/100.0) + "% " + rWins/(argument/100.0) + "% " + ties/(argument/100.0) + "%\n");
        return new int[] {cWins, rWins, ties};
    }

    public static int[] testB(int arg, String strat){//computer vs WinsWhenCan, computer first
        int rWins = 0;
        int cWins = 0;
        int ties = 0;
        int win = 0;
        int turns = 0;
        Board board = new Board();
        if(!(strat.equals("TTT")||strat.equals("WWC")||strat.equals("Rand")||strat.equals("HumanSim"))) throw new IllegalArgumentException("Dan,you broke it.");
        while(arg > 0){
            while(turns < 8 && win == 0){//while nobody has won and board is not full
                if(strat.equals("WWC"))WWC.move('X', board);
                else if(strat.equals("HumanSim"))HumanSim.move('X', board);
                else if(strat.equals("Rand"))TTT.rand('X', board);
                else if(strat.equals("TTT"))TTT.move('X', board);
                if(!board.check('X')){
                    TTT.move('O', board);
                    if(board.check('O')) win = 1;
                }
                else win = 2;
                turns++;
            }

            if(win == 1) rWins++;
            else if(win == 2) cWins++;
            else ties++;
            win = 0;
            turns = 0;
            board.clear();
            arg--;
        }
        System.out.println(strat.charAt(0) + "vC:");
        System.out.println(cWins/(argument/100.0) + "% " + rWins/(argument/100.0) + "% " + ties/(argument/100.0) + "%\n");
        return new int[] {cWins, rWins, ties};
    }
}
