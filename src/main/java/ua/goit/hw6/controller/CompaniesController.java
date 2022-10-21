package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.CompanyDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/companies")
public class CompaniesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(2L);
        companyDto.setName("Google");
        companyDto.setCountry("USA");
        List<CompanyDto> companies = new ArrayList<>();
        companies.add(companyDto);
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/companies.jsp").forward(req, resp);
    }
}
