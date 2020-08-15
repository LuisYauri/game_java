package ia.core.agente.impl;

import ia.core.agente.EstadoEntorno;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class EstadoEntornoDinamico extends ObjetoConAtributosDinamicos
		implements EstadoEntorno {
	public EstadoEntornoDinamico() {

	}

	@Override
	public String describeType() {
		return EstadoEntorno.class.getSimpleName();
	}
}