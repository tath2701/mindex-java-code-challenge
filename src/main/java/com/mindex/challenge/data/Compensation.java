package com.mindex.challenge.data;

import java.time.Instant;

public class Compensation {

    private String employeeId;
    private int salary;
    private Instant effectiveDate;

    public Compensation(String employeeId, int salary, Instant effectiveDate) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.effectiveDate = effectiveDate;

    }
    public Compensation() {
    }
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployee(String id) {
        this.employeeId = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
