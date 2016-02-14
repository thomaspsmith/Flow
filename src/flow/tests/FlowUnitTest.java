package flow.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import flow.Flow;
import flow.interfaces.IChannelSegment;
import flow.interfaces.IVolumeCalculator;
import flow.segments.SegmentFactory;
import flow.tests.utils.DecoratedVolumeCalculator;

/**
 * Run the Flow object through it's paces.
 * @author Tom
 *
 */
public class FlowUnitTest {
    
    
    /**
     * for assert double comparisons. 
     */
    private final double delta = 0.0;

    /**
     * calculation visitor
     */
    private IVolumeCalculator calc;
    
    @Before
    public void setUp() throws Exception {
        
        calc = new DecoratedVolumeCalculator();
    }

    @Test
    public void testNoSegments() {
        
        List<IChannelSegment> segs = null;
        
        // null input
        double volume = Flow.getFlow( segs, calc );
        
        // empty input
        segs = new ArrayList<IChannelSegment>();
        volume = Flow.getFlow( segs,  calc );
        assertEquals( volume, 0.0, delta );
        
        // input with null segments
        segs.add( (IChannelSegment)null );
        segs.add( (IChannelSegment)null );
        segs.add( (IChannelSegment)null );
        volume = Flow.getFlow( segs, calc );
        assertEquals( volume, 0.0, delta );
        
        // throw a valid segment into the mix
        segs.add( SegmentFactory.createSegment( 1, 1, 1 ) );
        volume = Flow.getFlow( segs, calc );
        assertEquals( volume, 1, delta);
    }
    
    @Test 
    public void testUnknownSegments() {
        
        // create a custom, arbitrary segment type that someone else has thrown in
        class LocalSeg implements IChannelSegment {

            @Override
            public double calculateFlowVolume( IVolumeCalculator vstr ) {
                return 50;
            }
        };
        
        List<IChannelSegment> segs = new ArrayList<IChannelSegment>();
        segs.add( new LocalSeg() );
        double volume = Flow.getFlow( segs, calc );
        assertEquals( volume, 50.0, delta );
        
        // Add a known segment type
        segs.add( SegmentFactory.createSegment( 1, 1, 1 ) );
        volume = Flow.getFlow(segs, calc);
        assertEquals( volume, 51, delta );
    }
    
    @Test (expected=RuntimeException.class)
    public void testInvalidCalculator()
    {
        List<IChannelSegment> segments = new ArrayList<IChannelSegment>();
        segments.add( SegmentFactory.createSegment( 1,2,3 ) );
        
        IVolumeCalculator badCalc = null;
        
        // should throw
        double volume = Flow.getFlow( segments, badCalc );
        
        fail();
    }
}
