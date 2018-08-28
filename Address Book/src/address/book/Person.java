/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package address.book;

import java.io.Serializable;

/**
 *
 * @author carlosturcios
 */
public class Person implements Serializable {

    String firstName;
    String lastName;
    String phoneNumber;
    String emailAddress;

    Person(String firstName, String lastName,
            String phoneNumber, String emailAddress) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getfirstName() {

        return firstName;
    }

    public String getlastName() {

        return lastName;
    }

    public String getphoneNumber() {

        return phoneNumber;
    }

    public String getemailAddress() {

        return emailAddress;
    }

    @Override
    public String toString() {
        return ("\n" 
                + "------------------------ \n\n"
                + "First Name: " + this.getfirstName() + "\n" 
                + "Last Name: " + this.getlastName() + "\n" 
                + "Phone Number: " + this.getphoneNumber() + "\n" 
                + "Email Address: " + this.getemailAddress() + "\n\n");
                // + "------------------------");
    }
}
