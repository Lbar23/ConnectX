package cpsc2150.extendedConnectX.models;

import java.util.*;
/**
 * This class represents the game board itself as a map
 *
 * @author Luis Barrera
 *
 * @invariant [rows of the board should equal desired number] AND [columns of the board should equal desired number]
AND boardPosition = #boardPosition AND [map only has values of positions of characters in their corresponding key]
 *
 * @correspondence self = board
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private Map<Character, List<BoardPosition>> board;
    private BoardPosition position;
    private int numToWin;
    private int row;
    private int column;

    public GameBoardMem(int r, int c, int nW){
        row = r;
        column = c;
        numToWin = nW;
        board = new HashMap<Character, List<BoardPosition>>();
        position = new BoardPosition(0,0);
    }

    public void placeToken(char p, int c){
        List<BoardPosition> ls = new ArrayList<>();
        BoardPosition marker;
        BoardPosition pos = new BoardPosition(row - 1, c);
            for (int i = row - 1; i >= 0; i--)
            {
                marker = new BoardPosition(i, c);
                if (whatsAtPos(marker) == ' ')
                {
                    pos = marker;
                    break;
                }
            }
        if (board.containsKey(p)) {
            ls = board.get(p);
            board.remove(p);
        }
        ls.add(pos);
        board.put(p, ls);
        position = pos;

    }

    @Override
    public boolean checkForWin(int c){
        char player = whatsAtPos(position);
        if (checkDiagWin(position, player) || checkHorizWin(position, player) || checkVertWin(position, player))
        { return true; }
        else
        { return false; }
    }

    public char whatsAtPos(BoardPosition pos){
        char player = ' ';
        List<BoardPosition> ls = new ArrayList<>();
        for (Map.Entry<Character, List<BoardPosition>> m: board.entrySet()){
            ls = m.getValue();
            if (ls.contains(pos))
            { player = m.getKey(); }
        }
        return player;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        List<BoardPosition> ls = new ArrayList<>(board.get(player));
        if (ls.contains(pos))
        { return true; }
        else
        { return false; }
    }

    public int getNumRows(){
        return row;
    }

    public int getNumColumns(){
        return column;
    }

    public int getNumToWin(){
        return numToWin;
    }
}
