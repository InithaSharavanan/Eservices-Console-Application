/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uhcl_eservices;

import java.util.Scanner;

/**
 *
 * @author asini
 */
public class UHCL_EServices {

    /**
     * @param args the command line arguments
     */
    
    DataStorage data=new SQL_Database();
    public static void main(String[] args) {
        String userInput="";
        Scanner  input=new Scanner(System.in);
         while(!userInput.equals("x"))
        {
            System.out.println("Please select one of the following options:");
            System.out.println("1 : Student Sign In");
            System.out.println("2: Faculty Sign In");
            System.out.println("x : Exit");
            // get  the input from the user
            userInput=input.nextLine();
            String id,password;
            if(userInput.equals("1")){
               System.out.println("Please enter your Student ID");
               id=input.nextLine();
               System.out.println("Please enter your password");
               password=input.nextLine();
               new StudentAccount(id, password).SignIn();
            }
            else if(userInput.equals("2")){
               System.out.println("Please enter your Faculty ID");
               id=input.nextLine();
               System.out.println("Please enter your password");
               password=input.nextLine();
               new FacultyAccount(id, password).SignIn();
            }
        }
    }
    
    
}
