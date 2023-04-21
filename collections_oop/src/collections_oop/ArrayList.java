package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayList implements List {
	
	/**
	 * @invar | elements != null
	 * @invar | 0 <= size
	 * @invar | size <= elements.length
	 * @invar | IntStream.range(0, elements.length).allMatch(i ->
	 *        |     (elements[i] == null) == (size <= i)
	 *        | )
	 * @representationObject
	 */
	private Object[] elements;
	private int size;
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) {
		return elements[index];
	}

	@Override
	public boolean contains(Object element) {
		return stream().anyMatch(e -> e.equals(element));
	}
	
	public ArrayList() {
		elements = new Object[10];
	}

	@Override
	public void add(Object element) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, size * 2);
		}
		elements[size++] = element;
	}
	
	@Override
	public void add(int index, Object element) {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, size * 2);
		}
		System.arraycopy(elements, index, elements, index + 1, size++ - index);
		elements[index] = element;
	}

	@Override
	public void remove(int index) {
		System.arraycopy(elements, index + 1, elements, index, --size - index);
		elements[size] = null;
	}

}
