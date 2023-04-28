package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface List {
	
	/**
	 * @inspects | this
	 * @creates | result
	 * @post | result != null
	 * @post | Arrays.stream(result).allMatch(e -> e != null)
	 */
	Object[] toArray();
	
	/**
	 * @inspects | this
	 * @post | result == toArray().length
	 */
	int size();
	
	/**
	 * @pre | 0 <= index && index < size()
	 * @inspects | this
	 * @post | result == toArray()[index]
	 */
	Object get(int index);
	
	default Stream<Object> stream() { return Arrays.stream(toArray()); }
	
	/**
	 * @pre | element != null
	 * @inspects | this
	 * @post | result == stream().anyMatch(e -> e.equals(element))
	 */
	boolean contains(Object element);
	
	/**
	 * @pre | element != null
	 * @inspects | this
	 * @post | result ==
	 *       | IntStream.range(0, size()).filter(i -> get(i).equals(element)).findFirst().orElse(-1)
	 */
	default int indexOf(Object element) {
		for (int i = 0; i < size(); i++)
			if (get(i).equals(element))
				return i;
		return -1;
	}
	
	/**
	 * @pre | element != null
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(), 0, old(size()), old(toArray()), 0, old(size()))
	 * @post | get(old(size())) == element
	 */
	default void add(Object element) { add(size(), element); }
	
	/**
	 * @pre | element != null
	 * @mutates | this
	 * @post | size() == old(size()) + 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index + 1, size(), old(toArray()), index, old(size()))
	 * @post | get(index) == element
	 */
	void add(int index, Object element);
	
	/**
	 * @pre | 0 <= index && index < size()
	 * @mutates | this
	 * @post | size() == old(size()) - 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index, size(), old(toArray()), index + 1, size() + 1)
	 */
	void remove(int index);
	
	default void remove(Object element) {
		int index = indexOf(element);
		if (index != -1)
			remove(index);
	}
	
	/**
	 * @pre | 0 <= index && index < size()
	 * @pre | element != null
	 * @mutates | this
	 * @post | size() == old(size())
	 * @post | IntStream.range(0, size())
	 *       |          .allMatch(i -> get(i) == (i == index ? element : old(toArray())[i]))
	 */
	void set(int index, Object element);

}
