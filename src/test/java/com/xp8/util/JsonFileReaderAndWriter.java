package com.xp8.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class JsonFileReaderAndWriter {

	public static String JSonFileReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/config_file.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceId = (String) jsonObject.get("DeviceId");
		return deviceId;
	}

	public static String ReadMobileNumFrmJson() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/RefMobileNumber.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String mobileNum = (String) jsonObject.get("RefMobileNum");
		return mobileNum;

	}

	//This Method is used to write the deviceId into Json file
	public static void WriteRefDevIDtoJson(String RefDevId,String Reffirmware,String RefModelNum,String RefOperatorName) throws IOException {

		/*File f=new File("src/test/resources/drivers/RefDeviceId.json");
		if(f.exists()) {
			f.delete();
		}
		f.createNewFile();*/
		JSONObject obj=new JSONObject();
		obj.put("RefDevId", RefDevId);
		obj.put("RefDevicefirmWare", Reffirmware);
		obj.put("RefDeviceModelNum", RefModelNum);
		obj.put("RefOperatorName", RefOperatorName);

		try (FileWriter file = new FileWriter("src/test/resources/json/RefDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String RefDevOperatorName() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String RefDevOperatorName = (String) jsonObject.get("RefOperatorName");
		return RefDevOperatorName;

	}

	//This Method is used to read the deviceId into Json file
	public static String ReadRefDeviceId() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String mobileNum = (String) jsonObject.get("RefDevId");
		return mobileNum;

	}
	
	public static void RefMobileNumber(String RefMobNum) {

		JSONObject obj=new JSONObject();
		obj.put("RefMobileNum", RefMobNum);


		try (FileWriter file = new FileWriter("src/test/resources/json/RefMobileNumber.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String RefDeviceModelNum() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String RefDeviceModelNum = (String) jsonObject.get("RefDeviceModelNum");
		return RefDeviceModelNum;

	}
	
	public static String ReadRefDeviceFirmWare() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String mobileNum = (String) jsonObject.get("RefDevicefirmWare");
		return mobileNum;

	}

	public static void TestCaseNameWriter(String methodName) {

		JSONObject obj=new JSONObject();
		obj.put("TestCaseName", methodName);

		try (FileWriter file = new FileWriter("src/test/resources/json/TestCaseName.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String TestCaseNameReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/TestCaseName.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String testcaseName = (String) jsonObject.get("TestCaseName");
		return testcaseName;
	}

	public static void deviceModelWriter(String model) {

		JSONObject obj=new JSONObject();
		obj.put("model", model);

		try (FileWriter file = new FileWriter("src/test/resources/json/DeviceModel.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String deviceModelreader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/DeviceModel.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("model");
		return deviceModel;
	}

	//This is used for Two device commnication

	public static void primaryDevIdWriter(String primaryDeviceId,String priDevModel,String operatorName,String firmwareVersion,String chkSimAvailability) throws IOException {

		JSONObject obj=new JSONObject();

		obj.put("primaryDevId", primaryDeviceId);
		obj.put("prideviceModel", priDevModel);
		obj.put("operatorName", operatorName);
		obj.put("firmwareVersion", firmwareVersion);
		obj.put("simAvailability", chkSimAvailability);


		try (FileWriter file = new FileWriter("src/test/resources/json/primaryDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String PrimaryDevicesimAvailability() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("simAvailability");
		return deviceModel;
	}

	public static void OnlyPrimaryDeviceIdWriter(String OnlyprimaryDeviceId) {

		JSONObject obj=new JSONObject();
		obj.put("primaryDevId", OnlyprimaryDeviceId);

		try (FileWriter file = new FileWriter("src/test/resources/json/primaryDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String primaryDevIdReader() throws FileNotFoundException {
		String deviceModel = null;
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("src/test/resources/json/primaryDeviceId.json"));
			JSONObject jsonObject =  (JSONObject) obj;
			deviceModel= (String) jsonObject.get("primaryDevId");
			System.out.println(deviceModel);


		}catch (Exception e) {

		}
		return deviceModel;
	}
	
	public static String primaryDevModelReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("prideviceModel");
		return deviceModel;
	}
	
	public static String primaryDevOperatorReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("operatorName");
		return deviceModel;
	}

	public static String primaryDevFirmwareReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/json/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("firmwareVersion");
		return deviceModel;
	}

	/*public static void swap() throws FileNotFoundException, IOException, ParseException {


		JSONParser parser = new JSONParser();
		Object ref = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		Object pri = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
		Object temp = parser.parse(new FileReader("src/test/resources/drivers/temp.json"));


		JSONObject jsonObject =  (JSONObject) pri;
		try (FileWriter file = new FileWriter("src/test/resources/drivers/temp.json")) {

			file.write(jsonObject.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject1 =  (JSONObject) ref;
		try (FileWriter file = new FileWriter("src/test/resources/drivers/primaryDeviceId.json")) {

			file.write(jsonObject1.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jsonObject3 =  (JSONObject)temp ;
		try (FileWriter file = new FileWriter("src/test/resources/drivers/RefDeviceId.json")) {

			file.write(jsonObject3.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	 */




}

