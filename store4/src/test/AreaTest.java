package test;

/* 
 * Req: Area = subarray in neighbourhood of a given size of an item in an array.
 *  
 * Tests:
 *               0  1  2  3  4  5  6  7  8
 * done Area of [3, 2, 7, 3, 5, 9, 1, 3, 2] item #3 of size 5 = [2, 7, 3, 5, 9] 
 * 
 * done Area of item #5 of size 4 (even) = [5, 9, 1, 3]
 * 
 * Area of item #0 of size 5 (odd, at left edge) = [3, 2, 7, 3, 5] 
 * 
 * Area of item #8 of size 5 (odd, at the right edge) = [5, 9, 1, 3, 2]
 * 
 * #1 & #7 ? 
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AreaTest {
	
	private int data[];
	private Areator areator;



	static class Areator {

		private final int[] data;

		public Areator(int[] data) {
			this.data = data; 
		}

		public int[] getAreaOfPointWithSize(int index, int size) {
			int[] result = new int[size]; 
			for (int i = 0; i < size; i++) { 
				result[i] = data[index - (size - 1) / 2 + i]; 
			}
			return result; 
		} 
		
	}

	@Before
	public final void setup() { 
		data = new int[] {3, 2, 7, 3, 5, 9, 1, 3, 2};
		areator = new Areator(data);
	}
	
	@Test
	public final void testEvenAreaInTheMiddle() {
		int[] area = areator.getAreaOfPointWithSize(5, 4); 
		
		assertArrayEquals(new int[] {5, 9, 1, 3}, area); 
	}


	
	@Test
	public final void testOddAreaInTheMiddle() {
		int[] area = areator.getAreaOfPointWithSize(3, 5); 
		
		assertArrayEquals(new int[] {2, 7, 3, 5, 9}, area); 
	}

}
