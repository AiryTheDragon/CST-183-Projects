/************************************************************************************************
 *  This program simulates a weather risk assessment program and demonstrates using a
 *  graphical interface to test the functionality of the UpperAirObject class
 *
 *  The user is to enter data representing temperature and dew pressure values at multiple
 *  air pressures and after clicking the calculate button, a report is generated and some
 *  weather icons displayed
 *
 *
 *
 *  CST 183 Programming Assignment 5
 *  @author Michael Clinesmith
 ***********************************************************************************************/

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class WeatherInterface extends Application
{
   /*--------------------------------------------------------------------------------
   * Object declarations
   *
   * Note: I am unsure if putting all of these objects as private global variables is
   * good or standard programming practice, however I do not know how to better organize
   * the program to set up all the objects otherwise
   --------------------------------------------------------------------------------*/
   // graphical interface objects
   private Label T850, T700, T500, Td850, Td700, Instructions, TempLabel, DewLabel, ForcastLabel;
   private Label TTIndexLabel, TTIndex, KIndexLabel, KIndex, TTWarning, KWarning, RiskLabel;
   private Label ImageCredits, ProgramTitle, DesignerCredits;
   private TextField T850TF, T700TF, T500TF, Td850TF, Td700TF;
   private Button CalculateButton, ClearButton, QuitButton;
   private TextArea ForcastArea;

   private Image  sunnyImage, sunny1Image, partlySunnyImage, partlySunny1Image, mostlyCloudyImage,
                  mostlyCloudy1Image, drizzleImage, drizzle1Image, rainImage,
                  rain1Image, lightningImage, lightning1Image, tornadoImage, tornado1Image;
   private ImageView sunnyIView, sunny1IView, partlySunnyIView, partlySunny1IView, mostlyCloudyIView,
                     mostlyCloudy1IView, drizzleIView, drizzle1IView, rainIView,
                     rain1IView, lightningIView, lightning1IView, tornadoIView, tornado1IView;

   // storage containers to set up graphical interface
   private HBox CreditsBox, PartialForcastBox;
   private HBox op0Box, op1Box, op2Box, op3Box, op4Box, op5Box, op6Box;
   private VBox LeftForcastBox, RightForcastBox, ForcastBox, op1VBox;

   private UpperAirData airData;                            // object to hold weather data

   final private String INTRO_MESSAGE = "Enter temperature values into fields then click the " +
           "calculate button to generate a report.";

   private FontWeight   FontWt = FontWeight.BOLD;           // used to adjust text settings
   private FontPosture  FontPo = FontPosture.REGULAR;

   /**
    * main method of program, used to launch graphical interface
    * @param args String array - arguments are not used besides being passed to launch method
    */
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }

    /**
     * Method to start the graphical interface
     * @param primaryStage Stage object used to show graphical interface
     */
   @Override
   public void start(Stage primaryStage)
   {
      initializeScene();

      // Set up overall scene
      Scene scene = new Scene(op1VBox, 1000, 700);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Weather Forcaster");
      primaryStage.show();
      
    }

    /**
     * Method that calls other methods to initialize objects for display on the scene
     */
    public void initializeScene()
    {
        // call methods to create scene
        loadImages();
        setLabels();
        createTextAreas();
        createButtons();
        createForcastBox();
        createSceneContainers();
    }

    /**
     * Method to create objects for images
     */
   public void loadImages()
   {
      // load images
      sunnyImage = new Image("file:sunny.png");
      sunny1Image = new Image("file:sunny.png");
      partlySunnyImage = new Image("file:partlySunny.png");
      partlySunny1Image = new Image("file:partlySunny.png");
      mostlyCloudyImage = new Image("file:mostlyCloudy.png");
      mostlyCloudy1Image = new Image("file:mostlyCloudy.png");
      drizzleImage = new Image("file:drizzle.png");
      drizzle1Image = new Image("file:drizzle.png");
      rainImage = new Image("file:rain.png");
      rain1Image = new Image("file:rain.png");
      lightningImage = new Image("file:lightning.png");
      lightning1Image = new Image("file:lightning.png");
      tornadoImage = new Image("file:tornado.png");
      tornado1Image = new Image("file:tornado.png");

      // create ImageView objects for display
      sunnyIView = new ImageView(sunnyImage);
      sunny1IView = new ImageView(sunny1Image);
      partlySunnyIView = new ImageView(partlySunnyImage);
      partlySunny1IView = new ImageView(partlySunny1Image);
      mostlyCloudyIView = new ImageView(mostlyCloudyImage);
      mostlyCloudy1IView = new ImageView(mostlyCloudy1Image);
      drizzleIView = new ImageView(drizzleImage);
      drizzle1IView = new ImageView(drizzle1Image);
      rainIView = new ImageView(rainImage);
      rain1IView = new ImageView(rain1Image);
      lightningIView = new ImageView(lightningImage);
      lightning1IView = new ImageView(lightning1Image);
      tornadoIView = new ImageView(tornadoImage);
      tornado1IView = new ImageView(tornado1Image);
   }

    /**
     * Method to create and initialize label objects
     */
   public void setLabels()
   {
      T850 = new Label("T850:");
      T700 = new Label("T700:");
      T500 = new Label("T500:");
      Td850 = new Label("Td850:");
      Td700 = new Label("Td700:");
      Instructions = new Label("Enter values for Temperatures in degrees C");
      TempLabel = new Label( "Pressure Level Temperatures: ");
      DewLabel = new Label( "Pressure Level Dew Points: ");
      ForcastLabel =  new Label("Projected Weather Forcast Below:");
      TTIndexLabel = new Label( "Total Totals Index: ");
      KIndexLabel = new Label("K-Index: ");
      TTIndex = new Label("42.0");
      KIndex = new Label( "19.0");
      TTWarning = new Label( "Thunderstorms Unlikely");
      KWarning = new Label("Thunderstorms Unlikely");
      RiskLabel = new Label("Severe Weather Risk: ");
      ImageCredits = new Label("HTC Sense5 Icons credited to Jesse Penico");
      DesignerCredits = new Label( "designed by Michael Clinesmith");
      ProgramTitle = new Label("Weather Risk Assessment Program");

      // adjust label size and style
      ForcastLabel.setFont(Font.font("Arial", FontWt, FontPo, 20));
      ProgramTitle.setFont(Font.font("Arial", FontWt, FontPo, 20));

   }

    /**
     * Method to create and initialize text fields and areas
     */
   public void createTextAreas()
   {
      T850TF = new TextField();
      T700TF = new TextField();
      T500TF = new TextField();
      Td850TF = new TextField();
      Td700TF = new TextField();
      ForcastArea = new TextArea(INTRO_MESSAGE);
      ForcastArea.setWrapText(true);                // wraps text instead of scrolling

      // adjust size of text areas
      T850TF.setPrefColumnCount(4);
      T700TF.setPrefColumnCount(4);
      T500TF.setPrefColumnCount(4);
      Td850TF.setPrefColumnCount(4);
      Td700TF.setPrefColumnCount(4);
   }

    /**
     * Method to create buttons
     */
   public void createButtons()
   {
      CalculateButton = new Button( "Calculate");
      ClearButton = new Button( "Clear");
      QuitButton = new Button( "Quit");

      // set buttons to use same click handler
      CalculateButton.setOnAction(new ButtonClickHandler());
      ClearButton.setOnAction(new ButtonClickHandler());
      QuitButton.setOnAction(new ButtonClickHandler());

   }

    /**
     * Method that creates and organizes the ForcastBox that displays the data report
     */
   public void createForcastBox()
   {
      LeftForcastBox = new VBox();
      RightForcastBox = new VBox();
      CreditsBox = new HBox(ImageCredits);
      CreditsBox.setAlignment(Pos.CENTER);
      PartialForcastBox = new HBox(10, LeftForcastBox, ForcastArea, RightForcastBox);
      PartialForcastBox.setAlignment(Pos.CENTER);
      ForcastBox = new VBox(10, PartialForcastBox, CreditsBox);
   }

    /**
     * Method to create the containers to attach to the main node, op1VBox
     */
   public void createSceneContainers()
   {
      //  put together horizontal containers for main node
      op0Box = new HBox(10, ProgramTitle);
      op1Box = new HBox(10, DesignerCredits);
      op2Box = new HBox(10, TempLabel, T850, T850TF, T700, T700TF, T500, T500TF);
      op3Box = new HBox(10, DewLabel, Td850, Td850TF, Td700, Td700TF);
      op4Box = new HBox(10, Instructions );
      op5Box = new HBox(10, CalculateButton, ClearButton, QuitButton);
      op6Box = new HBox(10, ForcastLabel);

      // center containers
      op0Box.setAlignment(Pos.CENTER);
      op1Box.setAlignment(Pos.CENTER);
      op2Box.setAlignment(Pos.CENTER);
      op3Box.setAlignment(Pos.CENTER);
      op4Box.setAlignment(Pos.CENTER);
      op5Box.setAlignment(Pos.CENTER);
      op6Box.setAlignment(Pos.CENTER);

      // put together main node
      op1VBox = new VBox(10, op0Box, op1Box, op2Box, op3Box, op4Box, op5Box, op6Box, ForcastBox);
   }

    /**
     * class created to handle button clicks
     */
    class ButtonClickHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles button click events
         *
         * If the calculate button is clicked, the data in the textfields is stored into a
         * UpperAirData object and a report is generated which includes updating the side boxes
         * to contain weather icons
         *
         * If the clear button is clicked, the field data is cleared and forcast box is reset
         *
         * If the quit button is clicked, the program ends
         *
         * @param event ActionEvent object that contains data about a button click event
         */
        @Override
        public void handle(ActionEvent event)
        {

            if (event.getSource() == CalculateButton)             // calculate based on data
            {
                int int850, int700, int500, int850d, int700d;
                int intTTIndex, intKIndex;

                // get textfield values for weather object

                int850 = Integer.parseInt(T850TF.getText());
                int700 = Integer.parseInt(T700TF.getText());
                int500 = Integer.parseInt(T500TF.getText());
                int850d = Integer.parseInt(Td850TF.getText());
                int700d = Integer.parseInt(Td700TF.getText());

                // create object
                airData = new UpperAirData(int850, int700, int500, int850d, int700d);

                // perform actions based on data

                ForcastArea.setText(airData.toString());
                if (airData.isValid())        // if valid data
                {
                    intTTIndex = airData.calculateTTIndex();
                    intKIndex = airData.calculateKIndex();

                    LeftForcastBox.getChildren().clear();
                    RightForcastBox.getChildren().clear();

                    // left box icons for TT index value

                    if (intTTIndex < 44)
                    {
                        LeftForcastBox.getChildren().addAll(sunnyIView);

                    } else if (intTTIndex < 46)
                    {
                        LeftForcastBox.getChildren().addAll(partlySunnyIView, drizzleIView);
                    } else if (intTTIndex < 48)
                    {
                        LeftForcastBox.getChildren().addAll(mostlyCloudyIView, rainIView, lightningIView);
                    } else if (intTTIndex < 50)
                    {
                        LeftForcastBox.getChildren().addAll(rainIView, lightningIView);
                    } else
                    {
                        LeftForcastBox.getChildren().addAll(rainIView, lightningIView, tornadoIView);
                    }

                    // right box icons for K-index value

                    if (intKIndex < 20)
                    {
                        RightForcastBox.getChildren().addAll(sunny1IView);
                    } else if (intKIndex < 26)
                    {
                        RightForcastBox.getChildren().addAll(partlySunny1IView, drizzle1IView);
                    } else if (intKIndex < 31)
                    {
                        RightForcastBox.getChildren().addAll(mostlyCloudy1IView, rain1IView, lightning1IView);

                    } else if (intKIndex < 36)
                    {
                        RightForcastBox.getChildren().addAll(rain1IView, lightning1IView);
                    } else
                    {
                        RightForcastBox.getChildren().addAll(rain1IView, lightning1IView, tornado1IView);

                    }
                }
                else          // clear the side box icons since not valid data
                {
                    LeftForcastBox.getChildren().clear();
                    RightForcastBox.getChildren().clear();
                }

                LeftForcastBox.setAlignment(Pos.CENTER);
                RightForcastBox.setAlignment(Pos.CENTER);

            } else if (event.getSource() == ClearButton)      // clear the text and icons
            {
                T850TF.setText("");
                T700TF.setText("");
                T500TF.setText("");
                Td850TF.setText("");
                Td700TF.setText("");
                ForcastArea.setText(INTRO_MESSAGE);

                LeftForcastBox.getChildren().clear();
                RightForcastBox.getChildren().clear();

                LeftForcastBox.setAlignment(Pos.CENTER);
                RightForcastBox.setAlignment(Pos.CENTER);

            } else if (event.getSource() == QuitButton)       // exit program
            {
                System.exit(0);
            }
        }
    }
}
