package com.xp8.util;

import java.util.List;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class New_SanityLocators {

	private static AndroidDriver<AndroidElement> aDriver;

	public New_SanityLocators(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
	public static AndroidElement turnOff_Airplane_PopUp;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add call\")")
	public static AndroidElement add_Call;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/floating_action_button' or @resource-id='com.google.android.dialer:id/fab']")
	public static AndroidElement dailerPad;
	
	 @AndroidFindBy(id="com.android.launcher3:id/all_apps_handle")
	 public static AndroidElement HomeBtnIcon;
	
	/*************************Calculator Element**********************************************************/
	
	 @AndroidFindBy(id="com.google.android.calculator:id/op_add")
	 public static AndroidElement calc_add_btn;
	 
	 @AndroidFindBy(id="com.google.android.calculator:id/op_sub")
	 public static AndroidElement calc_sub_btn;
	 
	 @AndroidFindBy(id="com.google.android.calculator:id/op_mul")
	 public static AndroidElement calc_Mul_btn;
	 
	 @AndroidFindBy(id="com.google.android.calculator:id/op_div")
	 public static AndroidElement calc_div_btn;
	 
	 @AndroidFindBy(id="com.google.android.calculator:id/eq")
	 public static AndroidElement calc_Eql_btn;
	
	 @AndroidFindBy(id = "com.google.android.calculator:id/del")
	 public static AndroidElement calc_Delete_btn;
	
	 @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
	 public static AndroidElement calc_Edit_text_field;
	
	 @AndroidFindBy(id = "com.google.android.calculator:id/clr")
	 public static AndroidElement calc_Clear_btn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Calendar']")
	public static AndroidElement calender;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement NetworkAndInternet_tc1;
	
	/****************** Camera Elements ********************************/
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/scene_mode_switcher")
	 public static AndroidElement camera_MenuBtn;
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/setting_button")
	 public static AndroidElement camera_SettingBtn;
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/video_button")
	 public static AndroidElement video_Button;
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/shutter_button")
	 public static AndroidElement capturePicture;
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/front_back_switcher")
	 public static AndroidElement front_back_switcher;
	
	@AndroidFindBy(id="org.codeaurora.snapcam:id/preview_thumb")
	 public static AndroidElement camera_previewThumb_btn;
	
	@AndroidFindBy(id="com.google.android.apps.photos:id/details")
	 public static AndroidElement camera_details_btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"/storage/emulated/0/DCIM/Camera\")")
	public static AndroidElement camera_imgName;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;  // This Locator is in Messaging.
	
	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/flash_button\")")
	public static AndroidElement flash_Button;

	
	/**************** Permissions **************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ALLOW\")")
	 public static AndroidElement Permission_AllowBtn;
	
	/****************File Manager *********************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"DCIM\")")
	 public static AndroidElement FileMgr_DCIMFldr;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Camera\")")
	 public static AndroidElement FileMgr_CameraFldr;
	
	/*****************************system Setting*********************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").description(\"Settings\")")
	public static AndroidElement SettingLnk;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").instance(0)")
	 public static AndroidElement systemStng_data_timeBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").instance(1)")
	 public static AndroidElement systemStng_time_ZoneBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(8)")
	 public static AndroidElement systemStng_time;
	
	/********************** Clock ********************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"to\").description(\"Alarm\")")
	public static AndroidElement clock_alarmIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"to\").description(\"Timer\")")
	public static AndroidElement clock_TimerIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"to\").description(\"Clock\")")
	public static AndroidElement clock_ClockIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"to\").description(\"Stopwatch\")")
	public static AndroidElement clock_StopWatchIcon;
	
	@AndroidFindBy(id="com.google.android.deskclock:id/digital_clock")
	 public static AndroidElement clock_clkTimevalidate;
	
	@AndroidFindBy(id="com.google.android.deskclock:id/timer_setup_time")
	public static AndroidElement clock_Timervalidate;
	
	@AndroidFindBy(id="com.google.android.deskclock:id/alarm_recycler_view")
	public static AndroidElement clock_Alarmvalidate;
	
	@AndroidFindBy(id="com.google.android.deskclock:id/stopwatch_circle")
	public static AndroidElement clock_StopWatchvalidate;
	
	/**********************SettingStorage******************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Used of 64 GB\")")
	 public static AndroidElement storage_totalSpace;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"17.13 GB\")")
	 public static AndroidElement storage_availableSpace;
	
	/**********************FMRadio****************************************/
	
	
	@AndroidFindBy(id="com.caf.fmradio:id/btn_onoff")
	 public static AndroidElement FM_ON_OFF_Swtch;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Please plug in a Headset to use FM Radio\")")
	 public static AndroidElement FM_PlugInMsg;
	
	@AndroidFindBy(id="com.caf.fmradio:id/prog_frequency_tv")
	 public static AndroidElement FM_frequency_Selecter;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"More options\")")
	public static AndroidElement FM_MoreOptBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Scan\")")
	 public static AndroidElement FM_scanBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"All Stations\")")
	 public static AndroidElement FM_scanAllStationsBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"All Channels\")")
	 public static AndroidElement FM_AllChannelsBtn;
	
	/*@AndroidFindBy(xpath="//android.widget.TableLayout/android.widget.TableRow[@index=1]/android.widget.TextView")
	public static list<AndroidElement> FM_AllChannelsLst;*/
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@instance=2]/android.widget.RelativeLayout[@index=2]/android.widget.TextView[@index=1]")
	 public static AndroidElement FM_frequency_SelecterTxt;
	
	/*********************Wifi*********************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").instance(7)")
	 public static AndroidElement Wifi_wifiLnk;
	
	@AndroidFindBy(id = "com.android.settings:id/switch_widget")
	public static AndroidElement Wifi_Switch_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement Wifi_PageLogo;
	
	@AndroidFindBy(id="com.android.settings:id/password")
	public static AndroidElement Wifi_PasswordTxt;
	
	@AndroidFindBy(id="android:id/edit")
	public static AndroidElement QuickStng_Edit;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"More options\")")
	public static AndroidElement QuickStng_EditMoreOptBtn;
	
	@AndroidFindBy(id="android:id/title")
	public static AndroidElement QuickStng_ResetBtn;
	
	@AndroidFindBy(xpath="//android.widget.Switch[@index=0]")
	public static AndroidElement QuickStng_WifiBtn;
	
	@AndroidFindBy(id="android:id/button1")
	public static AndroidElement Wifi_connectBtn;
	
	@AndroidFindBy(id="android:id/toggle")
	public static AndroidElement Wifi_ToggleSwitch;
	
	@AndroidFindBy(id="android:id/button1")
	public static AndroidElement Allow_Btn;
	
	
	/************Chrome*********************/
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='www.google.co.in']")
	public static AndroidElement googleCoIn_Text;
	
	@AndroidFindBy(xpath="//android.view.View[@text='Google offered in:']")
	public static AndroidElement google_Logo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
	public static AndroidElement ACCEPTCONTINUE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT;  //This Locator is in Chrome Breowser.	
	
	@AndroidFindBy(id = "com.android.chrome:id/negative_button")
	public static AndroidElement NO_THANKS;
	
	@AndroidFindBy(id = "com.android.chrome:id/negative_button")
	public static AndroidElement cancel;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Save data and browse faster\")")
	public static AndroidElement google_savedDataOpt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO THANKS\")")
	public static AndroidElement google_savedDataOpt_OkBtn;
	
	@AndroidFindBy(id="com.android.chrome:id/url_bar")
	public static AndroidElement google_urlBar;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Google apps\")")
	public static AndroidElement google_appsBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"Google offered in:\")")
	public static AndroidElement google_offeredInTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"Sign in\")")
	public static AndroidElement google_signinBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Alert\")")
	public static AndroidElement google_Alert_NoInternetOpt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"OK\")")
	public static AndroidElement google_Alert_okBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"No Internet\")")
	public static AndroidElement google_NoInternetOpt;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Cancel']")
	public static AndroidElement chrome_languageCancelBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Write your own\")")
	public static AndroidElement write_Your_Own;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SEND\")")
	public static AndroidElement SEND;
	
	
	
	
	/*********************Apps****************************/
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement AppListIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement browser_App;
	
	/****************** Bluetooth **********************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").index(2)")
	public static AndroidElement QuickStng_BlurtoothBtn;
	
	
	/******************** Display *********************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(2)")
	 public static AndroidElement display_BrightnessPercentTxt;
	
	/******************* Amazon *************************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Clear search keywords\")")
	public static AndroidElement amazon_SearchBx;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Amazon\")")
	public static AndroidElement amazon_logoTxt;
	
	/****************** yahoo *****************************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Search\")")
	public static AndroidElement yahoo_SearchBx;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Profile\")")
	public static AndroidElement yahoo_ProfileIcon;
	
	/****************faceBook ***************************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"facebook\")")
	public static AndroidElement facebook_LogoTxt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"English (UK)\")")
	public static AndroidElement facebook_SearchBx;
	
	/************** Flash Light ***********************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").index(4)")
	public static AndroidElement QuickStng_flashLightBtn;
	
	/**************** Message **************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"To\")")
	public static AndroidElement TO_Field;
	
	@AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	public static AndroidElement messageField;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/send_button_sms' or @resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_Icon;

	@AndroidFindBy(xpath="//*[contains(@text,'Now') or contains(@text,'Not sent') or contains(@text,'now') or contains(@text,'Just now')]")
	public static AndroidElement now_Text;	

	@AndroidFindBy(xpath="new UiSelector().className(\"android.widget.TextView\").textContains(\"Not sent\")")
	public static AndroidElement not_Sent_Text;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/avatar']/../..//*[@index='1']//*[@resource-id='com.android.mms:id/from']")
	public static AndroidElement firstSMS_InList;
	
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;
	
	@AndroidFindBy(xpath="//*[@text='DELETE' or @text='Delete']")
	public static AndroidElement delete_Confirm;
	
	/***************** Dialer Call **************************/

	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/digits' or @resource-id='com.google.android.dialer:id/digits']")
	public static AndroidElement enterNumFiled;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='dial']")
	public static AndroidElement dail;
	
	@AndroidFindBy(xpath="//*[@content-desc='End Call' or @content-desc='End call']")
	public static AndroidElement end_Call;
	
	@AndroidFindBy(xpath="//android.widget.HorizontalScrollView[@resource-id='com.android.dialer:id/lists_pager_header']/android.widget.LinearLayout/android.widget.RelativeLayout[@index=1]")
	public static AndroidElement dialer_callHistoryBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button' and @instance=3]")
	public static AndroidElement dialer_callHistory1stNobtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view' and @instance=5]")
	public static AndroidElement dialer_callHistory1stLnk;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_type' and @instance=3]")
	public static AndroidElement dialer_callDetails;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Call History') or contains(@text,'Recent')]")
	public static AndroidElement call_History;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/name' or @resource-id='com.google.android.dialer:id/name']")
	public static AndroidElement first_No_In_CallLog;
	
	@AndroidFindBy(xpath="//*[@text='Your call log is empty' or @text='Your call history is empty']")
	public static AndroidElement call_Log_Empty;
	
	@AndroidFindBy(xpath="//*[@text='Call History' or @text='Call history']")
	public static AndroidElement callHistory_MoreOptions;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement moreOptions_CallHistory;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static AndroidElement clear_CallHistory;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"OK\")") 
	public static AndroidElement OK_1;
	
	@AndroidFindBy(id="com.android.dialer:id/custom_sms_input")
	public static AndroidElement msg_popup;
	
	
	/***************** Testing Code *********************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"CIT Test\")")
	public static AndroidElement CitTestLnk;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Phone Version\")")
	public static AndroidElement Phone_Version;
	
	@AndroidFindBy(id="com.sonimtech.cit.verifier:id/model_info")
	public static AndroidElement phnVrsn_ModelNum;
	
	@AndroidFindBy(id="com.sonimtech.cit.verifier:id/build_num_info")
	public static AndroidElement phnVrsn_BuildNum;
	
	@AndroidFindBy(id="com.sonimtech.cit.verifier:id/baseband_ver_info")
	public static AndroidElement phnVrsn_BaseBand;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"registered\")")
	public static AndroidElement Phone_IMS_Register;
	
	
	/******************* About Phone *************************/
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=2]/android.widget.RelativeLayout/android.widget.TextView[@index=1]")
	public static AndroidElement Stng_phnVrsn_ModelNum;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=5]/android.widget.RelativeLayout/android.widget.TextView[@index=1]")
	public static AndroidElement Stng_Phone_Version;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=7]/android.widget.RelativeLayout/android.widget.TextView[@index=1]")
	public static AndroidElement Stng_phnVrsn_BuildNum;
	
	/******************** notifications ******************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Your phone supports AT&T Video Call.\")")
	public static AndroidElement At_T_videocallNotification;
	
	/******************** Date And Time *****************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(6)")
	public static AndroidElement date_time_dateTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(8)")
	public static AndroidElement date_time_timeTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(10)")
	public static AndroidElement date_time_timeZoneTxt;
	
	/************************* Battery *****************/ 
	
	@AndroidFindBy(id="com.android.settings:id/summary1")
	public static AndroidElement battery_;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(2)")
	public static AndroidElement battery_statusTxt;
	
	
	/****************** Network Setting ***************/
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=2]")
	public static AndroidElement ntwrkSettingLnk;
	
	@AndroidFindBy(xpath="//android.widget.Switch[@instance=1]")
	public static AndroidElement airplaneModeSwitch;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@resource-id='com.android.systemui:id/tile_page']/android.widget.Switch[@index=2]")
	public static AndroidElement QuickStng_AirplaneModeBtn;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@resource-id='com.android.systemui:id/tile_page']/android.widget.Switch[@index=1]")
	public static AndroidElement QuickStng_dataServicesBtn;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout[@index=1]")
	public static AndroidElement mobileNtwrk;
	
	@AndroidFindBy(id="com.google.android.setupwizard:id/welcome_next_button")
	public static AndroidElement airplaneTurnOff_notification;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Mobile network\")")
	public static AndroidElement checkMobilenetwork;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").instance(0)")
	 public static AndroidElement networkStng_mobileDataSwitch;
	
	@AndroidFindBy(id="android:id/icon")
	public static AndroidElement mobileNtwrk_triangleIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Phone\")")
	public static AndroidElement phone;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Airplane mode') and contains(@text,'Off')]")
	public static AndroidElement airplane_OffState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Airplane mode') and contains(@text,'On')]")
	public static AndroidElement airplane_OnState_QuickPanel_O;
	
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public static AndroidElement CANCEL;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement switch_On_State;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(2)")
	public static AndroidElement message_1stMsg;
	
	
	
	
	
	/*************** Engg Mode ************************/
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"GPS Test Application\")")
	public static AndroidElement EnggMode_gpsTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Testing\")")
	public static AndroidElement EnggMode_networkTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"UMTS Cell Status Report\")")
	public static AndroidElement EnggMode_enggTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"CIT\")")
	public static AndroidElement EnggMode_CitTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"LogCollector\")")
	public static AndroidElement EnggMode_loggerTxt;
	
	/****************** APN Window ************************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Name\")")
	public static AndroidElement APNname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")
	public static AndroidElement APNinput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN\")")
	public static AndroidElement APNno;
	
	@AndroidFindBy(id="New APN")
	public static AndroidElement Apn_addNewApnBtn;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@index=0]")
	public static List<AndroidElement> Apn_savedApnLst;
	
	@AndroidFindBy(id="More options")
	public static AndroidElement MoreOptionsBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete APN\")")
	public static AndroidElement APN_deleteOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Save\")")
	public static AndroidElement APN_SaveOpt;
	
	/**************** ATT url ************************/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Homepage\")")
	public static AndroidElement Att_homepageLogo;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.MenuItem\").text(\"Mail\")")
	public static AndroidElement Att_mailIcon;
	
	
	
	//////////////////// gobi sanity Locators //////////////////////
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Setup\")")
	public static AndroidElement ScoutSetup;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Utilities\")")
	public static AndroidElement ScoutUtilities;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Support\")")
	public static AndroidElement ScoutSupport;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")	
	public static AndroidElement Switch;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")	
	public static AndroidElement editPIN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement emailField;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your contacts list is empty']")
	public static AndroidElement No_Contacts;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete']"))
	public static AndroidElement deleteContactOptn1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static AndroidElement one_Selection_menu;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	public static AndroidElement OKBtn1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SIM\")")
	public static AndroidElement AccountSIMOPt;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.contacts:id/floating_action_button']")
	public static AndroidElement AddcontactBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement nameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement phoneField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement SaveBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().description(\"PHONE PHONE\")")
	public static AndroidElement AccountsPhoneOPt1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement FirstnameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement EmailField;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"More fields\")")
	public static AndroidElement Morefields ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Address\")")
	public static AndroidElement Addressfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Test 1\")")
	public static AndroidElement Test_1 ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.contacts:id/icon\")")	
	public static AndroidElement callBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/incall_second_button\")")	
	public static AndroidElement KeypadOPT;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/incall_end_call\")")	
	public static AndroidElement endBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.contacts:id/nav_all_contacts\")")	
	public static AndroidElement contactsBtn;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: Hulu Live TV Guide']")
	public static AndroidElement HuluApp1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1234567890sonim']")
	public static AndroidElement wifi_Dellas;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='dlink-F092-media']")
	public static AndroidElement wifi_Cannada;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Skip\")")
	public static AndroidElement skip_;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.vending:id/li_title\")")
	public static AndroidElement installed_Playstore1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.android.vending:id/flat_card_apps_mdp']/../..//android.widget.Button[@text='UNINSTALL']"))
	public static AndroidElement UNINSTALL1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/navigation_button\")")
	public static AndroidElement account_Page;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.music:id/search\")")
	public static AndroidElement searchOpt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search Google Play Music\")")
	public static AndroidElement Search1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.music:id/search_box_text_input\")")
	public static AndroidElement Search2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.music:id/li_thumbnail_frame\")")
	public static AndroidElement musicfile;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.music:id/fab_play\")")
	public static AndroidElement playBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.music:id/play_pause_header\")")
	public static AndroidElement play_pause_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SKIP\")")
	public static AndroidElement skipBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"GOT IT\")")
	public static AndroidElement gotitBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP\")")
	public static AndroidElement skipOtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Primary\")")
	public static AndroidElement PrimaryOtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search mail\")")
	public static AndroidElement Search_mail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement I_agreeOtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"MORE\")")
	public static AndroidElement MOREOtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Accept & continue\")")
	public static AndroidElement Accept_continuebtn;   
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CONTINUE\")")
	public static AndroidElement CONTINUEbtn;   
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"OK,GOT IT\")")
	public static AndroidElement OK_GOT_ITbtn;   
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement Nextbtn; 
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"No, thanks\")")
	public static AndroidElement No_thanksbtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"No Thanks\")")
	public static AndroidElement No_thanksbtn2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"https://www.bbc.com\")")
	public static AndroidElement bbcURL;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"bbc.com\")")
	public static AndroidElement bbcURL2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Homepage\")")
	public static AndroidElement Homepage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Not now\")")
	public static AndroidElement Notnow;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Back up & sync\")")
	public static AndroidElement Backup_sync1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SIGN IN TO BACK UP\")")
	public static AndroidElement Backup_sync2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Keep off\")")
	public static AndroidElement Keep_off;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement shutter_button;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/preview_thumb\")")
	public static AndroidElement preview_thumb;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static AndroidElement video_button;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.google.android.music:id/play_card\")")
	public static AndroidElement play_card;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"NOT NOW\")")
	public static AndroidElement NOT_NOWBtn;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Search')]")
	public static AndroidElement SearchOpn1;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Search']")
	public static AndroidElement SearchOpn2;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search YouTube\")")
	public static AndroidElement Search_YouTube;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.google.android.youtube:id/controls_layout\")")
	public static AndroidElement controls_layout;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(0)")
	public static AndroidElement Screen;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.youtube:id/fast_forward_rewind_triangles\")")
	public static AndroidElement Screen2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(7)")
	public static AndroidElement Full_Screen;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.youtube:id/fullscreen_button\")")
	public static AndroidElement Full_Screen2;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Enter fullscreen']")
	public static AndroidElement Full_Screen3;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Got it\")")
	public static AndroidElement GOT_it;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.calendar:id/floating_action_button\")")
	public static AndroidElement action_button;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").description(\"Nothing planned. Tap to create.\")")
	public static AndroidElement Tap_to_create1;
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc='Nothing planned. Tap to create.']")
	public static AndroidElement Tap_to_create2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Enter title\")")
	public static AndroidElement Enter_title;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.google.android.calendar:id/right_action\")")
	public static AndroidElement SwitchBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static AndroidElement permissionPopUp;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static AndroidElement allowBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Add location\")")
	public static AndroidElement Add_location;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Add note\")")
	public static AndroidElement Add_note;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Save\")")
	public static AndroidElement Save;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.calendar:id/speed_dial_icon\"[2])")
	public static AndroidElement EventBtn1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Event\")")
	public static AndroidElement EventBtn2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Test Event\")")
	public static AndroidElement Test_Event;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement More_options;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete\")")
	public static AndroidElement Delete2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement createBtn;
	
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement Type_message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/send_button_sms\")")
	public static AndroidElement sendBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now • SMS\")")
	public static AndroidElement justnow_Text;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending…']")
	public static AndroidElement sending_Txt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/recipients_picker\")")
	public static AndroidElement add_con_Btn;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Send\"]")
	public static AndroidElement sendbtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/navigation_button\")")
	public static AndroidElement more_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/main_nav_item\")")
	public static AndroidElement more_Btn2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Auto-update apps\")")
	public static AndroidElement Auto_update_apps;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RadioButton\").text(\"Don't auto-update apps\")")
	public static AndroidElement Dont_auto_update_apps;
	
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static AndroidElement Ok;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"OK\")") 
	public static AndroidElement OK_2;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[contains(@text,'@gmail.com')]"))
	public static AndroidElement connectedAccount;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove account\")")
	public static AndroidElement remove_Account;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement add_Account;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google_Account;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"identifierId\")")
	public static AndroidElement email_googleAcnt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement next;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REMOVE ACCOUNT\")")
	public static AndroidElement REMOVE_ACCOUNT;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement password_googleAcnt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Search Apps\")")
	public static AndroidElement searchApps;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Twitter\")")
	public static AndroidElement apkExtractor;
	
	@AndroidFindBy(xpath="//*[@text='ACCEPT' or @text='AGREE']")
	public static AndroidElement ACCEPTorAGREE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Store\")")
	public static AndroidElement PlayStore;
	
	@AndroidFindBy(xpath="//*[@text='DONE']")
	public static AndroidElement done_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search for apps & games\")")
	public static AndroidElement search_PlayStore2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/search_box_idle_text\")")
	public static AndroidElement google_Play;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search Google Play\")")
	public static AndroidElement search_PlayStore;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"UNINSTALL\")")
	public static AndroidElement UNINSTALL;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.vending:id/search_box_text_input\")")
	public static AndroidElement search_PlayStore1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"INSTALL\")")
	public static AndroidElement INSTALL;
	
	@AndroidFindBy(xpath="//*[@index='0' and contains(@text,'Apk Extract')]")
	public static AndroidElement firstApp_Suggetion;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"TAKE ME TO GMAIL\")")
	public static AndroidElement TAKE_ME_TO_GMAIL;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.calendar:id/right_arrow\")")
	public static AndroidElement right_arrow;
	
	@AndroidFindBy(uiAutomator=	"new UiSelector().className(\"android.view.View\").description(\"Test Event, All day: , Bengaluru\")")
	public static AndroidElement eventBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement Delete;
	
	@AndroidFindBy(xpath="//*[@text='Battery is full' or @text='Sonim AppKey Policy']")
	public static AndroidElement batteryFullorAppKey;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"key pad\")")
	public static AndroidElement keypad;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\")")
	public static AndroidElement enterNumber;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"dial\")")
	public static AndroidElement call;
	
	
	

	

	
	
	/////////////////// gobi sanity locators ended /////////////////
	
}