package cpsc2150.extendedConnectX.models;


/**
 * This class represents the game board itself, a 2-dimensional grid
 *
 * @author Luis Barrera
 *
 * @invariant [rows of the board should equal desired number] AND [columns of the board should equal desired number]
AND boardPosition = #boardPosition AND [each cell of board should have a player's character or blank]
 *
 * @correspondence self = board
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private char[][] board;
    private BoardPosition position;
    private int numToWin;
    private int row;
    private int column;

    /**
     * This is the constructor for this class that initializes the board
     *
     * @pre
     *      NONE
     *
     * @post
     *      [rows of the board set to desired rows] AND [columns of the board set to desired columns]
     */
    public GameBoard(int r, int c, int nW) {
        row = r;
        column = c;
        board = new char[row][column];
        position = new BoardPosition(0,0);
        numToWin = nW;

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                board[i][j] = ' ';
            }
        }
    }

    public void placeToken(char p, int c) {
        for (int i = row - 1; i >= 0; i--)
        {
            if (board[i][c] == ' ')
            {
                board[i][c] = p;
                position = new BoardPosition(i,c);
                break;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumRows() {
        return row;
    }

    public int getNumColumns() {
        return column;
    }

    public int getNumToWin() {
        return numToWin;
    }

    @Override
    public boolean checkForWin(int c){
        char player = whatsAtPos(position);
        if (checkDiagWin(position, player) || checkHorizWin(position, player) || checkVertWin(position, player))
        { return true; }
        else
        { return false; }
    }
}
