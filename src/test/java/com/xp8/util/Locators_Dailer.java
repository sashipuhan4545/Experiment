package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Dailer {

private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Dailer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Dismiss Phone.']"))
	public static AndroidElement DismissIcon;

//	 @FindBy(how=How.XPATH, using ="//android.widget.HorizontalScrollView[contains(@resource-id,'com.android.dialer:id/lists_pager_header')]")
//	 public static AndroidElement DailerPage;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.HorizontalScrollView\").resource-id(\"com.android.dialer:id/lists_pager_header\")")
	 public static AndroidElement DailerPage;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	 public static AndroidElement searchField;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.FrameLayout\").resource-id(\"com.android.dialer:id/search_view_container\")")
	 public static AndroidElement searchFieldTab;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.dialer:id/search_view')]")
	 public static AndroidElement searchTxtEnter;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.TextViewt[contains(@text,'Use touch tone keypad)]")
	 public static AndroidElement TouchToneKeypad;
	 
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.contacts:id/floating_action_button')]")
	 public static AndroidElement addContactBtn;
	
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='dial pad']"))
	 public static AndroidElement dailerPad;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Speed dial']"))
	 public static AndroidElement speedDail;
	 
	 @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id,'ADD A FAVORITE']")
	 public static AndroidElement addFavorite;
	 
	 @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id,'MAKE A CALL']")
	 public static AndroidElement makeCall;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Call History']"))
	 public static AndroidElement callHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@resource-id='com.android.dialer:id/digits']"))
	 public static AndroidElement enterNumFiled;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Contacts']"))
	 public static AndroidElement contactsIcon;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.ImageButton\").content-desc(\"Create new contact\")")
	 public static AndroidElement addContactsIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='UIDAI']"))
	 public static AndroidElement UidaiContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='dial']"))
	 public static AndroidElement dailIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[[@resource-id='com.android.dialer:id/callStateLabel']"))
	 public static AndroidElement callStateLabel;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']"))
	 public static AndroidElement CallIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/name']"))
	 public static AndroidElement dailedNum;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='End Call']"))
	 public static AndroidElement endCallIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/contact_tile_name']"))
	 public static AndroidElement contactTitle;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/name']"))
	 public static AndroidElement contactNameTitle;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Create new contact']"))
	 public static AndroidElement createNewContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Add to a contact']"))
	 public static AndroidElement AddtoContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.contacts:id/right_button']"))
	 public static AndroidElement OkBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	 public static AndroidElement OkBtnForClr;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	 public static AndroidElement OkBtnFrExport;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@text='John']"))
	 public static AndroidElement NameFieldJohn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@text='Name']"))
	 public static AndroidElement NameField;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='android:id/text1']"))
	 public static AndroidElement text1;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.CheckedTextView[@text='Work']"))
	 public static AndroidElement Work;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ListView[@index='1']/android.widget.LinearLayout[@index='0']"))
	 public static AndroidElement phoneDefault;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Save']"))
	 public static AndroidElement saveIcn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Automation']"))
	 public static AndroidElement saveContactName;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android.widget.Button\")")
	 public static AndroidElement saveExport;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Edit']"))
	 public static AndroidElement editContactIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	 public static AndroidElement MoreOptnsIcn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='More options']"))
	 public static AndroidElement MoreOptnCallHistry;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call History']"))
	 public static AndroidElement CallHistoryOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Settings']"))
	 public static AndroidElement settingsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Import/export']"))
	 public static AndroidElement ImptExprtOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='XP8800']"))
	 public static AndroidElement Xp8800Optn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Downloads']"))
	 public static AndroidElement DownloadsOptn;
 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Clear frequents']"))
	 public static AndroidElement clrFrquentsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Spinner[@resource-id='com.android.dialer:id/filter_status_spinner']"))
	 public static AndroidElement AllCallTypOptns;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Outgoing calls only']"))
	 public static AndroidElement outgoingCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Outgoing call']"))
	 public static AndroidElement outgoingCallTxt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='All calls']"))
	 public static AndroidElement AllCall;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Incoming calls only']"))
	 public static AndroidElement incomingCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Missed calls only']"))
	 public static AndroidElement missedCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Search call log']"))
	 public static AndroidElement searchCallLogs;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@text='Search call log']"))
	 public static AndroidElement editsearchCallLog;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Clear call history']"))
	 public static AndroidElement clearCallHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.contacts:id/call_log_list_item']"))
	 public static AndroidElement firstEntry;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view']"))
	 public static AndroidElement firstEntryCallHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/details_action']"))
	 public static AndroidElement callDetailsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call details']"))
	 public static AndroidElement CallDetailTxt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.contacts:id/selection_menu']"))
	 public static AndroidElement selectionMenu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.contacts:id/popup_list_title']"))
	 public static AndroidElement oneselectionMenu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Select all']"))
	 public static AndroidElement selectAllList;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='CLEAR']"))
	 public static AndroidElement clearBtn;

	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='No call log']"))
	 public static AndroidElement callLogEmpty;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='ALLOW']"))
	 public static AndroidElement AllowOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']"))
	 public static AndroidElement AllowOptnImprt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Block number']"))
	 public static AndroidElement blockNumOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Unblock number']"))
	 public static AndroidElement unblockNumOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='BLOCK']"))
	 public static AndroidElement blockBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='UNBLOCK']"))
	 public static AndroidElement unblockBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call screening']"))
	 public static AndroidElement callScreeningOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Block black list']"))
	 public static AndroidElement blockList;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Export to .vcf file']"))
	 public static AndroidElement exportVcf;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Export to SIM card']"))
	 public static AndroidElement exportSim;
	 
//	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Downloads']"))
//	 public static AndroidElement downloadsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='storage']"))
	 public static AndroidElement storageOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Download']"))
	 public static AndroidElement downloadOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Import from .vcf file']"))
	 public static AndroidElement importVcf;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Show roots']"))
	 public static AndroidElement menuStorageOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.android.documentsui:id/nameplate']"))
	 public static AndroidElement nameVcfCard;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation1']"))
	 public static AndroidElement Automation1Contact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation2']"))
	 public static AndroidElement Automation2Contact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation1']"))
	 public static AndroidElement AutomationContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	 public static AndroidElement deleteContactOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='DELETE']"))
	 public static AndroidElement deleteConfiemBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Unlock']"))
	 public static AndroidElement UnlockOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.systemui:id/keyguard_carrier_text']"))
	 public static AndroidElement destinationDrop;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Phone']"))
	 public static AndroidElement PhoneIcon;
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	 public static WebElement PhoneNumField;
}

