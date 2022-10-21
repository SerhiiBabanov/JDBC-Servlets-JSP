package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.ProjectDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/projects")
public class ProjectsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(4L);
        projectDto.setName("CostCalculationSystem");
        projectDto.setGit_url("someurl");
        projectDto.setCost(126000);
        projectDto.setDate(LocalDate.now().toEpochDay());
        List<ProjectDto> projects = new ArrayList<>();
        projects.add(projectDto);
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/jsp/projects.jsp").forward(req, resp);

    }
}
