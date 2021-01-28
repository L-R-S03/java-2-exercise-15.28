/*
 * Project: Java 2 Programming exercise 15.28
 * Name: Lauren Smith
 * Date: 1/13/2021 
 * Description: Makes an animated fan that can go forward,reverse and pause 
 */
package pkg1528;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //makes an hbox to hold all the buttons
        HBox hbox=new HBox(5); 
        //makes the three buttons for all the fan settings 
        Button btnPause=new Button("Pause"); 
        Button btnResume=new Button("Resume"); 
        Button btnReverse=new Button("Reverse"); 
        //sets the hbox to align children in the center 
        hbox.setAlignment(Pos.CENTER); 
        //adds the buttons to the hbox 
        hbox.getChildren().addAll(btnPause,btnResume,btnReverse); 
        
        //makes a BorderPane and places the hbox with buttons at the bottom
        BorderPane pane=new BorderPane(); 
        pane.setBottom(hbox); 
        
        //makes a FanPane and adds it to the main pane 
        FanPane fan=new FanPane(); 
        pane.getChildren().add(fan); 
        
        //makes a timeline animation that moves every 25  milliseconds using 
        //the move method from the FanPane class
        Timeline animation=new Timeline(
            new KeyFrame(Duration.millis(25), e-> fan.move()));
        //runs the animation indefinitely 
        animation.setCycleCount(Timeline.INDEFINITE); 
        animation.play();
        
        //Lamda's for each button set to pause and play through animation 
        btnPause.setOnAction(e-> animation.pause()); 
        btnResume.setOnAction(e-> animation.play()); 
        //reverse Lamda uses reverse method in fan class
        btnReverse.setOnAction(e-> fan.reverse()); 
        
        Scene scene = new Scene(pane, 300, 250);
        
        primaryStage.setTitle("Running Fan");
        primaryStage.setScene(scene);
        primaryStage.show();
    } 


    public static void main(String[] args) {
        launch(args);
    }
    
} 

class FanPane extends Pane 
{
    //declares variables for width, height and radius
    private double w=200; 
    private double h=200; 
    //radius is set dependent on width and height specificed so it fits in frame
    private double radius=Math.min(w,h)*0.45;
    //array of 4 arcs, one for each fan blade 
    private Arc arc[]=new Arc[4];
    //sets an angle for the arc to start at so the fan blades are equally spread
    private double startAngle=30; 
    //makes the circle by dividing width and height by 2 and applying radius
    private Circle circle=new Circle(w/2,h/2,radius); 
    
    public FanPane() 
    { 
        //specifies color and fill of circle for the fan
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        circle.setFill(javafx.scene.paint.Color.WHITE); 
        //adds the circle to the fan pane 
        getChildren().add(circle);
        
        //loop for each of the blades  
        for(int i=0; i<4; i++) 
        { 
            //makes a new arc in the array and increments the start angle by i 
            //multiplied by 90 degrees so they are evenly spaced out
            arc[i]=new Arc(w/2,h/2,radius*0.9,radius*0.9,startAngle+i*90,35); 
            //makes the blade red
            arc[i].setFill(javafx.scene.paint.Color.RED); 
            //sets the arc to be the round type
            arc[i].setType(ArcType.ROUND); 
            //adds the arc to the pane 
            getChildren().add(arc[i]); 
            
        } 
    }
    //method to reset all pane values 
    public void setValues() 
    {
        //sets the radius and centers the same as above 
        radius=Math.min(w,h)*0.45;
        circle.setRadius(radius); 
        circle.setCenterX(w/2); 
        circle.setCenterY(h/2); 
        
        //loops for each blade again
        for(int i=0; i<4; i++) 
        {
            //sets the radius, centers and angle same as above 
            arc[i].setRadiusX(radius*0.9); 
            arc[i].setRadiusY(radius*0.9); 
            arc[i].setCenterX(w/2); 
            arc[i].setCenterY(h/2); 
            arc[i].setStartAngle(startAngle+i*90); 
        }
    } 
    
    
    //setters for w and h 
     public void setW(double w) 
     {
         this.w=w; 
     } 
     
     public void setH(double h) 
     {
         this.h=h; 
     } 
     
     //increment to see how far to move the fan for animation 
     private double increment=5; 
     public void move() 
     {
         //increments the startAngle by increment to make it move
         setStartAngle(startAngle+increment); 
         //runs setValues so everything else updates as the fan moves 
         setValues(); 
     } 
     
     //reverses the fan by multiplying the increment by -1
     public void reverse() 
     {
         increment=-increment; 
     }
     //setter for start angle
     public void setStartAngle(double angle ) 
     {
         this.startAngle=angle; 
     } 
     
    
}


