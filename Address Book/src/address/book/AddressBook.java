/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package address.book;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @author carlosturcios
 * 
 * With this program the user is able to create an E-Mail address book 
 * They are able to edit an person, delete and view them all at once 
 * or one by one
 * 
 */
public class AddressBook {

    static String fileName;
    static String optionString;
    static int option;
    static String firstName, lastName, phoneNumber, emailAddress;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        boolean optionBool = true;
        Scanner scan = new Scanner(System.in);

        // Will loop until the user wants to quit the program
        // Or if the user is done creating a book they are able to create a new one
        
        while (optionBool) {

            
            System.out.println(
                    "Welcome to Adress Book plese select one of the fallowing options: \n\n"
                    + "\t 1. Create new book file \n"
                    + "\t 2. Exit Program \n\n"
                    + "Please enter an option: "
            );

            // Will get input as a string 
            // willcheck if the input is just strings 
            // else it will tell user to use numbers only 
            optionString = scan.next();
            if (optionString.matches("\\d+")) {
                
                option = Integer.parseInt(optionString);
                
            } else {
                System.out.println("Please, only numbers");
            }

            
            if (option == 1) {
                
                // will ask the user a name for the file they want to save the book 
                System.out.println("What name would you like to give the file: ");
                fileName = scan.next();

                // ArrayList<Person> book = new ArrayList<Person>();
                HashMap<String, Object> book = new HashMap();

                while (true) {

                    System.out.println(""
                            + "\t 1. View Everyone\n"
                            + "\t 2. Add a Person\n"
                            + "\t 3. Search Person\n"
                            + "\t 4. Update Person\n"
                            + "\t 5. Delete Person\n"
                            + "\t 6. Save and Exit \n\n"
                            + "Please enter an option: "
                    );

                    // Will get input as a string 
                    // willcheck if the input is just strings 
                    // else it will tell user to use numbers only 
                    // this method it can be implemeneted in fucntion
                    optionString = scan.next();
                    if (optionString.matches("\\d+")) {

                        option = Integer.parseInt(optionString);

                    } else {
                        System.out.println("Please, only numbers");
                    }

                    
                    
                    if (option == 1) {

                        // will loop for all the keys of hashmap and print 
                        // the values of every key
                        // this time being each person
                        for (Object key : book.keySet()) {
                            
                            System.out.println(book.get(key));
                        }

                    } else if (option == 2) {
                        
                        // will ask the user for name, last name, phone number 
                        // and email
                        System.out.println("First Name: ");
                        firstName = scan.next();
                        System.out.println("Last Name: ");
                        lastName = scan.next();
                        System.out.println("Phone Number: ");
                        phoneNumber = scan.next();
                        // will convert get rid of all charcater that are not numbers 
                        // in case a phone number like 
                        // (111)111-1111 is given the value given to 
                        // phoneNumber is 1111111111
                        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
                        //System.out.println(phoneNumber);
                        System.out.println("Email Address: ");
                        emailAddress = scan.next();

                        // adds the person to the map and uses the phonenumber 
                        // as key
                        book.put(phoneNumber, (new Person(firstName, lastName, phoneNumber, emailAddress)));

                        System.out.println("\n");
                        // Person person1 = new Person(firstName, lastName, phoneNumberInt, emailAddress);

                    } else if (option == 3) {

                        // gets key from user this time being the phone number 
                        // to dislay person 
                        System.out.println("Phone number of person: ");
                        phoneNumber = scan.next();
                        System.out.println(book.get(phoneNumber));

                    } else if (option == 4) {
                        
                        
                        // gets key from user this time being the phone number 
                        // to ask the user for the new first name, last name and email
                        System.out.println("Phone number of person you want to update: ");
                        phoneNumber = scan.next();

                        System.out.println("New First Name: ");
                        firstName = scan.next();
                        System.out.println("New Last Name: ");
                        lastName = scan.next();
                        System.out.println("New Email Address: ");
                        emailAddress = scan.next();

                        // With the new information we replace 
                        // the person with the old information and replace it 
                        // witht he person with the updated info
                        book.put(phoneNumber, (new Person(firstName, lastName, phoneNumber, emailAddress)));

                        // String name = book.get(phoneNumber)Person.getfirstName());
                        
                        // Tells the user what the new information given
                        System.out.println("\n"
                                + "Updating... \n"
                                + "Information has been updated. Here is the new infromation: ");
                        System.out.println(book.get(phoneNumber));

                        //System.out.println(book.get(phoneNumber)); 
                    } else if (option == 5) {

                        
                        // gets key from user this time being the phone number 
                        // with the key then we proceed to remove the person from the map
                        System.out.println("Phone number of person you wish to remove: ");
                        phoneNumber = scan.next();
                        book.remove(phoneNumber);
                        System.out.println("\n"
                                + "Updating... \n"
                                + "Person has been deleted.");
                        // System.out.println(book.get(phoneNumber)); 

                    } else if (option == 6) {
                        
                        // Creates file with the provided name and adds the .txt extension
                        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName + ".txt"));

                        // will loop book getting each key and value
                        // then sends it to writer
                        book.forEach((key, value) -> {
                            try {
                                // writes to file it's key and value 
                                bw.write(key + ":" + value);
                            } catch (IOException ex) {
                                Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        // Closes writer
                        bw.flush();
                        bw.close();

                        // book.forEach((key, value) -> System.out.println(value));
                        System.out.println("Saving Book... \n"
                                + "Good Bye!");
                        break;

                       
                    } 
                    // tells the user to choose from 1-6 
                    else {

                        System.out.println("\n"
                                + "Plese select one of the the options from 1-6. "
                                + "Try Again... \n\n");
                    }
                }
                
            } else if (option == 2) {
                System.out.println("Exiting Program... \n"
                        + "Good Bye!");
                // breaks the loop finishing the program
                break;
            }
            
            // If the user uses more than the given options it tells 
            // the user to use try again 
            
            else{
                
                System.out.println("\n"
                        + "Plese select one of the the two options. "
                        + "Try Again... \n\n");
            }

        }
    }
    
   //  public String CheckInt(){ }

}
