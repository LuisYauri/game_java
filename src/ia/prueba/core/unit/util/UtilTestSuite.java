package ia.prueba.core.unit.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.util.datastructure.FIFOQueueTest;
import ia.prueba.core.unit.util.datastructure.LIFOQueueTest;
import ia.prueba.core.unit.util.datastructure.TableTest;
import ia.prueba.core.unit.util.datastructure.XYLocationTest;
import ia.prueba.core.unit.util.math.MixedRadixNumberTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({FIFOQueueTest.class, LIFOQueueTest.class,
		TableTest.class, XYLocationTest.class, MixedRadixNumberTest.class,
		DisjointSetsTest.class, SetOpsTest.class, UtilTest.class })
public class UtilTestSuite {

}
