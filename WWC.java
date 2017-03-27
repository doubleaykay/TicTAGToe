public class WWC{
    private static boolean exposed = false;
    public static void move(char c, Board board){
        if(TTT.win(c, board));
        else TTT.rand(c, board);
    }
}