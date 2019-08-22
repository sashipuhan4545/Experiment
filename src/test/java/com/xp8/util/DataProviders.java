package com.xp8.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name ="LaunchSettingsApp")
	public static Object[][] LaunchSettingsApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="LaunchCalculatorApp")
	public static Object[][] LaunchCalculatorApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchContactsApp")
	public static Object[][] LaunchContactsApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchSoundRecorderApp")
	public static Object[][] LaunchSoundRecorderApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchFMRadioApp")
	public static Object[][] LaunchFMRadioApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="ProgrammableKeyTest")
	public static Object[][] ProgrammableKeyTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="DailerTest")
	public static Object[][] DailerTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="SonimCareTest")
	public static Object[][] SonimCareTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="SafeguardTest")
	public static Object[][] SafeguardTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="SonimWarrantyReg")
	public static Object[][] SonimWarrantyReg(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="ContactTransfer")
	public static Object[][] ContactTransfer(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}	 

	@DataProvider(name ="AllAppsMonkeyTest")
	public static Object[][] AllAppsMonkeyTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	//=========================================================================================

	@DataProvider(name ="XP8SanityTest")
	public static Object[][] XP8SanityTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Device_Sanity");
	}
	
	@DataProvider(name ="XP8_NewSanityTest")
	public static Object[][] XP8_NewSanityTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Device_NewSanity");
	}
	
	
	

	@DataProvider(name ="XP8_Stability")
	public static Object[][] XP8_Stability(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Stability");
	}

	@DataProvider(name ="XP8DevSanity")
	public static Object[][] XP8DevSanity(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Dev_Sanity");
	}
	
	@DataProvider(name ="XP8_ATTStability")
	public static Object[][] XP8_ATTStability(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_ATT_Stability");
	}

	@DataProvider(name ="XP8_Call_Settings")
	public static Object[][] XP8_Call_Settings(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Call_Settings");
	}	
	@DataProvider(name ="XP8_Emergencycall")
	public static Object[][] XP8_Emergencycall(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Emergencycall");
	}	
	
	@DataProvider(name ="XP8_Data_Setting")
	public static Object[][] XP8_Data_Setting(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Data_Setting");
	}	
	
}
