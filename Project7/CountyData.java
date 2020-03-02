/************************************************************************************************
 *  This class stores county FIPS code, state and population data.
 *
 *  The user is requested to choose a menu option, then enter the data required to fulfill the request.
 *
 *  The program consists mostly of getters and setters,
 *  however, outside of the constructor, an individual year needs to be to be chosen to get or set
 *  a population number
 *
 *  CST 183 Programming Assignment 7
 *  @author Michael Clinesmith
 ***********************************************************************************************/

public class CountyData
{
    // class constants
    private final int FIRST_YEAR = 2010, LAST_YEAR = 2017;
    private final int YEARS_STORED = LAST_YEAR - FIRST_YEAR + 1;

    // class fields
    private String FIPScode;
    private String countyName, stateCode;

    private int[] population = new int[YEARS_STORED];

    /**
     * No parameter constructor
     */
    public CountyData()
    {
        FIPScode = "00000";
        countyName = "None";
        stateCode = "NA";

        for (int i = 0; i<YEARS_STORED; i++)
        {
            population[i] = 0;
        }
    }

    /**
     * Constructor with parameters
     * @param code String: County FIPS code
     * @param county String: County name
     * @param state String: State code
     * @param popData int array: contains population data
     */
    public CountyData(String code, String county, String state, int[] popData)
    {
        FIPScode = code;
        countyName = county;
        stateCode = state;

        if (popData.length >= YEARS_STORED)         // if complete data, fill entire population array with data
        {
            for (int i = 0; i < YEARS_STORED; i++)
            {
                population[i] = popData[i];
            }

        }
        else                                        // if not enough data, fill extra array elements with 0s
        {
            for (int i = 0; i < popData.length; i++)
            {
                population[i] = popData[i];
            }
            for (int i = popData.length; i < YEARS_STORED; i++)
            {
                population[i] = 0;
            }
        }

    }

    /**
     * Mutator method to set the FIPS code
     * @param FIPScode String: FIPS code of a county
     */
    public void setFIPScode(String FIPScode)
    {
        this.FIPScode = FIPScode;
    }

    /**
     * Mutator method to set the county name
     * @param countyName String: A county name
     */
    public void setCountyName(String countyName)
    {
        this.countyName = countyName;
    }

    /**
     * Mutator method to set the state code
     * @param stateCode String: A state code representing the state a county is in
     */
    public void setStateCode(String stateCode)
    {
        this.stateCode = stateCode;
    }

    /**
     * Mutator method to set the county population for a particular year
     * @param year int: The year to set the population for
     * @param pop int: The population in a particular year
     */
    public void setPopulation( int year, int pop)
    {
        if (year >= FIRST_YEAR && year <= LAST_YEAR)        // make certain year is in proper range
        {
            population[year-FIRST_YEAR] = pop;
        }
    }

    /**
     * Accessor method to get a county's FIPS code
     * @return String: The FIPS code of a county
     */
    public String getFIPScode()
    {
        return FIPScode;
    }

    /**
     * Accessor method to get a county's name
     * @return String: The name of a county
     */
    public String getCountyName()
    {
        return countyName;
    }

    /**
     * Accessor method to get a county's state code
     * @return String: The state code of a county
     */
    public String getStateCode()
    {
        return stateCode;
    }

    /**
     * Accessor method to get a county's population in a particular year
     * @param year int: a year
     * @return int: the county's population in that year
     */
    public int getPopulation( int year)
    {
        int pop = -1;
        if (year >= FIRST_YEAR && year <= LAST_YEAR)        // make certain year is in proper range
        {
            pop = population[year-FIRST_YEAR];
        }

        return pop;
    }

    /**
     * Method to return a string representing the data stored in a CountyData obuect
     * @return String: Contains the FIPS code, county name, state code, and population data of a CountyData object
     */
    @Override
    public String toString()
    {
        String data;
        data = "FIPS Code: " + FIPScode +
                "\nCounty Name: " + countyName +
                "\nState: " + stateCode + "\n";

        for (int i = 0; i<YEARS_STORED; i++)
        {
            data += "" + (FIRST_YEAR + i) + " population: " + population[i] + "\n";
        }

        return data;
    }

}
