package OCR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.xp8.util.CommonConfig;




public class Read_File {
	
	
	public static String read_a_file(String file_name) {
		BufferedReader br = null; 
		String read_string="";
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file_name));
			while ((sCurrentLine = br.readLine()) != null) {
				read_string=read_string+sCurrentLine;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return read_string;
	}
	
	
	
	
	public static void takeScreenShotForOcr(String imageName) {

		File scrFile = ((TakesScreenshot) CommonConfig.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			
			String ocrImageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\OCR_FILES\\" +imageName+".png";
			FileUtils.copyFile(scrFile, new File(ocrImageFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("<-----------Exeption occurs in takeScreenShotsForOcr method----------->");
		}

	}

	
	
}