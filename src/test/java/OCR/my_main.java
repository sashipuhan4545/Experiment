package OCR;



import java.io.PrintWriter;




public class my_main {


	public static void validate_Using_OCR(String imageName) {
		
		String input_file="src/test/resources/OCR_FILES/"+imageName+"";
		System.out.println(input_file);
		String output_file="src/test/resources/OCR_FILES/ocr";	
		String tesseract_install_path="src/test/resources/OCR/tesseract.exe";
		
		String[] command =
			{
					"cmd",
			};
		Process p;
		try {
			
			System.out.println("9999999999999999999999999");
			p = Runtime.getRuntime().exec(command);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("\""+tesseract_install_path+"\" \""+input_file+"\" \""+output_file+"\" ");
			stdin.close();
			p.waitFor();
		/*	System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();*/
			System.out.println(Read_File.read_a_file(output_file+".txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
		
		
		
		
		
		
		
		
		
	}