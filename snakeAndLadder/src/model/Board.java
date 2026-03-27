package model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int SIZE = 100;

    private Map<Integer, Integer> jumps = new HashMap<>();

    public void addSnake(int head, int tail) {
        jumps.put(head, tail);
    }

    public void addLadder(int start, int end) {
        jumps.put(start, end);
    }

    public int getFinalPosition(int position) {

        // Handle chain jumps
        while (jumps.containsKey(position)) {
            position = jumps.get(position);
        }

        return position;
    }

    public int getSize() {
        return SIZE;
    }
}