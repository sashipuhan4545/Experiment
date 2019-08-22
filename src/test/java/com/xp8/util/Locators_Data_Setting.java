package com.xp8.util;

import org.jsoup.select.Evaluator.AllElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import android.R.anim;
import bsh.This;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Data_Setting {
	
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Data_Setting(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkandinternetopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile network\")")
	public static AndroidElement Mobilenetworkopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Airplane mode\")")
	public static AndroidElement airplanemode;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\")")
	public static AndroidElement airplanemodeswitchonoff;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
	public static AndroidElement wifiswitchonoff;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile data\")")
	public static AndroidElement mobiledata;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement wifiopt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Advanced\")")
	public static AndroidElement Advancedopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network settings\")")
	public static AndroidElement networksettingbar;
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	public static AndroidElement Apnclickbck;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Enhanced 4G LTE services\")")
	public static AndroidElement lteservice;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static WebElement lte_radiobtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Access Point Names\")")
	public static AndroidElement APNopt;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Edit access point\")")
	public static AndroidElement editapopt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Vodafone connect\")")
	public static AndroidElement VodafoneConnect;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement APNSettings;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/apn_radiobutton\")")
	public static WebElement radiobtn;

	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='New APN']")
	public static WebElement NewAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APNs\")")
	public static AndroidElement apnsframe;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Vodafone connect\")")
	public static AndroidElement vodafoneconnect;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
	public static AndroidElement okbtn;
	//CANCEL
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement apncancelbtn;
	
	
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement newAPNSettings;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reset to default\")")
	public static AndroidElement resettodefaul;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Save\")")
	public static AndroidElement savebtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Discard\")")
	public static AndroidElement discardbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Name\")")
	public static AndroidElement APNname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"The Name field can’t be empty.\")")
	public static AndroidElement errormsginname;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"The APN can’t be empty.\")")
	public static AndroidElement errormsginapn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN\")")
	public static AndroidElement APNno;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")
	public static AndroidElement APNinput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim\")")
	public static AndroidElement newapnnum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonimedit\")")
	public static AndroidElement editapnnum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim1\")")
	public static AndroidElement sonim1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim2\")")
	public static AndroidElement sonim2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim3\")")
	public static AndroidElement sonim3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim4\")")
	public static AndroidElement sonim4;
	
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete APN\")")
	public static AndroidElement deleteapnnum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Proxy\")")
	public static AndroidElement proxyAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Port\")")
	public static AndroidElement portAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Username\")")
	public static AndroidElement usernameAPN;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Password\")")
	public static AndroidElement PasswordAPN;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Server\")")
	public static AndroidElement ServerAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMSC\")")
	public static AndroidElement MMSCAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMS proxy\")")
	public static AndroidElement MMSproxyAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMS port\")")
	public static AndroidElement MMSport;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MCC\")")
	public static AndroidElement MCCAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MNC\")")
	public static AndroidElement MNCAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN protocol\")")
	public static AndroidElement APNprotool;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN roaming protocol\")")
	public static AndroidElement APNroamingprotool;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4\")")
	public static AndroidElement ipv4r;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv6\")")
	public static AndroidElement ipv6r;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4/IPv6\")")
	public static AndroidElement ipv4oripv6r;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4\")")
	public static AndroidElement ipv4p;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv6\")")
	public static AndroidElement ipv6p;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4/IPv6\")")
	public static AndroidElement ipv4oripv6p;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	public static AndroidElement protocolid;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN type\")")
	public static AndroidElement APNtypeAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Bearer\")")
	public static AndroidElement bearer;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Authentication type\")")
	public static AndroidElement AuthenticationtypeofAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"None\")")
	public static AndroidElement NoneAPNAT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"PAP\")")
	public static AndroidElement PAPAPNAT;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"CHAP\")")
	public static AndroidElement CHAPAPNAT;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"PAP or CHAP\")")
	public static AndroidElement PAPorCHAPPNAT;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/search_box_text\")")
	public static AndroidElement googletextfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/url_bar\")")
	public static AndroidElement textfieldinchrome;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Login\")")
//	public static AndroidElement flipcartloginbtn;
//	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static WebElement Nointernet;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"m_login_email\")")
	public static WebElement fbloginfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Image\").text(\"facebook\")")
	public static AndroidElement facebooktext;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"NO INTERNET\")")
	public static AndroidElement nointernet;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Could not load content\")")
