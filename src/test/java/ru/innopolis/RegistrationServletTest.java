package ru.innopolis;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.servlet.RegistrationServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Незаконченный тест для сервлета регистрации
 */
public class RegistrationServletTest {
    private static Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    private RegistrationServlet registrationServlet;
    private Mockery context;

    @Before
    public void before() {
        log.info("This is @Before method");
        this.context = new JUnit4Mockery();
        this.registrationServlet = new RegistrationServlet();
    }

    @Test
    public void doPost() throws Exception {
        final HttpServletRequest request = context.mock(HttpServletRequest.class);
        final HttpServletResponse response = context.mock(HttpServletResponse.class);

//        context.checking(new Expectations() {{
//            oneOf(request).setAttribute("userName", "name");
//            will(returnValue(request.getParameter("userName")));
//        }});
//
//        registrationServlet.doPost(request, response);
    }



}
