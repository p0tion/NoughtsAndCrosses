/**
 * @author Tulskih Anton
 * 19.03.2015
 *
 * The logic of the games' core.
 */
package com.NoughtsAndCrosses;

import java.util.ArrayList;
import java.util.Scanner;

class Game {

    private Player player1;
    private Player player2;
    private Board gameBoard;
    private Scanner scanner;
    private ArrayList<Board> saveList;
    private int turnCount;

    /**
     * Starts the game.
     */
    void start() {

        gameBoard = new Board();
        turnCount = 0;
        scanner = new Scanner(System.in);
        saveList = new ArrayList<Board>();
        String playerName;

        gameBoard.clearScreen();

        System.out.println("Welcome to the \"Noughts and crosses\" game.\nWhat is Your name: ");

        do {

            playerName = scanner.next();
            gameBoard.clearScreen();
            player1 = new Player(playerName, false);

        } while (player1.getName() == null);

        gameBoard.clearScreen();

        System.out.println(player1.getName() + ", who do you want to play with?\nType \"1\" if you want to play with another player, or \"2\" if you want to play against computer:");

        int choice;
        boolean isValidChoice = false;

        do {

            scanner = new Scanner(System.in);

            if (scanner.hasNextInt()) {

                choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        playWithAnotherPlayer();
                        isValidChoice = true;
                        break;

                    case 2:
                        playWithComputer();
                        isValidChoice = true;
                        break;

                    default:
                        gameBoard.clearScreen();
                        System.out.println("You should choose between \"1\" and \"2\".");
                        System.out.println("Type \"1\" if you want to play with another player, or \"2\" if you want to play against computer:");

                }


            } else {

                gameBoard.clearScreen();
                System.out.println("You should choose between \"1\" and \"2\".");
                System.out.println("Type \"1\" if you want to play with another player, or \"2\" if you want to play against computer:");

            }

        } while (!isValidChoice);

    }

    /**
     * Starts the game with computer.
     */
    void playWithComputer() {

        player2 = new Player("Computer", true);

        gameBoard.clearScreen();

        System.out.println(player1.getName() + ", what do you choose to play with:\n1. Noughts\n2. Crosses\n3. Random choice");

        chooseSide(player1, player2);

        gameBoard.refresh();

        do {

            turn(player1);

            if (getTurnCount() == 9) {

                System.out.println("\nIt's a draw!\nEND OF THE GAME.");
                break;

            }

            turn(player2);

        } while(true);

    }

    /**
     * Starts the game with another player.
     */
    void playWithAnotherPlayer() {

        gameBoard.clearScreen();

        System.out.println("What is the name of the second player:");

        scanner = new Scanner(System.in);
        String playerName;

        do {

            playerName = scanner.next();
            gameBoard.clearScreen();
            player2 = new Player(playerName, false);

        } while(player2.getName() == null);

        gameBoard.clearScreen();

        System.out.println(player1.getName() + ", what do you choose to play with:\n1. Noughts\n2. Crosses\n3. Random choice");

        chooseSide(player1, player2);

        gameBoard.refresh();
        saveMove(gameBoard);

        do {

            turn(player1);

            if (getTurnCount() == 9) {

                System.out.println("\nIt's a draw!\nEND OF THE GAME.");
                break;

            }

            turn(player2);

        } while(true);

    }

    /**
     * Processes the players' turn
     * @param player current player.
     */
    void turn(Player player) {

        System.out.println(player.getName() + " moves.\nEnter the cell coordinates, where do you want to place a " + (player.getPlayWith() ? "nought" : "cross") + ":" + (getTurnCount() >= 2 ? "\nType \"R\" if you want to cancel your previous move" : "\n") +"\nOr type \"EXIT\" to close the game");
        readMove(player, false);
        gameBoard.makeMove(player);
        saveMove(gameBoard);
        setTurnCount(getTurnCount() + 1);
        gameBoard.refresh();
        if(gameBoard.isPlayerWinner()) {

            System.out.println("\n\nWe have a winner: " + player.getName() + "!");
            System.exit(0);

        }

    }

    /**
     * Defines, with what players will be playing.
     * @param player1 Player1.
     * @param player2 Player2 or Computer.
     */
    void chooseSide(Player player1, Player player2) {

        int choice;
        boolean isValidChoice = false;

        do {

            scanner = new Scanner(System.in);

            if(scanner.hasNextInt()) {

                choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        player1.setPlayWithNoughts();
                        player2.setPlayWithCrosses();
                        isValidChoice = true;
                        break;

                    case 2:
                        player1.setPlayWithCrosses();
                        player2.setPlayWithNoughts();
                        isValidChoice = true;
                        break;

                    case 3:
                        int random = (int)(Math.random()*2);
                        if(random == 0) {

                            player1.setPlayWithNoughts();
                            player2.setPlayWithCrosses();

                        } else {

                            player1.setPlayWithCrosses();
                            player2.setPlayWithNoughts();

                        }
                        isValidChoice = true;
                        break;

                    default:
                        gameBoard.clearScreen();
                        System.out.println("You should choose between \"1\", \"2\" and \"3\".");

                }

            } else {

                gameBoard.clearScreen();
                System.out.println("You should choose between \"1\", \"2\" and \"3\".");

            }

        } while(!isValidChoice);

    }

    /**
     * Reads user input to make a move or exits the game if "EXIT" typed.
     */
    void readMove(Player player, boolean didCancel) {

        String move = null;

        if(player.getAi()) {

            do {

                int randomRow = (int)(Math.random()*3);
                int randomCol = (int)(Math.random()*3);

                switch(randomRow) {

                    case 0:
                        move = "A";
                        break;

                    case 1:
                        move = "B";
                        break;

                    case 2:
                        move = "C";
                        break;

                }

                switch(randomCol) {

                    case 0:
                        move += 1;
                        break;

                    case 1:
                        move += 2;
                        break;

                    case 2:
                        move += 3;
                        break;

                }

            } while(!gameBoard.isCellEmpty(move));

        } else {

            boolean isValid = false;

            do {

                scanner = new Scanner(System.in);

                move = scanner.next();

                if(move.equalsIgnoreCase("exit")) {

                    System.exit(0);

                }

                if(getTurnCount() >= 2 && move.equalsIgnoreCase("r") && !didCancel) {

                    cancelMove();
                    System.out.println(player.getName() + " moves.\nEnter the cell coordinates, where do you want to place a " + (player.getPlayWith() ? "nought" : "cross") + ":" + (getTurnCount() >= 2 ? "\nType \"R\" if you want to cancel your previous move" : "\n") +"\nOr type \"EXIT\" to close the game");
                    readMove(player, true);
                    break;

                }

                if(gameBoard.isValidCell(move)) {

                    if(gameBoard.isCellEmpty(move)) {

                        isValid = true;

                    } else {

                        gameBoard.refresh();
                        System.out.println("\n\n\nThe required cell is filled! Please, try another input:");

                    }

                } else {

                    gameBoard.refresh();
                    System.out.println("\n\n\nYour input is invalid! Please, try another input:");

                }

            } while(!isValid);

        }

    }

    /**
     * Saves the gameBoard after each move.
     * @param board current gameBoard.
     */
    void saveMove(Board board) {

        saveList.add(new Board(board));

    }

    /**
     * Cancels previous two moves (last iteration of the opponent and players' last iteration) so the player can change his last move.
     */
    void cancelMove() {

        saveList.remove(getTurnCount());
        saveList.remove(getTurnCount() - 1);
        this.gameBoard = new Board(saveList.get(getTurnCount()-2));
        setTurnCount(getTurnCount() - 2);
        this.gameBoard.refresh();

    }

    /**
     * Returns value of the counter.
     * @return value of the counter.
     */
    int getTurnCount() {

        return turnCount;

    }

    /**
     * Defines new value of the counter.
     * @param newCount new value of the counter.
     */
    void setTurnCount(int newCount) {

        turnCount = newCount;

    }

}