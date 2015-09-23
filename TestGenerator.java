import java.io.*;
class TestGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			gigaHP();
		} catch (Exception ex) {
			System.err.println("Error");
		}
		long finish = System.currentTimeMillis();
		System.out.println(finish - start);
	}
	
	static void gigaTest() throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("/home/vitaliy/workspace/SysProgLab1/tests/test2.txt");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos), true);
		final int giga = 1024 * 1024 * 1024;
		
		for (int i = 0; i < giga; i++) {
			pw.append((char)(int)(Math.random() * 127));
		}
		
		pw.close();
	}
	
	static void gigaHP() throws IOException {
		FileInputStream fis = new FileInputStream("/home/vitaliy/workspace/SysProgLab1/tests/input/hp");
		FileOutputStream fos = new FileOutputStream("/home/vitaliy/workspace/SysProgLab1/tests/input/ghp");
		final int bufsize = 500 * 1024;
		byte[] buf = new byte[bufsize];
		for (int i = 0; i < 150; i++) {
			while ((fis.read(buf)) != -1) {
				fos.write(buf);
			}
			fis = new FileInputStream("/home/vitaliy/workspace/SysProgLab1/tests/input/hp");
		}
		
		fis.close();
		fos.close();
	}

}
