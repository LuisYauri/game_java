package ia.core.busqueda.informada;

import ia.core.busqueda.framework.FuncionEvaluacion;
import ia.core.busqueda.framework.FuncionHeuristica;
import ia.core.busqueda.framework.Nodo;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 92.<br>
 * <br>
 * Greedy best-first search tries to expand the node that is closest to the
 * goal, on the grounds that this is likely to lead to a solution quickly. Thus,
 * it evaluates nodes by using just the heuristic function; that is, f(n) = h(n)
 * 
 * @author Ciaran O'Reilly
 * 
 */
public class GreedyBestFirstEvaluationFunction implements FuncionEvaluacion {

	private FuncionHeuristica hf = null;

	public GreedyBestFirstEvaluationFunction(FuncionHeuristica hf) {
		this.hf = hf;
	}

	public double f(Nodo n) {
		// f(n) = h(n)
		return hf.h(n.getState());
	}
}
