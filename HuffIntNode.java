public class HuffIntNode<Character> implements HuffBaseNode<Character> {
	private int weight; // Cumulative frequency all left and right children
	private HuffBaseNode<Character> left; // Pointer to left child
	private HuffBaseNode<Character> right; // Pointer to right child

	/** Constructor */
	public HuffIntNode(HuffBaseNode<Character> l, HuffBaseNode<Character> r, int wt) {
		left = l;
		right = r;
		weight = wt;
	}

	/** @return The left child */
	public HuffBaseNode<Character> left() {
		return left;
	}

	/** @return The right child */
	public HuffBaseNode<Character> right() {
		return right;
	}

	/** @return The weight */
	public int weight() {
		return weight;
	}

	/** Return false */
	public boolean isLeaf() {
		return false;
	}
}
