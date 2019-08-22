package com.xp8.util;

import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class XP8_Contacts_Util extends BaseUtil {
	
	public boolean check = false;
	public SoftAssert softAssert;
	
	public void End() {
		aDriver.quit();
	}

	public void addContact() throws InterruptedException {
		 
		  //softAssert.assertTrue(isElementExist(Locators.name),"Element is present ");
		  Locators_Contacts.addContactBtn.click();
		  minWait();
//		  
		  minWait();
		  if(isElementExist( Locators_Contacts.OKBtn)){
			  Locators_Contacts.OKBtn.click();
			   minWait();
			   addField();
			  APP_LOGS.info("Default select PHONE is displayed, succesful.");
			 
			  }else{
			  
			   APP_LOGS.info("Default select PHONE is not displayed.");
			  }
		  
//		  Locators_Contacts.defaultSaveContactBtn.click();
		  //enterTextToInputField(Locators.searchfield,name);
		  /*if(isElementExist(Locators.name)) {
		   
		   APP_LOGS.info("Elemet is present ");
		   Locators.name.click();
		  }*/
		 }
	//Enter All the fileds while Adding Contacts
	public void addField() throws InterruptedException{
//		Locators_Contacts.contactNameField.click();
		minWait();
//		Locators_Contacts.expandNameField.click();
		minWait();
		enterTextToInputField(Locators_Contacts.nameField,"John");
		 minWait();
		enterTextToInputField(Locators_Contacts.phoneNumberField,"8899665520");
	}
	
}
