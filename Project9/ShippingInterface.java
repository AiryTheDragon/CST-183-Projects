/************************************************************************************************
 *  This program simulates a shipping tool to calculate the shipping charge for shipping an item
 *  from a shipping center to another location in the state
 *
 *  This class creates the graphical user interface that includes a keypad to enter a zip code,
 *  a drop down list to select a shipping center and then will calculate and display the cost to the
 *  user in a text area.
 *
 *  One of the other classes will load zip code information for the state so the information the user
 *  requests can be calculated.
 *
 *  The user can clear all the data using the clear button at the bottom of the screen.
 *  The user can clear the zip code using the "C" button.
 *  The user can delete one character in the zip code using the backspace button.
 *
 *  The program will check the input for the following items:
 *      That a shipping center was selected
 *      That a 5-digit zip code was entered
 *      That the 5-digit zip code exists in the state of Michigan
 *
 *  When the user presses the calculate button, an error message is displayed if there are errors,
 *  if there are no errors, information including the shipping distance and cost are displayed in the
 *  text field.  The user can enter more data if desired, or can press the quit button to quit
 *
 *  CST 183 Programming Assignment 9
 *  @author Michael Clinesmith
 ***********************************************************************************************/

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ShippingInterface extends Application
{
    // main node
    private BorderPane mainLayout;

    // shipping location arrays
    private final String shippingCenter[] = {"University Center", "Mackinaw City", "Grand Rapids", "Marquette", "Traverse City"};
    private final String shippingCenterZip[] = {"48710", "49701", "49501", "49855", "49684"};

    // keypad objects
    private GridPane keyPad;
    private Button keyButton[];
    private TextField zip;
    private VBox keyPadVBox;
    private Label zipLabel;

    // center choice objects
    private Label centerLabel;
    private ComboBox<String> centerBox;
    private VBox centerVBox;

    // informational objects
    TextArea messageArea;
    private HBox infoHBox;
    private final String FIRST_MESSAGE = "Select a shipping center, enter a zip code on the key pad then " +
                        "press the calculate button to determine the shipping costs for delivering a " +
                        "package from the shipping center to that zip code.";

    // bottom button objects
    private Button calculateButton, clearButton, quitButton;
    private HBox buttonHBox;

    // holds shipping record
    private ShippingRecord record = new ShippingRecord();

    /**
     * main method of program, used to launch graphical interface
     *
     * @param args String array - arguments are not used besides being passed to launch method
     */
    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }

    /**
     * Method that calls the initializeScene method and creates the scene
     * @param primaryStage Stage object used to create the stage
     */
    @Override
    public void start(Stage primaryStage)
    {
        initializeScene();

        // Set up overall scene
        Scene scene = new Scene(mainLayout, 1100, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shipping Application");
        primaryStage.show();
    }

    /**
     * Method that calls other methods to create the scene, then puts the containers together in thee main node
     */
    public void initializeScene()
    {
        createKeypad2();
        createComboBox();
        createBottomButtons();
        createInformationObjects();

        mainLayout = new BorderPane();
        mainLayout.setCenter(infoHBox);
        mainLayout.setRight(keyPadVBox);
        mainLayout.setLeft(centerVBox);
        mainLayout.setBottom(buttonHBox);
        mainLayout.setStyle("-fx-background-color: lightgreen;");

    }

    /**
     * Method creates the keypad elements including the display
     */
    public void createKeypad2()
    {
        // the display textField
        zip = new TextField("");
        zip.setFont(new Font(20));
        zip.setAlignment(Pos.CENTER);
        zip.setMaxWidth(100);
        zip.setStyle("-fx-text-inner-color: purple; -fx-background-color: lightgray;");
        zip.setEditable(false);

        // the informational label
        zipLabel = new Label("Zip Code");
        zipLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 24px;");
        zipLabel.setAlignment(Pos.CENTER);

        // the keypad design
        keyButton = new Button[12];
        keyPad = new GridPane();

        // design the keypad top three rows
        for (int i=1; i<10; i++)
        {
            keyButton[i]= new Button(Integer.toString(i));
            keyButton[i].setFont(new Font(40));
            keyButton[i].setPrefSize(100, 100);
            keyButton[i].setMinSize(100,100);
            keyButton[i].setMaxSize(100,100);
            keyPad.add(keyButton[i], (i-1)%3, (i-1)/3);
            keyPad.setPrefSize(300,300);
            keyButton[i].setOnAction(new KeypadButtonHandler());
        }

        // bottom keypad row

        // 0 button
        keyButton[0] = new Button("0");
        keyButton[0].setFont(new Font(40));
        keyPad.add(keyButton[0], 1, 3);
        keyButton[0].setPrefSize(100, 100);
        keyButton[0].setMinSize(100,100);
        keyButton[0].setMaxSize(100,100);
        keyButton[0].setOnAction(new KeypadButtonHandler());

        // backspace button
        keyButton[10] = new Button("\u2190");               // back arrow unicode character
        keyButton[10].setFont(new Font(40));
        keyPad.add(keyButton[10], 0, 3);
        keyButton[10].setPrefSize(100, 100);
        keyButton[10].setMinSize(100,100);
        keyButton[10].setMaxSize(100,100);
        keyButton[10].setStyle("-fx-background-color: red;");
        keyButton[10].setOnAction(new KeypadButtonHandler());

        // clear zipcode button
        keyButton[11] = new Button("C");
        keyButton[11].setFont(new Font(40));
        keyPad.add(keyButton[11], 2, 3);
        keyButton[11].setPrefSize(100, 100);
        keyButton[11].setMinSize(100,100);
        keyButton[11].setMaxSize(100,100);
        keyButton[11].setStyle("-fx-background-color: red;");
        keyButton[11].setOnAction(new KeypadButtonHandler());

        keyPadVBox =  new VBox (20, zipLabel, zip, keyPad);
        keyPadVBox.setAlignment(Pos.CENTER);
        keyPadVBox.setPadding(new Insets(20));

    }

    /**
     * Method creates the Combo Box that holds the shipping center choices
     */
    public void createComboBox()
    {
           centerBox= new ComboBox<String>();
           centerBox.getItems().add("");
           centerBox.setStyle("-fx-font-size: 22px;");
           centerBox.setPadding(new Insets(20));

           // add contents to centerBox
           centerBox.setValue("");
           for (int i=0; i<shippingCenter.length; i++)
           {
               centerBox.getItems().add((shippingCenter[i]+ " (" + shippingCenterZip[i] + ")"));
           }

           centerBox.getSelectionModel().selectFirst();                 // select first option in ComboBox

           //   Create label identifying ComboBox
           centerLabel = new Label("Shipping Centers");
           centerLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 24px;");
           centerLabel.setAlignment(Pos.CENTER);

           // put ComboBox and label together
           centerVBox = new VBox(20, centerLabel, centerBox);
           centerVBox.setAlignment(Pos.CENTER);
           centerVBox.setPadding(new Insets(20));
    }

    /**
     * Method creates the buttons to calculate the shipping cost, clear and quit the application
     */
    public void createBottomButtons()
    {
        calculateButton = new Button("\u23CE Calculate Shipping Cost");         // include unicode enter character
        calculateButton.setOnAction(new CalculateButtonHandler());
        calculateButton.setStyle("-fx-font-size: 20px;");

        clearButton = new Button("Clear Input");
        clearButton.setOnAction(new CalculateButtonHandler());
        clearButton.setStyle("-fx-font-size: 20px;");

        quitButton = new Button("Quit");
        quitButton.setOnAction((new CalculateButtonHandler()));
        quitButton.setStyle("-fx-font-size: 20px;");

        // put together button elements
        buttonHBox = new HBox (20, calculateButton, clearButton, quitButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(20));
    }

    /**
     * Method creates the informational objects in the middle of the interface
     */
    public void createInformationObjects()
    {
        messageArea = new TextArea(FIRST_MESSAGE);
        messageArea.setStyle("-fx-font-size: 16px;");
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPadding(new Insets(20));
        messageArea.setPrefColumnCount(20);
        messageArea.setPrefRowCount(60);
        messageArea.setMaxHeight(500);

        infoHBox = new HBox(20, messageArea);
        infoHBox.setAlignment(Pos.CENTER);

    }

    /**
     * This method inputs in a string that has a zip code between parentheses (XXXXX)
     * and extracts the zip code from the string and returns it
     *
     * it does error checking on the string and zip to ensure it is a 5-digit number code
     * if it does not find parentheses, or they are in the wrong order, or the number sequence is
     * not the right length, an empty string is returned.
     *
     * @param str String that should contain a zip code between parentheses
     * @return str a five digit zip code, or an empty string if not found
     */
    public String extractZip(String str)
    {
        String zipString ="";
        int paraLoc1,paraLoc2;

        if(str.length()!=0)
        {
            paraLoc1 = str.indexOf('(');
            paraLoc2 = str.indexOf(')');

            if (paraLoc1>=0 && paraLoc1<paraLoc2)       // make certain characters found and in right order
            {
                zipString = str.substring(paraLoc1+1, paraLoc2);    // set zip code, then make some final checks
            }

            if(zipString.length()==5)                   // make certain zip is right length and all numbers
            {
                boolean allDigits=true;
                for(int i=0; i<5; i++)
                {
                    if (!Character.isDigit(zipString.charAt(i)))    // check each character if it is a digit
                    {
                        allDigits=false;
                    }
                }

                if(!allDigits)                          // return blank string if code not all digits
                {
                    zipString="";
                }

            }
            else                                        // return blank string if code not right length
            {
                zipString="";
            }
        }
        return zipString;
    }
    /**
     * Class ButtonClickHandler handles the button click events
     */
    class KeypadButtonHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles button click events for the keypad
         *
         * @param event ActionEvent object that contains data about a button click event
         */
        @Override
        public void handle(ActionEvent event)
        {

            boolean buttonFound = false;

            for (int i = 0; i < 10 && !buttonFound; i++)            // check if number button pressed
            {
                if (event.getSource() == keyButton[i])
                {
                    String keyInput;
                    keyInput = zip.getText();
                    if (keyInput.length() < 5)                      // add to zip code if less than 5 digits
                    {
                        keyInput = keyInput + i;
                        zip.setText(keyInput);
                    }
                    buttonFound = true;
                }
            }

            if (event.getSource() == keyButton[10])                 // backspace button
            {
                String keyInput;
                keyInput = zip.getText();
                if (keyInput.length() != 0)                         // remove a digit if at least one exists
                {
                    keyInput = keyInput.substring(0,keyInput.length()-1);
                    zip.setText(keyInput);
                }

            }
            if (event.getSource() == keyButton[11])                 // clear keypad button
            {
                zip.setText("");
            }
        }
    }
    class CalculateButtonHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles button click events for the buttons on the bottom of the screen
         *
         *
         * @param event ActionEvent object that contains data about a button click event
         */
        @Override
        public void handle(ActionEvent event)
        {

            if (event.getSource() == calculateButton)           // calculate based on data
            {
                boolean isValid=false;
                String boxValue;                                // raw ComboBox String selection
                String zipBoxValue;                             // for shipping center zip code from ComboBox
                String zipDest;                                 // for destination zip code
                String message="";                              // for informational message to user

                boxValue = centerBox.getValue();
                zipBoxValue = extractZip(boxValue);             // gets zip code out of option choices

                zipDest = zip.getText();                        // gets destination zip code

                //  error check data to see if valid and display appropriate messages
                if (zipBoxValue.length()!=5)                                // center does not have valid zip
                {
                    message = "Please select an appropriate shipping center.";
                    messageArea.setText(message);
                }
                else if (zipDest.length()!=5)                               // destination zip not 5 digits
                {
                    message = "Please enter a 5 digit zip code.";
                    messageArea.setText(message);
                }
                else if (zipBoxValue.equals(zipDest))                       // destination equals center zip
                {
                    message = "Destination zip code matches shipping center zip code.\n\n" +
                            "Please enter a zip code that does not match the shipping center " +
                            "zip code to determine shipping costs.";
                    messageArea.setText(message);
                }
                else
                {
                    record = new ShippingRecord(zipBoxValue, zip.getText());

                    if (!record.isValid())                                  // destination zip does not exist
                    {
                        message = "Destination zip code does not exist in the state of Michigan.\n\n" +
                                "Please enter a zip code that exists in Michigan.";
                        messageArea.setText(message);
                    }
                    else                                                    // valid data, make calculations
                    {
                        message += record.toString();

                        message += "\n\nCalculating cost to ship from " + record.getCenterName() + " to " +
                                record.getDestinationName() + ".";

                        message += "\n\nDistance to destination from the shipping source: " +
                                String.format("%.2f", record.calculateShippingDistance()) + " miles.\n\n";

                        message += "Cost for shipping: $" + String.format("%.2f", record.calculateShippingCost()) + ".";

                        messageArea.setText(message);
                    }
                }

            }
            else if (event.getSource() == clearButton)              // clear button
            {
                centerBox.setValue("");
                centerBox.getSelectionModel().selectFirst();        // sets ComboBox to first blank option
                zip.setText("");
                messageArea.setText(FIRST_MESSAGE);                 // resets informational message
            }
            if (event.getSource() == quitButton)                    // quit button
            {
                System.exit(0);
            }
        }
    }

}
