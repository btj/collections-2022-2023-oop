package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashSet implements Set {
	
	/**
	 * @invar | buckets != null
	 * @invar | buckets.length >= 1
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     buckets[i].stream().allMatch(e ->
	 *        |         Math.floorMod(e.hashCode(), buckets.length) == i))
	 * 
	 * @representationObject
	 * @representationObjects
	 */
	private Set[] buckets;

	@Override
	public Object[] toArray() {
		return Arrays.stream(buckets).flatMap(b -> b.stream()).toArray();
	}

	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(b -> b.size()).sum();
	}
	
	private Set getBucket(Object element) {
		return buckets[Math.floorMod(element.hashCode(), buckets.length)];
	}

	@Override
	public boolean contains(Object element) {
		return getBucket(element).contains(element);
	}
	
	/**
	 * @pre | 1 <= capacity
	 * @post | size() == 0
	 */
	public HashSet(int capacity) {
		buckets = new Set[capacity];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new ArraySet();
	}

	@Override
	public void add(Object element) {
		getBucket(element).add(element);
	}

	@Override
	public void remove(Object element) {
		getBucket(element).remove(element);
	}

}
