import javax.swing.JOptionPane;

/************************************************************************************************
 *  This class demonstrates the functionality of the Message class
 *  by requesting the user to enter a message to encode
 *
 *  It does allow the user to enter digits in a message, but does not encode them.
 *  Some dialogue boxes received seperate functions to shorten the main function code
 *
 *  CST 183 Programming Assignment 5
 *  @author Michael Clinesmith
 ***********************************************************************************************/

public class MessageTest
{

    public static void main(String[] args)
    {
        Message messageObject;
        String inputString, outputString, keyWord, messageText;
        boolean isValid, anotherEncryption = true;
        char code;

        printOpeningMessage();              // displays opening message

        while (anotherEncryption)           // loops while user wants to do another encryption
        {

            printFormatMessage();           // displays formatting message
            inputString = getMessage();     // gets message from user

            isValid = Message.createMessageIsValid(inputString);    // checks if message is valid

            while (!isValid)                                        // repeat if problem with input
            {
                outputString = "There was a problem with your message, please try again.";
                JOptionPane.showMessageDialog(null, outputString);

                printFormatMessage();
                inputString = getMessage();

                isValid = Message.createMessageIsValid(inputString);
            }

            messageObject = Message.createMessage(inputString);

            keyWord = getKeyword();                                 // gets keyword from user

            isValid = Message.isValidKey(keyWord);                  // checks if keyword is valid
            while (!isValid)                                        // repeat if problem with input
            {
                outputString = "There was a problem with your keyword, please try again.";
                JOptionPane.showMessageDialog(null, outputString);

                keyWord = getKeyword();
                isValid = Message.isValidKey(keyWord);
            }

            messageText = messageObject.getMessage();

            messageObject.encryptMessage(keyWord);

            printEncryptionMessage(messageText, keyWord, messageObject);    // displays information regarding the message

            anotherEncryption = askIfAnotherEncryption();           // asks user if another encryption wanted
        }

        printClosingMessage();                                      // displays ending message

    }

    /**
     *  This method displays an opening message for the user regarding the program
     */
    public static void printOpeningMessage()
    {
        String outputString;

        outputString = "Welcome to the Message Encryption Program!\n\n" +
                "This program allows you to enter a priority code and a message,\n" +
                "then will encrypt it based on the key you provide.\n\n" +
                "Program designed by Michael Clinesmith";
        JOptionPane.showMessageDialog(null, outputString);
    }

    /**
     * This method displays the codes and format the message needs to be in
     */
    public static void printFormatMessage()
    {
        String outputString;

        outputString = "You will enter a code then a message in the form of code,message\n" +
                "The possible message codes are:\n" +
                "Z - FLASH\n" +
                "O - IMMEDIATE\n" +
                "P - PRIORITY\n" +
                "R - ROUTINE\n\n" +
                "One example is given below:\n\n" +
                "P,Delta College is closed.";

        JOptionPane.showMessageDialog(null, outputString);
    }

    /**
     * This method requests the user for a message to encode
     * @return  String representing the message (including the priority code)
     */
    public static String getMessage()
    {
        String outputString, inputString;

        outputString = "Please enter your code, a comma, then your message:\n" +
                "Codes: Z, O, P or R";

        inputString = JOptionPane.showInputDialog(outputString);

        if (inputString==null)              // catch if user cancelled dialogue box
        {
            inputString = "";
        }

        return inputString;
    }

    /**
     * This method requests the user for a keyword to encode a message
     * @return  String representing the keyword
     */
    public static String getKeyword()
    {
        String outputString, inputString;

        outputString = "Please enter a keyword consisting of all capital letters, at least four letters in length:";

        inputString = JOptionPane.showInputDialog(outputString);

        if (inputString==null)              // catch if user cancelled dialogue box
        {
            inputString = "";
        }

        return inputString;
    }

    /**
     * This method displays information regarding the message that was encrypted
     * @param messageText   String for the initial message
     * @param keyWord       String the keyword used to encode the message
     * @param messageObject Message the object containing the encrypted message
     */
    public static void printEncryptionMessage(String messageText, String keyWord, Message messageObject)
    {
        String outputString;

        outputString = "Original message\n" +
                        messageText +
                        "\n-------------------------------------------------------\n" +
                        "Priority code\n" +
                        messageObject.getPriority() +
                        "\n-------------------------------------------------------\n" +
                        "Keyword\n" +
                        keyWord +
                        "\n-------------------------------------------------------\n" +
                        "Encrypted message\n" +
                        messageObject.toString();

        JOptionPane.showMessageDialog(null, outputString);
    }

    /**
     * This method requests if the user wants to do another encryption
     * @return  boolean value, true if the user entered a message beginning with 'Y' or 'y', false otherwise
     */
    public static boolean askIfAnotherEncryption()
    {
        String outputString, inputString;
        boolean isYes = false;

        outputString = "Do you want to do another encryption? Y/N";

        inputString = JOptionPane.showInputDialog(outputString);

        if (inputString != null && inputString.length()>0)          // catch if user cancelled or did not submit anything
        {
            if(inputString.charAt(0) == 'y' || inputString.charAt(0) == 'Y')
            {
                isYes = true;
            }
        }
        return isYes;
    }

    /**
     * This method displays an ending message
     */
    public static void printClosingMessage()
    {
        String outputString;
        outputString ="Thank you for using the Message Encryption Program!\n";
        JOptionPane.showMessageDialog(null, outputString);
    }
}
