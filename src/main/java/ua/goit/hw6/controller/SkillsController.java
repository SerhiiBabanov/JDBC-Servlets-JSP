package ua.goit.hw6.controller;

import ua.goit.hw6.model.SkillLevel;
import ua.goit.hw6.model.dto.SkillDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/skills")
public class SkillsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(5L);
        skillDto.setLanguage("Java");
        skillDto.setLevel(SkillLevel.Middle);
        List<SkillDto> skills = new ArrayList<>();
        skills.add(skillDto);
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/jsp/skills.jsp").forward(req, resp);
    }
}
