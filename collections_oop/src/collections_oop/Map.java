package collections_oop;

public interface Map {
	
	interface Entry {
		/**
		 * @inspects | this
		 * @post | result != null
		 */
		Object key();
		/**
		 * @inspects | this
		 * @post | result != null
		 */
		Object value();
		/**
		 * @post | result == (
		 *       |    obj instanceof Entry e && key().equals(e.key()) && value().equals(e.value())
		 *       | )
		 */
		@Override
		boolean equals(Object obj);
	}
	
	/**
	 * @creates | result
	 * @post | result != null
	 * @post | result.stream().allMatch(e -> e instanceof Entry)
	 * @post No two entries have equal keys.
	 *      | result.stream().map(e -> ((Entry)e).key()).distinct().count() == result.size()
	 */
	Set entrySet();
	
	/**
	 * @inspects | this
	 * @post | result == entrySet().size()
	 */
	int size();
	
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream().anyMatch(e -> ((Entry)e).key().equals(key))
	 */
	boolean containsKey(Object key);
	
	/**
	 * @pre | key != null
	 * @inspects | this
	 * @post | result == entrySet().stream()
	 *       |                     .filter(e -> ((Entry)e).key().equals(key))
	 *       |                     .map(e -> ((Entry)e).value())
	 *       |                     .findFirst().orElse(null)
	 */
	Object get(Object key);
	
	/**
	 * @pre | key != null
	 * @pre | value != null
	 * @mutates | this
	 * @post | containsKey(key) && get(key).equals(value)
	 * @post | old(entrySet()).stream()
	 *       |                .allMatch(e -> ((Entry)e).key().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream()
	 *       |           .allMatch(e -> ((Entry)e).key().equals(key) || old(entrySet()).contains(e))
	 */
	void put(Object key, Object value);
	
	/**
	 * @pre | key != null
	 * @mutates | this
	 * @post | !containsKey(key)
	 * @post | old(entrySet()).stream()
	 *       |                .allMatch(e -> ((Entry)e).key().equals(key) || entrySet().contains(e))
	 * @post | entrySet().stream().allMatch(e -> old(entrySet()).contains(e))
	 */
	void remove(Object key);

}
