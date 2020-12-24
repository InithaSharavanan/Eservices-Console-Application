/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhcl_eservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author asini
 */
public class SQL_Database implements DataStorage {
       final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/annamalaishai70?useSSL=false";
        final String db_id="root";
        final String db_pwd="root123";
    
    Connection connection=null;
    Statement statement=null;
    ResultSet resultSet=null;

    @Override
    public String StudentSignIn(String id, String password) {
        try{
            connection=DriverManager.getConnection(DATABASE_URL,db_id,db_pwd);
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select * from student where id='"+id+"'");
            if(resultSet.next()){
                
                if(password.equals(resultSet.getString(2))){
                    return "success";
                }
                else{
                    System.out.println("The password is not correct");
                    return "The password is not correct";
                }
            }
            else{
                System.out.println("The ID :"+id+" is not found");
                return("the ID :"+id+" is not found");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Internal Error";
        }
        finally{
            try{
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
     @Override
    public String FacultySignIn(String id, String password) {
        try{
            connection=DriverManager.getConnection(DATABASE_URL,db_id,db_pwd);
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select * from faculty where id='"+id+"'");
            if(resultSet.next()){
                
                if(password.equals(resultSet.getString(2))){
                    return "success";
                }
                else{
                    System.out.println("The password is not correct");
                    return "The password is not correct";
                }
            }
            else{
                System.out.println("The ID :"+id+" is not found");
                return("the ID :"+id+" is not found");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return "Internal Error";
        }
        finally{
            try{
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Course> getOpenCourses() {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            //Get the complete profile based on id
            //select id,name,type,company from profile where id='J101#' 
            resultSet=statement.executeQuery("select * from course where status='Open'");    
            ArrayList<Course> aList = new ArrayList<>();
            //Store the profile data in arrayList
            while(resultSet.next())
            {                                    
                Course course;
                course = new Course(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getInt(8));
                aList.add(course);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Course> getCourseDetails(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            //Get the complete profile based on id
            //select id,name,type,company from profile where id='J101#' 
            resultSet=statement.executeQuery("select * from course where status='Open' and course_id='" + courseId + "' ");    
            ArrayList<Course> aList = new ArrayList<>();
            //Store the profile data in arrayList
            while(resultSet.next())
            {                                    
                Course course;
                course = new Course(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getInt(8));
                aList.add(course);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void RegisterCourse(String studentId, String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into studentregistration values('" + studentId + "','" + courseId + "')");
            System.out.println("You have successfully registered to "+courseId);
            
            
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong during course registratin");
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
    

    @Override
    public void UpdateCourseCount(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set students_enrolled=students_enrolled+1 where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong during course count updation");
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public int getStudentsEnrolledCount(String CourseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            //Get the complete profile based on id
            //select id,name,type,company from profile where id='J101#' 
            resultSet=statement.executeQuery("select students_enrolled from course where course_id='" + CourseId + "' ");    
            //Store the profile data in arrayList
            if(resultSet.next())
            {                                    
                return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void UpdateCourseStatus(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("update course set status='Closed' where course_id='" + courseId + "'");
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong during course status updation");
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public boolean checkCourseAvailability(String courseId) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            resultSet=statement.executeQuery("select status from course where course_id='" + courseId + "' ");      
            while(resultSet.next())
            {                                    
               return true;
            }
            return false;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public boolean IsRegistered(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            resultSet=statement.executeQuery("select * from studentregistration where course_id='" + courseId + "' ");      
            while(resultSet.next())
            {                                    
               return true;
            }
            return false;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Course> getSchedule(String id) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            //Get the complete profile based on id
            //select id,name,type,company from profile where id='J101#' 
            resultSet=statement.executeQuery("select * from course c, studentregistration s where s.course_id=c.course_id and s.student_id='" + id + "' ");    
            ArrayList<Course> aList = new ArrayList<>();
            //Store the profile data in arrayList
            while(resultSet.next())
            {                                    
                Course course;
                course = new Course(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getInt(8));
                aList.add(course);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void dropCourse(String id, String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("delete from studentregistration where student_id='" + id + "' and course_id='" + courseId + "'");
            System.out.println("You have successfully deleted  "+courseId);
            
            
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            System.out.println("Something went wrong during course drop");
            e.printStackTrace();
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }

    }

    @Override
    public int getStudentCourses(String id) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
 
            resultSet=statement.executeQuery("select count(*) Std_Count from studentregistration where student_id='" + id + "' ");    
            
            if(resultSet.next())
            {                                    
                return resultSet.getInt(1);
            }
            return 0;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<Course> getFacultySchedule(String id) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            
            //Get the complete profile based on id
            //select id,name,type,company from profile where id='J101#' 
            resultSet=statement.executeQuery("select * from course  where instructor_info='" + id + "' ");    
            ArrayList<Course> aList = new ArrayList<>();
            //Store the profile data in arrayList
            while(resultSet.next())
            {                                    
                Course course;
                course = new Course(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getString(7),resultSet.getInt(8));
                aList.add(course);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public ArrayList<StudentAccount> getStudents(String courseId) {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
 
            resultSet=statement.executeQuery("select * from studentregistration where course_id='" + courseId + "' ");    
            ArrayList<StudentAccount> aList = new ArrayList<>();
            //Store the profile data in arrayList
            while(resultSet.next())
            {                                    
                StudentAccount student;
                student = new StudentAccount(resultSet.getString(1),resultSet.getString(2));
                aList.add(student);
            }
            return aList;
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }

}
