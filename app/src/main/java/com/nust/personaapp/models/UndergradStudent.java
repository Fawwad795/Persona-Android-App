package com.nust.personaapp.models;

import com.google.firebase.database.DataSnapshot;

/**
 * UndergradStudent - Represents an undergraduate student
 * All undergraduate students must have department and semester
 */
public class UndergradStudent extends Student {

    public UndergradStudent(String firstName, String lastName, String degree, String school, String department, String semester, String qalamId, String email, String password, int percentMonthlyAttendance) {
        super(firstName, lastName, degree, school, department, semester, qalamId, email, password, percentMonthlyAttendance);
    }

    /**
     * Constructor to create UndergradStudent from Firebase DataSnapshot
     * @param dataSnapshot Firebase DataSnapshot containing student data
     */
    public UndergradStudent(DataSnapshot dataSnapshot) {
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
}
