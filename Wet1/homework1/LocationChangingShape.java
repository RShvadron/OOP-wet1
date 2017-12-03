package homework1;

import java.awt.*;
import java.util.Random;

import homework1.Shape;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {
	
	protected int velocityX;
	protected int velocityY;

	// Abstraction Function:
    //    represents a shape with location = (x,y) (point on Two-dimensional space) at this.location, and a color at this.color,   
    //    Horizontal velocity at this.velocityX and vertical velocity at this.velocityY 

    // Representation Invariant:
    //   location != NULL, color != NULL 
	//   horizontal and vertical velocities are integers between -5 to 5 but NOT 0

	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert (location != null && color !=null ): "location or color or both is null";
    	assert (velocityX <= 5 && velocityX >= -5 && velocityX != 0) : " velocityX has violated the represntation invariant";
    	assert (velocityY <= 5 && velocityY >= -5 && velocityY != 0) : " velocityY has violated the represntation invariant";
    }
	
	
    /**
     * @effects Initializes this with a a given location and color. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationChangingShape(Point location, Color color) {
    	super(location,color);
    	Random rand = new Random();
    	velocityX = rand.nextInt(5) + 1;
    	velocityY = rand.nextInt(5) + 1;
        checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
    	checkRep();
        return velocityX;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
    	checkRep();
    	return velocityY;
    }


    /**
	 * @requires -5 <= velocityX <= 5 && -5 <= velocityY <= 5
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     *          vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
    	checkRep();
    	this.velocityX = velocityX;
    	this.velocityY = velocityY;
    	checkRep();
    }
    
    
    /**
     * 
     * @return a new Rectangle with location = this.location + (this.velocityX,this.velocityY) and the same dimension as this
     *          
     */
    protected Rectangle getNewRectangle() {
    	int newXcordinate = (int)this.getLocation().getX() + this.velocityX;
        int newYcordinate = (int)this.getLocation().getY() + this.velocityY;
        Point newLocation = new Point(newXcordinate,newYcordinate);
        Rectangle newRectangle = new Rectangle(newLocation,this.getBounds().getSize());
        return newRectangle;
    }


    /**
     * @modifies this
     * @effects Let p = location
     *              v = (vx, vy) = velocity
     *              r = the bounding rectangle of this
     *          If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     *              If adding v to p would move r horizontally farther away
     *              from the center of bound,
     *                  vx = -vx
     *              If adding v to p would move r vertically farther away
     *              from the center of bound,
     *                  vy = -vy
     *          }
     *          p = p + v
     */
    public void step(Rectangle bound) {
        checkRep();
        Rectangle boudingRectangle = this.getBounds();
        Rectangle newBoundingRectangle = getNewRectangle();
        if(boudingRectangle.contains(bound) == false || newBoundingRectangle.contains(bound) == false ) {  // part of r is outside bound or 
        	// r is within bound but adding v to p would bring part of r outside bound
            if( (newBoundingRectangle.getX() > bound.getCenterX() && bound.getCenterX() > 0) || 
            		newBoundingRectangle.getX() < bound.getCenterX() && bound.getCenterX() < 0) {
            	velocityX = -velocityX;
            }
            if( (newBoundingRectangle.getX() > bound.getCenterX() && bound.getCenterX() > 0) || 
            		newBoundingRectangle.getX() < bound.getCenterX() && bound.getCenterX() < 0) {
            	velocityY = -velocityY;
            }
        }
        else { //  r is inside bound
        	setLocation(newBoundingRectangle.getLocation());
        }
        checkRep();
    }
    
    
    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
    	checkRep();
    	LocationChangingShape cloneObject = (LocationChangingShape)super.clone();
    	cloneObject.velocityX = this.velocityX; // primitive type --> shallow copy
    	cloneObject.velocityY = this.velocityY; // primitive type --> shallow copy
    	checkRep();
        return cloneObject;
    }
}
