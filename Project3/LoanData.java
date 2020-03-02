/**************************************************************************************
 * This program takes input data from a file called "loandata.txt"
 * displays the information to the console, and accumulates the information,
 * displaying totals at the end.
 *
 * Program by Michael Clinesmith
 * CST 183 Programming Assignment 3
 **************************************************************************************/
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class LoanData
{

    public static void main(String[] args) throws IOException
    {
        final String FILE_NAME = "loandata.txt";                                        // loan data filename
        String name, creditRatingLabel;                                                 // loan data variables
        double principal, annualRate, monthlyRate, feeRate, monthlyPayment, payoff, feeDollars;
        int term, termMonths, creditRating;

        double totalPrincipal = 0, totalPayment = 0, totalPayoff = 0, totalFee = 0;     // accumulator variables
        int validRecords = 0;                                                           // counter variable
        String initialDisplay, recordDisplay, finalDisplay;                             // display variable
        File loanData;                                                                  // input file variable

        // introductory message

        JOptionPane.showMessageDialog(null, "This program processes loan data located " +
                                    "in the file loandata.txt\n" +
                                    "and displays the information and totals to the console.");

        // open file

        loanData = new File(FILE_NAME);

        if(!loanData.exists())  // file not found
        {

            JOptionPane.showMessageDialog(null, "loandata.txt does not exist for processing.\n" +
                                    "The program will now end.");
            System.exit(0);
        }

        Scanner inputFile = new Scanner(loanData);

        //  display first heading
        initialDisplay =    String.format("%-12s","Customer") + "  " +
                            String.format("%-13s","Principal") + "  " +
                            String.format("%-5s","Rate") + "  " +
                            String.format("%-5s","Years") + "  " +
                            String.format("%-10s","Payment") + "  " +
                            String.format("%-13s","Payoff") + "  " +
                            String.format("%-10s","Fee") + "  " +
                            String.format("%-15s","Credit Rating");

        System.out.println(initialDisplay);

        //  input and process data
        while(inputFile.hasNext())
        {
            name = inputFile.next();                // input next record
            principal = inputFile.nextDouble();
            term = inputFile.nextInt();
            annualRate = inputFile.nextDouble();
            creditRating = inputFile.nextInt();

            if (creditRating < 580)                 // extra token for very poor credit ratings
            {
                feeRate = inputFile.nextDouble();
            }
            else
            {
                feeRate = 0.0;
            }
            validRecords++;                         // increase valid records count

            // process record

            termMonths = term * 12;
            monthlyRate = annualRate / 1200;        // also converts to a decimal value from percentage

                                                    // monthly payment formula
            monthlyPayment = monthlyRate * principal / (1 - Math.pow(1 + monthlyRate, -termMonths));
            payoff = monthlyPayment * termMonths;
            feeDollars = feeRate / 100 * principal; // fee rate must be converted to a decimal value

            if (creditRating<300 || creditRating>850)   // display text rating based on credit range
            {
                creditRatingLabel = "Invalid Rating";
            }
            else if (creditRating<580)
            {
                creditRatingLabel = "Very Poor";
            }
            else if (creditRating<670)
            {
                creditRatingLabel = "Fair";
            }
            else if (creditRating<740)
            {
                creditRatingLabel = "Good";
            }
            else if (creditRating<800)
            {
                creditRatingLabel = "Very Good";
            }
            else
            {
                creditRatingLabel = "Exceptional";
            }

            // accumulate data
            totalPrincipal += principal;
            totalPayment += monthlyPayment;
            totalPayoff += payoff;
            totalFee += feeDollars;

            // display record
            recordDisplay =     String.format("%-12s",name) + "  " +
                                String.format("$%,12.2f", principal) + "  " +
                                String.format("%4.1f%%",annualRate) + "  " +
                                String.format("%5d", term) + "  " +
                                String.format("$%,9.2f", monthlyPayment) + "  " +
                                String.format("$%,12.2f", payoff) + "  " +
                                String.format("$%,9.2f", feeDollars) + "  " +
                                String.format("%-15s",creditRatingLabel);

            System.out.println(recordDisplay);

        }

        // display totals

            System.out.println("----------------------------------------------------------------------------------------");

        finalDisplay =      String.format("%-12s","Totals") + "  " +
                            String.format("$%,12.2f", totalPrincipal) + "  " +
                            String.format("%5s"," ") + "  " +
                            String.format("%5s", " ") + "  " +
                            String.format("$%,9.2f", totalPayment) + "  " +
                            String.format("$%,12.2f", totalPayoff) + "  " +
                            String.format("$%,9.2f", totalFee);


        System.out.println(finalDisplay);
        System.out.println(validRecords + " records processed.");

    }
}
