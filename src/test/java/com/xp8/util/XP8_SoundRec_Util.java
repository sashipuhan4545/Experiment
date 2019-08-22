package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;



import antlr.collections.List;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;



public class XP8_SoundRec_Util extends BaseUtil  {
	
	public boolean check = false;
	public SoftAssert softAssert;
	
	public void End() {
		aDriver.quit();
	}
	
	public void validateSoundRecorderLaunch() throws InterruptedException{
		/*
		 * Check the Recorder button with elements and initial duration
		 */
		customWait(4000);
		if(isElementExist(Locators_SoundRec.PressRecordText) && isElementExist(Locators_SoundRec.initialTimeText) && isElementExist(Locators_SoundRec.recordBtn) ){

			check = true;
			APP_LOGS.info(" PressRecordText, initialTimeText and Record button is displayed. Sound Recorder Launch is sucessfully.");
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info("PressRecordText, initialTimeText and Record button is not displayed.Sound Recorder Launch is unsuccesful.");
			Assert.fail();	
		}
	}
	
	//Start Recorder
	public void recordSound() throws InterruptedException {
		Locators_SoundRec.recordBtn.click();
		minWait();
		selectRecordBtn();
		APP_LOGS.info("Pressed Record button sucessfully");
		minWait();
		Locators_SoundRec.stopBtn.click();
		APP_LOGS.info("Pressed Stop button sucessfully");
		minWait();
	}
	
	//Selecting List Option after Launching Application
	public void selectListOptn() throws InterruptedException {
		Locators_SoundRec.listIcon.click();
		minWait();
		if(isElementExist(Locators_SoundRec.ListallowBtn)) {
		Locators_SoundRec.ListallowBtn.click();}
		minWait();
		longpress(4); 
	}
	
	public void validateListPage() throws InterruptedException {
		//deleteSavedFolder();
		validateSoundRecordDeletion();
	}
	
	public void DiscardSound() throws InterruptedException {
		minWait();
		Locators_SoundRec.DiscardBtn.click();
		APP_LOGS.info("Pressed Discard button sucessfully");
		minWait();
	}
	  
	public void validateRecordedSoundDiscarded() throws InterruptedException {
		/*
		 * Delete the sound recorder and check it is present or not
		 */
		minWait();
		Locators_SoundRec.listIcon.click();
		APP_LOGS.info("Pressed List Icon sucessfully");
		minWait();
		String getMsg = Locators_SoundRec.NorecordingMsg.getText();
		
		if(getMsg.equalsIgnoreCase("No recording")){
			check=true;
			APP_LOGS.info("Sound Record is discarded succesfully");
			Assert.assertTrue(check);

		}else{
			APP_LOGS.info("Sound Record  is not discarded succesfully");
			Assert.fail();
		}minWait();
		minWait();
		Locators_SoundRec.backBtn.click();
	}
	
	//Pause and resume
	public void ValidatePauseAndResume() throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;
		
		
		Locators_SoundRec.recordBtn.click();
		minWait();
		selectRecordBtn();
		minWait();
		APP_LOGS.info("Pressed Record button sucessfully");
		Locators_SoundRec.PauseBtn.click();
		minWait();
		String PauseState = Locators_SoundRec.PauseText.getText();
		minWait();
		Locators_SoundRec.recordBtn.click();
		minWait();
		String ResumeState = Locators_SoundRec.RecordingText.getText();
		minWait();
		Locators_SoundRec.stopBtn.click();
		minWait();
		DiscardSound();
		APP_LOGS.info("Pressed Stop button sucessfully");
		
