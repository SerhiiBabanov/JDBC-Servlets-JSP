package ua.goit.hw6.controller;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.config.PropertiesConfig;
import ua.goit.hw6.model.dto.SkillDto;
import ua.goit.hw6.repository.SkillRepository;
import ua.goit.hw6.service.SkillService;
import ua.goit.hw6.service.conventer.SkillConverter;

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

@WebServlet("/skills")
public class SkillsController extends HttpServlet {

    private SkillService skillService;

    @Override
    public void init() {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        SkillRepository skillRepository = new SkillRepository(manager);
        SkillConverter skillConverter = new SkillConverter();
        skillService = new SkillService(skillRepository, skillConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<SkillDto> skills = new ArrayList<>();
            skills.add(skillService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(SkillDto::new));
            req.setAttribute("skills", skills);
            req.getRequestDispatcher("/WEB-INF/jsp/developers.jsp").forward(req, resp);
        }

        List<SkillDto> skills = skillService.getAll();
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/jsp/skills.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<SkillDto> skillDto = skillService.getById(Long.valueOf(req.getParameter("id")));
            skillDto.ifPresent((skill) -> skillService.delete(skill));
            req.removeAttribute("id");
            req.getRequestDispatcher("/WEB-INF/jsp/skills.jsp").forward(req, resp);
        }
    }
}
