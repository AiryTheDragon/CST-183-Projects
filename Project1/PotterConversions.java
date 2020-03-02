/**************************************************************************************
 * This program inputs information regarding a deal of hogsheads of ale for dollars
 * then displays this information using hogsheads, gallons, dollars, euros, pounds,
 * galleons, sickles, and knuts
 *
 * Program by Michael Clinesmith
 * CST 183 Programming Assignment 1
 **************************************************************************************/

import javax.swing.JOptionPane;

public class PotterConversions {

    public static void main(String args[])
    {
        // current exchange rate variable declarations
        final int       GALLEONS_TO_SICKLES = 17, SICKLES_TO_KNUTS = 29;
        final double    HOGSHEADS_TO_GALLONS = 54.0, DOLLARS_TO_EUROS = .86,
                        DOLLARS_TO_POUNDS = .76, GALLEONS_TO_DOLLARS = 25.50;

        // volume and currency variable declarations
        int hogsheads, galleons, sickles, knuts;
        double gallons, dollars, euros, pounds, galleonsDouble;

        // other variable declarations
        String inputString;      // used to input variables
        String formatString;     // used to format output strings

        // Display introductory messages
        JOptionPane.showMessageDialog( null, "Harry Potter money conversion program");
        JOptionPane.showMessageDialog( null,
                                "This program will ask for input regarding a deal made\n" +
                                         "by a pub owner exchanging dollars for ale and format\n" +
                                         "the exchange in various volumes and currencies.");

        // Request hogsheads
        inputString = JOptionPane.showInputDialog( "Enter the number of hogsheads of ale purchased (an integer)");
        hogsheads = Integer.parseInt(inputString);    // this converts the input to an int

        // Request dollars
        inputString = JOptionPane.showInputDialog( "Enter the purchase price of the ale in dollars (a double)");
        dollars = Double.parseDouble(inputString);    // this converts the input to a double

        // Display deal
        formatString =  "The deal is:\n%,d hogsheads of ale for\n%,.2f dollars.";
        JOptionPane.showMessageDialog( null, String.format( formatString, hogsheads, dollars));

        // Display conversion rates
        formatString =  "Current conversion rates:\n" +
                        "1 hogshead = %,.2f gallons\n" +
                        "1 dollar = %,.2f euros\n" +
                        "1 dollar = %,.2f British pounds\n" +
                        "1 galleon = %,.2f dollars\n" +
                        "1 galleon = %,d sickles\n" +
                        "1 sickle = %,d knuts";
        JOptionPane.showMessageDialog( null,
                String.format( formatString, HOGSHEADS_TO_GALLONS, DOLLARS_TO_EUROS, DOLLARS_TO_POUNDS,
                                            GALLEONS_TO_DOLLARS, GALLEONS_TO_SICKLES, SICKLES_TO_KNUTS));


        // calculate conversions
        gallons = hogsheads * HOGSHEADS_TO_GALLONS;
        euros = dollars * DOLLARS_TO_EUROS;
        pounds = dollars * DOLLARS_TO_POUNDS;

        galleonsDouble = dollars / GALLEONS_TO_DOLLARS; // a temporary variable for wizarding currency conversion
        galleons = (int)galleonsDouble;                 // store integer number of galleons
        galleonsDouble -= galleons;                     // fractional part of galleons
        galleonsDouble *= GALLEONS_TO_SICKLES;          // number of sickles (double)
        sickles = (int)galleonsDouble;                  // store integer number of sickles
        galleonsDouble -= sickles;                      // fractional part of sickles
        galleonsDouble *= SICKLES_TO_KNUTS;             // number of knuts
        knuts = (int)(galleonsDouble);                  // integer number of knuts (truncated)

        // display volumes
        formatString = "The total volume of ale purchased is:\n%,d hogsheads\n%,.1f gallons";
        JOptionPane.showMessageDialog( null, String.format( formatString, hogsheads, gallons));

        // display monetary values
        formatString =  "The total value of ale purchased is:\n" +
                        "$%,.2f\n" +
                        "%,.2f euros\n" +
                        "%,.2f British pounds\n" +
                        "%,d galleons, %d sickles, and %d knuts";
        JOptionPane.showMessageDialog( null,
                String.format( formatString, dollars, euros, pounds, galleons, sickles, knuts ));

        // display ending message
        JOptionPane.showMessageDialog( null,
                "Thank you for using the Harry Potter money conversion program!");

    }



}
