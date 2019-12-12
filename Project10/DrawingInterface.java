/************************************************************************************************
 *  This program shows drawings that the author created using the graphics objects in javaFX
 *
 *  The graphic icons creates are a traffic light, a dragon head (logo), a weather icon and
 *  a bad guy
 *
 *  It gives the user the option to view or not view the files
 *
 *  CST 183 Programming Assignment 11
 *  @author Michael Clinesmith
 ***********************************************************************************************/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class DrawingInterface extends Application
{
    // Constants for the scene size
    final double SCENE_WIDTH = 1200.0;
    final double SCENE_HEIGHT = 600.0;

    Pane[] pane;                                // for drawing objects
    GridPane drawingGridPane = new GridPane();  // contains the drawing panes
    BorderPane mainLayout = new BorderPane();   // holds gridpane and checkboxes

    // traffic light
    private final double arrowXCoords[] = {  0, 20, 30, 20, 50, 50, 20, 30, 20,  0};
    private final double arrowYCoords[] = { 10,  0,  0,  5,  5, 15, 15, 20, 20, 10};
    Polygon greenArrow, yellowArrow;
    private Rectangle[] rect;
    Circle[] circ;

    // dragon
    private final double dragonHeadXCoords[] = { 100, 95, 70, 60, 55, 45, 40, 30, 24, 16, 12,  8,  4,  2, 0, 30, 40,
            30, 0,  2,  4,  8, 12, 16, 18, 24, 30, 34, 37, 40, 45, 50, 60, 70, 80, 90, 95, 100, 105};
    private final double dragonHeadYCoords[] = {  25, 28, 28, 26, 26, 24, 22, 21, 22, 22, 20, 18, 14, 10, 4,  3,  0,
            2,  0, -6,-10,-14,-16,-16,-15,-15,-16,-18,-21,-26,-30,-32,-33,-34,-34,-33,-31,- 27, -20};
    Polygon dragonHead;
    Ellipse dragonEyeWhite, dragonEyeGreen, dragonEyeBlack;
    Arc noseHorn;
    Polygon earFin[];
    Arc headHorn;
    Arc tooth;
    Text airy;

    // weather
    Circle sun;
    Line[] sunRays;
    Circle[] cloud;
    Polygon lightning;
    Arc[] rain;

    // bad guy
    Ellipse head;
    Ellipse[] eye;
    Line[] nose;
    Ellipse hair;
    Arc mouth;

    Ellipse bubble;
    Arc bubblePart;
    Text badMessage;


    // check box objects
    CheckBox viewCheck[] = new CheckBox[4];
    HBox checkBoxHBox;


    /**
     * main method of program, used to launch graphical interface
     *
     * @param args String array - arguments are not used besides being passed to launch method
     */
    public static void main( String[] args )
    {
        launch( args );
    }

    /**
     * Method that calls the initializeScene method and creates the scene
     * @param primaryStage Stage object used to create the stage
     */
    @Override
    public void start( Stage primaryStage )
    {
        createDrawings();
        createCheckBoxes();
        setUpContainers();

        // Create a Scene and display drawing
        Scene scene = new Scene( mainLayout, SCENE_WIDTH, SCENE_HEIGHT );
        primaryStage.setScene( scene );
        primaryStage.setTitle( "Drawings" );
        primaryStage.show();
    }

    /**
     * Method that calls other methods to design the drawings
     */
    public void createDrawings()
    {
        pane = new Pane[4];
        createTrafficLight();
        createDragon();
        createWeatherIcon();
        createBadGuy();
    }

    /**
     * Method that attaches the drawings to the gridpane and attaches the gridpane and check boxes
     * to the main node
     */
    public void setUpContainers()
    {
        for (int i=0; i<pane.length; i++)                   // adds panes with drawings to GridPane
        {
            drawingGridPane.add( pane[i], i, 0 );
        }

        mainLayout.setCenter( drawingGridPane );
        mainLayout.setBottom( checkBoxHBox );
    }

    /**
     * Method creates the checkboxes that can select or deselect viewing the drawings
     */
    public void createCheckBoxes()
    {

    //        addOnLabel = new Label("Add-On Ingredients:");
            viewCheck[0] = new CheckBox("Traffic Light");
            viewCheck[1] = new CheckBox("Logo");
            viewCheck[2] = new CheckBox("Weather Icon");
            viewCheck[3] = new CheckBox( "Bad Guy");
            checkBoxHBox = new HBox(100, viewCheck);
            checkBoxHBox.setPadding(new Insets(30));

            for (int i = 0; i<4; i++)
            {
                viewCheck[i].setOnAction( new CheckBoxClickHandler() );
                viewCheck[i].setSelected( true );
            }
    }

    /**
     * Method that creates the traffic light
     */
    public void createTrafficLight()
    {
        rect = new Rectangle[5];
        circ = new Circle[5];

        // rectangles for light
        rect[0] = createRectangle( 100, 100, Color.ORANGE );
        rect[1] = createRectangle( 40, 200, Color.ORANGE );
        rect[2] = createRectangle( 160, 200, Color.ORANGE );
        rect[3] = createRectangle( 40, 300, Color.ORANGE );
        rect[4] = createRectangle( 160, 300, Color.ORANGE );

        // circles for light
        circ[0] = createCircle( 150, 150, 40, Color.RED );
        circ[1] = createCircle( 90, 250, 40, Color.BLACK );
        circ[2] = createCircle( 210, 250, 40, Color.YELLOW );
        circ[3] = createCircle( 90, 350, 40, Color.BLACK );
        circ[4] = createCircle( 210, 350, 40, Color.GREEN );

        // arrows in lights
        yellowArrow = createArrow( 60, 240, Color.YELLOW );
        greenArrow = createArrow( 60,340, Color.GREEN );

        // Add the lines to primary pane
        pane[0] = new Pane();
        pane[0].getChildren().addAll( rect);                        // add all elements of array rect
        pane[0].getChildren().addAll( circ);                        // add all elements of array circ
        pane[0].getChildren().addAll( yellowArrow, greenArrow );
        pane[0].setStyle( "-fx-background-color: lightblue;" );
        pane[0].setPrefSize( 300, 500 );

    }

    /**
     * Method takes parameters to create a 100 X 100 rectangle
     * @param xPos double: upper left corner x position for the arrow
     * @param yPos double: upper left corner y position for the arrow
     * @param fillColor Color: the color for the rectangle
     * @return  Rectangle: a rectangle object representing the data received
     */
    public Rectangle createRectangle(double xPos, double yPos, Color fillColor)
    {
        Rectangle rectangle;

        rectangle = new Rectangle( xPos, yPos, 100, 100 );
        rectangle.setFill( fillColor);
        rectangle.setStroke( Color.BLACK );

        return rectangle;
    }

    /**
     * Method takes parameters to create a circle of radius 40
     * @param xPos double: center x position for the circle
     * @param yPos double: center y position for the circle
     * @param fillColor Color: the color for the circle
     * @return  Circle: a Circle object representing the data received
     */
    public Circle createCircle(double xPos, double yPos, double radius, Color fillColor)
    {
        Circle circle;
        circle = new Circle( xPos, yPos, radius, fillColor);
        circle.setStroke( Color.BLACK );

        return circle;
    }

    /**
     * Method takes parameters to create an traffic light arrow
     * @param xPos double: upper left x position for the arrow
     * @param yPos double: upper left y position for the arrow
     * @param fillColor Color: the color for the arrow
     * @return  Polygon: a Polygon object representing the arrow
     */
    public Polygon createArrow(double xPos, double yPos, Color fillColor)
    {
        Polygon arrow= new Polygon();

        for (int i = 0; i < arrowXCoords.length; i++)
        {
            arrow.getPoints().add(arrowXCoords[i]+xPos);
            arrow.getPoints().add(arrowYCoords[i]+yPos);
        }
        arrow.setFill(fillColor);
        arrow.setStroke(Color.BLACK);

        return arrow;
    }

    /**
     * Method that facilitates the creation of the dragon head (logo)
     */
    public void createDragon()
    {
        // head background
        dragonHead = createDragonHead(100, 100);

        // eye
        dragonEyeWhite = createEye( 161, 78, 10, 4, Color.LIGHTGREY );
        dragonEyeGreen = createEye( 164, 78, 4, 4, Color.GREEN );
        dragonEyeBlack = createEye( 164, 78, 1, 4, Color.BLACK );

        // nose horn
        noseHorn = new Arc(112, 84, 4, 10, 80, 120);
        noseHorn.setType( ArcType.ROUND );
        noseHorn.setFill( Color.LIGHTGREY );
        noseHorn.setStroke( Color.BLACK );

        // ear
        createDragonEarFins();

        // top of head horn
        headHorn = new Arc(200, 80, 8, 50, 90, 90);
        headHorn.setType( ArcType.ROUND );
        headHorn.setFill( Color.LIGHTGREY );
        headHorn.setStroke( Color.BLACK );
        headHorn.setRotate( 45 );

        // tooth
        tooth = new Arc(116, 100, 6, 10, 180, 80);
        tooth.setType( ArcType.ROUND );
        tooth.setFill( Color.LIGHTGREY );
        tooth.setStroke( Color.BLACK );

        airy = new Text(120, 160, "Airy");
        airy.setFont( new Font(32) );
        airy.setFill( Color.ORANGERED );
        airy.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));

        pane[1] = new Pane();

        pane[1].getChildren().addAll( dragonHead, dragonEyeWhite, dragonEyeGreen, dragonEyeBlack, noseHorn );
        pane[1].getChildren().addAll( earFin );
        pane[1].getChildren().addAll( headHorn, tooth, airy );
        pane[1].setStyle( "-fx-background-color: lightblue;" );
        pane[1].setPrefSize( 300, 500 );

    }

    /**
     * Method that creates the shape of a dragon head
     * @param xPos double: x coordinate to use to build the head
     * @param yPos double: y coordinate to use to build the head
     * @return Polygon: a polygon object shaped like a dragon head
     */
    public Polygon createDragonHead(double xPos, double yPos)
    {
        Polygon headOutline = new Polygon();

        for (int i = 0; i < dragonHeadXCoords.length; i++)          // use stored coordinates to create the head shape
        {
            headOutline.getPoints().add(dragonHeadXCoords[i]+xPos);
            headOutline.getPoints().add(dragonHeadYCoords[i]+yPos);
        }
        headOutline.setFill(Color.ORANGE);
        headOutline.setStroke(Color.BLACK);

        return headOutline;

    }

    /**
     * Method to create the parts of an eye
     * @param xPos  double: x coordinate for the center of the ellipse for the eye
     * @param yPos  double: y coordinate for the center of the ellipse for the eye
     * @param width double: the length of the ellipse in the x direction
     * @param height double: the length of the ellipse in the y direction
     * @param fillColor Color: the color for the eye part
     * @return Ellipse: an Ellipse for the eye part
     */
    public Ellipse createEye(double xPos, double yPos, double width, double height, Color fillColor)
    {
        Ellipse eye;
        eye = new Ellipse( xPos, yPos, width, height);
        eye.setFill( fillColor );
        eye.setStroke( Color.BLACK );

        return eye;
    }

    /**
     * Method that creates the ear fins for the dragon
     */
    public void createDragonEarFins()
    {

        // coordinates used to make ear fins
        double fin0XCoords[] = {  185, 207, 210, 217, 187};
        double fin0YCoords[] = {   76,  58,  68,  73,  88};

        double fin1XCoords[] = {  187, 217, 214, 217, 190};
        double fin1YCoords[] = {   88,  73,  86,  98, 100};

        double fin2XCoords[] = {  190, 217, 212, 215, 188};
        double fin2YCoords[] = {  100,  98, 107, 120, 112};

        double fin3XCoords[] = {  188, 215, 208, 206, 180};
        double fin3YCoords[] = {  112, 120, 128, 140, 122};

        earFin = new Polygon[4];        // 4 parts to fin

        earFin[0] = new Polygon();
        for (int i = 0; i < fin0XCoords.length; i++)
        {
            earFin[0].getPoints().add( fin0XCoords[i] );
            earFin[0].getPoints().add( fin0YCoords[i] );
        }

        earFin[1] = new Polygon();
        for (int i = 0; i < fin1XCoords.length; i++)
        {
            earFin[1].getPoints().add( fin1XCoords[i] );
            earFin[1].getPoints().add( fin1YCoords[i] );
        }

        earFin[2] = new Polygon();
        for (int i = 0; i < fin2XCoords.length; i++)
        {
            earFin[2].getPoints().add( fin2XCoords[i] );
            earFin[2].getPoints().add( fin2YCoords[i] );
        }

        earFin[3] = new Polygon();
        for (int i = 0; i < fin3XCoords.length; i++)
        {
            earFin[3].getPoints().add( fin3XCoords[i] );
            earFin[3].getPoints().add( fin3YCoords[i] );
        }

        for (int j = 0; j<4; j++)
        {
            earFin[j].setFill( Color.ORANGE );
            earFin[j].setStroke( Color.RED );
        }

    }

    /**
     * Method that creates the weather icons for the weather icon drawing
     */
    public void createWeatherIcon()
    {
        pane[2] = new Pane();

        // sun
        sun = createCircle( 150, 150, 40, Color.YELLOW );

        // rays of sun
        sunRays = new Line[4];
        sunRays[0] = new Line(150, 100, 150, 50);
        sunRays[1] = new Line(100, 150, 50, 150);
        sunRays[2] = new Line( 110, 110, 70, 70);
        sunRays[3] = new Line( 190, 110, 230, 70);

        for (int i = 0; i<sunRays.length; i++)               // adjust sun rays
        {
            sunRays[i].setFill( Color.YELLOW );
            sunRays[i].setStrokeWidth( 6 );
            sunRays[i].setStroke( Color.YELLOW );
        }

        // cloud
        cloud = new Circle[5];

        cloud[0] = new Circle( 140, 200, 30, Color.GRAY );
        cloud[1] = new Circle( 180, 190, 30, Color.GRAY );
        cloud[2] = new Circle( 130, 220, 40, Color.GRAY );
        cloud[3] = new Circle( 185, 230, 45, Color.GRAY );
        cloud[4] = new Circle( 200, 220, 50, Color.GRAY );

        // rain
        rain = new Arc[3];

        rain[0] = new Arc(200, 283, 3, 30, 260, 280);
        rain[1] = new Arc(220, 279, 3, 30, 260, 280);
        rain[2] = new Arc(180, 290, 3, 30, 260, 280);

        for (int i = 0; i<rain.length; i++)               // adjust rain
        {
            rain[i].setFill( Color.BLUE );
            rain[i].setStroke( Color.BLUE );
        }

        // lightning
        lightning = createLightning( 130, 260 );

        // add items to pane
        pane[2].getChildren().addAll( sun );
        pane[2].getChildren().addAll( sunRays );
        pane[2].getChildren().addAll( cloud );
        pane[2].getChildren().addAll( rain );
        pane[2].getChildren().addAll( lightning );
        pane[2].setStyle( "-fx-background-color: lightblue;" );
        pane[2].setPrefSize( 300, 500 );

    }

    /**
     * Method that creates a bolt of lightning
     * @param xPos double: adjusts the x position of the lightning
     * @param yPos double: adjusts the y position of the lightning
     * @return Polygon: a polygon shaped like a lightning bolt
     */
    public Polygon createLightning(double xPos, double yPos)
    {
        Polygon ning = new Polygon();

        double ningXCoords[] = {  0, -3, -1, -6, -4, -10,  1, -1, 10, 9, 13};
        double ningYCoords[] = {  0, 10,  8, 22, 21,  40, 21, 22,  7, 8, 0};

        for (int i = 0; i < ningXCoords.length; i++)
        {
            ning.getPoints().add( ningXCoords[i] + xPos);
            ning.getPoints().add( ningYCoords[i] + yPos);
        }
        ning.setFill(Color.YELLOW);
        ning.setStroke( Color.ORANGE );

        return ning;
    }

    /**
     * Method to create the drawing of a bad guy
     */
    public void createBadGuy()
    {

        pane[3] = new Pane();

        // creates hair
        hair = createEye( 150, 140, 55, 60, Color.BLACK );

        // creates head shape
        head = createEye( 150, 150, 50, 60, Color.PEACHPUFF );

        eye = new Ellipse[6];
        eye[0] = createEye( 130, 125, 10, 8, Color.WHITE );
        eye[1] = createEye( 132, 123, 6, 6, Color.BROWN );
        eye[2] = createEye( 132, 123, 4, 4, Color.BLACK );
        eye[3] = createEye( 170, 125, 10, 8, Color.WHITE );
        eye[4] = createEye( 172, 126, 6, 6, Color.BROWN );
        eye[5] = createEye( 172, 126, 4, 4, Color.BLACK );

        // creates nose
        nose = new Line[2];
        nose[0] = new Line(150, 125, 140, 150);
        nose[1] = new Line(140, 150, 155, 150);

        // creates mouth
        mouth = new Arc(152, 185, 15, 25, 30, 120);
        mouth.setType( ArcType.CHORD );
        mouth.setStroke( Color.RED );

        // creates bubble message
        bubblePart = new Arc(90, 100, 40, 40, 70, 50);
        bubblePart.setType( ArcType.ROUND );
        bubblePart.setFill( Color.WHITE );
        bubble = createEye( 100, 50, 50, 25, Color.WHITE );
        badMessage = new Text(60, 50, "Recall the Clerk!");

        // puts parts of bad guy together
        pane[3].getChildren().addAll( bubblePart, bubble, badMessage, hair);
        pane[3].getChildren().addAll( head);
        pane[3].getChildren().addAll( eye );
        pane[3].getChildren().addAll( nose);
        pane[3].getChildren().addAll( mouth );

        pane[3].setStyle( "-fx-background-color: lightblue;" );
        pane[3].setPrefSize( 300, 500 );
    }


    /**
     * class CheckBoxClickHandler addresses the extra checkbox selections
     */
    class CheckBoxClickHandler implements EventHandler<ActionEvent>
    {
        /**
         * This method handles the checkbox click events
         *
         * It passes through a loop to see which check box received the event.If the include pop option is clicked
         * or unclicked, then will add or remove the drawing from the gridpane
         *
         * @param event ActionEvent object that contains data about a checkbox click event
         */
        @Override
        public void handle(ActionEvent event)
        {
            for (int i=0; i<4; i++)
            {
                if (event.getSource() == viewCheck[i])             // calculate based on data
                {
                    if (viewCheck[i].isSelected())
                    {
                        drawingGridPane.add( pane[i], i, 0 );
                    } else
                    {
                        drawingGridPane.getChildren().remove( pane[i] );
                    }

                }
            }

        }
    }

}
