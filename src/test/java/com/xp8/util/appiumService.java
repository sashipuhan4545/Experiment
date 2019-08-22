/* -------------------------------------------Appium server loader class---------------------------------------------------------------------
 * 							Starts an appium server on ip address and port specified..
 * 
 * 
 


package com.xp8.util;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DecimalFormat;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class appiumService {
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	protected int port = 4723;
	protected static URL appiumServerURL;
	public static String primary,secondary;

	public static double TOTAL_NUM_OF_TESTCASES;

	public static double INCREMENT_VLAUE;
	public static double TESTCASES_INCREMENTER=1;


	@BeforeSuite	
	public void startServer() {

		System.out.println("<------------------------Started Appium Service---------------------------------->");
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");

		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File(System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
		builder.withLogFile(new File (System.getProperty("user.home")+"\\AppiumServerLogs.txt"));

		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);


		//builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		//builder.withArgument(GeneralServerFlag.LOG_LEVEL);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

		//	if (!this.checkIfServerIsRunning(port)) {
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		appiumServerURL = service.getUrl();

		//	}
		//		else {
		//			System.out.println("Appium Server already running on Port - " + port);
		//		}
	}

	//	@AfterSuite
	//	public void stopServer() {
	//		service.stop();
	//	}


	@BeforeSuite
	public void profileSelectionForQAAndNonQA() {


		if(MainController.TEAM_BASED_LOGIN.contains("QA Team")) {

			// AllQA.PRIMARYDEVMDN=AllQA.PRIMARYDEVMDN;
			//AllQA.REFERENCEDEVMDN=AllQA.REFERENCEDEVMDN;
			System.out.println("Logged in As QA-team");
		}else {

			AllQA.PRIMARYDEVMDN=SalesTeam.PRIMARYDEVMDN;
			AllQA.REFERENCEDEVMDN=SalesTeam.REFERENCEDEVMDN;
			AllQA.NUMOFCALLS=SalesTeam.NUMOFCALLS;
			AllQA.CALLDURATION=SalesTeam.CALLDURATION;
			AllQA.CALLGAP=SalesTeam.CALLGAP;



			System.out.println(AllQA.PRIMARYDEVMDN +","+AllQA.REFERENCEDEVMDN +","+AllQA.NUMOFCALLS);	
		}

	}

	@BeforeSuite	
	public void deletedTestReports() {

		try {

			deleteAdbLogFiles();

			System.out.println("<-----------------------DELETING EXTENT REPORT----------------->");

			File dir = new File("src/test/resources/extentreport");
			if(dir.isDirectory() == false) 
			{
				System.out.println("Not a directory. Do nothing");
				return;
			}

			File[] listFiles = dir.listFiles();
			for(File file : listFiles)
			{
				System.out.println("Deleting "+file.getName());
				file.delete();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}


	}

	//This Method is used to delete all adb logs before Test case execution starts
	public void deleteAdbLogFiles() {
		
		try {


			System.out.println("<-----------------------DELETING ADB LOG Files----------------->");

			File dir = new File("src/test/resources/adbLogs");
			if(dir.isDirectory() == false) 
			{
				System.out.println("Not a directory. Do nothing");
				return;
			}

			File[] listFiles = dir.listFiles();
			for(File file : listFiles)
			{
				System.out.println("Deleting "+file.getName());
				file.delete();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}


	}



	public boolean checkIfServerIsRunning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;

		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}




	@AfterMethod
	public void UpdateProgressIndicator() {


		try {

			System.out.println(TESTCASES_INCREMENTER);

			double percentagePerCase = (TESTCASES_INCREMENTER/TOTAL_NUM_OF_TESTCASES);
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.FLOOR);
			String num = df.format(percentagePerCase);
			double value=Double.parseDouble(num);
			INCREMENT_VLAUE=value;
			TESTCASES_INCREMENTER++;



		}catch (Exception e) {

			System.out.println("Any Kind of Exeption");

		}

		}
}
*/