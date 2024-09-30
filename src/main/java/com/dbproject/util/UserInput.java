package com.dbproject.util;

import java.util.Scanner;

public class UserInput {
    
    public int getInput(String... messages){
        
        Scanner scanner = new Scanner(System.in);

        for (String message : messages) {
            System.out.println(message);
        }
        returscanner.toString()
    }
}
