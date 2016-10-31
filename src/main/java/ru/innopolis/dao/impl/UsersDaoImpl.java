package ru.innopolis.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.dao.UsersDao;
import ru.innopolis.model.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Li on 31.10.16.
 */
public class UsersDaoImpl implements UsersDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/education";
    private static final String USER = "postgres";
    private static final String PWD = "";
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);

    public boolean addUser(Users user) throws ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        try (Connection connection = DriverManager.getConnection(URL, USER, PWD);
             PreparedStatement ps = connection.prepareStatement("insert into users(name,lname,password,email) values(?,?,?,?)")) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            if (ps.executeUpdate() > 0) {
                logger.info("You are successfully registered...");
                return true;
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return false;
    }
}
