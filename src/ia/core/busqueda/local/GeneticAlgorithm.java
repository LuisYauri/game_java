package ia.core.busqueda.local;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ia.core.busqueda.framework.PruebaDeMeta;
import ia.core.busqueda.framework.Metricas;
import ia.core.util.Util;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 4.8, page
 * 129.<br>
 * <br>
 * 
 * <pre>
 * function GENETIC-ALGORITHM(population, FITNESS-FN) returns an individual
 *   inputs: population, a set of individuals
 *           FITNESS-FN, a function that measures the fitness of an individual
 *           
 *   repeat
 *     new_population &lt;- empty set
 *     for i = 1 to SIZE(population) do
 *       x &lt;- RANDOM-SELECTION(population, FITNESS-FN)
 *       y &lt;- RANDOM-SELECTION(population, FITNESS-FN)
 *       child &lt;- REPRODUCE(x, y)
 *       if (small random probability) then child &lt;- MUTATE(child)
 *       add child to new_population
 *     population &lt;- new_population
 *   until some individual is fit enough, or enough time has elapsed
 *   return the best individual in population, according to FITNESS-FN
 * --------------------------------------------------------------------------------
 * function REPRODUCE(x, y) returns an individual
 *   inputs: x, y, parent individuals
 *   
 *   n &lt;- LENGTH(x); c &lt;- random number from 1 to n
 *   return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c+1, n))
 * </pre>
 * 
 * Figure 4.8 A genetic algorithm. The algorithm is the same as the one
 * diagrammed in Figure 4.6, with one variation: in this more popular version,
 * each mating of two parents produces only one offspring, not two.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * 
 * @param <A>
 *            the type of the alphabet used in the representation of the
 *            individuals in the population (this is to provide flexibility in
 *            terms of how a problem can be encoded).
 */
public class GeneticAlgorithm<A> {
	protected static final String POPULATION_SIZE = "populationSize";
	protected static final String ITERATIONS = "iterations";
	protected static final String TIME_IN_MILLISECONDS = "timeInMilliseconds";
	//
	protected Metricas metrics = new Metricas();
	//
	protected int individualLength;
	protected List<A> finiteAlphabet;
	protected double mutationProbability;
	protected Random random;

	public GeneticAlgorithm(int individualLength, Set<A> finiteAlphabet,
			double mutationProbability) {
		this(individualLength, finiteAlphabet, mutationProbability,
				new Random());
	}

	public GeneticAlgorithm(int individualLength, Set<A> finiteAlphabet,
			double mutationProbability, Random random) {
		this.individualLength = individualLength;
		this.finiteAlphabet = new ArrayList<A>(finiteAlphabet);
		this.mutationProbability = mutationProbability;
		this.random = random;

		assert (this.mutationProbability >= 0.0 && this.mutationProbability <= 1.0);
	}

	/**
	 * Returns the best individual in the specified population, according to the
	 * specified FITNESS-FN and goal test.
	 * 
	 * @param population
	 *            a set of individuals
	 * @param fitnessFn
	 *            a function that measures the fitness of an individual
	 * @param goalTest
	 *            test determines whether a given individual is fit enough to
	 *            return.
	 * @param maxTimeMilliseconds
	 *            the maximum time in milliseconds that the algorithm is to run
	 *            for (approximate). Only used if > 0L.
	 * @return the best individual in the specified population, according to the
	 *         specified FITNESS-FN and goal test.
	 */
	// function GENETIC-ALGORITHM(population, FITNESS-FN) returns an individual
	// inputs: population, a set of individuals
	// FITNESS-FN, a function that measures the fitness of an individual
	public Individuo<A> geneticAlgorithm(Set<Individuo<A>> population,
			FuncionDeCapacidad<A> fitnessFn, PruebaDeMeta goalTest,
			long maxTimeMilliseconds) {
		Individuo<A> bestIndividual = null;

		// Create a local copy of the population to work with
		population = new LinkedHashSet<Individuo<A>>(population);
		// Validate the population and setup the instrumentation
		validatePopulation(population);
		clearInstrumentation();
		setPopulationSize(population.size());

		long startTime = System.currentTimeMillis();

		// repeat
		int cnt = 0;
		do {
			bestIndividual = nextGeneration(population, fitnessFn);
			cnt++;

			// until some individual is fit enough, or enough time has elapsed
			if (maxTimeMilliseconds > 0L) {
				if ((System.currentTimeMillis() - startTime) > maxTimeMilliseconds) {
					break;
				}
			}
		} while (!goalTest.isGoalState(bestIndividual));
		setIterations(cnt);
		setTimeInMilliseconds(System.currentTimeMillis()-startTime);

		// return the best individual in population, according to FITNESS-FN
		return bestIndividual;
	}

	/**
	 * Sets the population size and number of iterations to zero.
	 */
	public void clearInstrumentation() {
		setPopulationSize(0);
		setIterations(0);
		setTimeInMilliseconds(0L);
	}

	/**
	 * Returns all the metrics of the genetic algorithm.
	 * 
	 * @return all the metrics of the genetic algorithm.
	 */
	public Metricas getMetrics() {
		return metrics;
	}

	/**
	 * Returns the population size.
	 * 
	 * @return the population size.
	 */
	public int getPopulationSize() {
		return metrics.getInt(POPULATION_SIZE);
	}

	/**
	 * Sets the population size.
	 * 
	 * @param size
	 *            the population size.
	 */
	public void setPopulationSize(int size) {
		metrics.set(POPULATION_SIZE, size);
	}

	/**
	 * Returns the number of iterations of the genetic algorithm.
	 * 
	 * @return the number of iterations of the genetic algorithm.
	 */
	public int getIterations() {
		return metrics.getInt(ITERATIONS);
	}

