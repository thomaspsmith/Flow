package flow.tests.utils;

import flow.GeometricVolumeCalculator;
import flow.interfaces.IChannelSegment;
import flow.segments.SquareChannelSegment;

/**
 * Provide feedback on segment data and calculation results for test/demonstration purposes
 * @author Tom
 *
 */
public class DecoratedVolumeCalculator extends GeometricVolumeCalculator {
    
    @Override
    public double calculate( SquareChannelSegment seg ) {
        
        double flow = super.calculate( seg );
        print(seg, flow);
        return flow;
    }
    
    private void print(IChannelSegment seg, double volume ) {
        
        System.out.println( "Calculating for segment [" + seg.toString() + "] = " + volume );
    }
}
