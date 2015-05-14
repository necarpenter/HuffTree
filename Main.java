import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Invalid Argument");
			System.exit(0);
		}
		ArrayList<Integer> chars = readFile(args[0]);
		Heap<HuffNode<Integer>> h = makeHeap(chars);
		HuffTree<Integer> huff = buildTree(h);
		System.out.println("Done");
	}

	//reads each character in as an integer value (ASCII) returns an arraylist with each value
	public static ArrayList<Integer> readFile(String file) {
		FileReader fr = null;
		ArrayList<Integer> chars = new ArrayList<Integer>(); //to be returned containing all commands in the file
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			int tempChar = ' ';
			String tempLine = "";
			while ((tempLine = br.readLine()) != null) {
				for (int i = 0; i < tempLine.length(); i++) {
					int tempIntValue = tempLine.charAt(i);
					chars.add(tempIntValue);
				}
				chars.add(10);//add line feed (new line) 
			}
			chars.set(chars.size() - 1, 0); //remove last line feed and replace it with EOF 
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Missing file");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Empty file");
			System.exit(0);
		}
		return chars;
	}

	//takes the ArrayList with all chars (int values) converts each char to HuffNode object and then creates a min-heap 
	public static Heap<HuffNode<Integer>> makeHeap(ArrayList<Integer> chars) {
		int numChars = chars.size() - 1; //total number of characters will be reduced everytime a char is push to the heap 
		Heap<HuffNode<Integer>> letHeap = new Heap<HuffNode<Integer>>(); //Heap to be returned (Letters to be order less frequent first.)
		while (chars.size() != 0) {
			int currentChar = chars.get(0);
			chars.remove(0);
			int freqCounter = 1; //counts the frequency of each letter, init 1
			while (chars.indexOf(currentChar) != -1) {
				freqCounter++;
				chars.remove(chars.indexOf(currentChar));
				numChars--;
			}
			HuffNode<Integer> currentNode = new HuffNode<Integer>(currentChar, freqCounter);
			letHeap.insert(currentNode);
		}
		return letHeap;
	}

	//specific toString method for characters for testing
	public String toString(HuffNode<Integer> printNode) {
		if (printNode.getElement() == 0) {
			return "The character:EOF" + " has frequency:" + printNode.getFrequency();
		} else {
			int charInt = printNode.getElement();
			char tempChar = (char) charInt;
			return "The character:" + tempChar + " has frequency: " + printNode.getFrequency();
		}
	}

	//assumes it is being added to HuffIntNode 
	public static void insert(HuffNode<Integer> node1, HuffNode<Integer> node2, Heap<HuffNode<Integer>> hp, HuffTree<Integer> huff) {
		int newFreq = node1.getFrequency() + node2.getFrequency(); //the frequnecy of the new node to be added
		if (node1.getElement() == huff.root().getElement()) {//switch the root
			huff.setRoot(new HuffNode<Integer>(newFreq, node2, node1)); //always adds new material to the right
		} else if (node2.getElement() == huff.root().getElement()) {
			huff.setRoot(new HuffNode<Integer>(newFreq, node1, node2)); //always adds new material to the right
		} else {// there are two new nodes to be added
			HuffNode<Integer> newNode = new HuffNode<Integer>(newFreq, node1, node2);
			hp.insert(newNode);//insert new node into heap 
		}
	}

	//makes Hufftree from a min heap containing chars in int form 
	public static HuffTree<Integer> buildTree(Heap<HuffNode<Integer>> hp) {
		HuffNode<Integer> node1 = hp.extractMin();
		HuffNode<Integer> node2 = hp.extractMin();
		HuffTree<Integer> huff = new HuffTree<>(node1, node2); //init HuffTree with root from two min items in heap
		hp.insert(huff.root()); //push root back into heap
		while (hp.size() < 1) {
			node1 = hp.extractMin();
			node2 = hp.extractMin();
			insert(node1, node2, hp, huff);

		}
		return huff;
	}
}
