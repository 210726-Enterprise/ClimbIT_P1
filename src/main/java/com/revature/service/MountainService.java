package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Mountain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MountainService {
    private static final Logger logger = LoggerFactory.getLogger(ClimberService.class);

    private ObjectMapper mapper;
    private Mountain mountainQuery;

    public MountainService(ObjectMapper mapper, Mountain mountainQuery) {


        this.mapper = mapper;
        this. mountainQuery = mountainQuery;
    }

    public void getMountains(HttpServletRequest req, HttpServletResponse resp) {

        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        logger.info(String.format("getMountains - %s", ipAddress));


        Mountain mtnQuery = (Mountain) mountainQuery.query();
        // run through valid params and attach to query
        if (req.getParameter("min") != null) {
            int min = Integer.parseInt(req.getParameter("min"));
            mtnQuery.where("feet").greaterThanEqualTo(min);
        }
        if (req.getParameter("max") != null) {
            int max = Integer.parseInt(req.getParameter("max"));
            mtnQuery.where("feet").lessThanEqualTo(max);
        }
        if (req.getParameter("location") != null) {
            String loc = req.getParameter("location");
            mtnQuery.where("location").like(loc);
        }
        if (req.getParameter("name") != null) {
            String name = req.getParameter("name");
            mtnQuery.where("name").like(name);
        }

        List<Object> mountains = mtnQuery.all();

        try {
            String json = mapper.writeValueAsString(mountains);
            resp.getOutputStream().print(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
