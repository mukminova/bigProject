package ru.innopolis.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.dao.UsersDao;
import ru.innopolis.dao.impl.UsersDaoImpl;
import ru.innopolis.model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Сервлет, осуществляющий регистрацию пользователя
 */
@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends MainServlet {

    private static Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        Users user = new Users();
        user.setEmail(request.getParameter(FIELD_USER_EMAIL));
        user.setName(request.getParameter(FIELD_USER_NAME));
        user.setLname(request.getParameter(FIELD_USER_LNAME));
        user.setWithRepeatPassword(request.getParameter(FIELD_USER_PASSWORD), request.getParameter(FIELD_USER_REPEAT_PWD));

        UsersDao usersDao = new UsersDaoImpl();
        try {
            if (user.getPassword() != null && !user.isEmpty() && usersDao.addUser(user)) {
                session.setAttribute(FIELD_USER_EMAIL, user.getEmail());
                logger.info("You are successfully registered...");
                out.println("You are successfully registered...");
                request.getRequestDispatcher("index.jsp").include(request, response);
            } else {
                logger.info("Incorrect entered data");
                out.println("Incorrect entered data");
                request.getRequestDispatcher("registration.jsp").include(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
}
