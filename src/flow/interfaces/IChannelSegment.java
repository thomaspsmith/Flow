package flow.interfaces;

/**
 * Interface defining segment structure
 * @author Tom
 *
 */
public interface IChannelSegment {
    
    /** 
     * Volume calculation is based on a cross sectional area multiplied
     * by a measured flow rate. This could conceivably be handled many different
     * ways depending on instrumentation, sampling technique, water channel 
     * configuration, etc. 
     * @param vstr Visitor to perform calculations.
     * @return Calculated volume based upon segment particulars.
     */
    public double calculateFlowVolume( IVolumeCalculator vstr );
}
