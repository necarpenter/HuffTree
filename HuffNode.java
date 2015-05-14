public class HuffNode<E> implements Comparable<HuffNode<E>> {
	private E element; //to be a char in this implementation 
	private int frequency; //Frequency char appears in text

	//these only exist if this isn't an internal node
	private HuffNode<E> left;
	private HuffNode<E> right;

	//constructor to construct a leaf
	public HuffNode(E ele, int freq) {
		this.setElement(ele);
		this.frequency = freq;
	}

	/*	constructor for node (has a left and/or right) 
		takes both left and right can have both or can have one set to null or both set to null
		does not have E. frequency is cumulative of childrens' frequencies 
		*/
	public HuffNode(int freq, HuffNode<E> l, HuffNode<E> r) {
		this.frequency = freq;
		this.setLeft(l);
		this.setRight(r);
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int freq) {
		this.frequency = freq;
	}

	public HuffNode<E> getLeft() {
		return left;
	}

	public void setLeft(HuffNode<E> left) {
		this.left = left;
	}

	public HuffNode<E> getRight() {
		return right;
	}

	public void setRight(HuffNode<E> right) {
		this.right = right;
	}

	@Override
	//compares based on frequencies
	public int compareTo(HuffNode<E> camp) {
		int result = 0;
		if (this.frequency > camp.getFrequency()) {
			result = 1;
		} else if (this.frequency < camp.getFrequency()) {
			result = -1;
		}
		return result;
	}

	//specific toString method for characters for testing
	public String toString() {
		int charInt = (Integer) this.element; //already an int
		if (charInt == 0) {
			return "The character:EOF" + " has frequency:" + this.frequency;
		} else {
			
			char tempChar = (char) charInt;
			return "The character:" + tempChar + " has frequency: " + this.frequency;
		}
	}

}
