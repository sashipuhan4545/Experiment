package com.xp8.util;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;



import io.appium.java_client.android.AndroidKeyCode;

public class XP8_ProgrammableKey_Util extends BaseUtil {
	
	public SoftAssert softAssert;
	

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;
	public boolean check6 = false;
	public boolean check7 = false;
	public boolean check8 = false;
	public boolean check9 = false;
	public boolean check10= false;
	
	public void End() {
		aDriver.quit();
	}
	
	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}
	
	public void stopAdb() throws InterruptedException, IOException {
		  customWait(4000);
		  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		  APP_LOGS.info("Adb logs stopped succesfully. ");
		  customWait(2000);
	}
	
	public void launchSettings() throws InterruptedException {
		launchApp("SETTINGS_PACKAGE","SETTINGS_ACTIVITY");
		APP_LOGS.info("Settings Launched succesfully. ");
		minWait();
	}
	
	public void press_PTT_Key() throws InterruptedException, IOException{
		/*
		 * Long press event of PTT key is fired.
		*/
		
		try {
			Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_PTT");
			customWait(5000);
			customWait(9000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void press_Yellow_Key() throws InterruptedException, IOException{
		/*
		 * Long press event of PTT key is fired.
		*/
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_CAMERA");
		customWait(5000);
	}
	
	/*
	 public void scroll() throws InterruptedException {
		 try {
			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			 System.out.println(size);
			 int x=size.getWidth()/2;
			 int starty=(int)(size.getHeight()*0.60);
			 int endy=(int)(size.getHeight()*0.10);
			 aDriver.swipe(x, starty, x, endy, 2000);
			 minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		
	 }
	*/
	 public void validate_ProgrammableKey_in_NativeSettings() throws InterruptedException{
		 try { 
			 launchSettings();
			 minWait();
			 for (int iter = 1; iter <= 4; iter++) {
				 minWait();
				 if (isElementExist(Locators_ProgrammableKey.ProgrammableKey)) {
					 APP_LOGS.info("Programmable key displayed sucessfully");
					 minWait();	
					 Assert.assertTrue(true);
					 break;					
				 }
				 else {
					 scroll();
					 continue;					
				 } 
			}
		 }
		 catch (NoSuchElementException e) {
			 APP_LOGS.info("Programmable key is not displayed.");
			 minWait();	
			 Assert.fail();
		 }
	 }
	
	 public void scroll_to_ProgrammableKey() throws InterruptedException{
		 try { 
			 launchSettings();
			 minWait();
		
//		 	 for (int iter = 1; iter <= 4; iter++) {
//			 minWait();
//			 if (isElementExist(Locators_ProgrammableKey.ProgrammableKey)) {
//				 APP_LOGS.info("Programmable key displayed sucessfully");
//				 minWait();	
//				
//				 APP_LOGS.info("Programmable key clicked sucessfully");
//				 
//				 break;					
//			 }
//			 else {
//				 scroll();
//				 continue;					
//			 }
//			 }		 
			 aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/content_parent\")).scrollIntoView(new UiSelector().text(\"Programmable Keys\"))");
			 clickBtn(Locators_ProgrammableKey.ProgrammableKey);
		 }
		 catch (NoSuchElementException e) {
			 APP_LOGS.info("Programmable key is not displayed.");
			 minWait();	
		 }
	 }
	 
	 public void validate_ProgrammableKeySummaryText(WebElement app) throws InterruptedException{
		 /*
		  * Retrieves programmable key summary text.
		  */
		 minWait();
		 try {
			 if(isElementExist(app)){
				 APP_LOGS.info("Programmable key summary is displayed successfully");	
				 String getSummaryText = app.getText();
				 APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				 minWait();
				 check=true;
				 softAssert_true(check, "Pass");	 
			 }else{
				 APP_LOGS.info("Programmable key summary is not displayed");
				 softAssert_false();
			 }
		 } catch (NoSuchElementException e) {			
			 e.printStackTrace();
		 }
	 }

	 
	 public void scrollToNoApplication(WebElement key) throws InterruptedException{
		 /*
		  * scrolls to top of the PTT key app list
		  */
		 try {
			minWait();
			 clickBtn(key);
			 customWait(4000);
			 APP_LOGS.info("Navigated to select PTT KEY app screen sucessfully");
			 for (int i = 1; i <= 44; i++) {
				 if (isElementExist(Locators_ProgrammableKey.no_Application_app)) {
					 String getAppName = Locators_ProgrammableKey.no_Application_app.getText();
						APP_LOGS.info("No Application app is found.");
						minWait();					
						break;					
					} else {
						//APP_LOGS.info("Searching for No application app: " + i);
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
						continue;					
					}
			 }
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}	
     }
	 
	 public void assign_app_to_ProgrammableKey(WebElement app) throws InterruptedException{
			/*
			 * Assign app to programmable key.
			 * Send webelement to be selected as parameter
			*/
			try {         	
				for (int iter = 1; iter <= 44; iter++) {
					
					if (isElementExist(app)) {
						String getAppName = app.getText();
						APP_LOGS.info("Searched application is found sucessfully");
						APP_LOGS.info("Searched application name: "+getAppName);
						minWait();
						clickBtn(app);
						APP_LOGS.info(getAppName+" : Application is assigned to programmableKey");
						minWait();	
						if(isElementExist(Locators_ProgrammableKey.Ok_Btn)) {
						clickBtn(Locators_ProgrammableKey.Ok_Btn);
						minWait();
						}
						break;					
					} else {
						//APP_LOGS.info("Searching for application to assign: " + iter);
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;					
					} 
				}
	        } catch (NoSuchElementException e) {
	            e.printStackTrace();
	        }				
			
		}
	 
	 public void validate_ProgrammableKeySummaryText_of_ParticularApp(String summaryText) throws InterruptedException{
			/*
			 * Retrieves programmable key summary text of a particular application.
			*/
			minWait();
			try {
				String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+summaryText+"']")).getText();
				if(getSummaryText.equalsIgnoreCase(getSummaryText)){
					APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
					minWait();
					check=true;
					softAssert_true(check, "Pass");	
				}else{
					APP_LOGS.info("Incorrect programmable key is displayed:"+getSummaryText);
					softAssert_false();
				}								
				
			} catch (NoSuchElementException e) {			
				e.printStackTrace();
			}
		}
	 
	 public void validate_launchedApplication(WebElement app, String summaryText) throws InterruptedException, IOException {
			/*
			 * Validates application launched with respect to the application
			 * assigned to PTT KEY.
			 */
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
				
				press_PTT_Key();
				
				if (isElementExist(app)) {
					check2 = true;
					APP_LOGS.info("Assinged application launched succesfully upon pressing programmable key.");
				} else {
					APP_LOGS.info("Assinged application is not launched");
				}
				minWait();
				minWait();
				
				if((check1)&&(check2)){
					check=true;
					APP_LOGS.info("Test case passed");
					softAssert_true(check, "Pass");
				}else{
					APP_LOGS.info("Test case failed");
					Assert.fail();
				}aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			} catch (NoSuchElementException e) {				
				e.printStackTrace();
			}
		}
	 
	
	 public void validate_launchedApplicationYellowKey(WebElement app, WebElement app2,String summaryText) throws InterruptedException, IOException{
			/*
			 * Retrieves programmable Yellow key summary text of a particular application.
			*/
			minWait();
			try {
				//System.out.println(summaryText);
				if ( isElementExist(app)) {
					check1 = true;
					
					//String appName = Locators_ProgrammableKey.calculator_App.getText();
					APP_LOGS.info("App assigned to programmable Yellow key ");
					
				} else {
					APP_LOGS.info("Incorrect app summary text is displayed: ");
					
				}
				
				press_Yellow_Key();
				
				if (isElementExist(app2)) {
					check2 = true;
					APP_LOGS.info("Assinged application launched succesfully upon pressing programmable key.");
				} else {
					APP_LOGS.info("Assinged application is not launched");

				}
				if((check1)&&(check2)){
					check=true;
					APP_LOGS.info("Test case passed");
					softAssert_true(check, "Pass");
				}else{
					APP_LOGS.info("Test case failed");
					Assert.fail();
				}aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				
			} catch (NoSuchElementException e) {			
				e.printStackTrace();
		}
	}
	 
}
