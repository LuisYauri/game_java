package ia.core.busqueda.local;

/**
 * Cada estado es ranqueado por la función objetivo, o la función capacidad
 * (en terminología de algoritmos genéticos). Una función de capacidad debería
 * devolver valores altos paro los mejores estados.
 * 
 * 
 * @param <A>
 *            representación de los individuos en la población (esto es para
 *            probar la flexibilidad en términos de como un problema puede ser
 *            codificado).
 */
public interface FuncionDeCapacidad<A> {

	/**
	 * 
	 * @param individuo
	 *            el individuo cuya capacidad será evaluada.
	 * @return el valor de la capacidad del individuo (los mejores tienen
         *         valores altos).
	 */
	double getValor(Individuo<A> individuo);
}
