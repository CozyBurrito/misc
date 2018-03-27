
public class Day15 {

	public static void main(String[] args) {
		int[][] ing = {{3,0,0,-3,2}, {-3,3,0,0,9}, {-1,0,4,0,1}, {0,0,-2,2,8}}; 
		int capacity, durability, flavor, texture, calories; 
		int h, score, max = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100 - i; j++) {
				for (int k = 0; k < 100 - i - j; k++) {
					h = 100 - i - j - k;

					capacity   = (ing[0][0] * i) + (ing[1][0] * j) + (ing[2][0] * k) + (ing[3][0] * h);
					durability = (ing[0][1] * i) + (ing[1][1] * j) + (ing[2][1] * k) + (ing[3][1] * h);
					flavor     = (ing[0][2] * i) + (ing[1][2] * j) + (ing[2][2] * k) + (ing[3][2] * h);
					texture    = (ing[0][3] * i) + (ing[1][3] * j) + (ing[2][3] * k) + (ing[3][3] * h);

						if (capacity <= 0 || durability <= 0 || flavor <= 0 || texture <= 0)
							score = 0;
						else
							score = capacity * durability * flavor * texture;

						if (score > max)
							max = score;

				}
			}
		}

		System.out.println(max);

	}

}
