package loginRegister;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.valueOf;
import static loginRegister.FileServiceRegister.MD5;
import static loginRegister.UserService.*;

public class RegisterLoginMain {

    public static void main(String[] args) {
        FileServiceRegister.createFolder("files");
        try {
            FileServiceRegister.createFile("files", "register.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }



        Scanner s = new Scanner(System.in);

        boolean isMenuActive = true;
        while (isMenuActive) {
            System.out.println("----------------------------");
            System.out.println("Hi choose command number");
            System.out.println("Enter command number  | 1.Registration| |2.Login|  |0.exit|");


            int command = s.nextInt();
            s.nextLine();
            switch (command) {
                case 1:
                    registrationCommand(s);
                    break;
                case 2:
                    loginCommand(s);


                    break;

                case 0:
                    isMenuActive = false;
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("invalid number");
            }
        }
    }

    private static void loginCommand(Scanner s) {
        System.out.println("username:");
        String username = s.nextLine();
        System.out.println("password:");
        String password = s.next();
        if(login(username,password)){
            System.out.println(" Success Login bebe");
        }else System.out.println("lets try again");

    }




    private static void registrationCommand(Scanner s) {

        String fullName;
        while (true) {
            System.out.println("full name:name surname");
            fullName = s.nextLine();
            if (!validateFullName(fullName)) {
                System.out.println("invalid full name ");

            } else break;
        }

        String username;
        while (true) {
            System.out.println("username:");
             username = s.nextLine();
            if (!validateUsername(username)) {
                System.out.println("invalid");
            }else break;
        }

        String email;
        while (true) {
            System.out.println("email");
             email = s.next();
            if (!validateEmail(email)) {
                System.out.println("invalid");
            }else break;
        }
        String password;
        while (true) {
            System.out.println("password:");
            password = s.next();
            if (!passwordValidation(password)) {
                System.out.println("Password length must be large form 8 have a 2 uppercase letters and 3 numbers");
            }else break;
        }

        User user =new User(fullName,username,email,MD5(password));
        FileServiceRegister.writeToFile(user);

    }



}

