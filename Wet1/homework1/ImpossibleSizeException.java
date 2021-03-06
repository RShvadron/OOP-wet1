package homework1;

import java.awt.*;
import java.lang.Math;

public class ImpossibleSizeException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension suggestedDimension;
	
	/**
	 * @effects Initializes this with a given Dimension.
	 */
	public ImpossibleSizeException(Dimension dimension){
		suggestedDimension = new Dimension(Math.abs(dimension.width),Math.abs(dimension.height));
	}
	
	/**
	 * @return suggestedDimension of this
	 */
	public Dimension getSuggestedDimension(){
		return (Dimension)suggestedDimension.clone();
	}
}
