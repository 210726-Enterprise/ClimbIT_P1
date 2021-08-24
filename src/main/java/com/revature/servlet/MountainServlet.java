package com.revature.servlet;

import com.revature.service.MountainService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MountainServlet extends HttpServlet {

    MountainService service;

    public MountainServlet(MountainService mountainService) {
        this.service = mountainService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        service.getMountains(req, resp);
    }

}
