package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.DeveloperDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/developers")
public class DeveloperController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(1L);
        developerDto.setName("Vova");
        developerDto.setUsername("vovas");
        developerDto.setSalary(4800);
        List<DeveloperDto> developers = new ArrayList<>();
        developers.add(developerDto);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/developers.jsp").forward(req,resp);
    }
}
