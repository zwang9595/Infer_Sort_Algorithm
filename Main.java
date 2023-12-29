/**
 * Custom test for sort algorithm and resource leak analyzer by Zhao Wang
 * Version: 1.2
 * Date: 4/25/2022
 */
package cs6110;

import java.util.*;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		// Generator the local path to find the file
		URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
		String path = url.getPath() + File.separator + "test_java" + File.separator;
		
		IntegerComparator1 cint1 = new IntegerComparator1();
		IntegerComparator2 cint2 = new IntegerComparator2();
		StringComparator1 cstr1 = new StringComparator1();
		StringComparator2 cstr2 = new StringComparator2();
		
    	new FileInputStream(path + "itest1.txt");	// Comment this to pass the test
		
		// Generate random integer test for sort algorithm
		int length = 100;
		Integer[] test1 = new Integer[length];
		Random rand = new Random();
		for(int i = 0; i < length; i++) {
			test1[i] = rand.nextInt(length) - length / 2;
		}
		
		// Test for all different sorts and print result for manual time complexity analysis
		Sort<Integer> Isort1 = new Sort<Integer>(test1, cint1);
		Isort1.print();
		// Test for changing comparator
		Isort1.setComparator(cint2);
		Isort1.print();
		
		// Test for all different sorts and print result for manual time complexity analysis
		// Also test for resource leak
		Integer[] test2 = intArrayReader1(path + "itest1.txt");
		Sort<Integer> Isort2 = new Sort<Integer>(test2, cint1);
		Isort2.print();
		// Test for changing comparator
		Isort2.setComparator(cint2);
		Isort2.print();
		
		// Test for all different sorts and print result for manual time complexity analysis
		// Also test for resource leak
		// Comment this section to pass the test
		Integer[] test3 = intArrayReader2(path + "itest2.txt");
		Sort<Integer> Isort3 = new Sort<Integer>(test3, cint1);
		Isort3.print();
		// Test for changing comparator
		Isort3.setComparator(cint2);
		Isort3.print();
		
		String[] test4 = new String[] {"aaaa", "apple", "banana", "bananc"};
		// Test for all different sorts and print result for manual time complexity analysis
		Sort<String> Ssort1 = new Sort<String>(test4, cstr1);
		Ssort1.print();
		// Test for changing comparator
		Ssort1.setComparator(cstr2);
		Ssort1.print();
		
		// Test for all different sorts and print result for manual time complexity analysis
		// Also test for resource leak
		String[] test5 = stringArrayReader1(path + "stest1.txt");
		Sort<String> Ssort2 = new Sort<String>(test5, cstr1);
		Ssort2.print();
		// Test for changing comparator
		Ssort2.setComparator(cstr2);
		Ssort2.print();
		
		// Test for all different sorts and print result for manual time complexity analysis
		// Also test for resource leak
		// Comment this section to pass the test
		String[] test6 = stringArrayReader2(path + "stest2.txt");
		Sort<String> Ssort3 = new Sort<String>(test6, cstr1);
		Ssort3.print();
		// Test for changing comparator
		Ssort3.setComparator(cstr2);
		Ssort3.print();
		
	}
	
	/**
	 * Text file integer reader that can pass the leak test
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	private static Integer[] intArrayReader1(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		Integer[] t = new Integer[15];
		int count = 0;
		while(s.hasNextInt()) {
			t[count++] = s.nextInt();
		}
		s.close();
		return t;
	}
	
	/**
	 * Text file integer reader that cannot pass the leak test
	 * Comment this section to pass the test
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	private static Integer[] intArrayReader2(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		Integer[] t = new Integer[15];
		int count = 0;
		while(s.hasNextInt()) {
			t[count++] = s.nextInt();
		}
		return t;
	}
	
	/**
	 * Text file string reader that can pass the leak test
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	private static String[] stringArrayReader1(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		String[] t = new String[15];
		int count = 0;
		while(s.hasNext()) {
			t[count++] = s.next();
		}
		s.close();
		return t;
	}

	/**
	 * Text file string reader that cannot pass the leak test
	 * Comment this section to pass the test
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	private static String[] stringArrayReader2(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		String[] t = new String[15];
		int count = 0;
		while(s.hasNext()) {
			t[count++] = s.next();
		}
		return t;
	}
	
	/**
	 * Integer comparator ascend for test
	 */
	private static class IntegerComparator1 implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 < o2) {
				return -1;
			}
			else if(o1 > o2) {
				return 1;
			}
			else {
				return 0;
			}
		}
		
	}
	
	/**
	 * String comparator ascend for test
	 */
	private static class StringComparator1 implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if (o1 == o2) {
		        return 0;
		    }
		    if (o1 == null) {
		        return -1;
		    }
		    if (o2 == null) {
		        return 1;
		    }
		    return o1.compareTo(o2);
		}
		
	}
	
	/**
	 * Integer comparator descend for test
	 */
	private static class IntegerComparator2 implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 < o2) {
				return 1;
			}
			else if(o1 > o2) {
				return -1;
			}
			else {
				return 0;
			}
		}
		
	}
	
	/**
	 * String comparator descend for test
	 */
	private static class StringComparator2 implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if (o1 == o2) {
		        return 0;
		    }
		    if (o1 == null) {
		        return -1;
		    }
		    if (o2 == null) {
		        return 1;
		    }
		    return -o1.compareTo(o2);
		}
		
	}
	
}
