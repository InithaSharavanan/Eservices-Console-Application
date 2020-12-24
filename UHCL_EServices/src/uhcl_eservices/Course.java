/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhcl_eservices;

/**
 *
 * @author asini
 */
public class Course {   
    private String courseId;
    private String courseTitle;
    private String instructorInfo;
    private String classTime;
    private int classCapacity;
    private int StudentsEnrolled;
    private String status;
    private int amount;

    public Course(String courseId, String courseTitle, String instructorInfo, String classTime, int classCapacity, int StudentsEnrolled, String status, int amount) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.instructorInfo = instructorInfo;
        this.classTime = classTime;
        this.classCapacity = classCapacity;
        this.StudentsEnrolled = StudentsEnrolled;
        this.status = status;
        this.amount = amount;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getInstructorInfo() {
        return instructorInfo;
    }

    public void setInstructorInfo(String instructorInfo) {
        this.instructorInfo = instructorInfo;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public int getStudentsEnrolled() {
        return StudentsEnrolled;
    }

    public void setStudentsEnrolled(int StudentsEnrolled) {
        this.StudentsEnrolled = StudentsEnrolled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
 
    
}
