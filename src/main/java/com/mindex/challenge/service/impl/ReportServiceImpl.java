package com.mindex.challenge.service.impl;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Stack;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Generate a report for an employee that computes the number of reports for an employee.
     * @param id employeeId
     * @return generated ReportingStructure
     */
    @Override
    public ReportingStructure getReport(String id) {
        LOG.debug("Getting the report of employee with id [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        int total = 0;
        HashSet<String> visited = new HashSet<>();
        Stack<String> reportsStack = new Stack<>();
        visited.add(employee.getEmployeeId());
        reportsStack.add(employee.getEmployeeId());
        while (!reportsStack.empty()) {
            Employee currEmployee = employeeRepository.findByEmployeeId(reportsStack.pop());
            if(currEmployee.getDirectReports()!= null) {
                for (Employee directEmployee : currEmployee.getDirectReports()) {
                    if (!visited.contains(directEmployee.getEmployeeId())) {
                        visited.add(directEmployee.getEmployeeId());
                        reportsStack.push(directEmployee.getEmployeeId());
                        total += 1;
                    }
                }
            }
        }
        return new ReportingStructure(employee,total);
    }
}
