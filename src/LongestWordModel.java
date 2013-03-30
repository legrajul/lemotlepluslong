public class LongestWordModel {
	public static final char[] CONSONANTS = { 'B', 'C', 'D', 'E', 'G', 'H',
			'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X',
			'Z' };
	public static final char[] VOWELS = { 'A', 'E', 'I', 'O', 'U', 'Y' };
	
	private int sequence_length;
	private char[] sequence;
	private int idx;

	public LongestWordModel(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException();
		}
		sequence_length = length;
		sequence = new char[length];
		for (int i = 0; i < length; i++) {
			sequence[i] = '_';
		}
		idx = 0;
	}

	public int getSequenceLength() {
		return sequence_length;
	}

	public char[] getSequence() {
		return sequence;
	}

	public boolean isReady() {
		return idx == sequence_length;
	}

	public void generateVowel() {
		if (isReady()) {
			throw new IllegalStateException();
		}
		char c = VOWELS[(int) (Math.random() * VOWELS.length)];
		sequence[idx] = c;
		idx++;
	}
	
	public void generateConsonant() {
		if (isReady()) {
			throw new IllegalStateException();
		}
		char c = CONSONANTS[(int) (Math.random() * CONSONANTS.length)];
		sequence[idx] = c;
		idx++;
	}
	
	public void reinit() {
		sequence = new char[sequence_length];
		for (int i = 0; i < sequence_length; i++) {
			sequence[i] = '_';
		}
		idx = 0;
	}
}
