package ia.prueba.core.unit.environment.map;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.mapa.ExtendableMap;
import ia.core.entorno.mapa.MapFunctionFactory;
import ia.core.entorno.mapa.MoveToAction;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionResultado;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class MapFunctionFactoryTest {
	FuncionAcciones af;
	FuncionResultado rf;

	@Before
	public void setUp() {
		ExtendableMap aMap = new ExtendableMap();
		aMap.addBidirectionalLink("A", "B", 5.0);
		aMap.addBidirectionalLink("A", "C", 6.0);
		aMap.addBidirectionalLink("B", "C", 4.0);
		aMap.addBidirectionalLink("C", "D", 7.0);
		aMap.addUnidirectionalLink("B", "E", 14.0);

		af = MapFunctionFactory.getActionsFunction(aMap);
		rf = MapFunctionFactory.getResultFunction();
	}

	@Test
	public void testSuccessors() {
		ArrayList<String> locations = new ArrayList<String>();

		// A
		locations.clear();
		locations.add("B");
		locations.add("C");
		for (Accion a : af.actions("A")) {
			Assert.assertTrue(locations.contains(((MoveToAction) a)
					.getToLocation()));
			Assert.assertTrue(locations.contains(rf.result("A", a)));
		}

		// B
		locations.clear();
		locations.add("A");
		locations.add("C");
		locations.add("E");
		for (Accion a : af.actions("B")) {
			Assert.assertTrue(locations.contains(((MoveToAction) a)
					.getToLocation()));
			Assert.assertTrue(locations.contains(rf.result("B", a)));
		}

		// C
		locations.clear();
		locations.add("A");
		locations.add("B");
		locations.add("D");
		for (Accion a : af.actions("C")) {
			Assert.assertTrue(locations.contains(((MoveToAction) a)
					.getToLocation()));
			Assert.assertTrue(locations.contains(rf.result("C", a)));
		}

		// D
		locations.clear();
		locations.add("C");
		for (Accion a : af.actions("D")) {
			Assert.assertTrue(locations.contains(((MoveToAction) a)
					.getToLocation()));
			Assert.assertTrue(locations.contains(rf.result("D", a)));
		}
		// E
		locations.clear();
		Assert.assertTrue(0 == af.actions("E").size());
	}
}
