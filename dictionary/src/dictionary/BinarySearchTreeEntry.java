package dictionary;

import dictionary.DictionaryEntry;

// Implementation class representing nodes of the binary search tree.
public class BinarySearchTreeEntry<K, V> implements DictionaryEntry<K, V> {

	private K key;
	private V value;
	private BinarySearchTreeEntry<K, V> left;
	private BinarySearchTreeEntry<K, V> right;
	
	public BinarySearchTreeEntry(K key, V value){
		this.key = key;
		this.value = value;
		left = null;
		right = null;
	}

	public BinarySearchTreeEntry(){
		key = null;
		value = null;
		left = null;
		right = null;
	}
	
	public void setValue(V value){
		this.value=value;
	}

	public void setKey(K key){
		this.key = key;
	}
	
	public boolean hasLeft(){
		return left != null;
	}

	public boolean hasRight(){
		return right != null;
	}

	public void setLeft(BinarySearchTreeEntry<K, V> left){
		this.left = left;
	}

	public void setRight(BinarySearchTreeEntry<K, V> right){
		this.right = right;
	}

	public BinarySearchTreeEntry<K, V> getLeft(){
		return left;
	}
	
	public BinarySearchTreeEntry<K, V> getRight(){
		return right;
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
