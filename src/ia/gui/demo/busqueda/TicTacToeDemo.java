package ia.gui.demo.busqueda;

import ia.core.entorno.tresenraya.TicTacToeGame;
import ia.core.entorno.tresenraya.TicTacToeState;
import ia.core.busqueda.adversarios.AdversarialSearch;
import ia.core.busqueda.adversarios.AlphaBetaSearch;
import ia.core.busqueda.adversarios.MinimaxSearch;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * Applies Minimax search and alpha-beta pruning to find optimal moves for the
 * Tic-tac-toe game.
 * 
 * @author Ruediger Lunde
 */
public class TicTacToeDemo {
	public static void main(String[] args) {
		System.out.println("TIC-TAC-TOE DEMO");
		System.out.println("");
		startMinimaxDemo();
		startAlphaBetaDemo();
	}

	private static void startMinimaxDemo() {
		System.out.println("MINI MAX DEMO\n");
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeState currState = game.getInitialState();
		AdversarialSearch<TicTacToeState, UbicacionXY> search = MinimaxSearch
				.createFor(game);
		while (!(game.isTerminal(currState))) {
			System.out.println(game.getPlayer(currState) + "  playing ... ");
			UbicacionXY action = search.makeDecision(currState);
			currState = game.getResult(currState, action);
			System.out.println(currState);
		}
		System.out.println("MINI MAX DEMO done");
	}

	private static void startAlphaBetaDemo() {
		System.out.println("ALPHA BETA DEMO\n");
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeState currState = game.getInitialState();
		AdversarialSearch<TicTacToeState, UbicacionXY> search = AlphaBetaSearch
				.createFor(game);
		while (!(game.isTerminal(currState))) {
			System.out.println(game.getPlayer(currState) + "  playing ... ");
			UbicacionXY action = search.makeDecision(currState);
			currState = game.getResult(currState, action);
			System.out.println(currState);
		}
		System.out.println("ALPHA BETA DEMO done");
	}
}
