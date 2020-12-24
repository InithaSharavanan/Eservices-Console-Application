/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhcl_eservices;

import java.util.ArrayList;

/**
 *
 * @author asini
 */
public interface DataStorage {
    public String StudentSignIn(String id, String password);
    public String FacultySignIn(String id, String password);
    public ArrayList<Course> getOpenCourses();
    public ArrayList<Course> getCourseDetails(String courseId);
    public void RegisterCourse(String studentId,String courseId);
    public void UpdateCourseCount(String courseId);
    public int getStudentsEnrolledCount(String CourseId);
    public void UpdateCourseStatus(String courseId);
    public boolean checkCourseAvailability(String courseId);
    public boolean IsRegistered(String courseId);
    public ArrayList<Course> getSchedule(String id);
    public void dropCourse(String id,String courseId);
    public int getStudentCourses(String id);
    public ArrayList<Course> getFacultySchedule(String id);
    public ArrayList<StudentAccount> getStudents(String courseId);
    
}
