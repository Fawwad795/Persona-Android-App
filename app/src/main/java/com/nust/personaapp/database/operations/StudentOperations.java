package com.nust.personaapp.database.operations;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nust.personaapp.database.DatabaseManager;
import com.nust.personaapp.models.Student;
import com.nust.personaapp.models.UndergradStudent;
import com.nust.personaapp.models.PostgradStudent;

/**
 * StudentOperations - Handles all student-related database operations
 */
public class StudentOperations {
    private DatabaseManager dbManager;
    
    public StudentOperations() {
        dbManager = DatabaseManager.getInstance();
    }
    
    /**
     * Interface for student authentication callbacks
     */
    public interface AuthenticationCallback {
        void onSuccess(Student student);
        void onError(String errorMessage);
    }
    
    /**
     * Interface for student registration callbacks
     */
    public interface RegistrationCallback {
        void onSuccess();
        void onError(String errorMessage);
    }
    
    /**
     * Authenticate a student with Qalam ID and password
     * @param qalamId Student's Qalam ID
     * @param password Student's password
     * @param callback AuthenticationCallback to handle the result
     */
    public void authenticateStudent(String qalamId, String password, AuthenticationCallback callback) {
        dbManager.getStudentByQalamId(qalamId, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Get password from database
                    String passwordFromDB = snapshot.child(qalamId).child("password").getValue(String.class);
                    
                    if (passwordFromDB != null && passwordFromDB.equals(password)) {
                        // Password correct, create student object
                        String degreeFromDB = snapshot.child(qalamId).child("degree").getValue(String.class);
                        Student student = createStudentFromSnapshot(snapshot.child(qalamId), degreeFromDB);
                        
                        if (student != null) {
                            callback.onSuccess(student);
                        } else {
                            callback.onError("Error creating student object");
                        }
                    } else {
                        callback.onError("Incorrect password");
                    }
                } else {
                    callback.onError("No such user exists");
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError("Database error: " + error.getMessage());
            }
        });
    }
    
    /**
     * Register a new student
     * @param student Student object to register
     * @param callback RegistrationCallback to handle the result
     */
    public void registerStudent(Student student, RegistrationCallback callback) {
        // First check if student already exists
        dbManager.checkStudentExists(student.getQalamId(), new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    callback.onError("Student with this Qalam ID already exists");
                } else {
                    // Student doesn't exist, proceed with registration
                    dbManager.saveStudent(student);
                    callback.onSuccess();
                }
            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError("Database error: " + error.getMessage());
            }
        });
    }
    
    /**
     * Update student information
     * @param student Updated student object
     * @param callback RegistrationCallback to handle the result
     */
    public void updateStudent(Student student, RegistrationCallback callback) {
        dbManager.updateStudent(student.getQalamId(), student);
        callback.onSuccess();
    }
    
    /**
     * Create student object from Firebase DataSnapshot based on degree type
     * @param dataSnapshot Firebase DataSnapshot containing student data
     * @param degree Student's degree type
     * @return Student object (UndergradStudent or PostgradStudent)
     */
    private Student createStudentFromSnapshot(DataSnapshot dataSnapshot, String degree) {
        if (degree == null) return null;
        
        if (degree.equals("Undergraduate")) {
            return new UndergradStudent(dataSnapshot);
        } else if (degree.equals("Postgraduate")) {
            // Check what fields are available for postgrad students
            boolean hasDepartment = dataSnapshot.hasChild("department");
            boolean hasSemester = dataSnapshot.hasChild("semester");
            
            if (!hasSemester) {
                if (hasDepartment) {
                    return new PostgradStudent(dataSnapshot, false);
                } else {
                    return new PostgradStudent(dataSnapshot, false, false);
                }
            } else {
                return new PostgradStudent(dataSnapshot);
            }
        }
        
        return null;
    }
}
