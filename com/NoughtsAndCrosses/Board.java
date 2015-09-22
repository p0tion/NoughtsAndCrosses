/**
 * @author Tulskih Anton
 * 24.03.2015
 *
 * All the logic for board.
 */
package com.NoughtsAndCrosses;

public class Board {

    private final int numOfRows = 4;
    private final int numOfCols = 4;
    private String[][] board;
    private int moveRow;
    private int moveCol;

    /**
     * Initializes the board, where noughts and crosses will be placed.
     * It has a view:
     *                 A  B  C
     *              1 [ ][ ][ ]
     *              2 [ ][ ][ ]
     *              3 [ ][ ][ ]
     */
    Board() {

        board = new String[numOfRows][numOfCols];

        for(int i = 0; i < numOfRows; i++) {

            for(int j = 0; j < numOfCols; j++) {

                if (i == 0) {

                    switch(j) {

                        case 0: board[i][j] = "   ";
                            break;

                        case 1: board[i][j] = " A ";
                            break;

                        case 2: board[i][j] = " B ";
                            break;

                        case 3: board[i][j] = " C ";
                            break;
                    }

                } else if(j == 0) {

                    switch(i) {

                        case 1: board[i][j] = " 1 ";
                            break;

                        case 2: board[i][j] = " 2 ";
                            break;

                        case 3: board[i][j] = " 3 ";
                            break;

                    }

                } else {

                    board[i][j] = "[ ]";

                }

            }

        }

    }

    /**
     * This constructor is used in saveMove() method to save Board instance after each move
     * and in cancelMove() method to assign previous Players' Board instance to this. instance.
     * @param oldBoard Board instance that will be assigned to this. instance
     */
    Board(Board oldBoard) {

        this.board = new String[numOfRows][numOfCols];

        for (int i = 0; i < oldBoard.board.length; i++) {

            System.arraycopy(oldBoard.board[i], 0, this.board[i], 0, oldBoard.board[i].length);

        }

    }

    /**
     * Fills the console with empty lines, so we have a clear screen effect.
     * Know it's stupid, but couldn't find anything that could work...
     */
    void clearScreen() {

        for (int i = 0; i < 300; i++) {

            System.out.println("");

        }

    }

    /**
     * Refreshes the board with the last changes.
     */
    void refresh() {

        clearScreen();

        for(String array[]: board) {

            System.out.print("\t\t\t\t");

            for(String cell: array) {

                System.out.print(cell);

            }

            System.out.println();

        }

        for(int i = 0; i < 8; i++) {

            System.out.println("");

        }

    }

    /**
     * Defines what the player chose to play with(noughts or crosses) and calls appropriate method.
     * @param player player1 or player2.
     */
    void makeMove(Player player) {

        if(player.getPlayWith()) {

            setNought();

        } else {

            setCross();

        }

    }

    /**
     * Sets 'X' to the cell where player did a move.
     */
    void setCross() {

        board[moveRow][moveCol] = "[X]";

    }

    /**
     * Sets 'O' to the cell where player did a move.
     */
    void setNought() {

        board[moveRow][moveCol] = "[O]";

    }

    /**
     * Checks if the desirable cell is empty or not.
     * @param move input of a player.
     * @return true if the cell is empty or false - if not.
     */
    boolean isCellEmpty(String move) {

        moveRow = Integer.parseInt(move.substring(1, 2));

        if(move.substring(0, 1).matches("[Aa]")) {

            moveCol = 1;

        } else if(move.substring(0, 1).matches("[Bb]")) {

            moveCol = 2;

        } else {

            moveCol = 3;

        }

        return board[moveRow][moveCol].equals("[ ]");

    }

    /**
     * Checks if the players' input is valid or not.
     * @param move input of a player.
     * @return true if the input is valid, false - if not.
     */
    boolean isValidCell(String move) {

        return move.matches("[AaBbCc][1-3]");

    }

    /**
     * Checks if the player won or not.
     * @return true if the player won, otherwise returns false.
     */
    boolean isPlayerWinner() {

        return  (!board[1][1].equals("[ ]") && board[1][1].equals(board[1][2]) && board[1][1].equals(board[1][3])) ||
                (!board[2][1].equals("[ ]") && board[2][1].equals(board[2][2]) && board[2][1].equals(board[2][3])) ||
                (!board[3][1].equals("[ ]") && board[3][1].equals(board[3][2]) && board[3][1].equals(board[3][3])) ||
                (!board[1][1].equals("[ ]") && board[1][1].equals(board[2][1]) && board[1][1].equals(board[3][1])) ||
                (!board[1][2].equals("[ ]") && board[1][2].equals(board[2][2]) && board[1][2].equals(board[3][2])) ||
                (!board[1][3].equals("[ ]") && board[1][3].equals(board[2][3]) && board[1][3].equals(board[3][3])) ||
                (!board[1][1].equals("[ ]") && board[1][1].equals(board[2][2]) && board[1][1].equals(board[3][3])) ||
                (!board[3][1].equals("[ ]") && board[3][1].equals(board[2][2]) && board[3][1].equals(board[1][3]));

    }

}