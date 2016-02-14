package flow.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import flow.Flow;
import flow.GeometricVolumeCalculator;
import flow.interfaces.IChannelSegment;
import flow.interfaces.IVolumeCalculator;
import flow.segments.SegmentFactory;

/**
 * This is mostly to show that the segmenting approach works to 
 * approximate the exact value as calculated from a regular geometric
 * profile. It contains methods to show that as the segment count increases, 
 * the calculated estimation of flow volume approaches the "exact" volume.
 * 
 * Please consider this the demo of the project. 
 * 
 * @author Tom
 *
 */
public class FlowFuntionalTest
{
    
    /**
     * Calculation vistor for segments.
     */
    private IVolumeCalculator calc;

    final double expectedRectangleVolume = 5.0*2.0*2.0;

    
    @Before
    public void setUp() {
        // use the quiet calculator rather than the decorated version
        calc = new GeometricVolumeCalculator();
        
    }
    
    @Test
    public void testSquareSection_SingleSegment()
    {
        List<IChannelSegment> segments = new ArrayList<IChannelSegment>();

        // single segment
        segments.add( SegmentFactory.createSegment( 5.0, 2.0, 2.0 ) );
        double volume = Flow.getFlow( segments,  calc );
        assertEquals( volume, expectedRectangleVolume, 0 );
    }
    
    @Test
    public void testSquareSection_MultipleSegments()
    {
        List<IChannelSegment> segments = new ArrayList<IChannelSegment>();
        
        final double expectedRectangleVolume = 5.0*2.0*2.0;
        
        // channel split into 3 segments
        segments.add( SegmentFactory.createSegment( 2.0, 2.0, 2.0 ) );
        segments.add( SegmentFactory.createSegment( 2.0, 2.0, 2.0 ) );
        segments.add( SegmentFactory.createSegment( 1.0, 2.0, 2.0 ) );

        double volume = Flow.getFlow( segments, calc );
        assertEquals( volume, expectedRectangleVolume, 0 );
       
    }
    
    
    @Test
    public void testSemiCircleRiverProfile() {
        
        // imagine a channel with a semi-circular profile; ie. a cylinder split down the long axis
        final double rate = 3.0;
        final double radius = 5.0;
        
        // exact volume, given radius=5m, flowrate=3m/s is 25*pi/2*3, or approximately 117.81 m^3/s
        final double expectedVolume = Math.pow( radius, 2 ) * Math.PI / 2 * rate;
        
        // measure using various segments (to demonstrate accuracy increase)
        for(int i = 10; i < 50; i+= 10) {
            double segmentedVolume = Flow.getFlow( createSemiCircleSegments( i, radius, rate), calc );
            System.out.println("Segment count = " + i );
            System.out.println("Expected = " + expectedVolume + 
                                "; Calculated = " + segmentedVolume + 
                                "; difference = " + (expectedVolume - segmentedVolume) );
            
            // arbitrary delta of 5 between the expected and approximated volumes.
            assertEquals(expectedVolume, segmentedVolume, 5);
        }                
    }
    
    /**
     * Create segments of a semicircular cross section 
     * @param segCount
     * @param channelRadius
     * @param flowRate
     * @return List of channel segments
     */
    private List<IChannelSegment> createSemiCircleSegments(final int segCount, 
            final double channelRadius, final double flowRate ) {
        
        List<IChannelSegment> segments = new ArrayList<IChannelSegment>();
        double segWidth = channelRadius * 2 / segCount;
        for(int i = 1; i <= segCount; i++ ) {
            // calculate the depth at this particular segment location
            double depth = Math.sqrt( Math.pow( channelRadius, 2) - 
                                      Math.pow( channelRadius - i * segWidth, 2 ) ); 
            segments.add( SegmentFactory.createSegment( segWidth, depth, flowRate ) );
        }
        return segments;
    }
}
