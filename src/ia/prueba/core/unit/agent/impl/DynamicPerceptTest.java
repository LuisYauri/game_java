package ia.prueba.core.unit.agent.impl;

import org.junit.Assert;
import org.junit.Test;

import ia.core.agente.impl.PercepcionDinamica;

public class DynamicPerceptTest {

	@Test
	public void testToString() {
		PercepcionDinamica p = new PercepcionDinamica("key1", "value1");

		Assert.assertEquals("Percept[key1==value1]", p.toString());

		p = new PercepcionDinamica("key1", "value1", "key2", "value2");

		Assert.assertEquals("Percept[key1==value1, key2==value2]", p.toString());
	}

	@Test
	public void testEquals() {
		PercepcionDinamica p1 = new PercepcionDinamica();
		PercepcionDinamica p2 = new PercepcionDinamica();

		Assert.assertEquals(p1, p2);

		p1 = new PercepcionDinamica("key1", "value1");

		Assert.assertNotSame(p1, p2);

		p2 = new PercepcionDinamica("key1", "value1");

		Assert.assertEquals(p1, p2);
	}
}
