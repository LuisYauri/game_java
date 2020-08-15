package ia.prueba.core.unit.search.csp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.busqueda.csp.Assignment;
import ia.core.busqueda.csp.BacktrackingStrategy;
import ia.core.busqueda.csp.CSP;
import ia.core.busqueda.csp.MapCSP;
import ia.core.busqueda.csp.MinConflictsStrategy;

/**
 * @author Ravi Mohan
 * 
 */
public class MapCSPTest {
	private CSP csp;

	@Before
	public void setUp() {
		csp = new MapCSP();
	}

	@Test
	public void testBackTrackingSearch() {
		Assignment results = new BacktrackingStrategy().solve(csp);
		Assert.assertNotNull(results);
		Assert.assertEquals(MapCSP.GREEN, results.getAssignment(MapCSP.WA));
		Assert.assertEquals(MapCSP.RED, results.getAssignment(MapCSP.NT));
		Assert.assertEquals(MapCSP.BLUE, results.getAssignment(MapCSP.SA));
		Assert.assertEquals(MapCSP.GREEN, results.getAssignment(MapCSP.Q));
		Assert.assertEquals(MapCSP.RED, results.getAssignment(MapCSP.NSW));
		Assert.assertEquals(MapCSP.GREEN, results.getAssignment(MapCSP.V));
		Assert.assertEquals(MapCSP.RED, results.getAssignment(MapCSP.T));
	}

	@Test
	public void testMCSearch() {
		new MinConflictsStrategy(100).solve(csp);
	}
}
