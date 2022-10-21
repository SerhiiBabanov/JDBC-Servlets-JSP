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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/customers")
public class CustomersController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() {
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
        if (req.getParameterMap().containsKey("id")) {
            List<CustomerDto> customers = new ArrayList<>();
            customers.add(customerService.getById(Long.valueOf(req.getParameter("id"))).orElseGet(CustomerDto::new));
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/WEB-INF/jsp/customers.jsp").forward(req, resp);
        }

        List<CustomerDto> customers = customerService.getAll();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/jsp/customers.jsp").forward(req, resp);
    }
}
