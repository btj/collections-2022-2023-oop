package collections_oop;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HashMap implements Map {
	
	/**
	 * @invar | buckets != null
	 * @invar | buckets.length >= 1
	 * @invar | IntStream.range(0, buckets.length).allMatch(i ->
	 *        |     buckets[i] != null &&
	 *        |     buckets[i].entrySet().stream().allMatch(e -> e instanceof Entry entry &&
	 *        |         Math.floorMod(entry.key().hashCode(), buckets.length) == i))
	 *        
	 * @representationObject
	 * @representationObjects
	 */
	private Map[] buckets;
	
	@Override
	public int size() {
		return Arrays.stream(buckets).mapToInt(b -> b.size()).sum();
	}

	@Override
	public Set entrySet() {
		HashSet result = new HashSet(size());
		for (Map bucket : buckets)
			bucket.entrySet().stream().forEach(e -> result.add(e));
		return result;
	}
	
	private Map getBucket(Object key) {
		return buckets[Math.floorMod(key.hashCode(), buckets.length)];
	}

	@Override
	public boolean containsKey(Object key) {
		return getBucket(key).containsKey(key);
	}

	@Override
	public Object get(Object key) {
		return getBucket(key).get(key);
	}

	@Override
	public void put(Object key, Object value) {
		getBucket(key).put(key, value);
	}

	@Override
	public void remove(Object key) {
		getBucket(key).remove(key);
	}

}
