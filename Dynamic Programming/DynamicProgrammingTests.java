import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * These are the test cases we will use to grade your dynamic programming assignment.
 * 
 * Each test is worth 5 points for a total of 100 points.
 *  
 * Do not manipulate the value of num_calls in your code. We want to make sure you aren't solving these problems recursively.
 * 
 * 
 */
public class DynamicProgrammingTests{
	@Rule
    public Timeout globalTimeout = new Timeout(200); // 10 seconds max per method tested
	
	/*
	 * TEST FIBONACCI
	 */
	@Test
	public void testFib0() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 0: Incorrect Value", 0, DynamicProgrammingAssignment.fib(0));
		assertEquals("Failed fibonnaci test case 0: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}

	@Test
	public void testFib1() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 1: Incorrect Value", 1, DynamicProgrammingAssignment.fib(1));
		assertEquals("Failed fibonnaci test case 1: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testFib2() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 2: Incorrect Value", 5, DynamicProgrammingAssignment.fib(5));
		assertEquals("Failed fibonnaci test case 2: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testFib3() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 3: Incorrect Value", 233, DynamicProgrammingAssignment.fib(13));
		assertEquals("Failed fibonnaci test case 3: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testFib4() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 4: Incorrect Value", 514229, DynamicProgrammingAssignment.fib(29));
		assertEquals("Failed fibonnaci test case 4: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testFib5() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed fibonacci test case 5: Incorrect Value", 24157817, DynamicProgrammingAssignment.fib(37));
		assertEquals("Failed fibonnaci test case 5: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}

	/*
	 * TEST LONGEST COMMON SUBSEQUENCE
	 */

	@Test
	public void testLCS0() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 0: Incorrect Value", 4, DynamicProgrammingAssignment.lcs("ABBA", "ABBA"));
		assertEquals("Failed lcs test case 0: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testLCS1() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 1: Incorrect Value", 5, DynamicProgrammingAssignment.lcs("dancing queen", "dances"));
		assertEquals("Failed lcs test case 1: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}

	@Test
	public void testLCS2() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 2: Incorrect Value", 3, DynamicProgrammingAssignment.lcs("sharpie", "grape"));
		assertEquals("Failed lcs test case 2: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testLCS3() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 3: Incorrect Value", 0, DynamicProgrammingAssignment.lcs("NOTHING IN", "common"));
		assertEquals("Failed lcs test case 3: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testLCS4() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 4: Incorrect Value", 20, DynamicProgrammingAssignment.lcs("The next sentence is false.", "The previous sentence is true."));
		assertEquals("Failed lcs test case 4: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}	
	
	@Test
	public void testLCS5() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 5: Incorrect Value", 6, DynamicProgrammingAssignment.lcs("an apple a day", "keeps the doctor away"));
		assertEquals("Failed lcs test case 5: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
	@Test
	public void testLCS6() {
		DynamicProgrammingAssignment.num_calls = 0;
		assertEquals("Failed lcs test case 6: Incorrect Value", 10, DynamicProgrammingAssignment.lcs("finals are coming", "coming around the mountain"));
		assertEquals("Failed lcs test case 6: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
	}
	
    /*
	 * TEST EDIT DISTANCE
	 */
    @Test
    public void testEdit0() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 0: Incorrect Value", 1, DynamicProgrammingAssignment.edit("jeans", "beans"));
    	assertEquals("Failed edit distance test case 0: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit1() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 1: Incorrect Value", 2, DynamicProgrammingAssignment.edit("ababb", "bbab"));
    	assertEquals("Failed edit distance test case 1: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit2() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 2: Incorrect Value", 14, DynamicProgrammingAssignment.edit("peter piper picked", "peck of pickled peppers"));
    	assertEquals("Failed edit distance test case 2: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit3() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 3: Incorrect Value", 0, DynamicProgrammingAssignment.edit("yes", "yes"));
    	assertEquals("Failed edit distance test case 3: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit4() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 4: Incorrect Value", 16, DynamicProgrammingAssignment.edit("The Amazing Spiderman", "The Incredible Hulk"));
    	assertEquals("Failed edit distance test case 4: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit5() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 5: Incorrect Value", 3, DynamicProgrammingAssignment.edit("salmon", "sultan"));
    	assertEquals("Failed edit distance test case 5: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    }
    
    @Test
    public void testEdit6() {
    	DynamicProgrammingAssignment.num_calls = 0;
    	assertEquals("Failed edit distance test case 6: Incorrect Value", 18, DynamicProgrammingAssignment.edit("finals are coming", "coming around the mountain"));
    	assertEquals("Failed edit distance test case 6: Should not be recursive", 1, DynamicProgrammingAssignment.num_calls);
    } 
}
