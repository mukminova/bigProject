package ru.innopolis.model;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * тесты модели юзеров
 */
public class UsersTest {
    private static Logger log = LoggerFactory.getLogger(Users.class);
    private Users user;

    @Before
    public void before() {
        this.user = new Users();
    }

    /**
     * Тест корректного сохранения пароля
     * @throws Exception
     */
    @Test
    public void testSetPassword() throws Exception {
        log.info("This is testSetPassword method");
        this.user.setPassword("1");
        assertEquals("md5 is not working", "c4ca4238a0b923820dcc509a6f75849b", this.user.getPassword());
    }
}