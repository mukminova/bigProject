package ru.innopolis.servlet;

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
 * Сервлет, осуществляющий редактирование/просмотр данных пользователя
 */
@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends MainServlet {
    private static final String UPDATE_PROFILE_REQUEST = "UPDATE users SET name = ?, lname = ?, password = ? where email = ?";
    private static final String SELECT_PROFILE_REQUEST = "select name, lname, password from users where email = ?";
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

        if (user.getPassword() != null && !user.isEmpty()) {
            getDriverClass();
            try (Connection connection = DriverManager.getConnection(URL, USER, PWD);
                 PreparedStatement ps = connection.prepareStatement(UPDATE_PROFILE_REQUEST)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getLname());
                ps.setString(3, user.getPassword());
                ps.setString(4, user.getEmail());
                if (ps.executeUpdate() > 0) {
                    logger.info("You are successfully update...");
                    out.println("You are successfully update...");
                    request.getRequestDispatcher("index.jsp").include(request, response);
                }
            } catch (SQLException e) {
                logger.warn(e.getMessage());
                out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            logger.info("Incorrect entered data");
            out.println("Incorrect entered data");
            request.getRequestDispatcher("profile.jsp").include(request, response);
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);/*???????????????*/

        if (session != null && session.getAttribute(FIELD_USER_EMAIL) != null) {
            Users user = new Users();
            user.setEmail((String) session.getAttribute(FIELD_USER_EMAIL));
            try (Connection connection = DriverManager.getConnection(URL, USER, PWD);
                 PreparedStatement ps = connection.prepareStatement(SELECT_PROFILE_REQUEST)) {
                ps.setString(1, user.getEmail());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    request.setAttribute(FIELD_USER_NAME, rs.getString("name"));
                    request.setAttribute(FIELD_USER_LNAME, rs.getString("lname"));
                    request.setAttribute(FIELD_USER_PASSWORD, rs.getString("password"));
                }
                rs.close();
            } catch (SQLException e) {
                logger.warn(e.getMessage());
                out.println(e.getMessage());
                e.printStackTrace();
            }
            logger.info("Hello, " + user.getEmail() + " Welcome to Profile");
            out.println("Hello, " + user.getEmail() + " Welcome to Profile");
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            logger.info("Please login first");
            out.println("Please login first");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
        out.close();
    }
}
