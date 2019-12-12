/************************************************************************************************
 *  This class manages the zip code data that it loads from a file
 *
 *  The class generates the static list that holds zip codes date for the entire state of Michigan
 *  which the class can then access to determine the shipping costs
 *
 *  CST 183 Programming Assignment 9
 *  @author Michael Clinesmith
 ***********************************************************************************************/
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

public class MiZipCodeList
{

        private final int ZIP_ARRAY_MAX_SIZE = 2000;                                // set array maximum size
        private final String ZIP_DATA_FILE = "zipMIcity.txt";                       // file to load for zip code data
        private int zipArrayElements = 0;
        private ZipCodeData[] MiZipList = new ZipCodeData[ZIP_ARRAY_MAX_SIZE];      // create array to store zip data
        private final static double RADIUS_EARTH = 3963.189;                        // miles

    /**
     * No-parameter constructor, but it does a lot of work, generating the zip code list
     * It load the file stored in ZIP_DATA_FILE, loads the data into an array of ZipCodeData objects
     *
     * There is error detection done to see determine if the file exists.  If it does not, the method will display
     * an error message and exit the program.
     *
     * There is error detection done to check if a line of data is in the proper format.  If it is not, the method
     * will inform the user with a message and will skip that line and go to the next one.
     *
     * It will display a message indicating to the user that data was uploaded into memory.
     *
     */
    public MiZipCodeList()
        {
            String message;
            File zipData;                       // file that holds the population data
            Scanner inputFile;                  // used to get data from file
            int i = 0;
            String inputLine;                   // String used to get a line of file input

            String zipCode, zipLatitudeString, zipLongitudeString, zipStateCode, zipName;   // data fields
            double zipLatitude, zipLongitude;
            StringTokenizer lineTokens;     //  used to get tokens from data input

            try
            {
                // Attempt to open file
                zipData = new File(ZIP_DATA_FILE);

                if (!zipData.exists())  // file not found
                {
                    message = "The file " + ZIP_DATA_FILE + " does not exist for processing data.\n" +
                            "The program will now end.";

                    JOptionPane.showMessageDialog(null, message, "File Not Found", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }

                inputFile = new Scanner(zipData);
                // build list of zip code data

                // Read input file while more data exist
                // Read one line at a time (assuming each line contains one username)
                i = 0;          // used to work through array elements

                while (inputFile.hasNext())
                {
                    try                     // used to catch possible error in formating in data file
                    {
                        inputLine = inputFile.nextLine();
                        lineTokens = new StringTokenizer(inputLine);

                        // Read all data on one line
                        zipCode = lineTokens.nextToken();
                        zipLatitudeString = lineTokens.nextToken();
                        zipLongitudeString = lineTokens.nextToken();
                        zipStateCode = lineTokens.nextToken();
                        zipName = lineTokens.nextToken();

                        // format data
                        zipLatitude = Double.parseDouble(zipLatitudeString);
                        zipLongitude = Double.parseDouble(zipLongitudeString);

                        // add to zip array
                        MiZipList[i] = new ZipCodeData(zipCode, zipLatitude, zipLongitude, zipStateCode, zipName);

                        i++;    // count number of valid lines of data
                    }
                    catch (NumberFormatException e)
                    {
                        message = "There was an error processing a line of data in " + ZIP_DATA_FILE + ".\n" +
                                "The line will be skipped and the program will continue processing.";
                        JOptionPane.showMessageDialog(null, message, "Data Corrupted", JOptionPane.ERROR_MESSAGE);

                    }

                }
                zipArrayElements = i;    // Capture number of elements
                inputFile.close();
            }
            catch (IOException e)  // if error loading data, give error message and end program
            {
                message = "There was an error opening the file " + ZIP_DATA_FILE + ".\n" +
                        "The program will now end.";

                JOptionPane.showMessageDialog(null, message);
                System.exit(0);
            }

            message = "The data from the file " + ZIP_DATA_FILE +
                    "\nis now uploaded into memory.";
            JOptionPane.showMessageDialog(null, message);

        }

    /**
     * Accessor method returning the number of elements in the array
     * @return  int: The number of elements in the array
     */
        public int getNumberOfElements()
        {
            return zipArrayElements;
        }

    /**
     * Method to calculate the distance between to zip codes
     * @param zip1  String: the first 5-digit zip code
     * @param zip2  String: the second 5-digit zip code
     * @return  double: The distance in miles between the two zip codes
     */
        public double calculateDistance(String zip1, String zip2)
        {
            int index1, index2;
            double la1, lo1, la2, lo2;
            double distance = -1.0;                                         // flag value if distance not found
            index1 = zipCodeIndex( zip1 );                                  // gets index value of first zip code
            index2 = zipCodeIndex( zip2 );                                  // gets index value of second zip code

            if (index1 >=0 && index2 >=0)                                   // records found
            {
                // Convert latitude and longitude to radians
                la1 = Math.toRadians(MiZipList[index1].getLatitude());
                lo1 = Math.toRadians(MiZipList[index1].getLongitude());
                la2 = Math.toRadians(MiZipList[index2].getLatitude());
                lo2 = Math.toRadians(MiZipList[index2].getLongitude());

                // Calculate great circle distance and return
                distance = RADIUS_EARTH * Math.acos(Math.sin(la1) * Math.sin(la2)
                        + Math.cos(la1) * Math.cos(la2) * Math.cos(lo2 - lo1));
            }
            return distance;
        }

    /**
     * Method to return a ZipCodeData object the cooresponds to the given 5-digit zip code
     * @param zip String: A 5-digit zip code
     * @return ZipCodeData: the object with the 5-digit zip code, or a blank object if it did not exist
     */
        public ZipCodeData findZipCodeDataObject(String zip)
        {
            ZipCodeData zipObject;
            int index = zipCodeIndex(zip);
            if (index>=0)                                         // record found
            {
                zipObject = new ZipCodeData(MiZipList[index]);    // makes copy of record requested
            }
            else
            {
                zipObject = new ZipCodeData();                    // returns empty record
            }
            return zipObject;
        }

    /**
     * Method to get the name cooresponding to a zip code
     * @param zip String: a 5-digit zip code
     * @return  String: the name the cooresponds to the zip code, or blank if it does not exist
     */
        public String getName(String zip)
        {
            String name="";
            int index = zipCodeIndex(zip);
            if (index>=0)                                   // record found
            {
                name = MiZipList[index].getZipName();
            }

            return name;
        }

    /**
     * Method to get the latitude of a given zip code
     * @param zip String: a 5-digit zip code
     * @return  double: the latitude of the zip code or returns -360.0 if it does not exist
     */
        public double getLatitude(String zip)
        {
            double lat = -360.0;                            // some never used value
            int index = zipCodeIndex(zip);
            if (index>=0)                                   // record found
            {
                lat = MiZipList[index].getLatitude();
            }
            return lat;
        }

    /**
     * Method to get the longitude of a given zip code
     * @param zip String: a 5-digit zip code
     * @return  double: the longitude of the zip code or returns -360.0 if it does not exist
     */
        public double getLongitude(String zip)
        {
            double lon = -360.0;                            // some never used value
            int index = zipCodeIndex(zip);
            if (index>=0)                                   // record found
            {
                lon = MiZipList[index].getLongitude();
            }
            return lon;
        }

    /**
     * Method to get the state code of a zip code
     * @param zip   String: a 5-digit zip code
     * @return  String: "MI" if the code exists or "NA" if it does not (all zips are in MI)
     */
        public String getStateCode(String zip)
        {
            String stCode= "NA";

            if(zipCodeExists(zip))
            {
                stCode = "MI";
            }
            return stCode;
        }

    /**
     * Method to determine if a given zip code exists
     * @param zip   String: The zip code being searched
     * @return  boolean: true if the zip code is found, false if it was not
     */
        public boolean zipCodeExists(String zip)
        {
            boolean doesExist=true;
            int index = zipCodeIndex(zip);
            if (index<0)
            {
                doesExist = false;
            }
            return doesExist;
        }

    /**
     * Method that gets the index of a zip code in the MiZipList array
     * @param zip   String: a 5-digit zip code
     * @return  int:    The index of the zip code in the array, or -1 if it is not found
     */
        private int zipCodeIndex(String zip)
        {
            int index=-1;
            boolean isFound = false;

            for (int i=0; i<zipArrayElements && !isFound; i++)
            {
                if (MiZipList[i].getZipCode().equals(zip))
                {
                    index = i;
                    isFound = true;
                }
            }
            return index;
        }

}
