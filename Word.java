class Word implements Comparable<Word>{
	String word;
	int count;
	
	Word(String w, Integer i) {
		word = w;
		count = i;
	}

	@Override
	public int compareTo(Word arg0) {
		return count - arg0.count;
	}
}
