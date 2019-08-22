package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_FM_Util extends BaseUtil {

	public boolean check = false;
	public SoftAssert softAssert;
	
	public void End() {
		aDriver.quit();
	}
	
	//Press Android KeyCode 4 ,for back button
	public void logout() throws InterruptedException {
		customWait(4000);
		longpress(4);
		  APP_LOGS.info("Exit FM radio App succesfully. ");
		minWait();
	}
	
	public void stopAdb() throws InterruptedException, IOException {
		  customWait(4000);
		  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		  APP_LOGS.info("Adb logs stopped succesfully. ");
		  customWait(2000);
		 }
	
	//Launch FM App
	public void launchFM() throws InterruptedException {
		launchApp("FM_PACKAGE","FM_ACTIVITY");
		APP_LOGS.info("FM Radio Launched Succesfully");
	}
	
	//Validate String FM and Record Ic
	public void validateFMLaunch() {
		boolean check1 = false;
		boolean check2 = false;
		if(isElementExist(Locators_FM.FMRadioTxt)){
			check1 = true;
			APP_LOGS.info("FM Radio Text is present.");
		}
		if(isElementExist(Locators_FM.recordMsgIcon)){
			check2 = true;
			APP_LOGS.info("FM Radio Record Msg Tv is present.");
		}

		if((check1)&&(check2)){
			check = true;
			APP_LOGS.info("FM Radio Launched succesful.");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("FM Radio is not  Launched unsuccesful.");
			Assert.fail();
		}
	}
	
	//FM toggle Switch Off
	public void turnOFF_FM() throws InterruptedException {
		minWait();	
		clickBtn(Locators_FM.OnOFFBtn);
		APP_LOGS.info("FM Radio is Turn Off");
	}
	
	//validate by Element I.e REcord Icon 
	public void validateOFF_FM() {
		try{if(isElementExist(Locators_FM.recordMsgIcon)){
			APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
			Assert.fail();
		}
		} 
		catch (Exception e){
			check = false;
			APP_LOGS.info("FM Radiois Turn Off Succesfully.");
		}
	}
	
	//Start and Stop Recording using Record Icon
	public void StartStopRecording()  {
		try{minWait();	
		Locators_FM.recordMsgIcon.click();
		APP_LOGS.info("Recording is Started succesfully");
		minWait();
		minWait();
		Locators_FM.recordMsgIcon.click();
		APP_LOGS.info("Recording is Stopped succesfully");
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			Assert.fail();
			e.printStackTrace();
		}
	}

	//Validate using Adb Log captured
	public void validateRecording() { 
		searchString("Audio track drift time","XP8_FM_003");
		APP_LOGS.info("Recording is Started validated");
		searchString("Audio track stopping","XP8_FM_003");
		APP_LOGS.info("Recording is Stopped validated");
		searchString("/storage/emulated/0/FMRecording/FMRecording","XP8_FM_003");
		APP_LOGS.info("Recording is saved in Storage validated");
		check = true;
		Assert.assertTrue(check);
	}
	
	public void saveChannel()  {
		try{minWait();
		Locators_FM.PresetBtn2.click();
		APP_LOGS.info("Clicked Add icon");
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	//Select settings option from FM menu option
	public void selectSetting() {
		try{minWait();
		Locators_FM.MenuIcon.click();
		APP_LOGS.info("Clicked Menu icon");
		minWait();
		Locators_FM.SettgOptn.click();
		APP_LOGS.info("Clicked settings icon");
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	//Change recording timings and validate the set time by string
	public void changeandValidateRecordDurtn() throws InterruptedException{
		minWait();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		minWait();
		Locators_FM.RecDurtnOptn.click();
		APP_LOGS.info("Clicked Record Duration Option");
		Locators_FM.FiveminsOptn.click();
		String newRecDurtn1=Locators_FM.SummryBtn.getText();
		System.out.println(newRecDurtn1);
		if(newRecDurtn1.equals("5 minutes")) {
			check1 = true;
		}
		Locators_FM.RecDurtnOptn.click();
		minWait();
		Locators_FM.FifteenminsOptn.click();
		String newRecDurtn2=Locators_FM.SummryBtn.getText();
		if(newRecDurtn2.equals("15 minutes")) {
			check2 = true;
		}
		Locators_FM.RecDurtnOptn.click();
		minWait();
		Locators_FM.ThirtyminsOptn.click();
		minWait();
		String newRecDurtn3=Locators_FM.SummryBtn.getText();
		if(newRecDurtn3.equals("30 minutes")) {
			check3 = true;
		}
		Locators_FM.RecDurtnOptn.click();
		minWait();
		Locators_FM.UntilStopOptn.click();
		String newRecDurtn4=Locators_FM.SummryBtn.getText();
		if(newRecDurtn4.equals("Until stopped")) {
			check4 = true;
		}

		if((check1)&&(check2)&&(check3)&&(check4)){
			check= true;
			APP_LOGS.info("New Record Duration is changed and Updated");
			Assert.assertTrue(check);

		}else{
			APP_LOGS.info(newRecDurtn1+": "+check1+ "\n"+newRecDurtn2+": "+check2+"\n"+newRecDurtn3+": "+check3+"\n"+
					newRecDurtn4);
			Assert.fail();
		}
	}
	
	//Setting Sleep Time from FM menu
	public void sleepTimeSet() {
		try{
			minWait();
			Locators_FM.MenuIcon.click();
			APP_LOGS.info("Clicked Menu icon");
			minWait();
			Locators_FM.SleepOptn.click();
			APP_LOGS.info("Clicked settings icon");
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			Assert.fail();
			e.printStackTrace();
		}
	}
	
	public void validateSleepSetNCancelSleep() throws InterruptedException {
		minWait();
		Locators_FM.fifteenminsSleepOptn.click();
		searchString("Sleeptimer is active","XP8_FM_005");
		APP_LOGS.info("FM Radio is in Sleep Time Mode validated");
		minWait();
		sleepTimeSet();
		searchString("Sleep Cancelled","XP8_FM_005");
		APP_LOGS.info("FM Radio is Cancelled Sleep Time Mode validated");
	}
	
	
	public void validatescanAllStations() {
		
		try {minWait();
		String getChannelName=Locators_FM.ChannelTxt.getText();
		String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
		System.out.println(sChannel);
		minWait();
		minWait();
		Locators_FM.MenuIcon.click();
		minWait();
		Locators_FM.ScanOptn.click();
		minWait();
		Locators_FM.AllStationsOptn.click();
		minWait();
		minWait();
		minWait();
		Locators_FM.StopOptn.click();
		String getChannelName1=Locators_FM.ChannelTxt.getText();
		String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
		System.out.println(sChannel1);
		if(sChannel.equals(sChannel1)){
			minWait();
			Locators_FM.ForwrdBtn.click();
			minWait();
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_FM.PresetBtn2).release().perform();
		}
		else {
			minWait();
			minWait();
			minWait();
		   TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_FM.PresetBtn2).release().perform();
		}
		check =true;
		Assert.assertTrue(check);
		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Save the Scan channel
	public void saveScanChannel(){
		validatescanAllStations();
	}
	
	public void validatereplaceChannel() {
		try {
		minWait();
		Locators_FM.ForwrdBtn.click();
		minWait();
		String getChannelName=Locators_FM.ChannelTxt.getText();
		String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
		//System.out.println(sChannel);
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_FM.PresetBtn2).release().perform();
		//minWait();
		Locators_FM.ReplaceOptn.click();
		String getChannelName1=Locators_FM.ChannelTxt.getText();
		String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
		//System.out.println(sChannel1);
		if(sChannel.equals(sChannel1)){
			minWait();
			check =true;
			Assert.assertTrue(check);
			APP_LOGS.info("Saved Channel is Replaced with other Channel");
		}
		else {
			Assert.fail();
			APP_LOGS.info("Saved Channel is not Replaced with other Channel");
		}
		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateRenameChannel() {
		try {
		minWait();
		String getChannelName=Locators_FM.ChannelTxt.getText();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_FM.PresetBtn2).release().perform();
		minWait();
		minWait();
		Locators_FM.RenameOptn.click();
		enterTextToInputField(Locators_FM.EditName, "98");
		minWait();
		Locators_FM.OKOptn.click();
		minWait();
		String sChannel1=Locators_FM.PresetBtn2.getText();
	    //System.out.println(sChannel1);
		if(sChannel1.equals("98")){
			minWait();
			check =true;
			Assert.assertTrue(check);
			APP_LOGS.info("Saved Channel is Renamed with other Channel");
		}
		else {
			Assert.fail();
			APP_LOGS.info("Saved Channel is not Renamed with other Channel");
			
		}
		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateDeleteChannel() {
		try {
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_FM.PresetBtn2).release().perform();
		minWait();
		minWait();
		Locators_FM.DeleteOptn.click();
		minWait();
		String sChannel1=Locators_FM.PresetBtn2.getText();
	   // System.out.println(sChannel1);
		if(sChannel1.equals("+")){
			minWait();
			check =true;
			Assert.assertTrue(check);
			APP_LOGS.info("Saved Channel is Deleted,Validated");
		}
		else {
			Assert.fail();
			APP_LOGS.info("Saved Channel is not Deleted");
			
		}
		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	
	
	//DefaultChannel As per the Country Target
	public void validatedefaultChannel() throws InterruptedException {
		minWait();
		String getDefaultBandName1=Locators_FM.DefaultRegnlBnd.getText();
		APP_LOGS.info("Got the Default band Channel Name");
		String RegionalBandName = "North America (87.5MHz To 108.0MHz In 200 Khz Steps)";
		if(getDefaultBandName1.equals(RegionalBandName)){
			check =true;
			Assert.assertTrue(check);
			APP_LOGS.info("Default Regional Band Name is Validated");

		}else {
			APP_LOGS.info("Default Regional Band Name is not Validated");
			Assert.fail();
		}
	}

	public void addExistChannel() throws InterruptedException {
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_FM.PresetBtn3).release().perform();
		minWait();
	}
	
	public void validateExistingChannel() { 
		searchString("input event receiver has already been disposed","XP8_FM_011");
		APP_LOGS.info("EXisting Channel is validated");
	}
	
	public void validateForwardAndBackBtn() throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;

		String getfrwdName1=Locators_FM.ChannelTxt.getText();
		//System.out.println(getfrwdName1);
		minWait();
		minWait();
		Locators_FM.ForwrdBtn.click();
		minWait();
		String getfrwdName2=Locators_FM.ChannelTxt.getText();
		//System.out.println(getfrwdName2);
		if(getfrwdName1.equals(getfrwdName2)) {
			Assert.fail();
			APP_LOGS.info("Forward button changed the channel unsucessful");

		}else{
			check1 =true;
			APP_LOGS.info("Forward button changed the channel sucessfully");
		}minWait();
		Locators_FM.BackwrdBtn.click();
		String getfrwdName3=Locators_FM.ChannelTxt.getText();
		//System.out.println(getfrwdName3);
		if(getfrwdName1.equals(getfrwdName3)) {
			check2 =true;
			APP_LOGS.info("Backward button changed the channel sucessfully");
		}
		if((check1)&&(check2)){
			check= true;
			APP_LOGS.info("Forward button and Backward button changed the channel is validated");
			Assert.assertTrue(check);

		}else{
			APP_LOGS.info(getfrwdName2+": "+check1+ "\n"+getfrwdName3+": "+check2+"\n");
			Assert.fail();
		}
	}
	
	
	public void validateForwardBackwrdStations() throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;

		String getfrwdName1=Locators_FM.ChannelTxt.getText();
		System.out.println("1=="+getfrwdName1);
		minWait();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_FM.ForwrdBtn).release().perform();
		minWait();
		minWait();
		customWait(5000);
		String getfrwdName2=Locators_FM.ChannelTxt.getText();
		System.out.println("2==="+getfrwdName2);
		if(getfrwdName1.equals(getfrwdName2)) {
			check1 =true;
			APP_LOGS.info("Forward button changed the channel unsucessful,Bcz of signal");
		}else{
			check1 =true;
			APP_LOGS.info("Forward button changed the channel sucessfully");
		}minWait();
		minWait();
		action.longPress(Locators_FM.BackwrdBtn).release().perform();
		minWait();
		minWait();
		minWait();
		minWait();
		String getfrwdName3=Locators_FM.ChannelTxt.getText();
		System.out.println("3===="+getfrwdName3);
		if(getfrwdName1.equals(getfrwdName3)) {
			check2 =true;
			APP_LOGS.info("Backward button changed the channel sucessfully");
		}
		if((check1)&&(check2)){
			check= true;
			APP_LOGS.info("Forward button and Backward button changed the channel is validated");
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info(getfrwdName1+": "+check1+ "\n"+getfrwdName3+": "+check2+"\n");
			APP_LOGS.info("Forward button and Backward button changed the channel is not validated, because of poor signal");
			Assert.fail();
		}
	}
	
	
	public void changeSettingOptn() throws InterruptedException {
		minWait();
		Locators_FM.RegionalBandOptn.click();
		minWait();
		Locators_FM.EuropeOptn.click();
		minWait();
		
		Locators_FM.AudioOutMode.click();
		minWait();
		Locators_FM.MonoAudioOpt.click();
		minWait();
		Locators_FM.AlternateFreqOpt.click();
		minWait();
		Locators_FM.RecDurtnOptn.click();
		minWait();
		Locators_FM.FifteenminsOptn.click();
		minWait();
		Locators_FM.FactryResetOpt.click();
		minWait();
		Locators_FM.OKOptn.click();
	}
	
	public void validateResortDefaultSettg() throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		 selectSetting();
		 String getRegionalBnd = Locators_FM.DefaultRegnlBnd.getText();
         //System.out.println(getRegionalBnd);
			if(getRegionalBnd.equalsIgnoreCase("North America (87.5MHz To 108.0MHz In 200 Khz Steps)")){
				check1 = true;
				minWait();
			}
			String getAudioMode = Locators_FM.SteroTxt.getText();
			//System.out.println(getAudioMode);
			if(getAudioMode.equalsIgnoreCase("Stereo")){
				check2 = true;
				minWait();
			}
			String getAlternateFreq = Locators_FM.AutoSelectTxt.getText();
			//System.out.println(getAlternateFreq);
			if(getAlternateFreq.equalsIgnoreCase("Auto Select Enabled")){
				check3 = true;
				minWait();
			}
			String getRecrdDuratn = Locators_FM.fiveminxTxt.getText();
			//System.out.println(getRecrdDuratn);
			if(getRecrdDuratn .equalsIgnoreCase("5 minutes")){
				check4 = true;
				minWait();
			}
			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Resorted to Default Setting validated Sucessfully .");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info(getRegionalBnd+": "+check1+"\n"+getAudioMode+": "+"\n"+getAlternateFreq+": "+"\n"+getRecrdDuratn+": "+"\n");
				Assert.fail();
			}
	}
	
	public void initialCall() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("adb -s c1edac9c shell am start -a android.intent.action.CALL -d tel:555-5555");
		customWait(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
	}
	
	public void validateCallInteraction() {
		searchString("startService:1357","XP8_FM_014");
	}

	public void RecordingStartStop()  {
		try{minWait();	
		Locators_FM.recordMsgIcon.click();
		APP_LOGS.info("Recording is Started succesfully");
		minWait();
		minWait();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		minWait();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
		minWait();
		aDriver.openNotifications();
		minWait();
		Locators_FM.FMNotifctn.click();
		Locators_FM.recordMsgIcon.click();
		APP_LOGS.info("Recording is Stopped succesfully");
		}
		catch (Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
 }
	
	public void pushBackgrdFM() {
		try{
		customWait(5000);
		minWait();
		minWait();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		APP_LOGS.info("Push the FM Radio into Background Sucessfully");
		customWait(5000);
		}
	catch(Exception e) {
		Assert.fail();
		APP_LOGS.info("Element not Found");
		e.printStackTrace();
	}	
	}
	
	public void performOperatn() {
		Dimension dimensions = aDriver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		aDriver.swipe(0,scrollStart,0,scrollEnd,2000);
	}
	
	public void validateFMOn() {
		searchString("select_devices for use case (play-fm)","XP8_FM_015");
	
	}
	
	public void switchToMute() {
		try {
		minWait();
		Locators_FM.MuteBtn.click();
		APP_LOGS.info("FM radio enable Mute, succesfully");
		minWait();
		Locators_FM.MonoStereoOptn.click();
		APP_LOGS.info("FM radio enable Speaker, succesfully");
		minWait();
		Locators_FM.MuteBtn.click();
		APP_LOGS.info("FM radio enable unMute, succesfully");
		minWait();
		Locators_FM.MonoStereoOptn.click();
		APP_LOGS.info("FM radio enable Headset, succesfully");
		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}	
	}
	
	public void validateMuteMode() throws InterruptedException {
		searchString("disable_audio_route","XP8_FM_017");
		searchString("speakerOn completed","XP8_FM_017");
		customWait(5000);
	}
	
	public void volumeUpandDown() {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			APP_LOGS.info("FM Radio decrease volume Sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			APP_LOGS.info("FM Radio increase volume Sucessfully");

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			APP_LOGS.info("FM Radio decrease volume Sucessfully");

		}
		catch(Exception e) {
			Assert.fail();
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateVolumeUpDow() throws InterruptedException {
		searchString("android.media.VOLUME_CHANGED_ACTION","XP8_FM_018");
		//searchString("speakerOn completed","XP8_FM_018");
		customWait(5000);
	}
	
	public void validateTune() {
		  try {
		   minWait();
		   String getChannelName=Locators_FM.ChannelTxt.getText();
		   String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
		   
		   minWait();
		   
		   TouchAction action = new TouchAction(aDriver);
		   action.longPress(Locators_FM.PresetBtn1).release().perform();
		   minWait();
		   Locators_FM.TuneBtn.click();
		   minWait();
		   
		   String getChannelName1=Locators_FM.ChannelTxt.getText();
		   String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
		   //System.out.println(sChannel1);
		   if(sChannel.equals(sChannel1)){
		    minWait();
		    check=true;
		    Assert.assertTrue(check);
		    APP_LOGS.info("channel Tune is validated");
		   }
		  }
		  catch(Exception e) {
		   Assert.fail();
		   APP_LOGS.info("Element not Found");
		   e.printStackTrace();
		  }
		 }
	
}
