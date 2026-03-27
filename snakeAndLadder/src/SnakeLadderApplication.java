import model.Board;
import model.Player;
import service.GameService;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakeLadderApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Board board = new Board();

        // Snakes
        int snakes = scanner.nextInt();
        for (int i = 0; i < snakes; i++) {
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            board.addSnake(head, tail);
        }

        // Ladders
        int ladders = scanner.nextInt();
        for (int i = 0; i < ladders; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            board.addLadder(start, end);
        }

        // Players
        int playersCount = scanner.nextInt();
        Queue<Player> players = new LinkedList<>();

        for (int i = 0; i < playersCount; i++) {
            String name = scanner.next();
            players.add(new Player(name));
        }


        GameService gameService = new GameService(board, players);
        gameService.startGame();
    }
}