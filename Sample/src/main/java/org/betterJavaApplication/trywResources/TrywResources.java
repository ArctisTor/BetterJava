package org.betterJavaApplication.trywResources;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class TrywResources {

    public static void main(String[] args) {
        String userInput;

        try (Scanner scan = new Scanner(System.in)) {
            do {
                System.out.println("1 divide by: ");
                userInput = scan.nextLine();
                if (StringUtils.isNumeric(userInput)) {
                    System.out.println("Answer: " + (1.0 / Integer.parseInt(userInput)));
                } else if (userInput.equals("q")) {
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            } while(!userInput.equals("q"));
        }

    }
}
