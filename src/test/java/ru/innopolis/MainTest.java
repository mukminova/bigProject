package ru.innopolis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Li on 30.10.16.
 */
public class MainTest {
    private static Logger log = LoggerFactory.getLogger(MainServlet.class);


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
