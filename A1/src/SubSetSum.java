//http://stackoverflow.com/questions/10423575/given-a-set-of-n-integers-return-all-subsets-of-k-elements-that-sum-to-0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SubSetSum {

	private Map<List<Integer>, List<List<Integer>>> cache = new HashMap<List<Integer>, List<List<Integer>>> ();

	public SubSetSum() {
		
	}
	
	public ArrayList<ArrayList<Integer>> calcSubSet(List<Integer> data, int size, int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		int x = 0;
		
		for (List<Integer> sub : subsetsWithSum(data, Arrays.asList(size, sum, 0), cache)) {		
			
			result.add(new ArrayList<Integer>());
			
			for (int i : sub) {
				result.get(x).add(data.get(i));
			}

			x++;
		}
		
		return result;
	}

	private static List<List<Integer>> subsetsWithSum(List<Integer> A, List<Integer> args, Map<List<Integer>, List<List<Integer>>> cache) {
		if (cache.containsKey(args))
			return cache.get(args);

		int k = args.get(0), s = args.get(1), o = args.get(2);
		List<List<Integer>> results = new LinkedList<List<Integer>>();

		if (k == 1) {
			if (A.get(o) == s)
				results.add(Arrays.asList(o));
		}
		else {
			List<Integer> newArgs = Arrays.asList(k-1, s-A.get(o), o+1);

			for (List<Integer> sub : subsetsWithSum(A, newArgs, cache))
			{
				List<Integer> newSub = new LinkedList<Integer>(sub);
				newSub.add(0, o);
				results.add(0, newSub);
			}
		}

		if (o < A.size() - k)
			results.addAll(subsetsWithSum(A, Arrays.asList(k, s, o+1), cache));

		cache.put(args, results);
		return results;
	}

}
