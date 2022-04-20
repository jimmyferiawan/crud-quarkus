package org.acme.handler;

import org.acme.model.Employee;

public class RespEmployee extends RespHandler{
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
