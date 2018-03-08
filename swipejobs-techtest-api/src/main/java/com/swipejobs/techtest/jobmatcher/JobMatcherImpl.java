package com.swipejobs.techtest.jobmatcher;

import com.swipejobs.techtest.model.Job;
import com.swipejobs.techtest.model.Worker;
import com.swipejobs.techtest.service.JobMatchService;
import com.swipejobs.techtest.utils.DistanceCalculatorFromLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of JobMatcher to determine the jobs which match worker's location peference, skills, certifications and
 * Driver Licence requirement
 */
@Component
public class JobMatcherImpl implements JobMatcher {

    //instance of Logger to log relevant information within the scope of the application
    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatchService.class);

    /**
     *
     * @param jobs
     * @param worker
     * @return
     */
    @Override
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker) {

        LOGGER.info("Number of available jobs are {}", jobs.size());
        List<Job> matchedJobs = new ArrayList<>();

        // Filter matching jobs based on Driving Licence Requirement.
        matchedJobs = jobs.stream().filter(p -> p.isDriverLicenseRequired() == worker.isHasDriversLicense()).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Driving Licence Requirement are {}", matchedJobs.size());

        //Filter jobs based on maximum distance a worker is willing to travel.
        matchedJobs = matchedJobs.stream().filter(p -> DistanceCalculatorFromLocation.distance(
                worker.getJobSearchAddress().getLatitude(),
                p.getLocation().getLatitude(),
                worker.getJobSearchAddress().getLongitude(),
                p.getLocation().getLongitude(),
                worker.getJobSearchAddress().getUnit()) <= worker.getJobSearchAddress().getMaxJobDistance())
                .collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after jobSearchAddress.maxJobDistance are {}", matchedJobs.size());

        //Look up for at least one matching certificate the job requires.
        matchedJobs = matchedJobs.stream().filter(p -> isWorkerHasRequiredCertificates(p.getRequiredCertificates(),worker.getCertificates())== true).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Required Certificate check are {}", matchedJobs.size());

        // Filter based on skills.
        matchedJobs = matchedJobs.stream().filter(p -> worker.getSkills().contains(p.getJobTitle())).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after skills check are {}", matchedJobs.size());

        return matchedJobs;
    }

    /**
     * return a boolean flag based on the certificates required for a particular job
     *
     * @param requiredCertificates
     * @param certificates
     * @return boolean(true if certifications are required, false otherwise)
     */
    private boolean isWorkerHasRequiredCertificates(List requiredCertificates, List certificates) {
        for(Object certificate :requiredCertificates) {
            if(certificates.contains(certificate)) {
                LOGGER.info("Matched certificate {}", certificate);
                return true;
            }
        }
        return false;
    }
}
