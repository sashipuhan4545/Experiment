package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Browser {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Browser(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ACCEPT & CONTINUE')]")
	public static AndroidElement AccptBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	public static AndroidElement DismissIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Chrome')]")
	public static AndroidElement BrowserIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NEXT')]")
	public static AndroidElement NextIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NO THANKS')]")
	public static AndroidElement NothanksBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/url_bar')]")
	public static AndroidElement DefaultUrlTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/url_bar')]")
	public static AndroidElement Urlbar;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/search_box_text')]")
	public static AndroidElement UrlbarNewTb;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Bookmark this page']"))
	public static AndroidElement BookmarkIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Edit bookmark']"))
	public static AndroidElement BookmarkEditIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'android:id/summary')]")
	public static AndroidElement summaryTextSearch;

	//After tapping on Url tab



	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.view.View[@index='0']"))
	public static AndroidElement TimesofInd;

	//    @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/android.widget.ImageButton[@index='0']"))
	//    public static AndroidElement Tabs;

	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.chrome:id/tab_switcher_button')]")
	public static AndroidElement TabBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.chrome:id/menu_button')]")
	public static AndroidElement MenuChrome;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'New tab')]")
	public static AndroidElement NewTabOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'New incognito tab')]")
	public static AndroidElement NewIncgTabOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='2 open tabs']"))
	public static AndroidElement TwoTabopen;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'History')]")
	public static AndroidElement HistoryOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Bookmarks')]")
	public static AndroidElement BookmarksOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Request desktop site')]")
	public static AndroidElement RqstDsktpOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Add to Home screen')]")
	public static AndroidElement AddToHomeScrnOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Find in page')]")
	public static AndroidElement FindInPageOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recent tabs')]")
	public static AndroidElement RecentTabsOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Settings')]")
	public static AndroidElement SettgsOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.ImageButton[@index='4']"))
	public static AndroidElement RefrshIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Close all tabs')]")
	public static AndroidElement CloseAllOptn;


	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Close incognito tabs')]")
	public static AndroidElement CloseAllIncgOptn;

	//History page
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Home - YouTube')]")
	public static AndroidElement YoutubeHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in')]")
	public static AndroidElement AmazonHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay')]")
	public static AndroidElement EbayHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'m.facebook.com')]")
	public static AndroidElement FBHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.chrome:id/close_menu_id')]")
	public static AndroidElement HistryCancelIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Go back']"))
	public static AndroidElement GobackIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'No history here')]")
	public static AndroidElement NoHstryTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'mobile.twitter.com')]")
	public static AndroidElement TxtLnkHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Copy link')]")
	public static AndroidElement CopyLinkOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Open in incognito tab')]")
	public static AndroidElement OpnIncgntOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@index='4']"))
	public static AndroidElement HistryMenuIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'CLEAR BROWSING DATA…')]")
	public static AndroidElement ClearBrowsngDataOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@resource-id,'com.android.chrome:id/spinner')]")
	public static AndroidElement ClrDataDropdown;

	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'All time')]")
	public static AndroidElement begningTimeOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'CLEAR DATA')]")
	public static AndroidElement ClrDataBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Search']"))
	public static AndroidElement HistrySearchIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Search your history')]")
	public static AndroidElement SearchField;



	//Incognito tab Page

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@resource-id,'com.android.chrome:id/refine_view_id')]")
	public static AndroidElement LinkCopyIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='2']/android.widget.EditText[@index='0']"))
	public static AndroidElement IncogntUrl;

	//FB Page
	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.view.View[@index='3']"))
	public static AndroidElement ImageTxt;

	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.widget.Image[@index='1']"))
	public static AndroidElement ImagePic;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Download image')]")
	public static AndroidElement DwnldImg;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'UPDATE PERMISSIONS')]")
	public static AndroidElement UpdtePermssnTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ALLOW')]")
	public static AndroidElement AllwBtn;

	//Settings page
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.settings:id/search')]")
	public static AndroidElement SearchSettgIcn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Search…')]")
	public static AndroidElement SearchsettgField;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Languages')]")
	public static AndroidElement SelectLanguages;

	@FindBy(how=How.XPATH, using =("//android.support.v7.widget.RecyclerView[@index='2']/android.widget.LinearLayout[@index='0']"))
	public static AndroidElement Languageselect;


	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Add a language')]")
	public static AndroidElement AddLanguage;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Español (Estados Unidos)')]")
	public static AndroidElement SelectSpanish;

	@FindBy(how=How.XPATH, using ="//android.widget.CheckBox[contains(@text,'English (United States)')]")
	public static AndroidElement SelectEnglishUS;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement IconMenuSettg;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Remove')]")
	public static AndroidElement RemoveOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Remove']"))
	public static AndroidElement DeleteIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'OK')]")
	public static AndroidElement OkOptn;

	//Bookmarks page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The New York Times - Breaking News, World News & Multimedia')]")
	public static AndroidElement BookmarkNytimes;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile bookmarks']")
	public static AndroidElement MobileBookmarks;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete bookmarks']"))
	public static AndroidElement DeleteBookmarks;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Options']"))
	public static AndroidElement OptnBkmrkIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Delete')]")
	public static AndroidElement DeleteBkmrkOptn;

	//Recent Tabs Page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'CNN - Breaking News, U.S., World, Weather, Entertainment & Video News')]")
	public static AndroidElement RecentTabsCNN;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Show full history')]")
	public static AndroidElement ShowFullHistory;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'On')]")
	public static AndroidElement wifiOn;

	@FindBy(how=How.XPATH, using =("//android.view.View[@content-desc='You are offline']"))
	public static AndroidElement offlineTxt;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.ImageView[@index='0']"))
	public static AndroidElement enableWifi;

	//Find in page

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Close']"))
	public static AndroidElement CloseIcon;

	@FindBy(how=How.XPATH, using =("//android.view.View[@content-desc='Times of India']"))
	public static AndroidElement TimesofIndiaBlogs;



	//Add to HomeScreen Popup

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ADD')]")
	public static AndroidElement AddOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'https://Times of India.com/ - Google Search')]")
	public static AndroidElement HomeScreenAdded;

	//Find in page 
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Find in page')]")
	public static AndroidElement FndPageUrlField;


	//Settings Page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Site settings')]")
	public static AndroidElement SiteSettgs;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Cookies')]")
	public static AndroidElement CookiesOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'ON')]")
	public static AndroidElement OnCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Blocked')]")
	public static AndroidElement BlockedCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'OFF')]")
	public static AndroidElement OffCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'OFF')]")
	public static AndroidElement HelpCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Clear browsing data')]")
	public static AndroidElement ClrBrwsingData;

	//search engine

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Search engine')]")
	public static AndroidElement SearchEng;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Yahoo!')]")
	public static AndroidElement Yahooo;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Google')]")
	public static AndroidElement Google;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Navigate up']"))
	public static AndroidElement NavigateUpSrchEng;

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@text,'Yahoo!')]")
	public static AndroidElement Yahooologo;

	// 
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Home page')]")
	public static AndroidElement Homepage;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'ON')]")
	public static AndroidElement OnHomepge;

	@FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@resource-id,'com.android.chrome:id/compositor_view_holder')]")
	public static AndroidElement HompageScreen;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'OFF')]")
	public static AndroidElement OffHomepge;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Open this page')]")
	public static AndroidElement OpnPage;

	@FindBy(how=How.XPATH, using =("//android.widget.EditText[@content-desc='Open this page']"))
	public static AndroidElement OpnPgeField;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Save')]")
	public static AndroidElement SaveOptn;



}