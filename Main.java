
class Main {
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Main inputfile.txt outputfile.txt");
			return;
		}
		
		Solution solution = new Solution(args[0], args[1]);
		solution.process();
	}
}
