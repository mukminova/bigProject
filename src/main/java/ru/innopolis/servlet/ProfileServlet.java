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
 * Сервлет, осуществляющий редактирование/просмотр данных пользователя
 */
@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends MainServlet {
    private static Logger logger = LoggerFactory.getLogger(ProfileServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        Users user = new Users();
        user.setEmail((String) session.getAttribute(FIELD_USER_EMAIL));
        user.setName(request.getParameter(FIELD_USER_NAME));
        user.setLname(request.getParameter(FIELD_USER_LNAME));
        user.setWithRepeatPassword(request.getParameter(FIELD_USER_PASSWORD), request.getParameter(FIELD_USER_REPEAT_PWD));

        UsersDao usersDao = new UsersDaoImpl();
        try {
            if (user.getPassword() != null && !user.isEmpty() && usersDao.updateUser(user)) {
                logger.info("You are successfully update...");
                out.println("You are successfully update...");
                request.getRequestDispatcher("index.jsp").include(request, response);
            } else {
                logger.info("Incorrect entered data");
                out.println("Incorrect entered data");
                request.getRequestDispatcher("profile.jsp").include(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(FIELD_USER_EMAIL) != null) {
            UsersDao usersDao = new UsersDaoImpl();
            try {
                Users user = usersDao.getUserByEmail((String) session.getAttribute(FIELD_USER_EMAIL));
                request.setAttribute(FIELD_USER_NAME, user.getName());
                request.setAttribute(FIELD_USER_LNAME, user.getLname());
                request.setAttribute(FIELD_USER_PASSWORD, user.getPassword());
                logger.info("Hello, " + user.getEmail() + " Welcome to Profile");
                out.println("Hello, " + user.getEmail() + " Welcome to Profile");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Please login first");
            out.println("Please login first");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
        out.close();
    }
}
