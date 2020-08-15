package ia.core.agente.impl.progagente.reglasimple;

import ia.core.agente.impl.ObjetoConAtributosDinamicos;

/**
 * Implementation of an OR condition.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class CondicionOR extends Condicion {
	private Condicion left;

	private Condicion right;

	public CondicionOR(Condicion leftCon, Condicion rightCon) {
		assert (null != leftCon);
		assert (null != rightCon);

		left = leftCon;
		right = rightCon;
	}

	@Override
	public boolean evaluate(ObjetoConAtributosDinamicos p) {
		return (left.evaluate(p) || right.evaluate(p));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.append("[").append(left).append(" || ").append(right)
				.append("]").toString();
	}
}
