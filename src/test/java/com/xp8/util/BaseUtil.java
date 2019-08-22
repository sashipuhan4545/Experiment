package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.library.AndroidWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class BaseUtil extends CommonConfig 
{
	//public static AndroidDriver AndroidDriverWait;
	public static ExtentReports extent;
	public static ExtentTest test;

	public void clearRecentApps() throws InterruptedException, IOException {			 			
		minWait();
		Runtime.getRuntime().exec("adb  shell input keyevent 3"); 
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
		minWait();	 	
		if(isElementExist(Locators_BaseUtil.no_Recent_Items)) {
			APP_LOGS.info("No Recent Applications Present");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
		}else {
			while(true) {
				if(isElementExist(Locators_BaseUtil.clear_all)) {					
					Locators_BaseUtil.clear_all.click();
					break;
				}
				aDriver.swipe(600, 300, 600, 1400, 250);
				minWait();						 					
			}
		}		 					 	
	}

	public void clearAllow() throws InterruptedException {
		try {
			minWait();
			while(isElementExist(Locators_BaseUtil.allow_PopUp)) {
				clickBtn(Locators_BaseUtil.allow_PopUp);
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	

	public  void executeShellCommands(int input,String URL,String pkgName,String activityName ) throws IOException {

		switch(input) {		  
		case 1:
			String cmd1 = "adb shell am start -a android.intent.action.VIEW -d "+URL+"";
			Process p1 = Runtime.getRuntime().exec(cmd1);
			break;

		case 2:
			String  cmd2 = "adb shell am start -n "+pkgName+"/"+activityName+"";
			Process p2 = Runtime.getRuntime().exec(cmd2); 
			break;
		}		
	}	


	public boolean ifAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public void executeJS(String code) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(code, "");
	}

	public void clickOnElementUsingJS(WebElement e) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", e);
	}

	public boolean clickBtn(WebElement e) {
		try {
			e.click();
			return true;
		} catch (NoSuchElementException s) {
			return false;
		}
	}

	public boolean clickBtn(AndroidElement e) {
		try {
			e.click();
			return true;
		} catch (NoSuchElementException s) {
			return false;
		}
	}

	public boolean longpress(int keyCode){
		try {
			aDriver.longPressKeyCode(keyCode);
			return true;
		} catch (NoSuchElementException s) {
			return false;
		}
	}

	public void launchAppThroughABD(String adbCommand) throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec(adbCommand);
		minWait();		
	}

	public void launchApp(String pkgName, String actName){

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/properties/objects.properties"));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String pkgN = properties.getProperty(pkgName);
		String actN = properties.getProperty(actName);

		aDriver.startActivity(pkgN, actN);

	}

	public void selectCheckbox(WebElement e) {
		if (!e.isSelected()) {
			e.click();
		}
	}

	public void selectCheckbox(AndroidElement e) {
		if (!e.isSelected()) {
			e.click();
		}
	}

	public void deSelectCheckbox(WebElement e) {
		if (e.isSelected()) {
			e.click();
		}
	}

	public void deSelectCheckbox(AndroidElement e) {
		if (e.isSelected()) {
			e.click();
		}
	}	

	public boolean enterTextToInputField(WebElement e, String text) throws InterruptedException {
		if (e == null) {
			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(300);
				e.clear();
				e.click();
				Thread.sleep(300);
				e.sendKeys(text);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "+ e);
				return false;
			}
		}
	}

	public boolean enterTextToInputField(AndroidElement e, String text) throws InterruptedException {
		if (e == null) {
			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(300);
				
				e.click();
				e.clear();
				e.sendKeys(text); 
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAIL: Element not found or displayed: Object Locator : "+ e);
				return false;
			}
		}
	}

	public boolean clearInputField(WebElement e) throws InterruptedException {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(500);
				e.clear();
				Thread.sleep(500);
				APP_LOGS.info("PASS: Element found and cleared the value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}

	public boolean clearInputField(AndroidElement e) throws InterruptedException {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(500);
				e.clear();
				Thread.sleep(500);
				APP_LOGS.info("PASS: Element found and cleared the value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}


	public boolean enterKeysToInputField(WebElement e, Keys operation) {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				e.clear();
				e.sendKeys(operation);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}

	public boolean enterKeysToInputField(AndroidElement e, Keys operation) {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				e.clear();
				e.sendKeys(operation);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}

	public boolean hoverOverElement(WebElement el) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(el).build().perform();
		return false;
	}

	public boolean hoverOverElement(AndroidElement el) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(el).build().perform();
		return false;
	}

	public String getCurrentTimeStamp() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return format.format(new Date()).toString();
	}

	public void selectDropDownByValue(WebElement e, String value) {

		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public void selectDropDownByValue(AndroidElement e, String value) {

		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public void selectDropDownByText(WebElement e, String text) {

		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(text);
	}

	public void selectDropDownByText(AndroidElement e, String text) {

		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(text);
	}

	public void selectDropDownsByText(WebElement e, String text) {
		Select dropdown = new Select(e);
		if (dropdown.isMultiple()) 
		{
			dropdown.deselectAll();
			dropdown.selectByVisibleText(text);

		}
		else 
		{
			dropdown.selectByVisibleText(text);
		}	
	}

	public void selectDropDownsByText(AndroidElement e, String text) {
		Select dropdown = new Select(e);
		if (dropdown.isMultiple()) 
		{
			dropdown.deselectAll();
			dropdown.selectByVisibleText(text);

		}
		else 
		{
			dropdown.selectByVisibleText(text);
		}	
	}


	public void selectDropDownValueByIndex(WebElement e, int index) {
		Select dropdown = new Select(e);
		dropdown.selectByIndex(index);
	}

	public void selectDropDownValueByIndex(AndroidElement e, int index) {
		Select dropdown = new Select(e);
		dropdown.selectByIndex(index);
	}

	public String getSelectedDropDownOptionText(WebElement e) {
		Select dropdown = new Select(e);
		return dropdown.getFirstSelectedOption().getText();
	}

	public String getSelectedDropDownOptionText(AndroidElement e) {
		Select dropdown = new Select(e);
		return dropdown.getFirstSelectedOption().getText();
	}

	public boolean isElementPresent(String csslocator) {
		if (getDriver().findElements(By.cssSelector(csslocator)).size() == 0)
			return false;
		else
			return true;
	}

	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;

		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public Alert getAlertbox() {

		try {
			return getDriver().switchTo().alert();

		} catch (NoAlertPresentException ex) {
			return null;
		}
	}

	public void waituntilnew(WebElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void waituntilnew(AndroidElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));
		//wait.until(equals(aDriver.switchTo().alert().accept()));

	}

	public void waituntilnewElementtobeClickable(WebElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}

	public void waituntilnewElementtobeClickable(AndroidElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}


	public void dragAndDrop(WebElement source, WebElement target) {
		(new Actions(getDriver())).dragAndDrop(source, target).perform();
	}

	public void dragAndDrop(AndroidElement source, AndroidElement target) {
		(new Actions(getDriver())).dragAndDrop(source, target).perform();
	}


	public boolean isElementExist(WebElement e) {
		boolean isPresent = false;
		try {
			isPresent = e.isDisplayed();
		} catch (NoSuchElementException nse) {
			isPresent = false;
		} catch (NullPointerException npe) {
			isPresent = false;
		}
		return isPresent;
	}
	public boolean isElementExist(AndroidElement e) {
		boolean isPresent = false;
		try {
			isPresent = e.isDisplayed();
		} catch (NoSuchElementException nse) {
			isPresent = false;
		} catch (NullPointerException npe) {
			isPresent = false;
		}
		return isPresent;
	}

	public boolean isElementNull(WebElement e) {
		boolean isNull = false;
		if (e.getText() == null) {
			isNull = true;
		}
		return isNull;
	}

	public boolean isElementNull(AndroidElement e) {
		boolean isNull = false;
		if (e.getText() == null) {
			isNull = true;
		}
		return isNull;
	}

	public void maxWait() throws InterruptedException{
		Thread.sleep(12000);

	}
	public void minWait() throws InterruptedException{
		Thread.sleep(1500);
	}

	public static void customWait(long secs) throws InterruptedException{
		Thread.sleep(secs) ;
	}

	public void softAssert_true(boolean check, String message){
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(check, message); 
	}

	public void softAssert_false(){
		SoftAssert softAssertFail = new SoftAssert();
		softAssertFail.fail();
	}		 

	public void softAssert_equals(String actual, String expected){
		SoftAssert softAssertFail = new SoftAssert();
		softAssertFail.assertEquals(actual, expected);
	}


	public void scrollAlittle() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)aDriver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		minWait();
	}


	/*public void scrollUp() throws InterruptedException{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
		minWait();
	}*/


	public void scrollToElement(WebElement e) throws InterruptedException{
		minWait();
		WebElement element = e;
		((JavascriptExecutor) aDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		minWait();
	}

	public void scrollToElement(AndroidElement e) throws InterruptedException{
		minWait();
		WebElement element = e;
		((JavascriptExecutor) aDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		minWait();
	}

	public void javaScriptScroll(){
		JavascriptExecutor jse = (JavascriptExecutor)aDriver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}


	public static void startAdbLog(String fileName) throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";

		try {
			Runtime.getRuntime().exec("adb logcat -c");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>"+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void stopAdbLog(String TCName) throws InterruptedException, AWTException, IOException {
		Thread.sleep(1000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		/*
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_C);
		 */
		APP_LOGS.info(TCName+" : Adb logs stopped succesfully. ");
	}	

	public static String captureScreenshot(String methodName) throws IOException{

		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String mailscreenshotpath = null;

		File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			mailscreenshotpath = System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+methodName+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpg";
			FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailscreenshotpath;
	}

	public void minWait_SonimCare() throws InterruptedException{
		Thread.sleep(2500);
	}

	public boolean searchString(String searchstring, String fileName) {

		boolean check = false;
		try { 
			BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt"));
			int linecount = 0;
			String line;
			while (( line = bf.readLine()) != null){

				boolean indexfound = line.contains(searchstring);
				if (indexfound) {
					check=true;
					APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
					break;
				}  
				else {
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

			
			
	

	public void recordVideo(String TC_Name) throws InterruptedException, IOException{
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		minWait();
		Runtime.getRuntime().exec("cmd /C \"adb  -s "+Uid+" shell screenrecord mnt/sdcard/"+TC_Name+".mp4 \"");
		customWait(2000);
	}

/*	public void acceptGoogleSecurity() throws InterruptedException {
		if(isElementExist(Locators_SonimCare.AcceptBtn)) {
			minWait();
			Locators_SonimCare.AcceptBtn.click();		   
		}
	}*/
	
	
	public void enableFeature(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
		/*
		 * This Method is to Enable Settings Feature"
		 */
		try {
			minWait();
			if(isElementExist(switchtitle)) {
				if (!isElementExist(enablebtn)) {
					minWait();
					clickBtn(disablebtn);
					APP_LOGS.info("Feature is Enabled");                     
					minWait();
				}
			}
			else {
//				test.log(LogStatus.ERROR, "Toggle button is disabled");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void disableFeature(WebElement enablebtn, WebElement disablebtn) throws InterruptedException {
		/*
		 * This Method is to Disable Settings Feature"
		 */
		try {
			minWait();
			if (!isElementExist(disablebtn)) {
				minWait();
				clickBtn(enablebtn);
				APP_LOGS.info("Featureis disabled");
				minWait();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Disable toggle : No Such Element Found");
			Assert.fail();
		}
	}
	
	
	 public static String fetchDeviceProperty(String cmd) {

			//  String cmd = "adb shell getprop gsm.version.baseband";
			String value = null;
			try {

				Process child = Runtime.getRuntime().exec(cmd);
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				System.out.println(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return value;

		}
	
	public void checkSimCardAvailability() throws IOException, InterruptedException {
		/*
		 * Check SIM card is inserted using Adb shell command
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		String XP8SIMcard =BaseUtil.fetchDeviceProperty("adb -s "+Uid+" shell getprop gsm.sim.operator.alpha");
		minWait();
		if(!XP8SIMcard.equals("")) {
			APP_LOGS.info("SIM Card is Available "+ XP8SIMcard);
		}
		else {
			test.log(LogStatus.SKIP, "SIM card is not inserted");
//			Assert.fail();
		}
	}

	@SuppressWarnings("rawtypes")
	public static String image(AndroidDriver<AndroidElement> driver) {

		File targetFile = null;
		try {
			File scrFile = driver.getScreenshotAs(OutputType.FILE);
			String fileName = UUID.randomUUID().toString();
			targetFile = new File(System.getProperty("user.dir")+"//toastScreenshots//"+fileName+".png");
			FileUtils.copyFile(scrFile, targetFile);		   
			System.out.println(targetFile.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}	   
		return targetFile.toString();	   
	}

	public static String OCR(String imagePath) {

		String result = null;
		File imageFile = new File(imagePath);
		ITesseract instance = new Tesseract();
		instance.setDatapath(System.getProperty("user.dir")+"\\tessdata");
		try {
			result = instance.doOCR(imageFile);
		} catch (TesseractException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean scrollToText(String text) {
		  /*
		    Method used to select an element on the page by scrolling the Scroll View/List View
		   */

		  boolean check = false;
		  try {  
		   String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
		   String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
		   aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
		   APP_LOGS.info("Searched application is found sucessfully : ");
		   check = true;
		   return check;
		  }
		  catch(NoSuchElementException e) {
		   return check;
		  }
		 }
	
	public String Verify(AndroidDriver<AndroidElement> driver) {

		String result = null;
		File scrFile = driver.getScreenshotAs(OutputType.FILE);
		ITesseract instance = new Tesseract();
		try {
			result = instance.doOCR(scrFile);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}



	static String scrShotDir = "screenshots";
	File scrFile;
	static File scrShotDirPath = new java.io.File(System.getProperty("user.dir")+"//"+ scrShotDir+ "//");
	String destFile;
	// static AndroidDriver driver = null;


	public String readToastMessage() throws TesseractException {

		String imgName = takeScreenShot();
		System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");


		String result = null;
		File imageFile = new File(scrShotDirPath, imgName);
		System.out.println("Image name is :" + imageFile.toString());
		ITesseract instance = new Tesseract();
		//		   ITesseract instance1 = new Tesseract1();
		//	   ITesseract instance = Tesseract.getInstance();
		File tessDataFolder = LoadLibs.extractTessResources(System.getProperty("user.dir")+"\\tessdata"); // Extracts
		// Tessdata
		// folder
		// from
		// referenced
		// tess4j
		// jar
		// for
		// language
		// support
		instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData
		// path

		result = instance.doOCR(imageFile);
		//	   System.out.println(result);
		return result;
	}

	/**
	 * Takes screenshot of active screen
	 * 
	 * @return ImageFileName
	 */
	public String takeScreenShot() {

		scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE); 

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		new File(scrShotDir).mkdirs(); // Create folder under project with name
		// "screenshots" if doesn't exist
		destFile = dateFormat.format(new Date()) + ".png"; // Set file name
		// using current
		// date time.
		try {
			FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy
			// paste
			// file
			// at
			// destination
			// folder
			// location
		} catch (IOException e) {
			APP_LOGS.info("Image not transfered to screenshot folder");
			e.printStackTrace();
		}
		return destFile;
	}

	public void scroll() {
		try {
			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			System.out.println(size);
			int x=size.getWidth()/2;
			int starty=(int)(size.getHeight()*0.60);
			int endy=(int)(size.getHeight()*0.10);
			aDriver.swipe(x, starty, x, endy, 2000);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
	
	public boolean scrollTo(String phoneNum) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */

		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ phoneNum +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}
	
	public boolean scrollToTextContains(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}


	public void scrollToElements(WebElement e) throws InterruptedException {
		minWait();
		/*  WebElement element = e;
		   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);*/

		for(int i=1; i<=4;i++)
		{
			if(isElementExist(e)) {
				customWait(2000);
				break;
			}
			else {
				scroll();
				continue;
			}
		}
	}

	public void launch_an_app(String appName) throws InterruptedException {
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		APP_LOGS.info("HOme PAge");
		customWait(3000);
		Locators_BaseUtil.AppListIcon.click();
		customWait(1000);
		switch (appName) {
		case "browser":
			scrollToElements(Locators_BaseUtil.browser_App);
			clickBtn(Locators_BaseUtil.browser_App);
			APP_LOGS.info("Clicked on Browser successfully.");

			break;

		case "contacts":
			scrollToElements(Locators_BaseUtil.contacts);
			clickBtn(Locators_BaseUtil.contacts);
			APP_LOGS.info("Clicked on Contact successfully.");
			break;

		case "phone":

			scrollToElements(Locators_BaseUtil.phone_DailerApp);
			clickBtn(Locators_BaseUtil.phone_DailerApp);
			APP_LOGS.info("Clicked on Phone successfully.");
			break;

		case "scout":
			scrollToElements(Locators_BaseUtil.scoutApp_icon);
			clickBtn(Locators_BaseUtil.scoutApp_icon);
			APP_LOGS.info("Clicked on ScoutApps successfully.");
			break;

		case "music":
			scrollToElements(Locators_BaseUtil.music_icon);
			minWait();
			clickBtn(Locators_BaseUtil.music_icon);
			APP_LOGS.info("Clicked on Music successfully.");
			break;

		case "fm":
			scrollToElements(Locators_BaseUtil.FM_App);
			minWait();
			clickBtn(Locators_BaseUtil.FM_App);
			APP_LOGS.info("Clicked on FM Radio successfully.");
			break;

		case "fileExplorer":
			scrollToElements(Locators_BaseUtil.FileExplorer_App);
			minWait();
			clickBtn(Locators_BaseUtil.FileExplorer_App);
			APP_LOGS.info("Clicked on FileExplorer_App successfully.");
			break;

		case "backUpReset":
			scrollToElements(Locators_BaseUtil.backUP_reset);
			minWait();
			clickBtn(Locators_BaseUtil.backUP_reset);
			APP_LOGS.info("Clicked on backUP_reset successfully.");
			break;

		case "downloads":
			scrollToElements(Locators_BaseUtil.downloads_icon);
			minWait();
			clickBtn(Locators_BaseUtil.downloads_icon);
			APP_LOGS.info("Clicked on downloads_icon successfully.");
			break;

		case "settings":
			scrollToElements(Locators_BaseUtil.settings);
			clickBtn(Locators_BaseUtil.settings);
			APP_LOGS.info("Clicked on settings successfully.");
			break;

		case "camera":
			scrollToElements(Locators_BaseUtil.cameraApp);
			clickBtn(Locators_BaseUtil.cameraApp);
			APP_LOGS.info("Clicked on cameraApp successfully.");
			break;

		case "calendar":
			scrollToElements(Locators_BaseUtil.CalenderApp);
			clickBtn(Locators_BaseUtil.CalenderApp);
			APP_LOGS.info("Clicked on CalendarApp successfully.");
			break;

		case "clock":
			scrollToElements(Locators_BaseUtil.clock_Icon);
			minWait();
			clickBtn(Locators_BaseUtil.clock_Icon);
			APP_LOGS.info("Clicked on clock_Icon successfully.");
			break;

		case "calculator":
			scrollToElements(Locators_BaseUtil.calciApp);
			minWait();
			clickBtn(Locators_BaseUtil.calciApp);
			APP_LOGS.info("Clicked on Calculator successfully.");
			break;

		case "soundRecorder":
			scrollToElements(Locators_BaseUtil.RecorderApp);
			minWait();
			clickBtn(Locators_BaseUtil.RecorderApp);
			APP_LOGS.info("Clicked on RecorderApp successfully.");
			break;

		case "gallery":
			scrollToElements(Locators_BaseUtil.gallery_icon);
			clickBtn(Locators_BaseUtil.gallery_icon);
			APP_LOGS.info("Clicked on gallery_icon successfully.");
			break;

		case "messaging":
			System.out.println("Im in MSg");
			scrollToElements(Locators_BaseUtil.messaging_icon);
			
			if(isElementExist(Locators_BaseUtil.messaging_icon)) {
				clickBtn(Locators_BaseUtil.messaging_icon);
			}
			else if(isElementExist(Locators_BaseUtil.MessagePlus)) {
				clickBtn(Locators_BaseUtil.MessagePlus);
			}
			else {
				clickBtn(Locators_BaseUtil.message_icon);
			}

			APP_LOGS.info("Clicked on messaging_icon successfully.");
			break;

		case "fillmemory":
			scrollToElements(Locators_BaseUtil.fillmemoryIcon);

			clickBtn(Locators_BaseUtil.fillmemoryIcon);

			APP_LOGS.info("Clicked on FillMemory_icon successfully.");
			break;
			
		case "gmail":
			scrollToElements(Locators_BaseUtil.gmail);

			clickBtn(Locators_BaseUtil.gmail);

			APP_LOGS.info("Clicked on Gmail_Icon successfully.");
			break;
		}

	}
	
	
	public void enableSwitch(WebElement enablebtn, WebElement disablebtn, WebElement switchwidget) throws InterruptedException {
		/*
		 * Enable Switch widget or toggle button
		 */
		try {
		minWait();
		
			if (!isElementExist(enablebtn)) {
				minWait();
				clickBtn(disablebtn);
				APP_LOGS.info("Switch is Enabled");                     
				minWait();
			}
		
		else {
			System.out.println("Not working");
		
		}
	} catch (NoSuchElementException e) {
		e.printStackTrace();
//		test.log(LogStatus.ERROR, "No Such Element Found");
		Assert.fail();
	}
	}
	
	public void disableSwitch(WebElement disablebtn, WebElement enablebtn, WebElement switchwidget) throws InterruptedException {
		/*
		 * Enable Switch widget or toggle button
		 */
		try {
		minWait();
	
			if (!isElementExist(disablebtn)) {
				minWait();
				clickBtn(enablebtn);
				APP_LOGS.info("Switch is Disabled");                     
				minWait();
			}
		
		else {
//			test.log(LogStatus.ERROR, "Toggle button is enabled");
			Assert.fail();
		}
	} catch (NoSuchElementException e) {
		e.printStackTrace();
//		test.log(LogStatus.ERROR, "No Such Element Found");
		Assert.fail();
	}
	}

	public String startRIL_Log() throws AWTException, InterruptedException, IOException {

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+Uid+" logcat -c");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -b all -v threadtime>\""+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
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
		
		public String fetchOperatorName() throws FileNotFoundException, IOException, ParseException {
//			product_DUT.setText("XP5800");

			if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {



				return("ATT"); 
			}

			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

				return("Rogers"); 

			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

				return("Bell"); 

			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

				return("Telus"); 

			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {


				return("Sprint"); 
			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {


				return("SL"); 
			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-32.")) {

				return("ACG"); 

			}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34.")) {

				return("USC"); 

			}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

				return("Verizon"); 

			}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00")) {

				return("MainLine"); 
			}
			return "GEN";

		}
		
		
		
		public static String getCurrentDate() {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			return dateFormat.format(date);
		}
		
		public static String getCurrentTime() {
			
			  Date date = new Date();
			    String strDateFormat = "hh-mm-ss";
			    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			    String formattedDate= dateFormat.format(date);
			    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
			return formattedDate;
			
		}
		public AndroidElement multi_Loc_Strategy(AndroidElement e1,AndroidElement e2,AndroidElement e3,AndroidElement e4,AndroidElement e5,int xCordinate,int yCordinate) {
			/*
			 * It takes 5 locators and choose which is Present
			 */
			AndroidElement isElementPresentOntheDeviceScreen = null;
			
			if (isElementExist(e1)) {
				System.out.println("Locator 1 is found");
			isElementPresentOntheDeviceScreen=e1;
			}else if (isElementExist(e2)) {
				System.out.println("Locator 2 is found");
			isElementPresentOntheDeviceScreen=e2;
			}else if (isElementExist(e3)) {
				System.out.println("Locator 3 is found");
			isElementPresentOntheDeviceScreen=e3;
			}else if (isElementExist(e4)) {
				System.out.println("Locator 4 is found");
			isElementPresentOntheDeviceScreen=e4;

			}else if (e5.isDisplayed()) {
				System.out.println("Locator 5 is found");
			isElementPresentOntheDeviceScreen=e5;

			}else {
				System.out.println("No locator using Coordinates");
			TouchAction tap=new TouchAction(aDriver);
			tap.tap(xCordinate, yCordinate).perform();

			}

			return isElementPresentOntheDeviceScreen;



			}
		

}