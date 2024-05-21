package cpsc2150.extendedConnectX.models;

/**
 * This class holds the row and column of a position on the board
 *
 * @author Luis Barrera
 *
 * @invariant 0 {@code <=} row AND row {@code <=} [desired rows] AND 0 {@code <=} column AND column {@code <=} [desired columns]
 */
public class BoardPosition {
    private int row;
    private int column;

    /**
     * initializes the row and column of the board position
     *
     * @param r row of position
     * @param c column of position
     *
     * @pre
     *      0 {@code <=} r AND r {@code <=} [desired rows] AND 0 {@code <=} c AND c {@code <=} [desired columns]
     * @post
     *      column = c AND row = r
     */
    public BoardPosition(int r, int c) {
        row = r;
        column = c;
    }

    /**
     * returns the row of the position
     *
     * @pre
     *      NONE
     * @post
     *      getRow() = row AND 0 {@code <=} row AND row {@code <=} [desired rows]
    AND 0 {@code <=} column AND column {@code <=} [desired columns]
     */
    public int getRow() {
        return row;
    }

    /**
     * returns the column of the position
     *
     * @pre
     *      NONE
     * @post
     *      getColumn() = column AND 0 {@code <=} row AND row {@code <=} [desired rows]
    AND 0 {@code <=} column AND column {@code <=} [desired columns]
     */
    public int getColumn() {
        return column;
    }

    /**
     * overrides the method to check if two board positions have the same row and column
     * @param o position being compared
     *
     * @return [TRUE if both the row and columns are equal] OR
    [FALSE if either the rows and/or columns do not match]
     *
     * @pre
     *      NONE
     * @post
     *      equals() = TRUE [if both the row and columns are equal] OR
    equals() = FALSE [if either the rows and/or columns do not match] AND 0 {@code <=} row
    AND row {@code <=} [desired rows] AND 0 {@code <=} column AND column {@code <=} [desired columns]
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof BoardPosition)) {
            return false;
        }

        // typecast o to BoardPosition so that we can compare data members
        BoardPosition c = (BoardPosition) o;

        if (this.column == c.column && this.row == c.row)
        { return true; }
        else
        { return false; }
    }

    /**
     * overrides the method to output the row and column as a coordinate
     *
     * @return a string with the coordinate
     *
     * @pre
     *      NONE
     * @post
     *      toString() = <row, column> AND 0 {@code <=} row
    AND row {@code <=} [desired rows] AND 0 {@code <=} column AND column {@code <=} [desired columns]
     */
    @Override
    public String toString() {
        return row + "," + column;
    }
}

