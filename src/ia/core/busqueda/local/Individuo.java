package ia.core.busqueda.local;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Un estado en un algoritmo genético se representa como un individuo de la
 * población.
 * 
 * @param <A>
 *            el tipo usado en la representación de los individuos en la
 *            población (esto proporciona flexibilidad en terminos de como
 *            un problema puede ser codificado).
 */
public class Individuo<A> {
	private List<A> representacion = new ArrayList<A>();

	/**
	 * Construye un individuo usando la representación provista.
	 * 
	 * @param representation
	 *            la representación del individuo.
	 */
	public Individuo(List<A> representation) {
		this.representacion = Collections.unmodifiableList(representation);
	}

	/**
	 * 
	 * @return la representación del individuo.
	 */
	public List<A> getRepresentacion() {
		return representacion;
	}

	/**
	 * 
	 * @return la longitud de la representación del individuo.
	 */
	public int longitud() {
		return representacion.size();
	}
}