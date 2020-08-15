package ia.gui.demo.agente;

import ia.core.agente.Agente;
import ia.core.agente.Entorno;
import ia.core.agente.VistaEntorno;
import ia.core.agente.impl.VistaEntornoSimple;
import ia.core.entorno.aspiradora.ModelBasedReflexVacuumAgent;
import ia.core.entorno.aspiradora.VacuumEnvironment;

/**
 * Demonstrates, how to set up a simple environment, place an agent in it,
 * and run it. The vacuum world is used as a simple example.
 * 
 * @author Ruediger Lunde
 */
public class TrivialVacuumDemo {
	public static void main(String[] args) {
		// create environment with random state of cleaning.
		Entorno env = new VacuumEnvironment();
		VistaEntorno view = new VistaEntornoSimple();
		env.addEnvironmentView(view);
		
		Agente a = null;
		a = new ModelBasedReflexVacuumAgent();
		// a = new ReflexVacuumAgent();
		// a = new SimpleReflexVacuumAgent();
		// a = new TableDrivenVacuumAgent();
		
		env.agregarAgent(a);
		env.paso(16);
		env.notifyViews("Performance=" + env.getMedidaPerformance(a));
	}
}
