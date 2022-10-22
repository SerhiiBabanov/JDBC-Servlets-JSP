package ua.goit.hw6.controller;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.config.PropertiesConfig;
import ua.goit.hw6.model.dto.CompanyDto;
import ua.goit.hw6.repository.CompanyRepository;
import ua.goit.hw6.service.CompanyService;
import ua.goit.hw6.service.conventer.CompanyConverter;

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

@WebServlet("/companies")
public class CompaniesController extends HttpServlet {

    private CompanyService companyService;

    @Override
    public void init() {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");

        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        CompanyRepository companyRepository = new CompanyRepository(manager);
        CompanyConverter companyConverter = new CompanyConverter();
        companyService = new CompanyService(companyRepository, companyConverter);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            List<CompanyDto> companies = new ArrayList<>();
            companies.add(companyService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(CompanyDto::new));
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/WEB-INF/jsp/companies.jsp").forward(req, resp);
        }

        List<CompanyDto> companies = companyService.getAll();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/companies.jsp").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey("id")) {
            Optional<CompanyDto> companyDto = companyService.getById(Long.valueOf(req.getParameter("id")));
            companyDto.ifPresent((company) -> companyService.delete(company));
            req.removeAttribute("id");
            String redirect =
                    resp.encodeRedirectURL(req.getContextPath() + "/companies");
            resp.sendRedirect(redirect);
        }
    }
}
