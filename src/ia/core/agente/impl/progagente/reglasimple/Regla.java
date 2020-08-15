package ia.core.agente.impl.progagente.reglasimple;

import ia.core.agente.Accion;
import ia.core.agente.impl.ObjetoConAtributosDinamicos;

/**
 * A simple implementation of a "condition-action rule".
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 */
public class Regla {
	private Condicion con;

	private Accion action;

	/**
	 * Constructs a condition-action rule.
	 * 
	 * @param con
	 *            a condition
	 * @param action
	 *            an action
	 */
	public Regla(Condicion con, Accion action) {
		assert (null != con);
		assert (null != action);

		this.con = con;
		this.action = action;
	}

	public boolean evaluate(ObjetoConAtributosDinamicos p) {
		return (con.evaluate(p));
	}

	/**
	 * Returns the action of this condition-action rule.
	 * 
	 * @return the action of this condition-action rule.
	 */
	public Accion getAction() {
		return action;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Regla)) {
			return super.equals(o);
		}
		return (toString().equals(((Regla) o).toString()));
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.append("if ").append(con).append(" then ").append(action)
				.append(".").toString();
	}
}
