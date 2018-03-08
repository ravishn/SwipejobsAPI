package com.swipejobs.techtest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration class defining all the properties used in the application
 */
@Component
public class Configuration {

    // URL of the API which returns all the jobs with work requirement
    @Value("${jobResourceUrl:http://test.swipejobs.com/api/jobs}")
    private String jobResourceUrl;

    // URL of the API which returns all the workers with their certifications and skills
    @Value("${workerResourceUrl:http://test.swipejobs.com/api/workers}")
    private String workerResourceUrl;

    // define how many search results should be returned for each worker
    @Value("${maxJobSearchResults:3}")
    private int maxJobSearchResults;

    //getter methods for all properties
    public int getMaxJobSearchResults() {
        return maxJobSearchResults;
    }

    public String getJobResourceUrl() {
        return jobResourceUrl;
    }

    public String getWorkerResourceUrl() {
        return workerResourceUrl;
    }
}
