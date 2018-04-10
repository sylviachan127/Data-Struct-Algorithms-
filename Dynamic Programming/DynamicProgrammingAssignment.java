/**
 * Assignment to teach dynamic programming using 3 simple example problems: 1.
 * Fibonacci numbers 2. Longest common subsequence 3. Edit distance
 * 
 * @author Yuen Han Chan
 */
public class DynamicProgrammingAssignment {
	public static int num_calls = 0; // DO NOT TOUCH

	/**
	 * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
	 * Remember that fib(0) = 0 and fib(1) = 1.
	 * 
	 * This should NOT be done recursively - instead, use a 1 dimensional array
	 * so that intermediate fibonacci values are not re-calculated.
	 * 
	 * The running time of this function should be O(n).
	 * 
	 * @param n
	 *            A number
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		num_calls++; // DO NOT TOUCH

		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		int[] fibCal = new int[n + 1];
		fibCal[0] = 0;
		fibCal[1] = 1;

		for (int num = 2; num <= n; num++) {
			fibCal[num] = fibCal[num - 1] + fibCal[num - 2];
		}
		return fibCal[n];
	}

	/**
	 * Calculates the length of the longest common subsequence between a and b.
	 * 
	 * @param a
	 * @param b
	 * @return The length of the longest common subsequence between a and b
	 */
	public static int lcs(String a, String b) {
		num_calls++; // DO NOT TOUCH
		int[][] word = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i < word[0].length; i++) {
			word[0][i] = 0;
		}
		for (int i = 0; i < word.length; i++) {
			word[i][0] = 0;
		}

		for(int j=1; j<word[0].length; j++){
			for(int i = 1; i<word.length; i++){
				if(a.charAt(i-1)==b.charAt(j-1)){
					word[i][j]=word[i-1][j-1]+1;
				}
				else{
					word[i][j]=Math.max(word[i][j-1], word[i-1][j]);
				}
			}
		}
		return word[word.length-1][word[0].length-1];
	}
	
	/**
	 * Calculates the edit distance between two strings.
	 * 
	 * @param a
	 *            A string
	 * @param b
	 *            A string
	 * @return The edit distance between a and b
	 */
	public static int edit(String a, String b) {
		num_calls++; // DO NOT TOUCH
		int[][] word = new int[a.length() + 1][b.length() + 1];
		int count = 0;
		for (int i = 0; i < word[0].length; i++) {
			word[0][i] = count++;
		}
		count = 0;
		for (int i = 0; i < word.length; i++) {
			word[i][0] = count++;
		}
		for(int j=1; j<word[0].length; j++){
			for(int i = 1; i<word.length; i++){
				if(a.charAt(i-1)==b.charAt(j-1)){
					int minLU = Math.min(word[i][j-1], word[i-1][j]);
					word[i][j]=Math.min(word[i-1][j-1],minLU+1);
				}
				else{
					int minLU = Math.min(word[i][j-1], word[i-1][j]);
					word[i][j]=Math.min(word[i-1][j-1],minLU)+1;
				}
			}
		}
		return word[word.length-1][word[0].length-1];
	}
}
