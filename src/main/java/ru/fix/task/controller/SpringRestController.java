package ru.fix.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fix.task.entity.Board;
import ru.fix.task.entity.Cell;
import ru.fix.task.service.ParamsValidator;
import ru.fix.task.service.StepsCounter;
import ru.fix.task.utils.PositionHandler;

@RestController
public class SpringRestController {

    @RequestMapping("/horse/rest/count")
    public ResponseEntity<String> count(@RequestParam(value="width", defaultValue="8") int width,
                                      @RequestParam(value="height", defaultValue="8") int height,
                                      @RequestParam(value="start") String start,
                                      @RequestParam(value = "end") String end) {

        if(!ParamsValidator.isValidBoardParams(width, height)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid board width/height parameters");
        }
        if (!ParamsValidator.isValidCellParams(start, width, height)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not valid start parameter");
        }
        if (!ParamsValidator.isValidCellParams(end, width, height)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not valid end parameter");
        }

        Board board = new Board(width, height);
        Cell startCell = new Cell(PositionHandler.getHorizontalPosition(start),
                PositionHandler.getVerticalPosition(start));
        Cell endCell = new Cell(PositionHandler.getHorizontalPosition(end),
                PositionHandler.getVerticalPosition(end));

        return ResponseEntity.ok(String.valueOf(StepsCounter.findMinPath(board, startCell, endCell)));
    }

}
