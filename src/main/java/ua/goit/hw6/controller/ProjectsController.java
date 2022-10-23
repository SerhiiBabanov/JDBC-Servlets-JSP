package ua.goit.hw6.controller;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.config.PropertiesConfig;
import ua.goit.hw6.model.dto.ProjectDto;
import ua.goit.hw6.repository.ProjectDeveloperRelationRepository;
import ua.goit.hw6.repository.ProjectRepository;
import ua.goit.hw6.service.ProjectService;
import ua.goit.hw6.service.conventer.ProjectConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/projects")
public class ProjectsController extends HttpServlet {

    private ProjectService projectService;

    @Override
    public void init() {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        ProjectDeveloperRelationRepository pdRelationRepository = new ProjectDeveloperRelationRepository(manager);
        ProjectRepository projectRepository = new ProjectRepository(manager);
        ProjectConverter projectConverter = new ProjectConverter();
        projectService = new ProjectService(projectRepository, pdRelationRepository, projectConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<ProjectDto> projects = new ArrayList<>();
            projects.add(projectService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(ProjectDto::new));
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/WEB-INF/jsp/projects.jsp").forward(req, resp);
        }

        if (req.getParameterMap().containsKey("developerId")) {
            List<ProjectDto> projects = projectService.getProjectsByDeveloperId(Long.valueOf(req.getParameter("developerId")));
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/WEB-INF/jsp/projects.jsp").forward(req, resp);
        }

        List<ProjectDto> projects = projectService.getAll();
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/jsp/projects.jsp").forward(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<ProjectDto> projectDto = projectService.getById(Long.valueOf(req.getParameter("id")));
            projectDto.ifPresent((project) -> projectService.delete(project));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/projects");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(req.getParameter("name"));
        projectDto.setGit_url(req.getParameter("git_url"));
        projectDto.setCost(Integer.valueOf(req.getParameter("cost")));
        String dateString = req.getParameter("date");
        LocalDate date = LocalDate.parse(dateString);
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        projectDto.setDate(instant.toEpochMilli());
        projectService.create(projectDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/projects");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(Long.valueOf(req.getParameter("id")));
        projectDto.setName(req.getParameter("name"));
        projectDto.setGit_url(req.getParameter("git_url"));
        projectDto.setCost(Integer.valueOf(req.getParameter("cost")));
        String dateString = req.getParameter("date");
        LocalDate date = LocalDate.parse(dateString);
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        projectDto.setDate(instant.toEpochMilli());
        projectService.update(projectDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/projects");
        resp.sendRedirect(redirect);
    }
}
