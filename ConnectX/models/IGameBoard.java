package cpsc2150.extendedConnectX.models;

/**
 * Interface for a Game Board
 *
 * @author Luis Barrera
 *
 * Initialization ensures:
 *      Game Board is empty
 *
 * Constraints:
 *      rows of self = #rows AND columns of self = #columns
 */
public interface IGameBoard {

    /**
     * This method will check if the column is full
     *
     * @param c column that is being checked if there is a piece
     *
     * @return [True if there is a row in the column that is blank,
    false if all rows in the column have a character]
     *
     * @pre
     *       column of self {@code >=} c {@code >=} 0
     * @post
     *      [checkIfFree is True if there is a row in the column that is blank]
    OR [checkIfFree is False if all the rows in the column have a character]
    AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkIfFree(int c)
    {
        BoardPosition pos = new BoardPosition(0,c);
        if (whatsAtPos(pos) != ' ')
        { return false; }
        else
        { return true; }
    }

    /**
     * This method will place a token at the desired column
     *
     * @param p the character of the player places the piece
     * @param c column that the player wants to place a piece
     *
     * @pre
     *      p = [current player] AND column of self {@code >=} c {@code >=} 0 AND checkIfFree = TRUE
     * @post
     *      [a character is place in the cell with the desired column and lowest available row]
    AND rows of self = #rows AND columns of self = #columns
     */
    public void placeToken(char p, int c);

