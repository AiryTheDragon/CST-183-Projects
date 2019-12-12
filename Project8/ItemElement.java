/************************************************************************************************
 *  This class holds information regarding a pizza order element
 *
 *  It contains a property, the property value and a level of indentation
 *
 *  CST 183 Programming Assignment 8
 *  @author Michael Clinesmith
 ***********************************************************************************************/

public class ItemElement
{

    private String itemName;
    private String itemProperty;
    private int level;      // used to keep track of indentation level

    public ItemElement()
    {
        itemName = "";
        itemProperty = "";
        level = 0;
    }

    public ItemElement(String name)
    {
        itemName = name;
        itemProperty = "";
        level = 0;
    }

    public ItemElement(String name, String property)
    {
        itemName = name;
        itemProperty = property;
        level = 0;
    }

    public ItemElement(String name, String property, int lev)
    {
        itemName = name;
        itemProperty = property;

        if (lev < 0)
        {
            lev = 0;
        }
        level = lev;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setItemProperty(String itemProperty)
    {
        this.itemProperty = itemProperty;
    }

    public void setLevel(int level)
    {
        if (level<0)
        {
            level = 0;
        }
        this.level = level;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getItemProperty()
    {
        return itemProperty;
    }

    public int getLevel()
    {
        return level;
    }

    @Override
    public String toString()
    {
        String message = "";
        if (itemName.length()!=0)                         // return empty String if no data
        {
            for (int i = 0; i < level; i++)               // gives indentation for level
            {
                message += "     ";
            }
            message += itemName + ": " + itemProperty;
        }
        return message;
    }

}
