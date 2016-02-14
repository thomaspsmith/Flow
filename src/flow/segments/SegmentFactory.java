package flow.segments;

import flow.interfaces.IChannelSegment;

/**
 * Create various segment types based on input.
 * @author Tom
 *
 */
public class SegmentFactory {

    /**
     * Create a rectangular profile segment
     * @param width
     * @param depth
     * @param flowrate
     * @return
     */
    public static IChannelSegment createSegment(final double width, final double depth, final double flowrate) {
        return new SquareChannelSegment( width, depth, flowrate );
    }

    /* The following are only for demonstration in the event that different segment types 
     * are added (examples of possibilities are in this project, but disabled)

    public static IChannelSegment createSegment(final double radius, final double flowrate ) {
        return new RoundChannelSegment( radius, flowrate );
    }
    
    public static IChannelSegment createSegment( final double width, final double side1, 
            final double side2, final double flowrate ) {
    
        return new TrapezoidalChannelSegment( width, side1, side2, flowrate );
    }
    */
}
