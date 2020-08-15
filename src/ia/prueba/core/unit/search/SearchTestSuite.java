package ia.prueba.core.unit.search;

import ia.prueba.core.unit.search.csp.AssignmentTest;
import ia.prueba.core.unit.search.csp.CSPTest;
import ia.prueba.core.unit.search.csp.MapCSPTest;
import ia.prueba.core.unit.search.framework.NodeTest;
import ia.prueba.core.unit.search.framework.SolutionCheckerTest;
import ia.prueba.core.unit.search.informed.AStarSearchTest;
import ia.prueba.core.unit.search.informed.GreedyBestFirstSearchTest;
import ia.prueba.core.unit.search.informed.RecursiveBestFirstSearchTest;
import ia.prueba.core.unit.search.local.SimulatedAnnealingSearchTest;
import ia.prueba.core.unit.search.nondeterministic.AndOrSearchTest;
import ia.prueba.core.unit.search.online.LRTAStarAgentTest;
import ia.prueba.core.unit.search.online.OnlineDFSAgentTest;
import ia.prueba.core.unit.search.uninformed.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AssignmentTest.class, CSPTest.class, MapCSPTest.class,
		AStarSearchTest.class, GreedyBestFirstSearchTest.class,
		RecursiveBestFirstSearchTest.class, 
                SimulatedAnnealingSearchTest.class, AndOrSearchTest.class,
		LRTAStarAgentTest.class, OnlineDFSAgentTest.class,
		BidirectionalSearchTest.class, BreadthFirstSearchTest.class,
		DepthFirstSearchTest.class, DepthLimitedSearchTest.class,
		IterativeDeepeningSearchTest.class, UniformCostSearchTest.class,
		NodeTest.class, SolutionCheckerTest.class })
public class SearchTestSuite {
}
