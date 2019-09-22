package ru.fix.task.entity;

import java.util.Arrays;

public class Horse {

    /** Доступные направления коня */
    private final int[][] validMoves = new int[][]
            {{-1, 2}, {1, 2}, {1, -2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};

    /**
     * Возвращает массив доступных направлений коня.
     *
     * @return массив доступных направлений коня
     */
    public int[][] getValidMoves() {
        return Arrays.copyOf(validMoves, validMoves.length);
    }

}
