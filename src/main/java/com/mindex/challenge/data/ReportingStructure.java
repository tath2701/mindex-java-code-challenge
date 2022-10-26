package com.mindex.challenge.data;

public class ReportingStructure {


    private Employee employee;
    private int numberOfReports;

    public ReportingStructure (Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }
    public ReportingStructure () {
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

}
