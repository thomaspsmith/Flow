package flow;

import java.util.List;

import flow.interfaces.IChannelSegment;
import flow.interfaces.IVolumeCalculator;

/**
 * Utility class for collating water volumes for a given set
 * of river channel measurement segments
 * @author Tom
 *
 */
public class Flow {
    
    /**
     * Prevent construction
     */
    private Flow() {}
    
    /**
     * Collates the volumes from the provided list of segments that encompass a particular
     * cross section of a river.
     * @param segments List of measured segments
     * @param vstr Volume calculator visitor to perform individual calculations
     * @return Sum of calculated volumes.
     */
    public static double getFlow( List<IChannelSegment> segments, IVolumeCalculator vstr ) {
        
        // incorrect invocation. Hit the brakes.
        if( vstr == null ) 
            throw new RuntimeException("Invalid method invocation - must provide non-null IVolumeCalculator");

        double volume = 0.0;
        if( segments != null ) {
            for ( IChannelSegment segment : segments) {
                if( segment != null ) {
                    volume += segment.calculateFlowVolume( vstr );
                }
            }
        }
        
        return volume;
    }
}
