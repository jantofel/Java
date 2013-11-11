package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Ordered linked list based implementation of the Dictionary
 * interface. The nodes of the list are ordered in ascending order by
 * the key-attribute of type K. Duplicate keys are not permitted.
 */
public class OrderedLinkedList<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {
	
	private int size = 0;
	private OrderedLinkedListEntry<K, V> head = new OrderedLinkedListEntry<K, V>();

	@Override
	public Iterator<DictionaryEntry<K, V>> iterator() {
		return (new Iterator<DictionaryEntry<K, V>>(){

		private OrderedLinkedListEntry<K, V> current = head;
		
		@Override
		public boolean hasNext() {
			return current.hasNext();
		}
		
		@Override
		public DictionaryEntry<K, V> next() {
			if (current == null) {
				return null;
			} else {
				current = current.getNext();
				return current;
			}
		}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Cannot be removed.");
			}
		});
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) throws NoSuchElementException {
		OrderedLinkedListEntry<K, V> current = head;
		if (current == null) 
			return null;
		if (current.getKey().equals(key)) {
			return current.getValue();
		} else {
			while(current!=null && current.getKey().compareTo(key) < 0){
				current = current.getNext();
			}
			if (current!=null && current.getKey().equals(key))
				return	current.getValue();
		}
		return null;
	}

	@Override
	public void put(K key, V value) {
		OrderedLinkedListEntry<K, V> current = head;
		OrderedLinkedListEntry<K, V> next = current.getNext();
		while (true)
		{
			if (next == null) {
				current.setNext(new OrderedLinkedListEntry<K, V>(key, value));
				size++;
				return;
			}
			if (next.getKey().compareTo(key) < 0) {
				current = next;
				next = next.getNext();
			} else {
				OrderedLinkedListEntry<K, V> entry =
						new OrderedLinkedListEntry<K, V>(key, value);
				current.setNext(entry);
			entry.setNext(next);
			size++;
			return;
			}
		}
	}

	@Override
	public void remove(K key) throws NoSuchElementException {
		OrderedLinkedListEntry<K, V> current = head;
		OrderedLinkedListEntry<K, V> next = current.getNext();
		if (!next.hasNext()) throw new NoSuchElementException();
		while(next.hasNext()) {
			if (next.getKey().compareTo(key) == 0) {
				current.setNext(next.getNext());
				size--;
				return;
			} else {
				current = next;
				next = next.getNext();
			}
		}
		if (next.getKey().compareTo(key) == 0){
		current.setNext(null);
		size--;
		}
	}

	@Override
	public void clear() {
		size = 0;
		head = new OrderedLinkedListEntry<K, V>();
	}
    // TODO
}
