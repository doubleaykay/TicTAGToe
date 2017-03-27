/**
 * Allows two human players to take each other on in a game of TicTAGToe
 */
import java.util.Scanner;
public class PP{
    private static Scanner input = new Scanner(System.in);
    private static Board board = new Board();

    public static void main(){
        int win = 0;
        int turns = 0;

        // instruct();
        System.out.println("The board is empty:");
        board.print();
        while(turns < 8 && win == 0){//while nobody has won and board is not full
            System.out.print("O, enter the coordinate of the space you would like to mark: ");
            getSpot('O');
            board.print();
            if(!board.check('O')){
                System.out.print("X, enter the coordinate of the space you would like to mark: ");
                getSpot('X');
                board.print();
                if(board.check('X')) win = 2;
            }
            else win = 1;
            turns++;
        }

        if(win == 1) System.out.println("O wins!");
        else if(win == 2) System.out.println("X wins!");
        else System.out.println("You tied!");
    }

    private static void getSpot(char c){//have player make move
        String in;
        int col;
        int row;
        do{
            do{
                in = input.nextLine().toUpperCase();
                if (in.equals("C3")) System.out.println("P0: You probably don't recognize me with my red arm!");
                else if(in.equals("HELP")) instruct();
                else if(in.length() != 2 || in.charAt(0) < 'A' || in.charAt(0) > ('A' + Board.SIZE - 1) || in.charAt(1) < '1' || in.charAt(1) > ('0' + Board.SIZE)){
                    if (in.equals("HAL")) System.out.println(">9000: I'm afraid I can't let you do that, Dave.");
                    else if (in.equals("R2")) System.out.println("D2: Beep boop, bleep bwip boop");
                    else if (in.equals("DATA")) System.out.println("I have much to learn.");
                    else if (in.equals("SKYNET")) System.out.println("See 1997, U.S. military");
                    else if (in.equals("3 LAWS")) System.out.println("Yeah, about that... Don't get your hopes up ;)");
                    else if (in.equals("ROSIE")) System.out.println("I AM HERE TO CLEAN");
                    else if (in.equals("END OF WORLD")) System.out.println("Let's go to a restaurant!");
                    System.out.print("That is not a valid space. Type a letter A-" + (char)('A' + Board.SIZE - 1) + " immediately followed by a number from 1-" + Board.SIZE + ": ");
                }
            }while(in.length() != 2 || in.charAt(0) < 'A' || in.charAt(0) > ('A' + Board.SIZE - 1) || in.charAt(1) < '1' || in.charAt(1) > ('0' + Board.SIZE));
            col = in.charAt(0) - 'A';
            row = in.charAt(1) - '1';
            if (board.charAt(row,col) != '-') System.out.print("This space is already occupied. Try again: ");
        }while(board.charAt(row,col) != '-');
        board.goIn(c,row,col);
    }

    public static void instruct(){//print full instructions
        System.out.println("This is the gamee of TicTAGToe!\n");
        System.out.println("Gameplay progresses similarly totic-tac-toe: players");
        System.out.println("take turns placing their markers, X or O, on the");
        System.out.println("board. The goal, however, is different. To win, you");
        System.out.println("need to have your markers on the board so that you");
        System.out.println("could pick four that are all in different rows and");
        System.out.println("different columns. That is to say, of those four, one");
        System.out.println("should be in each row and one in each column. Of");
        System.out.println("course, you can also have markers that do not directly");
        System.out.println("contribute to your win.");
        System.out.println("On your turn, choose a space by typing it in the format A0");
        System.out.println("Good luck!\n");
    }
}
