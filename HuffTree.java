public class HuffTree<E extends Comparable<E>> implements Comparable<HuffTree<E>> {
	private HuffNode<E> root;

	//inits a HuffTree with two HuffNodes creates a root from thos nodes
	public HuffTree(HuffNode<E> node1, HuffNode<E> node2) {
		int newFreq = node1.getFrequency() + node2.getFrequency();
		this.root = new HuffNode<E>(newFreq, node1, node2);
	}

	public HuffNode<E> root() {
		return this.root;
	}

	public int weight() {
		return this.root.getFrequency();
	}

	public void setRoot(HuffNode<E> rt) {
		this.root = rt;
	}

	@Override
	public int compareTo(HuffTree<E> hf) {
		int result = 0;
		if (this.root.getFrequency() < hf.root().getFrequency()) {
			result = -1;
		} else {
			if (this.root.getFrequency() > hf.root().getFrequency()) {
				result = 1;
			}
		}
		return result;
	}

}
