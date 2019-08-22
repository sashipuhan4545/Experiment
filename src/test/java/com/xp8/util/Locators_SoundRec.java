package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Locators_SoundRec {
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_SoundRec(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Press Record')]")
	 public static AndroidElement PressRecordText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Sound Recorder')]")
	 public static AndroidElement SoundRecorderText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.android.systemui:id/dismiss_task')]")
	 public static AndroidElement DismissIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'CLEAR ALL')]")
	 public static AndroidElement ClearAllOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'00:00')]")
	 public static AndroidElement initialTimeText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/recordButton')]")
	 public static AndroidElement recordBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/android.widget.ImageButton[@index='1']"))
	public static AndroidElement PauseBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/stopButton')]")
	 public static AndroidElement stopBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Pause')]")
	 public static AndroidElement PauseText;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.ImageButton[@index='1']"))
	 public static AndroidElement PauseBtnList;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recording')]")
	 public static AndroidElement RecordingText;
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/listButton')]")
	 public static AndroidElement listIcon;
	
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='2']"))
	public static AndroidElement allowBtn;
	

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement ListallowBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.soundrecorder:id/rename_edit_text')]")
	public static AndroidElement recordingName;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	 public static AndroidElement DiscardBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	 public static AndroidElement saveBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/list_item_title')]"))
	 public static AndroidElement saveBtnUnderRecList;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[contains(@resource-id,'com.android.soundrecorder:id/file_list_recording_layout')]"))
	 public static AndroidElement firstEntry;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[contains(@resource-id,'com.android.soundrecorder:id/file_list_folder_layout')]"))
	 public static AndroidElement firstEntryFolder;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.CheckBox[@index='0']"))
	 public static AndroidElement CheckBox1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.CheckBox[@index='0']"))
	 public static AndroidElement CheckBox2;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.CheckBox[@index='0']"))
	 public static AndroidElement CheckBox3;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='3']/android.widget.CheckBox[@index='0']"))
	 public static AndroidElement CheckBox4;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='4']/android.widget.CheckBox[@index='0']"))
	 public static AndroidElement CheckBox5;
	
	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='0']/android.widget.ImageButton[@index='0']"))
	 public static AndroidElement backBtn;
	
	//@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='0']/android.widget.ImageButton[@index='0']"))
	// public static AndroidElement backBtnList;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'android:id/action_mode_close_button')]")
	 public static AndroidElement CloseBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_delete')]")
	 public static AndroidElement DeleteIcon;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement DeleteBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_share')]")
	 public static AndroidElement ShareIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@resource-id,'com.android.soundrecorder:id/selection_spinner')]")
	 public static AndroidElement SelectDropDown;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Select all')]")
	public static AndroidElement SelectAllOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Deselect all')]")
	public static AndroidElement DeSelectAllOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'No recording')]")
	public static AndroidElement NorecordingMsg;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_edit')]")
	 public static AndroidElement EditIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recording list')]")
	public static AndroidElement RecordingListText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SoundRecorder')]")
	 public static AndroidElement SoundRecoderOpt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.ImageButton[@index='0']"))
	 public static AndroidElement SearchOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ALLOW')]")
	 public static AndroidElement AllowOpt;
	
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Type your search')]")
	public static AndroidElement SearchTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SoundRecorder')]")
	 public static AndroidElement SelectSoundRec;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.cyanogenmod.filemanager:id/navigation_view_item_name')]")
	public static AndroidElement HistorySearch;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SampleSoundRecord.amr')]")
	public static AndroidElement SampleSoundRec;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The name already exists.')]")
	public static AndroidElement NameExist;
	
	
	
	
}
