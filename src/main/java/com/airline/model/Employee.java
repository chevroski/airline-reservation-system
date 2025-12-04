package com.airline.model;

import java.time.LocalDate;

public class Employee extends Person {
    protected int numberEmp;
    protected LocalDate hiringDate;

    public Employee(int id, String name, String address, String contact, int numberEmp, LocalDate hiringDate) {
        super(id, name, address, contact);
        this.numberEmp = numberEmp;
        this.hiringDate = hiringDate;
    }

    public int getNumberEmp() {
        return numberEmp;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Employee Number: " + numberEmp);
        System.out.println("Hiring Date: " + hiringDate);
    }

    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
