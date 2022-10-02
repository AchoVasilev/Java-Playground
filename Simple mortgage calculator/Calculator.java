import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte monthsInYear = 12;
        final byte percent = 100;

        var scanner = new Scanner(System.in);
        System.out.print("Principal: ");
        var principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        var annualInterestRate = scanner.nextFloat();

        var monthlyInterestRate = annualInterestRate / percent / monthsInYear;

        System.out.print("Period (Years): ");
        var years = scanner.nextByte();

        var numberOfPayments = years * monthsInYear;

        var mortgage = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        var mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortgage: " + mortgageFormatted);
    }
}
