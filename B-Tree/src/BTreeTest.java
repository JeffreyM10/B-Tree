/**
 * Unit test cases for the implementation of a Binary Search Tree.
 *
 * @author  __your_name___
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BTreeTest
{
	/**
	 * the tree to use for testing
	 */
	private BTree<Integer> tree;
	private StringVisitor<Integer> strVisitor;

	// returns a tree loaded with the given items
	static BTree<Integer> load(int order, Integer... items)
	{
		IntComparator compare = new IntComparator();
		BTree<Integer> tree = new BTree<Integer>(order, compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}

	/**
	 * Test the add method with tree of order 2
	 */
	@Test
	public void test_add_2()
	{
		// testing empty of order 2
		tree = load(2); //order then items
		tree.add( 1 );
		assertEquals( tree.toString(), "[[1]]" );
		assertEquals( tree.toStringSorted(), "[[1]]" );

		// testing single tree of order 2
		//*** one test case for single may not be enough ***
		tree = load( 2, 1 );
		tree.add( 2 );
		assertEquals( tree.toString(), "[[1 2]]" );
		assertEquals( tree.toStringSorted(), "[[1 2]]" );
		
		tree = load( 2, 5 );
		tree.add( 1 );
		assertEquals( tree.toString(), "[[1 5]]" );
		assertEquals( tree.toStringSorted(), "[[1 5]]" );
		
		// testing multi tree of order 2
		//*** one test case for multi may not be enough ***
//		System.out.print("hi");
		tree = load( 2, 1, 2, 3, 4, 5, 10);
		tree.add( 20 );
		assertEquals( tree.toString(), "[[4] [2] [10] [1] [3] [5] [20]]" );
		assertEquals( tree.toStringSorted(), "[[1] [2] [3] [4] [5] [10] [20]]" );
		
		tree = load( 2, 1, 2, 3, 4, 5, 10, 20);
		tree.add( 6 );
		assertEquals( tree.toString(), "[[4] [2] [10] [1] [3] [5 6] [20]]" );
		assertEquals( tree.toStringSorted(), "[[1] [2] [3] [4] [5 6] [10] [20]]" );
		
		tree = load( 2, 1, 2, 3, 4, 5, 6, 10, 20);
		tree.add( 9 );
		assertEquals( tree.toString(), "[[4] [2] [6 10] [1] [3] [5] [9] [20]]" );
		assertEquals( tree.toStringSorted(), "[[1] [2] [3] [4] [5] [9] [6 10] [20]]" );		
	}

	@Test
	public void test_add_4()
	{
		// testing empty of order 2
		tree = load(4); //order then items
		tree.add( 1 );
		assertEquals( tree.toString(), "[[1]]" );
		assertEquals( tree.toStringSorted(), "[[1]]" );

		// testing single tree of order 2
		//*** one test case for single may not be enough ***
		tree = load( 4, 1 );
		tree.add( 2 );
		assertEquals( tree.toString(), "[[1 2]]" );
		assertEquals( tree.toStringSorted(), "[[1 2]]" );
		
		tree = load( 4, 5 );
		tree.add( 1	 );
		assertEquals( tree.toString(), "[[1 5]]" );
		assertEquals( tree.toStringSorted(), "[[1 5]]" );
				
		// *** one test case for multi may not be enough ***
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		tree.add( 20 );
		assertEquals( tree.toString(), "[[10] [3 6] [13 17] [1 2] [4 5] [7 8] [11 12] [15 16] [18 20]]" );
//		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 18 20]]" );
		
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 20);
		tree.add( 21 );
		assertEquals( tree.toString(), "[[10] [3 6] [13 17] [1 2] [4 5] [7 8] [11 12] [15 16] [18 20 21]]" );
//		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 18 20 21]]" );
		
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18, 20, 21);
		tree.add( 22 );
		assertEquals( tree.toString(), "[[10] [3 6] [13 17] [1 2] [4 5] [7 8] [11 12] [15 16] [18 20 21 22]]" );
//		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 18 20 21 22]]" );
	}
	
	@Test
	public void test_contains()
	{
		// testing empty
		tree = load(4);
		assertFalse( tree.contains(4));
		assertEquals( tree.toString(), "[[]]" );
		assertEquals( tree.toStringSorted(), "[[]]" );

		// testing single	
		tree = load(4, 1);
		assertFalse( tree.contains(4));
		assertEquals( tree.toString(), "[[1]]" );
		assertEquals( tree.toStringSorted(), "[[1]]" );

		tree = load(4, 1);
		assertTrue( tree.contains(1));
		assertEquals( tree.toString(), "[[1]]" );
		assertEquals( tree.toStringSorted(), "[[1]]" );

		// testing multi
		//false
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		assertFalse( tree.contains(20));
		assertEquals( tree.toString(), "[[3 6 10 13] [1 2] [4 5] [7 8] [11 12] [15 16 17 18]]" );
		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 17 18]]" );

		//root
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		assertTrue( tree.contains(10));
		assertEquals( tree.toString(), "[[3 6 10 13] [1 2] [4 5] [7 8] [11 12] [15 16 17 18]]" );
		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 17 18]]");

		//far right
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		assertTrue( tree.contains(17));
		assertEquals( tree.toString(), "[[3 6 10 13] [1 2] [4 5] [7 8] [11 12] [15 16 17 18]]" );
		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 17 18]]");

		//far Left 
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		assertTrue( tree.contains(1));
		assertEquals( tree.toString(), "[[3 6 10 13] [1 2] [4 5] [7 8] [11 12] [15 16 17 18]]" );
		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 17 18]]");

		//Left 
		tree = load( 4, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 15, 16, 17, 18);
		assertTrue( tree.contains(6));
		assertEquals( tree.toString(), "[[3 6 10 13] [1 2] [4 5] [7 8] [11 12] [15 16 17 18]]" );
		assertEquals( tree.toStringSorted(), "[[1 2 3 4 5 6 7 8 10 11 12 13 15 16 17 18]]");
	}
	
	@Test
	public void test_inorder()
	{
		//empty
		tree = load(2);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[[]]" );
		
		//single
		tree = load(2,10 );
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10]" );
		assertEquals( tree.toString(), "[[10]]" );

		//multi
		tree = load(2, 10, 6, 16, 3, 7, 12, 17 );
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 6 7 10 12 16 17]" );
		assertEquals( tree.toString(), "[[6] [10] [16] [3] [7] [12] [17]]" );
	}
}