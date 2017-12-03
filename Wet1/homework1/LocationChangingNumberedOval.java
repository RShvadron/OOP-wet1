package homework1;

import java.awt.*;


/**
 * A LocationChangingNumberedOval is an Oval that can change its location using its step()
 * method. A LocationChangingNumberedOval has a velocity property that determines the speed
 * of location changing.LocationChangingNumberedOval has in its center a number witch represents 
 * the numerical order of this among all other Oval that was created.
 * Thus, a typical LocationChangingOval consists of the following set of
 * properties: {location, color, shape, size, velocity, number}
 */
public class LocationChangingNumberedOval extends LocationChangingOval {
	
	private int number;
	private static int count = 0;
	
	
	// Abstraction Function:
    //    represents a shape with location = (x,y) (point on Two-dimensional space) at this.location, color at this.color,  
	//    size = width*height at this.size, Horizontal velocity at this.velocityX, vertical velocity at this.velocityY 
	//    and a number at this.number

    // Representation Invariant:
    //    location != NULL, color != NULL 
    //    location.width && location.height > 0 
	//    size width > 0 && size height > 0 
	//    number >= 1 
	//    horizontal and vertical velocities are integers between -5 to 5 but NOT 0

	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert (location != null && color !=null ): "location or color or both is null";
    	assert (size.height > 0 && size.width > 0) : "height or width of Oval are negative";
    	assert (velocityX <= 5 && velocityX >= -5 && velocityX != 0) : " velocityX has violated the represntation invariant";
    	assert (velocityY <= 5 && velocityY >= -5 && velocityY != 0) : " velocityY has violated the represntation invariant";
    	assert (number >= 1) : "Number of Oval is less then 1";
    }
	
	
    /**
     * @effects Initializes this with a a given location, color and dimension.
     *          Each of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationChangingNumberedOval(Point location, Color color,Dimension dim) {
    	super(location,color,dim);
    	number = ++count;
    	checkRep();
    }
    
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g) {
    	checkRep();
    	g.setColor(color);
    	g.fillOval(location.x,location.y,size.width,size.height);
    	g.drawString(Integer.toString(number), (int)size.getCenterX(), (int)size.getCenterY());
    	checkRep();
    }
    
    
    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
    	checkRep();
    	LocationChangingNumberedOval cloneObject = (LocationChangingNumberedOval)super.clone();
    	cloneObject.number = number;
    	checkRep();
        return cloneObject;
    }
    

}
