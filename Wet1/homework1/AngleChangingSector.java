package homework1;

import java.awt.*;

/**
 * A AngleChangingSector is an abstraction of a Oval changing sector object. AngleChangingSector will change his animation 
 * every time there will be a change in the sector.
 * A typical Shape consists of a set of properties: {location, color, shape, size}.
 */
public class AngleChangingSector extends Shape implements Animatable{
	
	private int startAngle;
	private int endAngle;
	private Rectangle size;
	private boolean animationUp;
	
	
	// Abstraction Function:
    //   represents a shape with location = (x,y) (point on Two-dimensional space) at this.location,   
    //   size = width*height at this.size, a color at this.color, start angle at this.startAngle and end angle at this.endAngle

    // Representation Invariant:
    //   location != NULL, color != NULL 
    //   size width > 0 && size height > 0 
	//   both angles are greater or equal to 0 and less or equal to 359
    
    /**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
	private void checkRep() {
		assert (location != null && color !=null ): "location or color or both is null";
		assert (size.height > 0 && size.width > 0) : "height or width of Oval are negative";
		assert (startAngle >= 0 && startAngle < 360 && endAngle >= 0 && endAngle < 360);
	}
	
	
	/**
     * @effects Initializes this with a given location, color, start angle, end angle, dimension.
     */
	AngleChangingSector(Point location, Color color, int startAngle, int endAngle, Dimension dim) {
		super(location,color);
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.size = new Rectangle(location,dim);
		if(startAngle == 359) {  // in this case the animation step will count 1 degree down until 0 
			animationUp = false;
		}
		else {  // in this case the animation step will count 1 degree up until 359 
			animationUp = true;
		}
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
		g.fillArc(location.x, location.y, size.width, size.height, startAngle, endAngle); 	
		checkRep();
	}
	
	
	/**
     * @modifies this
     * @effects shift angle by 1 degree according to animationUp flag
     */
	public void step(Rectangle bound) {
		checkRep();
		if(endAngle == 359) {
			animationUp = false;
		}
		else if(endAngle == 0) {
			animationUp = true;
		}
		if(animationUp = true) {
			++endAngle;
		}
		else {
			--endAngle;
		}
		checkRep();
	}
	
	
	/**
     * @effects Creates and returns a copy of this.
     */
	@Override
	public Object clone() {
		checkRep();
		AngleChangingSector cloneObject = (AngleChangingSector)super.clone();
    	cloneObject.size = (Rectangle)this.size.clone();
    	cloneObject.startAngle = startAngle;
    	cloneObject.endAngle = endAngle;
    	checkRep();
        return cloneObject;
    }	

}