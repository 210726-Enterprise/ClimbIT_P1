package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.MrORM;
import com.revature.model.Climber;
import com.revature.model.Mountain;
import com.revature.service.ClimberService;
import com.revature.service.MountainService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new MrORM();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        Climber climberQuery = new Climber();
        Mountain mountainQuery = new Mountain();

        ClimberService climberService = new ClimberService(objectMapper, climberQuery);
        MountainService mountainService = new MountainService(objectMapper, mountainQuery);

        ServletContext context = sce.getServletContext();

        context.addServlet("ClimberServlet", new ClimberServlet(climberService)).addMapping("/api/climbers");
        context.addServlet("MountainServlet", new MountainServlet(mountainService)).addMapping("/api/mountains");
    }
}