package ru.fix.task.controller;

import org.springframework.boot.web.servlet.ServletComponentScan;
import ru.fix.task.entity.Board;
import ru.fix.task.entity.Cell;
import ru.fix.task.service.ParamsValidator;
import ru.fix.task.service.StepsCounter;
import ru.fix.task.utils.PositionHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ServletController", urlPatterns = "/horse/servlet/count")
public class ServletController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = Integer.parseInt((String) request.getParameter("width"));
        int height = Integer.parseInt((String) request.getParameter("height"));
        String start = (String) request.getParameter("start");
        String end = (String) request.getParameter("end");

        try(PrintWriter writer = response.getWriter()) {
            if(!ParamsValidator.isValidBoardParams(width, height)) {
                response.setStatus(400);
                writer.println("Invalid board width/height parameters");
            } else if(!ParamsValidator.isValidCellParams(start, width, height)) {
                response.setStatus(400);
                writer.println("Not valid start parameter");
            } else if(!ParamsValidator.isValidCellParams(end, width, height)) {
                response.setStatus(400);
                writer.println("Not valid end parameter");
            } else{
                Board board = new Board(width, height);
                Cell startCell = new Cell(PositionHandler.getHorizontalPosition(start),
                        PositionHandler.getVerticalPosition(start));
                Cell endCell = new Cell(PositionHandler.getHorizontalPosition(end),
                        PositionHandler.getVerticalPosition(end));
                response.setStatus(200);
                writer.println(StepsCounter.findMinPath(board, startCell, endCell));
            }
        } catch (IOException e) {
            response.setStatus(418);
        }
    }

}
