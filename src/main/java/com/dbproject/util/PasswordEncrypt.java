package com.dbproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//TODO: rewrite a bit to obscure gpt use
public class PasswordEncrypt {

    // Method to hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            // Create an instance of the SHA-256 MessageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Perform the hash calculation
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert byte array into a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error: SHA-256 algorithm not found.");
        }
    }

    // Method to compare two hashed passwords
    public static boolean checkPassword(String inputPassword, String storedHashedPassword) {
        // Hash the input password
        String hashedInput = hashPassword(inputPassword);

        // Compare the hashed input with the stored hashed password
        return hashedInput.equals(storedHashedPassword);
    }

    
    public static void main(String[] args) {
        // Example usage

        // Step 1: Hash the password when storing it
        String password = "mySecretPassword";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Step 2: Validate user login by checking input password
        String inputPassword = "mySecretPassword"; // User enters this during login
        boolean isValid = checkPassword(inputPassword, hashedPassword);

        if (isValid) {
            System.out.println("Password is correct!");
        } else {
            System.out.println("Invalid password.");
        }
    }
}