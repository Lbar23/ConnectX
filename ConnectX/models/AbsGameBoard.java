package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {
    /**
     * This method overrides the toString method to output the playing board
     *
     * @return the playing board
     *
     * @pre
     *      NONE
     * @post
     *      [toString outputs the playing board with the number of columns at the top
    and each column is separated by a dotted line] AND [there are desired number of rows in the board]
    AND [there are desired number of columns in the board]
     */
    @Override
    public String toString() {
        String board = "";
        BoardPosition pos;

        for (int i = 0; i < getNumRows() + 1; i++)
        {
            board += "|";
            for (int j = 0; j < getNumColumns(); j++) {
                if (i == 0) {
                    if (j < 10)
                    { board += " "; }
                    board += j + "|";
                }
                else {
                    pos = new BoardPosition(i - 1,j);
                    board += whatsAtPos(pos) + " |";
                }
            }
            board += "\n";
        }

        return board;
    }
}
