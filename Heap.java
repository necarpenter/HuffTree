import java.util.ArrayList;

public class Heap<E extends Comparable<E>> {
	private ArrayList<E> array;
	private int numOfItems;

	public Heap() {
		array = new ArrayList<E>();
		numOfItems = 0;
	}

	public boolean isEmpty() {
		boolean isEmp = false;
		if (this.numOfItems == 0) {
			isEmp = true;
		}
		return isEmp;
	}

	public boolean isLeaf(int pos) {
		boolean isL = true;
		if (pos >= 0 && pos < this.numOfItems) {
			int leftPos = 2 * pos + 1;
			int rightPos = 2 * pos + 2;
			if (leftPos < this.numOfItems) {

				if (this.array.get(leftPos) != null) {
					isL = false;
				}
			}
			if (isL && rightPos < this.numOfItems) {
				if (this.array.get(rightPos) != null) {
					isL = false;
				}
			}
			return isL;
		} else {// bad position
			System.out.println("No element at position: " + pos);
			return false;
		}

	}

	public E extractMin() {

		if (!this.isEmpty()) {
			E minE = this.array.get(0);
			array.set(0, array.get(array.size() - 1));
			array.remove(array.size() - 1);
			
			this.numOfItems--;
			if (!this.isEmpty()) {
				this.siftDown(0);
			}

			return minE;
		} else {
			return null;
		}

	}

	public void insert(E element) {
		array.add(element);
		int loc = array.size()-1;
		while (loc > 0 && array.get(loc).compareTo(array.get(parent(loc))) < 0) {
			swap(loc, parent(loc));
			loc = parent(loc);
		}
		this.numOfItems++;
	}

	private void swap(int loc, int parent) {
		E locE = this.array.get(loc);
		E parentE = this.array.get(parent);
		this.array.set(loc, parentE);
		this.array.set(parent, locE);

	}

	// returns index of a parent
	private int parent(int loc) {
		// TODO Auto-generated method stub
		return (loc - 1) / 2;
	}

	public E minimum() {
		if (this.numOfItems != 0) {
			return this.array.get(0);
		} else {

			return null;
		}

	}

	public void siftDown(int i) {
		while (!isLeaf(i)) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int smallest;
			if (left < array.size() && array.get(left).compareTo(array.get(i)) < 0) {
				smallest = left;
			} else {
				smallest = i;
			}
			if (right < array.size() && array.get(right).compareTo(array.get(smallest)) < 0) {

				smallest = right;
			}
			if (array.get(i).compareTo(array.get(smallest)) <= 0) {
				break;
			} else {
				swap(i, smallest);
				i = smallest;
			}
		}
	}

	public ArrayList<E> getArray() {
		return array;
	}

	public void setArray(ArrayList<E> array) {
		this.array = array;
	}

	public int size() {
		return numOfItems;
	}

	public void setNumOfItems(int numOfItems) {
		this.numOfItems = numOfItems;
	}

}
