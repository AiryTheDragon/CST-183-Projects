/****************************************************************************************
 * This class implements a message that allows for setting a message and priority level,
 * and encryption of the message given a particular key
 *
 * This class gives the user multiple ways to input messages into the class and has some
 * extra functions to increase usability.
 *
 * It also contains some static functions that an outside class can call
 *
 *
 * CST 183 Programming Assignment 5
 * @author Michael Clinesmith
 *
 ****************************************************************************************/

public class Message
{
    //  class fields
    private String message;
    private char priority;


    /**
     * Constructor with no arguments
     */
    public Message()
    {
        priority = 'R';
        message = " ";
    }

    /**
     * Constructor with message string given
     * @param msg   String containing the message
     */

    public Message(String msg)
    {
        priority = 'R';
        message = msg;
    }

    /**
     * Constructor with message string a priority code given
     * @param msg   String containing the message
     * @param code  char containing the priority code
     */

    public Message(String msg, char code)
    {
        code = Character.toUpperCase(code);         // converts code to upper case if not already

        priority = code;
        message = msg;
    }

    /**
     * Mutator method to set priority level of message
     * @param priority  char to set priority level
     */
    public void setPriority(char priority)
    {
        this.priority = priority;
    }

    /**
     * Mutator method to set the message
     * @param message   String containing the message
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * Accessor method to get priority level
     * @return  char containing priority level
     */
    public char getPriority()
    {
        return priority;
    }

    /**
     * Accessor method to get message
     * @return  String containing message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Method to get the string representation of the priority level
     * @return  String listing priority level
     */
    public String getPriorityString()
    {
        String priorStr;

        switch (priority)
        {
            case 'Z':
                priorStr = "FLASH";
                break;
            case 'O':
                priorStr = "IMMEDIATE";
                break;
            case 'P':
                priorStr = "PRIORITY";
                break;
            case 'R':
                priorStr = "ROUTINE";
                break;
            default:
                priorStr = "INVALID CODE";
        }
        return priorStr;

    }

