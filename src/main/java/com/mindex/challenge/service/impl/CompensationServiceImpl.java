package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationService.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * insert a compensation object to the database
     * @param compensation
     * @return compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * find the compensation associated with employerId in the database
     * @param employerId
     * @return the found compensation
     */
    @Override
    public Compensation read(String employerId) {
        LOG.debug("Creating employee with id [{}]", employerId);

        Compensation compensation = compensationRepository.findByEmployeeId(employerId);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + employerId);
        }

        return compensation;
    }
}
