package ia.prueba.core.unit.environment.nqueens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.entorno.nreinas.TableroNReinas;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * @author Ravi Mohan
 * 
 */
public class NQueensBoardTest {

	TableroNReinas board;

	@Before
	public void setUp() {

		board = new TableroNReinas(8);
	}

	@Test
	public void testBasics() {
		Assert.assertEquals(0, board.getNumeroDeReinasEnTablero());
		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
		board.agregarReinaEn(new UbicacionXY(1, 1));
		Assert.assertEquals(2, board.getNumeroDeReinasEnTablero());
		Assert.assertTrue(board.reinaExisteEn(new UbicacionXY(1, 1)));
		Assert.assertTrue(board.reinaExisteEn(new UbicacionXY(0, 0)));
		board.moverReina(new UbicacionXY(1, 1), new UbicacionXY(3, 3));
		Assert.assertTrue(board.reinaExisteEn(new UbicacionXY(3, 3)));
		Assert.assertTrue(!(board.reinaExisteEn(new UbicacionXY(1, 1))));
		Assert.assertEquals(2, board.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testCornerQueenAttack1() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(false,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 0)));
		// queen on square not included
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(1, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(7, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 7)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(1, 1)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(2, 2)));
		Assert.assertEquals(false,
				board.estaCasillaBajoAtaque(new UbicacionXY(2, 1)));
		Assert.assertEquals(false,
				board.estaCasillaBajoAtaque(new UbicacionXY(1, 2)));
	}

	@Test
	public void testCornerQueenAttack2() {

		board.agregarReinaEn(new UbicacionXY(7, 7));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(7, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 7)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(7, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(6, 6)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(5, 5)));
		Assert.assertEquals(false,
				board.estaCasillaBajoAtaque(new UbicacionXY(6, 5)));
		Assert.assertEquals(false,
				board.estaCasillaBajoAtaque(new UbicacionXY(5, 6)));
	}

	@Test
	public void testEdgeQueenAttack() {

		board.agregarReinaEn(new UbicacionXY(0, 3));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 7)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(7, 3)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(3, 0)));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(4, 7)));
	}

	@Test
	public void testAttack2() {

		board.agregarReinaEn(new UbicacionXY(7, 0));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(6, 1)));
	}

	@Test
	public void testAttack3() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(true,
				board.estaCasillaBajoAtaque(new UbicacionXY(0, 1)));
	}

	@Test
	public void testAttack4() {

		board.agregarReinaEn(new UbicacionXY(0, 2));
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(1, 1)));
	}

	@Test
	public void testMidBoardDiagonalAttack() {

		board.agregarReinaEn(new UbicacionXY(3, 3));
		// forwardDiagonal from the queen
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(4, 2)));
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(4, 4)));
		// backwardDiagonal from the queen
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(2, 2)));
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(2, 4)));
	}

	@Test
	public void testCornerDiagonalAttack() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		// forwardDiagonal from the queen
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(1, 1)));
		board.limpiar();

		board.agregarReinaEn(new UbicacionXY(7, 7));
		// backwardDiagonal from the queen
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(6, 6)));

		// assertTrue(board.estaCasillaBajoAtaque(new XYLocation(2, 2)));
		// assertTrue(board.estaCasillaBajoAtaque(new XYLocation(2, 4)));
	}

	@Test
	public void testAttack6() {

		board.agregarReinaEn(new UbicacionXY(1, 6));
		Assert.assertTrue(board.estaCasillaBajoAtaque(new UbicacionXY(0, 7)));
	}

	@Test
	public void testRemoveQueen() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
		board.quitarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(0, board.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testMoveQueen() {

		UbicacionXY from = new UbicacionXY(0, 0);
		UbicacionXY to = new UbicacionXY(1, 1);

		board.agregarReinaEn(from);
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
		Assert.assertTrue(board.reinaExisteEn(from));
		Assert.assertFalse(board.reinaExisteEn(to));

		board.moverReina(from, to);
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
		Assert.assertFalse(board.reinaExisteEn(from));
		Assert.assertTrue(board.reinaExisteEn(to));
	}

	@Test
	public void testMoveNonExistentQueen() {

		UbicacionXY from = new UbicacionXY(0, 0);
		UbicacionXY to = new UbicacionXY(1, 1);
		board.moverReina(from, to);

		Assert.assertEquals(0, board.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testRemoveNonExistentQueen() {
		board.quitarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(0, board.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testEquality() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		TableroNReinas board2 = new TableroNReinas(8);
		board2.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(board, board2);
		TableroNReinas board3 = new TableroNReinas(8);
		board3.agregarReinaEn(new UbicacionXY(0, 1));
		Assert.assertFalse(board.equals(board3));
	}

	@Test
	public void testPrint() {

		TableroNReinas board2 = new TableroNReinas(2);
		board2.agregarReinaEn(new UbicacionXY(0, 0));
		String expected = " Q  - \n -  - \n";
		Assert.assertEquals(expected, board2.getImagenTablero());
	}

	@Test
	public void testDontPlaceTwoQueensOnOneSquare() {

		board.agregarReinaEn(new UbicacionXY(0, 0));
		board.agregarReinaEn(new UbicacionXY(0, 0));
		Assert.assertEquals(1, board.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testSimpleHorizontalAttack() {
		UbicacionXY loc = new UbicacionXY(0, 0);
		board.agregarReinaEn(loc);
		Assert.assertEquals(0, board.getNumeroDeAtaquesOn(loc));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(1, 0)));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.derecha()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(7, 0)));
	}

	@Test
	public void testSimpleVerticalAttack() {
		UbicacionXY loc = new UbicacionXY(0, 0);
		board.agregarReinaEn(loc);
		Assert.assertEquals(0, board.getNumeroDeAtaquesOn(loc));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.abajo()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(0, 7)));
	}

	@Test
	public void testSimpleDiagonalAttack() {
		UbicacionXY loc = new UbicacionXY(3, 3);
		board.agregarReinaEn(loc);
		Assert.assertEquals(0, board.getNumeroDeAtaquesOn(loc));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.abajo().derecha()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.abajo().izquierda()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.arriba().izquierda()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc.arriba().derecha()));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(7, 7)));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(0, 0)));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(6, 0)));
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(new UbicacionXY(0, 6)));
	}

	@Test
	public void testMultipleQueens() {
		UbicacionXY loc1 = new UbicacionXY(3, 3);
		board.agregarReinaEn(loc1);
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc1.derecha()));

		board.agregarReinaEn(loc1.derecha().derecha());
		Assert.assertEquals(1, board.getNumeroDeAtaquesOn(loc1));
		Assert.assertEquals(2, board.getNumeroDeAtaquesOn(loc1.derecha()));

		board.agregarReinaEn(loc1.derecha().abajo());
		Assert.assertEquals(2, board.getNumeroDeAtaquesOn(loc1));
		Assert.assertEquals(3, board.getNumeroDeAtaquesOn(loc1.derecha()));
		Assert.assertEquals(2, board.getNumeroDeAtaquesOn(loc1.derecha().derecha()));
	}

	@Test
	public void testBoardDisplay() {
		board.agregarReinaEn(new UbicacionXY(0, 5));
		board.agregarReinaEn(new UbicacionXY(1, 6));
		board.agregarReinaEn(new UbicacionXY(2, 1));
		board.agregarReinaEn(new UbicacionXY(3, 3));
		board.agregarReinaEn(new UbicacionXY(4, 6));
		board.agregarReinaEn(new UbicacionXY(5, 4));
		board.agregarReinaEn(new UbicacionXY(6, 7));
		board.agregarReinaEn(new UbicacionXY(7, 7));
		Assert.assertEquals(" -  -  -  -  -  -  -  - \n"
				+ " -  -  Q  -  -  -  -  - \n" + " -  -  -  -  -  -  -  - \n"
				+ " -  -  -  Q  -  -  -  - \n" + " -  -  -  -  -  Q  -  - \n"
				+ " Q  -  -  -  -  -  -  - \n" + " -  Q  -  -  Q  -  -  - \n"
				+ " -  -  -  -  -  -  Q  Q \n", board.getImagenTablero());

		Assert.assertEquals("--------\n" + "--Q-----\n" + "--------\n"
				+ "---Q----\n" + "-----Q--\n" + "Q-------\n" + "-Q--Q---\n"
				+ "------QQ\n", board.toString());
	}
}
