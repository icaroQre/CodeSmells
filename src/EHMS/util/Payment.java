package EHMS.util;

import java.util.Scanner;

public class Payment {
    private Scanner input = new Scanner(System.in);

    public String CreditCardDetails(int fee) {
        String cardHolderName = getCardHolderName();
        String cardNumber = getCardNumber();
        String expiryDate = getExpiryDate();
        int cvc = getCvc();
        return confirmPayment();
    }

    private String getCardHolderName() {
        System.out.println("\t\tCARD-HOLDER Name: ");
        return input.nextLine();
    }

    private String getCardNumber() {
        System.out.println("\t\tCARD-NUMBER : ");
        return input.nextLine();
    }

    private String getExpiryDate() {
        System.out.println("\t\tEXPIRY DATE : ");
        return input.nextLine();
    }

    private int getCvc() {
        System.out.println("\t\tCVC number: ");
        return input.nextInt();
    }

    private String confirmPayment() {
        System.out.println("Please Enter 1 to confirm Payment---");
        int x = input.nextInt();
        if (x == 1) {
            System.out.println("Your Payment is confirmed");
            return "Payed";
        } else {
            System.out.println("Your Appointment is cancelled");
            return "NotPayed";
        }
    }
}
