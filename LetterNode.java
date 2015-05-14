//leaf node class for this implementation is character specific
public class LetterNode<Charactor> implements HuffBaseNode<Character> {
	private int letter; //ASCII value
	private int weight;
	private String code;

	public LetterNode(int let, int freq) {
		this.letter = let;
		this.weight = freq;
		this.code = null;
	}

	public int getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public void setFrenquency(int frenquency) {
		this.weight = frenquency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public int compareTo(LetterNode let) {
		int result = 0;
		if (this.weight > let.weight()) {
			result = 1;
		} else {
			if (this.weight < let.weight()) {
				result = -1;
			}
		}
		return result;
	}

	public String toString() {
		if (this.letter == 0) { // check for EOF and handle
			return "LetterNode:" + "EOF" + ", has frequency: " + this.weight;
		}
		char tempChar = (char) this.letter;
		return "LetterNode:" + tempChar + ", has frequency: " + this.weight;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int weight() {
		return this.weight();
	}
}
