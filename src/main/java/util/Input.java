package util;

import model.InputCheck;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    public static Scanner scanner = new Scanner(System.in);

    public static String inputString() {
        return scanner.nextLine();
    }

    public static int inputInt() {return inputInt(false, new InputCheck() {
        @Override
        public boolean check(int value) {
            return true;
        }
    });}
    public static int inputInt(InputCheck additionalCheck) {return inputInt(false, additionalCheck);}
    public static int inputInt(boolean positiveOnly, InputCheck additionalCheck) {
        int intvalue = 0;

        boolean isValid = false;
        while(!isValid) {
            try {
                intvalue = scanner.nextInt();
                scanner.nextLine();
                if (!positiveOnly) isValid = true;
                else {
                    if (intvalue >= 0) isValid = true;
                    else System.out.println("Input can't be a negative number");
                }

                if (isValid) isValid = additionalCheck.check(intvalue);
            }catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Input must be a number!");
            }
        }

        return intvalue;
    }
}
