/**
 * Method to make a single move against another player using DANOUSH strategy:
 * Doing
 * Anything
 * Natural or
 * Obvious,
 * Ultimately
 * Succeeding
 * Honorably
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class TTT{
    private static boolean exposed = false;
    
    public static void move(char c, Board board){
        List<int[]> bigList = new ArrayList<int[]>();
        List<int[]> tempList = new ArrayList<int[]>();
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'. Was '" + c + "'");

        boolean iCanWin = canWin(c, board);
        boolean uCanWin = canWin(d, board);

        if(iCanWin && win(c, board)) return;
        if(!choose(c, stopWin(c,board), board)){
            if(iCanWin){
                tempList = trap(c, board);
                bigList.addAll(tempList);
                bigList.addAll(tempList);
                tempList = stopBlock(c, board);
                bigList.addAll(tempList);
                bigList.addAll(tempList);
            }
            if(uCanWin){
                tempList = block(c, board);
                bigList.addAll(tempList);
                bigList.addAll(tempList);
                tempList = stopTrap(c, board);
                bigList.addAll(tempList);
                bigList.addAll(tempList);
            }
            tempList = leastCross(c, board);
            bigList.addAll(tempList);
            bigList.addAll(tempList);//I don't know why this line is imp
            chooseSpot(c, bigList, board);
            //choose(c, bigList, board);
        }
    }

    /**
     * Turn-taking methods
     * Each returns true if the move is made
     * Returns false otherwise
     */

    public static boolean win(char c, Board inBoard){//attempts to win in one move
        if(exposed) System.out.println("win");
        List<int[]> list = new ArrayList<int[]>();
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(inBoard.goIn(c,i,j)){
                    if(inBoard.check(c)) list.add(new int[] {i,j});
                    inBoard.set('-',i,j);
                }
            }
        }
        return choose(c, list, inBoard);
    }

    public static List<int[]> stopWin(char c, Board inBoard){//returns list of moves to stop an imminent win
        if(exposed) System.out.println("stopWin");
        List<int[]> list = new ArrayList<int[]>();
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(inBoard.goIn(d,i,j)){
                    if(inBoard.check(d)) list.add(new int[] {i,j});
                    inBoard.set('-',i,j);
                }
            }
        }
        return list;
    }

    private static List<int[]> trap(char c, Board inBoard){//returns list of moves to trap the opponent
        if(exposed) System.out.println("trap");
        int winCount = 0;
        List<int[]> list = new ArrayList<int[]>();
        for(int k = 0; k < Board.SIZE; k++){
            for(int l = 0; l < Board.SIZE; l++){
                if(inBoard.goIn(c,k,l)){
                    winCount = 0;
                    for(int i = 0; i < Board.SIZE; i++){
                        for(int j = 0; j < Board.SIZE; j++){
                            if(inBoard.goIn(c,i,j)){
                                if(inBoard.check(c)) winCount++;
                                inBoard.set('-',i,j);
                            }
                        }
                    }
                    inBoard.set('-',k,l);
                    if(winCount >= 2) list.add(new int[] {k,l});//then add this to the list of moves
                }
            }
        }
        return list;
    }

    private static List<int[]> stopTrap(char c, Board inBoard){//returns list of moves to stop a trap from the opponent
        if(exposed) System.out.println("stopTrap");
        int winCount = 0;
        List<int[]> list = new ArrayList<int[]>();
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        for(int k = 0; k < Board.SIZE; k++){
            for(int l = 0; l < Board.SIZE; l++){
                if(inBoard.goIn(d,k,l)){
                    winCount = 0;
                    for(int i = 0; i < Board.SIZE; i++){
                        for(int j = 0; j < Board.SIZE; j++){
                            if(inBoard.goIn(d,i,j)){
                                if(inBoard.check(d)) winCount++;
                                inBoard.set('-',i,j);
                            }
                        }
                    }
                    inBoard.set('-',k,l);
                    if(winCount >= 2) list.add(new int[] {k,l});//then add this to the list of moves
                }
            }
        }
        return list;
    }

    public static List<int[]> block(char c, Board inBoard){//returns list of moves to fully block opponent
        if(exposed) System.out.println("block");
        List<int[]> list = new ArrayList<int[]>();
        Board testBoard = new Board(inBoard);
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(testBoard.goIn(c,i,j)){
                    if(!canWin(d, testBoard)) list.add(new int[] {i,j});
                    testBoard.set('-',i,j);
                }
            }
        }
        return list;
    }

    public static List<int[]> stopBlock(char c, Board inBoard){//returns list of moves that prevent opponent from fully blocking
        if(exposed) System.out.println("stopBlock");
        List<int[]> list = new ArrayList<int[]>();
        Board testBoard = new Board(inBoard);
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(testBoard.goIn(d,i,j)){
                    if(!canWin(c, testBoard)) list.add(new int[] {i,j});
                    testBoard.set('-',i,j);
                }
            }
        }
        return list;
    }

    private static List<int[]> leastCross(char c, Board inBoard){//returns list of spots with the fewest characters in their crosses
        if(exposed) System.out.println("leastCross");
        int[] least = new int[] {100,100};
        List<int[]> list = new ArrayList<int[]>();
        int[] inC;
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(inBoard.charAt(i,j) == '-'){
                    inC = inCross(i,j,c,inBoard);
                    if(inC[0] == least[0] && inC[1] == least[1] || inC[0] == least[1] && inC[1] == least[0]){
                        list.add(new int[] {i,j});
                    }
                    else if((inC[0] <= least[0] && inC[1] <= least[1] || inC[0] <= least[1] && inC[1] <= least[0])){
                        list = new ArrayList<int[]>();
                        list.add(new int[] {i,j});
                        least = inC;
                        //System.out.print(least[0] + ", " + least[1]);//for debugging this method
                    }
                }
            }
        }
        return list;
    }

     public static boolean emptyCross(char c, Board inBoard){//returns list of random spots in an empty row and empty column.
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> cols = new ArrayList<Integer>();
        for(int i = 0; i < Board.SIZE; i++){
            rows.add(i);
            cols.add(i);
        }

        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(inBoard.charAt(i,j) != '-'){
                    rows.remove(new Integer(i));
                    cols.remove(new Integer(j));
                    j = Board.SIZE;
                }
            }
        }
        if(rows.isEmpty() || cols.isEmpty()) return false;
        inBoard.goIn(c, rows.get((int)(Math.random() * rows.size())), cols.get((int)(Math.random() * cols.size())));
        return true;
    }

    public static boolean rand(char c, Board inBoard){//goes in a random spot
        if(exposed) System.out.println("rand");
        List<int[]> list = new ArrayList<int[]>();
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                if(inBoard.charAt(i,j) == '-')list.add(new int[] {i,j});
            }
        }
        return choose(c, list, inBoard);
    }

    /**
     * Helping methods
     */

    private static boolean canWin(char c, Board inBoard){//returns whether player c can legally win. Still occasionally returns false positives when opponent MUST win first
        boolean wasExposed = exposed;
        exposed = false;//this weird business is so it doesn't print all that win() and emptyCross() jazz down below from within this method
        char d;
        int ltw = 0;
        int leastLeft;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        Board testBoard = new Board(inBoard);
        while(!testBoard.check(c) && !testBoard.isFull() && (win(c, testBoard) || emptyCross(c, testBoard))){
            ltw++;
        }
        leastLeft = leastToWin(c, testBoard);
        if(leastLeft == -1) return false;
        exposed = wasExposed;
        return ltw + leastLeft + testBoard.count(c) <= testBoard.count(d) + testBoard.count('-');
    }

    public static int leastToWin(char c, Board inBoard){//theoretical least moves needed to win. returns -1 if no solution
        Board testBoard = new Board(inBoard);
        int hold = leastToWin(c, testBoard, new ArrayList<Integer>(), new ArrayList<Integer>());
        if(hold == 404) return -1;
        return hold;
    }

    private static int leastToWin(char c, Board inBoard, ArrayList<Integer> rows, ArrayList<Integer> cols){//recursive helper
        int least = 404;//dummy value, always larger than solution if there is one
        int potential;
        for(int i = 0; i < Board.SIZE; i++){
            if(rows.indexOf(i) == -1){
                for(int j = 0; j < Board.SIZE; j++){
                    if(cols.indexOf(j) == -1 && inBoard.goIn(c,i,j)){
                        if(inBoard.check(c))least = 0;
                        else{
                            rows.add(i);
                            cols.add(j);
                            potential = leastToWin(c, inBoard, rows, cols);
                            if(potential < least){
                                least = potential;
                            }
                            rows.remove(rows.size()-1);
                            cols.remove(cols.size()-1);
                        }
                        inBoard.set('-',i,j);
                    }
                }
            }
        }
        if(least > 2) return 404;
        return least + 1;
    }
    
    private static int[] inCross(int row, int col, char c, Board inBoard){//returns 5*(number of char c) + (number of char d) in row and col combined
        int inCol = 0;
        int inRow = 0;
        char d;
        if(c == 'X') d = 'O';
        else if(c == 'O') d = 'X';
        else throw new IllegalArgumentException("argument must be either 'X' or 'O'");

        for(int i = 0; i < Board.SIZE; i++){
            if(inBoard.charAt(row,i) == c) inRow += 5;
            else if(inBoard.charAt(row,i) == d) inRow++;
            if(inBoard.charAt(i,col)  == c) inCol += 5;
            else if(inBoard.charAt(i,col)  == d) inCol++;
        }
        return new int[] {inRow, inCol};
    }

    private static int countOf(int[] arr, List<int[]> list){//returns number of instances of this int[] in list<>
        int temp = indexOf(arr, list);
        if(temp == -1) return 0;
        return 1 + countOf(arr, list.subList(temp + 1, list.size()));
    }
    
    private static int indexOf(int[] arr, List<int[]> list){//returns the index of the first instance of arr in list
        for(int i = 0; i < list.size(); i++){
            if(Arrays.equals(arr, list.get(i))) return i;
        }
        return -1;
    }

    private static boolean chooseSpot(char c, List<int[]> mainList, Board inBoard){//finds the spot that occurs the most in mainList<>
        List<int[]> finalList = new ArrayList<int[]>();
        int max = 2;
        int temp;
        for(int i = 0; i < Board.SIZE; i++){
            for(int j = 0; j < Board.SIZE; j++){
                temp = countOf(new int[] {i,j}, mainList);
                if(temp >= max){
                    if (temp != max){
                        finalList.clear();
                        max = temp;
                    }
                    finalList.add(new int[] {i,j});
                }
            }
        }
        return choose(c, finalList, inBoard);
    }

    public static boolean choose(char c, List<int[]> list, Board inBoard){//selects and goes in a space in list at random
        if(list.isEmpty()) return false;
        int rand = (int)(Math.random() * list.size());
        return inBoard.goIn(c, list.get(rand)[0], list.get(rand)[1]);
    }
}