package ru.innopolis.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Модель данных пользователя
 */
public class Users {
    private Integer userId;
    private String name;
    private String lname;
    private String password;
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Шифрование пароля пользователя
     *
     * @param password - пароль пользователя
     */
    public void setPassword(String password) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        this.password = md5Hex;
    }

    /**
     * Проверка идентичности введенных паролей
     *
     * @param password       - пароль пользователя
     * @param repeatPassword - повтор пароля для проверки
     */
    public void setWithRepeatPassword(String password, String repeatPassword) {
        if (!password.equals("") && password.equals(repeatPassword)) {
            this.setPassword(password);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public boolean isEmpty() {
        if (email.equals("") && password.equals("") && name.equals("")) {
            return true;
        }
        return false;
    }
}
