package ia.core.entorno.entxy;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.agente.Agente;
import ia.core.agente.ObjetoEntorno;
import ia.core.agente.EstadoEntorno;
import ia.core.agente.Percepcion;
import ia.core.agente.impl.EntornoAbstracto;
import ia.core.agente.impl.PercepcionDinamica;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class XYEnvironment extends EntornoAbstracto {
	private XYEnvironmentState envState = null;

	//
	// PUBLIC METHODS
	//
	public XYEnvironment(int width, int height) {
		assert (width > 0);
		assert (height > 0);

		envState = new XYEnvironmentState(width, height);
	}

	@Override
	public EstadoEntorno getCurrentState() {
		return envState;
	}

	@Override
	public EstadoEntorno executeAction(Agente a, Accion action) {
		return envState;
	}

	@Override
	public Percepcion getPerceptSeenBy(Agente anAgent) {
		return new PercepcionDinamica();
	}

	public void addObjectToLocation(ObjetoEntorno eo, UbicacionXY loc) {
		moveObjectToAbsoluteLocation(eo, loc);
	}

	public void moveObjectToAbsoluteLocation(ObjetoEntorno eo,
			UbicacionXY loc) {
		// Ensure the object is not already at a location
		envState.moveObjectToAbsoluteLocation(eo, loc);

		// Ensure is added to the environment
		agregarObjetoEntorno(eo);
	}

	public void moveObject(ObjetoEntorno eo, UbicacionXY.Direccion direction) {
		UbicacionXY presentLocation = envState.getCurrentLocationFor(eo);

		if (null != presentLocation) {
			UbicacionXY locationToMoveTo = presentLocation.ubicacionA(direction);
			if (!(isBlocked(locationToMoveTo))) {
				moveObjectToAbsoluteLocation(eo, locationToMoveTo);
			}
		}
	}

	public UbicacionXY getCurrentLocationFor(ObjetoEntorno eo) {
		return envState.getCurrentLocationFor(eo);
	}

	public Set<ObjetoEntorno> getObjectsAt(UbicacionXY loc) {
		return envState.getObjectsAt(loc);
	}

	public Set<ObjetoEntorno> getObjectsNear(Agente agent, int radius) {
		return envState.getObjectsNear(agent, radius);
	}

	public boolean isBlocked(UbicacionXY loc) {
		for (ObjetoEntorno eo : envState.getObjectsAt(loc)) {
			if (eo instanceof Wall) {
				return true;
			}
		}
		return false;
	}

	public void makePerimeter() {
		for (int i = 0; i < envState.width; i++) {
			UbicacionXY loc = new UbicacionXY(i, 0);
			UbicacionXY loc2 = new UbicacionXY(i, envState.height - 1);
			envState.moveObjectToAbsoluteLocation(new Wall(), loc);
			envState.moveObjectToAbsoluteLocation(new Wall(), loc2);
		}

		for (int i = 0; i < envState.height; i++) {
			UbicacionXY loc = new UbicacionXY(0, i);
			UbicacionXY loc2 = new UbicacionXY(envState.width - 1, i);
			envState.moveObjectToAbsoluteLocation(new Wall(), loc);
			envState.moveObjectToAbsoluteLocation(new Wall(), loc2);
		}
	}
}

class XYEnvironmentState implements EstadoEntorno {
	int width;
	int height;

	private Map<UbicacionXY, Set<ObjetoEntorno>> objsAtLocation = new LinkedHashMap<UbicacionXY, Set<ObjetoEntorno>>();

	public XYEnvironmentState(int width, int height) {
		this.width = width;
		this.height = height;
		for (int h = 1; h <= height; h++) {
			for (int w = 1; w <= width; w++) {
				objsAtLocation.put(new UbicacionXY(h, w),
						new LinkedHashSet<ObjetoEntorno>());
			}
		}
	}

	public void moveObjectToAbsoluteLocation(ObjetoEntorno eo,
			UbicacionXY loc) {
		// Ensure is not already at another location
		for (Set<ObjetoEntorno> eos : objsAtLocation.values()) {
			if (eos.remove(eo)) {
				break; // Should only every be at 1 location
			}
		}
		// Add it to the location specified
		getObjectsAt(loc).add(eo);
	}

	public Set<ObjetoEntorno> getObjectsAt(UbicacionXY loc) {
		Set<ObjetoEntorno> objectsAt = objsAtLocation.get(loc);
		if (null == objectsAt) {
			// Always ensure an empty Set is returned
			objectsAt = new LinkedHashSet<ObjetoEntorno>();
			objsAtLocation.put(loc, objectsAt);
		}
		return objectsAt;
	}

	public UbicacionXY getCurrentLocationFor(ObjetoEntorno eo) {
		for (UbicacionXY loc : objsAtLocation.keySet()) {
			if (objsAtLocation.get(loc).contains(eo)) {
				return loc;
			}
		}
		return null;
	}

	public Set<ObjetoEntorno> getObjectsNear(Agente agent, int radius) {
		Set<ObjetoEntorno> objsNear = new LinkedHashSet<ObjetoEntorno>();

		UbicacionXY agentLocation = getCurrentLocationFor(agent);
		for (UbicacionXY loc : objsAtLocation.keySet()) {
			if (withinRadius(radius, agentLocation, loc)) {
				objsNear.addAll(objsAtLocation.get(loc));
			}
		}
		// Ensure the 'agent' is not included in the Set of
		// objects near
		objsNear.remove(agent);

		return objsNear;
	}

	@Override
	public String toString() {
		return "XYEnvironmentState:" + objsAtLocation.toString();
	}

	//
	// PRIVATE METHODS
	//
	private boolean withinRadius(int radius, UbicacionXY agentLocation,
			UbicacionXY objectLocation) {
		int xdifference = agentLocation.getCoordenadaX()
				- objectLocation.getCoordenadaX();
		int ydifference = agentLocation.getCoordenadaY()
				- objectLocation.getCoordenadaY();
		return Math.sqrt((xdifference * xdifference)
				+ (ydifference * ydifference)) <= radius;
	}
}