
public class Day17 {

	public static void main(String[] args) {
		comb("", "123");

	}

    private static void comb(String prefix, String s) {
        System.out.println(prefix);
        for (int i = 0; i < s.length(); i++)
            comb(prefix + s.charAt(i), s.substring(i + 1));
    }  
}
