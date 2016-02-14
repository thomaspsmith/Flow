package flow.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
    FlowFuntionalTest.class, 
    FlowUnitTest.class, 
    SegmentCalculationTests.class })
public class AllTests
{}
