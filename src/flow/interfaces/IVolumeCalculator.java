package flow.interfaces;

import flow.segments.SquareChannelSegment;

/**
 * Interface defining methods for performing the various Segment volume calculations
 * @author Tom
 *
 */
public interface IVolumeCalculator {
    
    /**
     * Calculates the area of a rectangle and multiplies by flow rate. Segment areas
     * are converted to absolute values for all values.
     * @param SquareChannelSegment
     * @return FlowRate (volume/s)
     */
    public double calculate( SquareChannelSegment seg );
    
    
    /** Calculates the area of a circular cross section and multiplied by flow rate.
     * NOTE: for demonstration
     * @param RoundChannelSegment
     * @return FlowRate (volume/s)
     */
    //public double calculate( RoundChannelSegment seg );
    
    
    /**
     * Calculates the area of a trapezoidal segment and multiplies by flow rate.
     * NOTE: for demonstration
     * Formula = ( Side1 + Side2 ) / 2 * Width
     * @param TrapezoidalChannelSegment
     * @return FlowRate (volume/s)
     */
    //public double calculate( TrapezoidalChannelSegment trapezoidalChannelSegment );
}
