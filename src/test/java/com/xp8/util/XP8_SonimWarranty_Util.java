package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.testng.asserts.SoftAssert;

public class XP8_SonimWarranty_Util extends BaseUtil{


	public boolean check = false;
	public SoftAssert softAssert;

	public void End() {
		aDriver.quit();
	}


	public void presence_Of_SonimWarrantyReg_In_ScoutApp() throws InterruptedException, IOException {

		APP_LOGS.info("**************************Starts******************************"); 
		//			clearApp();
		SoftAssert SA1=new SoftAssert();
		navigateToWarrantyRegApp();
		customWait(1000);
		try {
			clickBtn(Locators_Warranty_Reg.SWR_Support_Tab);
			APP_LOGS.info("Tapped on Support tab successfully");
			customWait(1000);	
			String fetch_sf_text=Locators_Warranty_Reg.SWR_String_Name.getText();
			APP_LOGS.info("Fetching safeguard string text is" +fetch_sf_text);
			SA1.assertEquals(fetch_sf_text, "Warranty Registration");
			SA1.assertAll();
		}
		catch(NoSuchElementException ex){
			ex.printStackTrace();
			clearRecentApps();
			APP_LOGS.info("Element is not present in the page");
		}
		APP_LOGS.info("***************************** Ends******************************"); 	
	}
	/*
		 public void navigateToDeviceInfoScreen() throws InterruptedException {
			 APP_LOGS.info("isDeviceReg:Checking wheather device is registered or not ******************************");  
			 aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
			 customWait(1000);
			 try {
			 clickBtn(Locators_Warranty_Reg.SWR_Support_Tab);
			 customWait(1000);
			 clickBtn(Locators_Warranty_Reg.SWR_String_Device_Information);
			 customWait(1000); 
			 }
			 catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("isDeviceReg: No Such element found exeception");
			}
		 }*/

