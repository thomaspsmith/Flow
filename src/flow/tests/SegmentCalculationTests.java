package flow.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import flow.interfaces.IChannelSegment;
import flow.segments.SegmentFactory;
import flow.tests.utils.DecoratedVolumeCalculator;



/**
 * Tests the SquareChannelSegment and corresponding GeometricVolumeCalculator with
 * various input values.
 * 
 * @author Tom
 *
 */
@RunWith(Parameterized.class)
public class SegmentCalculationTests
{
    
    /**
     * for assert comparisons. 
     */
    private final double delta = 0.0;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 { 0, 0, 0, 0 },        // dry land beside the river
                 { 2, 2, 0, 0 },        // still water
                 { -1, -1, 1, 1 },      // input error (depth and width absolute values used)
                 { 10, -1, 1, 10 },     // input error (depth and width absolute values used)
                 { 3, 2, -1, -6 },      // tide coming in (negative flow)
                 { 4, 3, 1, 12 },       // normal flow, nothing odd here
                 { 10, 0.01, 10, 1},    // shallow segment
                 { 1/Double.MAX_VALUE, 1, 1, 5.562684646268003E-309}, // narrow segment
                 { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.POSITIVE_INFINITY } // mystic river  
           });
    }
    
    // the following intialized with the above arrays
    @Parameter(0)
    public double width;
    
    @Parameter(1)
    public double depth;
    
    @Parameter(2)
    public double flowrate;
    
    @Parameter(3)
    public double result;
    
    /**
     * The visitor for test (and for echoing the contained values)
     */
    public DecoratedVolumeCalculator calc;

    @Before
    public void setUp() throws Exception {
        
        calc = new DecoratedVolumeCalculator();
    }

    @Test
    public void testSegmentCalculations() {
        
        IChannelSegment seg = SegmentFactory.createSegment( width, depth, flowrate );
        assertEquals( result, seg.calculateFlowVolume( calc ), delta );
    }

}
