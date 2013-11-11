package dictionary;


public class OrderedLinkedListEntry<K, V> implements DictionaryEntry<K, V> {
	
	private K key;
	private V value;
	private OrderedLinkedListEntry<K, V> nextNode = null;
	
	public void setNext(OrderedLinkedListEntry<K, V> next) {
		this.nextNode = next;
	}

	public OrderedLinkedListEntry(K key, V value){
		this.key = key;
		this.value = value;
		nextNode = null;
	}
	
	public OrderedLinkedListEntry(){
		this.key = null;
		this.value = null;
		nextNode = null;
	}

	public void setValue(V value){
		this.value=value;
	}
	
	public void setKey(K key){
		this.key=key;
	}
	
	public boolean hasNext(){
		return nextNode != null;
	}
	
	public OrderedLinkedListEntry<K, V> getNext(){
		return nextNode;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

}
