package com.xp8.util;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import android.graphics.Point;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Dailer_Util extends BaseUtil{


	public boolean check = false;
	public SoftAssert softAssert;

	public void End() {
		aDriver.quit();
	}

	public void launchDailer() throws InterruptedException {
		launchApp("DAILER_PACKAGE","DAILER_ACTIVITY");
		APP_LOGS.info("Phone Dailer Launched succesfully. ");
		minWait();
	}

	public void validateDailerLaunch() throws InterruptedException {
		try {
			launchDailer();
			minWait();
			if (isElementExist(Locators_Dailer.dailerPad)) {
				APP_LOGS.info("Dailer Pad is displayed sucessfully");
				minWait();	
				Assert.assertTrue(true);
			}
			else if(isElementExist(Locators_Dailer.addContactBtn)){
				APP_LOGS.info("Add ContactIcon is displayed sucessfully");
				minWait();	
				Assert.assertTrue(true);
			} 
			else {
				APP_LOGS.info("Add ContactIcon or Dailer Pad is not displayed sucessfully");
				Assert.fail();
			}
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Programmable key is not displayed.");
			minWait();	
			e.printStackTrace();
		}
	}
	public void selectPage(WebElement selectpage) throws InterruptedException {
		minWait();	
		selectpage.click();
	}


	public void validateSelectedPage(WebElement page) throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;

		try { 
			minWait();	
			if (isElementExist (page)) {
				check1 = true;
				APP_LOGS.info("No Speed Dail List is displayed sucessfully");
			}
			else if(isElementExist(Locators_Dailer.dailerPad)){
				check2 = true;
				APP_LOGS.info("Dailer Pad is displayed sucessfully");	
			} 
			if((check1)||(check2)) {
				check= true;
				APP_LOGS.info("All entries are displayed properly.");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Page is not displayed sucessfully");
				Assert.fail();
			}

		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			minWait();	
			e.printStackTrace();
		}
	}
	
	public void dailCallUsingDailPad(String dailNum) throws InterruptedException {
		try {
			Locators_Dailer.dailerPad.click();
			minWait();
			Locators_Dailer.enterNumFiled.click();
			minWait();
			enterTextToInputField(Locators_Dailer.enterNumFiled, dailNum);
			APP_LOGS.info("Number dailed is: "+dailNum);

		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			minWait();	
			e.printStackTrace();
		}
	}
	
	public void DailPadInUnlockMode() throws InterruptedException, IOException {
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
		minWait();
		Locators_Dailer.UnlockOptn.click();
		minWait();
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent 82");
		minWait();
		minWait();
	}
	
	public void validateDailPadAfterUnlock(String summaryText) throws InterruptedException, IOException {
		try {
			
			String getSummaryText = aDriver.findElement(By.xpath("//android.widget.EditText[@text='"+summaryText+"']")).getText();
			if(getSummaryText.equalsIgnoreCase(getSummaryText)){
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				check=true;
				softAssert_true(check, "Pass");	
			}else{
				APP_LOGS.info("Incorrect programmable key is displayed:"+getSummaryText);
				softAssert_false();
			}	
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			minWait();	
			e.printStackTrace();
		}
   }
	 
	public void editSaveContact(WebElement text) throws InterruptedException {
		try {Locators_Dailer.saveContactName.click();
		minWait();
		for(int i=0; i<=2; i++){
			if(isElementExist(Locators_Dailer.AllowOptn)) {
				Locators_Dailer.AllowOptn.click();
				minWait();
			}
		}
		Locators_Dailer.editContactIcon.click();
		minWait();
		Locators_Dailer.text1.click();
		minWait();
		text.click();
		minWait();
		Locators_Dailer.saveIcn.click();
		APP_LOGS.info("Contact is edited and Saved: ");
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			minWait();	
			e.printStackTrace();
		}
	}

	
	public void validateEditContact(String summaryText) throws InterruptedException {
		boolean check1 = false;
		try {
			minWait();
			String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='" + summaryText + "']"))
					.getText();
			System.out.println(getSummaryText);
			if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				APP_LOGS.info("App assigned to programmable key is: " + getSummaryText);
				check1 = true;
				minWait();
			} else {
				APP_LOGS.info("Incorrect app summary text is displayed: " + getSummaryText);
			}
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			minWait();	
			e.printStackTrace();
		}
	}
	
	
	public void saveContact() throws InterruptedException {
		Locators_Dailer.AddtoContact.click();
		minWait();
		Locators_Dailer.createNewContact.click();
		minWait();
		if(isElementExist(Locators_Dailer.OkBtn)) {
			Locators_Dailer.OkBtn.click();
		}
		minWait();
		Locators_Dailer.saveIcn.click();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}
	
	public void dailcall() throws InterruptedException {
		minWait();
		Locators_Dailer.dailIcon.click();
	}
	
	 public void validateDailCall() {
			searchString("startService:1357","XP8_Dailer_005");
		}
		 
	 public void validateUI_DailCall(WebElement num,String dailNum) throws InterruptedException {
		 boolean check1 = false;
		 boolean check2 = false;
		 try {
			 minWait();
			 minWait();
			 if ( isElementExist(num)) {
				 APP_LOGS.info("Dailed Number exists");
				 check1 = true;
				 minWait();
			 } else {
				 APP_LOGS.info("Incorrect Dail number is displayed: " );
			 }

			 if(isElementExist(Locators_Dailer.endCallIcon)){
				 check2 = true;
				 APP_LOGS.info("Dail Call displayed sucessfully");	
			 } 
			 if((check1)&&(check2)) {
				 check= true;
				 APP_LOGS.info("Dailed Call Number from Dail Pad");
				 Assert.assertTrue(check);
			 }
			 else {
				 APP_LOGS.info("Dailed Call Number from Dail Pad not displayed sucessfully");
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 APP_LOGS.info("Dail call is not sucessful.");
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void validateCallLogsUpdate(WebElement num, WebElement name) throws InterruptedException {
		 boolean check1 = false;
		 boolean check2 = false;
		 try {
			 if ( isElementExist(num)) {
				 APP_LOGS.info("Dailed Number exists");
				 check1 = true;
				 minWait();
			 } 
			 if ( isElementExist(name)) {
				 APP_LOGS.info("Dailed Name exists");
				 check2 = true;
				 minWait();
			 }
			 if((check1)||(check2)) {
				 check= true;
				 APP_LOGS.info("Dailed Call Number and Name is Displayed");
				 Assert.assertTrue(check);
			 }
			 else {
				 APP_LOGS.info("Dailed Call Number from Dail Pad not displayed sucessfully");
				 Assert.fail();
			 }


		 }catch (NoSuchElementException e) {
			 APP_LOGS.info("Dail call is not sucessful.");
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void validateLogSavedContact(String num) {
		 searchString("Saved contact","XP8_Dailer_007");
		 searchString("startCapDiscoveryFromDialer = " + num,"XP8_Dailer_007");
	 }
	 
	 public void validateUiSavedContact(String num) throws InterruptedException {
		 try {
			 minWait();
			 String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='" + num + "']"))
					 .getText();
			 System.out.println(getSummaryText);
			 if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				 APP_LOGS.info("Saved Contact is present: " + getSummaryText);
				 check= true;
				 minWait();
			 } else {
				 APP_LOGS.info("Saved Contact is not displayed: " + getSummaryText);
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 APP_LOGS.info("Dail call is not sucessful.");
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void validateAllOptions() throws InterruptedException {
		 boolean check1 = false;
		 boolean check2 = false;
		 boolean check3 = false;
		 Locators_Dailer.MoreOptnsIcn.click();
		 minWait();

		 try {
			 if ( isElementExist(Locators_Dailer.CallHistoryOptn)) {
				 APP_LOGS.info("Dailed Number exists");
				 check1 = true;
				 minWait();
			 } 
			 if ( isElementExist(Locators_Dailer.ImptExprtOptn)) {
				 APP_LOGS.info("Dailed Name exists");
				 check2 = true;
				 minWait();
			 }

			 if ( isElementExist(Locators_Dailer.clrFrquentsOptn)) {
				 APP_LOGS.info("Dailed Name exists");
				 check3 = true;
				 minWait();
			 } if((check1)&&(check2)&&(check3)) {
				 check= true;
				 APP_LOGS.info("All the Optiona are Displayed");
				 Assert.assertTrue(check);
			 }
			 else {
				 APP_LOGS.info("All the Options are not Displayed");
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {

			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void selectClearFrequentContacted() throws InterruptedException {
		 try {minWait();
		 Locators_Dailer.MoreOptnsIcn.click();
		 minWait();
		 if(isElementExist(Locators_Dailer.clrFrquentsOptn)) {
			 APP_LOGS.info("Clear Frequent Contacted is not available");
			 Locators_Dailer.clrFrquentsOptn.click();
			 minWait();
			 Locators_Dailer.OkBtnForClr.click();
			 minWait();
		 }
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void selectClearHistory() throws InterruptedException {
		 try {
			 minWait();
			 Locators_Dailer.MoreOptnsIcn.click();
			 minWait();
			 Locators_Dailer.CallHistoryOptn.click();

		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void selectAllCallOptn() throws InterruptedException {
		 Locators_Dailer.AllCallTypOptns.click();
		 minWait();
		 Locators_Dailer.AllCall.click();
	 }
	 
	 public void validateCallTypeHistory(WebElement callType,String summaryText) throws InterruptedException {
		 try {  
			 Locators_Dailer.AllCallTypOptns.click();
			 minWait(); 
			 clickBtn(callType);
			 minWait();
			 String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='" + summaryText + "']"))
					 .getText();
			 System.out.println(getSummaryText);
			 if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				 APP_LOGS.info("Call Type History is: " + getSummaryText);
				 check = true;
				 minWait();
				 Assert.assertTrue(check);
			 } else {
				 APP_LOGS.info("Call Type History is not displayed: " + getSummaryText);
				 Assert.fail();
			 }
			 selectAllCallOptn();
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void searchCallLog(String searchname) throws InterruptedException {
		 Locators_Dailer.MoreOptnCallHistry.click();
		 minWait();
		 Locators_Dailer.searchCallLogs.click();
		 minWait();
		 minWait();
		 enterTextToInputField(Locators_Dailer.editsearchCallLog,searchname);
		 APP_LOGS.info("Search Name in Call Log is sucessful");
	 }
	 
	 public void selectclearCallHistory() throws InterruptedException {
		 Locators_Dailer.MoreOptnCallHistry.click();
		 minWait();
		 Locators_Dailer.clearCallHistory.click();
		 minWait();
		 TouchAction action = new TouchAction(aDriver);
		 action.longPress(Locators_Dailer.firstEntry).release().perform();
		 APP_LOGS.info("Selected first Entry Call Log History is sucessful");
		 minWait();
		 Locators_Dailer.selectionMenu.click();
		 APP_LOGS.info("Select All List Menu");
		 if(isElementExist(Locators_Dailer.selectAllList)){
			 minWait();
			 Locators_Dailer.selectAllList.click();
			 APP_LOGS.info("Selected All Call history Logs");
		 }else {
			 Locators_Dailer.oneselectionMenu.click();
			 APP_LOGS.info("Selected History logs ");
		 }
		 Locators_Dailer.clearBtn.click();
		 minWait();
		 Locators_Dailer.OkBtnForClr.click();
	 }
	 
	 public void validateClearCallHistory() throws InterruptedException {
		 try{
			 if( isElementExist(Locators_Dailer.callLogEmpty)) {
				 APP_LOGS.info("Cleared Call Log History  is Sucessfull" );
				 check = true;
				 minWait();
				 Assert.assertTrue(check);
			 } else {
				 APP_LOGS.info("Cleared Call Log History is unsucessful" );
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 
	 public void validateSearchLogs(String summaryText) throws InterruptedException {
		 try {
			 minWait();
			 String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='" + summaryText + "']"))
					 .getText();
//			 System.out.println(getSummaryText);
			 if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				 APP_LOGS.info("Search Name in Call Log History/Search Contact is: " + getSummaryText);
				 check = true;
				 minWait();
				 Assert.assertTrue(check);
			 } else {
				 APP_LOGS.info("Search Name is not displayed in Search Call Logs/Search Contact: " + getSummaryText);
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	
	 public void callSearchName(WebElement name) throws InterruptedException {
		name.click();
		 APP_LOGS.info("Call the search name from Contact List  is Sucessfull" );
		 customWait(5000);
		 customWait(5000);
			endCall();
	 }
	 
	 public void enterSearchContact(String searchName) throws InterruptedException {
		 Locators_Dailer.searchField.click();
		 minWait();
			enterTextToInputField(Locators_Dailer.searchTxtEnter,searchName);
			 APP_LOGS.info("Entered Contact in seach field is sucessful");
	 }
	 
	 public void selectCallDetails() throws InterruptedException {
		 minWait();
		 Locators_Dailer.firstEntryCallHistory.click();
		 minWait();
		 Locators_Dailer.callDetailsOptn.click();
	 }
	 
	 public void blockContact() throws InterruptedException {
		 minWait();
		 TouchAction action = new TouchAction(aDriver);
		 action.longPress(Locators_Dailer.firstEntryCallHistory).release().perform();
		 minWait();
		 minWait();
		 Locators_Dailer.blockNumOptn.click();
		
		 Locators_Dailer.blockBtn.click();
		 minWait();
//		 Locators_Dailer.CallIcon.click();
//		 TouchAction action = new TouchAction(aDriver);
		 action.longPress(Locators_Dailer.firstEntryCallHistory).release().perform();
		 minWait();
		 Locators_Dailer.unblockNumOptn.click();
		 Locators_Dailer.unblockBtn.click();
	 }
	 
	 public void validateCallDetails() throws InterruptedException {
		 boolean check1 = false;
		 boolean check2 = false;
		 try {
	
		  if( isElementExist(Locators_Dailer.CallDetailTxt)) {
			 APP_LOGS.info("Call Details Text is Displayed Sucessfully" );
			 check1 = true;
			 minWait();
		 } 
		  if( isElementExist(Locators_Dailer.outgoingCallTxt)) {
				 APP_LOGS.info("Out going Call text is displayed Sucessfully" );
				 check2 = true;
				 minWait();
			 }  
		  if((check1)&&(check2)) {
			  APP_LOGS.info("Call Details Page is displayed sucessfully" );
			  check = true;
			  Assert.assertTrue(check);
		  }
			else {
			 APP_LOGS.info("Call Details page display unsucessful" );
			 Assert.fail();
		 }
	 }catch (NoSuchElementException e) {
		 minWait();	
		 e.printStackTrace();
	 }
	 }
	 
	 public void validateLogBlockedUnBlockedContact() throws InterruptedException {
		 try {
			 if( isElementExist(Locators_Dailer.blockList)) {
				 APP_LOGS.info("Call Block Black List is selected and Displayed Sucessfully" );
				 check = true;
				 minWait();
			 } 
			 else {
				 APP_LOGS.info("Call Block Black List is not selected and not Displayed " );
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
		 searchString("showBlockNumberDialogFlow - start","XP8_Dailer_024");
		 searchString("Unblock number" ,"XP8_Dailer_024");
	 }
	 
	 public void selectSettings() throws InterruptedException {
		 minWait();
		 Locators_Dailer.MoreOptnsIcn.click();
		 minWait();
		 Locators_Dailer.settingsOptn.click();
		 minWait();
		 Locators_Dailer.callScreeningOptn.click();
	 }
	 
	 public void exportVCFContactsStorage() throws InterruptedException {
			Locators_Dailer.MoreOptnsIcn.click();
			minWait();
			Locators_Dailer.ImptExprtOptn.click();
			minWait();
			Locators_Dailer.exportVcf.click();
			minWait();
			Locators_Dailer.selectionMenu.click();
			minWait();
			Locators_Dailer.selectAllList.click();
			minWait();
			Locators_Dailer.OkBtnFrExport.click();
			minWait();
			minWait();
			Locators_Dailer.DownloadsOptn.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
//			Locators_Dailer.Xp8800Optn.click();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
//			scroll();
//			Locators_Dailer.storageOptn.click();
			 APP_LOGS.info("Clicked on Downloads " );
//			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
//			Locators_Dailer.saveExport.click();
			 APP_LOGS.info("Stored Exported VCf in Storage " );
	 }
	 
	 
	 public void importVCFContact() throws InterruptedException {
		 Locators_Dailer.MoreOptnsIcn.click();
			minWait();
			Locators_Dailer.ImptExprtOptn.click();
			minWait();
			Locators_Dailer.importVcf.click();
			minWait();
			Locators_Dailer.AllowOptnImprt.click();
			minWait();
			Locators_Dailer.menuStorageOptn.click();
			minWait();
			Locators_Dailer.DownloadsOptn.click();
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_TAB);
			minWait();
//			Locators_Dailer.downloadOptn.click();
			minWait();
			minWait();
			Locators_Dailer.nameVcfCard.click();
			 APP_LOGS.info("Imported Contact from VCF file is selected" );
	 }
	 
	 public void validateImportedVcfContact() throws InterruptedException {
		 boolean check1 = false;
		 boolean check2 = false;
		 try {
			  if( isElementExist(Locators_Dailer.Automation1Contact)) {
				 APP_LOGS.info("Imported Contact from VCF file is Sucessful" );
				 check1 = true;
				 minWait();
			 } 
			  if( isElementExist(Locators_Dailer.Automation2Contact)) {
					 APP_LOGS.info("Imported Contact from VCF file is Sucessful" );
					 check2 = true;
					 minWait();
				 }  
			  if((check1)&&(check2)) {
				  APP_LOGS.info("Imported Contact from VCF file is displayed sucessfully" );
				  check = true;
				  Assert.assertTrue(check);
			  }
				else {
				 APP_LOGS.info("Imported Contact from VCF file is display unsucessful");
				 Assert.fail();
			 }
		 }catch (NoSuchElementException e) {
			 minWait();	
			 e.printStackTrace();
		 }
	 }
	 
	 public void deleteimportedContacts() {
		 Locators_Dailer.Automation1Contact.click();
		 Locators_Dailer.MoreOptnsIcn.click();
		 Locators_Dailer.deleteContactOptn.click();
		 Locators_Dailer.deleteConfiemBtn.click();
		 
		 Locators_Dailer.Automation2Contact.click();
		 Locators_Dailer.MoreOptnsIcn.click();
		 Locators_Dailer.deleteContactOptn.click();
		 Locators_Dailer.deleteConfiemBtn.click();
		 
	 }
	 
	 public void validateExportedVCF() {
		 searchString("storagebackend.StorageBackendContentProvider","XP8_Dailer_026");
	 }
	
	 
	 public void endCall() throws InterruptedException {
		 minWait();	
		 customWait(5000);
		 Locators_Dailer.endCallIcon.click();
	 }
	 
	 public void DailLandscapemode() throws InterruptedException {
		 minWait();
		 aDriver.rotate(ScreenOrientation.LANDSCAPE);
		 APP_LOGS.info("Dail Number in Landscape mode succesfully.");	
		 minWait();
	 }
	 
	 public void BackPotraitmode() throws InterruptedException {
		 minWait();
		 aDriver.rotate(ScreenOrientation.PORTRAIT);
		 APP_LOGS.info("Browser Turn into Potrait mode succesfully.");	
	 }
	 
	 
	 public void callinBackground() throws InterruptedException {
		 dailcall();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			Locators_Dailer.PhoneIcon.click();
			APP_LOGS.info("Phone Dailer is selected succesfully. ");
	 }
	 
	
	 
	 
	 
	 public void scroll() {
		 org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
		 System.out.println(size);
		 int x=size.getWidth()/2;
		 int starty=(int)(size.getHeight()*0.60);
		 int endy=(int)(size.getHeight()*0.10);
		 aDriver.swipe(x, starty, x, endy, 2000);
		
	 }
	 
	 public void logout() throws InterruptedException {
		 minWait();
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 APP_LOGS.info("Home Button is Selected sucessfully");
		 minWait();
	 }
	
	public void stopAdb() throws InterruptedException, IOException {
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		APP_LOGS.info("Adb logs stopped succesfully. ");
		customWait(2000);
	}
}
