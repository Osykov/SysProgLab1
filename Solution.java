import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


class Solution {
	
	public final String outputfile;
	public final String inputfile;
	public final char[] consonants = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n',
												'p', 'q', 'r', 's', 't', 'v', 'x', 'z', 'w', 'y' };  
	Hashtable<String, Integer> words = new Hashtable<String, Integer>();
	ArrayList<Word> w = new ArrayList<>();
	
	Solution(String in, String out) {
		inputfile = in;
		outputfile = out;
	}
	
	boolean isConsonant(char c) {
		for (int i = 0; i < consonants.length; i++) {
			if (c == consonants[i]) {
				return true;
			}
		}
		
		return false;
	}
	
	public void process() {
	
		long start = System.currentTimeMillis();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inputfile);
		} catch (FileNotFoundException e) {
			System.err.println("Input file is not found.");
			return;
		}
		final int bufsize = 500 * 1024;
		byte[] buf = new byte[bufsize];
		int len;
		int count = 0;
		StringBuilder sb = new StringBuilder("");
		try {
			while ((len = fis.read(buf)) != -1) {
				for (int i = 0; i < len; i++) {
					if (Character.isAlphabetic(buf[i]) || 
					((Character.isDigit(buf[i]) || (char)buf[i] == '_') &&
					!sb.toString().equals(""))) {
						sb.append((char)buf[i]);
						if (isConsonant((char) Character.toLowerCase(buf[i]))) {
							count++;
						}
					}
					else {
						if (!sb.toString().equals("") && !words.containsKey(sb.toString())) {
							words.put(sb.toString(), count);
						}
						sb.delete(0, sb.length());
						count = 0;
					}
				}
			}
		} catch (IOException ex) {
			System.err.println("File is not found");
		}
		Set<Map.Entry<String, Integer>> set = words.entrySet();
		Iterator<Map.Entry<String, Integer>> i = set.iterator();
		while (i.hasNext()) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)i.next();
			w.add(new Word(entry.getKey(), entry.getValue()));
		}
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(outputfile);
		} catch (FileNotFoundException e) {
			System.err.println("Output file is not found.");
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)), true);
		
		Collections.sort(w);
		
		for (Word d : w) {
			pw.println(d.word + " " + d.count);
		}
		pw.close();
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}
}
