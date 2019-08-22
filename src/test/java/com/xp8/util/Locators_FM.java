package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Locators_FM {
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_FM(AndroidDriver<AndroidElement> aDriver) {
		Locators_FM.aDriver=aDriver;
	}
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'FM Radio')]")
	 public static AndroidElement FMRadioTxt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/android.widget.ImageButton[@index='0']"))
	 public static AndroidElement MenuIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/record_msg_tv')]")
	 public static AndroidElement recordMsgIcon;
	 
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	 public static AndroidElement DismissIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_onoff')]")
	 public static AndroidElement OnOFFBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='2']"))
	 public static AndroidElement MuteBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='3']"))
	 public static AndroidElement MonoStereoOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	 public static AndroidElement PresetBtn1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	 public static AndroidElement PresetBtn2;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='2']"))
	 public static AndroidElement PresetBtn3;
//	
//	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.ImageView[@index='2']"))
//	 public static AndroidElement ForwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_forward')]")
	 public static AndroidElement ForwrdBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.TextView[@index='1']"))
	 public static AndroidElement ChannelTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/prog_frequency_tv')]")
	 public static AndroidElement ChannelTxt1;
	
//	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_back')]")
//	 public static AndroidElement BackwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_back')]")
	 public static AndroidElement BackwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Settings')]")
	 public static AndroidElement SettgOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Scan')]")
	 public static AndroidElement ScanOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'All Stations')]")
	 public static AndroidElement AllStationsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Record Duration')]")
	 public static AndroidElement RecDurtnOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'5 minutes')]")
	 public static AndroidElement FiveminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'15 minutes')]")
	 public static AndroidElement FifteenminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'30 minutes')]")
	 public static AndroidElement ThirtyminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'Until stopped')]")
	 public static AndroidElement UntilStopOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='6']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']"))
	 public static AndroidElement SummryBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Sleep')]")
	 public static AndroidElement SleepOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'15 minutes')]")
	 public static AndroidElement fifteenminsSleepOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'candroid:id/alertTitle')]")
	 public static AndroidElement AlertTitle;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'STOP')]")
	 public static AndroidElement StopOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Regional Band')]")
	 public static AndroidElement RegionalBandOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'North America (87.5MHz To 108.0MHz In 200 Khz Steps)')]")
	 public static AndroidElement DefaultRegnlBnd;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Stereo')]")
	 public static AndroidElement SteroTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Auto Select Enabled')]")
	 public static AndroidElement AutoSelectTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'5 minutes')]")
	 public static AndroidElement fiveminxTxt;
	
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Replace')]")
	 public static AndroidElement ReplaceOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Rename')]")
	 public static AndroidElement RenameOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.caf.fmradio:id/list_edit')]")
	 public static AndroidElement EditName;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Tune')]")
	  public static AndroidElement TuneBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'OK')]")
	 public static AndroidElement OKOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Delete')]")
	 public static AndroidElement DeleteOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'Europe')]")
	 public static AndroidElement EuropeOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Audio Output Mode')]")
	 public static AndroidElement AudioOutMode;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Alternate Frequency')]")
	 public static AndroidElement AlternateFreqOpt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.ListView[@index='0']/android.widget.CheckedTextView[@index='1']"))
	 public static AndroidElement MonoAudioOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Revert to Factory Defaults')]")
	 public static AndroidElement FactryResetOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/frequency')]")
	 public static AndroidElement FMNotifctn;
	
	
	
}

