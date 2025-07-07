package com.nust.personaapp.models;

import com.google.firebase.database.DataSnapshot;
import java.io.Serializable;

/**
 * Abstract Student class - Base class for all student types
 * Contains common properties and methods for all students
 */
public abstract class Student implements Serializable {
    private String firstName, lastName, degree, school, department, semester, qalamId, email, password;
    private int percentMonthlyAttendance;

    public Student(){
    }

    public Student(String firstName, String lastName, String degree, String school, String department, String semester, String qalamId, String email, String password, int percentMonthlyAttendance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.school = school;
        this.department = department;
        this.semester = semester;
        this.qalamId = qalamId;
        this.email = email;
        this.password = password;
        this.percentMonthlyAttendance = percentMonthlyAttendance;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getQalamId() {
        return qalamId;
    }

    public void setQalamId(String qalamId) {
        this.qalamId = qalamId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPercentMonthlyAttendance() {
        return percentMonthlyAttendance;
    }

    public void setPercentMonthlyAttendance(int percentMonthlyAttendance) {
        this.percentMonthlyAttendance = percentMonthlyAttendance;
    }
}
