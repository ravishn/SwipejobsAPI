package com.swipejobs.techtest.jobmatcher;

import com.swipejobs.techtest.model.Job;
import com.swipejobs.techtest.model.Worker;

import java.util.List;
import java.util.Set;

public interface JobMatcher {

    /**
     * Matches Jobs for a worker based on his or her Location preference, Driving Licence Requirement and Skill Set
     * @param jobs
     * @param worker
     * @return List of matching jobs
     */
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker);
}
