package ia.gui.demo.busqueda;

import ia.core.busqueda.csp.Assignment;
import ia.core.busqueda.csp.BacktrackingStrategy;
import ia.core.busqueda.csp.CSP;
import ia.core.busqueda.csp.CSPStateListener;
import ia.core.busqueda.csp.ImprovedBacktrackingStrategy;
import ia.core.busqueda.csp.MapCSP;
import ia.core.busqueda.csp.MinConflictsStrategy;
import ia.core.busqueda.csp.SolutionStrategy;

/**
 * Demonstrates the performance of different constraint solving strategies.
 * The map coloring problem from the textbook is used as CSP.
 * 
 * @author Ruediger Lunde
 */

public class MapColoringCSPDemo {
	public static void main(String[] args) {
		CSP csp = new MapCSP();
		StepCounter stepCounter = new StepCounter();
		SolutionStrategy solver;
		
		solver = new MinConflictsStrategy(1000);
		solver.addCSPStateListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Minimum Conflicts)");
		System.out.println(solver.solve(csp.copyDomains()));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new ImprovedBacktrackingStrategy(true, true, true, true);
		solver.addCSPStateListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking + MRV + DEG + AC3 + LCV)");
		System.out.println(solver.solve(csp.copyDomains()));
		System.out.println(stepCounter.getResults() + "\n");
		
		solver = new BacktrackingStrategy();
		solver.addCSPStateListener(stepCounter);
		stepCounter.reset();
		System.out.println("Map Coloring (Backtracking)");
		System.out.println(solver.solve(csp.copyDomains()));
		System.out.println(stepCounter.getResults() + "\n");
	}
	
	/** Counts assignment and domain changes during CSP solving. */
	protected static class StepCounter implements CSPStateListener {
		private int assignmentCount = 0;
		private int domainCount = 0;
		
		@Override
		public void stateChanged(Assignment assignment, CSP csp) {
			++assignmentCount;
		}
		
		@Override
		public void stateChanged(CSP csp) {
			++domainCount;
		}
		
		public void reset() {
			assignmentCount = 0;
			domainCount = 0;
		}
		
		public String getResults() {
			StringBuffer result = new StringBuffer();
			result.append("assignment changes: " + assignmentCount);
			if (domainCount != 0)
				result.append(", domain changes: " + domainCount);
			return result.toString();
		}
	}
}
