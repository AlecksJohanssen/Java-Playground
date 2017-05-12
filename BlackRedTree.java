import java.util.*;
import java.util.NoSuchElementException;

class BlackRedTree<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private boolean color;
		private int size;

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}
	public BlackRedTree() {

	}

	private boolean isRed(Node x) {
		if(x == null) {
			return false;
		}
		return x.color == RED;
	}

	private int size(Node x) {
		if(x == null) {
			return 0;
		}
		return x.size;
	}

	public int size() {
		return size(root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Value get(Key key) {
		if(key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) {
				x = x.left;
			} else if (cmp > 0) {
				x = x.right;
			} else {
				return x.val;
			}
		}
		return null;
	}

	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
		root.color = BLACK;
	}
	private Node put(Node h, Key key, Value val) {
		if(h == null) return new Node(key, val, RED, 1);
		int cmp = key.compareTo(h.key);
		if(cmp < 0) {
			h.left = put(h.left, key, val);
			h.right = put(h.right, key, val);
		} else if(cmp > 0) {
			h.right = put(h.right, key, val);
		} else {
			h.val = val;
		}
		if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// make a right-leaning link lean to the left
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node moveRedLeft(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	private Node moveRedRight(Node h) {
		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}



	private void flipColors(Node h) {
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	public void delete(Key key) { 
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, key);
		if (!isEmpty()) root.color = BLACK;
	}

	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("BST underflow");

		// if both children of root are black, set root to red
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = deleteMin(root);
		if (!isEmpty()) root.color = BLACK;
		// assert check();
	}

	// delete the key-value pair with the minimum key rooted at h
	private Node deleteMin(Node h) { 
		if (h.left == null)
			return null;

		if (!isRed(h.left) && !isRed(h.left.left))
			h = moveRedLeft(h);

		h.left = deleteMin(h.left);
		return balance(h);
	}

	private Node delete(Node h, Key key) { 
		if (key.compareTo(h.key) < 0)  {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = moveRedLeft(h);
			h.left = delete(h.left, key);
		}
		else {
			if (isRed(h.left))
				h = rotateRight(h);
			if (key.compareTo(h.key) == 0 && (h.right == null))
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = moveRedRight(h);
			if (key.compareTo(h.key) == 0) {
				Node x = min(h.right);
				h.key = x.key;
				h.val = x.val;
				// h.val = get(h.right, min(h.right).key);
				// h.key = min(h.right).key;
				h.right = deleteMin(h.right);
			}
			else h.right = delete(h.right, key);
		}
		return balance(h);
	}
	public Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
		return min(root).key;
	} 

	// the smallest key in subtree rooted at x; null if no such key
	private Node min(Node x) { 
		// assert x != null;
		if (x.left == null) return x; 
		else                return min(x.left); 
	}
	public boolean contains(Key key) {
		return get(key) != null;
	}
	// restore red-black tree invariant
	private Node balance(Node h) {
		// assert (h != null);

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}


}
class runTree {
  public static final void main(String[] args) {
    
  }
}
