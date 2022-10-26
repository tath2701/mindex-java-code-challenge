package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportServiceImpTest {
    private String reportsUrl;

    @Autowired
    private ReportService reportService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {

        reportsUrl = "http://localhost:" + port + "/report/{id}/";
    }

    private Employee generateTestEmployee(String firstName, String lastName, String department, String position) {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName(firstName);
        testEmployee.setLastName(lastName);
        testEmployee.setDepartment(department);
        testEmployee.setPosition(position);
        return testEmployee;
    }
    @Test
    public void testGetReport() {
        Employee testEmployee1 = generateTestEmployee("Paul", "McCartney", "Engineering", "Developer I");
        Employee testEmployee2 = generateTestEmployee("Ringo", "Starr", "Engineering", "Developer V");
        Employee testEmployee3 = generateTestEmployee("John", "Lennon", "Engineering", "Development Manager");
        // test case for employee with no direct reports
        ReportingStructure report = restTemplate.getForEntity(reportsUrl, ReportingStructure.class, "b7839309-3348-463b-a7e3-5de1c168beb3").getBody();
        assertNotNull(report);
        assertEmployeeEquivalence(report.getEmployee(), testEmployee1);
        assertEquals(report.getNumberOfReports(), 0);
        // test case for employee with direct reports
        report = restTemplate.getForEntity(reportsUrl, ReportingStructure.class, "03aa1462-ffa9-4978-901b-7c001562cf6f").getBody();
        assertNotNull(report);
        assertEmployeeEquivalence(report.getEmployee(), testEmployee2);
        assertEquals(report.getNumberOfReports(), 2);
        // test case for employee with multiple layers of direct reports
        report = restTemplate.getForEntity(reportsUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertNotNull(report);
        assertEmployeeEquivalence(report.getEmployee(), testEmployee3);
        assertEquals(report.getNumberOfReports(), 4);



    }

    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
