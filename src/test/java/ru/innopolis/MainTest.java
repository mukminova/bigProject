package ru.innopolis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Тест для класса MainServlet
 */
public class MainTest {
    private static Logger log = LoggerFactory.getLogger(MainServlet.class);

    /**
     * Тест подгрузки класса драйвера
     * @throws IOException
     */
    @Test
    public void testGetDriverClass() throws IOException {
        log.info("This is testGetResourceByPath method");
        try {
            MainServlet.getDriverClass();
        } catch (Exception e) {
            log.warn("The testGetResourceByPath failed");
        }
    }
}
