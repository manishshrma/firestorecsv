package com.example.assignment.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "myemployee")
public class Employee {
    @Id @Column(name = "empno")
    private long EmployeeNo;
    @Column(name = "empname")
    private String EmployeeName;
    @Column(name = "basic")
    private int Basic;
    @Column(name = "hra")
    private int HRA;
    @Column(name = "sup_allowances")
    private int SupplementaryAllowance;
    @Column(name = "car_maintenance")
    private int CarMaintenance;
    @Column(name = "book_periodicals")
    private int BooksPeriodicals;
    @Column(name = "pf")
    private int PF;
    @Column(name = "incometax")
    private int IncomeTax;

    private int GrossPay;
    private int Netpay;


    public Employee()
    {

    }

    public Employee(long employeeNo, String employeeName, int basic, int HRA, int supplementaryAllowance, int carMaintenance, int booksPeriodicals, int PF, int incomeTax) {
        EmployeeNo = employeeNo;
        EmployeeName = employeeName;
        Basic = basic;
        this.HRA = HRA;
        SupplementaryAllowance = supplementaryAllowance;
        CarMaintenance = carMaintenance;
        BooksPeriodicals = booksPeriodicals;
        this.PF = PF;
        IncomeTax = incomeTax;
    }

    public long getEmployeeNo() {
        return EmployeeNo;
    }

    public void setEmployeeNo(long employeeNo) {
        EmployeeNo = employeeNo;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public int getBasic() {
        return Basic;
    }

    public void setBasic(int basic) {
        Basic = basic;
    }

    public int getHRA() {
        return HRA;
    }

    public void setHRA(int HRA) {
        this.HRA = HRA;
    }

    public int getSupplementaryAllowance() {
        return SupplementaryAllowance;
    }

    public void setSupplementaryAllowance(int supplementaryAllowance) {
        SupplementaryAllowance = supplementaryAllowance;
    }

    public int getCarMaintenance() {
        return CarMaintenance;
    }

    public void setCarMaintenance(int carMaintenance) {
        CarMaintenance = carMaintenance;
    }

    public int getBooksPeriodicals() {
        return BooksPeriodicals;
    }

    public void setBooksPeriodicals(int booksPeriodicals) {
        BooksPeriodicals = booksPeriodicals;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF) {
        this.PF = PF;
    }

    public int getIncomeTax() {
        return IncomeTax;
    }

    public void setIncomeTax(int incomeTax) {
        IncomeTax = incomeTax;
    }

    public int getGrossPay() {
        return GrossPay;
    }

    public void setGrossPay(int grossPay) {
        GrossPay = grossPay;
    }

    public int getNetpay() {
        return Netpay;
    }

    public void setNetpay(int netpay) {
        Netpay = netpay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmployeeNo=" + EmployeeNo +
                ", EmployeeName='" + EmployeeName + '\'' +
                ", Basic=" + Basic +
                ", HRA=" + HRA +
                ", SupplementaryAllowance=" + SupplementaryAllowance +
                ", CarMaintenance=" + CarMaintenance +
                ", BooksPeriodicals=" + BooksPeriodicals +
                ", PF=" + PF +
                ", IncomeTax=" + IncomeTax +
                '}';
    }
}