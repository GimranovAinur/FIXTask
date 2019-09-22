package ru.fix.task.entity;

/**
 * Доска.
 */
public class Board {

    /** Ширина доски */
    private int _width;

    /** Высота доски */
    private int _height;

    /**
     * Конструктор.
     *
     * @param aWidth ширина
     * @param aHeight высота
     */
    public Board(int aWidth, int aHeight) {
        _width = aWidth;
        _height = aHeight;
    }

    /**
     * Возвращает ширину доски.
     *
     * @return ширина доски
     */
    public int getWidth() {
        return _width;
    }

    /**
     * Возвращает высоту доски.
     *
     * @return высота доски
     */
    public int getHeight() {
        return _height;
    }

}
