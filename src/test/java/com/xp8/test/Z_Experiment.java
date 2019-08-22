package com.xp8.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.UUID;

import com.xp8.util.JsonFileReaderAndWriter;

public class Z_Experiment {

	public static void main(String[] args) throws ParseException, IOException, InterruptedException, Exception {

		//		String time1="1:35:31";
		//		String time2="26:35:37";
		//		
		//		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		//
		//		Date date1 = format.parse(time1);
		//		Date date2 = format.parse(time2);
		//		
		//		System.out.println(date1);
		//		System.out.println(date2);
		//		
		//		long diff = date2.getTime()-date1.getTime();
		//		
		//		
		//		System.out.println(diff);
		//		
		//		long diffSeconds = diff / 1000 % 60;
		//		long diffMinutes = diff / (60 * 1000) % 60;
		//		long diffHours = diff / (60 * 60 * 1000) % 24;
		//		long diffDays = diff / (24 * 60 * 60 * 1000);		
		//		System.out.println(diffHours+" : "+diffMinutes+" : "+diffSeconds);

		//		Runtime run = Runtime.getRuntime();
		//		Process pr = run.exec("adb shell input tap 540 1776");
		//		pr.waitFor();
		//		Runtime run1 = Runtime.getRuntime();
		//		Process pr1 = run1.exec("adb shell input tap 713 1098");
		//		pr1.waitFor();

		//		boolean check = false;
		//		try { 
		//			BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\adbLogs\\XP8_Sanity_009.txt"));
		//			int linecount = 0;
		//			String line;
		//			while (( line = bf.readLine()) != null){
		//				
		//				boolean indexfound = line.contains("Setting voice volume index: 9");
		//				if (indexfound) {
		//					check=true;
		//					
		//					break;
		//				}  
		//				else {
		//					check=false;
		//					linecount++;
		//				}
		//			}
		//			bf.close();
		//		}
		//		catch (IOException e) {
		//		
		//		}
		//
		//		System.out.println("Print : "+check);

		//		Calendar cal = Calendar.getInstance();
		//		Formatter fmt = new Formatter();
		//		//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
		//		fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
		//		System.out.println(fmt);
		//		java.util.Date today = new java.util.Date();
		//		Time fmt1 = new java.sql.Time(today.getTime());
		//		System.out.println(fmt1);

		//		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\adbLogs\\XP8_Sanity_004.txt";
		//		
		//		System.out.println(path);
		//		
		//		Path path1 = Paths.get(path);
		//		Files.newBufferedWriter(path1, StandardOpenOption.TRUNCATE_EXISTING);

		//		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");



		//		String path = System.getProperty("user.dir")+"\\src\\test\\resources\\adbLogs\\chandan.txt";	
		//		System.out.println(path);
		//
		//		final Process exec = Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>"+path);
		//
		//		InputStream inputStream = exec.getInputStream();
		//		InputStreamReader buInputStreamReader = new InputStreamReader(inputStream);
		//		final BufferedReader bufferedReader = new BufferedReader(buInputStreamReader);
		//		final StringBuilder buf = new StringBuilder();
		//
		//
		//
		//		new Thread(){
		//            @Override
		//            public void run() {
		//                try {
		//                    String str = null;
		//                    while ((str = bufferedReader.readLine()) != null) {
		//                        buf.append(str);
		//                        buf.append("\n");
		//                        System.out.println(str);
		//                    }
		//                }catch (Exception ignore){}
		//            }
		//        }.start();
		//
		//		Thread.sleep(9000);
		//
		//		bufferedReader.close();
		//		
		//		exec.destroy();



		//		Runtime.getRuntime().exec("cmd /C \"C:\\Users\\chandan.a\\eclipse-workspace\\2_XP8800\\ADB\\adb.exe logcat -v time>"+path);
		//		Thread.sleep(9000);

		//		p.destroy();
		
		
//		 UUID uuid = UUID.randomUUID();
//		 for (long i = 0; i <5; i++) {
//			 long randomUUIDString = uuid.timestamp();
//			 System.out.println(randomUUIDString);
//		}
		
//		System.out.println(Runtime.getRuntime().exec("adb shell getprop ro.build.id"));
//		 
//		Process child = Runtime.getRuntime().exec("adb shell getprop ro.build.display.id");
//		InputStream lsOut = child.getInputStream();
//		InputStreamReader r = new InputStreamReader(lsOut);
//		BufferedReader in = new BufferedReader(r);
//		String builNo = in.readLine();
//		System.out.println(builNo);
			
//			Process pcs = Runtime.getRuntime().exec("adb shell getprop ro.build.id");
//			InputStream ipsr = pcs.getInputStream();
		
		//System.out.println(JsonFileReaderAndWriter.primaryDevFirmwareReader());
		
	
//		Runtime.getRuntime().exec("adb -s e0116a80 shell input keyevent 3");
//		Thread.sleep(2000);
		try {
			Runtime.getRuntime().exec("adb shell input keyevent 6");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
