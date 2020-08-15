package ia.core.entorno.mundowumpus.accion;

import ia.core.agente.impl.AccionDinamica;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 237.<br>
 * <br>
 * The action Climb can be used to climb out of the cave, but only from square
 * [1,1].
 * 
 * @author Ciaran O'Reilly
 */
public class Climb extends AccionDinamica {
	public static final String CLIMB_ACTION_NAME = "Climb";

	public Climb() {
		super(CLIMB_ACTION_NAME);
	}
}
