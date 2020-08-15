package ia.prueba.core.unit.environment.xyenv;

import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.ObjetoEntorno;
import ia.core.agente.impl.AgenteAbstracto;
import ia.core.entorno.entxy.Wall;
import ia.core.entorno.entxy.XYEnvironment;
import ia.core.util.estructuradedatos.UbicacionXY;
import ia.prueba.core.unit.agent.impl.MockAgent;

/**
 * @author Ravi Mohan
 * 
 */
public class XYEnvironmentTest {
	XYEnvironment env;

	AgenteAbstracto a;

	@Before
	public void setUp() {
		env = new XYEnvironment(10, 12);
		a = new MockAgent();
		env.addObjectToLocation(a, new UbicacionXY(3, 4));
	}

	@Test
	public void testAddObject() {
		Assert.assertEquals(1, env.getAgentes().size());
		Assert.assertEquals(new UbicacionXY(3, 4), env.getCurrentLocationFor(a));
	}

	@Test
	public void testAddObject2() {
		env.addObjectToLocation(new Wall(), new UbicacionXY(9, 9));
		Assert.assertEquals(1, env.getAgentes().size());
		Assert.assertEquals(2, env.getObjetosEntorno().size());
		Assert.assertEquals(1, env.getObjectsAt(new UbicacionXY(9, 9)).size());
	}

	@Test
	public void testAddObjectTwice() {
		Assert.assertEquals(1, env.getAgentes().size());
		UbicacionXY loc = new UbicacionXY(5, 5);
		AgenteAbstracto b = new MockAgent();
		env.addObjectToLocation(b, loc);
		Assert.assertEquals(2, env.getAgentes().size());

		Assert.assertEquals(loc, env.getCurrentLocationFor(b));
	}

	@Test
	public void testMoveObjectToAbsoluteLocation() {
		UbicacionXY loc = new UbicacionXY(5, 5);
		env.moveObjectToAbsoluteLocation(a, loc);
		Assert.assertEquals(new UbicacionXY(5, 5), env.getCurrentLocationFor(a));
	}

	@Test
	public void testMoveObject() {
		UbicacionXY loc = new UbicacionXY(5, 5);
		env.moveObjectToAbsoluteLocation(a, loc);
		Assert.assertEquals(new UbicacionXY(5, 5), env.getCurrentLocationFor(a));
		env.moveObject(a, UbicacionXY.Direccion.Norte);
		Assert.assertEquals(new UbicacionXY(5, 4), env.getCurrentLocationFor(a));
		env.moveObject(a, UbicacionXY.Direccion.Este);
		Assert.assertEquals(new UbicacionXY(6, 4), env.getCurrentLocationFor(a));
		env.moveObject(a, UbicacionXY.Direccion.Sur);
		Assert.assertEquals(new UbicacionXY(6, 5), env.getCurrentLocationFor(a));
		env.moveObject(a, UbicacionXY.Direccion.Oeste);
		Assert.assertEquals(new UbicacionXY(5, 5), env.getCurrentLocationFor(a));
	}

	@Test
	public void testIsBlocked() {
		UbicacionXY loc = new UbicacionXY(5, 5);
		Assert.assertEquals(0, env.getObjectsAt(loc).size());
		Assert.assertEquals(false, env.isBlocked(loc));
		env.addObjectToLocation(new Wall(), loc);
		Assert.assertEquals(1, env.getObjectsAt(loc).size());
		Assert.assertEquals(true, env.isBlocked(loc));
	}

	@Test
	public void testMoveWithBlockingWalls() {
		UbicacionXY loc = new UbicacionXY(5, 5);
		env.moveObjectToAbsoluteLocation(a, loc);
		UbicacionXY northLoc = new UbicacionXY(5, 6);
		UbicacionXY southLoc = new UbicacionXY(5, 4);
		UbicacionXY westLoc = new UbicacionXY(4, 5);

		env.addObjectToLocation(new Wall(), northLoc); // wall to the north of
		// object
		Assert.assertTrue(env.isBlocked(northLoc));
		env.addObjectToLocation(new Wall(), southLoc); // wall to the south of
		// object
		env.addObjectToLocation(new Wall(), westLoc); // wall to the west of
		// object
		Assert.assertEquals(4, env.getObjetosEntorno().size());

		env.moveObject(a, UbicacionXY.Direccion.Norte); // should not move
		env.moveObject(a, UbicacionXY.Direccion.Sur); // should not move
		env.moveObject(a, UbicacionXY.Direccion.Oeste); // should not move
		env.moveObject(a, UbicacionXY.Direccion.Este); // SHOULD move
		Assert.assertEquals(new UbicacionXY(6, 5), env.getCurrentLocationFor(a));
	}

	@Test
	public void testGetObjectsAt() {
		UbicacionXY loc = new UbicacionXY(5, 7);
		env.moveObjectToAbsoluteLocation(a, loc);
		Assert.assertEquals(1, env.getObjectsAt(loc).size());
		AgenteAbstracto b = new MockAgent();
		env.addObjectToLocation(b, loc);
		Assert.assertEquals(2, env.getObjectsAt(loc).size());
	}

	@Test
	public void testGetObjectsNear() {
		UbicacionXY loc = new UbicacionXY(5, 5);
		env.moveObjectToAbsoluteLocation(a, loc);
		AgenteAbstracto b = new MockAgent();
		AgenteAbstracto c = new MockAgent();
		Wall w1 = new Wall();

		env.addObjectToLocation(b, new UbicacionXY(7, 4));
		env.addObjectToLocation(c, new UbicacionXY(5, 7));
		env.addObjectToLocation(w1, new UbicacionXY(3, 10));

		// at this point agent A should be able to see B and C but not the wall
		// with a "vision radius" of 3
		Set<ObjetoEntorno> visibleToA = env.getObjectsNear(a, 3);
		Assert.assertEquals(2, visibleToA.size());
		// agent B should be able to see A only
		Set<ObjetoEntorno> visibleToB = env.getObjectsNear(b, 3);
		Assert.assertEquals(1, visibleToB.size());

		// move B South
		env.moveObject(b, UbicacionXY.Direccion.Sur);
		// at this point both a and c should be visible to b
		visibleToB = env.getObjectsNear(b, 3);
		Assert.assertEquals(2, visibleToB.size());
		// move c near the wall
		env.moveObjectToAbsoluteLocation(c, new UbicacionXY(3, 11));
		// only the wall should be visible
		Set<ObjetoEntorno> visibleToC = env.getObjectsNear(c, 3);
		Assert.assertEquals(1, visibleToC.size());
	}

	@Test
	public void testMakePerimeter() {
		env.makePerimeter();
		Assert.assertTrue(env.isBlocked(new UbicacionXY(0, 0)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(0, 6)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(0, 11)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(6, 0)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(9, 0)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(9, 6)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(9, 11)));
		Assert.assertTrue(env.isBlocked(new UbicacionXY(6, 11)));
	}
}
