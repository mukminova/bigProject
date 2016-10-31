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

/**
 * Сервлет, осуществляющий аутентификацию пользователя
 */
@WebServlet(name = "AuthServlet")
public class AuthServlet extends MainServlet {
    private static Logger logger = LoggerFactory.getLogger(AuthServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Users user = new Users();
        user.setEmail(request.getParameter(FIELD_USER_EMAIL));
        user.setPassword(request.getParameter(FIELD_USER_PASSWORD));
        UsersDao usersDao = new UsersDaoImpl();
        try {
            if (usersDao.checkUser(user)) {
                logger.info(user.getEmail() + " user authenticated");
                out.println(user.getEmail() + " user authenticated");
                session.setAttribute(FIELD_USER_EMAIL, user.getEmail());
                request.getRequestDispatcher("index.jsp").include(request, response);
            } else {
                logger.info("Incorrect email or password");
                out.println("Incorrect email or password");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

