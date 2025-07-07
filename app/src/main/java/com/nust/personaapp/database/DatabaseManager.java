package com.nust.personaapp.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nust.personaapp.models.Student;

/**
 * DatabaseManager - Singleton class to handle all Firebase database operations
 * This class centralizes all database interactions for the Persona App
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private FirebaseDatabase database;
    private DatabaseReference studentsRef;
    
    // Database paths
    private static final String STUDENTS_PATH = "Student_Records";
    
    private DatabaseManager() {
        database = FirebaseDatabase.getInstance();
        studentsRef = database.getReference(STUDENTS_PATH);
    }
    
    /**
     * Get singleton instance of DatabaseManager
     * @return DatabaseManager instance
     */
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Get reference to students collection
     * @return DatabaseReference for students
     */
    public DatabaseReference getStudentsReference() {
        return studentsRef;
    }
    
    /**
     * Save a student to the database
     * @param student Student object to save
     */
    public void saveStudent(Student student) {
        studentsRef.child(student.getQalamId()).setValue(student);
    }
    
    /**
     * Get a student by Qalam ID
     * @param qalamId Student's Qalam ID
     * @param listener ValueEventListener to handle the response
     */
    public void getStudentByQalamId(String qalamId, ValueEventListener listener) {
        Query query = studentsRef.orderByChild("qalamId").equalTo(qalamId);
        query.addListenerForSingleValueEvent(listener);
    }
    
    /**
     * Update student data
     * @param qalamId Student's Qalam ID
     * @param student Updated student object
     */
    public void updateStudent(String qalamId, Student student) {
        studentsRef.child(qalamId).setValue(student);
    }
    
    /**
     * Delete a student
     * @param qalamId Student's Qalam ID
     */
    public void deleteStudent(String qalamId) {
        studentsRef.child(qalamId).removeValue();
    }
    
    /**
     * Check if a student exists
     * @param qalamId Student's Qalam ID
     * @param listener ValueEventListener to handle the response
     */
    public void checkStudentExists(String qalamId, ValueEventListener listener) {
        studentsRef.child(qalamId).addListenerForSingleValueEvent(listener);
    }
}
