package com.postalservice;

import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServletInitializerTests {

    @Test
    public void testSpringApplicationBuilder() {
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder application = new SpringApplicationBuilder();

        SpringApplicationBuilder result = servletInitializer.configure(application);

        assertNotNull(result);
        assertEquals(SpringApplicationBuilder.class, result.getClass());
    }

    @Test
    public void testMailAppApplicationClass() {
        ServletInitializer servletInitializer = new ServletInitializer();
        SpringApplicationBuilder application = mock(SpringApplicationBuilder.class);

        servletInitializer.configure(application);

        verify(application).sources(PostalServiceApplication.class);
    }

}