//	public static AndroidElement couldnotload;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\")")
	public static AndroidElement mobiledatarbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\")")
	public static AndroidElement apnname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.googlequicksearchbox:id/search_box\")")
	public static AndroidElement textfieldgoogle;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.googlequicksearchbox:id/text_container\")")
	public static AndroidElement clicktextfield;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.googlequicksearchbox:id/execute_button\")")
	public static AndroidElement googlealarm;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search…\")")
	public static AndroidElement search;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.deskclock:id/fab\")")
	public static  AndroidElement addalaram;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadialTimePickerView$RadialPickerTouchHelper\").discription(\"13\")")
	public static  AndroidElement picker;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add another account\")")
	public static AndroidElement addaccount;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gm:id/og_apd_internal_image_view\")")
	public static AndroidElement gmenu;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").index(2)")
	public static AndroidElement email;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").index(0)")
	public static AndroidElement password;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement next;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"signinconsentNext\")")
	public static AndroidElement iagree;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"janardhansonim123@gmail.com\")")
	public static AndroidElement jana;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/hours\")")
	public static  AndroidElement addalaramhr;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")")
	public static AndroidElement clock;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(\"2\")")
	public static  AndroidElement addalarammin;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").text(\"AM\")")
	public static AndroidElement alarmam;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").text(\"PM\")")
	public static AndroidElement alarmpm;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement alarmok;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"\").text(\"Settings\")")
	public static AndroidElement settingal;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceid(\"com.google.android.deskclock:id/digital_clock\")")
	public static AndroidElement setalarm;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceid(\"com.android.launcher3:id/all_apps_handle\")")
	public static AndroidElement home;
	
	
	
	
	
	
	
	
//	@AndroidFindBy(xpath  = "//*[@contentDescription='Dismiss']")
//	public static  AndroidElement dismiss;
	//*[@resource-id='android:id/action0']
	@AndroidFindBy(xpath  = "//*[@resource-id='android:id/action0']")
	

	
	public static  AndroidElement dismiss;
	@AndroidFindBy(id = "action0")
	public static  AndroidElement dismiss1;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//wifi
	
	
//	
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
//	public static AndroidElement networkandinternetopt;
//	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").content-desc(\"IDCSONWAP,Check password and try again,Wifi signal full.,Secure network\")")
	public static AndroidElement wifitext;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='IDCSONWAP,Check password and try again,Wifi signal full.,Secure network']")
	public static AndroidElement wifij;
	

	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement wifi;
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement wifinew;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
	public static AndroidElement wifiradiobtn;
//		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
//		public static AndroidElement wifioff;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\")")
	public static AndroidElement wifi1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Belkin 2.4\")")
	public static AndroidElement wifi2;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/password\")")
	public static AndroidElement wifitextfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement wifioff;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
	public static AndroidElement wificonnect;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Check password and try again\")")
	public static AndroidElement wificonmsg;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement wififrame;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static AndroidElement nointernet1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static AndroidElement connectinternet;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Starred\")")
	public static AndroidElement starred;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement googlemap;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
	public static AndroidElement maps;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Maps is offline. Check your network connection.\")")
	public static AndroidElement netwkerr;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.apps.maps:id/search_omnibox_menu_button\")")
	public static  AndroidElement appmenu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Your Timeline\")")
	public static  AndroidElement urtimeline;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Nothing in Starred\")")
	public static  AndroidElement starmsg;
//	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.google.android.gm:id/open_search']/android.widget.ImageButton")
//	public static AndroidElement menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\")")
	public static  AndroidElement menu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Social\")")
	public static  AndroidElement social;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static  AndroidElement youtube;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search YouTube\")")
	public static  AndroidElement searchyoutubeenter;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").index(\"1\")")
	public static  AndroidElement youtubesearchbtn;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"sonim xp8\")")
	public static  AndroidElement xp8;
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement gmailmenu;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
	public static  AndroidElement turnoff_on_wifi;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"img_0_srch_hungama_48508182\")")
	public static  AndroidElement music;
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='srch_bsb_1404393019358']/android.widget.ListView/android.view.View")
	public static  AndroidElement musicxpath;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"System\")")
	public static  AndroidElement system;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Languages & input\")")
	public static  AndroidElement languagesandinput;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Languages\")")
	public static  AndroidElement languages;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Add a language\")")
	public static  AndroidElement addlanguages;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Français (France)\")")
	public static  AndroidElement france;
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='More options']")
	public static  AndroidElement languagesetting;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Remove\")")
	public static  AndroidElement remove;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Remove']")
	public static  AndroidElement delete;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/expand_indicator\")")
	public static  AndroidElement wififrameN;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/tile_label\")")
	public static  AndroidElement wififrameN1;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
	public static  AndroidElement wifiradiobtn1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").text(\"English (India)\")")
	public static  AndroidElement englishcheckbox;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static  AndroidElement removeok;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Activé\")")
	public static  AndroidElement activewifi;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Désactivé\")")
	public static  AndroidElement deactivewifi;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Ajouter un réseau\")")
	public static  AndroidElement devicename;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Préférences Wi-Fi\")")
	public static  AndroidElement prefference;
	@AndroidFindBy(xpath = "//android.widget.ScrollView[@index='1']")
	public static AndroidElement framever;
}
