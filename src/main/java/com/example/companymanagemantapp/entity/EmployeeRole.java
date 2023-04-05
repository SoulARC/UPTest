package com.example.companymanagemantapp.entity;

public enum EmployeeRole {
    MANAGER("Manager"),
    DEVELOPER("Developer"),
    QA_TESTER("QA Tester"),
    DESIGNER("Designer");

    private final String name;

    EmployeeRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
