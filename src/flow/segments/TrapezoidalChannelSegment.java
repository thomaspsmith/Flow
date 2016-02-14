package flow.segments;

//import flow.interfaces.IChannelSegment;
//import flow.interfaces.IVolumeCalculator;

/**
 * Theory - if two depth measurements are taken on either side of the flow sensor, 
 * then the bottom could be more accurately approximated by assigning a trapezoidal
 * segment boundary for calculation. The additional cost of measurement may make the 
 * slight improvement in accuracy moot, however. I will leave this in place for 
 * interest only.
 * @author Tom
 *
 */
//public class TrapezoidalChannelSegment implements IChannelSegment
//{
//    
//    /**
//     * Trapezoid consists of width, and two parallel sides. 
//     * Assumption :  in this case, the flow measurement is in the center of the trapezoid, 
//     *      the width is the distance on the top of the water between the depths
//     */
//    public final double width;
//    public final double depth1;
//    public final double depth2;
//    public final double flowRate;
//    
//    /**
//     * Constructor.
//     * @param width
//     * @param d1
//     * @param d2
//     * @param flowrate
//     */
//    public TrapezoidalChannelSegment(double width, double d1, double d2, double flowrate) {
//        
//        this.width = width;
//        this.depth1 = d1;
//        this.depth2 = d2;
//        this.flowRate = flowrate;
//    }
//
//    @Override
//    public double calculateFlowVolume( IVolumeCalculator vstr ) {
//        
//        return vstr.calculate( this );
//    }
//
//    @Override
//    public String toString() {
//        
//        return this.getClass().getName() + " : Width = " + width + " Sides( "+ depth1 + "," + depth2 + ") FlowRate = " + flowRate;
//    }
//}
