/************************************************************************************************
 *  This program simulates a pizza order graphical interface and application
 *  and tests the funcationality of the PizzaOrder and ItemElement objects
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
 *  The user also has the option to request pop and breadsticks
 *
 *  The application error checks that valid selections are made when placing the order and
 *  lists error messages listing the problems if there are some.
 *  If there are not errors, the order is placed into a PizzaOrder Object
 *
 *  CST 183 Programming Assignment 8
 *  @author Michael Clinesmith
 ***********************************************************************************************/

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PizzaInterface extends Application
{
    // image objects
    private Image orangeDragon, pizza, pizzaPlaceName;
    private ImageView orangeDragonIV, pizzaIV, pizzaPlaceNameIV;

    private HBox heading, buttonHBox, infoHBox;
    private VBox orderTypeVBox, serverVBox, serverOrderTypeVBox, bottomVBox;

    // left frame objects
    private Label serverLabel, orderTypeLabel;
    private TextField serverName;
    private ComboBox<String> orderType;

    // bottom frame objects
    private Button clearOrderButton, submitOrderButton, exitButton;
    private BorderPane borderPane;
    private TextArea infoTextArea;

    // pizza interface objects
    private GridPane pizzaOrderGrid;
    private Label pizzaSizeLabel, specialtyLabel, addOnLabel, instructionsLabel, spicyLabel;
    private RadioButton smallPizzaRadio, mediumPizzaRadio, largePizzaRadio, xlargePizzaRadio;
    private VBox pizzaSizeVBox, specialtyVBox, addOnVBox, instructionsVBox, spicyVBox;

    private ToggleGroup pizzaToggle;
    private ComboBox<String> specialty;
    private CheckBox extraCheese, extraSauce, garlicCrust, stuffedCrust;
    private TextArea specialInstructionsArea;
    private Slider spiciness;

    // extra options interface objects
    private Label popSizeLabel, popTypeLabel, breadstickRadioLabel;
    private ToggleGroup popSizeToggle, breadstickToggle;
    private CheckBox popCheckBox, breadstickCheckBox;
    private ComboBox<String> popTypeComboBox;
    private HBox PopTypeHBox;
    private VBox extraOptionsVBox, popVBox, breadstickVBox, popOptionVBox, popSizeVBox, popTypeVBox,
                    breadstickOptionVBox, breadstickTypeVBox;
    private RadioButton smallPopRadio, mediumPopRadio, largePopRadio, xlargePopRadio;
    private RadioButton regularBreadsticksRadio, cheesyBreadsticksRadio, frostedBreadsticksRadio;

    // main object
    private PizzaOrder pizzaObject;

    // String to hold error message
    private String errorMessage = "No Errors";
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

        Scene scene = new Scene(borderPane, 1100, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();

    }

    /**
     * Method calls other methods to design the user interface
     */
    public void initializeScene()
    {
        loadImages();
        prepareHeader();
        prepareOrderTypeBox();
        prepareOrderGrid();
        prepareExtraOptions();
        prepareBottomBox();

        borderPane = new BorderPane();
        displayMainMenu();
    }

    /**
     * Method to create the borderPane for the interface
     */
    public void displayMainMenu()
    {
        // the first two lines of code are elements of the design process which changed during development
        // and could be moved or changed later if necessary
        buttonHBox.getChildren().clear();
        buttonHBox.getChildren().addAll(submitOrderButton, clearOrderButton, exitButton );

        borderPane.setTop(heading);
        borderPane.setLeft(serverOrderTypeVBox);
        borderPane.setRight(extraOptionsVBox);
        borderPane.setBottom(bottomVBox);
        borderPane.setCenter(pizzaOrderGrid);

    }

    /**
     * Method to upload and set up images for interface
     */
    public void loadImages()
    {
        orangeDragon = new Image("file:Cute orange dragon.jpg");
        pizza = new Image("file:pizza.jpg");
        pizzaPlaceName = new Image("file:AirysPizzaPalace.png");

        orangeDragonIV = new ImageView(orangeDragon);
        pizzaIV = new ImageView(pizza);
        pizzaPlaceNameIV = new ImageView(pizzaPlaceName);

        orangeDragonIV.setFitHeight(100);
        orangeDragonIV.setPreserveRatio(true);

        pizzaIV.setFitHeight(100);
        pizzaIV.setPreserveRatio(true);

        pizzaPlaceNameIV.setFitHeight(100);
        pizzaPlaceNameIV.setPreserveRatio(true);

    }

    /**
     * Method to create the interface header
     */
    public void prepareHeader()
    {
        heading = new HBox(pizzaPlaceNameIV, pizzaIV, orangeDragonIV);
        heading.setStyle("-fx-background-color: white");
    }

    /**
     * Method to setup the server name and delivery type on the left side of the interface
     */
    public void prepareOrderTypeBox()
    {
        serverLabel = new Label(("Server Name"));
        serverName = new TextField();
        serverVBox = new VBox(serverLabel, serverName);
        serverVBox.setAlignment(Pos.TOP_CENTER);

        orderTypeLabel = new Label("Type of Delivery");
        orderType = new ComboBox<String>();
        orderType.getItems().addAll("Dine In", "Carry Out", "Delivery");
        orderTypeVBox = new VBox(orderTypeLabel, orderType);
        orderTypeVBox.setAlignment(Pos.TOP_CENTER);
        serverOrderTypeVBox = new VBox(100, serverVBox, orderTypeVBox);
        serverOrderTypeVBox.setAlignment(Pos.TOP_CENTER);
        serverOrderTypeVBox.setPadding(new Insets(30));
        serverOrderTypeVBox.setStyle("-fx-background-color: yellow");
    }

    /**
     * Method to prepare the buttons at the bottom of the screen
     */
    public void prepareButtons()
    {
        submitOrderButton = new Button("Submit Order");
        clearOrderButton = new Button("Clear Order");
        exitButton = new Button( "Exit Program");

        clearOrderButton.setOnAction(new ButtonClickHandler());
        submitOrderButton.setOnAction(new ButtonClickHandler());
        exitButton.setOnAction(new ButtonClickHandler());

        // buttonHBox = new HBox( 100, beginOrderButton, reviewOrderButton, exitButton );
        buttonHBox = new HBox( 100 );
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(30));

    }

    /**
     * Method that calls other methods to design the center order grid
     */
    public void prepareOrderGrid()
    {
        preparePizzaSizeOption();
        preparePizzaSpecialityOption();
        preparePizzaAddOnIngredients();
        prepareSpecialInstructions();
        prepareSpicyOption();
        createGridPane();
    }

    /**
     * Method to design the pizza size option
     */
    public void preparePizzaSizeOption()
    {
        pizzaSizeLabel = new Label("Pizza Size:");
        smallPizzaRadio = new RadioButton("Small: 6\"");
        mediumPizzaRadio = new RadioButton("Medium: 10\"");
        largePizzaRadio = new RadioButton("Large: 12\"");
        xlargePizzaRadio = new RadioButton("X-large: 14\"");
        pizzaToggle = new ToggleGroup();
        smallPizzaRadio.setToggleGroup(pizzaToggle);
        mediumPizzaRadio.setToggleGroup(pizzaToggle);
        largePizzaRadio.setToggleGroup(pizzaToggle);
        xlargePizzaRadio.setToggleGroup(pizzaToggle);

        pizzaSizeVBox = new VBox(10, pizzaSizeLabel, smallPizzaRadio, mediumPizzaRadio, largePizzaRadio, xlargePizzaRadio);
        pizzaSizeVBox.setPadding(new Insets(30));

    }

    /**
     * Method to design the pizza specialty choice option
     */
    public void preparePizzaSpecialityOption()
    {

        specialty = new ComboBox<String>();
        specialtyLabel = new Label("Pizza Type:");
        specialty.getItems().addAll("Supreme", "Meat", "Cheese", "Veggie", "Fruit", "Chocolate");
        specialtyVBox = new VBox(10, specialtyLabel, specialty);
        specialtyVBox.setAlignment(Pos.TOP_CENTER);
        specialtyVBox.setPadding(new Insets(30));

    }

    /**
     * Method to design the extra ingredient options
     */
    public void preparePizzaAddOnIngredients()
    {
        addOnLabel = new Label("Add-On Ingredients:");
        extraCheese = new CheckBox("Extra Cheese");
        extraSauce = new CheckBox("Extra Sauce");
        garlicCrust = new CheckBox("Garlic Crust");
        stuffedCrust = new CheckBox( "Stuffed Crust");
        addOnVBox = new VBox(10, addOnLabel, extraCheese, extraSauce, garlicCrust, stuffedCrust);
        addOnVBox.setPadding(new Insets(30));
    }

    /**
     * Method to design the special instructions box
     */
    public void prepareSpecialInstructions()
    {
        instructionsLabel = new Label("Special Instructions:");
        specialInstructionsArea = new TextArea();
        specialInstructionsArea.setPrefColumnCount(15);
        specialInstructionsArea.setPrefRowCount(8);
        specialInstructionsArea.setWrapText(true);

        instructionsVBox = new VBox(10, instructionsLabel,specialInstructionsArea );
        instructionsVBox.setPadding(new Insets(30));
    }

    /**
     * Method to design the spicy option lable
     */
    public void prepareSpicyOption()
    {
        spicyLabel = new Label("Sauce Spiciness Level:");
        spiciness = new Slider(0, 20, 0);
        spiciness.setOrientation(Orientation.VERTICAL);
        spiciness.setShowTickMarks(true);
        spiciness.setShowTickLabels(true);
        spiciness.setMajorTickUnit(5);
        spiciness.setMinorTickCount(1);
        spiciness.setSnapToTicks(true);

        spicyVBox = new VBox(10, spicyLabel, spiciness);
        spicyVBox.setPadding(new Insets(30));
    }

    /**
     * Method that designs the center gridpane and incorperates the other designed elements into it
     */
    public void createGridPane()
    {
        pizzaOrderGrid = new GridPane();
        pizzaOrderGrid.add(pizzaSizeVBox, 0, 0);
        pizzaOrderGrid.add(specialtyVBox, 1, 0);
        pizzaOrderGrid.add(addOnVBox, 2, 0);
        pizzaOrderGrid.add(instructionsVBox, 0, 1);
        pizzaOrderGrid.add(spicyVBox, 1, 1);
        pizzaOrderGrid.setAlignment(Pos.CENTER);
        pizzaOrderGrid.setStyle("-fx-background-color: orange");
    }

    /**
     * Method that designs the box that includes the buttons and informational area at the bottom of the interface
     */
    public void prepareBottomBox()
    {
        prepareButtons();
        infoTextArea = new TextArea("Satisfy your dragon sized hunger at Airy's Pizza Palace!");
        infoTextArea.setPrefColumnCount(40);
        infoTextArea.setPrefRowCount(5);
        infoTextArea.setEditable(false);
        infoHBox = new HBox(infoTextArea);
        infoHBox.setAlignment(Pos.CENTER);
        infoHBox.setPadding(new Insets(30));
        bottomVBox = new VBox(20, infoHBox, buttonHBox );
        bottomVBox.setStyle("-fx-background-color: red");
    }

    /**
     * Method that designs the extra options on the right side of the screen
     */
    public void prepareExtraOptions()
    {
        preparePopOption();
        prepareBreadstickOption();

        extraOptionsVBox = new VBox(popVBox, breadstickVBox);
        extraOptionsVBox.setPadding(new Insets(30));
        extraOptionsVBox.setStyle("-fx-background-color: yellow");
    }

    /**
     * Method that designs the pop options, some do not show initially until the user selects the option
     */
    public void preparePopOption()
    {

        // frame for Check Box for pop
        popCheckBox = new CheckBox("Include Pop?");
        popCheckBox.setOnAction(new CheckBoxClickHandler());

        popOptionVBox = new VBox(10, popCheckBox);

        // frame for RadioButtons for size
        popSizeLabel = new Label("Pop Size");
        smallPopRadio = new RadioButton("Small: 16 oz");
        mediumPopRadio = new RadioButton("Medium: 24 oz");
        largePopRadio = new RadioButton("Large: 32 oz");
        xlargePopRadio = new RadioButton("X-Large 42 oz");
        popSizeToggle = new ToggleGroup();
        smallPopRadio.setToggleGroup(popSizeToggle);
        mediumPopRadio.setToggleGroup(popSizeToggle);
        largePopRadio.setToggleGroup(popSizeToggle);
        xlargePopRadio.setToggleGroup(popSizeToggle);

        popSizeVBox = new VBox(10, smallPopRadio, mediumPopRadio, largePopRadio, xlargePopRadio);

        // frame for flavor choice
        popTypeLabel = new Label("Pop Flavor:");
        popTypeComboBox = new ComboBox<String>();
        popTypeComboBox.getItems().addAll("Pepsi", "Coke", "Red Pop", "Root Beer", "Vernors");

        popTypeVBox = new VBox(10, popTypeLabel, popTypeComboBox);

        //  put frames together, only show one option until user checks
        popVBox = new VBox(10, popCheckBox);
        popVBox.setPadding(new Insets(30));

    }

    /**
     * Method that designs the breadstick options, some do not show initially until the user selects the option
     */
    public void prepareBreadstickOption()
    {

        // frame for breadstick option box
        breadstickCheckBox = new CheckBox("Include Breadsticks?");
        breadstickCheckBox.setOnAction(new CheckBoxClickHandler());
        breadstickOptionVBox = new VBox (10, breadstickCheckBox);

        // frame for breadstick flavors radiobuttons
        breadstickRadioLabel = new Label("Breadstick Type");
        regularBreadsticksRadio = new RadioButton("Regular");
        cheesyBreadsticksRadio = new RadioButton( "Cheesy");
        frostedBreadsticksRadio = new RadioButton("Frosted");
        breadstickToggle = new ToggleGroup();
        regularBreadsticksRadio.setToggleGroup(breadstickToggle);
        cheesyBreadsticksRadio.setToggleGroup(breadstickToggle);
        frostedBreadsticksRadio.setToggleGroup(breadstickToggle);
        breadstickTypeVBox = new VBox(10, breadstickRadioLabel, regularBreadsticksRadio,
                                                cheesyBreadsticksRadio, frostedBreadsticksRadio);

        // connect frames for breadstick option choice, only show one option until user checks
        breadstickVBox = new VBox(10, breadstickOptionVBox);
        breadstickVBox.setPadding(new Insets(30));
    }

    /**
     * Method that makes the pop options appear on the interface
     */
    public void showPopOptions()
    {
        popVBox.getChildren().clear();
        popVBox.getChildren().addAll( popCheckBox, popSizeVBox, popTypeVBox);

    }

    /**
     * Method that makes the pop options disappear on the interface
     */
    public void hidePopOptions()
    {
        popVBox.getChildren().clear();
        popVBox.getChildren().addAll( popCheckBox);

    }

    /**
     * Method that makes the breadstick options appear on the interface
     */
    public void showBreadstickOptions()
    {
        breadstickVBox.getChildren().clear();
        breadstickVBox.getChildren().addAll( breadstickOptionVBox, breadstickTypeVBox);

    }

    /**
     * Method that makes the breadstick options disappear on the interface
     */
    public void hideBreadstickOptions()
    {
        breadstickVBox.getChildren().clear();
        breadstickVBox.getChildren().addAll( breadstickOptionVBox);

    }

    /**
     * Method that returns the selection make regarding the pizza size choice
     * @return String representing the pizza size choice selected, or "None Selected" if none selected
     */
    public String getPizzaSizeChoice()
    {
        String sizeChoice="None Selected";
        if (smallPizzaRadio.isSelected())
        {
            sizeChoice = smallPizzaRadio.getText();
        }
        else if(mediumPizzaRadio.isSelected())
        {
            sizeChoice = mediumPizzaRadio.getText();
        }
        else if(largePizzaRadio.isSelected())
        {
            sizeChoice = largePizzaRadio.getText();
        }
        else if(xlargePizzaRadio.isSelected())
        {
            sizeChoice = xlargePizzaRadio.getText();
        }

        return sizeChoice;
    }

    /**
     * Method that returns a value based on if the options selected in the interface are valid
     * The method also creates an error message based on invalid selections
     * @return boolean true if selections are valid, false if not
     */
    public boolean isSelectionsValid()
    {
        boolean isValid = true;
        errorMessage = "";

        if(specialty.getValue()==null || specialty.getValue().length()==0)
        {
            isValid = false;
            errorMessage += "Specialty pizza Style not chosen.\n";
        }
        if(serverName.getText().length()==0)
        {
            isValid = false;
            errorMessage += "Server name not entered.\n";
        }
        if(orderType.getValue() == null || orderType.getValue().length()==0)
        {
            isValid = false;
            errorMessage += "Delivery method not entered.\n";
        }
        if(getPizzaSizeChoice().equals("None Selected"))
        {
            isValid = false;
            errorMessage += "Pizza size not selected.\n";
        }
        if (popCheckBox.isSelected())
        {
            if (!smallPopRadio.isSelected() && !mediumPopRadio.isSelected() && !largePopRadio.isSelected()
                && !xlargePopRadio.isSelected())  // no pop size selected
            {
                isValid = false;
                errorMessage += "Pop size not selected.\n";
            }
            if (popTypeComboBox.getValue() == null || popTypeComboBox.getValue().length()==0)
            {
                isValid = false;
                errorMessage += "Pop flavor not selected.\n";
            }

        }
        if (breadstickCheckBox.isSelected())
        {
            if(!regularBreadsticksRadio.isSelected() && !cheesyBreadsticksRadio.isSelected()
                        && !frostedBreadsticksRadio.isSelected())       // no breadstick type selected
            {
                isValid = false;
                errorMessage += "No breadstick type selected.\n";
            }
        }

        return isValid;
    }

    /**
     * Class ButtonClickHandler handles the button click events
     */
    class ButtonClickHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles button click events
         *
         * If the submit order button is clicked, the selections are checked to see if the are valid
         * if the selections are valid, a pizza object is created and the order displayed
         * if some selections are invalid, an error message is displayed to the user
         *
         * If the clear button is clicked, the settings in the interface are cleared
         *
         * If the quit button is clicked, the program ends
         *
         * @param event ActionEvent object that contains data about a button click event
         */
        @Override
        public void handle(ActionEvent event)
        {

            if (event.getSource() == submitOrderButton)             // calculate based on data
            {
                // check if valid selections made
                if(isSelectionsValid())
                {
                    // create pizzaObject
                    pizzaObject = new PizzaOrder(specialty.getValue(),
                            serverName.getText(),
                            orderType.getValue(),
                            getPizzaSizeChoice(),
                            extraCheese.isSelected(),
                            extraSauce.isSelected(),
                            garlicCrust.isSelected(),
                            stuffedCrust.isSelected(),
                            spiciness.getValue(),
                            specialInstructionsArea.getText());

                    infoTextArea.setText(pizzaObject.toString()+"Order confirmed.");
                }
                else
                {
                    infoTextArea.setText(errorMessage +"Order not placed.");
                }

            }
            if (event.getSource() == clearOrderButton)
            {
                serverName.setText("");
                orderType.setValue("");
                // deselect pizza size radio buttons
                smallPizzaRadio.setSelected(true);
                smallPizzaRadio.setSelected(false);

                specialty.setValue("");
                extraCheese.setSelected(false);
                extraSauce.setSelected(false);
                garlicCrust.setSelected(false);
                stuffedCrust.setSelected(false);
                specialInstructionsArea.setText("");
                spiciness.setValue(0.0);
                popCheckBox.setSelected(false);
                // deselect pop size radio buttons
                smallPopRadio.setSelected(true);
                smallPopRadio.setSelected(false);

                popTypeComboBox.setValue("");
                breadstickCheckBox.setSelected(false);
                // deselect breadstick type options
                regularBreadsticksRadio.setSelected(true);
                regularBreadsticksRadio.setSelected(false);

                // remove extra pop and breadstick options if exist
                hidePopOptions();
                hideBreadstickOptions();

                infoTextArea.setText("Menu options have been cleared.");

            }
            if (event.getSource() == exitButton)
            {
                System.exit(0);
            }

        }
    }

    /**
     * class CheckBoxClickHandler addresses the extra checkbox selections
     */
    class CheckBoxClickHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles the extra checkbox click events
         *
         * If the include pop option is clicked or unclicked, the interface is updated to increase or decrease
         * the pop options
         *
         * If the include breadsticks option is clicked or unclicked, the interface is updated to increase or
         * decrease the breadsticks options
         *
         * @param event ActionEvent object that contains data about a checkbox click event
         */
        @Override
        public void handle(ActionEvent event)
        {

            if (event.getSource() == popCheckBox)             // calculate based on data
            {
                if (popCheckBox.isSelected())
                {
                    showPopOptions();
                }
                else
                {
                    hidePopOptions();
                }

            }
            if (event.getSource() == breadstickCheckBox)
            {
                if (breadstickCheckBox.isSelected())
                {
                    showBreadstickOptions();
                }
                else
                {
                    hideBreadstickOptions();
                }
            }

        }
    }

}
