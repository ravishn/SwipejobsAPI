package com.swipejobs.techtest.service;

import com.swipejobs.techtest.config.Configuration;
import com.swipejobs.techtest.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Integration class for the Job Service API.
 */
@Component
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private Configuration configuration;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Returns the available jobs from Job Service API
     *
     * @return Jobs from the Job Service
     */
    public Set<Job> getJobs() {
        try {
            ResponseEntity<Job[]> jobResponse = restTemplate.getForEntity(configuration.getJobResourceUrl(), Job[].class);

            //Basic error scenarios
            if (null == jobResponse) {
                LOGGER.error("Failed to get Response from Jobs service");
                return null;
            } else if (HttpStatus.OK != jobResponse.getStatusCode()) {
                LOGGER.error("Failed to get proper response from Jobs Service. StatusCode={}", jobResponse.getStatusCode());
                return null;
            }
            Set<Job> jobs = Arrays.stream(jobResponse.getBody()).collect(Collectors.toSet());

            LOGGER.info("Jobs={}", jobs);
            return jobs;
        } catch (Exception e) {

            LOGGER.error("Exception returned while getting Jobs from JobService",e);
        }
        return null;
    }
}
