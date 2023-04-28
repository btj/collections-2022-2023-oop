package collections_oop;

import java.util.Arrays;
import java.util.stream.Stream;

public interface Set {
	
	/**
	 * @inspects | this
	 * @creates | result
	 * @post | result != null
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 * @post The array has no duplicates.
	 *       | Arrays.stream(result).distinct().count() == result.length
	 */
	Object[] toArray();
	
	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	int size();
	
	default Stream<Object> stream() { return Arrays.stream(toArray()); }
	
	/**
	 * @inspects | this
	 * @post | result == stream().anyMatch(e -> e.equals(element))
	 */
	boolean contains(Object element);
	
	/**
	 * @pre | element != null
	 * @mutates | this
	 * @post | contains(element)
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> contains(e))
	 * @post | stream().allMatch(e ->
	 *       |     e.equals(element) || Arrays.stream(old(toArray())).anyMatch(e1 -> e1.equals(e)))
	 */
	void add(Object element);
	
	/**
	 * @pre | element != null
	 * @mutates | this
	 * @post | !contains(element)
	 * @post | Arrays.stream(old(toArray())).allMatch(e -> e.equals(element) || contains(e))
	 * @post | stream().allMatch(e -> Arrays.stream(old(toArray())).anyMatch(e1 -> e1.equals(e)))
	 */
	void remove(Object element);

}
