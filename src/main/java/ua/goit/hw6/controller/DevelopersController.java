package ua.goit.hw6.controller;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.config.PropertiesConfig;
import ua.goit.hw6.model.dto.DeveloperDto;
import ua.goit.hw6.repository.DeveloperRepository;
import ua.goit.hw6.repository.DeveloperSkillRelationRepository;
import ua.goit.hw6.repository.SkillRepository;
import ua.goit.hw6.service.DeveloperService;
import ua.goit.hw6.service.conventer.DeveloperConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/developers")
public class DevelopersController extends HttpServlet {

    private DeveloperService developerService;

    @Override
    public void init() {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        DeveloperRepository developerRepository = new DeveloperRepository(manager);
        DeveloperSkillRelationRepository dsRelationRepository = new DeveloperSkillRelationRepository(manager);
        SkillRepository skillRepository = new SkillRepository(manager);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository, dsRelationRepository,
                skillRepository, developerConverter);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<DeveloperDto> developers = new ArrayList<>();
            developers.add(developerService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(DeveloperDto::new));
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/WEB-INF/jsp/developers.jsp").forward(req, resp);
        }
        if (req.getParameterMap().containsKey("projectId")) {
            List<DeveloperDto> developers = developerService.getByProjectId(Long.valueOf(req.getParameter("projectId")));
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/WEB-INF/jsp/developers.jsp").forward(req, resp);
        }

        List<DeveloperDto> developers = developerService.getAll();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/developers.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<DeveloperDto> developerDto = developerService.getById(Long.valueOf(req.getParameter("id")));
            developerDto.ifPresent((developer) -> developerService.delete(developer));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/developers");
            resp.sendRedirect(redirect);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setName(req.getParameter("name"));
        developerDto.setUsername(req.getParameter("username"));
        developerDto.setSalary(Integer.valueOf(req.getParameter("salary")));
        developerService.create(developerDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/developers");
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(Long.valueOf(req.getParameter("id")));
        developerDto.setName(req.getParameter("name"));
        developerDto.setUsername(req.getParameter("username"));
        developerDto.setSalary(Integer.valueOf(req.getParameter("salary")));
        developerService.update(developerDto);
        String redirect =
                resp.encodeRedirectURL(req.getContextPath() + "/developers");
        resp.sendRedirect(redirect);
    }
}
