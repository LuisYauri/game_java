package ia.core.busqueda.noinformada;

import java.util.List;

import ia.core.agente.Accion;
import ia.core.busqueda.framework.Metricas;
import ia.core.busqueda.framework.Nodo;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.BusquedaCola;
import ia.core.busqueda.framework.Busqueda;
import ia.core.util.estructuradedatos.LIFOQueue;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): page 85.<br>
 * <br>
 * Depth-first search always expands the deepest node in the current frontier of
 * the search tree. <br>
 * <br>
 * <b>Note:</b> Supports both Tree and Graph based versions by assigning an
 * instance of TreeSearch or GraphSearch to its constructor.
 * 
 * @author Ravi Mohan
 * 
 */
public class DepthFirstSearch implements Busqueda {

	BusquedaCola search;

	public DepthFirstSearch(BusquedaCola search) {
		this.search = search;
	}

	public List<Accion> search(Problema p) {
		return search.search(p, new LIFOQueue<Nodo>());
	}

	public Metricas getMetrics() {
		return search.getMetrics();
	}
}