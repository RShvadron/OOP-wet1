package homework1;

import java.awt.*;


/**
 * A LocationChangingOval is an Oval(type of Shape) that can change its location using its step()
 * method. A LocationChangingOval has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChangingOval consists of the following set of
 * properties: {location, color, shape, size(specific for Oval), velocity}
 */
public class LocationChangingOval extends LocationChangingShape {
	
	protected Rectangle size;
	
	// Abstraction Function:
    //    represents a shape with location = (x,y) (point on Two-dimensional space) at this.location, color at this.color,  
	//    size = width*height at this.size, Horizontal velocity at this.velocityX and vertical velocity at this.velocityY     

    // Representation Invariant:
    //   location != NULL, color != NULL 
	//   size width > 0 &&  size height > 0 
	//   horizontal and vertical velocities are integers between -5 to 5 but NOT 0

	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert (location != null && color !=null ): "location or color or both is null";
    	assert (size.height > 0 && size.width > 0) : "height or width of Oval are negative";
    	assert (velocityX <= 5 && velocityX >= -5 && velocityX != 0) : " velocityX has violated the represntation invariant";
    	assert (velocityY <= 5 && velocityY >= -5 && velocityY != 0) : " velocityY has violated the represntation invariant";
    }
	
	
    /**
     * @effects Initializes this with a a given location, color and dimension.
     *          Each of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
    LocationChangingOval(Point location, Color color,Dimension dim) {
    	super(location,color);
    	size = new Rectangle(location,dim);
    	checkRep();
    }
    
    
    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
    	checkRep();
    	if(dimension.height < 0 || dimension.width < 0 ) {
    		throw new ImpossibleSizeException(dimension);
    	}
    	size = new Rectangle(location,dimension);
    	checkRep();
    }
    
    
    /**
     * @return the bounding rectangle of this.
     */
    public Rectangle getBounds() {
    	checkRep();
    	return (Rectangle)size.clone();
    }
    
    
    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public void draw(Graphics g) {
    	checkRep();
    	g.setColor(color);
    	g.fillOval(location.x,location.y,size.width,size.height);
    	checkRep();
    }
    
    
    /**
     * @effects Creates and returns a copy of this.
     */
    @Override
    public Object clone() {
    	checkRep();
    	LocationChangingOval cloneObject = (LocationChangingOval)super.clone();
    	cloneObject.size = (Rectangle)this.size.clone();
    	checkRep();
        return cloneObject;
    }

}