	public void navigateToWarrantyRegApp() throws InterruptedException, IOException {
		APP_LOGS.info("*****************************Common Method to launch Sonim Warranty Reg******************************");  
		aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
		customWait(1000);
		try {
			clickBtn(Locators_Warranty_Reg.SWR_Support_Tab);
			customWait(1000);
			clickBtn(Locators_Warranty_Reg.SWR_String_Name);
			customWait(1000);
			if(isElementExist(Locators_Warranty_Reg.SWR_Contacting_server_progress_dialog)) {
				APP_LOGS.info(" launchWarrantyRegApp: Progress dialog displayed on FG,So Waiting for 5 seconds");
				customWait(6000);
				if(isElementExist(Locators_Warranty_Reg.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
					APP_LOGS.info("Unable to fetch registration information from server present on FG");
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				}		
			}
			else if (isElementExist(Locators_Warranty_Reg.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
				APP_LOGS.info("Unable to fetch registration information from server present on FG");
				clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				System.out.println("Execution has stopped as,Device is unable to fetch registration in information from server");
				aDriver.quit();
			}
			else if (isElementExist(Locators_Warranty_Reg.SWR_Please_contact_Sonim_At)) {
				customWait(1000);
				Runtime.getRuntime().exec("adb shell pm clear com.sonimtech.warrantyregapp");
				customWait(1000);
				//			      clearApp();
				aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
			}
			else if(isElementExist(Locators_Warranty_Reg.SWR_No_internet_Connection)) {
				clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				APP_LOGS.info("************No internet connection window display ***************");
				System.out.println("Execution has stopped as ,there is No internet connection available in the device!! ");
				aDriver.quit();
			}
		}
		catch (NoSuchElementException ex) {
			clearRecentApps();
			APP_LOGS.info("launchWarrantyRegApp:Element not found");
		}
	}


	public void presence_Of_Register_Option() throws InterruptedException, IOException {

		APP_LOGS.info("**********Start*******************************"); 
		//		 clearApp();
		SoftAssert SA2=new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				check=true;
				SA2.assertTrue(check, "Register");
				APP_LOGS.info("presence_Of_Register_Option:Register option is present in the device");
			}
			else {
				APP_LOGS.info("presence_Of_Register_Option:Register option is not present in the device");
				SA2.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("presence_Of_Register_Option:No Such Element exist");
		}
		SA2.assertAll();
		APP_LOGS.info("**********End*******************************"); 
	}

	//This method check whether device is registered or not
	public String isDeviceRegistered() throws InterruptedException, IOException {
		APP_LOGS.info("******************************start******************************"); 
		String status = null; 
		navigateToWarrantyRegApp();
		customWait(1000);
		try {
			if(isElementExist(Locators_Warranty_Reg.SWR_String_Register_Your_Device)) {
				APP_LOGS.info("isDeviceRegistered:Need to Registered the device");
				status=Locators_Warranty_Reg.SWR_String_Register_Your_Device.getText();	
				APP_LOGS.info("isDeviceRegistered :" +status);
			}
			else {
				APP_LOGS.info("isDeviceRegistered:Device is Already registered");
				status="Device is Already Registered";	
				APP_LOGS.info("isDeviceRegistered :"+status);
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("isDeviceRegistered:No Such Element found exeception");
		}
		APP_LOGS.info("TC isDevice Registered end******************************");
		return status;
	}


	// Validating submit button without entering any values in all the fields
	public void skip_AllFields_Press_SubmitBtn() throws InterruptedException, IOException {
		APP_LOGS.info("*******************Start*******************************");
		customWait(1000);	 
		SoftAssert SA3=new SoftAssert();		   
		if(isDeviceRegistered().equals("Register your device")) {
			try {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Reset);
				customWait(1000);				
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(2000);
				clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				customWait(2000);
				//Dont enter any value in the test field and press on "submit" button and check
				if(isElementExist(Locators_Warranty_Reg.SWR_Submit)) {
					APP_LOGS.info("Did not entered anything and press the Submit button");
					check=true;
					APP_LOGS.info("PASSED");
					SA3.assertTrue(check, "D");					 
				}
				else {
					SA3.fail();
					APP_LOGS.info("FAILED");
				}	 
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Without entering Any value press submit button:No such Element found execption");
			}
			catch (NullPointerException e) {
				e.printStackTrace();
				APP_LOGS.info("Without entering Any value press submit button:\"Null pointer exception\"");
			}
			SA3.assertAll();
		}
		else {
			APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			aDriver.quit(); 
		}
	}

	//Enters the country name 
	public void enter_Country_Name_Press_SubmitBtn() throws InterruptedException, IOException {		 
		SoftAssert SA4=new SoftAssert();
		if(isDeviceRegistered().equals("Register your device")) {		 
			try {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				//				 customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Country\"))");  
				customWait(1000);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Country, "India");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(2000);
				if(isElementExist(Locators_Warranty_Reg.SWR_please_enter_first_Name)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA4.assertTrue(check, "");
				}
				else {
					SA4.fail();
				}	 
			}
			catch(NoSuchElementException e) {
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				e.printStackTrace();
			}		 
			SA4.assertAll();		 
		} 
		else {
			APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			aDriver.quit(); 
		} 
	}

