package ia.core.agente.impl;

import ia.core.agente.Estado;

/**
 * @author Ciaran O'Reilly
 */
public class EstadoDinamico extends ObjetoConAtributosDinamicos implements Estado {
	public EstadoDinamico() {

	}

	@Override
	public String describeType() {
		return Estado.class.getSimpleName();
	}
}