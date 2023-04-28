package collections_oop;

public class ArrayMap implements Map {
	
	private record MyEntry(Object key, Object value) implements Entry {}
	
	/**
	 * @invar | entries != null
	 * @invar | entries.stream().allMatch(e -> e != null && e instanceof Entry)
	 * @invar No two entries have equal keys.
	 *        | entries.stream().map(e -> ((Entry)e).key()).distinct().count() == entries.size()
	 * 
	 * @representationObject
	 */
	private ArrayList entries = new ArrayList();

	@Override
	public Set entrySet() {
		HashSet result = new HashSet(entries.size());
		for (int i = 0; i < entries.size(); i++)
			result.add(entries.get(i));
		return result;
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean containsKey(Object key) {
		return entries.stream().anyMatch(e -> ((Entry)e).key().equals(key));
	}

	@Override
	public Object get(Object key) {
		return entrySet().stream()
				         .filter(e -> ((Entry)e).key().equals(key))
				         .map(e -> ((Entry)e).value())
				         .findFirst().orElse(null);
	}

	@Override
	public void put(Object key, Object value) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).key().equals(key)) {
				entries.set(i, new MyEntry(key, value));
				return;
			}
		entries.add(new MyEntry(key, value));
	}

	@Override
	public void remove(Object key) {
		for (int i = 0; i < entries.size(); i++)
			if (((Entry)entries.get(i)).key().equals(key)) {
				entries.remove(i);
				return;
			}
	}

}
