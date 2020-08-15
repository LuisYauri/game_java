package ia.prueba.core.unit.util.datastructure;

import org.junit.Assert;
import org.junit.Test;

import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * @author Ravi Mohan
 * 
 */
public class XYLocationTest {

	@Test
	public void testXYLocationAtributeSettingOnConstruction() {
		UbicacionXY loc = new UbicacionXY(3, 4);
		Assert.assertEquals(3, loc.getCoordenadaX());
		Assert.assertEquals(4, loc.getCoordenadaY());
	}

	@Test
	public void testEquality() {
		UbicacionXY loc1 = new UbicacionXY(3, 4);
		UbicacionXY loc2 = new UbicacionXY(3, 4);
		Assert.assertEquals(loc1, loc2);
	}
}
