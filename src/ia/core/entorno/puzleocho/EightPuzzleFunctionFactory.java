package ia.core.entorno.puzleocho;

import java.util.LinkedHashSet;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionResultado;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class EightPuzzleFunctionFactory {
	private static FuncionAcciones _actionsFunction = null;
	private static FuncionResultado _resultFunction = null;

	public static FuncionAcciones getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static FuncionResultado getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements FuncionAcciones {
		public Set<Accion> actions(Object state) {
			EightPuzzleBoard board = (EightPuzzleBoard) state;

			Set<Accion> actions = new LinkedHashSet<Accion>();

			if (board.canMoveGap(EightPuzzleBoard.UP)) {
				actions.add(EightPuzzleBoard.UP);
			}
			if (board.canMoveGap(EightPuzzleBoard.DOWN)) {
				actions.add(EightPuzzleBoard.DOWN);
			}
			if (board.canMoveGap(EightPuzzleBoard.LEFT)) {
				actions.add(EightPuzzleBoard.LEFT);
			}
			if (board.canMoveGap(EightPuzzleBoard.RIGHT)) {
				actions.add(EightPuzzleBoard.RIGHT);
			}

			return actions;
		}
	}

	private static class EPResultFunction implements FuncionResultado {
		public Object result(Object s, Accion a) {
			EightPuzzleBoard board = (EightPuzzleBoard) s;

			if (EightPuzzleBoard.UP.equals(a)
					&& board.canMoveGap(EightPuzzleBoard.UP)) {
				EightPuzzleBoard newBoard = new EightPuzzleBoard(board);
				newBoard.moveGapUp();
				return newBoard;
			} else if (EightPuzzleBoard.DOWN.equals(a)
					&& board.canMoveGap(EightPuzzleBoard.DOWN)) {
				EightPuzzleBoard newBoard = new EightPuzzleBoard(board);
				newBoard.moveGapDown();
				return newBoard;
			} else if (EightPuzzleBoard.LEFT.equals(a)
					&& board.canMoveGap(EightPuzzleBoard.LEFT)) {
				EightPuzzleBoard newBoard = new EightPuzzleBoard(board);
				newBoard.moveGapLeft();
				return newBoard;
			} else if (EightPuzzleBoard.RIGHT.equals(a)
					&& board.canMoveGap(EightPuzzleBoard.RIGHT)) {
				EightPuzzleBoard newBoard = new EightPuzzleBoard(board);
				newBoard.moveGapRight();
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}