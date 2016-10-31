package ru.innopolis.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, осуществляющий выход пользователя
 */
@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(AuthServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            session.invalidate();
            logger.info("User is successfully logged out!");
            out.println("You are successfully logged out!");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}
