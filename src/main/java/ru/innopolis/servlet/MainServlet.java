package ru.innopolis.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Родительский сервлет сервлетов, для общих полей
 */
@WebServlet(name = "MainServlet")
public abstract class MainServlet extends HttpServlet {
    public static final String FIELD_USER_NAME = "userName";
    public static final String FIELD_USER_LNAME = "userLastName";
    public static final String FIELD_USER_EMAIL = "userEmail";
    public static final String FIELD_USER_PASSWORD = "userPass";
    public static final String FIELD_USER_REPEAT_PWD = "userRepeatPass";

}
