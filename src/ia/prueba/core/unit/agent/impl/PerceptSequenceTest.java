package ia.prueba.core.unit.agent.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.Percepcion;
import ia.core.agente.impl.PercepcionDinamica;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class PerceptSequenceTest {

	@Test
	public void testToString() {
		List<Percepcion> ps = new ArrayList<Percepcion>();
		ps.add(new PercepcionDinamica("key1", "value1"));

		Assert.assertEquals("[Percept[key1==value1]]", ps.toString());

		ps.add(new PercepcionDinamica("key1", "value1", "key2", "value2"));

		Assert.assertEquals(
				"[Percept[key1==value1], Percept[key1==value1, key2==value2]]",
				ps.toString());
	}

	@Test
	public void testEquals() {
		List<Percepcion> ps1 = new ArrayList<Percepcion>();
		List<Percepcion> ps2 = new ArrayList<Percepcion>();

		Assert.assertEquals(ps1, ps2);

		ps1.add(new PercepcionDinamica("key1", "value1"));

		Assert.assertNotSame(ps1, ps2);

		ps2.add(new PercepcionDinamica("key1", "value1"));

		Assert.assertEquals(ps1, ps2);
	}
}
