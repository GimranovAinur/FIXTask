package ru.fix.task.service;

import ru.fix.task.entity.Board;
import ru.fix.task.utils.PositionHandler;

import java.util.regex.Pattern;

public class ParamsValidator {

    /**
     * Возвращает признак корректности параметров доски.
     *
     * @param aWidth ширина доски
     * @param aHeight высота доски
     * @return {@code true} - параметры корректны, иначе {@code false}
     */
    public static boolean isValidBoardParams(int aWidth, int aHeight) {
        return aWidth >= 3 && aHeight >= 3;
    }

    /**
     * Возвоащает признак корректности параметра координат ячейки.
     *
     * @param aParam параметр ячейки
     * @param aBoardWidth ширина доски
     * @param aBoardHeight высота доски
     * @return {@code true} - параматер корректен, иначе {@code false}
     */
    public static boolean isValidCellParams(String aParam, int aBoardWidth, int aBoardHeight) {
        return matchesCellParam(aParam)
                && StepsCounter.isInside(PositionHandler.getHorizontalPosition(aParam),
                PositionHandler.getVerticalPosition(aParam), new Board(aBoardWidth, aBoardHeight));
    }

    /**
     * Возвращает признак соответствия параметра шаблону координат шахматной доски.
     *
     * @param aParam параметр ячейки
     * @return {@code true} - параматер соответствует, иначе {@code false}
     */
    private static boolean matchesCellParam(String aParam) {
        return Pattern.matches("[a-zA-Z]\\d+", aParam);
    }

}
