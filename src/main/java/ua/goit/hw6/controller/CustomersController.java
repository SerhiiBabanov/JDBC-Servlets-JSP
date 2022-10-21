package ua.goit.hw6.controller;

import ua.goit.hw6.model.dto.CustomerDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(3L);
        customerDto.setName("Mark");
        customerDto.setEmail("mark@example.com");
        List<CustomerDto> customers = new ArrayList<>();
        customers.add(customerDto);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/jsp/customers.jsp").forward(req, resp);
    }
}
