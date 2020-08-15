package ia.core.entorno.mapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.NotificadorVistaEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.Estado;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.agente.impl.EstadoDinamico;
import ia.core.busqueda.framework.Problema;
import ia.core.busqueda.framework.Busqueda;
import ia.core.busqueda.framework.AgenteSolucionadorDeProblemaSimple;

/**
 * @author Ciaran O'Reilly
 * 
 */
public class MapAgent extends AgenteSolucionadorDeProblemaSimple {
	private Map map = null;

	private NotificadorVistaEntorno notifier = null;

	private EstadoDinamico state = new EstadoDinamico();

	private Busqueda search = null;

	private String[] goalTests = null;

	private int goalTestPos = 0;

	public MapAgent(Map map, NotificadorVistaEntorno notifier, Busqueda search) {
		this.map = map;
		this.notifier = notifier;
		this.search = search;
	}

	public MapAgent(Map map, NotificadorVistaEntorno notifier, Busqueda search,
			int maxGoalsToFormulate) {
		super(maxGoalsToFormulate);
		this.map = map;
		this.notifier = notifier;
		this.search = search;
	}

	public MapAgent(Map map, NotificadorVistaEntorno notifier, Busqueda search,
			String[] goalTests) {
		super(goalTests.length);
		this.map = map;
		this.notifier = notifier;
		this.search = search;
		this.goalTests = new String[goalTests.length];
		System.arraycopy(goalTests, 0, this.goalTests, 0, goalTests.length);
	}

	//
	// PROTECTED METHODS
	//
	@Override
	protected Estado updateState(Percepcion p) {
		PercepcionDinamica dp = (PercepcionDinamica) p;

		state.setAttribute(DynAttributeNames.AGENT_LOCATION,
				dp.getAttribute(DynAttributeNames.PERCEPT_IN));

		return state;
	}

	@Override
	protected Object formulateGoal() {
		Object goal = null;
		if (null == goalTests) {
			goal = map.randomlyGenerateDestination();
		} else {
			goal = goalTests[goalTestPos];
			goalTestPos++;
		}
		notifier.notifyViews("CurrentLocation=In("
				+ state.getAttribute(DynAttributeNames.AGENT_LOCATION)
				+ "), Goal=In(" + goal + ")");

		return goal;
	}

	@Override
	protected Problema formulateProblem(Object goal) {
		return new BidirectionalMapProblem(map,
				(String) state.getAttribute(DynAttributeNames.AGENT_LOCATION),
				(String) goal);
	}

	@Override
	protected List<Accion> search(Problema problem) {
		List<Accion> actions = new ArrayList<Accion>();
		try {
			List<Accion> sactions = search.search(problem);
			for (Accion action : sactions) {
				actions.add(action);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return actions;
	}

	@Override
	protected void notifyViewOfMetrics() {
		Set<String> keys = search.getMetrics().keySet();
		for (String key : keys) {
			notifier.notifyViews("METRIC[" + key + "]="
					+ search.getMetrics().get(key));
		}
	}
}
