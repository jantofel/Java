package dictionary;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/*
 * Binary search tree based implementation of the Dictionary
 * interface. The nodes of the tree are ordered by an associated key-attribute
 * key of type K, such that every node's left subtree contains only nodes 
 * whose key-attributes are less than key, and every node's right subtree
 * contains only nodes whose key-attributes are greater than key. A
 * linear order is defined on keys through the Comparable interface.
 * Duplicate keys are not permitted.
 */
public class BinarySearchTree<K extends Comparable<? super K>, V> implements
        Dictionary<K, V> {
	
	private int size;
	private BinarySearchTreeEntry<K, V> root;
	
	public BinarySearchTree() {
		root = new BinarySearchTreeEntry<K,V>();
		size = 0;
	}

	@Override
	public Iterator<DictionaryEntry<K, V>> iterator() {
		return (new Iterator<DictionaryEntry<K, V>>(){

			private BinarySearchTreeEntry<K, V> current = root;
			private Stack<BinarySearchTreeEntry<K, V>> stack =
			new Stack<BinarySearchTreeEntry<K,V>>();

			@Override
			public boolean hasNext() {
				return current.hasLeft() || current.hasRight();
				// TODO: Wrong?
			}

			@Override
			public DictionaryEntry<K, V> next() {
				BinarySearchTreeEntry<K, V> res = new BinarySearchTreeEntry<K, V>();
				while (current!= null) {
					stack.push(current);
					current = current.getLeft();
				}
				if (!stack.isEmpty()) {
					res = stack.pop();
					current = res.getRight();
				}
				return res;
			}

			/*
			@Override
			public DictionaryEntry<K,V> next() {
				BinarySearchTreeEntry<K,V> res = null;
				while (current!= null) {
					stack.push(current);
					current = current.getLeft();
				}
				res = stack.pop();
				current = res.getRight();
				return res;
			}
			*/
			@Override
			public void remove() {
				throw new UnsupportedOperationException("Cannot be removed");
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
		return get(root, key);
	}
	
	private V get(BinarySearchTreeEntry<K, V> elem, K key){
		if (elem == null || elem.getKey() == null) 
			return null;
		int comp = elem.getKey().compareTo(key);
		if (comp == 0) 
			return elem.getValue();
		if (comp < 0) 
			return get(elem.getLeft(), key);
		return get(elem.getRight(), key);
	}
	
	@Override
	public void put(K key, V value) {
		put(root, key, value);
	}

	private void put(BinarySearchTreeEntry<K, V> elem, K key, V value){
		if (elem.getKey() == null) {
			elem.setKey(key);
			elem.setValue(value);
			size++;
		} else {
			int comp = elem.getKey().compareTo(key);
			if (comp == 0) {
				elem.setValue(value);
			} else if (comp > 0) {
				if (elem.hasLeft()) put(elem.getLeft(), key, value);
				else {
					elem.setLeft(new BinarySearchTreeEntry<K, V>(key, value));
					size++;
				}
			} else {
				if (elem.hasRight()) put(elem.getRight(), key, value);
				else {
					elem.setRight(new BinarySearchTreeEntry<K, V>(key, value));
					size++;
				}
			}
		}
	}
	
	@Override
	public void remove(K key) throws NoSuchElementException {
		remove(root, key);
	}
	
	private BinarySearchTreeEntry<K, V> remove(BinarySearchTreeEntry<K, V> elem, K
			key){
		if (elem == null || elem.getKey() == null){
			throw new NoSuchElementException();
		}
		int comp = elem.getKey().compareTo(key);
		if (comp == 0) {
			elem = deleteNode(elem);
		} else if (comp > 0) {
			elem.setLeft(remove(elem.getLeft(), key));
		} else {
			elem.setRight(remove(elem.getRight(),key));
		}
		return elem;
	}
	
	private BinarySearchTreeEntry<K, V> deleteNode(BinarySearchTreeEntry<K, V> elem){
		if (elem == null) return null;
		else if (!elem.hasRight()) return elem.getLeft();
		else if (!elem.hasLeft()) return elem.getRight();
		else{
		BinarySearchTreeEntry<K, V> replacement =
				findLeftMost(elem.getRight());
		BinarySearchTreeEntry<K, V> newRight =
				deleteLeftMost(elem.getRight());
		replacement.setRight(newRight);
		replacement.setLeft(elem.getLeft());
		return replacement;
		}
	}
	
	private BinarySearchTreeEntry<K, V> 
	findLeftMost(BinarySearchTreeEntry<K, V> elem) {
		if (elem.getLeft() == null) return elem;
		else return findLeftMost(elem.getLeft());
	}
	
	private BinarySearchTreeEntry<K, V> 
	deleteLeftMost(BinarySearchTreeEntry<K, V> elem) {
		if (elem.getLeft() == null)
			return elem.getRight();
		else {
			BinarySearchTreeEntry<K, V> newElem = deleteLeftMost(elem.getLeft());
			elem.setLeft(newElem);
			return elem;
			}
	}

	@Override
	public void clear() {
		root = new BinarySearchTreeEntry<K,V>();
		size = 0;
	}
}
