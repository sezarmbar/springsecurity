package com.sezar.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by mahmoudbarakat on 23.06.17.
 */
public class Initializer implements WebApplicationInitializer {


    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SecurityConfig.class);
        context.register(WebAppConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        context.setServletContext(servletContext);

        Dynamic servlet= servletContext.addServlet(DISPATCHER_SERVLET_NAME,new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);


    }
}
