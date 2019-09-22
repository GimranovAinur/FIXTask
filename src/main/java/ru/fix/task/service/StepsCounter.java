package ru.fix.task.service;

import ru.fix.task.entity.Board;
import ru.fix.task.entity.Cell;
import ru.fix.task.entity.Horse;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class StepsCounter {

    /**
     * Расчитывает минимальный путь коня от начальный позиции до конечной методом обхода в ширину.
     *
     * @param aBoard доска
     * @param aStart начальная позиция
     * @param aEnd конечная позиция
     * @return количество ходов, {@code -1} - если невозможно достичь
     */
    public static int findMinPath(Board aBoard, Cell aStart, Cell aEnd) {
        Horse horse = new Horse();
        Set<Cell> visited = new HashSet<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(aStart);

        while(!queue.isEmpty()) {
            Cell cell = queue.poll();
            if((cell.getXCoordinate() == aEnd.getXCoordinate())
                    && (cell.getYCoordinate() == aEnd.getYCoordinate())) {
                return cell.getDistance();
            }
            if(!isVisited(visited, cell.getXCoordinate(), cell.getYCoordinate())) {
                visited.add(cell);
                for(int[] move : horse.getValidMoves()) {
                    int xCoordinate = move[0] + cell.getXCoordinate();
                    int yCoordinate = move[1] + cell.getYCoordinate();
                    if(isInside(xCoordinate, yCoordinate, aBoard)) {
                        Cell newCell = new Cell(xCoordinate, yCoordinate, cell.getDistance() + 1);
                        queue.add(newCell);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Возвращает признак того, что координата не выходит за рамки доски.
     *
     * @param aX координата по оси Х
     * @param aY координата по оси Y
     * @param aBoard доска
     * @return {@code true} - координата в пределах доски, иначе {@code false}
     */
    public static boolean isInside(int aX, int aY, Board aBoard) {
        return (aX >= 1 && aX <= aBoard.getWidth()
                && aY >= 1 && aY <= aBoard.getHeight());
    }

    /**
     * Возвращает признак того, что ячейка была посещена ранее.
     *
     * @param aVisited множество посещенных ячеек
     * @param aX координата Х
     * @param aY координата Y
     * @return
     */
    private static boolean isVisited(Set<Cell> aVisited, int aX, int aY) {
        for(Cell cell : aVisited) {
            if((cell.getXCoordinate() == aX) && (cell.getYCoordinate() == aY)) {
                return true;
            }
        }
        return false;
    }

}
