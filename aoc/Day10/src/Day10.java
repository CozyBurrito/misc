public class Day10 {

	public static void main(String[] args){
		new Day10().run();
	}

	public void run() {
		String input = "3113322113";
		String input2 = "";
		int count = 0;
		int j;

		for (int i = 0; i < 40; i++) {
			for (j = 0; j < input.length()-1; j++) {
				if(input.charAt(j) == input.charAt(j+1))
					count++;
				else {
					input2 += Integer.toString(count+1) + Character.toString(input.charAt(j));
					count = 0;
				}
			}
			
			input2 += Integer.toString(count+1) + Character.toString(input.charAt(j));

			input = input2;
			input2 = "";
			count = 0;
		}
		
		System.out.println(input.length());

	}

}