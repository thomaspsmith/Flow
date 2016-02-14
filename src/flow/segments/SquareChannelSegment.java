package flow.segments;

import flow.interfaces.IChannelSegment;
import flow.interfaces.IVolumeCalculator;


/**
 * Represents a rectangular segment of a channel being measured
 * @author Tom
 *
 */
public class SquareChannelSegment implements IChannelSegment {
    
    /**
     * Area definition parameters and flow rate.
     */
    public final double width;
    public final double depth;
    public final double flowRate;
    
    /**
     * Create Square segment 
     * @param width
     * @param depth
     * @param flowrate
     */
    public SquareChannelSegment(final double width, final double depth, final double flowrate) {
        this.width = width;
        this.depth = depth;
        this.flowRate = flowrate;
    }
    
    @Override 
    public double calculateFlowVolume( IVolumeCalculator calc ) {
        return calc.calculate( this );
    }
    
    @Override
    public String toString() {

        return this.getClass().getName() + " : Depth = " + depth + ", Width = " + width + ", FlowRate = " + flowRate;
    }
}