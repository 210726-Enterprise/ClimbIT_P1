package com.revature.servlet;

import com.revature.service.ClimberService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/climbers")
public class ClimberServlet extends HttpServlet {

    ClimberService service;

    public ClimberServlet() {
        this.service = new ClimberService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        service.getClimbers(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        service.insertClimber(req, resp);
        // should return the id or the whole object in json
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        service.updateClimber(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            service.deleteClimber(req, resp);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

}
