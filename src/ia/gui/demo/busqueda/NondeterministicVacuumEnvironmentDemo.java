package ia.gui.demo.busqueda;

import ia.core.entorno.aspiradora.FullyObservableVacuumEnvironmentPerceptToStateFunction;
import ia.core.entorno.aspiradora.NondeterministicVacuumAgent;
import ia.core.entorno.aspiradora.NondeterministicVacuumEnvironment;
import ia.core.entorno.aspiradora.VacuumEnvironment;
import ia.core.entorno.aspiradora.VacuumEnvironmentState;
import ia.core.entorno.aspiradora.VacuumEnvironmentViewActionTracker;
import ia.core.entorno.aspiradora.VacuumWorldActions;
import ia.core.entorno.aspiradora.VacuumWorldGoalTest;
import ia.core.entorno.aspiradora.VacuumWorldResults;
import ia.core.busqueda.framework.FuncionCostoDePasoPorDefecto;
import ia.core.busqueda.nodeterministico.NondeterministicProblem;

/**
 * Applies AND-OR-GRAPH-SEARCH to a non-deterministic version of the Vacuum World.
 * 
 * 
 * @author Andrew Brown
 */
public class NondeterministicVacuumEnvironmentDemo {
	public static void main(String[] args) {
		System.out.println("NON-DETERMINISTIC-VACUUM-ENVIRONMENT DEMO");
		System.out.println("");
		startAndOrSearch();
	}

	private static void startAndOrSearch() {
		System.out.println("AND-OR-GRAPH-SEARCH");
	    
	    NondeterministicVacuumAgent agent = new NondeterministicVacuumAgent(
        		new FullyObservableVacuumEnvironmentPerceptToStateFunction());
        // create state: both rooms are dirty and the vacuum is in room A
        VacuumEnvironmentState state = new VacuumEnvironmentState();
        state.setLocationState(VacuumEnvironment.LOCATION_A, VacuumEnvironment.LocationState.Dirty);
        state.setLocationState(VacuumEnvironment.LOCATION_B, VacuumEnvironment.LocationState.Dirty);
        state.setAgentLocation(agent, VacuumEnvironment.LOCATION_A);
        // create problem
        NondeterministicProblem problem = new NondeterministicProblem(
                state,
                new VacuumWorldActions(),
                new VacuumWorldResults(agent),
                new VacuumWorldGoalTest(agent),
                new FuncionCostoDePasoPorDefecto());
        // set the problem and agent
        agent.setProblem(problem);
        
        // create world
        NondeterministicVacuumEnvironment world = new NondeterministicVacuumEnvironment(VacuumEnvironment.LocationState.Dirty, VacuumEnvironment.LocationState.Dirty);
        world.addAgent(agent, VacuumEnvironment.LOCATION_A);
        
        // ejecutar and show plan
        System.out.println("Initial Plan: " + agent.getContingencyPlan());
        StringBuilder sb = new StringBuilder();
        world.addEnvironmentView(new VacuumEnvironmentViewActionTracker(sb));
        world.pasoHastaTerminar();
        System.out.println("Remaining Plan: " + agent.getContingencyPlan());
        System.out.println("Actions Taken: " + sb);
        System.out.println("Final State: " + world.getCurrentState());
	}
}
