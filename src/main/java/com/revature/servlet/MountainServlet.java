package com.revature.servlet;

import com.revature.service.MountainService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/mountains")
public class MountainServlet extends HttpServlet {

    MountainService service;

    public MountainServlet() {
        this.service = new MountainService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        service.getMountains(req, resp);
    }

}
