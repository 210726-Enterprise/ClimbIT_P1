package com.revature.servlet;

import com.revature.MrORM;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent _) {
        new MrORM();
    }
}