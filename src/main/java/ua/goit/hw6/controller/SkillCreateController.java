package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.SkillDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skillCreate")
public class SkillCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto emptySkillDto = new SkillDto();
        req.setAttribute("skill", emptySkillDto);
        req.getRequestDispatcher("/WEB-INF/jsp/skillCreateForm.jsp").forward(req, resp);
    }
}
