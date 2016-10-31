package ru.innopolis.dao;

import ru.innopolis.model.Users;

/**
 * интерфейс дао пользователей для работы с бд
 */
public interface UsersDao {
    boolean addUser(Users user) throws ClassNotFoundException;

    boolean updateUser(Users user) throws ClassNotFoundException;

    boolean checkUser(Users user) throws ClassNotFoundException;

    Users getUserByEmail(String email) throws ClassNotFoundException;
}
