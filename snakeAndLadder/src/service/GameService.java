package service;

import model.Player;
import model.Board;

import java.util.Random;

import java.util.LinkedList;
import java.util.Queue;

public class GameService {

    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public GameService(Board board, Queue<Player> players) {
        this.board = board;
        this.players = players;
        this.dice = new Dice();
    }

    public void startGame() {

        while (true) {

            Player currentPlayer = players.poll();

            int diceValue = dice.roll();
            int oldPosition = currentPlayer.getPosition();
            int newPosition = oldPosition + diceValue;

            // Rule: cannot go beyond 100
            if (newPosition > board.getSize()) {
                newPosition = oldPosition;
            } else {
                newPosition = board.getFinalPosition(newPosition);
            }

            currentPlayer.setPosition(newPosition);

            System.out.println(currentPlayer.getName() +
                    " rolled a " + diceValue +
                    " and moved from " + oldPosition +
                    " to " + newPosition);

            // Win condition
            if (newPosition == board.getSize()) {
                System.out.println(currentPlayer.getName() + " wins the game");
                break;
            }

            players.add(currentPlayer);
        }
    }
}
