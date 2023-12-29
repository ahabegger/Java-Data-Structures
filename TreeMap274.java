import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

public class TreeMap274<K extends Comparable<? super K>, V> implements Map<K, V> {

	// Leave this public because it will help with testing
	public Entry<K, V> root;

	public TreeMap274() {
		root = null;
	}	

	// SINCE THIS IS A BINARY SEARCH TREE, THE ALGORITHM
	// FOR put() IN A TREEMAP IS NEARLY IDENTICAL TO THE ALGORITHM FOR 
	// ADDING A NODE TO A BINARY SEARCH TREE.
	// So, the algorithm for finding the right place in the tree is
	// exactly the same, but what you do when you find that place is
	// different. The difference:
	//  - Search for the key. 
	//  -   If not found, add the key-value Entry, and return null
	//  -   If found, then just update the value, and return the old value
	@Override
	public V put(K key, V value) {
		if (isEmpty()) {
			root = new Entry<K, V>(key, value);
			return null;
		}
		
		Entry<K, V> curr = root;
		Entry<K, V> parent = null;
		
		while (curr != null) {
			parent = curr;
			
			if (key.compareTo(parent.key) < 0) {
				curr = parent.left;
			} else if (key.compareTo(parent.key) > 0) {
				curr = parent.right;
			} else { // found a duplicate
				V oldValue = parent.value;
				parent.value = value;
				return oldValue; // Found return
			}	
		}
		
		// Parent point to where the new entry needs to 
		// be added either left or right
		if (key.compareTo(parent.key) < 0) 
			parent.left = new Entry<K, V>(key, value);
		else 
			parent.right = new Entry<K, V>(key, value);
		
		return null; // Not found return
	}
	
	// SOLVED IN VIDEO, both recursively and iteratively. Either is fine.
	// Can be solved with a loop or recursively.
	@Override
	public boolean containsKey(K key) {
		if (isEmpty())
			return false;
		
		// We know that root exists
		Entry<K, V> curr = root;
		
		while (curr != null) {
			// If we find a match, done
			if (curr.key.equals(key)) {
				return true;
			} else if (curr.key.compareTo(key) < 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		
		// Did not find
		return false;
	}
	
	// SIMILAR TO containsKey() EXCEPT THAT THIS METHOD RETURNS A VALUE
	// OR NULL, RATHER THAN TRUE OR FALSE
	@Override
	public V get(K key) {
		if (isEmpty())
			return null;
		
		// We know that root exists
		Entry<K, V> curr = root;
		
		while (curr != null) {
			// If we find a match, done
			if (curr.key.equals(key)) {
				return curr.value;
			} else if (curr.key.compareTo(key) < 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		
		// Did not find
		return null;
	}
	
	// DON'T CREATE A SIZE INSTANCE VARIABLE. SOLVE THIS
	// RECURSIVELY. IT'S INEFFICIENT TO SOLVE RECURSIVELY
	// BUT THIS IS GOOD PRACTICE.
	@Override
	public int size() {
		return size(root);
	}
	
	// Recursive helper method
	private int size(Entry<K,V> top) {
		if (top == null) return 0; // check if empty tree
		
		int count = 1; // counting the root of the tree
		
		// Need to compute size for each of the subtrees
		count += size(top.left);
		count += size(top.right);

		return count;
	}
	
	// one line after you write the size() method
	@Override
	public boolean isEmpty() {
		return (root == null);
	}

	// one line
	@Override
	public void clear() {
		root = null;
	}

	// Keys should be returned sorted
	@Override
	public Set<K> keySet() {
		Set<K> set = new TreeSet<K>();
		keySet(root, set);
		
		return set;
	}
	
	// Recursive helper method
	private void keySet(Entry<K,V> top, Set<K> set) {
		if (top == null) return; // check if empty tree
		
		set.add(top.key); // adding the root of the tree
		
		// Adding the subtrees of the root
		keySet(top.left, set);
		keySet(top.right, set);
	}
	
	// THE SMALLEST VALUE IN A BINARY SEARCH TREE CAN BE FOUND
	// BY MOVING TO THE LEFTMOST NODE. 
	/**
	 * Returns the first (lowest) key in this map
	 * @return the first (lowest) key in this map
	 * @throws NoSuchElementException if this map is empty
	 */
	public K firstKey() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		Entry<K,V> curr = root;
		K min = root.key;
		
		while (curr != null) {
			min = curr.key;
			curr = curr.left;
		}
		
		return min;
	}
	
	// GET THE VALUE FROM THE RIGHTMOST NODE	
	/**
	 * Returns the last (highest) key in this map
	 * @return the last (highest) key in this map
	 * @throws NoSuchElementException if this map is empty
	 */
	public K lastKey() {
		if (isEmpty())
			throw new NoSuchElementException();
		
		Entry<K,V> curr = root;
		
		while (curr.right != null) {
			curr = curr.right;
		}
		
		return curr.key;
	}

	// Returns a string representation of this map
	// with the keys in order from smallest to largest
	public String toString() {
		return toString(root, "");
	}

	private String toString(Entry<K, V> entry, String stringSoFar) {
		if (entry == null)
			return stringSoFar;

		stringSoFar = toString(entry.left, stringSoFar);
		stringSoFar += " " + entry.key + " ----> " + entry.value + "\n";
		stringSoFar = toString(entry.right, stringSoFar);

		return stringSoFar;
	}
	
	// NOT IMPLEMENTING. BUT HERE BECAUSE IT IS IN THE MAP INTERFACE 
	@Override
	public V remove(K key) {
		throw new UnsupportedOperationException("remove() not supported");
	}
	
	/*
	 * Inner class (normally private, but here it is public
	 * to help with testing).
	 * This is very similar to a BinaryNode class:
	 * 	BinaryNode will have data, and left and right children
	 *  Entry has data (key and value), and left and right children
	 */
	@SuppressWarnings("hiding")
	public class Entry<K extends Comparable<? super K>, V> {
		public K key;
		public V value;

		public Entry<K, V> left, right;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		// Do your own testing here, but then test more
		// thoroughly using the JUnit tester
		TreeMap274<String, Integer> map = new TreeMap274<>();
		
		map.put("Z", 99); // null
		map.put("C", 75); // null
		map.put("Y", 200); // null
		map.put("C", 13); // 75
		
		System.out.println(map.containsKey("C")); // true
		System.out.println(map.get("C")); // 13
		System.out.println(map.containsKey("D")); // false
		System.out.println(map.get("D")); // null
		
		System.out.println(map); 
		System.out.println(map.keySet());

	}
}
