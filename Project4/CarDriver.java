import javax.swing.JOptionPane;

/************************************************************************************************
 *  This class demonstrates the functionality of the CarRental class
 *  by allowing the user to enter information about a car rental then
 *  displaying summary information about the rental including its cost.
 *
 *  The user is prompted to input information regarding a car rental, the information
 *  is validated then a summary is displayed and the user is asked
 *  if the user wants to input another rental
 *
 *  CST 183 Programming Assignment 4
 *  @author Michael Clinesmith
 */

public class CarDriver
{
    /**
     * The main function is the driver of the program, requesting messages
     * to be displayed to the user and requesting input regarding a car rental.
     * with invalid rental input the user is requested for valid input and
     * summary information regarding valid rental input is displayed then the
     * user is requested if another rental is to be entered
     *
     * @param args String array - not used
     */
    public static void main(String[] args)
    {
        // declarations
        CarRental rental = new CarRental(); // required to initialize otherwise compiler error
        String outputString;
        char type;
        int days;
        double startMileage, endMileage;

        boolean validRental = false;        // flag for if rental data is valid
        char another;                       // flag to do another rental

        // introduction message

        outputString ="Welcome to the CarRental Program!\n\n" +
                "This program allows you to enter information regarding \n" +
                "a car rental, then will process the information and \n" +
                "produce a summary output including rental charges.\n\n" +
                "Program designed by Michael Clinesmith";
        JOptionPane.showMessageDialog(null, outputString);

        // process car rentals
        do                                  // loop allows user to enter multiple rentals, but at least once
        {
            validRental = false;            // flag to require valid data
            while (!validRental)            // loop until valid rental data
            {
                // request rental data

                outputString = "Enter the type of rental:\n" +
                        "-------------------------\n" +
                        "Budget\n" +
                        "Daily\n" +
                        "Weekly\n";
                type = inputChar(outputString);

                outputString =  "Enter the whole number of days for the rental:\n" +
                                "Rentals may be made to a maximum of 60 days.";
                days = inputInt(outputString);

                outputString = "Enter the beginning mileage:";
                startMileage = inputDouble(outputString);

                outputString = "Enter the ending mileage:";
                endMileage = inputDouble(outputString);

                rental = new CarRental(type, days, startMileage, endMileage);

                validRental = rental.isValidRental();
                if (!validRental)           // if data not valid, user needs to try again
                {
                    outputString =  "The data for the car rental is not valid.\n" +
                                    "Please try again with valid data.";
                    JOptionPane.showMessageDialog(null, outputString, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // display rental information
            outputString = rental.rentalSummaryToString();
            JOptionPane.showMessageDialog(null, outputString);

            outputString = "Do you want to process another rental? (Y/N)";
            another = inputChar(outputString);
        }
        while (another == 'Y' || another == 'y');

        // ending message

        outputString ="Thank you for using the CarRental Program!\n";
        JOptionPane.showMessageDialog(null, outputString);

    }

    /**
     *  inputChar() gets a char of input from a user using dialog box
     *  This method validates the input to prevent runtime errors
     *
     * @param outputString  The string to be displayed to the user to get the required char
     * @return  a char from the first character entered by the user
     */
    public static char inputChar (String outputString)
    {
        String input;
        String messageString;
        char inputChar = ' ';
        boolean isValid = false;

        while (!isValid)
        {

            input = JOptionPane.showInputDialog(outputString);
            if (input == null)
            {
                messageString = "Proper input not entered.\n" +
                                "Please enter input in the correct format.";

                JOptionPane.showMessageDialog(null, messageString, "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (input.length()==0)
            {
                messageString = "Proper input not entered.\n" +
                        "Please enter input in the correct format.";

                JOptionPane.showMessageDialog(null, messageString, "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                inputChar = input.charAt(0);
                isValid = true;
            }

        }
        return inputChar;
    }

    /**
     *  inputInt() gets a int of input from a user using dialog box
     *  This method validates the input to prevent runtime errors
     *
     * @param outputString  The string to be displayed to the user to get the required int
     * @return  an int entered by the user
     */
    public static int inputInt (String outputString)
    {
        String input;
        String messageString;
        int inputInt = 0;
        boolean isValid = false;

        while (!isValid)
        {

            try                     // used to catch bad input data exception
            {
                input = JOptionPane.showInputDialog(outputString);
                if (input == null)
                {
                    messageString = "Proper input not entered.\n" +
                            "Please enter input in the correct format.";

                    JOptionPane.showMessageDialog(null, messageString, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else if (input.length() == 0)
                {
                    messageString = "Proper input not entered.\n" +
                            "Please enter input in the correct format.";

                    JOptionPane.showMessageDialog(null, messageString, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else
                {
                    inputInt = Integer.parseInt(input);
                    isValid = true;
                }
            }
            catch (NumberFormatException e)     // catches exception where user inputs improperly formatted data
            {
                messageString = "Proper input not entered.\n" +
                        "Please enter input in the correct format.";

                JOptionPane.showMessageDialog(null, messageString, "ERROR",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
        return inputInt;
    }

    /**
     *  inputDouble() gets a double of input from a user using dialog box
     *  This method validates the input to prevent runtime errors
     *
     * @param outputString  The string to be displayed to the user to get the required double
     * @return  a double entered by the user
     */
    public static double inputDouble (String outputString)
    {
        String input;
        String messageString;
        double inputDouble = 0.0;
        boolean isValid = false;

        while (!isValid)
        {
            try                 // used to catch bad input data exception
            {
                input = JOptionPane.showInputDialog(outputString);
                if (input == null)
                {
                    messageString = "Proper input not entered.\n" +
                            "Please enter input in the correct format.";

                    JOptionPane.showMessageDialog(null, messageString, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else if (input.length() == 0)
                {
                    messageString = "Proper input not entered.\n" +
                            "Please enter input in the correct format.";

                    JOptionPane.showMessageDialog(null, messageString, "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else
                {
                    inputDouble = Double.parseDouble(input);
                    isValid = true;
                }
            }
            catch (NumberFormatException e)     // catches exception where user inputs improperly formatted data
            {
                messageString = "Proper input not entered.\n" +
                        "Please enter input in the correct format.";

                JOptionPane.showMessageDialog(null, messageString, "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return inputDouble;
    }
}
