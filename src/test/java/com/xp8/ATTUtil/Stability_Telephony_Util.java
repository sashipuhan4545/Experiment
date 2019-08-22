package com.xp8.ATTUtil;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

import org.apache.poi.ss.formula.functions.MinaMaxa;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.BaseUtil;
import com.xp8.util.HomeController;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class Stability_Telephony_Util extends BaseUtil {
	public static ExtentReports extent;
	public static ExtentTest test;
	public Process p;

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = HomeController.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = HomeController.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		System.out.println(r_Id);
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	
	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+validationString);
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
		return check1;
	}

	
	public void installAPK() throws InterruptedException, IOException {
		System.out.println(" +++");
		customWait(1000);
		Process p1 = Runtime.getRuntime().exec("adb -s "+p_Id+" install src/test/resources/StorageFile/FillMemory.apk");
		customWait(2000);
//		 aDriver.installApp("src/test/resources/StorageFile/FillMemory.apk");
		  Thread.sleep(3000);
		  p1.destroy();
		  clickOnAppList();
		  customWait(1000);
		  scrollToElements(Locators_BaseUtil.fillmemoryIcon);
		  customWait(1000);
		  if(isElementExist(Locators_BaseUtil.fillmemoryIcon)) {
		   System.out.println("Verified");
		   test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		  }
		  else {
			  System.out.println("Not Verified");
			   test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		  }		  
	}
	
	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(Locators_Stability.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Stability.AllowOptn)) {
					clickBtn(Locators_Stability.AllowOptn);
				}
			}
			minWait();
			clickBtn(Locators_Stability.ALL_page);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			if(isElementExist(Locators_Stability.longpress_FirstContact)) {
				customWait(1000);
				TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_Stability.longpress_FirstContact).perform().release();
				minWait();
				clickBtn(Locators_Stability.Selection_menu);
				minWait();
				if(isElementExist(Locators_Stability.ALL_Selection_menu)) {
					clickBtn(Locators_Stability.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_Stability.one_Selection_menu);
				}
				clickBtn(Locators_Stability.MoreOptnsIcn);
				minWait();
				clickBtn(Locators_Stability.deleteContactOptn);
				minWait();
				clickBtn(Locators_Stability.OKBtn);
				APP_LOGS.info("Contacts Deleted");
				customWait(9000);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
			Assert.fail();
		}
	}
	
	public void pressBack() throws InterruptedException {
		/*
		 * Press Keycode back to transverse back page
		 */
		
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}


	public void createContact(String name,String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {
			Locators_Stability.AddtoContact.click();
			minWait();

			if(isElementExist(Locators_Stability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_Stability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(Locators_Stability.Contacts_Name)) {
				minWait();
			}
			else {
				minWait();		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				Locators_Stability.AddtoContact.click();
				minWait();
			}
			customWait(4000);
			enterTextToInputField(Locators_Stability.Contacts_Name,name);
			minWait();		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Stability.Contacts_Phone,num);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Stability.Contacts_Email,"Sonimtech@gmail.com");
			minWait();
			clickBtn(Locators_Stability.Save_ConatctIcon);
			minWait();

			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Stability.AllowOptn))
				{
					clickBtn(Locators_Stability.AllowOptn);
				} 
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
			Assert.fail();
		}		
	}

	
	public void contactCreation(String getname) throws IOException, InterruptedException {
		String getcontactNameText = null;
		/* String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		 String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();*/
		try {
			
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Stability.AllowOptn)) {
					clickBtn(Locators_Stability.AllowOptn);
				}
			}
			minWait();
			Locators_Stability.AddtoContact.click();
			minWait();

			if(isElementExist(Locators_Stability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_Stability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.INSERT -t vnd.android.cursor.dir/contact -e name \\"+getname+" -e phone "+refNum+"");		
			customWait(1000);
			minWait();
			clickBtn(Locators_Stability.Save_ConatctIcon);
			customWait(1000);		
			
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Stability.AllowOptn)) {
					clickBtn(Locators_Stability.AllowOptn);
				}
			}
			getcontactNameText=Locators_Stability.ContactTitle.getText();
			System.out.println(getcontactNameText);
			test.log(LogStatus.INFO, "Created Contact in phonebook with name:" + getname);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
			Assert.fail();
		}
	}
	

	public void searchContact(String name) throws InterruptedException {
		/*
		 * Search contact 
		 */
		try {
			minWait();
			clickBtn(Locators_Stability.Search_ConatctIcon);
			customWait(2000);
			enterTextToInputField(Locators_Stability.findContacts, name);
			minWait();
			clickBtn(Locators_Stability.longpress_FirstContact);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			Assert.fail();
		}
	}
	
	public void initiateCall() throws InterruptedException {
		/*
		 * initiate call with contact Number
		 */	
		try {
			minWait();
			clickBtn(Locators_Stability.contact_phnNum);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			Assert.fail();
		}		
	}
	
	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	
	}
	
	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			customWait(1000);
			enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " Airplane settings page is not found");
		}
	}
	
	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
			s1.assertTrue(check, " ");
		}
		else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled" );
			
		}
		s1.assertAll();
		return check;
	}
	
	
	//This method will take adb log
		public static void startLog(String fileName) throws AWTException, InterruptedException, FileNotFoundException {
			
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
			customWait(2000);
			try {
				APP_LOGS.info("Adb log started sucessfully ");
				Runtime.getRuntime().exec("adb -s "+Uid+" logcat -b all -v threadtime>src/test/resources/adbLogs/"+fileName+".txt \"");
				Thread.sleep(2000);
			}
			catch(Exception e) {
				System.out.println("Something goes Wrong!!!");  
				e.printStackTrace();  
			}
		}
		
		public String startRIL_Log() throws AWTException, InterruptedException, IOException {

			Thread.sleep(1000);
			String fileName = UUID.randomUUID().toString().replaceAll("-","");
			System.out.println(fileName);
			String path = "src/test/resources/adbLogs/"+fileName+".txt";
			try {
				Runtime.getRuntime().exec("adb -s "+p_Id+" logcat -c");
				Thread.sleep(1000);
				p=Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" logcat -b all -v threadtime>\""+path);
				Thread.sleep(2000);
			} catch(Exception e) {
				e.printStackTrace();
			}
			return fileName;
		}

		public void selectPage(WebElement selectpage) throws InterruptedException {
			try {
				minWait();	
				clickBtn(selectpage);
			}
			catch(org.openqa.selenium.NoSuchElementException e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, " No Such Element Found");
				Assert.fail();
			}
		}
		
		public void callHistory() throws InterruptedException, IOException {
			/*
			 * call save contact
			 */
			try {
				minWait();
				clickBtn(Locators_Stability.MoreOptions);
				minWait();		
				customWait(1000);
				clickBtn(Locators_Stability.callHistory);
				minWait();
				
				clickBtn(Locators_Stability.Call_Contact);
				minWait();
				APP_LOGS.info("Initiated a call");
				minWait();
				customWait(2000);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}

		
		public void phonebookCall(String name) throws InterruptedException, IOException {
			/*
			 * call save contact
			 */
			try {
				minWait();		
				clickBtn(Locators_Stability.Search_ConatctDailer);			
				customWait(1000);
//				enterTextToInputField(Locators_Stability.Search_ConatctDailer,name);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+name+" ");
				customWait(1000);		
				clickBtn(Locators_Stability.Phonebook_firstContact);
				minWait();
				APP_LOGS.info("Initiated a call");
				minWait();
				customWait(2000);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}

		
		public void validateCallLog(String str,int n,String callType) throws InterruptedException, IOException {
			/*
			 * Validate via log String that MO-Voice  call initiated 
			 */
			SoftAssert s1 = new SoftAssert();
			String value = null;
			customWait(2000);
			try {
			for(int j=1;j<=100;j++){
			Process child = Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27" );
			InputStream lsOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(r);
			value=in.readLine();
			System.out.println(value);
				customWait(2000);
				if(value.contains("00000001")) {
//					check = true;
					Thread.sleep(3000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.PASS,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
//					s1.assertTrue(check, " ");		
					break;
				}
				else if(value.contains("00000000")){			
					continue;				
				}
				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
				}
			}
			customWait(6000);
				endcall();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				Thread.sleep(2000);
			}
//			s1.assertAll();
		}
		
		public void memoryFill() throws InterruptedException {
			/*
			 * Fillinternal memory to 92%
			 */

			try {				
					launch_an_app("fillmemory");
					customWait(1000);
					enterTextToInputField(Locators_Stability.enterFill_Memory,"90");
					customWait(1000);
					Locators_Stability.startFilling.click();
					minWait();
					OCRScreencapture("File");
					int i=0;
					while(searchStringOCR("Filling Internal Memory", "OCR")) {				
						
						System.out.println("Screenshot  "+ i);
						i++;
						customWait(10000);
						OCRScreencapture("File");
					
						if(aDriver.isLocked()){
							minWait();
							 Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
							minWait();
							Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 82");
							customWait(500);
							System.out.println("InSide if");
							continue;
						}
						continue;
					}   
					System.out.println("Out ");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Assert.fail();
				}									
			}
	
		public void OCRScreencapture(String fileName) throws InterruptedException {
			//Capture required Screen shots for validation

			OCR.Read_File.takeScreenShotForOcr(fileName);
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR(fileName+".png");
		}
		
		//This method will provide the user a searched  string from the adb log text file  
		public boolean searchStringOCR(String searchstring, String fileName) {

			boolean check = false;
			boolean check1=false;
			boolean check2=false;

			try {

				BufferedReader bf = new BufferedReader(new FileReader("src/test/resources/OCR_FILES/"+fileName+".txt"));
				int linecount = 0;
				String line;
				APP_LOGS.info("Searching for " + searchstring + " in file...");

				while (( line = bf.readLine()) != null){

					boolean indexfound = line.contains(searchstring);

					if (indexfound) {
						check=true;
						APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
						break;
					}
					else {
						// APP_LOGS.info("Word "+searchstring+" was not found at position " + indexfound + " on line " + linecount);
						check=false;
						linecount++;
					}
				}
				bf.close();
			}
			catch (IOException e) {
				APP_LOGS.info("IO Error Occurred: " + e.toString());
			}
			return check;
		}
		
		public static void deleteDirectory() throws IOException, InterruptedException
		{
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		System.out.println(Uid);
		customWait(2000);	
		Runtime.getRuntime().exec("adb -s "+Uid+" shell rm -r /storage/emulated/0/FilledContent");  
				 System.out.println("Deleted Memory");
				 customWait(6000);
				 
				 Process p1 = Runtime.getRuntime().exec("adb -s "+Uid+" uninstall fillememory.myapplication");
				 customWait(2000);	
		}
		
		
		
		public void add_NewContact(int enter1forPhone_2forSimMemory,String contactName, String contactNum, String contactEmail, String address) throws InterruptedException, IOException {
			/* Method is to Add Contact in Contacts.
			 * Precondition:User should give 1 for first argument to save for Phone Memory and 2 fo rsim Memory.
			 */
			try {
				minWait();
				clickBtn(Locators_Stability.add_NewContact);
				minWait();
				if(enter1forPhone_2forSimMemory==1) {
					clickBtn(Locators_Stability.contact_SavingTo);
					minWait();
					clickBtn(Locators_Stability.savingTo_Phone);
					minWait();
					enterTextToInputField(Locators_Stability.name_NewContact, contactName);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					enterTextToInputField(Locators_Stability.phone_NewContact, contactNum);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					
				} 
				clickBtn(Locators_Stability.save_Icon);
				minWait();
//				clearAllow();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}		
		}
		
		
		
		public void make_Call_from_RefDev() throws InterruptedException, IOException {

			try {
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void recieve_Call_PrimaryDev(String str,String fileName,int n,String callType) throws IOException, InterruptedException {

			try {
				for(int j=1;j<=100;j++){
					Process child = null;
					if (p_b_No.contains("8A.")) {
						System.out.println("XP8");
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

					} else if(p_b_No.contains("5SA.")) {
						System.out.println("XP5");
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
					}					
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String  value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting call.");

						Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
						test.log(LogStatus.PASS,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
						break;
					}
						else if(value.contains("00000000")){			
							continue;				
						}
						else {
							test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
						}
				}
			}catch(Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
				Thread.sleep(2000);
			}
		}
		
		public void endCall_RefDevice() {
			try {
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
				Thread.sleep(1000);
			} catch (Exception e) {
			}
		}
}
