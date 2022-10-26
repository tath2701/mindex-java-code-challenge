package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    /**
     * GET endpoint to generate a report associated with an employee.
     * @param id employeeId
     * @return ReportingStructure.
     */
    @GetMapping("/report/{id}")
    public ReportingStructure getNumberOfReports(@PathVariable String id) {
        LOG.debug("Received report for the employee with id [{}]", id);
        return reportService.getReport(id);
    }

}
