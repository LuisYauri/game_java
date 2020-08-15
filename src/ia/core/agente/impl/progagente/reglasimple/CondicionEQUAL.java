package ia.core.agente.impl.progagente.reglasimple;

import ia.core.agente.impl.ObjetoConAtributosDinamicos;

/**
 * Implementation of an EQUALity condition.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class CondicionEQUAL extends Condicion {
	private Object key;

	private Object value;

	public CondicionEQUAL(Object key, Object value) {
		assert (null != key);
		assert (null != value);

		this.key = key;
		this.value = value;
	}

	@Override
	public boolean evaluate(ObjetoConAtributosDinamicos p) {
		return value.equals(p.getAttribute(key));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return sb.append(key).append("==").append(value).toString();
	}
}
