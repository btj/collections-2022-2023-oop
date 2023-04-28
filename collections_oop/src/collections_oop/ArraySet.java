package collections_oop;

public class ArraySet implements Set {
	
	/**
	 * @invar | elements != null
	 * @invar | elements.stream().allMatch(e -> e != null)
	 * @invar `elements` has no duplicates.
	 *     | elements.stream().distinct().count() == elements.size()
	 * 
	 * @representationObject
	 */
	private ArrayList elements = new ArrayList();

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public boolean contains(Object element) {
		return elements.contains(element);
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArraySet() {}

	@Override
	public void add(Object element) {
		if (!elements.contains(element))
			elements.add(element);
	}

	@Override
	public void remove(Object element) {
		elements.remove(element);
	}

}
