import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.awt.geom.Rectangle2D;

/**
 * Class BoxBall - a graphical ball that has the ability to move at a constant rate around inside the initiated "box".
 * The balls also bounce off of the "walls", which are defined by the values passed into the BoxBall Constructor, so that
 * the ball always stays inside. 
 * 
 * @author Giovanny Ospina
 * @version 10.21.2017
 */

public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private Canvas canvas;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int bottomWall;          // y position of bottom Wall
    private final int topWall;             // y position of top Wall
    private final int leftWall;            // x position of left Wall
    private final int rightWall;           // x position of right Wall
    private Random ballSpeed = new Random();
    private Random speedNegOrPos = new Random();
    private boolean negative;
    private boolean positive;
    private int ySpeed;               // initial downward speed
    private int xSpeed;                // initial horizontal speed
    
    /**
     * Constructor for objects of class BoxBall
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter of the ball
     * @param width  the width of the box
     * @param height  the height of the box
     * @param ballColor  the color of the ball
     * @param drawingCanvas  the canvas that'll draw this ball
     */

    public BoxBall(int xPos, int yPos, int ballDiameter, int width, int height,
                        Color ballColor, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        canvas = drawingCanvas;
        leftWall = 5;
        rightWall = width;
        topWall = 5;
        bottomWall = height;
        negative = false;
        positive = false;
        randomSpeed();
    }

    /**
     * Draw the ball at its current position onto the canvas.
     **/

    public void draw()

    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }  
    
    /**
     * Randomly generates a speed from -7 to 7 on the x and y axis, not including 0 so that the
     * balls do not have to have the same speed or direction.
     */
    private void randomSpeed()
    {
        int speed;
        int posNeg;
        int finished=0;
        
        while (finished < 2)
        {
            //Assign positive or negative to x.
             if (finished == 0)
             {
                posNeg = speedNegOrPos.nextInt(2);
                speed = ballSpeed.nextInt(7)+1;
                if (posNeg == 1)
                {
                    negative=true;
                    xSpeed += (-speed);
                    finished++;
                }
                else
                {
                    positive = true;
                    xSpeed += speed;
                    finished++;
                }   
            }  
            //Assign positive or negative to y.
             if (finished == 1)
             {
                posNeg = speedNegOrPos.nextInt(2);
                speed = ballSpeed.nextInt(7)+1;
                if (posNeg == 1)
                {
                    negative = true;
                    ySpeed += (-speed);
                    finished++;
                }
                else
                {
                    positive = true;
                    ySpeed += speed;
                    finished++;
                }   
            }
        }
    }
    
    /**
     * Erase the ball at its current position.
     **/

    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }  
    
    /**
     * Move the balls according to their position and speed, then redraw.
     **/

    public void move()
    {
        // remove from canvas at the current position
        erase();
        
        // compute new position
        //ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;

        // Checks each wall
        if (xPosition < leftWall ) 
        {
            xSpeed = -xSpeed;
        }

        if (xPosition > rightWall - diameter ) 
        {
            xSpeed = -xSpeed;
        }

        if (yPosition < topWall ) 
        {
            ySpeed = -ySpeed;
        }

        if (yPosition > bottomWall - diameter ) 
        {
            ySpeed = -ySpeed;
        }

        // draw the ball at its new position
        draw();
        
        // draw the box so that it does not get washed away
        drawBox();
    }    

    /**
     * return the horizontal position of the ball
     */
    
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of the ball
     */

    public int getYPosition()
    {
        return yPosition;
    }
    
    /**
     * Method to redraw a rectangle after ball movements are drawn and the rectangle gets chipped away at. 
     * This method is called from the move method.
     */
     private void drawBox()
    {
        Shape rectangle = (new Rectangle2D.Double(leftWall, topWall, rightWall, bottomWall));
        canvas.draw(rectangle);
    }
}