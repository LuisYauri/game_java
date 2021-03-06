package ia.core.entorno.nreinas;

import java.util.LinkedHashSet;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionResultado;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * Provides useful functions for two versions of the n-queens problem. The
 * incremental formulation and the complete-state formulation share the same
 * RESULT function but use different ACTIONS functions.
 * 
 * @author Ciaran O'Reilly
 * @author R. Lunde
 */
public class NQueensFunctionFactory {
	private static FuncionAcciones _iActionsFunction = null;
	private static FuncionAcciones _cActionsFunction = null;
	private static FuncionResultado _resultFunction = null;

	/**
	 * Returns an ACTIONS function for the incremental formulation of the
	 * n-queens problem.
	 */
	public static FuncionAcciones getIActionsFunction() {
		if (null == _iActionsFunction) {
			_iActionsFunction = new NQIActionsFunction();
		}
		return _iActionsFunction;
	}

	/**
	 * Returns an ACTIONS function for the complete-state formulation of the
	 * n-queens problem.
	 */
	public static FuncionAcciones getCActionsFunction() {
		if (null == _cActionsFunction) {
			_cActionsFunction = new NQCActionsFunction();
		}
		return _cActionsFunction;
	}

	/**
	 * Returns a RESULT function for the n-queens problem.
	 */
	public static FuncionResultado getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new NQResultFunction();
		}
		return _resultFunction;
	}

	/**
	 * Assumes that queens are placed column by column, starting with an empty
	 * board, and provides queen placing actions for all non-attacked positions
	 * of the first free column.
	 * 
	 * @author R. Lunde
	 */
	private static class NQIActionsFunction implements FuncionAcciones {
		public Set<Accion> actions(Object state) {
			TableroNReinas board = (TableroNReinas) state;

			Set<Accion> actions = new LinkedHashSet<Accion>();

			int numQueens = board.getNumeroDeReinasEnTablero();
			int boardSize = board.getTamanno();
			for (int i = 0; i < boardSize; i++) {
				UbicacionXY newLocation = new UbicacionXY(numQueens, i);
				if (!(board.estaCasillaBajoAtaque(newLocation))) {
					actions.add(new QueenAction(QueenAction.PLACE_QUEEN,
							newLocation));
				}
			}

			return actions;
		}
	}

	/**
	 * Assumes exactly one queen in each column and provides all possible queen
	 * movements in vertical direction as actions.
	 * 
	 * @author R. Lunde
	 */
	private static class NQCActionsFunction implements FuncionAcciones {

		public Set<Accion> actions(Object state) {
			Set<Accion> actions = new LinkedHashSet<Accion>();
			TableroNReinas board = (TableroNReinas) state;
			for (int i = 0; i < board.getTamanno(); i++)
				for (int j = 0; j < board.getTamanno(); j++) {
					UbicacionXY loc = new UbicacionXY(i, j);
					if (!board.reinaExisteEn(loc))
						actions.add(new QueenAction(QueenAction.MOVE_QUEEN, loc));
				}
			return actions;
		}
	}

	/** Supports queen placing, queen removal, and queen movement actions. */
	private static class NQResultFunction implements FuncionResultado {
		public Object result(Object s, Accion a) {
			if (a instanceof QueenAction) {
				QueenAction qa = (QueenAction) a;
				TableroNReinas board = (TableroNReinas) s;
				TableroNReinas newBoard = new TableroNReinas(board.getTamanno());
				newBoard.setTablero(board.getPosicionesDeReina());
				if (qa.getName() == QueenAction.PLACE_QUEEN)
					newBoard.agregarReinaEn(qa.getLocation());
				else if (qa.getName() == QueenAction.REMOVE_QUEEN)
					newBoard.quitarReinaEn(qa.getLocation());
				else if (qa.getName() == QueenAction.MOVE_QUEEN)
					newBoard.moverReinaA(qa.getLocation());
				s = newBoard;
			}
			// if action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}