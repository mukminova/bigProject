package ru.innopolis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Сервлет, осуществляющий аутентификацию пользователя
 */
@WebServlet(name = "AuthServlet")
public class AuthServlet extends MainServlet {
    private static final String AUTH_SELECT_REQUEST = "select * from users where email=? and password=?";
    private static Logger logger = LoggerFactory.getLogger(AuthServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Users user = new Users();
        user.setEmail(request.getParameter(FIELD_USER_EMAIL));
        user.setPassword(request.getParameter(FIELD_USER_PASSWORD));
        getDriverClass();
        try (Connection connection = DriverManager.getConnection(URL, USER, PWD);
             PreparedStatement ps = connection.prepareStatement(AUTH_SELECT_REQUEST)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                logger.info(user.getEmail() + " user authenticated");
                out.println(user.getEmail() + " user authenticated");
                session.setAttribute(FIELD_USER_EMAIL, user.getEmail());
                request.getRequestDispatcher("index.jsp").include(request, response);
            } else {
                logger.info("Incorrect email or password");
                out.println("Incorrect email or password");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
            rs.close();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            out.println(e.getMessage());
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

