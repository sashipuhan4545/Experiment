package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_ContactTransfer {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_ContactTransfer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Scout.']")
	public static WebElement DismissIconCare;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static WebElement AppListIcon;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='ACCEPT']")
	public static WebElement AcceptBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='CLEAR ALL']"))
	public static WebElement ClearAll;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferApp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/bluetooth_rb\")")
	public static WebElement BtRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/mdb_rb\")")
	public static WebElement MDBRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/vcf_rb\")")
	public static WebElement VCFRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/mdb_rb\")")
	public static WebElement CSVRadioBtn;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Warranty Registration.']")
	public static WebElement DismissIconWarranty;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	public static WebElement CT_MoreOptns;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About']")
	public static WebElement AbtContactTransfer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/message']")
	public static WebElement About_CT_Text;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public static WebElement OKBtn_Abt;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static WebElement AllowBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public static WebElement Allow_BT_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via Bluetooth\")")
	public static WebElement BluetoothOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via MDB\")")
	public static WebElement MDBOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via VCF\")")
	public static WebElement VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via CSV\")")
	public static WebElement CSVOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Download']")
	public static WebElement DownloadOptn;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.sonimtech.easycontactsshare:id/btn_ok']")
	public static WebElement OKBtn;

	@AndroidFindBy(xpath ="//android.widget.CheckBox[@resource-id='com.sonimtech.easycontactsshare:id/select_all_check']")
	public static WebElement selectAllOptn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonimtech.easycontactsshare:id/textSeparator']")
	public static WebElement ImprtSummry_Text;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='DONE']")
	public static WebElement DoneOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Completed contact import']")
	public static WebElement NotifctnTitle;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='CANCEL']")
	public static WebElement CancelBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btn_ok\")")
	public static WebElement search_Ok__Btn;


	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import MDB Contacts']")
	public static WebElement ImportMBDOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import VCF Contacts']")
	public static WebElement ImportVCFOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import CSV Contacts']")
	public static WebElement ImportCSVOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import via Bluetooth']")
	public static WebElement ImportBTOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/search_src_text\")")
	public static WebElement searchField;

	@AndroidFindBy(xpath ="//android.widget.RelativeLayout[@resource-id='com.sonimtech.easycontactsshare:id/view_parent']")
	public static WebElement SearchResult;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.CheckBox[@index='2']"))
	public static WebElement CheckBox_Search;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='2']"))
	public static WebElement UFMI_Search;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectMdbFile\")")
	public static WebElement Title_MBDOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectVcfFile\")")
	public static WebElement Title_VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectCsvFile\")")
	public static WebElement Title_CSVOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnBtImportContacts\")")
	public static WebElement Title_BTOptn;

}
