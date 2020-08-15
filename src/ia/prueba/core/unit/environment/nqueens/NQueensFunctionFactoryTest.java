package ia.prueba.core.unit.environment.nqueens;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ia.core.agente.Accion;
import ia.core.entorno.nreinas.TableroNReinas;
import ia.core.entorno.nreinas.NQueensFunctionFactory;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionResultado;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class NQueensFunctionFactoryTest {
	FuncionAcciones af;
	FuncionResultado rf;
	TableroNReinas oneBoard;
	TableroNReinas eightBoard;

	@Before
	public void setUp() {
		oneBoard = new TableroNReinas(1);
		eightBoard = new TableroNReinas(8);

		af = NQueensFunctionFactory.getIActionsFunction();
		rf = NQueensFunctionFactory.getResultFunction();
	}

	@Test
	public void testSimpleBoardSuccessorGenerator() {
		List<Accion> actions = new ArrayList<Accion>(af.actions(oneBoard));
		Assert.assertEquals(1, actions.size());
		TableroNReinas next = (TableroNReinas) rf.result(oneBoard, actions.get(0));
		Assert.assertEquals(1, next.getNumeroDeReinasEnTablero());
	}

	@Test
	public void testComplexBoardSuccessorGenerator() {
		List<Accion> actions = new ArrayList<Accion>(af.actions(eightBoard));
		Assert.assertEquals(8, actions.size());
		TableroNReinas next = (TableroNReinas) rf
				.result(eightBoard, actions.get(0));
		Assert.assertEquals(1, next.getNumeroDeReinasEnTablero());

		actions = new ArrayList<Accion>(af.actions(next));
		Assert.assertEquals(6, actions.size());
	}
}
