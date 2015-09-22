/**
 * @author Tulskih Anton
 * 19.03.2015
 *
 * All the logic for player.
 */
package com.NoughtsAndCrosses;

class Player {

    private String name;
    private Boolean playWithNoughts;
    private Boolean ai;

    /**
     * Create a player with the defined name, that is between 2 and 50 characters and may contain digits,
     * whitespaces and latin or cyrillic letters. Also sets isWinner parameter to "false" by default.
     *
     * @param name preferable name for the player.
     * @param ai   whether the player a human or a computer.
     */
    Player(String name, boolean ai) {

        this.ai = ai;

        if (name.length() < 2 || name.length() > 50) {

            System.out.println("Length of the name must be between 2 and 50 characters.\nPlease try again:");

        } else if (name.matches("[A-Za-z0-9_]{2,50}")) {

            this.name = name;

        } else {

            System.out.println("Incorrect player name.\nIt may contain english letters, digits and low line sign.\nPlease try again:");

        }

    }

    /**
     * Getter method for players' name.
     *
     * @return players' name or null if the player hasn't one.
     */
    public String getName() {

        if (name != null) {

            return name;

        } else {

            return null;

        }

    }

    /**
     * Returns description of the player.
     *
     * @return description of the player.
     */
    @Override
    public String toString() {

        return "Player{" +
                "name='" + name + '\'' +
                '}';

    }

    /**
     * Sets the player to play with noughts.
     */
    void setPlayWithNoughts() {

        playWithNoughts = true;

    }

    /**
     * Sets the player to play with crosses.
     */
    void setPlayWithCrosses() {

        playWithNoughts = false;

    }

    /**
     * Returns whether the player plays with noughts or crosses.
     *
     * @return playWithNoughts.
     */
    boolean getPlayWith() {

        return playWithNoughts;

    }

    /**
     * Finds if the player is computer or not.
     *
     * @return true if the player is computer, otherwise returns false.
     */
    boolean getAi() {

        return ai;

    }

}