	/**
	 * Sets the number of iterations of the genetic algorithm.
	 * 
	 * @param cnt
	 *            the number of iterations.
	 */
	public void setIterations(int cnt) {
		metrics.set(ITERATIONS, cnt);
	}

	/**
	 * 
	 * @return the time in milliseconds that the genetic algorithm took.
	 */
	public long getTimeInMilliseconds() {
		return metrics.getLong(TIME_IN_MILLISECONDS);
	}

	/**
	 * Set the time in milliseconds that the genetic algorithm took.
	 * 
	 * @param time
	 *            the time in milliseconds that the genetic algorithm took.
	 */
	public void setTimeInMilliseconds(long time) {
		metrics.set(TIME_IN_MILLISECONDS, time);
	}

	//
	// PROTECTED METHODS
	//
	// Note: Override these protected methods to create your own desired
	// behavior.
	//
	protected Individuo<A> nextGeneration(Set<Individuo<A>> population,
			FuncionDeCapacidad<A> fitnessFn) {
		// new_population <- empty set
		Set<Individuo<A>> newPopulation = new HashSet<Individuo<A>>();

		// Represent the population as a list to simplify/optimize random
		// selection.
		List<Individuo<A>> populationAsList = new ArrayList<Individuo<A>>(
				population);

		// for i = 1 to SIZE(population) do
		for (int i = 0; i < population.size(); i++) {
			// x <- RANDOM-SELECTION(population, FITNESS-FN)
			Individuo<A> x = randomSelection(populationAsList, fitnessFn);
			// y <- RANDOM-SELECTION(population, FITNESS-FN)
			Individuo<A> y = randomSelection(populationAsList, fitnessFn);
			// child <- REPRODUCE(x, y)
			Individuo<A> child = reproduce(x, y);
			// if (small random probability) then child <- MUTATE(child)
			if (random.nextDouble() <= this.mutationProbability) {
				child = mutate(child);
			}
			// add child to new_population
			newPopulation.add(child);
		}
		// population <- new_population
		population.clear();
		population.addAll(newPopulation);

		return retrieveBestIndividual(population, fitnessFn);
	}

	// RANDOM-SELECTION(population, FITNESS-FN)
	protected Individuo<A> randomSelection(List<Individuo<A>> population,
			FuncionDeCapacidad<A> fitnessFn) {
		Individuo<A> selected = null;

		// Determine all of the fitness values
		double[] fValues = new double[population.size()];
		for (int i = 0; i < population.size(); i++) {
			fValues[i] = fitnessFn.getValor(population.get(i));
		}

		// Normalize the fitness values
		fValues = Util.normalize(fValues);
		double prob = random.nextDouble();
		double totalSoFar = 0.0;
		for (int i = 0; i < fValues.length; i++) {
			// Are at last element so assign by default
			// in case there are rounding issues with the normalized values
			totalSoFar += fValues[i];
			if (prob <= totalSoFar) {
				selected = population.get(i);
				break;
			}
		}

		// selected may not have been assigned
		// if there was a rounding error in the
		// addition of the normalized values (i.e. did not total to 1.0)
		if (null == selected) {
			// Assign the last value
			selected = population.get(population.size() - 1);
		}

		return selected;
	}

	// function REPRODUCE(x, y) returns an individual
	// inputs: x, y, parent individuals
	protected Individuo<A> reproduce(Individuo<A> x, Individuo<A> y) {
		// n <- LENGTH(x);
		// Note: this is = this.individualLength
		// c <- random number from 1 to n
		int c = randomOffset(individualLength);
		// return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c+1, n))
		List<A> childRepresentation = new ArrayList<A>();
		childRepresentation.addAll(x.getRepresentacion().subList(0, c));
		childRepresentation.addAll(y.getRepresentacion().subList(c,
				individualLength));

		Individuo<A> child = new Individuo<A>(childRepresentation);
		return child;
	}

	protected Individuo<A> mutate(Individuo<A> child) {
		int mutateOffset = randomOffset(individualLength);
		int alphaOffset = randomOffset(finiteAlphabet.size());

		List<A> mutatedRepresentation = new ArrayList<A>(
				child.getRepresentacion());

		mutatedRepresentation
				.set(mutateOffset, finiteAlphabet.get(alphaOffset));

		Individuo<A> mutatedChild = new Individuo<A>(mutatedRepresentation);

		return mutatedChild;
	}

	protected Individuo<A> retrieveBestIndividual(
			Set<Individuo<A>> population, FuncionDeCapacidad<A> fitnessFn) {
		Individuo<A> bestIndividual = null;
		double bestSoFarFValue = Double.NEGATIVE_INFINITY;

		for (Individuo<A> individual : population) {
			double fValue = fitnessFn.getValor(individual);
			if (fValue > bestSoFarFValue) {
				bestIndividual = individual;
				bestSoFarFValue = fValue;
			}
		}

		return bestIndividual;
	}

	protected int randomOffset(int length) {
		return random.nextInt(length);
	}

	protected void validatePopulation(Set<Individuo<A>> population) {
		// Require at least 1 individual in population in order
		// for algorithm to work
		if (population.size() < 1) {
			throw new IllegalArgumentException(
					"Must start with at least a population of size 1");
		}
		// String lengths are assumed to be of fixed size,
		// therefore ensure initial populations lengths correspond to this
		for (Individuo<A> individual : population) {
			if (individual.longitud() != this.individualLength) {
				throw new IllegalArgumentException("Individual [" + individual
						+ "] in population is not the required length of "
						+ this.individualLength);
			}
		}
	}
}