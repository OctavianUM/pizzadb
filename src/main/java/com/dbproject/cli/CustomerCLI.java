package com.dbproject.cli;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.domain.Customer;

public class CustomerCLI {
    public static int customerAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDo you already have an existing account (Y/N)? ");
        String in = scanner.next().toLowerCase();
        switch (in) {
            case "y":
                return login(scanner);
            case "n":
                create(scanner);
                return login(scanner);
        
            default:
                scanner.close();
                return Integer.MIN_VALUE;
        }
    }

    private static int login(Scanner scanner){

        boolean successLogin = false;

        while (!successLogin) {
            System.out.println("\n======LOGIN MENU======");
            System.out.println("Provide your login information:");
            System.out.print("email: ");
            String email = scanner.next();
    
            System.out.print("password: ");
            String pass = scanner.next();

            int id = new CustomerDAO().validateLogin(email, pass);
            if(id > 0){
                System.out.println("Successfully login");
                return id;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static void create(Scanner scanner){
        System.out.println("\nCreate a new account:");

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


// SELECT 
//     mi.menuItemId,
//     mi.name AS menuItemName,
//     GROUP_CONCAT(i.name SEPARATOR ', ') AS ingredients,
//     SUM(i.price * mii.amount) AS totalPrice,
//     CASE
//         WHEN SUM(CASE WHEN i.dietary = "Vegetarian" THEN 0 ELSE 1 END) > 0 THEN 'Vegan'
//         ELSE 'Non-vegan'
//     END AS Dietary
// FROM 
//     Menu m
// JOIN 
//     MenuItem mi ON m.menuItemId = mi.menuItemId
// JOIN 
//     Recipe mii ON mi.menuItemId = mii.menuItemId
// JOIN 
//     Ingredient i ON mii.ingredientId = i.ingredientId
// WHERE 
//     m.menuID = 0 
// GROUP BY 
//     mi.menuItemId, mi.name, mi.type, mi.description;