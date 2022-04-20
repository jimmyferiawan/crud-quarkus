package org.acme.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mdepartemen")
public class Departemen {

    @Id
    @SequenceGenerator(name = "generator_mdepartemen_seq", sequenceName = "mdepartemen_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "generator_mdepartemen_seq", strategy = GenerationType.SEQUENCE)
    @Column
    private int mDepartemenPk;
    private String deptCode;
    private String deptName;

//    @OneToMany(mappedBy = "departemen")
//    private List<Employee> employees;
//
//    private List<Employee> getEmployees() {
//        return employees;
//    }
//
//    private void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

    public int getmDepartemenPk() {
        return mDepartemenPk;
    }

    public void setmDepartemenPk(int mDepartemenPk) {
        this.mDepartemenPk = mDepartemenPk;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
