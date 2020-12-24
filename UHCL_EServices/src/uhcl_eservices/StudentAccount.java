/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhcl_eservices;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author asini
 */
public class StudentAccount extends Account{
    

    public StudentAccount(String id, String password) {
        super(id, password);
    }

    @Override
    public void SignIn() {
        String res = data.StudentSignIn(getId(), getPassword()); 
        if(res.equals("success"))
        {
           displayMenu();
        }
    }
    public void displayMenu(){
        String userInput="";
        Scanner input=new Scanner(System.in);  
        while(!userInput.equals("x"))
        {
            System.out.println("Please make your selection from the below Menu");
            System.out.println("1: Register a Course");
            System.out.println("2: Show Class Schedule");
            System.out.println("3: Drop a Course");
            System.out.println("4: View the bill");
            System.out.println("x: Exit");
            System.out.println("");
            userInput=input.nextLine();
            if(userInput.equals("1")){
                RegisterCourse();
            }
            else if(userInput.equals("2")){
                ShowClassSchedule();
            }
            else if(userInput.equals("3")){
                DropCourse();
            }
            else if(userInput.equals("4")){
                viewBill();
            }
            
        }
    }
    public void RegisterCourse(){
        String userInput="";
        Scanner input=new Scanner(System.in);  
        System.out.println("Please make your selection from the below Menu");
        System.out.println("1: View Open Courses");
        System.out.println("2: Search a Course");
        System.out.println("x: Exit");
        System.out.println("");
        userInput=input.nextLine();
        if(userInput.equals("1")){
            ViewOpenCourses();
        }
        else if(userInput.equals("2")){
            SearchCourse();
        }
    }
    public void ViewOpenCourses(){
        ArrayList<Course> course =data.getOpenCourses();
        if(course.size()>0){
            for(int i=0;i<course.size();i++){
                 System.out.println("*\t"+course.get(i).getCourseId());
            }
            String courseId="";
            Scanner input=new Scanner(System.in);  
            System.out.println("Please enter the course Id to View the Course");
            courseId=input.nextLine();
            if(!(courseId.equals(""))){
                ArrayList<Course> Viewcourse =data.getCourseDetails(courseId);
                if(Viewcourse.size()>0){
                    for(int i=0;i<Viewcourse.size();i++){
                        System.out.println("*\t"+Viewcourse.get(i).getCourseId()+"\t\t"+Viewcourse.get(i).getCourseTitle()+"\t\t"+Viewcourse.get(i).getInstructorInfo()+"\t\t"+Viewcourse.get(i).getClassTime()+"\t\t"+Viewcourse.get(i).getClassCapacity()+"\t\t"+Viewcourse.get(i).getStudentsEnrolled()+"\t\t"+Viewcourse.get(i).getStatus()+"\t\t"+Viewcourse.get(i).getAmount());
                    }
                }
                RegisterSelectedCourse(courseId);
            }
            else{
                System.out.println("Invalid input");
            }
        }
        else{
            System.out.println("There are no Open Courses to display");
        }
        
    }
    public void SearchCourse(){
        String courseId="";
        Scanner input=new Scanner(System.in);  
        System.out.println("Please enter the course Id to View the details");
        courseId=input.nextLine();
         if(!(courseId.equals(""))){
            ArrayList<Course> Viewcourse =data.getCourseDetails(courseId);
            if(Viewcourse.size()>0){
                 for(int i=0;i<Viewcourse.size();i++){
                    System.out.println("*\t"+Viewcourse.get(i).getCourseId()+"\t\t"+Viewcourse.get(i).getCourseTitle()+"\t\t"+Viewcourse.get(i).getInstructorInfo()+"\t\t"+Viewcourse.get(i).getClassTime()+"\t\t"+Viewcourse.get(i).getClassCapacity()+"\t\t"+Viewcourse.get(i).getStudentsEnrolled()+"\t\t"+Viewcourse.get(i).getStatus()+"\t\t"+Viewcourse.get(i).getAmount());
                }
            }
            RegisterSelectedCourse(courseId);
        }
        else{
               System.out.println("Invalid input");
        }
    }
    public void  RegisterSelectedCourse(String courseId){
        String userInput="";
        Scanner input=new Scanner(System.in);  
        System.out.println("Do you want to register course: "+courseId+" (Yes/No)");
        userInput=input.nextLine();
        if(userInput.toLowerCase().equals("yes")){
            if(data.checkCourseAvailability(courseId)){
                if(!(data.IsRegistered(courseId))){
                    data.RegisterCourse(getId(),courseId);
                    data.UpdateCourseCount(courseId);
                    int count=data.getStudentsEnrolledCount(courseId);
                    if(count==2){
                        data.UpdateCourseStatus(courseId);
                    }
                }
            }
            else{
                System.out.println("Yoou have already registered this course");
            }
        }
        else{
            System.out.println("Course Is not available");
        }
    }
    public void ShowClassSchedule(){
        ArrayList<Course> course=data.getSchedule(getId());
        if(course.size()>0){
            for(int i=0;i<course.size();i++){
                 System.out.println("*\t"+course.get(i).getCourseId()+"\t\t"+course.get(i).getClassTime());
            }
            String userInput="";
            Scanner input=new Scanner(System.in);  
            System.out.println("Do you want view registered course (Yes/No)");
            userInput=input.nextLine();
            String courseId;
            if(userInput.toLowerCase().equals("yes")){
                System.out.println("Enter the course ID to view the details");
                courseId=input.nextLine();
                if(!(courseId.equals(""))){
                     ArrayList<Course> Viewcourse =data.getCourseDetails(courseId);
                    if(Viewcourse.size()>0){
                        for(int i=0;i<Viewcourse.size();i++){
                            System.out.println("*\t"+Viewcourse.get(i).getCourseId()+"\t\t"+Viewcourse.get(i).getCourseTitle()+"\t\t"+Viewcourse.get(i).getInstructorInfo()+"\t\t"+Viewcourse.get(i).getClassTime()+"\t\t"+Viewcourse.get(i).getClassCapacity()+"\t\t"+Viewcourse.get(i).getStudentsEnrolled()+"\t\t"+Viewcourse.get(i).getStatus()+"\t\t"+Viewcourse.get(i).getAmount());
                        }
                        System.out.println("");
                    }
                }
                else{
                    System.out.println("Invalid Input");
                }
            }
        }
        else{
            System.out.println("You have not registered any course to display the schedule");
        }
    }
    public void DropCourse(){
        ArrayList<Course> course=data.getSchedule(getId());
        if(course.size()>0){
            for(int i=0;i<course.size();i++){
                 System.out.println("*\t"+course.get(i).getCourseId()+"\t\t"+course.get(i).getClassTime());
            }
            String userInput="";
            Scanner input=new Scanner(System.in);  
            System.out.println("Do you want drop registered course (Yes/No)");
            userInput=input.nextLine();
            String courseId;
            if(userInput.toLowerCase().equals("yes")){
                System.out.println("Enter the course ID to drop");
                courseId=input.nextLine();
                if(!(courseId.equals(""))){
                    data.dropCourse(getId(),courseId);
                }
            }
        }else{
            System.out.println("You have not registered any course to drop");
        }
    }
    public void viewBill(){
        int courseCount=data.getStudentCourses(getId());
        if(courseCount>0){
            System.out.println("You total fees: "+courseCount*1000);
        }
        else{
            System.out.println("You have not registered any course");
        }
    }
   
}



