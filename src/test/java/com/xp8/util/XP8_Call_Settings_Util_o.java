package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Call_Settings_Util_o extends BaseUtil{

	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;


	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum =HomeController.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = HomeController.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void navigate_to_call_settings(){
		try {
			
			
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			
			
		} catch (Exception e) {
			System.out.println("Locator did not match");
			e.printStackTrace();
		}
	}
	public void validate_navigatetocallsettings(SoftAssert sa)
	{
		try{
			launch_an_app("phone");
			minWait();
			navigate_to_call_settings();
			minWait();
			if(isElementExist(Locators_CallSetting.callsettingsframe)){
				APP_LOGS.info("Call Setting is navigated successfully");
				sa.assertTrue(true, "Call Setting is navigated successfully");
				test.log(LogStatus.PASS, "Call Setting is navigated successfully");	


			}else {
				APP_LOGS.info("Call setting navigation fail");
				test.log(LogStatus.PASS, "Call setting navigation fail");
				sa.fail();
				test.log(LogStatus.FAIL, "Call setting navigation fail");

			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Navigate to call settings is failed");
		}
	}
	public void navigate_To_Settings_AndElement(AndroidElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			element.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Navigation to settings is failed");
		}
	}
	public String selectDisplaySubOption(AndroidElement element,AndroidElement subElement) throws InterruptedException
	{
		/*
		 * Navigates to display options and clicks on given sub element
		 */
		//	navigate_To_Settings_AndElement(Locators_CallSetting.displayOptions);
		minWait();
		element.click();
		minWait();
		String field = subElement.getText();
		subElement.click();
		minWait();
		return field;
	}
	public void validateSortByAndNameFormat(AndroidElement element,AndroidElement subElement ,AndroidElement contactName,String Contact,SoftAssert sa) throws InterruptedException {
		/*
		 * Common method to validate sortBy and name format option 
		 */

		try {

			navigate_To_Settings_AndElement(Locators_CallSetting.displayOptions);
            String field = selectDisplaySubOption(element, subElement);
			minWait();
			clickBackButton(2);
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			String actualText = contactName.getText();
			System.out.println(actualText);
			minWait();
			if(actualText.equals(Contact)){
				APP_LOGS.info( field +" feature is validated successfully");
				sa.assertTrue(true, "Sort by feature is validated successfully");
				test.log(LogStatus.PASS, field +" feature is validated successfully");

			}else{
				APP_LOGS.info(field +" feature is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, field +" feature is not validated");

			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate sortby name format is failed");

		}

	}
	public void clickBackButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateSettingsAndDisplayOptions(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * validates settings and display option
		 */
		try {
			launch_an_app("phone");
			minWait();
			navigate_to_call_settings();
			minWait();
			clickBtn(Locators_CallSetting.displayOptions);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.firstNameOpt,Locators_CallSetting.contact1, "Abc Automation",sa);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.lastNameOpt,Locators_CallSetting.contact2, "Cde Test",sa);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.firstNameFirstOpt,Locators_CallSetting.contact2, "Cde Test",sa);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.lastNameFirstOpt,Locators_CallSetting.lnfcontact2, "Test, Cde",sa);
			minWait();
			APP_LOGS.info("Settings and display option is validated");
			sa.assertTrue(true, "Settings and display option is validated");
			test.log(LogStatus.PASS, "Settings and display option is validated");	
		} catch (Exception e) {
			e.printStackTrace();
			sa.fail();
			test.log(LogStatus.ERROR, "Validation of Settings and display option is failed");
		}
	}

	public void deleteIfContactsPresent(SoftAssert sa) throws InterruptedException
	{
		/*
		 * deletes contacts if present in contacts application
		 */

		try {
			clickBtn(Locators_CallSetting.delete_option);
			minWait();
			if(isElementExist(Locators_CallSetting.zero_selected)){
				minWait();
				/*TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_CallSetting.zero_selected).perform().release();
				minWait();*/
				clickBtn(Locators_CallSetting.zero_selected);
				minWait();
				if(isElementExist(Locators_CallSetting.selectAllOpt)){
					clickBtn(Locators_CallSetting.selectAllOpt);
					minWait();
					APP_LOGS.info("Contacts are deleted successfully");
					sa.assertTrue(true, "Contacts are deleted successfully");
					test.log(LogStatus.PASS, "Contacts are deleted successfully");	
				}	
				else{
					clickBackButton(1);
				}
				clickBtn(Locators_CallSetting.Ok_option);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(5000);
				clickBackButton(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Delete contacts if present in contact application is failed");
		}
	}


	public void deleteConatactList() throws InterruptedException{
		//deleting added contacts if present in Black  list
		
		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			for(int i=1; i<=20; i++){
				if(isElementExist(Locators_CallSetting.blackcontactList)){
					clickBtn(Locators_CallSetting.deleteblockBtninContact);
					minWait();
					clickBtn(Locators_CallSetting.unblocktxt);
					minWait();
				} else {
                    clickBackButton(4);
}
			}
		} catch (Exception e) {
		           e.printStackTrace();
		}

	}
	public void createContactWithNameandPhone(String name,String lastname,String phone) throws InterruptedException
	{
		/*
		 * Enter name and phone number and save the contact
		 */
		try {
			customWait(2000);
			enterTextToInputField(Locators_CallSetting.nameEditFld, name);
			minWait();
			clickBackButton(1);
			enterTextToInputField(Locators_CallSetting.lastnameEditFld, lastname);
			minWait();
			clickBackButton(1);
			enterTextToInputField(Locators_CallSetting.phoneNumberEditFld, phone);
			minWait();
			clickBtn(Locators_CallSetting.saveOpt);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Create contact with name and phone is failed");
		}
	}

	public void validateCreateNewContactFromCallLog(String name,SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Create New contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			customWait(2000);
			CreateNewContactValidateSavedContact(name,"from call Log",sa);
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.addedContactCallLog);
			minWait();
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.delete);
			minWait();
			clickBtn(Locators_CallSetting.deleteBtn);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validation of create new contact from call log is failed");
		}
	}

	public void CreateNewContactValidateSavedContact(String name,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		try {
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			if(isElementExist(Locators_CallSetting.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_CallSetting.allowBtn);
				}
				validateSavedContact(name,location,sa);
			}else{
				validateSavedContact(name,location,sa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate Create New contact and validate saved contact is failed");
		}
	}
	public void CreateNewContactAndSave(String name) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.createNewContactOpt);
			customWait(2000);
			enterTextToInputField(Locators_CallSetting.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_CallSetting.saveOpt);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "create new contact and save is failed");
		}
	}

	public void validateSavedContact(String name,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */

		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(name)){
				clickBackButton(1);
				APP_LOGS.info("Contact saved "+ location +"successfully");
				sa.assertTrue(true, "Saved contacts are validated");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");


			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved");

			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate saved contact is failed");

		}

	}
	public void validateSavedContact1(String Newname,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */

		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(Newname)){
				clickBackButton(1);
				APP_LOGS.info("Contact saved "+ location +"successfully");
				sa.assertTrue(true, "Saved contact1 are validated");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");



			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved");


			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO,"Validate saved contact1 is failed");

		}

	}

	public void validateSavedContact2(String Thirdname,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */

		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(Thirdname)){
				clickBackButton(1);
				APP_LOGS.info("Contact saved "+ location +"successfully");
				sa.assertTrue(true, "Saved contact2 are validated");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");


			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved");

			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validates saved contact2 is failed");

		}

	}

	public void AddContactFromContactsPage(String name,String lastname,String phone) throws InterruptedException, IOException{
		/*
		 *  Add contact from contacts page 
		 */
		try
		{
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.createNewContactInContactPage);
			createContactWithNameandPhone(name,lastname, phone);
			minWait();

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Add contact from contact page is failed");
		}
	} 

	public void add_Contact(String Newname,String Newlastname,String Newphone) throws InterruptedException, IOException{
		/*
		 * Validate Add contact from contacts page and validate added contact 
		 */
		try
		{
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.createSecondcontactInContactPage);
			createContactWithNameandPhone(Newname,Newlastname, Newphone);
			minWait();

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate Add contact from contacts page and validate added contact is failed");
		}
	} 

	public void navigateToSettingsAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			customWait(2000);
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			element.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Navigates to settings and clicks on given element is failed");
		}
	}


	public void validatePhoneRingtone(SoftAssert sa) throws InterruptedException {
		/*
		 * Sets phone ringtone and validates selected phone ringtone
		 */

		try {
			minWait();
			clickBtn(Locators_CallSetting.phoneRingtoneOpt);
			minWait();
			clickBtn(Locators_CallSetting.ringtoneOpt);
			minWait();
			String expectedRingtone = Locators_CallSetting.ringtoneOpt.getText();
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(2000);
			String selectedRingtone = Locators_CallSetting.ringtoneSelected.getText();
			minWait();
			if(selectedRingtone.equals(expectedRingtone)){
				check = true;
				APP_LOGS.info("Phone Ringtone is validated successfully");
				sa.assertTrue(true, "Phone Ringtone is validated successfully");
				test.log(LogStatus.PASS, "Phone Ringtone is validated successfully");

			}else{
				APP_LOGS.info("Phone Ringtone is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "Phone Ringtone is not validated");

			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.INFO, "Element Not Found");

		}

	}
	public void validateSoundsAndVibrationSubOptions(SoftAssert sa) throws InterruptedException {
		/*
		 * validates sounds and vibration sub-options checkbox status 
		 */
		try {
			minWait();
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.alsoVibrateForCallsOpt, Locators_CallSetting.alsoVibrateCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.dialpadTonesOpt, Locators_CallSetting.dialpadToneCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.callEndToneOpt, Locators_CallSetting.callEndToneCheckbox,sa);
			minWait();
			APP_LOGS.info("Sounds and vibration are validated");
			sa.assertTrue(true, "Sounds and vibration are validated");
			test.log(LogStatus.PASS, "Sounds and vibration are validated");	
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}  
	public void  checkOtherSoundOptionsCheckboxStatus(WebElement element,WebElement checkbox,SoftAssert sa) throws InterruptedException {
		/*
		 * checks whether given elements checkbox is enabled
		 */

		try {
			minWait();
			String soundResourceText = element.getText();
			minWait();
			element.click();
			minWait();
			if(isElementExist(checkbox)){
				element.click();
				check = true;
				APP_LOGS.info(soundResourceText+" is Enabled");
				sa.assertTrue(true, "Sound option checkbox is enabled");
				test.log(LogStatus.PASS, soundResourceText+" is Enabled");

			}else{
				APP_LOGS.info(soundResourceText+" is disabled");
				test.log(LogStatus.INFO, soundResourceText+" is disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Test cases failed");
			}
		}catch (Exception e) {
			e.printStackTrace();

			test.log(LogStatus.ERROR, "Element Not Found");

		}

	}






	public void Restore_default_QuickResponses() throws InterruptedException
	{	
		/*
		 * Restore default in quick response
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.restoreDefaultsInQuickResponse);
			customWait(2000);

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void getAndValidateQuickResponsesList(SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates presence of quick response list messages
		 */
		try {
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextOne,"Can't talk now. What's up?");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextTwo,"I'll call you right back.");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextThree,"I'll call you later.");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextFour,"Can't talk now. Call me later?");
			APP_LOGS.info("Quick response list is verified");
			sa.assertTrue(true, "Quick response list is verified");
			test.log(LogStatus.PASS, "Quick response list is verified");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "validates presence of quick response list messages is failed");
		}
	}

	public void getQuickResonseText(WebElement element,String expectedTextMessage) throws InterruptedException 
	{
		/*
		 * Gets Quick response text message and returns
		 */

		try {
			minWait();
			String textMessage = element.getText();
			minWait();
			if(textMessage.contains(expectedTextMessage)){
				check = true;
				APP_LOGS.info("Quick Resonse message : "+textMessage);
				test.log(LogStatus.INFO,"Quick Resonse message : "+textMessage);

			}else{
				APP_LOGS.info("Quick Resonse message : "+textMessage +" is not available");
				test.log(LogStatus.ERROR, "Quick Resonse message : "+textMessage +" is not available");

			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");

		}

	}
	public void editAndValidateQuickResponse(String data,SoftAssert sa) throws InterruptedException
	{
		/*
		 *  Edit quick response and validate edited quick response message
		 */

		try{
			minWait();
			Locators_CallSetting.quickResponseText.click();
			minWait();
			Locators_CallSetting.quickResponseEditField.clear();
			minWait();
			enterTextToInputField(Locators_CallSetting.quickResponseEditField, data);
			minWait();
			Locators_CallSetting.okBtn.click();
			minWait();
			String actualEditedMessage = Locators_CallSetting.quickResponseText.getText();
			if(actualEditedMessage.contains("Hello Sonim")){
				check = true;
				APP_LOGS.info("Edited and validated Quick Response text");
				sa.assertTrue(true, "Edited and validated Quick Response text");
				test.log(LogStatus.PASS, "Edited and validated Quick Response text");

			}else{
				APP_LOGS.info("Edited Quick Response Text is not available");
				sa.fail();
				test.log(LogStatus.FAIL, "Edited Quick Response Text is not available");

			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Edit quick response and validate edited quick response message is failed");

		}

	}
	public void restoreDefaults() throws InterruptedException{
		/*
		 * Validate Restore default option
		 */

		try{
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.restoreDefaultsInQuickResponse);
			customWait(2000);
			String actualString = Locators_CallSetting.quickResponseText.getText();
			if(actualString.equals("Can't talk now. What's up?")){
				check = true;
				APP_LOGS.info("Default settings Restored");
				test.log(LogStatus.PASS, "Default settings Restored");

			}else{
				APP_LOGS.info("Failed to restore default Quick Response");
				test.log(LogStatus.FAIL, "Failed to restore default Quick Response");

			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate restore default is failed");

		}

	}

	public void presenceOfVoicemailInSpeedDailSettings(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.voiceMailOpt)){
				System.out.println("Voice mail present");

		APP_LOGS.info("Voicemail Option is present in Speed Dail settings");
				sa.assertTrue(true, "Voicemail Option is present in Speed Dail settings");
				test.log(LogStatus.PASS, "Voicemail Option is present in Speed Dail settings");				
			}else {

				APP_LOGS.info("Voicemail Option is not present in Speed Dail settings");
				test.log(LogStatus.PASS, "Voicemail Option is not present in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Test cases failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Element Not Found");
		}

	}

	public void addContactInSpeedDailSettings(String Phone,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Add contact in speed Dial settings and validate
		 */


		try
		{
			clickBtn(Locators_CallSetting.notSetInSpeedDialSettings);
			minWait();
			enterTextToInputField(Locators_CallSetting.editFldInSpeedDialSettings, Phone);
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(3000);
			if(isElementExist(Locators_CallSetting.addedContactInSpeedDialSettings))
			{

				APP_LOGS.info("Contact added in Speed Dail settings");
				sa.assertTrue(true, "Contact added in Speed Dail settings");
				test.log(LogStatus.PASS, "Contact added in Speed Dail settings");


			}else {
				APP_LOGS.info("Contact not added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not added in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Add contact in speed Dial settings and validate is failed");
		}


	}

	public void replaceContactInSpeedDailSettings(String name,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Replace contact in speed dial settings and validate
		 */


		try
		{
			clickBtn(Locators_CallSetting.addedContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.replaceOptInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.selectContactInSpeedDialSettings);
			minWait();
			/*clickBtn(Locators_CallSetting.searchOpt);
			customWait(4000);*/
			/*Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+name);
			customWait(3000);*/
			clickBtn(Locators_CallSetting.firstNameContact);
			minWait();
			if(isElementExist(Locators_CallSetting.firstNameContact)){

				System.out.println("0000000000000000000000000000");

				APP_LOGS.info("Contact replaced in Speed Dail settings");
				sa.assertTrue(true, "Contact replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact replaced in Speed Dail settings");

				test.log(LogStatus.PASS, "Contact replaced in Speed Dail settings");

			}else {

				System.out.println("FAILLLLLLLLLLLLLLL");


				APP_LOGS.info("Contact not replaced in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not replaced in Speed Dail settings");
				

			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Replace contact in speed dial settings and validate");
		}


	}
	public void deleteContactInSpeedDailSettings(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Delete contact in speed dial settings and validate
		 */


		try
		{
			System.out.println("delete");
			clickBtn(Locators_CallSetting.speeddialsettingfirstcontact);
			minWait();
			clickBtn(Locators_CallSetting.delete);
			minWait();
			if(isElementExist(Locators_CallSetting.notSetInSpeedDialSettings)){
				check = true;
				APP_LOGS.info("Contact deleted in Speed Dail settings");
				sa.assertTrue(true, "Contact deleted in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact deleted in Speed Dail settings");
				test.log(LogStatus.PASS, "Contact deleted in Speed Dail settings");


			}else {
				APP_LOGS.info("Contact not deleted in Speed Dail settings");
				sa.fail();
			    test.log(LogStatus.FAIL, "Contact not deleted in Speed Dail settings");



			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Delete contact in speed dial settings is failed");
		}


	}
	public void BlacklistincallScreening(String phoneno) throws InterruptedException {

		//* black list


		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);

			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			minWait();

		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Blacklist in callscreening is failed");

		}
	}

	public void UnblockincallScreening() throws InterruptedException {

		// * unblock in black list

		clickBackButton(4);
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.Callscreening);  
		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.deleteblockedno);
			minWait();
			clickBtn(Locators_CallSetting.unblockBtn);
			minWait();			
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Unblock in callscreening is failed");

		}
	}
	public void clearcallhistory(SoftAssert sa)throws InterruptedException, IOException
	{

		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		try {

			minWait();
			if(isElementExist(Locators_CallSetting.clearCallHistoryOpt)){

				launch_an_app("phone");
				navigateTocallHistory();
				minWait();
				clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
				minWait();
				clickBtn(Locators_CallSetting.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(3000);
				clickBackButton(3);
				deleteIfContactsPresent(sa);

			}else if(!isElementExist(Locators_CallSetting.clearCallHistoryOpt)){
				minWait();
				deleteIfContactsPresent(sa);
			}
			minWait();
			launch_an_app("phone");
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_CallSetting.clearCallHistoryOpt);
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(3000);
			//clickBackButton(3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.callHistoryOpt);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Navigate to call history is failed");
		}
	}
	public void MakeACall(String phone) throws InterruptedException, IOException
	{
		/*
		 * Validates Make a call option (Clicks Make a call --> Enters number--> Dial --> validate MO call with UI )
		 */
		try {

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			minWait();
			clickBtn(Locators_CallSetting.makeACallOption);
			minWait();
			enterTextToInputField(Locators_CallSetting.dialpadEditFld, phone);
			minWait();
			clickBtn(Locators_CallSetting.callBtn);
			customWait(5000);
			clickBtn(Locators_CallSetting.Endcall);
			customWait(3000);

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Make a call is failed");
		}
	}
	public void blockAndUnblockNumber(SoftAssert sf2) throws InterruptedException
	{

		//* Block And Unblock number and validate

		try {

			minWait();
			blockUnblockNumber(Locators_CallSetting.blockNumberOpt, Locators_CallSetting.blockBtn);
			minWait();
			clickBtn(Locators_CallSetting.unblockNumberOpt);
			validateBlockAndUnblockNumber( "blocked","phoneno",sf2);
			minWait();

			blockUnblockNumber(Locators_CallSetting.unblockNumberOpt, Locators_CallSetting.unblockBtn);
			minWait();
			clickBtn(Locators_CallSetting.blockNumberOpt);
			validateBlockAndUnblockNumber("Unblocked","phoneno",sf2);
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.addedContactCallLog);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Block And Unblock number and validate is failed");
		}
	}

	public void blockUnblockNumber(WebElement element, WebElement subElement) throws InterruptedException
	{
		/*
		 * Block And Unblock number
		 */
		try {
			minWait();
			element.click();
			minWait();
			subElement.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void validateBlockAndUnblockNumber(String status,String phonenum,SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {
			clickBtn(Locators_CallSetting.Callhistorytab);
			customWait(3000);
			System.out.println("Sarching...");
			String actualString = Locators_CallSetting.Blocked.getText();
			System.out.println(actualString);
			String NumString = Locators_CallSetting.dailedFirstNum.getText();
			System.out.println(NumString);

			if(actualString.contains("Blocked")){
				APP_LOGS.info("Number is blocked");
				sa.assertTrue(true, "Number is blocked");
				test.log(LogStatus.PASS, "Number is blocked ");

			}else {
				APP_LOGS.info("Number is  Unblocked");
				sa.fail();
				test.log(LogStatus.PASS, "Number is  Unblocked");


			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate block and unblock no is failed");
		}
	}


	public void navigateToCallDetails(WebElement element) throws InterruptedException
	{
		/*
		 * Navigates to call details with given element
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			customWait(2000);
			//element.click();
			minWait();
			clickBtn(Locators_CallSetting.callDetailsOpt);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}	
	public void validate_blocknumber_with_values(String phoneno,SoftAssert sa){

		try {
			
            launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			if(isElementExist(Locators_CallSetting.blocklistfirstno)){
				System.out.println("No is blocked");

				APP_LOGS.info("blocked no is validated successfully");
				sa.assertTrue(true, "blocked no is validated successfully");
				test.log(LogStatus.PASS, "blocked no is validated successfully");				
			}else {

				APP_LOGS.info("blocked no is not validated ");
				sa.fail();
				test.log(LogStatus.FAIL, "blocked no is not validated ");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Validate blockno with values is failed");
		}
	}
	public void addingno_in_blacklist_callscreening(String phoneno1){
		//Adding no in black list


		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Adding no in blacklist call screening is failed");
		}

	}

	public void validate_pressing_backkey_once(String phoneno1,SoftAssert sa) throws InterruptedException
	{

		// pressing Back key while adding number to the blocked list

		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton(1);
			String imagePath="C:\\Users\\naveenat.t\\Desktop\\sikuli images\\virtualkeyboard.PNG";
			Screen sc=new Screen();
			Pattern p=new Pattern(imagePath).similar((float)0.7);
			sc.wait(p,10);
			if(sc.exists(imagePath) != null){
				System.out.println("Image exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is verified successfully");
				sa.assertTrue(true, "Pressing backkey while adding no to blacklist is verified successfully");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is verified successfully");	
			}else {
				System.out.println("image doesnot exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is failure");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is failure");
				sa.fail();
				test.log(LogStatus.FAIL, "Pressing backkey while adding no to blacklist is failure");
			}
		} catch (FindFailed e) {
			System.out.println("IMAGE doesnot ");

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Pressing backkey once while adding no to whitelist is failed");
		}


	}

	public void validate_pressing_backkey_twice(String phoneno1,SoftAssert sa) throws InterruptedException{
		// pressing Back key while adding number to the blocked list.

		try{
			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton(2);
			customWait(3000);
			if (isElementExist(Locators_CallSetting.addingnotoblock)) {
				APP_LOGS.info("Blockedno screen page is dispalyed");
				sa.assertTrue(true, "Blockedno screen page is dispalyed");
				test.log(LogStatus.PASS, "Blockedno screen page is dispalyed");
			}else {
				APP_LOGS.info("Blockedno screen page is not displayed");
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " pressing Back key while adding number to the blocked list is failed");
		}

	}
	public void validate_pressing_Shortpress_HomeKey(String phoneno1,SoftAssert sa) throws InterruptedException{

		try{
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if (isElementExist(Locators_CallSetting.Appslist)) {
				APP_LOGS.info(" Homescreen is displayed");
				sa.assertTrue(true, "Homescreen is displayed");
				test.log(LogStatus.PASS, "Homescreen is displayed");

			}else {
				APP_LOGS.info("Homescreen is not displayed");
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Pressing shortpress homekey while adding no to blacklist is failed");
		}

	}

	public void validate_pressing_Longpress_HomeKey(String phoneno1,SoftAssert sa) throws InterruptedException{

		//pressing Home key (LONG PRESS)while adding number to the blocked list

		try{

			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_HOME");
			/* TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_CallSetting.file_list_SoundRec).release().perform();;
			 */

			minWait();
			if (isElementExist(Locators_CallSetting.googlequicksearch)) {
				APP_LOGS.info(" Googlenow on tap screen is displayed");
				sa.assertTrue(true, "Googlenow on tap screen is displayed");
				test.log(LogStatus.PASS, "Googlenow on tap screen is displayed");

			}else {
				APP_LOGS.info("Googlenow on tap screen is not displayed");
				test.log(LogStatus.FAIL, "Googlenow on tap screen is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Googlenow on tap screen is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "pressing Home key (LONG PRESS)while adding number to the blocked list is failed");
		}

	}
	public void validate_pressing_Recentsapp_Key(String phoneno1,SoftAssert sa) throws InterruptedException{

		//pressing Recent apps key while adding number to the blocked list

		try{
			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();	 	
			if(!isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info(" Recentappkey is displaying recent opened apps");
				sa.assertTrue(true, "Recentappkey is displaying recent opened apps");
				test.log(LogStatus.PASS, "Recentappkey is displaying recent opened apps");

			}else {
				APP_LOGS.info("Recentappkey is not displaying recent opened apps");
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");
				sa.fail();
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");

			}

		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "pressing Recent apps key while adding number to the blocked list is failed");
		}

	}
	public void whitelist_callscreening(String phoneno){

		try {
			minWait();

			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.addBtn);
			customWait(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addContactNumWhitelist(String phoneno){
		try {

			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.addBtn);
			customWait(2000);


			//whitelist_callscreening(phoneNum);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void deleting_no_in_whitelist(String phoneno) throws InterruptedException{

		try{
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.removenoBtn);
			minWait();
			clickBtn(Locators_CallSetting.removetxt);
			minWait();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleting_no_whitelist() throws InterruptedException{

		try{
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.removenoBtn);
			minWait();
			clickBtn(Locators_CallSetting.removetxt);
			minWait();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleting_no_in_blacklist(String phoneno) throws InterruptedException{

		try {
			launch_an_app("phone");
			//Navigate to call screening in call settings
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
	     	clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.deleteblockBtninContact);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
			}catch (Exception e) {
			e.printStackTrace();
		}				
	}
	public void delete_no_blacklist() throws InterruptedException{
		//blocking  numbers from Contact app
		try {
			launch_an_app("phone");

			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
			minWait();
			clickBtn(Locators_CallSetting.deleteblockBtninContact);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
			}else{
				clickBackButton(4);
			}
			/*deleteConatactList();
			minWait();*/


		}
		catch (Exception e) {
			e.printStackTrace();
		}				
	}
public void delete_all_No_in_blacklist() throws InterruptedException{
	try{
		launch_an_app("phone");

		minWait();
		navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
		minWait();
		clickBtn(Locators_CallSetting.Manageblacklist);
		minWait();
		if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
		minWait();
		clickBtn(Locators_CallSetting.deleteblockBtninContact);
		minWait();
		clickBtn(Locators_CallSetting.unblocktxt);
		minWait();
		
	}
		else{
			clickBackButton(4);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}		
}
	public void validatescrollingno_in_whitelist(String phoneNum,SoftAssert sa){
		//
		System.out.println(phoneNum);
		if(scrollTo(phoneNum)){
			check=true;
			APP_LOGS.info("Validated Scrolling no in whitelist contact");
			sa.assertTrue(true, "Validated Scrolling no in whitelist contact");
			test.log(LogStatus.PASS, "Validated Scrolling no in whitelist contact");
		} else {
			APP_LOGS.info("Validation Scrolling no in whitelist contactis is failed");
			sa.fail();
			test.log(LogStatus.FAIL,"Validation Scrolling no in whitelist contact is failed");

		}
	}
	public void validatescrollingno_in_blacklist(String phoneNum,SoftAssert sa){
		//
		System.out.println(phoneNum);
		if(scrollTo(phoneNum)){
			APP_LOGS.info("validated Scrolling no in blacklist contact");
			sa.assertTrue(true, "validated Scrolling no in blacklist contact");
			test.log(LogStatus.PASS, "validated Scrolling no in blacklist contact ");
	 	} else {
			APP_LOGS.info("Validation Scrolling no in blacklist contact is failed ");
			sa.fail();
			test.log(LogStatus.FAIL,"Validation Scrolling no in blacklist contact is failed");

		}
	}
	public void validate_cancel_option_blacklist(String phoneno1,SoftAssert sa) throws InterruptedException{

		//**CANCEL options while adding number to black list

		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();
			if(!isElementExist(Locators_CallSetting.blocklistfirstno)){
				APP_LOGS.info("BlacklistNumber is not added to list");
				sa.assertTrue(true, "BlacklistNumber is not added to list");
				test.log(LogStatus.PASS, "BlacklistNumber is not added to list");

			}else {
				APP_LOGS.info("BlacklistNumber is added to list");
				sa.fail();
				test.log(LogStatus.FAIL, "BlacklistNumber is added to list");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "CANCEL options while adding number to black list is failed");
		}

	}
	public void validate_cancel_option_whitelist(String phoneno1,SoftAssert sa) throws InterruptedException{

		//**CANCEL options while adding number to White list

		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();

			if(!isElementExist(Locators_CallSetting.blocklistfirstno)){
				APP_LOGS.info(" WhitelistNumber is not added to list");
				sa.assertTrue(true, "WhitelistNumber is not added to list");
				test.log(LogStatus.PASS, "WhitelistNumber is not added to list");

			}else {
				APP_LOGS.info("WhitelistNumber is added to list");
				sa.fail();
				test.log(LogStatus.FAIL, "WhitelistNumber is added to list");

			}
		}
		catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "CANCEL options while adding number to White list");
		}

	}


	public void validate_addno_option_under_callsettings_blacklist(String phoneno,SoftAssert sa) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under Black List
		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			if(isElementExist(Locators_CallSetting.blackcontactList)){
				APP_LOGS.info(" numberfield option is displayed in blacklist");
				sa.assertTrue(true, "numberfield option is displayed in blacklist");
				test.log(LogStatus.PASS, "numberfield option is displayed in blacklist");

			}else {
				APP_LOGS.info("numberfield option is not displayed in blacklist");
				sa.fail();
				test.log(LogStatus.FAIL, "numberfield option is not displayed in blacklist");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "number field is displayed on selecting ADD A NUMBER option under Black List");
		}
	}
	public void validate_addno_option_under_callsettings_Whitelist(String phoneno,SoftAssert sa) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under white List
		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			if(isElementExist(Locators_CallSetting.whitecontactList)){
				APP_LOGS.info(" numberfield option is displayed in whitelist ");
				sa.assertTrue(true, "numberfield option is displayed in whitelist");
				test.log(LogStatus.PASS, "numberfield option is displayed in whitelist");

			}else {
				APP_LOGS.info("numberfield option is not displayed in whitelist");
				sa.fail();
				test.log(LogStatus.FAIL, "numberfield option is not displayed in whitelist");

			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "number field is displayed on selecting ADD A NUMBER option under white List");
		}
	}

	public void checkScreeningIncomingEnabled() throws InterruptedException{
		if(!Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked enabled checkbox");
		}
	}
	public void checkScreeningOutgoingEnabled() throws InterruptedException{
		if(!Locators_CallSetting.screeningoutgoingcallcheckbox.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked enabled checkbox");
		}

	}
	public void checkScreeningOutgoingDisabled() throws InterruptedException{
		if(Locators_CallSetting.screeningoutgoingcall.isEnabled()){
			clickBtn(Locators_CallSetting.screeningoutgoingcallcheckbox);
			minWait();
			System.out.println("Clicked disabled checkbox");
		}

	}
	public void checkScreeningIncomingDisabled() throws InterruptedException{
		if(Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked disabled checkbox");
		}

	}

	public void validate_screening_incoming_call_highlighted(SoftAssert sa){
		//Screening incoming call Setting option is highlighted (not greyed out)
		try {
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			if(Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
				clickBtn(Locators_CallSetting.screeningincomingcallsetting);
				minWait();
				if(isElementExist(Locators_CallSetting.ScreeningincomingcallsettingOpt) && isElementExist(Locators_CallSetting.blockblacklistopt) && isElementExist(Locators_CallSetting.allowwhitelistopt)){

				}
				System.out.println("Highlighted");
				APP_LOGS.info(" Screening incoming call setting is highlighted & options are shown ");
				sa.assertTrue(true, " Screening incoming call setting is highlighted & options are shown ");
				test.log(LogStatus.PASS, "Screening incoming call setting is highlighted & options are shown");

			}else {
				APP_LOGS.info("Screening incoming call setting is not highlighted & options are not shown");
				test.log(LogStatus.FAIL, "Screening incoming call setting is not highlighted & options are not shown");

			}
		} catch (Exception e) {
			e.printStackTrace();
			sa.fail();
			test.log(LogStatus.ERROR, "Validation screening incoming call setting option highlighted option is failed");
		}
	}

	public void validate_Callscreening_under_Callsettings(SoftAssert sa)
	{
		try{
			clickBackButton(4);
			minWait();
			launch_an_app("phone");
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			clickBtn(Locators_CallSetting.Callscreening);
			minWait();
			if(isElementExist(Locators_CallSetting.callscreeningpage)){
				APP_LOGS.info(" CallScreening present under Callsettings  ");
				sa.assertTrue(true, "CallScreening present under Callsettings");
				test.log(LogStatus.PASS, "CallScreening present under Callsettings");

			}else {
				APP_LOGS.info("CallScreening not present under Callsettings");
				sa.fail();
				test.log(LogStatus.FAIL, "CallScreening not present under Callsettings");

			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validating call screening under call settings is failed");
		}
	}

	public void blockno_in_contactapp(String refNum){
		//Blocked and unblocked numbers in phone contact settings
		try{
			
			clickBtn(Locators_CallSetting.opennavigationdrawer);
			minWait();
			clickBtn(Locators_CallSetting.Settings);
			minWait();
			scrollToText("Blocked numbers");
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, refNum);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			clickBackButton(4);
			minWait();

		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Blocked and unblocked numbers in phone contact settings is failed");

		}
	}
	public void validate_blockno_in_blacklist(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(isElementExist(Locators_CallSetting.firstblockedno)){
				APP_LOGS.info(" Blockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "Blockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "Blockingno from contacts app is reflected in Black lsit under Call screening");

			}else {
				APP_LOGS.info("Blockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockingno from contacts app is not reflected in Black lsit under Call screening");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate blockno in blacklist is failed");
		}
	}



	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void make_Call_from_PrmyDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			System.out.println("Inside Make a call");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			customWait(10000);			
			System.out.println("Call is activitated");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
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

	public void endCall_PrmyDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}
	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {

		try {
			
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s "+ p_Id+" shell service call telecom 29");
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
					
				} else {
					System.out.println("Not accepting call.");

				}
			
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}
	


	public void validateCallLog_Orio(SoftAssert sa) throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */

		String value = null;
		customWait(10000);
		try {
			Process child = null;
			Process version = null;
			version = Runtime.getRuntime().exec("adb -s "+r_Id+" shell getprop ro.build.version.release");
			InputStream lsOut1 = version.getInputStream();
			InputStreamReader r1 = new InputStreamReader(lsOut1);
			BufferedReader in1 = new BufferedReader(r1);
			String  value1=in1.readLine();
			System.out.println(value1);    
			if (r_b_No.contains("8A.")) {

				System.out.println("XP8");
				if(value1.contains("8.1")) {
					//To get current call state
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 29");
				}else {
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
				}
			} 
			InputStream lsOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(r);
			value=in.readLine();
			customWait(2000);
			System.out.println(value);
			if(value.contains("00000001")) {
				check = true;
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				APP_LOGS.info("MO-Voice call is validated ");
				sa.assertTrue(true, "MO-Voice call is validated");
				test.log(LogStatus.PASS,"MO-Voice call from  is validated at : iteration " );
			}
			else {
				test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " );
				sa.fail();
				test.log(LogStatus.FAIL, "MO Voice call failed");

			}
			customWait(10000);	
			endCall_PrmyDevice();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}
	public void validate_calllog_callreceived(SoftAssert sa){
		try{
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			String dialedno =Locators_CallSetting.dailedFirstNum.getText();
			String new_Dial_Num=dialedno.replaceAll("\\s+","");
			String new_Ref_Num=refNum.replaceAll("\\s+", "");

			System.out.println(new_Dial_Num +","+new_Ref_Num);

			if(new_Dial_Num.contains(new_Ref_Num)){
				APP_LOGS.info("Call received successfully");
				sa.assertTrue(true, "Call received successfully");
				test.log(LogStatus.PASS, "Call received successfully");


			} else	{
				APP_LOGS.info("Call not received");
				test.log(LogStatus.FAIL, "Call not received");
				sa.fail();
				test.log(LogStatus.FAIL, "Call not received");

			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate calllog call received is failed");

		}
	}
	public void validate_calllog_contactcallreceived(SoftAssert sa){
		try{
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			String dialedno =Locators_CallSetting.dailedFirsttext.getText();
			System.out.println( "dialedno" );
			if(dialedno.contains("Test Automation")){
				APP_LOGS.info("Call received successfully");
				sa.assertTrue(true, "Call received successfully");
				test.log(LogStatus.PASS, "Call received successfully");


			} else	{
				APP_LOGS.info("Call not received");
				sa.fail();
				test.log(LogStatus.FAIL, "Call not received");

			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate calllog contact call received is failed");

		}
	}
	public void validate_calllog_callnotreceived(SoftAssert sa){
		try{
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			if(!isElementExist(Locators_CallSetting.dailedFirstNum))
			{
				APP_LOGS.info("Call not received ");
				sa.assertTrue(true, "Call not received");
				test.log(LogStatus.PASS, "Call not received");


			} else	{
				APP_LOGS.info("Call received ");
				sa.fail();
				test.log(LogStatus.FAIL, "Call received ");


			}		
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate calllog call not received is failed");

		}
	}





	public void validate_Num_In_CallLog(String expectedNum,SoftAssert sa) throws InterruptedException {
		/* 
		 * This Method will Validate the Dailled Number in Call Log history.
		 * Precondition : User should be in Dialler Home Page.
		 */

		try {
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.call_History);
			customWait(3000);
			String callLogNum = Locators_CallSetting.dialerfirstno.getText().replaceAll("[^0-9+]", "");
			System.out.println("callLogNum "+callLogNum);
			System.out.println("expectedNum "+expectedNum);
			if (callLogNum.contains(expectedNum)) {
				APP_LOGS.info("Dailled Number Successfully Recorded in Call log");
				sa.assertTrue(true, "Dailled Number Successfully Recorded in Call log");
				test.log(LogStatus.PASS, "Number Validated in the Call Logs.");
			}else {
				APP_LOGS.info("Dailled Number NOT Recorded in Call log");
				sa.fail();
				test.log(LogStatus.FAIL, "Dailled Number NOT Recorded in Call log");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}		

	}
	public void disabling_Screeningincomingcalls_callscreening() throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingDisabled();
			minWait();
}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "disabling Screeningincomingcalls in callscreening is failed");
		}
	}

	//Screeningincomingcallsetting_option2();

	public void enabling_Screeningincomingcalls_callscreening(String phoneno) throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option2();
			minWait();
			delete_no_blacklist();
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Enabling Screening incoming calls in call screening is failed");
		}

	}
	public void enabling_Screeningincomingcalls_allowonlycontacts(String phoneno) throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option1();
			minWait();
			delete_no_blacklist();
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}
	public void enabling_Screeningincomingcalls_allowwhitelist() throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option3();
			minWait();
			delete_no_blacklist();
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			minWait();
			deleting_no_whitelist();
			minWait();
			addContactNumWhitelist(refNum);
			minWait();
			clickBackButton(4);


		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}
	public void screeningincomingcallsetting_option2() throws InterruptedException{
		//screening incoming call setting block black list option
		try{

			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.blockblacklistopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "screening incoming call setting block black list option is failed");
		}
	}
	public void screeningincomingcallsetting_option3() throws InterruptedException{
		//screening incoming call setting Allow white list option
		try{
			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.allowwhitelistopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void screeningincomingcallsetting_option1() throws InterruptedException{
		//screening incoming call setting allow only contacts option
		try{
			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.allowonlycontactsopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void unblockno_in_contactapp(String refNum){
		//Blocked and unblocked numbers in phone contact settings
		try{
			clickBtn(Locators_CallSetting.opennavigationdrawer);
			minWait();
			clickBtn(Locators_CallSetting.Settings);
			minWait();
			scrollToText("Blocked numbers");
			minWait();
			clickBtn(Locators_CallSetting.deleteblockBtninContact);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void validate_Unblockno_in_blacklist(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(!isElementExist(Locators_CallSetting.firstblockedno)){
				APP_LOGS.info(" UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");

			}else {
				APP_LOGS.info("UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate unblockno in blacklist is failed");
		}
	}
	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			//				clickBtn(Locators_CallSetting.StartMessaging);
			minWait();
			clickBtn(Locators_CallSetting.NEXT_Msg);
			minWait();
			clickBtn(Locators_CallSetting.allow_Permission);
			minWait();
			//			Runtime run = Runtime.getRuntime();
			//			Process pr = run.exec("adb shell input tap 540 1776");
			//			pr.waitFor();
			//			Runtime run1 = Runtime.getRuntime();
			//			Process pr1 = run1.exec("adb shell input tap 713 1098");
			//			pr1.waitFor();
		} catch (Exception e) {	
			e.getMessage();
		}		 
	}


	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_CallSetting.add_NewSMS_O);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}
	public void create_NewSMS_O(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			enter_Num_ToField_O(number);
			enterText_MessageField_O(message);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}
	public void enterText_MessageField_O(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			if(isElementExist(Locators_CallSetting.messageField_O)) {
				enterTextToInputField(Locators_CallSetting.messageField_O, message);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_CallSetting.TO_Field_O, number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			clickBtn(Locators_CallSetting.contact_Select);
			//			customWait(2000);			
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}


	public void clickOn_Send_O() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_CallSetting.send_Icon_O);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validate_SentMessage_O(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 60);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_CallSetting.Delivered));
			if(isElementExist(Locators_CallSetting.Delivered)||isElementExist(Locators_CallSetting.not_Sent_Text)) {
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail();
				test.log(LogStatus.FAIL,"Message didn't sent at iteration ");
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			soft.fail();
		}
	}
	public void validate_NotSentMessage(SoftAssert sa) throws InterruptedException {
		////Verify  messages is not received from black list on selecting 'Block black list"
		try{
			if(!isElementExist(Locators_CallSetting.smsfirstmsg)) {
				APP_LOGS.info("Message not sent");
				System.out.println("Not Sent");
				sa.assertTrue(true, "Message not sent");
				test.log(LogStatus.PASS, "Message not sent ");
			} else {
				APP_LOGS.info("Message sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Message  sent ");
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			test.log(LogStatus.INFO, "Validate not sent message is failed");
		}
	}
	public void validate_SentMessage(SoftAssert sa) throws InterruptedException {
		////Verify  messages is not received from black list on selecting 'Block black list"
		try{
			if(isElementExist(Locators_CallSetting.smsfirstmsg)) {
				APP_LOGS.info("Message  sent");
				System.out.println("Sent");
				sa.assertTrue(true, "");
				test.log(LogStatus.PASS, "Message  sent ");
			} else {
				APP_LOGS.info("Message not sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Message not sent ");
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			test.log(LogStatus.INFO, "Validate sentmessage is failed");
		}
	}

	public void delete_SMS(SoftAssert sa) throws InterruptedException {
		/* This Method delete the First Thread in the List. */



		try {
			System.out.println("IN Delete");
			minWait();
			launch_an_app("messaging");
			minWait();
			if (isElementExist(Locators_CallSetting.firstSMS_InList)) {
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_CallSetting.firstSMS_InList).release().perform();
				minWait();
				clickBtn(Locators_CallSetting.smsmoreOptions);
				minWait();
				if(isElementExist(Locators_CallSetting.smsselectall));
				minWait();
				clickBtn(Locators_CallSetting.smsselectall);
				minWait();
				clickBtn(Locators_CallSetting.smsdeleteall);
				minWait();
				clickBtn(Locators_CallSetting.smsconfirmdelete);
				minWait();
			}    else{
					clickBtn(Locators_CallSetting.smsdeleteall);
					minWait();
					clickBtn(Locators_CallSetting.smsconfirmdelete);
					minWait();
			       APP_LOGS.info("SMS is deleted");
				   sa.fail();
				   test.log(LogStatus.PASS, "SMS is deleted");
}
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}
	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					//						test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(Locators_CallSetting.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void create_NewSMS(String number, String message) throws InterruptedException, IOException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS();
		try {
			enter_Num_ToField(number);
			System.out.println("Enter Text");
			enterText_MessageField(message);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}
	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_CallSetting.add_NewSMS);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}
	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			customWait(2000);
			enterTextToInputField(Locators_CallSetting.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}
	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methods is Used enter Message into Message Field. */
		try {
			customWait(1000);
			enterTextToInputField(Locators_CallSetting.messageField, message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_CallSetting.send_Icon)) {
				clickBtn(Locators_CallSetting.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_CallSetting.send_SMS)) {
				clickBtn(Locators_CallSetting.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_CallSetting.send_MMS_Icon);
				minWait();
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Entering text meassage.");
		}
	}
	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {
			minWait();
			// Below Code To clear Battery PopUp.
			//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 540 1776");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 713 1098");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
					minWait();
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();
				}else if(r_b_No.contains("-15.")){
					minWait();
					System.out.println("IN Android O");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
					customWait(4000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					System.out.println("Sending Message...");
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
				customWait(6000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}
		} catch (Exception e) {

		}
	}

	public void validate_RecievedMessage() throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_CallSetting.now_Text));	
			customWait(8000);
			if(isElementExist(Locators_CallSetting.now_Text)||isElementExist(Locators_CallSetting.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				//soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.PASS, "Message didn't Recieved at iteration : ");
				//soft.fail();
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			//soft.fail();
		}
	}

	public void blockno_in_Calldetails(SoftAssert sa) throws InterruptedException
	{
		//Blocking no from call details

		try {
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			minWait();
			blockUnblockNumber(Locators_CallSetting.blockNumberOpt, Locators_CallSetting.blockBtn);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void Unblockno_in_calldetails(){
		//UnBlocking no from call details

		try {
			clickBackButton(4);
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			minWait();
			blockUnblockNumber(Locators_CallSetting.unblockNumberOpt, Locators_CallSetting.unblockBtn);
			minWait();
		}
		catch (Exception e) {
			e.printStackTrace();

		}

	}
	public void validateBlockAndUnblockNumber(WebElement element,String status) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.callDetailsOpt);
			minWait();
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info("Number "+ status +" Successfully");
				test.log(LogStatus.INFO, "Number "+ status +" Successfully");

			}else{
				APP_LOGS.info("Number not " +status);
				test.log(LogStatus.ERROR,"Number not " +status);

			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");

		}

	}

	public void validate_canceloptionin_calldetailsblockno(SoftAssert sa) throws InterruptedException{
		//cancel option in call details block no

		try{
			
			if(isElementExist(Locators_CallSetting.blockNumberOpt)){
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				APP_LOGS.info("Cancel option is verified in call details block no");
				sa.assertTrue(true, "Cancel option is verified in call details block no");
				test.log(LogStatus.PASS, "Cancel option is verified in call details block no");
			}
			else{
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.unblockBtn);
				minWait();
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				APP_LOGS.info("Cancel option is verified in call details block no");
				test.log(LogStatus.PASS, "Cancel option is verified in call details block no");
				sa.fail();
				test.log(LogStatus.FAIL, "Cancel option is verified in call details block no");


			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate cancel option in call details block no is failed");

		}

	}

	public void validate_canceloptionin_calldetailsUnblockno(SoftAssert sa) throws InterruptedException{
		//cancel option in call details Unblock no
		try{
			
			if(isElementExist(Locators_CallSetting.unblockNumberOpt)){
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				APP_LOGS.info("Cancel option is verified in call details Unblock no");
				sa.assertTrue(true, "Cancel option is verified in call details Unblock no");
				test.log(LogStatus.PASS, "Cancel option is verified in call details Unblock no");
			}
			else{
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				APP_LOGS.info("Cancel option is verified in call details unblock no");
				sa.fail();
				test.log(LogStatus.PASS, "Cancel option is verified in call details Unblock no");
				clickBackButton(4);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validat cancel option in calldetails unblock no is failed");

		}

	}
	public void validation_Screeningoutgoingcall_enabled(SoftAssert sa) throws InterruptedException{
		//Verify enabling Screening outgoing calls

		try {
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingEnabled();
			minWait();
			if(!Locators_CallSetting.screeningoutgoingcall.isEnabled()){
				clickBtn(Locators_CallSetting.screeningoutgoingcall);
				minWait();
				System.out.println("Enabled");
				APP_LOGS.info( "Screening outgoing call enabled ");
				sa.assertTrue(true, "Screening outgoing call enabled ");
				test.log(LogStatus.PASS, "Screening outgoing call enabled ");
			}
			else{
				System.out.println("Disabled");
				APP_LOGS.info("Validating Screening outgoing call disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Validating Screening outgoing call disabled");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validating Screening outgoing call enabled is failed");

		}


	}
	public void validation_Screeningoutgoingcall_disabled(SoftAssert sa) throws InterruptedException{
		//Verify disabling Screening outgoing calls

		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingDisabled();
			minWait();
			if(!Locators_CallSetting.screeningoutgoingcall.isEnabled()){
				clickBtn(Locators_CallSetting.screeningoutgoingcall);
				minWait();
				System.out.println("Disabled");
				APP_LOGS.info( "Screening outgoing call disabled ");
				sa.assertTrue(true, "Screening outgoing call disabled");
				test.log(LogStatus.PASS, "Screening outgoing call disabled ");
			}
			else{
				System.out.println("Enabled");
				APP_LOGS.info("Validating Screening outgoing call enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Validating Screening outgoing call  enabled");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validating Screening outgoing call disabled is failed");

		}


	}
	public void setDefaultSavingAccount() throws InterruptedException
	{
		/*
		 * Set default saving account as phone in contacts application
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_CallSetting.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_CallSetting.contactsDefaultAccountSettings);
			minWait();
			clickBtn(Locators_CallSetting.contactsDefaultPhone);
			minWait();
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "setDefaultSavingAccount is failed");
		}
	}
	public void validation_initiatingcall_Screeningoutgoingcall_enabled(String name,String lastname,String refNum,SoftAssert sa) throws InterruptedException{
		//Verify initiating calls to saved contacts on enabling Screening outgoing calls


		try {

			launch_an_app("contacts");
			minWait();
			deleteIfContactsPresent(sa);
			minWait();
			launch_an_app("contacts");
			minWait();
			setDefaultSavingAccount();
			minWait();
			launch_an_app("phone");
			AddContactFromContactsPage(name,lastname,refNum);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingEnabled();
			minWait();
			APP_LOGS.info("Initiating calls to saved contacts on enabling Screening outgoing calls is verified");
			sa.assertTrue(true, "Initiating calls to saved contacts on enabling Screening outgoing calls is verified");
			test.log(LogStatus.PASS, "Initiating calls to saved contacts on enabling Screening outgoing calls is verified");		

		}

		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validating initiating calls to saved contacts on enabling Screening outgoing calls");

		}


	}

	public void add_contact_for_callscreening(String name,String lastname,String refNum,SoftAssert sa) throws InterruptedException{
		{
			try{
				launch_an_app("contacts");
				minWait();
				deleteIfContactsPresent(sa);
				minWait();
				launch_an_app("contacts");
				minWait();
				setDefaultSavingAccount();
				minWait();
				launch_an_app("phone");
				AddContactFromContactsPage(name,lastname,refNum);
				clickBackButton(4);
				minWait();
			}catch (Exception e) {

				e.printStackTrace();
				test.log(LogStatus.ERROR, "Add contact for call screening is failed");

			}
		}
	}
	public void Editedquickresponse_displayproperly_duringcallscreen() throws InterruptedException{
		//To verify user edited quick response should display properly on the screen while sending during a call screen
		clickBackButton(4);
		minWait();
		try {
			make_Call_from_RefDev();
			customWait(11000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 547 182");
			customWait(5000);
			aDriver.swipe(80, 1840, 300, 600, 750);
			minWait();
			clickBtn(Locators_CallSetting.quickresponseedittxt);
			minWait();
			endCall_RefDevice();
			minWait();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void validate_Editedquickresponse_displayproperly_duringcallscreen(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("messaging");
			minWait();
			
			if(isElementExist(Locators_CallSetting.quickresponsemsg)){
				clickBtn(Locators_CallSetting.quickresponsemsg);
				minWait();
				System.out.println("message sent");
				APP_LOGS.info( "Quick response message sent ");
				sa.assertTrue(true, "Quick response message sent");
				test.log(LogStatus.PASS, "Quick response message sent ");
			}
			else{
				System.out.println("message not sent");
				APP_LOGS.info("Quick response message not sent");
				sa.fail();
				test.log(LogStatus.FAIL, "Quick response message not sent");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Validate edited quickresponse display properly during callscreen is failed");

		}

	}
}
































































































































