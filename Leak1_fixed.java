/**
 * Custom test for resource leak analyzer by Zhao Wang
 * Version: 1.0
 * Date: 4/25/2022
 */
package cs6110;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Leak1_fixed {
	
	// Generator the local path to find the file
	private URL url = Main_fixed.class.getProtectionDomain().getCodeSource().getLocation();
	private String path = url.getPath() + File.separator + "test_java" + File.separator;
	private String fileName = path + "itest1.txt";
	
	/**
	 * Comment this method to pass the test
	 * @throws FileNotFoundException
	
	void newLeak1() throws FileNotFoundException {
		new FileInputStream(fileName);
	}
	
	/**
	 * Comment this method to pass the test
	 * @throws FileNotFoundException
	
	void newLeak2() throws FileNotFoundException {
		new FileInputStream(fileName);
		new FileInputStream(fileName);
	}
	*/

	/**
	 * Expect to pass this test
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void close1() throws IOException, FileNotFoundException {
		FileInputStream f1 = new FileInputStream(fileName);
		f1.close();
	}
	
	/**
	 * Comment this method to pass the test
	 * @throws IOException
	 * @throws FileNotFoundException
	
	void close1with2() throws IOException, FileNotFoundException {
		FileInputStream f1 = new FileInputStream(fileName);
		FileInputStream f2 = new FileInputStream(fileName);
		f1.close();
	}
	*/
	
	/**
	 * Expect to pass this test
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void close2with2() throws IOException, FileNotFoundException {
		FileInputStream f1 = new FileInputStream(fileName);
		f1.close();
		FileInputStream f2 = new FileInputStream(fileName);
		f2.close();
	}
	  
}
