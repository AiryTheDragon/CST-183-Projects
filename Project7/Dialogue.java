/******************************************************************************************
 * This class contains methods that do error checking to get valid input from dialogue boxes
 *
 * It handles requests for char, int, double and String
 * The program will reask a question for input if the following things happen:
 *      the user enters the wrong type of data,
 *      the user enters no data
 *      the user cancels the dialogue box
 *
 * @author  Michael Clinesmith
 ******************************************************************************************/

import javax.swing.JOptionPane;

public class Dialogue
{
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
    /**
     *  inputString() gets a String of input from a user using dialog box
     *  This method validates the input to prevent runtime errors
     *
     * @param outputString  The string to be displayed to the user to get the required String
     * @return  a String entered by the user
     */
    public static String inputString (String outputString)
    {
        String input;
        String messageString;
        String inputStr = "";
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
                inputStr = input;        // gives inputStr the same address as input, which is okay
                isValid = true;
            }

        }
        return inputStr;
    }

}
