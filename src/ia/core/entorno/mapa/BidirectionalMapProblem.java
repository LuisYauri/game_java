package ia.core.entorno.mapa;

import ia.core.busqueda.framework.ProblemaBidireccional;
import ia.core.busqueda.framework.PruebaMetaPorDefecto;
import ia.core.busqueda.framework.Problema;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class BidirectionalMapProblem extends Problema implements
		ProblemaBidireccional {

	Map map;

	Problema reverseProblem;

	public BidirectionalMapProblem(Map map, String initialState,
			String goalState) {
		super(initialState, MapFunctionFactory.getActionsFunction(map),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						goalState), new MapStepCostFunction(map));

		this.map = map;

		reverseProblem = new Problema(goalState,
				MapFunctionFactory.getActionsFunction(map),
				MapFunctionFactory.getResultFunction(), new PruebaMetaPorDefecto(
						initialState), new MapStepCostFunction(map));
	}

	//
	// START Interface BidrectionalProblem
	public Problema getOriginalProblem() {
		return this;
	}

	public Problema getReverseProblem() {
		return reverseProblem;
	}
	// END Interface BirectionalProblem
	//
}
