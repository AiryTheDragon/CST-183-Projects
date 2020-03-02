/****************************************************************************************
 * This class implements un UpperAirData class that stores temperature data for pressure
 * levels and processes them to calculate index values to determine the possibility
 * of severe weather
 *
 * The class has some error checking features to check if the data is valid and give some
 * useful error message is it is not
 *
 * The class has a toString() method to generate a report that can be used to display
 * the weather risk assessment
 *
 * CST 183 Programming Assignment 6
 * @author Michael Clinesmith
 *
 ****************************************************************************************/

public class UpperAirData
{
    private int T850, T700, T500, Td850, Td700;         // temperature fields at various pressures

    /**
     * Constructor with no parameters
     */
    public UpperAirData()
    {
        T850 = 0;
        T700 = 0;
        T500 = 0;
        Td850 = 0;
        Td700 = 0;
    }

    /**
     * Constructor when given integer parameters
     * temperatures are to be in Celsius
     * @param v850 int containing temperature at 850 mb
     * @param v700 int containing temperature at 700 mb
     * @param v500 int containing temperature at 500 mb
     * @param vd850 int containing dew point at 850 mb
     * @param vd700 int containing dew point at 700 mb
     */
    public UpperAirData(int v850, int v700, int v500, int vd850, int vd700)
    {
        T850 = v850;
        T700 = v700;
        T500 = v500;
        Td850 = vd850;
        Td700 = vd700;
    }

    /**
     * Constructor when given integer parameters
     * temperatures are to be in Celsius
     * @param s850 String containing temperature at 850 mb
     * @param s700 String containing temperature at 700 mb
     * @param s500 String containing temperature at 500 mb
     * @param sd850 String containing dew point at 850 mb
     * @param sd700 String containing dew point at 700 mb
     */
    public UpperAirData(String s850, String s700, String s500, String sd850, String sd700)
    {
        T850 = Integer.parseInt(s850);
        T700 = Integer.parseInt(s700);
        T500 = Integer.parseInt(s500);
        Td850 = Integer.parseInt(sd850);
        Td700 = Integer.parseInt(sd700);
    }

    /**
     * Mutator method to temperature value at 850 mb
     * @param t850 int containing temperature at 850 mb
     */
    public void setT850(int t850)
    {
        T850 = t850;
    }

    /**
     * Mutator method to temperature value at 700 mb
     * @param t700 int containing temperature at 700 mb
     */
    public void setT700(int t700)
    {
        T700 = t700;
    }

    /**
     * Mutator method to temperature value at 500 mb
     * @param t500 int containing temperature at 500 mb
     */
    public void setT500(int t500)
    {
        T500 = t500;
    }

    /**
     * Mutator method to dew point value at 850 mb
     * @param td850 int containing dew point at 850 mb
     */
    public void setTd850(int td850)
    {
        Td850 = td850;
    }

    /**
     * Mutator method to dew point value at 700 mb
     * @param td700 int containing dew point at 700 mb
     */
    public void setTd700(int td700)
    {
        Td700 = td700;
    }

    /**
     * Accessor method to get temperature at 850 mb
     * @return int containing temperature at 850 mb
     */
    public int getT850()
    {
        return T850;
    }

    /**
     * Accessor method to get temperature at 700 mb
     * @return int containing temperature at 700 mb
     */
    public int getT700()
    {
        return T700;
    }

    /**
     * Accessor method to get temperature at 500 mb
     * @return int containing temperature at 500 mb
     */
    public int getT500()
    {
        return T500;
    }

    /**
     * Accessor method to get dew point at 850 mb
     * @return int containing dew point at 850 mb
     */
    public int getTd850()
    {
        return Td850;
    }

    /**
     * Accessor method to get dew point at 700 mb
     * @return int containing dew point at 700 mb
     */
    public int getTd700()
    {
        return Td700;
    }

    /**
     * This method takes the values stored in the fields and generates a report in string form
     * including index values and risk assessments
     *
     * @return String containing the report
     */
    @Override
    public String toString()
    {
        String outString;

        // store field values
        outString = "T850: " + T850 +
                " C\nT700: " + T700 +
                " C\nT500: " + T500 +
                " C\nTd850: " + Td850 +
                " C\nTd700: " + Td700 + " C\n";

        if(!isValid())  // if not valid store error message
        {
            outString += "\n\n" + invalidMessage();
        }
        else            // if valid store index report summary
        {
            outString += "\nTTIndex: " + calculateTTIndex() +
                    "\nTTIndex Report: " + TTIndexMessage() +
                    "\n\nKIndex: " + calculateKIndex() +
                    "\nKIndex Report: " + KIndexMessage();
        }

    return outString;
    }


    /**
     * Method to calculate TTIndex
     * @return int containing the TTIndex value
     */
    public int calculateTTIndex()
    {
        return T850 + Td850 - 2 * T500;
    }

    /**
     * Method to calculate KIndex
     * @return int containing the KIndex value
     */
    public int calculateKIndex()
    {
        return T850 + Td850 - T500 - (T700 - Td700);
    }

