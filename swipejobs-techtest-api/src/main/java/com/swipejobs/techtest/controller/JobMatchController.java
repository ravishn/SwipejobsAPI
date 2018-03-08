package com.swipejobs.techtest.controller;

import com.swipejobs.techtest.model.Job;
import com.swipejobs.techtest.service.JobMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class which computes the matching jobs based on worker's skills, certifications, location preference
 * and Drivers Licence requirement for each job
 */
@RestController
public class JobMatchController {

    //instance of Logger to log relevant information within the scope of the application
    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchController.class);

    //instance of JobMatchService to get matched jobs
    @Autowired
    private JobMatchService jobMatcherService;

    /**
     * API to determine the best matching jobs for a given worker based on UserID.
     * @param workerId
     * @return List of jobs matching worker's skills, certifications, location preference and Driver Licence requirement
     */
    @RequestMapping(value = "/jobmatcher/{workerId}", method = RequestMethod.GET)
    List<Job> getMatchingJobs(@PathVariable String workerId) {

        LOGGER.info("New JobMatch request received for worker with ID {}", workerId);
        try {
            List<Job> jobs = jobMatcherService.getMatchedJobs(workerId);
            LOGGER.info("Found {} matching jobs for worker with ID {}",jobs.size(),workerId);
            return jobs;
        } catch (Exception ex){
            LOGGER.error("Error occurred while processing job match.",ex);
            return new ArrayList<>();
        }
    }
}
