package ia.prueba.core.unit.environment.nqueens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.entorno.nreinas.TableroNReinas;
import ia.core.entorno.nreinas.NQueensGoalTest;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * @author Ravi Mohan
 * 
 */
public class NQueensGoalTestTest {
	NQueensGoalTest goalTest;

	TableroNReinas board;

	@Before
	public void setUp() {
		goalTest = new NQueensGoalTest();
		board = new TableroNReinas(8);
	}

	@Test
	public void testEmptyBoard() {
		Assert.assertFalse(goalTest.isGoalState(board));
	}

	@Test
	public void testSingleSquareBoard() {
		board = new TableroNReinas(1);
		Assert.assertFalse(goalTest.isGoalState(board));
		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertTrue(goalTest.isGoalState(board));
	}

	@Test
	public void testInCorrectPlacement() {
		Assert.assertFalse(goalTest.isGoalState(board));
		// This is the configuration of Figure 3.5 (b) in AIMA 2nd Edition
		board.agregarReinaEn(new UbicacionXY(0, 0));
		board.agregarReinaEn(new UbicacionXY(1, 2));
		board.agregarReinaEn(new UbicacionXY(2, 4));
		board.agregarReinaEn(new UbicacionXY(3, 6));
		board.agregarReinaEn(new UbicacionXY(4, 1));
		board.agregarReinaEn(new UbicacionXY(5, 3));
		board.agregarReinaEn(new UbicacionXY(6, 5));
		board.agregarReinaEn(new UbicacionXY(7, 7));
		Assert.assertFalse(goalTest.isGoalState(board));
	}

	@Test
	public void testCorrectPlacement() {

		Assert.assertFalse(goalTest.isGoalState(board));
		// This is the configuration of Figure 5.9 (c) in AIMA 2nd Edition
		board.agregarReinaEn(new UbicacionXY(0, 1));
		board.agregarReinaEn(new UbicacionXY(1, 4));
		board.agregarReinaEn(new UbicacionXY(2, 6));
		board.agregarReinaEn(new UbicacionXY(3, 3));
		board.agregarReinaEn(new UbicacionXY(4, 0));
		board.agregarReinaEn(new UbicacionXY(5, 7));
		board.agregarReinaEn(new UbicacionXY(6, 5));
		board.agregarReinaEn(new UbicacionXY(7, 2));

		Assert.assertTrue(goalTest.isGoalState(board));
	}
}
