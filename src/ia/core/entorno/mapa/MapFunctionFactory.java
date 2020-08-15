package ia.core.entorno.mapa;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionPercepcionAEstado;
import ia.core.busqueda.framework.FuncionResultado;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class MapFunctionFactory {
	private static FuncionResultado _resultFunction = null;
	private static FuncionPercepcionAEstado _perceptToStateFunction = null;

	public static FuncionAcciones getActionsFunction(Map map) {
		return new MapActionsFunction(map);
	}

	public static FuncionResultado getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new MapResultFunction();
		}
		return _resultFunction;
	}

	private static class MapActionsFunction implements FuncionAcciones {
		private Map map = null;

		public MapActionsFunction(Map map) {
			this.map = map;
		}

		public Set<Accion> actions(Object state) {
			Set<Accion> actions = new LinkedHashSet<Accion>();
			String location = state.toString();

			List<String> linkedLocations = map.getLocationsLinkedTo(location);
			for (String linkLoc : linkedLocations) {
				actions.add(new MoveToAction(linkLoc));
			}

			return actions;
		}
	}

	public static FuncionPercepcionAEstado getPerceptToStateFunction() {
		if (null == _perceptToStateFunction) {
			_perceptToStateFunction = new MapPerceptToStateFunction();
		}
		return _perceptToStateFunction;
	}

	private static class MapResultFunction implements FuncionResultado {
		public MapResultFunction() {
		}

		public Object result(Object s, Accion a) {

			if (a instanceof MoveToAction) {
				MoveToAction mta = (MoveToAction) a;

				return mta.getToLocation();
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}

	private static class MapPerceptToStateFunction implements
			FuncionPercepcionAEstado {
		public Object getState(Percepcion p) {
			return ((PercepcionDinamica) p)
					.getAttribute(DynAttributeNames.PERCEPT_IN);
		}
	}
}