		if(PauseState.equalsIgnoreCase("Pause")){
			check1=true;
			APP_LOGS.info("Sound Record is saved succesfully");
		}
		if(ResumeState.equalsIgnoreCase("Recording")){
			check2=true;
			APP_LOGS.info("Sound Record is Resume succesfully");
		}
		if((check1)&&(check2)){
			check= true;
			APP_LOGS.info("Pause and Resume succesfully.");
			Assert.assertTrue(check);
		}
		else{
			APP_LOGS.info(PauseState+": "+check1+ "\n"+ResumeState+": "+check2+"\n");
			Assert.fail();
		}
	}
	
	//Selecting Allow for pop ups
	public void selectRecordBtn() throws InterruptedException {
		for(int i=0; i<=2; i++) {
			if(isElementExist(Locators_SoundRec.allowBtn)){
				customWait(2000);
				Locators_SoundRec.allowBtn.click();
				APP_LOGS.info("Pressed Allow button sucessfully");
				minWait();
				APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

			}else{
				APP_LOGS.info("Allow Button is not displayed.");

			}
		}maxWait();	
	}
	
	//Save Recorded Sound
	public void SaveSoundRecord() throws InterruptedException {
		minWait();
		Locators_SoundRec.recordingName.clear();
		minWait();
		enterTextToInputField(Locators_SoundRec.recordingName,"SampleSoundRecord");
		minWait();
		if(isElementExist(Locators_SoundRec.NameExist)) {
			Locators_SoundRec.DiscardBtn.click();
		}
		Locators_SoundRec.saveBtn.click(); 
		APP_LOGS.info("Pressed Save button sucessfully");
		minWait();
		Locators_SoundRec.backBtn.click();
	}
	
	public void validateSoundRecording() throws InterruptedException {
		/*
		 * Check sound recording is taking place in Silent mode
		 */
		String RecTxt=Locators_SoundRec.RecordingText.getText();
		minWait();
		if(RecTxt.equalsIgnoreCase("Recording")){
			check=true;
			APP_LOGS.info("Recording Sound succesfully");
			Assert.assertTrue(check);	
		}else{
			APP_LOGS.info("Recording Sound unsuccesfully");
			Assert.fail();
		}minWait();
		Locators_SoundRec.stopBtn.click();
		minWait();
		Locators_SoundRec.DiscardBtn.click();
		minWait();
	}
     
	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		minWait();
		String SavedRecName=Locators_SoundRec.recordingName.getText();
		minWait();
		Locators_SoundRec.saveBtn.click(); 
		APP_LOGS.info("Pressed Save button sucessfully");
		minWait();
		String ListSavedRecName=Locators_SoundRec.saveBtnUnderRecList.getText();
		Locators_SoundRec.backBtn.click();
		if(SavedRecName.equalsIgnoreCase(ListSavedRecName)){
			check=true;
			APP_LOGS.info("Sound Record is saved succesfully");
			Assert.assertTrue(check);	
		}else{
			APP_LOGS.info("Sound Record is not saved, unsuccesful");
			Assert.fail();
		}
	}
     
	//Delete Saved Recorded Sound
	public void deleteSavedRecorder() throws InterruptedException {
		Locators_SoundRec.listIcon.click();
		APP_LOGS.info("Pressed List Icon sucessfully");
		minWait();
		minWait();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.DeleteIcon.click();
		APP_LOGS.info("Pressed Delete Icon sucessfully");
		minWait();
		Locators_SoundRec.DeleteBtn.click();
		APP_LOGS.info("Pressed Delete Button sucessfully");
		Thread.sleep(2000);
		minWait();
		Locators_SoundRec.backBtn.click();
	}
	
	public void deleteSavedFolder() throws InterruptedException {
		Locators_SoundRec.listIcon.click();
		APP_LOGS.info("Pressed List Icon sucessfully");
		minWait();
		minWait();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntryFolder).release().perform();
		minWait();
		Locators_SoundRec.DeleteIcon.click();
		minWait();
		Locators_SoundRec.DeleteBtn.click();
		APP_LOGS.info("Pressed Delete Button sucessfully");
		Thread.sleep(2000);
		minWait();
		Locators_SoundRec.backBtn.click();
	}
	
	public void validateSoundRecordDeletion() throws InterruptedException {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		minWait();
		String getMsg = Locators_SoundRec.NorecordingMsg.getText();
		if(getMsg.equalsIgnoreCase("No recording")){
			check=true;
			APP_LOGS.info("Sound Record is deleted succesfully");
			Assert.assertTrue(check);

		}else{
			APP_LOGS.info("Sound Record  is not deleted succesfully");
			Assert.fail();
		}minWait();
		Locators_SoundRec.backBtn.click();
	}
	
	//Edit Saved Recoreded Sound
	public void editSoundRecordName() throws InterruptedException {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.EditIcon.click();
		minWait();
		Locators_SoundRec.recordingName.clear();
		minWait();
		enterTextToInputField(Locators_SoundRec.recordingName,"DemoSoundRecord");
		minWait();
		Locators_SoundRec.saveBtn.click();
		minWait();
		Locators_SoundRec.CloseBtn.click();	
		minWait();
		Locators_SoundRec.backBtn.click();
	}
     
	public void validateSoundRecordEdit() throws InterruptedException {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		String getEditedName = Locators_SoundRec.saveBtnUnderRecList.getText();
		if(getEditedName.equalsIgnoreCase("DemoSoundRecord")){
			check=true;
			APP_LOGS.info("Sound Record name is Edited succesfully");
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info("Sound Record name is not Edited succesfully");
			Assert.fail();
		}minWait();
		Locators_SoundRec.backBtn.click();		
	}
	
	//Adding Multiple sound Records
	public void addMultipleSoundRec() throws InterruptedException {
		for(int i=0; i<=2; i++){
			recordSound();
			minWait();
			Locators_SoundRec.recordingName.clear();
			minWait();
			enterTextToInputField(Locators_SoundRec.recordingName,"SampleSoundRecord"+i);
			minWait();
			Locators_SoundRec.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			Locators_SoundRec.backBtn.click();
		}
	}
	
	//Delete Sound Record using SelectAll Option
	public void deleteMulSoundRec() throws InterruptedException {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.SelectAllOpt.click();
		APP_LOGS.info("Selected All Sound Records sucessfully");
		minWait();
		Locators_SoundRec.DeleteIcon.click();
		minWait();
		Locators_SoundRec.DeleteBtn.click();
		minWait();
		Locators_SoundRec.backBtn.click();	
		
	}
	
	//Selecting All Sound Recording from checkBox
	public void selectAllSoundRec() throws InterruptedException {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		//List<AnroidElement> list=(List) aDriver.findElements(By.className(""));
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.SelectAllOpt.click();
		APP_LOGS.info("Selected All Sound Records sucessfully");
		minWait();
		/*selectCheckbox(Locators_SoundRec.CheckBox2);
		minWait();
		selectCheckbox(Locators_SoundRec.CheckBox3);
		minWait();
		selectCheckbox(Locators_SoundRec.CheckBox4);
		minWait();
		selectCheckbox(Locators_SoundRec.CheckBox5);*/
	}
	
	//Deselect All Sound Recording from Deselct Option
	public void deslectAllSoundRec() throws InterruptedException {
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.DeSelectAllOpt.click();
		APP_LOGS.info("Deselected All Sound Records sucessfully");
		minWait();
	}
	
	public void validateSelectAll() throws InterruptedException {
		minWait();
		if(isElementExist(Locators_SoundRec.DeleteIcon)&&isElementExist(Locators_SoundRec.ShareIcon)){
			check=true;
			APP_LOGS.info("Validayed Sound Record List is selected succesfully");
			Assert.assertTrue(check);
		}else{ 
		APP_LOGS.info("Sound Record List is not selected succesfully");
		Assert.fail();
		}minWait();
		//Locators_SoundRec.backBtn.click();	
	}
	
	
	
	public void validateDeselectAll() throws InterruptedException {
		minWait();
		if(isElementExist(Locators_SoundRec.DeleteIcon)&&isElementExist(Locators_SoundRec.ShareIcon)){
			APP_LOGS.info("Sound Record List is not deselected succesfully");
			Assert.fail();
		}else{ check=true;
		APP_LOGS.info("Validated Sound Record List is deselected succesfully");
		Assert.assertTrue(check);
		}minWait();
		Locators_SoundRec.backBtn.click();	
	}
	
	 
	public void validateMulDeleteSoundRec() throws InterruptedException {
		validateSoundRecordDeletion();
	}
	
	public void playSoundRecord() throws InterruptedException {
		minWait();
		SaveSoundRecord();
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		Locators_SoundRec.firstEntry.click();
		minWait();
		minWait();
		
	}
	
	public void validateRecSoundPlay() throws InterruptedException {
		//Select recording to play from recording list
		if(isElementExist(Locators_SoundRec.PauseBtnList)) {
			check = true;
			APP_LOGS.info("Playing Recorded Sound succesful.");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("Recorded Sound is not Played unsuccesful.");
			Assert.fail();
		}minWait();
		Locators_SoundRec.backBtn.click();	
	}
	
	public void deviceSilentMode() throws InterruptedException {
		minWait();
		minWait();
		longpress(25);
		minWait();
		Locators_SoundRec.recordBtn.click();
		minWait();
		
	}
	
	/*public void launchFileManager() throws InterruptedException {
		try{launchApp("FILEMGR_PACKAGE","FILEMGR_ACTIVITY");
		 customWait(2000);
		org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
		System.out.println(size);
		int x=size.getWidth()/2;
		int starty=(int)(size.getHeight()*0.60);
		int endy=(int)(size.getHeight()*0.10);
		aDriver.swipe(x, starty, x, endy, 2000);
		 minWait();
		 //minWait();
		Locators_SoundRec.SoundRecoderOpt.click();
		// aDriver.findElementByAndroidUIAutomator("UiSelector().text(\"SoundRecorder\")").click();
		}
		catch (Exception e){
			check = false;
			APP_LOGS.info("Element not Found.");
		}
	}*/
	
	public void launchFilemanager() throws InterruptedException {
		 try {
			 launchApp("FILEMGR_PACKAGE","FILEMGR_ACTIVITY");
		 
		   customWait(2000);
		   minWait();
		   //customWait(2000);
		  Locators_SoundRec.SearchOptn.click();
		  minWait();
		  enterTextToInputField(Locators_SoundRec.SearchTxt,"SoundRecorder");
		  if(isElementExist(Locators_SoundRec.HistorySearch)){
		   Locators_SoundRec.HistorySearch.click();
		  }
		   minWait();
		 
		  Locators_SoundRec.SelectSoundRec.click();
		 }catch (Exception e) {
				APP_LOGS.info("Element not Found");
				Assert.fail();
				e.printStackTrace();
			}
		 }
	
	public void validateDetailsSoundRec() throws InterruptedException {
		
		
		if(isElementExist(Locators_SoundRec.SampleSoundRec)){
			check = true;
			String getSoundRecName= Locators_SoundRec.SampleSoundRec.getText();
			APP_LOGS.info("Sound Recorder Name is :"+getSoundRecName);
		}
		else{
			APP_LOGS.info("All Details of Sound Record is not Displayed");
			Assert.fail();
		}	  customWait(2000);
		minWait();
		logout();
		minWait();
		logout();
		minWait();
		logout();
		minWait();
		logout();
	}
	
	
	public void validateCallInteraction() {
		
		searchString("startService:1357","XP8_SR_012");
	}
	
	public void initialCall() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("adb -s c1edac9c shell am start -a android.intent.action.CALL -d tel:555-5555");
		customWait(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
	}
	
	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}
	
	public void startRecord() throws InterruptedException {
		Locators_SoundRec.recordBtn.click();
		minWait();
		
	}
	
	public void stopAdb() throws InterruptedException, IOException {
		  customWait(4000);
		  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		  APP_LOGS.info("Adb logs stopped succesfully. ");
		  customWait(2000);
		 }
	
	public void launchSounchRec() throws InterruptedException {
		launchApp("SOUNDRECORDER_PACKAGE","SOUNDRECORDER_ACTIVITY");
		minWait();
		
	}
	
}
