package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.DeveloperDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developerCreate")
public class DeveloperCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto emptyDeveloperDto = new DeveloperDto();
        req.setAttribute("developer", emptyDeveloperDto);
        req.getRequestDispatcher("/WEB-INF/jsp/developerCreateForm.jsp").forward(req, resp);
    }
}
