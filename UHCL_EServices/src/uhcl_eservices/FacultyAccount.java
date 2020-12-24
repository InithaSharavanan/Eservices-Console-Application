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
public class FacultyAccount extends Account {

    public FacultyAccount(String id, String password) {
        super(id, password);
    }

    @Override
     public void SignIn() {
        String res = data.FacultySignIn(getId(), getPassword()); 
        if(res.equals("success"))
        {
            displaySchedule();

        }
    }
    public void displaySchedule(){
        ArrayList<Course> course=data.getFacultySchedule(getId());
        if(course.size()>0){
            for(int i=0;i<course.size();i++){
                 System.out.println("*\t"+course.get(i).getCourseId()+"\t\t"+course.get(i).getClassTime());
            }
            String courseId="";
            Scanner input=new Scanner(System.in);  
            System.out.println("Please enter the course Id to View the Course");
            courseId=input.nextLine();
            if(!(courseId.equals(""))){
                ArrayList<Course> Viewcourse =data.getCourseDetails(courseId);
                if(Viewcourse.size()>0){
                    for(int i=0;i<Viewcourse.size();i++){
                        System.out.println("*\t"+Viewcourse.get(i).getCourseId()+"\t\t"+Viewcourse.get(i).getCourseTitle()+"\t\t"+Viewcourse.get(i).getInstructorInfo()+"\t\t"+Viewcourse.get(i).getClassTime()+"\t\t"+Viewcourse.get(i).getClassCapacity()+"\t\t"+Viewcourse.get(i).getStudentsEnrolled()+"\t\t"+Viewcourse.get(i).getStatus());
                    }
                }
                System.out.println("Below are the names of the students who has enrolled the course");
                ArrayList<StudentAccount> students=data.getStudents(courseId);
                if(students.size()>0){
                    for(int i=0;i<course.size();i++){
                         System.out.println("*\t"+students.get(i).getId());
                    }
                }else{
                    System.out.println("No Students");
                }
            }
            else{
                System.out.println("Invalid input");
            }
        }
        else{
            System.out.println("There are no Courses to display");
        }
        
    }
    
}
