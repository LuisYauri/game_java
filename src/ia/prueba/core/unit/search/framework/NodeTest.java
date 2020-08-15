package ia.prueba.core.unit.search.framework;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.busqueda.framework.Nodo;

/**
 * @author Ravi Mohan
 * 
 */
public class NodeTest {

	@Test
	public void testRootNode() {
		Nodo node1 = new Nodo("state1");
		Assert.assertTrue(node1.isRootNode());
		Nodo node2 = new Nodo("state2", node1, null, 1.0);
		Assert.assertTrue(node1.isRootNode());
		Assert.assertFalse(node2.isRootNode());
		Assert.assertEquals(node1, node2.getParent());
	}

	@Test
	public void testGetPathFromRoot() {
		Nodo node1 = new Nodo("state1");
		Nodo node2 = new Nodo("state2", node1, null, 1.0);
		Nodo node3 = new Nodo("state3", node2, null, 1.0);
		List<Nodo> path = node3.getPathFromRoot();
		Assert.assertEquals(node1, path.get(0));
		Assert.assertEquals(node2, path.get(1));
		Assert.assertEquals(node3, path.get(2));
	}
}
