package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.ProjectDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projectCreate")
public class ProjectCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto emptyProjectDto = new ProjectDto();
        req.setAttribute("project", emptyProjectDto);
        req.setAttribute("dateValue", null);
        req.getRequestDispatcher("/WEB-INF/jsp/projectCreateForm.jsp").forward(req, resp);
    }
}
