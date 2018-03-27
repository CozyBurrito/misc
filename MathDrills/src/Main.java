import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public class Eq {
		public int x = -1;
		public int y = -1;
		
		public Eq(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";		
		}
	}
	
	
	
	private ArrayList<Eq> arr = new ArrayList<Eq>();
	
	private Random r = new Random();
	
	File f = new File("out.txt");
	FileOutputStream fout;
			
	private int min = 0;
	private int max = 10;
	private int n = 100;
	
	public static void main(String[] args) {
		try {
			new Main().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void run() throws IOException {
		
		fout = new FileOutputStream(f);
		
		for (int i = 0; i < n; i++) {
			int x = r.nextInt(max - min + 1) + min;
			int y = r.nextInt(max - min + 1) + min;
			
			arr.add(new Eq(x, y));
			String line = x + " + " + y + " = " + (x + y) + "\n";
			fout.write(line.getBytes());
		}
		
		
		System.out.println(arr.toString());
		
		
		
		

	}
	
	

}
