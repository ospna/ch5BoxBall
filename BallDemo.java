import java.util.Random;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;

/**
 * Class BallDemo - a short demonstration showing animation of random bouncing balls with the 
 * Canvas class. The balls will bounce around inside a box.
 *
 * @author Giovanny Ospina
 * @version 10.21.2017
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private BoxBall ball;
    private ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
    private Random randomLocation = new Random();
    private Random randomColor = new Random();
    private Random randomSize = new Random();
    
   /**
     * Creates the box in which the balls will bounce within and rebound of each side.
     */ 
   public void setCanvasBox(int width, int height)
    {
        myCanvas = new Canvas("Box Bounce", width, height, Color.white);
        drawBox();
    }
    
   /**
     * Simulates a range of five to thirty random bouncing balls that move inside the box. This method randomly assigns
     * the initial speed, position and color of the ball. After assigning all features, they are then added to the array 
     * where they later will be placed at a location
     */
    
    public void boxBounce()
    {
        int colorIndex = 5;
        for(int i = 5; i < 30; i++)
        { 
            //Randomly assigns red, green, blue colors in the range of 0 to 255. 
            int r = randomColor.nextInt(255)+1;
            int g = randomColor.nextInt(255)+1;
            int b = randomColor.nextInt(255)+1;
            Random randomRgb = new Random();
            Color colorSet = new Color(r,g,b);

            // Gets the size of the rectangle to draw the rectangle within.
            Dimension size = myCanvas.getSize();
            
            int width = size.width;
            int height = size.height; 
            width -= 10;
            height -= 10;
            
            int xPosition;
            int yPosition;
            int randomXPosition =  size.width;
            int randomYPosition = size.height;
                
            //creates balls with a random diameter from 10 to 35.
            int ballSize = randomSize.nextInt(20) + 15;
                
            randomXPosition -= (10 + ballSize);
            randomYPosition -= (10 + ballSize);
            
            //Randomly assigns an x location to the ball starting at a minimum x
            //position of 5 so the ball does not start from outside the box
            xPosition = randomLocation.nextInt(randomXPosition)+ 5;
            
            //The next line randomly assigns a y location to the ball starting at a minimum y
            //position of 5 so the ball does not start from outside the box
            yPosition = randomLocation.nextInt(randomYPosition)+ 5;
            
            //Adds the balls to the balls array one at a time.
            balls.add(new BoxBall(xPosition, yPosition, ballSize, width, height, colorSet, myCanvas));
        }
 
        /*
         * While loop picks the balls from the array and redraws them at a location insde the box. Calls the move method
         * from BoxBall and redraws the rectangle.
         */ 
        
        while(true)
        {
            myCanvas.wait(50);
            for(int index = 0; index < balls.size(); index++)
            {
                BoxBall ball;
                ball = balls.get(index);
                ball.draw();
                ball.move();
            }
        }
    }
    
   /**
     * This method draws a box that receives the dimensions of the canvas and then sets the walls
     * of the rectangle to be five less than the dimensions of the canvas.
     */
   private void drawBox()
   {
        Dimension size = myCanvas.getSize();
        double x = 5;
        double y = 5;
        double widthToBeFixed = size.width;
        double heightToBeFixed = size.height;
        double width = widthToBeFixed - 10.00;
        double height = heightToBeFixed - 10.00;
        Shape rectangle = (new Rectangle2D.Double(x, y, width, height));
        myCanvas.draw(rectangle);
   }
}



