package ia.prueba.core.unit.environment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ia.prueba.core.unit.environment.cellworld.CellWorldTest;
import ia.prueba.core.unit.environment.eightpuzzle.EightPuzzleBoardMoveTest;
import ia.prueba.core.unit.environment.eightpuzzle.EightPuzzleBoardTest;
import ia.prueba.core.unit.environment.eightpuzzle.EightPuzzleFunctionFactoryTest;
import ia.prueba.core.unit.environment.eightpuzzle.MisplacedTileHeuristicFunctionTest;
import ia.prueba.core.unit.environment.map.MapAgentTest;
import ia.prueba.core.unit.environment.map.MapEnvironmentTest;
import ia.prueba.core.unit.environment.map.MapFunctionFactoryTest;
import ia.prueba.core.unit.environment.map.MapStepCostFunctionTest;
import ia.prueba.core.unit.environment.map.MapTest;
import ia.prueba.core.unit.environment.nqueens.NQueensBoardTest;
import ia.prueba.core.unit.environment.nqueens.NQueensFitnessFunctionTest;
import ia.prueba.core.unit.environment.nqueens.NQueensFunctionFactoryTest;
import ia.prueba.core.unit.environment.nqueens.NQueensGoalTestTest;
import ia.prueba.core.unit.environment.tictactoe.TicTacToeTest;
import ia.prueba.core.unit.environment.vacuum.ModelBasedReflexVacuumAgentTest;
import ia.prueba.core.unit.environment.vacuum.ReflexVacuumAgentTest;
import ia.prueba.core.unit.environment.vacuum.SimpleReflexVacuumAgentTest;
import ia.prueba.core.unit.environment.vacuum.TableDrivenVacuumAgentTest;
import ia.prueba.core.unit.environment.vacuum.VacuumEnvironmentTest;
import ia.prueba.core.unit.environment.wumpusworld.HybridWumpusAgentTest;
import ia.prueba.core.unit.environment.wumpusworld.WumpusFunctionFactoryTest;
import ia.prueba.core.unit.environment.wumpusworld.WumpusKnowledgeBaseTest;
import ia.prueba.core.unit.environment.xyenv.XYEnvironmentTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ CellWorldTest.class, EightPuzzleBoardMoveTest.class,
		EightPuzzleBoardTest.class, EightPuzzleFunctionFactoryTest.class,
		MisplacedTileHeuristicFunctionTest.class, TicTacToeTest.class,
		MapAgentTest.class, MapEnvironmentTest.class,
		MapStepCostFunctionTest.class, MapFunctionFactoryTest.class,
		MapTest.class, NQueensBoardTest.class,
		NQueensFitnessFunctionTest.class, NQueensGoalTestTest.class,
		NQueensFunctionFactoryTest.class,
		ModelBasedReflexVacuumAgentTest.class, ReflexVacuumAgentTest.class,
		SimpleReflexVacuumAgentTest.class, TableDrivenVacuumAgentTest.class,
		VacuumEnvironmentTest.class, HybridWumpusAgentTest.class, 
		WumpusFunctionFactoryTest.class,
		WumpusKnowledgeBaseTest.class, XYEnvironmentTest.class })
public class EnvironmentTestSuite {

}
