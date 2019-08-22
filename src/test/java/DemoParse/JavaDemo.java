package DemoParse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;

public class JavaDemo {
	public static void main(String[] args) throws IOException {
		
		removeSpace();
	}
	
	
	public static void removeSpace() throws IOException {
		File temporaire = new File("temp.txt");
		  try {
		    Scanner scanner = new Scanner("C:/Users/farheen.t/eclipse-workspace/XP8_AutomationProduct/JavaTest.txt");
		    BufferedWriter bw = new BufferedWriter(new FileWriter(temporaire));
		    while (scanner.hasNextLine()) {
		      String line = StringUtils.stripEnd(scanner.nextLine(),null); // Clean blanks at the end of the line
		      if (StringUtils.isNotBlank(line)) {
		        bw.write(line); // Keep the line only if not blank
		        if (scanner.hasNextLine()){
		          // Go to next line (Win,Mac,Unix) if there is one
		          bw.write(System.getProperty("line.separator"));
		        }
		      }
		      bw.flush();
		    }
		    scanner.close();
		    bw.close();
		  
		  }
		  catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		  }
	}
}
