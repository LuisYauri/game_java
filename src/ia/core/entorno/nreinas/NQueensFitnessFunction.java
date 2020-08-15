package ia.core.entorno.nreinas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ia.core.busqueda.framework.PruebaDeMeta;
import ia.core.busqueda.local.FuncionDeCapacidad;
import ia.core.busqueda.local.Individuo;
import ia.core.util.estructuradedatos.UbicacionXY;

/**
 * Una clase cuyo propósito es evaluar la capacidad de los individuos de NReinas
 * y proveer métodos utiles para traducir entre una representación en el 
 * TableroNReinas y la representación String usada por el AlgoritmoGenetico.
 * 
 */
public class NQueensFitnessFunction implements FuncionDeCapacidad<Integer>, PruebaDeMeta {

	private final NQueensGoalTest goalTest = new NQueensGoalTest();

	public NQueensFitnessFunction() {

	}

	//
	// START - Interface FuncionDeCapacidad
	public double getValor(Individuo<Integer> individual) {
		double fitness = 0;

		TableroNReinas board = getBoardForIndividual(individual);
		int boardSize = board.getTamanno();

		// Calculate the number of non-attacking pairs of queens (refer to AIMA
		// page 117).
		List<UbicacionXY> qPositions = board.getPosicionesDeReina();
		for (int fromX = 0; fromX < (boardSize - 1); fromX++) {
			for (int toX = fromX + 1; toX < boardSize; toX++) {
				int fromY = qPositions.get(fromX).getCoordenadaY();
				boolean nonAttackingPair = true;
				// Check right beside
				int toY = fromY;
				if (board.reinaExisteEn(new UbicacionXY(toX, toY))) {
					nonAttackingPair = false;
				}
				// Check right and above
				toY = fromY - (toX - fromX);
				if (toY >= 0) {
					if (board.reinaExisteEn(new UbicacionXY(toX, toY))) {
						nonAttackingPair = false;
					}
				}
				// Check right and below
				toY = fromY + (toX - fromX);
				if (toY < boardSize) {
					if (board.reinaExisteEn(new UbicacionXY(toX, toY))) {
						nonAttackingPair = false;
					}
				}

				if (nonAttackingPair) {
					fitness += 1.0;
				}
			}
		}

		return fitness;
	}

	// END - Interface FuncionDeCapacidad
	//

	//
	// START - Interface PruebaDeMeta
	@SuppressWarnings("unchecked")
	public boolean isGoalState(Object state) {
		return goalTest.isGoalState(getBoardForIndividual((Individuo<Integer>) state));
	}

	// END - Interface PruebaDeMeta
	//

	public TableroNReinas getBoardForIndividual(Individuo<Integer> individual) {
		int boardSize = individual.longitud();
		TableroNReinas board = new TableroNReinas(boardSize);
		for (int i = 0; i < boardSize; i++) {
			int pos = individual.getRepresentacion().get(i);
			board.agregarReinaEn(new UbicacionXY(i, pos));
		}

		return board;
	}

	public Individuo<Integer> generateRandomIndividual(int boardSize) {

		List<Integer> individualRepresentation = new ArrayList<Integer>();
		for (int i = 0; i < boardSize; i++) {
			individualRepresentation.add(new Random().nextInt(boardSize));
		}
		
		Individuo<Integer> individual = new Individuo<Integer>(individualRepresentation);

		return individual;
	}

	public Set<Integer> getFiniteAlphabetForBoardOfSize(int size) {
		Set<Integer> fab = new HashSet<Integer>();

		for (int i = 0; i < size; i++) {
			fab.add(i);
		}

		return fab;
	}
}