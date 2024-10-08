package com.dbproject.cli;

import java.util.Scanner;

import com.dbproject.dao.MenuDAO;

public class ReportCLI {

    public static void show(Scanner scanner){
        System.out.println("\n====== EARNINGS REPORT ======");
        chooseFilter(scanner);
    }


    private static void chooseFilter(Scanner scanner){

        while (true) {
            System.out.println("\nREPORT FILTER:  1. region   2. gender  3. age  4. exit");
            
            if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1: //region
                        System.out.println("which postal (int)? ");
                        MenuDAO.getTotalMenuItemsByPostal(scanner.nextInt());
                        break;

                    case 2: //gender
                        System.out.println("which gender (m/f)? ");
                        MenuDAO.getTotalMenuItemsByGender(scanner.next().charAt(0));
                        break;

                    case 3: //age
                        System.out.println("which age (int)? ");
                        MenuDAO.getTotalMenuItemsByAge(scanner.nextInt());
                        break;

                    case 4: //exit
                        return;
                    default:
                        System.out.println("Invalid input!");
                }
            }

            break;
        }

    }
}