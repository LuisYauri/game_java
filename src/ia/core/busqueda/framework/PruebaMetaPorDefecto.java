package ia.core.busqueda.framework;

/**
 * Checks whether a given state equals an explicitly specified goal state.
 * 
 * @author Ruediger Lunde
 */
public class PruebaMetaPorDefecto implements PruebaDeMeta {
	private Object goalState;

	public PruebaMetaPorDefecto(Object goalState) {
		this.goalState = goalState;
	}

	public boolean isGoalState(Object state) {
		return goalState.equals(state);
	}
}
