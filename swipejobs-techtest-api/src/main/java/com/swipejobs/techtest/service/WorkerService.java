package com.swipejobs.techtest.service;

import com.swipejobs.techtest.config.Configuration;
import com.swipejobs.techtest.model.Worker;
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
 * Integration class for Worker service
 */
@Component
public class WorkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);

    @Autowired
    private Configuration configuration;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     *  Returns the list of available workers from Worker Service API.
     * @return Workers
     */
    public Set<Worker> getWorkers() {

        try {
            ResponseEntity<Worker[]> workersResponse = restTemplate.getForEntity(configuration.getWorkerResourceUrl(), Worker[].class);

            //Checking some basic error scenarios
            if (null == workersResponse) {
                LOGGER.error("Failed to get Response from Worker service");
                return null;
            } else if (HttpStatus.OK != workersResponse.getStatusCode()) {
                LOGGER.error("Failed to get proper response from Worker Service. StatusCode={}", workersResponse.getStatusCode());
                return null;
            }
            Set<Worker> workers = Arrays.stream(workersResponse.getBody()).collect(Collectors.toSet());

            LOGGER.info("Workers={}", workers);
            return workers;
        }catch (Exception e){

            LOGGER.error("Exception while trying to get Workers list from WorkerService",e);
        }
        return null;
    }
}
