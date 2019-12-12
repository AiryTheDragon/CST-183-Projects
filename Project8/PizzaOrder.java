/************************************************************************************************
 *  This class hold information regarding a pizza order
 *
 *  The user is to enter the following data for a pizza order:
 *      pizza specialty
 *      server name
 *      delivery method
 *      pizza size
 *      optional extra ingredients
 *      spiciness level
 *      optional special instructions
 *
 *  CST 183 Programming Assignment 8
 *  @author Michael Clinesmith
 ***********************************************************************************************/
public class PizzaOrder
{

    private ItemElement pizzaSpecialty, serverName, orderType, pizzaSize,
                        pizzaECOption, pizzaESOption, pizzaGCOption, pizzaSCOption,
                        spiciness, specialInstructions;

    /**
     * Basic constructor that sets up blank options
     */
    public PizzaOrder()
    {
        pizzaSpecialty = new ItemElement();
        serverName = new ItemElement();
        orderType = new ItemElement();
        pizzaSize = new ItemElement();

        pizzaECOption = new ItemElement();
        pizzaESOption = new ItemElement();
        pizzaGCOption = new ItemElement();
        pizzaSCOption = new ItemElement();
        spiciness = new ItemElement();
        specialInstructions = new ItemElement();

    }

    /**
     * Constructor that creates on object with the given paramaters
     * @param specialty String type of specialty pizza ordered
     * @param name String name of server
     * @param type String type of delivery
     * @param size String size of pizza
     * @param xCheese boolean if extra cheese selected
     * @param xSauce boolean if extra sauce selected
     * @param garlic boolean if garlic crust selected
     * @param stuffed boolean if stuffed crust selected
     * @param spicy double spiciness level of sauce
     * @param instruct String special instructions given
     */
    public PizzaOrder(String specialty, String name, String type, String size,  boolean xCheese, boolean xSauce,
                      boolean garlic, boolean stuffed, double spicy, String instruct )
    {
        pizzaSpecialty = new ItemElement("Pizza Selection", specialty, 0);
        serverName = new ItemElement("Server Name", name, 1);
        orderType = new ItemElement("Delivery Method", type, 1);
        pizzaSize = new ItemElement("Pizza Size", size, 1);
        if (xCheese)
        {
            pizzaECOption = new ItemElement("Extra Cheese Option", "Selected", 2);
        }
        else
        {
            pizzaECOption = new ItemElement("Extra Cheese Option", "Not Selected", 2);
        }
        if (xSauce)
        {
            pizzaESOption = new ItemElement("Extra Sauce Option", "Selected", 2);
        }
        else
        {
            pizzaESOption = new ItemElement("Extra Sauce Option", "Not Selected", 2);
        }
        if (garlic)
        {
            pizzaGCOption = new ItemElement("Garlic Crust Option", "Selected", 2);
        }
        else
        {
            pizzaGCOption = new ItemElement("Garlic Crust Option", "Not Selected", 2);
        }
        if (stuffed)
        {
            pizzaSCOption = new ItemElement("Stuffed Crust Option", "Selected", 2);
        }
        else
        {
            pizzaSCOption = new ItemElement("Stuffed Crust Option", "Not Selected", 2);
        }
        spiciness = new ItemElement("Spiciness Level", Double.toString(spicy), 1);
        specialInstructions = new ItemElement("Special Instructions", instruct, 1);

    }

    /**
     *  Setters for the pizza order individual elements are below
     */
    public void setPizzaSpecialty(String specialty)
    {
        pizzaSpecialty = new ItemElement("Pizza Selection", specialty, 0);
    }

    public void setServerName(String name)
    {
        serverName = new ItemElement("Server Name", name, 1);

    }

    public void setOrderType(String type)
    {
        orderType = new ItemElement("Delivery Method", type, 1);
    }

    public void setPizzaSize(String size)
    {
        pizzaSize = new ItemElement("Pizza Size", size, 1);
    }

    public void setPizzaECOption(boolean xCheese)
    {
        if (xCheese)
        {
            pizzaECOption = new ItemElement("Extra Cheese Option", "Selected", 2);
        }
        else
        {
            pizzaECOption = new ItemElement("Extra Cheese Option", "Not Selected", 2);
        }
    }

    public void setPizzaESOption(boolean xSauce)
    {
        if (xSauce)
        {
            pizzaESOption = new ItemElement("Extra Sauce Option", "Selected", 2);
        }
        else
        {
            pizzaESOption = new ItemElement("Extra Sauce Option", "Not Selected", 2);
        }
    }

    public void setPizzaGCOption(boolean garlic)
    {
        if (garlic)
        {
            pizzaGCOption = new ItemElement("Garlic Crust Option", "Selected", 2);
        }
        else
        {
            pizzaGCOption = new ItemElement("Garlic Crust Option", "Not Selected", 2);
        }
    }

    public void setPizzaSCOption(boolean stuffed)
    {
        if (stuffed)
        {
            pizzaSCOption = new ItemElement("Stuffed Crust Option", "Selected", 2);
        }
        else
        {
            pizzaSCOption = new ItemElement("Stuffed Crust Option", "Not Selected", 2);
        }
    }

    public void setSpiciness(double spicy)
    {
        spiciness = new ItemElement("Spiciness Level", Double.toString(spicy), 1);
    }

    public void setSpecialInstructions(String instruct)
    {
        specialInstructions = new ItemElement("Special Instructions", instruct, 1);
    }

    /*******************************************************************
     *
     * Individual getters still need to be set up for application

    public ItemElement getPizzaSpecialty()
    {
        return pizzaSpecialty;
    }

    public ItemElement getServerName()
    {
        return serverName;
    }

    public ItemElement getOrderType()
    {
        return orderType;
    }

    public ItemElement getPizzaSize()
    {
        return pizzaSize;
    }

    public ItemElement getPizzaECOption()
    {
        return pizzaECOption;
    }

    public ItemElement getPizzaESOption()
    {
        return pizzaESOption;
    }

    public ItemElement getPizzaGCOption()
    {
        return pizzaGCOption;
    }

    public ItemElement getPizzaSCOption()
    {
        return pizzaSCOption;
    }

    public ItemElement getSpiciness()
    {
        return spiciness;
    }

    public ItemElement getSpecialInstructions()
    {
        return specialInstructions;
    }

     *********************************************************/

    /**
     * Method to return information stored in the object
     * @return String information about order saved
     */
    @Override
    public String toString()
    {
        String message="";
        if(pizzaSpecialty.toString().length()!=0)               // if no data return blank string
        {
            message += pizzaSpecialty.toString() + "\n";
            message += serverName.toString() + "\n";
            message += orderType.toString() + "\n";
            message += pizzaSize.toString() + "\n";
            message += pizzaECOption.toString() + "\n";
            message += pizzaESOption.toString() + "\n";
            message += pizzaGCOption.toString() + "\n";
            message += pizzaSCOption.toString() + "\n";
            message += spiciness.toString() + "\n";
            message += specialInstructions.toString() + "\n";
        }
        return message;
    }


}
