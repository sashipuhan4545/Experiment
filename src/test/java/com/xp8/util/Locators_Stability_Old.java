package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Stability_Old {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Stability_Old(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}

	//	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/empty_list_view\")")
	public static AndroidElement UidaiContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"ALL\")")
	public static AndroidElement ALL_page;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement longpress_FirstContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement AddtoContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static WebElement one_Selection_menu;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement MoreOptnsIcn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement Contacts_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement Contacts_Phone;


	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android:id/icon']")
	public static AndroidElement Choose_Phone;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement Contacts_Email;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_save\")")
	public static AndroidElement Save_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/large_title\")")
	public static WebElement ContactTitle;

	@AndroidFindBy(xpath = "//*[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonimtech@gmail.com\")")
	public static AndroidElement contact_Emailid;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static WebElement Search_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement findContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement SavedContact;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement AllowOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Call History']"))
	public static AndroidElement callHistoryImage;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call History']"))
	public static WebElement callHistory;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/../..//android.widget.ImageView[@resource-id='com.android.dialer:id/icon']"))
	public static AndroidElement callHistry_notification;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']"))
	public static AndroidElement  contactTest;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']/../..//android.widget.ImageView[@content-desc='Call Test1']"))
	public static AndroidElement Call_Contact;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='1234567890sonim']")
	public static AndroidElement wifi_Dellas;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='dlink-F092-media']")
	public static AndroidElement wifi_Cannada;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;

	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement MoreOptions;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh']")
	public static AndroidElement RefreshOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@content-desc,'Webpage not available')]")
	public static WebElement networkNotAvailable;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ACCEPT & CONTINUE')]")
	public static WebElement AccptBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NEXT')]")
	public static WebElement NextIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NO THANKS')]")
	public static WebElement NothanksBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/swipeableContent\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement firstSMS_InList;


	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement new_Message_Icon;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/create']")
	public static AndroidElement add_NewSMS;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement toField_NewMessage;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field;
	
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement messageField;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement type_Message_enter;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now • SMS\")")
	public static AndroidElement justnow_Text; //To validate after sending SMS.

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending…']")
	public static AndroidElement sending_Txt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Attach']")
	public static AndroidElement attach_icon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement captureImage_SMS;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	public static AndroidElement zero_Characters_FirstPage;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static WebElement cameraIcon;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Review done']")
	public static WebElement OK_btn_resultConfirmation_dialog;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	//			@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.mms:id/image_attachment_view']/../..//android.widget.ImageView[@resource-id='com.android.mms:id/image_content']"))
	public static AndroidElement image_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/video_thumbnail\")")
	public static AndroidElement video_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static WebElement videoCaptureIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static WebElement videoStopIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_pause\")")
	public static WebElement videopauseIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/delete\")")
	public static AndroidElement delete_Icon_SMS; //To validate after sending SMS.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/action_delete\")")
	public static AndroidElement delete_Icon; 

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"NEXT\")")
	public static AndroidElement NEXT_Msg;  // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;  // This Locator is in Messaging.

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
	public static AndroidElement send_Icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
	public static AndroidElement send_SMS;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_MMS_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text;

	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/cliv_name_textview']"))
	public static AndroidElement Phonebook_firstContact;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Speed dial']"))
	public static WebElement speedDail;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static WebElement Search_ConatctDailer;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter percentage of memory to fill']")
	public static AndroidElement enterFill_Memory;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Start Filling']")
	public static AndroidElement startFilling;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//android.widget.TextView[@text='FilledContent']"))
	public static AndroidElement filedContact_File;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//andrandroid.widget.ImageButton[@resoucre-id='com.cyanogenmod.filemanager:id/navigation_view_item_check']"))
	public static AndroidElement checkbox;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_actionbar']/../..//android.widget.ImageButton[@resource-id='com.cyanogenmod.filemanager:id/ab_search']"))
	public static WebElement SearchOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Type your search')]")
	public static WebElement SearchTxt;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/../..//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement Allow_FileExplorer;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='add new contact']")
	public static AndroidElement add_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name']")
	public static AndroidElement name_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static AndroidElement phone_NewContact;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Saving to']")
	public static AndroidElement contact_SavingTo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement savingTo_Phone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Save']")
	public static AndroidElement save_Icon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement cellularData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement cellularData_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement mobileData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement mobileData_OffState;
	
	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;



}
