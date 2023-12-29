/**
 * Custom test 3 for resource leak analyzer by Zhao Wang
 * Version: 1.0
 * Date: 4/25/2022
 */
package cs6110;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Leak3_fixed {
	
	// Generator the local path to find the file
	private URL url = Main_fixed.class.getProtectionDomain().getCodeSource().getLocation();
	private String path = url.getPath() + File.separator + "test_java" + File.separator;
	private String fileName = path + "itest1.txt";
	
	/**
	 * Expect to pass this test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void loopTest1(String[] fileNames) throws IOException, FileNotFoundException {
		FileInputStream f;
		for(int i = 0; i < fileNames.length; i++) {
			String fileName = fileNames[i];
			f = new FileInputStream(fileName);
			f.close();
		}
	}
	
	/**
	 * Comment this method to pass the test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 
	void loopTest2(String[] fileNames) throws IOException, FileNotFoundException {
		FileInputStream[] fs = new FileInputStream[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			fs[i] = new FileInputStream(fileNames[i]);
		}
		for (int i = 0; i < fileNames.length; i++) {
			fs[i].close();
		}
	}
	*/

	/**
	 * Expect to pass this test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	FileInputStream returnTest1() throws IOException, FileNotFoundException {
		return new FileInputStream(fileName);
	}
	
	/**
	 * Expect to pass this test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	FileInputStream returnTest2() throws IOException, FileNotFoundException {
		return returnTest1();
	}
	
	/**
	 * Expect to pass this test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void returnTest3() throws IOException, FileNotFoundException {
		returnTest2().close();
	}
	
	/**
	 * Comment this method to pass the test
	 * @param fileNames
	 * @throws IOException
	 * @throws FileNotFoundException
	 
	int returnTest4() throws IOException, FileNotFoundException {
		returnTest2();
		return 0;
	}
	*/
}
