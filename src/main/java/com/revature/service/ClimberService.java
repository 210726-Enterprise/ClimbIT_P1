package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Climber;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

public class ClimberService {
    private static final Logger logger = LoggerFactory.getLogger(ClimberService.class);

    private ObjectMapper mapper;
    private Climber climberQuery;

    public ClimberService(ObjectMapper mapper, Climber climberQuery) {
        this.mapper = mapper;
        this.climberQuery = climberQuery;
    }

    public void getClimbers(HttpServletRequest req, HttpServletResponse resp) {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        logger.info(String.format("getClimbers - %s", ipAddress));

        List<Object> climbers = climberQuery.query().all();

        try {
            String json = mapper.writeValueAsString(climbers);
            resp.getOutputStream().print(json);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }

    }

    public void insertClimber(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        logger.info(String.format("insertClimber - %s", ipAddress));

        StringBuilder builder = new StringBuilder();

        req.getReader().lines().collect(Collectors.toList()).forEach(builder::append);

        Climber climber = mapper.readValue(builder.toString(), Climber.class);

        climber.add();

        if (climber.getId()!=null){
            resp.setStatus(HttpServletResponse.SC_CREATED);
            String json = mapper
//                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(climber);
            resp.getOutputStream().print(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    public void updateClimber(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        logger.info(String.format("updateClimbers - %s", ipAddress));

        StringBuilder builder = new StringBuilder();
        req.getReader().lines().collect(Collectors.toList()).forEach(builder::append);
        Climber climber = mapper.readValue(builder.toString(), Climber.class);
        int rowsEffected = climber.update();
        if (rowsEffected>0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(climber);
            resp.getOutputStream().print(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    public void deleteClimber(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        logger.info(String.format("deleteClimbers - %s", ipAddress));

        Integer id = Integer.parseInt(req.getParameter("climberId"));
        Climber toDelete = (Climber) climberQuery.get(id);
        if (toDelete.getId()==null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getOutputStream().print(
                    String.format("{\n\t\"message\": \"there is no climber with id=%d\"\n}", id)
            );
            return;
        }
        int rowsEffected = toDelete.delete();
        if (rowsEffected>0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getOutputStream().print(
                    String.format("{\n\t\"message\": \"climber %d successfully removed\"\n}", id)
            );

        } else {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getOutputStream().print("{\n\t\"message\": \"unknown error\"\n}");
        }
    }
}
