package ia.core.agente.impl.progagente.reglasimple;

import ia.core.agente.impl.ObjetoConAtributosDinamicos;

/**
 * Implementation of a NOT condition.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class CondicionNOT extends Condicion {
	private Condicion con;

	public CondicionNOT(Condicion con) {
		assert (null != con);

		this.con = con;
	}

	@Override
	public boolean evaluate(ObjetoConAtributosDinamicos p) {
		return (!con.evaluate(p));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.append("![").append(con).append("]").toString();
	}
}