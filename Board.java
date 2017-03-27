/**
 * Board object class
 * Contains a 2-dimensional array
 * includes necessary methods for human-human gameplay
 */
import java.util.Arrays;
public class Board{
    public static final int SIZE = 4;
    public char[][] matrix = new char[SIZE][SIZE];

    /*constructors*/
    public Board(){//0-arg constructor
        clear();
    }

    public Board(Board that){//constructor to duplicate existing Board
        copy(that);
    }

    public Board(char[][] inBoard){//constructor to convert char[][] to new Board
        copy(inBoard);
    }

    /*copy methods*/
    public void copy(char[][] inBoard){//copies char[][] into this
        if(inBoard.length != SIZE || inBoard[0].length != SIZE) throw new IllegalArgumentException("argument must be " + SIZE + " by " + SIZE);
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                matrix[i][j] = inBoard[i][j];
            }
        }
    }

    public void copy(Board other){//copies that into this
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
            matrix[i][j] = other.matrix[i][j];
            }
        }
    }

    /*equals methods*/
    public boolean equals(Board that){//checks if this and that are identical
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(this.matrix[i][j] != that.charAt(i,j)) return false;
            }
        }
        return true;
    }

    public boolean equals(char[][] inBoard){//checks if inBoard is identical to matrix
        if(inBoard.length != SIZE || inBoard[0].length != SIZE) throw new IllegalArgumentException("argument must be " + SIZE + " by " + SIZE);
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(inBoard[i][j] != matrix[i][j]) return false;
            }
        }
        return true;
    }

    /*other useful methods*/
    public void print(){//prints this with labels
        System.out.print("\n ");
        for(int i = 0; i < SIZE; i++){
            System.out.print(" " + (char)('A' + i));
        }
        System.out.println();
        for(int i = 0; i < SIZE; i++){
            System.out.print((char)('1' + i)+ " ");//print row index
            for(char c : matrix[i]){
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public char charAt(int i, int j){//returns character at location i,j
        if(i >= SIZE || j >= SIZE) throw new IllegalArgumentException("indices must be < " + SIZE + "; indices were " + i + ", " + j);
        return matrix[i][j];
    }

    public boolean goIn(char c, int i, int j){//puts c in location i,j. If spot is full, returns false
        if(i >= SIZE || j >= SIZE) throw new IllegalArgumentException("indices must be below " + SIZE);
        if(matrix[i][j] == '-'){
            matrix[i][j] = c;
            return true;
        }
        return false;
    }

    public boolean set(char c, int i, int j){//puts c in location i,j
        if(i >= SIZE || j >= SIZE) throw new IllegalArgumentException("indices must be below " + SIZE);
        matrix[i][j] = c;
        return false;
    }

    public void fill(char c){//fills empty spaces in matrix with c
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                goIn(c,i,j);
            }
        }
    }

    public boolean isFull(){//checks if board is full;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(matrix[i][j] == '-') return false;
            }
        }
        return true;
    }

    public void clear(){//clears this
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                set('-',i,j);
            }
        }
    }

    public int count(char c){//counts instances of c in this
        int count = 0;
        for(char[] arr : matrix){
            for(char e : arr){
                if(e == c) count++;
            }
        }
        return count;
    }

    /*checking methods*/
    public boolean check(char c){//checks for a win
        int[] positions = new int[SIZE];
        for(int i = 0; i < positions.length; i++) positions[i] = -1;
        return checkR(positions, c, 0);
    }

    private boolean checkR(int[] positions, char c, int n){//checks for a win recursively
        if(valid(positions)) return true;
        if(n == SIZE) return false;
        for(int i = 0; i < SIZE; i++){//each possible column wihtin that row
            if(charAt(n, i) == c){
                positions[n] = i;
                if(checkR(positions, c, n+1)) return true;
            }
        }
        return false;
    }

    private static boolean valid(int[] positions){//checks whether all elements of positions are distinct && none are -1
        for(int i = 0; i < SIZE; i++){
            if(positions[i] == -1) return false;
            for(int j = 0; j < SIZE; j++){
                if(j!= i && positions[j] == positions[i]) return false;
            }
        }
        return true;
    }
}