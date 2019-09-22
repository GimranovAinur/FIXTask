package ru.fix.task.entity;

/**
 * Ячейка доски.
 */
public class Cell {

    /** Координаты ячейки по оси X и Y соответственно */
    private int _xCoordinate, _yCoordinate;

    /**  Количество ходов от стартовой ячейки */
    private int _distance;

    /**
     * Конструктор.
     *
     * @param aXCoordinate координата по оси Х
     * @param aYCoordinate координата по оси Y
     */
    public Cell(int aXCoordinate, int aYCoordinate) {
        _xCoordinate = aXCoordinate;
        _yCoordinate = aYCoordinate;
    }

    /**
     * Конструктор.
     *
     * @param aXCoordinate координата по оси Х
     * @param aYCoordinate координата по оси Y
     * @param aDistance количество ходов от стартовой ячейки
     */
    public Cell(int aXCoordinate, int aYCoordinate, int aDistance) {
        _xCoordinate = aXCoordinate;
        _yCoordinate = aYCoordinate;
        _distance = aDistance;
    }

    /**
     * Возвращает координату по оси Х.
     *
     * @return координата по оси Х
     */
    public int getXCoordinate() {
        return _xCoordinate;
    }

    /**
     * Возвращает координату по оси Y.
     *
     * @return координата по оси Y
     */
    public int getYCoordinate() {
        return _yCoordinate;
    }

    /**
     * Возвращает количество ходов от стартовой ячейки.
     *
     * @return количество ходов от стартовой ячейки
     */
    public int getDistance() {
        return _distance;
    }

}
