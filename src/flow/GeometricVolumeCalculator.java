package flow;

import flow.interfaces.IVolumeCalculator;
import flow.segments.SquareChannelSegment;

/**
 * Visitor for calculation operations. The Round and Trapezoidal methods are for demonstration
 * only, and were added mainly to satisfy my own thoughts on different calculation scenarios
 * @author Tom
 *
 */
public class GeometricVolumeCalculator implements IVolumeCalculator {
    
    /* (non-Javadoc)
     * @see flow.interfaces.IVolumeCalculator#calculate(flow.segments.SquareChannelSegment)
     */
    @Override
    public double calculate( SquareChannelSegment seg ) {
        
        // ASSUMPTION : negative cross sections are not valid, and for now
        // negative inputs are assumed to be entry/measurement errors and treated 
        // as positive. If  the expectation is that the import/data entry routines 
        // filter out invalid data, the Math.abs call below can be removed.
        return Math.abs( seg.depth * seg.width ) * seg.flowRate;
    }

    /* experimentation/demonstration only
    @Override
    public double calculate( RoundChannelSegment seg ) {
        
        return Math.pow( seg.radius, 2) * Math.PI * seg.flowRate;
    }

    @Override
    public double calculate( TrapezoidalChannelSegment seg ) {
        
        return ( ( seg.depth1 + seg.depth2 ) / 2 * seg.width ) * seg.flowRate;
    }
    */
}