    /**
     * Static method to check if a key is all capital letters and at least 4 characters
     * @param key   String the key to check if a valid key
     * @return  boolean value, true if key it is a valid key, false if not
     */
    public static boolean isValidKey(String key)
    {
        boolean valid = true;

        if (key.length() < 4 )
        {
            valid = false;
        }

        for (int i = 0; i<key.length() && valid; i++)     // end for loop if invalid character found
        {
            // if character at position i is not a capital letter -> not valid
            if (!Character.isUpperCase(key.charAt(i)))
            {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Static method to return the key to partially decrypt a message
     * @param key   String that is a key to encrypt a message
     * @return  String that is a key to decrypt the message
     */
    public static String antiKey(String key)
    {
        String str = "";
        int code = 0;

        if(isValidKey(key))
        {
            StringBuilder antikey = new StringBuilder(key);
            for (int i=0; i<antikey.length(); i++)
            {
                code = 26 - letterToInt(antikey.charAt(i));                         // find value to add to 26
                code = code % 26;                                                   // wrap around if necessary for 'A'
                antikey.setCharAt(i, intToChar(code));                                  // set character
            }

            str = antikey.toString();
        }

        return str;
    }

    /**
     * Static method to check if string message is formatted properly with a
     * character code, a comma, then a message
     * so it can be converted into a Message class object
     * @param str   String that may be used to create a Message object
     * @return  boolean value, true if it is formatted properly, false if not
     */
    public static boolean createMessageIsValid(String str)
    {
        boolean valid = true;
        char firstChar = 'R';
        int anyCommas = 0;

        if (str.length()<3)                 // if less than three characters, message not valid
        {
            valid = false;
        }
        else
        {
            firstChar = str.charAt(0);
                                            // first character must be a valid code
            if (firstChar != 'R' && firstChar != 'P' && firstChar != 'O' && firstChar != 'Z')
            {
                valid = false;
            }
            else if (str.charAt(1) != ',') // second character must be a comma
            {
                valid = false;
            }

        }
        return valid;

    }

    /**
     * Static method to create a Message object if formatted properly with a
     * character code, a comma, then a message
     * If not properly formatted, an empty default Message object is created
     *
     * @param str   String to create a Message object
     * @return  Message object storing a priority code and message
     */
    public static Message createMessage(String str)
    {
        Message msg = new Message();    // default object
        char prior;
        String strMsg;

        if (createMessageIsValid(str))  // if valid, set Message object
        {
            prior = str.charAt(0);
            strMsg = str.substring(2);
            msg.setPriority(prior);
            msg.setMessage(strMsg);
        }

        return msg;
    }

    /**
     * Method to encrypt a message if the given key is valid
     * @param key   String used to encrypt a message
     * @return      boolean value, true if message was encrypted, false if not
     */
    public boolean encryptMessage(String key)
    {
        boolean valid = true;
        if (isValidKey(key))
        {
            formatMessage();            // changes to uppercase and removes whitespace and special characters
            encrypt(key);               // encrypts message based on key
        }
        else
        {
            valid = false;              // do nothing if not valid key but return false
        }

        return valid;
    }

    /**
     * Method to check if the message has already been formatted to remove punctuation and white space
     * @return  boolean value, true if the message has had punctuation and white space removed, false if not
     */
    public boolean isFormatted()
    {
        boolean valid = true;

        for (int i=0; i<message.length() && valid; i++)     // end for loop if invalid character found
        {
            // if character at position i is not (a letter or digit) or is lower case -> not formatted
            if (!Character.isLetterOrDigit(message.charAt(i)) || Character.isLowerCase(message.charAt(i)))
            {
                valid = false;
            }
        }

        return false;
    }

    /**
     * Method to check if the priority code is valid
     * @return  boolean value, true if priority code is valid, false if not
     */
    public boolean isValidCode()
    {
        boolean valid = false;
        if (priority == 'R' || priority == 'P' || priority == 'O' || priority == 'Z')
        {
            valid = true;
        }

        return valid;
    }

    /**
     * Method to convert a message (including a priority code) to a string
     * @return  String including a priority string then the message
     */
    public String toString()
    {
        return getPriorityString() + "\n" + message;
    }

    /**
     * Private method to convert the value stored in message to uppercase without spaces or punctuation
     * This method uses the StringBuilder class to modify the string
     */
    private void formatMessage()
    {
        if (!isFormatted())
        {
            StringBuilder msg = new StringBuilder(message.toUpperCase());
            int i=0;

            while (i<msg.length())
            {
                // check char at position i, if not character or digit, delete it, otherwise increase i
                if (Character.isLetterOrDigit(msg.charAt(i)))
                {
                    i++;
                }
                else
                {
                    msg.deleteCharAt(i);
                }
            }

            message = msg.toString();           // set message to modified msg
        }

    }

    /**
     * Private method that encrypts the value stored in message based on the given key
     * This method uses the StringBuilder class to modify a string that is being encrypted
     * The key is modified to repeat so it has the same number of letters as the message
     * Then the letters are "added" to encrypt the message
     *
     * @param key String that is to be used to encrypt the message
     */
    private void encrypt(String key)
    {
        StringBuilder msg = new StringBuilder(message);
        StringBuilder keymsg = new StringBuilder("");
        int code=0;                                         // used to add letters

        // create keyword with length of message
        for (int i=0; i<msg.length()/key.length(); i++)    // copy msg.length()/key.length() copies of key
        {
            keymsg.append(key);
        }
        if (msg.length() % key.length() !=0)               // if remander, and enough characters from key to keymsg
        {
            keymsg.append(key.substring(0,msg.length()%key.length()));
        }

        // encrypt message, one character at a time

        for (int i=0; i<msg.length(); i++)
        {
            if (Character.isLetter(msg.charAt(i)) )         // only encrypt if character is a capital letter
            {
                code = letterToInt(msg.charAt(i)) + letterToInt(keymsg.charAt(i));  // add two letters
                code = code % 26;                                                   // wrap around if necessary
                msg.setCharAt(i, intToChar(code));                                  // set character
            }
        }

        message = msg.toString();                                               // store encrypted message

    }

    /**
     * Private static method that changes a capital letter to a number (A-0, B-1, ..., Z-25)
     * @param chr   char a character
     * @return      int a number representing the character
     */
    private static int letterToInt(char chr)
    {
        return (int) chr - (int) 'A';
    }

    /**
     * Private static method that changes a number to a capital letter (0-A, 1-B, ..., 25-Z)
     * @param num   int a number
     * @return      char a capital letter representing the number
     */
    private static char intToChar(int num)
    {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return str.charAt(num);
    }
}