    /**
     * The method checks the field data and returns a code value based on what part of the
     * data is invalid
     * Codes:
     * 0                data is valid
     * 1                T850 is outside of the valid range of -40 to 40
     * 2                T700 is outside of the valid range of -60 to 10
     * 3                T500 is outside of the valid range of -50 to 0
     * 4                Td850 is outside of the valid range of -40 to 40
     * 5                Td700 is outside of the valid range of -60 to 10
     * 6                Td850 is greater than T850
     * 7                Td700 is greater than T700
     *
     *  If there are multiple errors, it returns a code representing the first error found
     * @return  int a code representing if the object has valid data or invalid data
     */
    public int invalidCode()
    {
        int code=0;

        if ( T850 > 40 || T850 < -40)
        {
            code = 1;
        }
        else if (T700 > 10 || T700 < -60)
        {
            code = 2;
        }
        else if (T500 > 0 || T500 < -50)
        {
            code = 3;
        }
        else if (Td850 > 40 || Td850 < -40)
        {
            code = 4;
        }
        else if (Td700 > 10 || Td700 < -60)
        {
            code = 5;
        }
        else if (Td850 > T850)
        {
            code = 6;
        }
        else if (Td700 > T700)
        {
            code = 7;
        }

        return code;
    }

    /**
     * The method returns a value indicating if the data stored is valid or invalid
     * It calls the invalidCode method to do the check
     * @return boolean - true if data is valid, false if not
     */
    public boolean isValid()
    {
        boolean valid = true;

        if( invalidCode() !=0)
        {
            valid = false;
        }

        return valid;
    }

    /**
     * This method returns an error message based on the error code
     * @return String, a informative message indicating a problem with field data
     */
    public String invalidMessage()
    {
        int code = invalidCode();  // call to invalidCode method to find location of invalid data
        String errorMessage;

        switch (code)
        {
            case 0:
                errorMessage = "No error with weather data.";
                break;
            case 1:
                errorMessage = "T850 is outside the valid range of -40 to 40.";
                break;
            case 2:
                errorMessage = "T700 is outside the valid range of -60 to 10.";
                break;
            case 3:
                errorMessage = "T500 is outside the valid range of -50 to 0.";
                break;
            case 4:
                errorMessage = "Td850 is outside the valid range of -40 to 40.";
                break;
            case 5:
                errorMessage = "Td700 is outside the valid range of -60 to 10.";
                break;
            case 6:
                errorMessage = "The dew point Td850 exceeds the temperature T850.";
                break;
            case 7:
                errorMessage = "The dew point Td700 exceeds the temperature T700.";
                break;
            default:
                errorMessage = "Unknown error with data.";

        }
        return errorMessage;

    }

    /**
     * This method displays a risk assessment message based on the TTIndex
     * If the data is valid, it returns the risk assessment based on the TTIndex
     * If the data is invalid, it gives a message to correct the data
     *
     * @return String  - a risk assessment or message to correct data
     */
    public String TTIndexMessage()
    {
        String Message;
        int TTIndex = calculateTTIndex();

        if (invalidCode() != 0)     // if invalid data, give informative message to correct data
        {
            Message = invalidMessage() +
                    "\nNo Index has been calculated." +
                    "\nPlease correct the input data.";

        } else                      // if valid data, get risk statement
        {
            if (TTIndex < 44)
            {
                Message = "Thunderstroms Unlikely";
            } else if (TTIndex < 46)
            {
                Message = "Isolated Moderate Thunderstorms";
            } else if (TTIndex < 48)
            {
                Message = "Scattered Moderate, Few Heavy Thunderstorms";
            } else if (TTIndex < 50)
            {
                Message = "Scattered Moderate, Few Heavy, Isolated Severe Thunderstorms";
            } else if (TTIndex < 52)
            {
                Message = "Scattered Heavy, Few Severe Thunderstorms, Isolated Tornadoes";
            } else if (TTIndex < 56)
            {
                Message = "Scattered to Numerous Heavy, Few to Scattered Severe Thunderstorms, Isolated Tornadoes";
            } else
            {
                Message = "Numerous Heavy, Scattered Severe Thunderstorms, Few to Scattered Tornadoes";
            }

        }
        return Message;
    }

    /**
     * This method displays a risk assessment message based on the KIndex
     * If the data is valid, it returns the risk assessment based on the KIndex
     * If the data is invalid, it gives a message to correct the data
     *
     * @return String  - a risk assessment or message to correct data
     */
    public String KIndexMessage()
    {
        String Message;
        int KIndex = calculateKIndex();

        if (invalidCode() != 0)
        {
            Message = invalidMessage() +
                    "\nNo Index has been calculated." +
                    "\nPlease correct the input data.";

        } else
        {
            if (KIndex < 20)
            {
                Message = "Thunderstroms Unlikely";
            } else if (KIndex < 26)
            {
                Message = "Isolated Thunderstorms";
            } else if (KIndex < 31)
            {
                Message = "40% - 60% chance of thunderstorms";
            } else if (KIndex < 36)
            {
                Message = "60% - 80% chance of thunderstorms, some severe";
            } else if (KIndex < 41)
            {
                Message = "80% - 90% chance of heavy thunderstorms, some severe";
            } else
            {
                Message = "Almost 100% chance of thunderstorms, some severe";
            }
        }
    return Message;
    }

}
