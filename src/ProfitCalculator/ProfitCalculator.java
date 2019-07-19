/*
 * The ProfitCalcultor program will calculate the total amount of interest a 
 * bank will earn on credit card accounts based on a specific number of months
 * and account info contained in an imported text file. 
 */

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Erik Davis
 * @version 1.0 - Java Assn 7
 */
public class ProfitCalculator {

    /**
     * The main method will display the program description to the user and call
     * the other methods to create an account object and calculate the total 
     * interest, and then display the results to the user. 
     * The user can repeat the program. 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String INPUT_DATA_FILE = "accounts.txt";
        int numMonths;
        double currentBalance;
        double annualInterestRate;
        double moPaymentPercent;
        char answer;
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.println("This program will use the input data contained in"
                + " a text file to calculate\nthe total amount of interest that"
                + " will accumulate from all accounts, over a\nspecfic number"
                + " of months.");
        System.out.println();
        System.out.println();
        
        do {
            double totalInterest = 0;
            int accountCounter = 0;
            
            numMonths = promptNumMonths();
            
            File inFile = new File(INPUT_DATA_FILE);
            Scanner fin = null; 
            
            try {
                fin = new Scanner(inFile);
            } catch (FileNotFoundException fnfe){
                System.out.println("Could not open the file " + INPUT_DATA_FILE
                        + "\nPlease check the file and try again."
                        + "\n***Exiting the program");
                System.exit(0);
            }
            
            while (fin.hasNextDouble()) {
                currentBalance = fin.nextDouble();
                annualInterestRate = fin.nextDouble();
                moPaymentPercent = fin.nextDouble();
                try {
                    CreditCardAccount newInquiry = new CreditCardAccount(currentBalance,
                        annualInterestRate, moPaymentPercent);
                    accountCounter ++;
                    totalInterest += newInquiry.calcAccountInterest(numMonths);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                    System.out.println();
                }
            }
            fin.close();
            
            
            System.out.println("Over the next " + numMonths + " months,");
            System.out.printf("    the total interest earned from " + accountCounter
                    + " accounts will be $%.2f", totalInterest);
            System.out.println();
            System.out.println();
            System.out.print("Would you like to run the program again with"
                    + " a different number of months? (y/n): ");
            answer = keyboard.next().charAt(0);
            System.out.println();
        } while (answer == 'y' || answer =='Y');
    }
    
    /**
     *The promptNumMonths methods will prompt the user for the number of months
     *s/he would like to calculate interest for.
     * 
     * @return numMonths - The number of months to calculate interest.
     */
    public static int promptNumMonths() {
        int numMonths = 0;
        
        Scanner keyboard = new Scanner(System.in);
        while (numMonths <= 0) {
            System.out.print("Please input the number of months to calculate"
                + " interest for: ");
            numMonths = keyboard.nextInt();
            System.out.println();
            if (numMonths <= 0) {
                System.out.println(numMonths + " is an invalid input. The number"
                + " of months must be greater than 0. Please try again.");
            }
        }
        return numMonths;
    }    
}
