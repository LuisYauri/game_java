package ia.core.entorno.mundowumpus;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ia.core.agente.Accion;
import ia.core.entorno.mundowumpus.accion.Forward;
import ia.core.entorno.mundowumpus.accion.TurnLeft;
import ia.core.entorno.mundowumpus.accion.TurnRight;
import ia.core.busqueda.framework.FuncionAcciones;
import ia.core.busqueda.framework.FuncionResultado;

/**
 * Factory class for constructing functions for use in the Wumpus World environment.
 * 
 * @author Federico Baron
 * @author Alessandro Daniele
 * @author Ciaran O'Reilly
 */
public class WumpusFunctionFactory {
	private static FuncionResultado resultFunction = null;

	public static FuncionAcciones getActionsFunction(WumpusCave cave) {
		return new WumpusActionsFunction(cave);
	}

	public static FuncionResultado getResultFunction() {
		if (null == resultFunction) {
			resultFunction = new WumpusResultFunction();
		}
		return resultFunction;
	}

	private static class WumpusActionsFunction implements FuncionAcciones {
		private WumpusCave cave;

		public WumpusActionsFunction(WumpusCave cave) {
			this.cave = cave;
		}

		@Override
		public Set<Accion> actions(Object state) {
			Set<Accion> actions = new LinkedHashSet<Accion>();
			AgentPosition position = null;
			try {
				position = (AgentPosition) state;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			List<AgentPosition> linkedPositions = cave.getLocationsLinkedTo(position);
			for (AgentPosition linkPos : linkedPositions) {
				if (linkPos.getX() != position.getX() || linkPos.getY() != position.getY()) {
					actions.add(new Forward(position));
				}
			}
			actions.add(new TurnLeft(position.getOrientation()));
			actions.add(new TurnRight(position.getOrientation()));

			return actions;
		}
	}

	private static class WumpusResultFunction implements FuncionResultado {
		public WumpusResultFunction() {
		}

		@Override
		public Object result(Object s, Accion a) {

			if (a instanceof Forward) {
				Forward fa = (Forward) a;
				
				return fa.getToPosition();
			}
			else if (a instanceof TurnLeft) {
				TurnLeft tLeft = (TurnLeft) a;
				AgentPosition res = (AgentPosition) s;

				return new AgentPosition(res.getX(), res.getY(), tLeft.getToOrientation());
			}
			else if (a instanceof TurnRight) {
				TurnRight tRight = (TurnRight) a;
				AgentPosition res = (AgentPosition) s;

				return new AgentPosition(res.getX(), res.getY(), tRight.getToOrientation());
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}
