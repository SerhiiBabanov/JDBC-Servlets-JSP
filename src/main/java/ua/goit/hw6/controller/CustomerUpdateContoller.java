package ua.goit.hw6.controller;

import ua.goit.hw6.config.DatabaseManagerConnector;
import ua.goit.hw6.config.PropertiesConfig;
import ua.goit.hw6.model.dto.CustomerDto;
import ua.goit.hw6.repository.CustomerRepository;
import ua.goit.hw6.service.CustomerService;
import ua.goit.hw6.service.conventer.CustomersConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/customerEdit")
public class CustomerUpdateContoller extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        String dbPassword = System.getenv("dbPassword");
        String dbUsername = System.getenv("dbUsername");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector manager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        CustomerRepository customerRepository = new CustomerRepository(manager);
        CustomersConverter customersConverter = new CustomersConverter();
        customerService = new CustomerService(customerRepository, customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        CustomerDto customerDto = customerService.getById(id).orElseGet(CustomerDto::new);
        req.setAttribute("customer", customerDto);
        req.getRequestDispatcher("/WEB-INF/jsp/customerUpdatePage.jsp").forward(req, resp);
    }
}
