package com.nust.personaapp.models;

import com.google.firebase.database.DataSnapshot;

/**
 * PostgradStudent - Represents a postgraduate student
 * Postgraduate students may or may not have department and semester
 */
public class PostgradStudent extends Student {

    public PostgradStudent(String firstName, String lastName, String degree, String school, String qalamId, String email, String password, int percentMonthlyAttendance) {
        super(firstName, lastName, degree, school, null, null, qalamId, email, password, percentMonthlyAttendance);
    }

    public PostgradStudent(String firstName, String lastName, String degree, String school, String department, String qalamId, String email, String password, int percentMonthlyAttendance) {
        super(firstName, lastName, degree, school, department, null, qalamId, email, password, percentMonthlyAttendance);
    }

    public PostgradStudent(String firstName, String lastName, String degree, String school, String department, String semester, String qalamId, String email, String password, int percentMonthlyAttendance) {
        super(firstName, lastName, degree, school, department, semester, qalamId, email, password, percentMonthlyAttendance);
    }

    /**
     * Constructor for PostgradStudent with all fields from DataSnapshot
     * @param dataSnapshot Firebase DataSnapshot containing student data
     */
    public PostgradStudent(DataSnapshot dataSnapshot) {
        super(dataSnapshot.child("firstName").getValue(String.class),
                dataSnapshot.child("lastName").getValue(String.class),
                dataSnapshot.child("degree").getValue(String.class),
                dataSnapshot.child("school").getValue(String.class),
                dataSnapshot.child("department").getValue(String.class),
                dataSnapshot.child("semester").getValue(String.class),
                dataSnapshot.child("qalamId").getValue(String.class),
                dataSnapshot.child("email").getValue(String.class),
                dataSnapshot.child("password").getValue(String.class),
                dataSnapshot.child("percentMonthlyAttendance").getValue(Integer.class));
    }

    /**
     * Constructor for PostgradStudent with optional semester
     * @param dataSnapshot Firebase DataSnapshot containing student data
     * @param hasSemester Whether the student has semester data
     */
    public PostgradStudent(DataSnapshot dataSnapshot, boolean hasSemester) {
        super(dataSnapshot.child("firstName").getValue(String.class),
                dataSnapshot.child("lastName").getValue(String.class),
                dataSnapshot.child("degree").getValue(String.class),
                dataSnapshot.child("school").getValue(String.class),
                dataSnapshot.child("department").getValue(String.class),
                null,
                dataSnapshot.child("qalamId").getValue(String.class),
                dataSnapshot.child("email").getValue(String.class),
                dataSnapshot.child("password").getValue(String.class),
                dataSnapshot.child("percentMonthlyAttendance").getValue(Integer.class));
    }

    /**
     * Constructor for PostgradStudent with optional department and semester
     * @param dataSnapshot Firebase DataSnapshot containing student data
     * @param hasDepartment Whether the student has department data
     * @param hasSemester Whether the student has semester data
     */
    public PostgradStudent(DataSnapshot dataSnapshot, boolean hasDepartment, boolean hasSemester) {
        super(dataSnapshot.child("firstName").getValue(String.class),
                dataSnapshot.child("lastName").getValue(String.class),
                dataSnapshot.child("degree").getValue(String.class),
                dataSnapshot.child("school").getValue(String.class),
                null,
                null,
                dataSnapshot.child("qalamId").getValue(String.class),
                dataSnapshot.child("email").getValue(String.class),
                dataSnapshot.child("password").getValue(String.class),
                dataSnapshot.child("percentMonthlyAttendance").getValue(Integer.class));
    }
}