    /**
     * This method will check if a win has been achieved with the placement of the latest piece
     *
     * @param c column where the latest piece was placed
     *
     * @return [True if piece resulted in a win, false if it did not]
     *
     * @pre
     *      column of self {@code >=} c {@code >=} 0
     * @post
     *      [checkForWin is true if the piece resulted in a win] OR
    [checkForWin is false if the piece did not result in a win] AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkForWin(int c) {
        BoardPosition position;
        char player;
        int rows = getNumRows();
        for (int i = rows - 1; i >= 0; i--)
        {
            position = new BoardPosition(i, c);
            player = whatsAtPos(position);
            if (checkDiagWin(position, player) || checkHorizWin(position, player) || checkVertWin(position, player))
            { return true; }
        }
        return false;
    }

    /**
     * This method will check if there are no more valid places to put a piece left
     *
     * @return [true if there are no more valid positions left to place a piece,
    false if there is at least 1 place to put a piece]
     *
     * @pre
     *      NONE
     * @post
     *      [checkTie is true if there are no more valid positions left to place a piece] OR
    [checkTie is false if there is at least 1 place to put a piece] AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkTie() {
        for (int i = 0; i < getNumColumns(); i++) {
            if (checkIfFree(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method will check if there is the desired number in a row horizontally for the player
     * @param pos position on the board from where a win will be checked
     * @param p the character used to check the win
     *
     * @return [True if there is the desired number of the desired character in a row horizontally,
    false if there is not]
     *
     * @pre
     *      [pos is a valid position on the board] AND p = [current player]
     * @post
     *      [checkHorizWin is true if there is the desired number of the desired character in a row horizontally] OR
    [checkHorizWin is false if there is not ] AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkHorizWin(BoardPosition pos, char p) {
        BoardPosition marker = new BoardPosition(pos.getRow(), pos.getColumn() + 1);
        int counter = 1, next = 0;
        if (marker.getColumn() > getNumColumns() - 1)
        { next = 1; }

        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter++;
                marker = new BoardPosition(marker.getRow(), marker.getColumn() + 1);
                if (marker.getColumn() > getNumColumns() - 1)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        marker = new BoardPosition(pos.getRow(), pos.getColumn() - 1);
        next = 0;
        if (marker.getColumn() < 0)
        { next = 1; }
        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter += 1;
                marker = new BoardPosition(marker.getRow(), marker.getColumn() - 1);
                if (marker.getColumn() < 0)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        if (counter == getNumToWin())
        { return true; }
        else
        { return false; }
    }

    /**
     * This method will check if there is the desired number in a row vertically for the player
     * @param pos position on the board from where a win will be checked
     * @param p the character used to check the win
     *
     * @return [True if there is the desired number of the desired character in a row vertically,
    false if there is not]
     *
     * @pre
     *      [pos is a valid position in self] AND p = [current player]
     * @post
     *      [checkVertWin is true if there is the desired number of the desired character in a row vertically] OR
    [checkVertWin is false if there is not] AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkVertWin(BoardPosition pos, char p) {
        BoardPosition marker = new BoardPosition(pos.getRow() + 1, pos.getColumn());
        int counter = 1, next = 0;
        if (marker.getRow() > getNumRows() - 1)
        { next = 1; }

        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter++;
                marker = new BoardPosition(marker.getRow() + 1, marker.getColumn());
                if (marker.getRow() > getNumRows() - 1)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        marker = new BoardPosition(pos.getRow() - 1, pos.getColumn());
        next = 0;
        if (marker.getRow() < 0)
        { next = 1; }
        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter += 1;
                marker = new BoardPosition(marker.getRow() - 1, marker.getColumn());
                if (marker.getRow() < 0)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        if (counter == getNumToWin())
        { return true; }
        else
        { return false; }
    }

    /**
     * This method will check if there is the desired number in a row diagonally for the player
     *
     * @param pos position on the board from where a win will be checked
     * @param p the character used to check the win
     *
     * @return [True if there is the desired number of the desired character in a row diagonally,
    false if there is not]
     *
     * @pre
     *      [pos is a valid position in self] AND p = [current player]
     * @post
     *      [checkDiagWin is true if there is the desired number of the desired character in a row diagonally] OR
    [checkDiagWin is false if there is not] AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean checkDiagWin(BoardPosition pos, char p) {
        BoardPosition marker = new BoardPosition(pos.getRow() + 1, pos.getColumn() - 1);
        int counter = 1, next = 0;
        if (marker.getRow() > getNumRows() - 1 || marker.getColumn() < 0)
        { next = 1; }

        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter++;
                marker = new BoardPosition(marker.getRow() + 1, marker.getColumn() - 1);
                if (marker.getRow() > getNumRows() - 1 || marker.getColumn() < 0)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        marker = new BoardPosition(pos.getRow() - 1, pos.getColumn() + 1);
        next = 0;
        if (marker.getRow() < 0 || marker.getColumn() > getNumColumns() - 1)
        { next = 1; }
        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter += 1;
                marker = new BoardPosition(marker.getRow() - 1, marker.getColumn() + 1);
                if (marker.getRow() < 0 || marker.getColumn() > getNumColumns() - 1)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        marker = new BoardPosition(pos.getRow() - 1, pos.getColumn() - 1);
        if (counter != getNumToWin())
        { counter = 1; }
        next = 0;
        if (marker.getRow() < 0 || marker.getColumn() < 0)
        { next = 1; }

        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter++;
                marker = new BoardPosition(marker.getRow() - 1, marker.getColumn() - 1);
                if (marker.getRow() < 0 || marker.getColumn() < 0)
                { next = 1; }
            }
            else
            { next = 1; }
        }

        marker = new BoardPosition(pos.getRow() + 1, pos.getColumn() + 1);
        next = 0;
        if (marker.getRow() > getNumRows() - 1 || marker.getColumn() > getNumColumns() - 1)
        { next = 1; }
        while (counter != getNumToWin() && next != 1) {
            if (whatsAtPos(marker) == p) {
                counter += 1;
                marker = new BoardPosition(marker.getRow() + 1, marker.getColumn() + 1);
                if (marker.getRow() > getNumRows() - 1 || marker.getColumn() > getNumColumns() - 1)
                { next = 1; }
            }
            else
            { next = 1; }
        }
        if (counter == getNumToWin())
        { return true; }
        else
        { return false; }
    }

    /**
     * This method checks which player is at a certain position
     *
     * @param pos position in self that is being checked
     *
     * @return the character that is in the cell at the desired position
     *
     * @pre
     *      [pos is a valid position on the board]
     * @post
     *      whatsAtPos = [a player's character] AND rows of self = #rows AND columns of self = #columns
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * This method checks if the player is at a certain position
     *
     * @param pos position in self that is being checked
     * @param player the character of whichever player is being checked
     *
     * @return [true if the character in the cell matches the player that is being checked,
    false if the character is the wrong character]
     *
     * @pre
     *      [pos is a valid position on the board] AND player = [a player's character]
     * @post
     *      [isPlayerAtPos is true if the character in the cell matches player]
    OR [isPlayerAtPos is false if the character in the cell does not match player]
    AND rows of self = #rows AND columns of self = #columns
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player)
    {
        if (whatsAtPos(pos) != player)
        { return false; }
        else
        { return true; }
    }

    /**
     * returns the number of rows in the game board
     *
     * @return number of rows in self
     *
     * @pre
     *      NONE
     * @post
     *      getNumRows() = [number of rows]
     */
    public int getNumRows();

    /**
     * returns the number of columns in the game board
     *
     * @return number of columns in self
     *
     * @pre
     *      NONE
     * @post
     *      getNumColumns() = [number of columns]
     */
    public int getNumColumns();

    /**
     * returns the number of tokens in a row needed to win the game
     *
     * @return number of tokens needed to win
     *
     * @pre
     *      NONE
     * @post
     *      getNumToWin() = [number of tokens needed to win]
     */
    public int getNumToWin();
}
