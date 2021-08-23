package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Mountain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MountainService {

    private ObjectMapper mapper;

    public MountainService() {
        mapper = new ObjectMapper();
    }

    public void getMountains(HttpServletRequest req, HttpServletResponse resp) {
        Mountain mtnQuery = (Mountain) new Mountain().query();
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
            resp.setContentType("json; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(mountains);
            resp.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
