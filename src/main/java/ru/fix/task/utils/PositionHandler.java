package ru.fix.task.utils;

import java.util.Locale;

public class PositionHandler {

    /**
     * Возвращает позицию по горизонтальной оси Х.
     *
     * @param aPosition позиция
     * @return позиция по горизонтальной оси Х
     */
    public static int getHorizontalPosition(String aPosition) {
        return aPosition.toLowerCase(Locale.getDefault()).charAt(0) - 'a' + 1;
    }

    /**
     * Возвращает позицию по вертикальной оси Y.
     *
     * @param aPosition позиция
     * @return позиция по вертикальной оси Y
     */
    public static int getVerticalPosition(String aPosition) {
        return Integer.parseInt(aPosition.substring(1));
    }

}
