package ru.innopolis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Li on 30.10.16.
 */
@WebServlet(name = "MainServlet")
public abstract class MainServlet extends HttpServlet {
    public static final String URL = "jdbc:postgresql://localhost:5432/education";
    public static final String USER = "postgres";
    public static final String PWD = "";
    public static final String DRIVER_NAME = "org.postgresql.Driver";

    public static final String FIELD_USER_NAME = "userName";
    public static final String FIELD_USER_LNAME = "userLastName";
    public static final String FIELD_USER_EMAIL = "userEmail";
    public static final String FIELD_USER_PASSWORD = "userPass";
    public static final String FIELD_USER_REPEAT_PWD = "userRepeatPass";



    public static void getDriverClass() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
