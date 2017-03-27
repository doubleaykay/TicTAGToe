public class HumanSim{
    private static boolean exposed = false;
    public static void move(char c, Board board){
             if(TTT.win(c, board));
        else if(TTT.choose(c, TTT.stopWin(c, board), board));
        else TTT.rand(c, board);
    }
}