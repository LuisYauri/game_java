package ia.core.agente.impl.progagente.reglasimple;

import ia.core.agente.impl.ObjetoConAtributosDinamicos;

/**
 * Base abstract class for describing conditions.
 * 
 * @author Ciaran O'Reilly
 * 
 */
public abstract class Condicion {
	public abstract boolean evaluate(ObjetoConAtributosDinamicos p);

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Condicion)) {
			return super.equals(o);
		}
		return (toString().equals(((Condicion) o).toString()));
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
