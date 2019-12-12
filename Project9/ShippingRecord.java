/************************************************************************************************
 *  This class saves a shipping record and is used to determine the cost to ship from
 *  one location to another
 *
 *  The class calls the class that generates the  static list that holds zip codes date for the
 *  entire state of Michigan which the class can then access to determine the shipping costs
 *
 *  CST 183 Programming Assignment 9
 *  @author Michael Clinesmith
 ***********************************************************************************************/

public class ShippingRecord
{
    private String centerZip;
    private String destinationZip;
    private static final double SHIPPING_COST_PER_MILE=.05;                 // given cost of shipping
    private static MiZipCodeList miZipList = new MiZipCodeList();           // create zip code list;

    /**
     * No parameter constructor
     * sets the center and destination Zips to be at University Center
     */
    public ShippingRecord()
    {
        centerZip = "48710";
        destinationZip = "48710";
    }

    /**
     * Constructor with zip code parameters
     * @param center    String: 5 digit zip code of the shipping center
     * @param dest      String: 5 digit zip code of the destination
     */
    public ShippingRecord(String center, String dest)
    {
        centerZip = center;
        destinationZip = dest;
    }

    /**
     * Mutator method to set the center zip code
     * @param centerZip String: 5 digit zip code for the shipping center
     */
    public void setCenterZip(String centerZip)
    {
        this.centerZip = centerZip;
    }

    /**
     * Mutator method to set the destination zip code
     * @param destinationZip    String: 5 digit zip code for the destination
     */
    public void setDestinationZip(String destinationZip)
    {
        this.destinationZip = destinationZip;
    }

    /**
     * Accessor method to get the current shipping cost
     * @return double: the shipping cost per mile
     */
    public static double getShippingCostPerMile()
    {
        return SHIPPING_COST_PER_MILE;
    }

    /**
     * Accessor method to get the shipping center's zip code
     * @return  double: the shipping center's 5-digit zip code
     */
    public String getCenterZip()
    {
        return centerZip;
    }

    /**
     * Accessor method to get the destination's zip code
     * @return  double: the destination's 5-digit zip code
     */
    public String getDestinationZip()
    {
        return destinationZip;
    }

    /**
     * Accessor method to get the shipping center's location's name
     * @return  String: the name of the shipping center's zip code location
     */
    public String getCenterName()
    {
        return miZipList.getName(centerZip);
    }

    /**
     * Accessor method to get the destination's name
     * @return  String: the name of the destination's zip code
     */
    public String getDestinationName()
    {
        return miZipList.getName(destinationZip);
    }

    /**
     * Accessor method to get the shipping center's latitude
     * @return  double: the latitude value
     */
    public double getCenterLatitude()
    {
        return miZipList.getLatitude(centerZip);
    }

    /**
     * Accessor method to get the destination's latitude
     * @return  double: the latitude value
     */
    public double getDestinationLatitude()
    {
        return miZipList.getLatitude(destinationZip);
    }

    /**
     * Accessor method to get the shipping center's longitude
     * @return  double: the longitude value
     */
    public double getCenterLongitude()
    {
        return miZipList.getLongitude(centerZip);
    }

    /**
     * Accessor method to get the destination's longitude
     * @return  double: the longitude value
     */
    public double getDestinationLongitude()
    {
        return miZipList.getLongitude(destinationZip);
    }

    /**
     * Method to return a string value of the information stored in the object
     * @return String: the center and destination zip codes
     */
    @Override
    public String toString()
    {
        String message;

        message = "Center ZIP: " + centerZip +
 /*
                  "\nCenter Latitude: " + getCenterLatitude() +
                  "\nCenter Longitude: " + getCenterLongitude() +
                  "\nDestination Latitude: " + getDestinationLatitude() +
                  "\nDestination Longitude: " + getDestinationLongitude() +
 */
                 "\nDestination ZIP: " + destinationZip;

        return message;
    }

    /**
     * Method to calculate the shipping cost to go from the shipping center to the destination
     * @return  double: the cost to ship a product
     */
    public double calculateShippingCost()
    {
        return calculateShippingDistance() * SHIPPING_COST_PER_MILE;
    }

    /**
     * Method to calculate the distance from the shipping center to the destination
     * @return  double: the distance in miles from the shipping center to the destination
     */
    public double calculateShippingDistance()
    {
        return miZipList.calculateDistance(centerZip, destinationZip);
    }

    /**
     * Method to check that the zip codes saved are valid
     * @return  boolean: returns true if the zip codes are valid, false if at least one is not
     */
    public boolean isValid()
    {
        boolean isValid= true;
        if(!miZipList.zipCodeExists(centerZip))
        {
            isValid = false;
        }
        if(!miZipList.zipCodeExists(destinationZip))
        {
            isValid = false;
        }
        return isValid;
    }
}
