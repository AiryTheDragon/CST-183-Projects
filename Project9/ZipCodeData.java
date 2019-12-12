/************************************************************************************************
 *  This class saves data for a single zip code record
 *
 *  CST 183 Programming Assignment 9
 *  @author Michael Clinesmith
 ***********************************************************************************************/
public class ZipCodeData
{
    private String zipCode, stateCode, zipName;
    private double latitude, longitude;

    /**
     * No parameter constructor
     */
    public ZipCodeData()
    {
        zipCode = "00000";
        stateCode = "NA";
        zipName = "";
        latitude = 0.0;
        longitude = 0.0;
    }

    /**
     * Constructor containing parameters for a zip code location
     * @param zip   String: the 5 digit zip code
     * @param lat   double: the latitude location of the zip code
     * @param lon   double: the longitude location of the zip code
     * @param state String: the 2 character state code
     * @param name  String: the name of the zip code location
     */
    public ZipCodeData(String zip, double lat, double lon, String state, String name)
    {
        zipCode = zip;
        stateCode = state;
        zipName = name;
        latitude = lat;
        longitude = lon;
    }

    /**
     * Copy constructor makes another zipObject
     * @param zipObject ZipCodeData: object to make a copy of
     */
    public ZipCodeData(ZipCodeData zipObject)
    {
        zipCode = zipObject.getZipCode();
        stateCode = zipObject.getStateCode();
        zipName = zipObject.getZipName();
        latitude = zipObject.getLatitude();
        longitude = zipObject.getLongitude();

    }

    /**
     * Mutator method to set the zip code
     * @param zipCode String: a 5 digit zip code
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * Mutator method to set the state code
     * @param stateCode String: a 2 character state code
     */
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    /**
     * Mutator method to set the latitude of the zip code
     * @param latitude double: a latitude value
     */
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * Mutator method to set the longitude of the zip code
     * @param longitude double: a longitude value
     */
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * Mutator method to set the name of the zip code area
     * @param zipName String: the name of the zip code area
     */
    public void setZipName(String zipName)
    {
        this.zipName = zipName;
    }

    /**
     * Accessor method to get the zip code
     * @return String: the 5-digit zip code
     */
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * Accessor method to get the state code
     * @return String: the 2-character state code
     */
    public String getStateCode()
    {
        return stateCode;
    }

    /**
     * Accessor method to get the latitude of a zip code
     * @return double: the latitude value
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Accessor method to get the longitude of a zip code
     * @return double: the longitude value
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Accessor method to get the name of a zip code
     * @return String: the name of the zip code area
     */
    public String getZipName()
    {
        return zipName;
    }

    /**
     * Method to return a string value of the information stored in the object
     * @return String: the values stored in the zip code object
     */
    @Override
    public String toString()
    {
        String message = "Zip Code: " + zipCode +
                    "\nState Code: " + stateCode +
                    "\nLocale Name: " + zipName +
                    "\nLatitude: " + latitude +
                    "\nLongitude: " + longitude;

        return message;
    }
}
