/**
 * Custom test 2 for resource leak analyzer by Zhao Wang
 * Version: 1.0
 * Date: 4/25/2022
 */
package cs6110;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Leak2 {
	
	// Generator the local path to find the file
	private URL url = Main.class.getProtectionDomain().getCodeSource().getLocation();
	private String path = url.getPath() + File.separator + "test_java" + File.separator;
	private String fileName = path + "itest1.txt";
	
	/**
	 * Comment this method to pass the test
	 * @param f1
	 * @param f2
	 * @throws IOException
	 */
	void ifTest1(FileInputStream f1, FileInputStream f2) throws IOException {
		if(f1 == f2) {
			f1.close();
		}
		else {
			f1.close();
			f2.close();
	    }
	}
	
	/**
	 * Comment this method to pass the test
	 * @param b
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void ifTest2(Boolean b) throws IOException, FileNotFoundException {
		FileInputStream f;
		if(b)
			f = new FileInputStream(fileName);
	}
	
	/**
	 * Expect to pass this test
	 * @param b
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void ifTest3(Boolean b) throws IOException, FileNotFoundException {
		FileInputStream f = new FileInputStream(fileName);
		if(b) {
			f.close();
	    }
		else {
			f.close();
		}
	}
	
}