	//Enters the Second name 
	public void enter_Sname_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA6=new SoftAssert();			
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_Last_Name, "sonim");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(1000);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_Company_Name)) {
					APP_LOGS.info("Please enter the company name dialog dsiplayed ");
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA6.assertTrue(check, "");
				}
				else {				 
					SA6.fail();				 
				}
				SA6.assertAll();	 
			} 
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//Enters the First name 
	public void enter_Fname_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA5=new SoftAssert();			
		try {
			if(isDeviceRegistered().equals("Register your device")) {	 
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_First_Name, "sonim");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_Enter_Last_Name)) {
					APP_LOGS.info("Please enter the Last name dialog dsiplayed ");
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA5.assertTrue(check, "");
				}
				else {
					SA5.fail();
				}	 
				SA5.assertAll();
			}
			else {		 
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();				 
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}

	//Enters the Company name 
	public void enter_CompanyName_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA7=new SoftAssert();			
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_Company_Name, "Sonim Technology");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(1000);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_Address_1)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA7.assertTrue(check, "");
				}
				else {
					SA7.fail();
				}
				SA7.assertAll();
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//Enters the first Address name 
	public void enter_Address1_Press_SubmitBtn() throws InterruptedException, IOException {
		try {
			SoftAssert SA8=new SoftAssert();
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_Address_1, "J P Nagar,3rd phase");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);				 
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_City)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA8.assertTrue(check, "");
				}
				else {
					SA8.fail();
				}
				SA8.assertAll();
			}
			else {				 
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}	
	}

	//Enters the City name 
	public void enter_City_Press_SubmitBtn() throws InterruptedException, IOException {
		try {
			SoftAssert SA9=new SoftAssert();
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter city\"))");
				enterTextToInputField(Locators_Warranty_Reg.SWR_enter_City, "Banglore");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(1000);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_Enter_State)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA9.assertTrue(check, "");
				}
				else {
					SA9.fail();
				}
				SA9.assertAll();
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//Enters the State name 
	public void enter_StateName_PressSubmitBtn() throws InterruptedException, WebDriverException, IOException {
		SoftAssert SA10=new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter state name\"))");
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_State_Name, "Karnataka");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_zipcode)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA10.assertTrue(check, "");
				}
				else {
					SA10.fail();
				}
				SA10.assertAll();
			}
			else {	 
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}

	//Enters the Zipcode
	public void enter_ZipCode_PressSubmitbtn() throws InterruptedException, IOException {			
		SoftAssert SA11=new SoftAssert();			
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter zip code\"))");
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_zip_Code, "560078");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_emailid)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA11.assertTrue(check, "");
				}
				else {
					SA11.fail();
				}
				SA11.assertAll();					 
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}

	//Enters the Email id 
	public void enter_EmailId_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA12=new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter email id\"))");
				customWait(1000);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_Email_Id, "sonim1@gmail.com");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(2000);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_enter_phone_number)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA12.assertTrue(check, "");
				}
				else {
					SA12.fail();
				}
				SA12.assertAll(); 
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}				
	}

	//Enters the Phone Num
	public void enter_PhoneNum_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA13=new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter phone number\"))");
				customWait(1000);
				enterTextToInputField(Locators_Warranty_Reg.SWR_Enter_Phone_Number, "9989898989");
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(1000);
				if(isElementExist(Locators_Warranty_Reg.SWR_Please_Enter_purchase_date)) {
					check=true;
					clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
					SA13.assertTrue(check, "");
				}
				else {

					SA13.fail();
				}
				SA13.assertAll();
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//Enters the Purchase date
	public void enter_PurchaseDate_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA14 =new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Select Date\"))").click();
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_ChooseBtn);
			}		 
			else {
				aDriver.quit();	
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}

	//Enters the Dealer name 
	public void enter_DealerName_Press_SubmitBtn() throws WebDriverException, InterruptedException, IOException {
		SoftAssert SA15=new SoftAssert();
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Dealer Name\"))").sendKeys("Sonim Tech");
				customWait(1000);
			}
			else {
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void addInvoice() throws InterruptedException, WebDriverException, IOException {
		try {
			if(isDeviceRegistered().equals("Register your device")) {
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Now);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Choose a File\"))").click();;
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_Take_a_Pic);
				if(isElementExist(Locators_Warranty_Reg.SWR_AllowBtn)) {
				clickBtn(Locators_Warranty_Reg.SWR_AllowBtn);
				customWait(2000);
				}
				customWait(2000);
				clickBtn(Locators_Warranty_Reg.SWR_PhotoCapture);
				customWait(1000);
				clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);    
			}
			else {
				aDriver.quit();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}

	public void finalSubmit() {
		try {
			APP_LOGS.info("Here clicking the final submit button");
			clickBtn(Locators_Warranty_Reg.SWR_Submit);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}	

}
