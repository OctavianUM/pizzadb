package com.dbproject.cli;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.domain.Customer;

public class CustomerCLI {
    public static void customerAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\nDo you already have an existing account (Y/N)?");
        String in = scanner.next().toLowerCase();
        switch (in) {
            case "y":
                login(scanner);
                break;
            case "n":
                create(scanner);
                login(scanner);
                break;
        
            default:
                break;
        }
    }

    private static void login(Scanner scanner){

        boolean successLogin = false;

        while (!successLogin) {
            System.out.println("\n\nProvide your login information:");
            System.out.print("email: ");
            String email = scanner.next();
    
            System.out.print("password: ");
            String pass = scanner.next();
    
            successLogin = new CustomerDAO().validateLogin(email, pass);
        }
        System.out.println("Succesfull login");
    }

    private static void create(Scanner scanner){
        System.out.println("\n\nCreate a new account:");

        Customer c = new Customer();
        System.out.print("first name: ");
        c.setFirstName(scanner.next());

        System.out.print("last name: ");
        c.setLastName(scanner.next());

        getGender(scanner);

        System.out.print("phone number : ");
        c.setPhone( scanner.nextInt());

        System.out.print("birthdate (YYYY-MM-DD):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        c.setBirthDate( Date.valueOf(LocalDate.parse(scanner.next(), formatter)));

        System.out.print("email: ");
        c.setEmail(scanner.next());

        c.setAdressId(1);

        System.out.print("password: ");
        c.setPassword(scanner.next());

        new CustomerDAO().saveCustomer(c);
    }

    private static boolean validateGender(String str){
        switch (str) {
            case "M":
                return true;
            case "F":
                return true;
        
            default:
                return false;
        }
    }

    private static char getGender(Scanner scanner){

        System.out.print("gender (M/F): ");

        String genderStr = scanner.next().toUpperCase();
        while (!validateGender(genderStr)) {
            genderStr = scanner.next();
        }
        return genderStr.toCharArray()[0];

    }
}
