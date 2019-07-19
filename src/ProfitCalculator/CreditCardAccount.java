/*
 * The CreditCardAccount Class compliments the ProfitCalculator program and will
 * do a variety of calculations for a credit card account.
 */

/**
 * CreditCardAccount Class - Ability to create different objects (accounts) 
 * with unique initial balances, interest rates, and monthly payment percentage. 
 * 
 * @author Erik Davis
 * @version 1.0 CreditCardAccount class - Java Assn 7
 */
public class CreditCardAccount {
    //Data Fields
    private double currentBalance;
    private double annualInterestRate;
    private double moPaymentPercent;
    
    /**
     * Account Constructor
     * 
     * @param currentBalance - The starting balance of the account.
     * @param annualInterestRate - The accounts annual interest rate. 
     * @param moPaymentPercent - The percentage of the balance to pay each month.
     */
    public CreditCardAccount(double currentBalance, double annualInterestRate,
            double moPaymentPercent) {
        if (currentBalance <= 0) {
            throw new IllegalArgumentException ("   ***Invalid starting balance $"
                + currentBalance + ". -- Data line: " + currentBalance + " " 
                + annualInterestRate + " " + moPaymentPercent + " ignored");
        } else {
            this.currentBalance = currentBalance;
        }
        if (annualInterestRate < 3 || annualInterestRate > 25) {
            throw new IllegalArgumentException ("   ***Invalid card interest "
                + annualInterestRate + "%. -- Data line: " + currentBalance + " " + 
                + annualInterestRate + " " + moPaymentPercent + " ignored");
        } else {
            this.annualInterestRate = annualInterestRate;
        }
        if (moPaymentPercent < 0 || moPaymentPercent > 33) {
            throw new IllegalArgumentException ("   ***Invalid monthly payment"
                    + " percentage " + moPaymentPercent + "%. -- Data line: " 
                    + currentBalance + " " + annualInterestRate + " " 
                    + moPaymentPercent + " ignored");
        } else {
            this.moPaymentPercent = moPaymentPercent;
        }
    }
    
    /**
     * The calcMoMinPayment method will calculate the minimum monthly payment
     * for a credit card based on its balance.
     * 
     * @return moMinPayment - The minimum monthly payment.
     */
    public double calcMoMinPayment() {
        final int LOW_BAL_MIN = 50;
        final double HIGH_BAL_PERCENT = 0.05;
        final int LOW_BAL_LIMIT = 1000;
        double moMinPayment;
        
        if (currentBalance < LOW_BAL_LIMIT) {
            moMinPayment = LOW_BAL_MIN;
        } else {
            moMinPayment = currentBalance * HIGH_BAL_PERCENT;
        }
        
        return moMinPayment;
    }
    
    /**
     * The makePayment method will calculate the amount to be paid each month
     * based on the initial balance, the interest rate, and the monthly
     * payment percentage, and then will update the new balance. Can be used 
     * multiple times for multiple payments. 
     * 
     * @return interestCharge - the amount of interest paid for the month.
     */
    public double makePayment() {
        double interestCharge;
        double moInterestRate;
        double moPayment;
        double moPaymentDecimal;
        double moMinPayment;
        double newBalance;
        
        moInterestRate = annualInterestRate / 100 / 12;
        interestCharge = currentBalance * moInterestRate; 
        currentBalance += interestCharge;
        
        moPaymentDecimal = moPaymentPercent / 100;
        moPayment = currentBalance * moPaymentDecimal;
        moMinPayment = calcMoMinPayment();
        if (moPayment < moMinPayment) {
            moPayment = moMinPayment;
        }
        if (moPayment > currentBalance) {
            moPayment = currentBalance;
        }
        
        newBalance = currentBalance - moPayment;
        currentBalance = newBalance;
        
        return interestCharge;
    }
    
    /**
     * The calcAccountInterest method will calculate the interest paid on an 
     * account over a specific number of months. 
     * 
     * @param numMonths - The number of months to calculate interest for.
     * @return totalInterestPaid - The total interest paid.
     */
    public double calcAccountInterest(int numMonths) {
        double totalInterestPaid = 0;
                
        for (int count = 1; count <= numMonths && currentBalance > 0; count++) {
            totalInterestPaid += this.makePayment();
        }
        
        return totalInterestPaid;
    }
}
