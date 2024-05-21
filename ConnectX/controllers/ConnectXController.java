package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

import java.awt.event.ActionEvent;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    /**
     * <p>
     * The possible player tokens in a game.
     * </p>
     */
    private Character[] totalPlayers = {'X', 'O', 'A', 'B', 'C', 'E', 'L', 'W', 'Z', 'Y'};

    /**
     * <p>
     * Index for player tokens. Keeps track of who's turn it is.
     * </p>
     */
    private int index;

    /**
     * <p>
     * Keeps track if the game needs to be reset.
     * </p>
     */
    private boolean newG;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        this.index = 0;
        this.newG = false;
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        int row = 0;
        BoardPosition pos;

        if (!newG) {

            // checks to see if column is full in the board. does not switch player
            if (!curGame.checkIfFree(col)) {
                screen.setMessage("Column is full! Try Again. It is " + totalPlayers[index] + "'s turn.");
            } else {
                curGame.placeToken(totalPlayers[index], col);

                // finds row of last placed token
                for (int i = curGame.getNumRows() - 1; i >= 0; i--) {
                    pos = new BoardPosition(i, col);
                    if (curGame.whatsAtPos(pos) != ' ') {
                        row++;
                    } else {
                        row--;
                        break;
                    }

                    if (i == 0) {
                        row = curGame.getNumRows() - 1;
                    }
                }
                screen.setMarker(row, col, totalPlayers[index]);

                if (curGame.checkForWin(col)) {
                    screen.setMessage("Player " + totalPlayers[index] + " wins! Click any button to start a new game.");
                    newG = true;
                } else if (curGame.checkTie()) {
                    screen.setMessage("Tie game! Click any button to start a new game.");
                    newG = true;
                } else {
                    // moves to next player
                    if (index == numPlayers - 1) {
                        index = 0;
                    } else {
                        index++;
                    }
                    screen.setMessage("It is " + totalPlayers[index] + "'s turn.");
                }
            }
        }
        // only called if the game ended in a win or tie
        else
        { newGame(); }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
