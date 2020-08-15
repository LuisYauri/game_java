package ia.gui.aplicaciones.aspiradora;

import ia.core.agente.impl.AgenteAbstracto;
import ia.core.entorno.aspiradora.FullyObservableVacuumEnvironmentPerceptToStateFunction;
import ia.core.entorno.aspiradora.ModelBasedReflexVacuumAgent;
import ia.core.entorno.aspiradora.NondeterministicVacuumAgent;
import ia.core.entorno.aspiradora.NondeterministicVacuumEnvironment;
import ia.core.entorno.aspiradora.ReflexVacuumAgent;
import ia.core.entorno.aspiradora.SimpleReflexVacuumAgent;
import ia.core.entorno.aspiradora.TableDrivenVacuumAgent;
import ia.core.entorno.aspiradora.VacuumEnvironment;
import ia.core.entorno.aspiradora.VacuumWorldActions;
import ia.core.entorno.aspiradora.VacuumWorldGoalTest;
import ia.core.entorno.aspiradora.VacuumWorldResults;
import ia.core.busqueda.framework.FuncionCostoDePasoPorDefecto;
import ia.core.busqueda.nodeterministico.NondeterministicProblem;
import ia.gui.framework.AgentAppController;
import ia.gui.framework.AgentAppFrame;
import ia.gui.framework.SimulationThread;
import ia.gui.framework.MessageLogger;

/**
 * Defines how to react on user button events.
 * 
 * @author Ruediger Lunde
 */
public class VacuumController extends AgentAppController {
	
	protected VacuumEnvironment env = null;
	protected AgenteAbstracto agent = null;
	protected boolean isPrepared = false;
	
	/** Prepares next simulation if that makes sense. */
	@Override
	public void clear() {
		if (!isPrepared())
		prepare(null);
	}

	/**
	 * Creates a vacuum environment and a corresponding agent based on the
	 * state of the selectors and finally passes the environment to the viewer.
	 */
	@Override
	public void prepare(String changedSelector) {
		AgentAppFrame.SelectionState selState = frame.getSelection();
		env = null;
		switch (selState.getIndex(VacuumFrame.ENV_SEL)) {
		case 0:
			env = new VacuumEnvironment();
			break;
		case 1:
			env = new NondeterministicVacuumEnvironment();
			break;
		}
		agent = null;
		switch (selState.getIndex(VacuumFrame.AGENT_SEL)) {
		case 0:
			agent = new TableDrivenVacuumAgent();
			break;
		case 1:
			agent = new ReflexVacuumAgent();
			break;
		case 2:
			agent = new SimpleReflexVacuumAgent();
			break;
		case 3:
			agent = new ModelBasedReflexVacuumAgent();
			break;
		case 4:
			agent = createNondeterministicVacuumAgent();
			break;
		}
		if (env != null && agent != null) {
			frame.getEnvView().setEnvironment(env);
			env.agregarAgent(agent);
			if (agent instanceof NondeterministicVacuumAgent) {
				// Set the problem now for this kind of agent
		        // set the problem and agent
		        ((NondeterministicVacuumAgent)agent).setProblem(createNondeterministicProblem());
			}
			isPrepared = true;
		}
	}
	
	/** Checks whether simulation can be started. */
	@Override
	public boolean isPrepared() {
		return isPrepared && !env.estaHecha();
	}

	/** Starts simulation. */
	@Override
	public void run(MessageLogger logger) {
		logger.log("<simulation-log>");
		try {
			while (!env.estaHecha() && !frame.simulationPaused()) {
				Thread.sleep(500);
				env.paso();
			}
		} catch (InterruptedException e) {}
		logger.log("Performance: "
				+ env.getMedidaPerformance(agent));
		logger.log("</simulation-log>\n");
	}

	/** Executes one simulation paso. */
	@Override
	public void step(MessageLogger logger) {
		env.paso();
	}

	/** Updates the status of the frame after simulation has finished. */
	public void update(SimulationThread simulationThread) {
		if (simulationThread.isCanceled()) {
			frame.setStatus("Task canceled.");
			isPrepared = false;
		} else if (frame.simulationPaused()){
			frame.setStatus("Task paused.");
		} else {
			frame.setStatus("Task completed.");
		}
	}
	
	//
	// PRIVATE METHODS
	//
	private NondeterministicVacuumAgent createNondeterministicVacuumAgent() {
		NondeterministicVacuumAgent agent = new NondeterministicVacuumAgent(
        		new FullyObservableVacuumEnvironmentPerceptToStateFunction());
        
        return agent;
	}
	
	private NondeterministicProblem createNondeterministicProblem() {
		// create problem
        NondeterministicProblem problem = new NondeterministicProblem(
                env.getCurrentState(),
                new VacuumWorldActions(),
                new VacuumWorldResults(agent),
                new VacuumWorldGoalTest(agent),
                new FuncionCostoDePasoPorDefecto());
        
        return problem;
	}
}

