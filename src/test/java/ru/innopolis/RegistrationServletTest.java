package ru.innopolis;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;

/**
 * Created by Li on 30.10.16.
 */
public class RegistrationServletTest {
    private static Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    private Mockery context;

    @Before
    public void before() {
        log.info("This is @Before method");
        this.context = new JUnit4Mockery();
    }

    @Test
    public void doPost() throws Exception {
        HttpServletRequest request = context.mock(HttpServletRequest.class);
        HttpServletResponse response = context.mock(HttpServletResponse.class);

        context.checking(new Expectations() {{
            oneOf(request).setAttribute("userName", "name");
            oneOf(request).setAttribute("userEmail", "test@com.ru");
            oneOf(request).setAttribute("userPass", "1");
            oneOf(request).setAttribute("userRepeatPass", "1");
//            will(returnValue(request.getParameter("userName")));
//            will(request.getParameter("username")).thenReturn("me");
        }});
        //new RegistrationServlet().doPost(request, response);
    }

}
