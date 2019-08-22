package com.xp8.util;

import java.io.IOException;

public class All_AppsMonkeyRun_Util extends BaseUtil {
	
	public void monkeyRunAllApps() throws InterruptedException, IOException {		
		/*
		 *  All Applications Monkey Run.
		*/
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell monkey --throttle 1000 -s 67523 --pct-trackball 0 --pct-nav 0 --pct-majornav 0 --ignore-crashes --ignore-timeouts --ignore-security-exceptions -p com.sonim.borqs.launcher -p com.borqs.camera -p com.qualcomm.qti.simsettings -p com.android.providers.contacts -p com.sonim.scout -p com.android.mms -p com.android.browser -p com.android.providers.calendar -p com.android.deskclock -p com.android.bluetooth -p com.android.gallery3d -p com.sonim.safeguard -p com.android.music  -p com.android.calculator2 -p com.android.soundrecorder -p com.sonimtech.sonimupdater -p com.sonimtech.easycontactsshare -p com.sonim.ble.connect -p com.sonimtech.setupwizard -p com.sonim.sonimcustomercare -p com.sonimtech.warrantyregapp -p com.sonim.scout -p cyberswift.com.sonim -p cyberswift.com.sonim -p com.android.documentsui -p -v -v -v 3000> monkey_logs.txt");
		for(int i=1; i<=18;i++) {
			customWait(60000);
		}		
	}
}
