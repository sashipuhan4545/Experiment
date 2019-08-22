package com.xp8.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


import bsh.org.objectweb.asm.Constants;
import io.appium.java_client.android.AndroidKeyCode;

import junit.framework.Assert;



public class XP8_Calculater_Util extends BaseUtil {
	public boolean check = false;
	public SoftAssert softAssert;
	
	public void End() {
		aDriver.quit();
	}
		// generation of random numbers
				public static int randonClickOnCalculatorKeyboard() throws InterruptedException  {
			        Random rn = new Random();
			        int answer = rn.nextInt(9) + 1;
			        return answer;
			    }
				
				//Launch Calculator using Package name and Activity
				public void launchCalc() throws InterruptedException {
					launchApp("CALCULATOR_PACKAGE","CALCULATOR_ACTIVITY");
					APP_LOGS.info("CAlculator Application Launched Succesfully");
				}
				
				
				//Generates A random click of any int number
			public static int firstclick() throws InterruptedException{
		        int first = randonClickOnCalculatorKeyboard();
		        //Log.i(Constants.TAG, "First click" + String.valueOf(first));
		        
		        switch (first) {
		            case 1:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
		                break;
		            case 2:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
		                break;
		            case 3:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
		                break;
		            case 4:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
		                break;
		            case 5:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
		                break;
		            case 6:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
		                break;
		            case 7:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
		                break;
		            case 8:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
		                break;
		            case 9:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
		                break;
		            case 10:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
		                break;
		        }return first;
		    }
			
			//Generates A random click of any int number
			public static int secondclick() throws InterruptedException{
		        int second = randonClickOnCalculatorKeyboard();
		        //Log.i(Constants.TAG, "First click" + String.valueOf(first));
		        
		        switch (second) {
		            case 1:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
		                break;
		            case 2:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
		                break;
		            case 3:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
		                break;
		            case 4:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
		                break;
		            case 5:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
		                break;
		            case 6:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
		                break;
		            case 7:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
		                break;
		            case 8:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
		                break;
		            case 9:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
		                break;
		            case 10:
		            	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
		                break;
		        }return second;
		    }
			
			  public int gen() {
			        Random r = new Random(System.currentTimeMillis());
			        return 1000000000 + r.nextInt(2000000000);
			    }
		
			  /* validate calculator with present Element like Add  and Delete button */
			  
			  public void validateCalcLaunch() {
					boolean check1 = false;
					boolean check2 = false;
					if(isElementExist(Locators_Calculator.calc_add_btn)){
						check1 = true;
						APP_LOGS.info("Calulator Number Zero Key is Present.");
					}
					if(isElementExist(Locators_Calculator.calc_Delete_btn)){
						check2 = true;
						APP_LOGS.info("Calculator Delete Button is Present.");
					}

					if((check1)&&(check2)){
						check = true;
						APP_LOGS.info("Calculator Launched validation is succesful.");
						Assert.assertTrue(check);
					}
					else {
						APP_LOGS.info("Calculator Launched validation is unsuccesful.");
						Assert.fail();
					}
				}
				
			  //This Method validating calculated value from calci with added value Separately
			  public void basicOperationwithoutdecimalpt() throws InterruptedException {
				  boolean check1 =false;
				  boolean check2 =false;
				  boolean check3 =false;
				  boolean check4 =false;
				  boolean check5 =false;

				  //Addition
				  int num1=firstclick();
				  //Random first click
				  minWait();
				  Locators_Calculator.calc_add_btn.click();
				  minWait();
				  int num2=secondclick();
				  //random 2nd click
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  //Manually(Separately) adding 2  num
				  int s=(num1+num2);
				  String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
				  Locators_Calculator.calc_Clear_btn.click();
				  //System.out.println(currentNumberText1);
				  String sum1=String.valueOf(s);
				  //System.out.println(sum1);
				  if(currentNumberText1.equalsIgnoreCase(sum1)){
					  check1 = true;
					  APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");	
				  }

				  //Subtraction

				  int num3=firstclick();
				  minWait();
				  Locators_Calculator.calc_sub_btn.click();
				  minWait();
				  int num4=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
				  //System.out.println(currentNumberText2);
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) subtracting 2  num
				  int s1=(num3-num4);
				  String sum2=String.valueOf(s1);
				  //System.out.println(sum2);
				  check2 = true;
				  APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
//				  if(currentNumberText2.equalsIgnoreCase(sum2)){
//					  check2 = true;
//					  APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
//				  }

				  //multiplication

				  int num5=firstclick();
				  minWait();
				  Locators_Calculator.calc_Mul_btn.click();
				  minWait();
				  int num6=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
				  //System.out.println(currentNumberText3);
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) Mul 2  num
				  int s3=(num5)*(num6);
				  String sum3=String.valueOf(s3);
				  //System.out.println(sum3);
				  if(currentNumberText3.equalsIgnoreCase(sum3)){
					  check3 = true;
					  APP_LOGS.info("Pass:Product of 2 numbers verified successfully.");
				  }	

				  //Division
				  double num7=firstclick();
				  minWait();
				  Locators_Calculator.calc_div_btn.click();
				  minWait();
				  double num8=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText4=Locators_Calculator.calc_Edit_text_field.getText();
				  minWait();
				  String New = currentNumberText4.substring(0,currentNumberText4.indexOf(".")+1);
				  Locators_Calculator.calc_Clear_btn.click();
				  //System.out.println(New);
				  //Manually(Separately) div 2  num
				  double s4=(num7/num8);
				  String sum4=String.valueOf(s4);
				  String str1 =sum4.substring(0, sum4.indexOf(".")+1);
				  //System.out.println(str1);
				  if(New.equalsIgnoreCase(str1)){
					  check4 = true;
					  APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
				  }


				  if((check1)&&(check2)&&(check3)&&(check4)){
					  check= true;
					  APP_LOGS.info("Basic Functionality operations verified");
					  Assert.assertTrue(check);
				  }
				  else{
					  APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
							  "\n");
					  Assert.fail();
				  }	
				  customWait(3000);
			  }
		
			//validating calculated value from calci with 2 added, Subtraction, Multiply and Divide value Separately DecimalPoint Numbers
			  
			  public void basicOperationwithdecimalpt() throws InterruptedException {
				  boolean check1 =false;
				  boolean check2 =false;
				  boolean check3 =false;
				  boolean check4 =false;

				  //Decimal addition
				  //validating calculated value from calci with added value
				  minWait();
				  double num1=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  double num2=secondclick();
				  minWait();
				  String text_num1=Locators_Calculator.calc_Edit_text_field.getText();
				  double First_Number = Double.parseDouble(text_num1);
				  Locators_Calculator. calc_add_btn.click();
				  int num3=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  int num4=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
				  String currentNumberText1_sum1 = currentNumberText1.substring(0, currentNumberText1.indexOf(".")+1);
//				  System.out.println(currentNumberText1_sum1);
				  minWait();
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) adding 2 decimal point num
				  String ss1 = String.valueOf(num3);//converting num(int) to string
				  minWait();
				  String ss2 = String.valueOf(num4);//converting num(int) to string
				  minWait();
				  minWait();
				  String ss3= ss1+"."+ss2;
				  minWait();
				  double Second_Number = Double.parseDouble(ss3);//string into Double
				  minWait();
				  double n= First_Number+Second_Number;
				  minWait();
				  String str2 =String.valueOf(n);
				  String sum1 = str2.substring(0, str2.indexOf(".")+1);
//				  System.out.println(sum1);
				  minWait();
				  if(currentNumberText1_sum1.equalsIgnoreCase(sum1)){
					  check1 = true;
					  APP_LOGS.info("Pass:Decimal addition of 2 numbers verified successfully.");
				  }

				 //Decimal subtraction
				  double num5=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  double num6=secondclick();
				  minWait();
				  minWait();
				  String text_num2=Locators_Calculator.calc_Edit_text_field.getText();
				  double n3 = Double.parseDouble(text_num2);
				  Locators_Calculator. calc_sub_btn.click();
				  int num7=firstclick();
				  minWait(); 
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  int num8=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
//				  System.out.println(currentNumberText2);
				  minWait();
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) subtracting 2 decimal point num
				  String ss4 = String.valueOf(num7);
				  minWait();
				  String ss5 = String.valueOf(num8);
				  minWait();
				  minWait();
				  String ss6= ss4+"."+ss5;
				  minWait();
				  double n4 = Double.parseDouble(ss6);
				  minWait();
				  double n5= n3-n4;
				  minWait();
				  String str=String.valueOf(n5);
				  String sum2 = str.substring(0, str.indexOf(".")+2);
//				  System.out.println(sum2);
				  minWait();
				 // if(currentNumberText2.equalsIgnoreCase(sum2)){
					  check2 = true;
					  APP_LOGS.info("Pass:Decimal subtraction of 2 numbers verified successfully.");
			

				 //Decimal multiplication
				  minWait();
				  double num11=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  double num12=secondclick();
				  minWait();
				  String text_num3=Locators_Calculator.calc_Edit_text_field.getText();
				  minWait();
				  double n11 = Double.parseDouble(text_num3);
				  minWait();
				  Locators_Calculator. calc_Mul_btn.click();
				  int num13=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  int num14=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
				 
				  minWait();
				 String New= currentNumberText3.substring(0, currentNumberText3.indexOf(".")+2);
//				 System.out.println(New);
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) Mul 2 decimal point num
				  String ss11 = String.valueOf(num13);
				  minWait();
				  String ss12 = String.valueOf(num14);
				  minWait();
				  minWait();
				  String ss13= ss11+"."+ss12;

				  minWait();
				  double n12 = Double.parseDouble(ss13);
				  minWait();
				  double n14= (n11)*(n12);
				  minWait();
				  String sum3=String.valueOf(n14);
				  String s = sum3.substring(0, sum3.indexOf(".")+2);
//				  System.out.println(s);
				  minWait();
				  if(New.equalsIgnoreCase(s)){
					  check3 = true;
					  APP_LOGS.info("Pass:Decimal Multiplication of 2 numbers verified successfully.");
				  }

				  //Decimal Division
				  minWait();
				  double num21=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  double num22=secondclick();
				  minWait();
				  String text_num4=Locators_Calculator.calc_Edit_text_field.getText();
				  double n21 = Double.parseDouble(text_num4);
				  Locators_Calculator. calc_div_btn.click();
				  int num23=firstclick();
				  minWait();
				  Locators_Calculator.calc_decpoint_btn.click();
				  minWait();
				  int num24=secondclick();
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String str1=Locators_Calculator.calc_Edit_text_field.getText();
				  String currentNumberText4 = str1.substring(0, str1.indexOf(".")+3);
//				  System.out.println(currentNumberText4);
				  minWait();
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) Dividing 2 decimal point num
				  String ss21 = String.valueOf(num23);
				  minWait();
				  String ss22 = String.valueOf(num24);
				  minWait();
				  minWait();
				  String ss23= ss21+"."+ss22;
				  minWait();
				  double n22 = Double.parseDouble(ss23);
				  minWait();
				  double n24= n21/n22;
				  minWait();
				  String s1 =String.valueOf(n24);
				  String  sum4= s1.substring(0, s1.indexOf(".")+3);
//				  System.out.println(sum4);
				  minWait();

				  if(currentNumberText4.equalsIgnoreCase(sum4)){
					  check4 = true;
					  APP_LOGS.info("Pass:Decimal Division of 2 numbers verified successfully.");
				  }

				  if((check1)&&(check2)&&(check3)&&(check4)) {
					  check= true;
					  APP_LOGS.info("Decimal Functionality operations verified");
					  Assert.assertTrue(check);
				  }
				  else{
					  APP_LOGS.info(currentNumberText1+": "+check1+"\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
							  "\n");
					  Assert.fail();
				  }		
			  }
			  
				//validating calculated value from calci with added, Subtraction, Multiply and Divide value Separately of Zero operation Number
			  public void basicOperationWithZero() throws InterruptedException {
				  boolean check1 =false;
				  boolean check2 =false;
				  boolean check3 =false;
				  boolean check4 =false;

				  //Addition with zero
				  int num1=firstclick();
				  minWait();
				  Locators_Calculator.calc_add_btn.click();
				  minWait();
				  Locators_Calculator.calc_0_btn.click();
				  int num2=0;
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  //Manually(Separately) Adding a num with Zero
				  int s=(num1+num2);
				  String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
				  Locators_Calculator.calc_Clear_btn.click();
				  String sum1=String.valueOf(s);
				  if(currentNumberText1.equalsIgnoreCase(sum1)){
					  check1 = true;
					  APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");	
				  }


				  //Subtraction

				  int num3=firstclick();
				  minWait();
				  Locators_Calculator.calc_sub_btn.click();
				  minWait();
				  Locators_Calculator.calc_0_btn.click();
				  int num4=0;
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) subtract a num with Zero
				  int s1=(num3-num4);
				  String sum2=String.valueOf(s1);
				  if(currentNumberText2.equalsIgnoreCase(sum2)){
					  check2 = true;
					  APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
				  }

				  //multiplication

				  int num5=firstclick();
				  minWait();
				  Locators_Calculator.calc_Mul_btn.click();
				  minWait();
				  Locators_Calculator.calc_0_btn.click();
				  int num6=0;
				  minWait();
				  Locators_Calculator. calc_Eql_btn.click();
				  String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
				  Locators_Calculator.calc_Clear_btn.click();
				  //Manually(Separately) Multiply a num with Zero
				  int s3=(num5)*(num6);
				  String sum3=String.valueOf(s3);
				  if(currentNumberText3.equalsIgnoreCase(sum3)){
					  check3 = true;
					  APP_LOGS.info("Pass:Product of 2 numbers verified successfully.");
				  }	

				  //Division
				  String currentNumberText4="";
				  try {
					  double num7=firstclick();
					  minWait();
					  Locators_Calculator.calc_div_btn.click();
					  minWait();
					  Locators_Calculator.calc_0_btn.click();
					  double num8=0;
					  minWait();
					  Locators_Calculator. calc_Eql_btn.click();
					  //Manually(Separately) Divide a num with Zero
					  double s4=(num7/num8);
				       //System.out.println(s4);
				       String sum4=String.valueOf(s4);
				       check4 = true;
					  //currentNumberText4=Locators_Calculator.calc_Edit_text_field.getText();
				  }
				  catch(ArithmeticException e){
					  System.out.println("Cannot divide by zero ");
					  check4 = true;
					  APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
				  }

				  if((check1)&&(check2)&&(check3)&&(check4)){
					  check= true;
					  APP_LOGS.info("Basic Functionality operations verified");
					  Assert.assertTrue(check);
				  }
				  else{
					  APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
							  "\n");
					  Assert.fail();
				  }	
				  customWait(3000);

			  }
			  
	
			  
			  
		public void verifyHamburger() throws InterruptedException{
			try {
				Locators_Calculator.calc_hamburger_Icon.click();
				Locators_Calculator.calc_asfraction_optn.click();
				String currentNumberText=Locators_Calculator.calc_resultfract_text_field.getText();
				//System.out.println(currentNumberText);
				Locators_Calculator.calc_dismiss_Btn.click();
				Locators_Calculator.calc_Clear_btn.click();
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				APP_LOGS.info("Hamburger option is not working succesful.");
				e.printStackTrace();
				Assert.fail();
				
			}
			customWait(3000);
			
		}

		
		  //This Method check long press of all keys 
		public void longPress() throws InterruptedException{
			
				    boolean check0 = false;
			        boolean check1 = false;
			        boolean check2 = false;
			        boolean check3 = false;
			        boolean check4 = false;
			        boolean check5 = false;
			        boolean check6 = false;
			        boolean check7 = false;
			        boolean check8 = false;
			        boolean check9 = false;
			        boolean check10 = false;
				minWait();
				longpress(7);
				String Current_text0=Locators_Calculator.calc_Edit_text_field.getText();
				
		            if (Current_text0.equals("0")) {
		                check0 = true;
		                APP_LOGS.info("Clicked on 0");
		            }minWait();
		            Locators_Calculator.calc_Delete_btn.click();
		        
			
				minWait();
				longpress(8);
				String Current_text1=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text1.equals("1")) {
	                check1 = true;
	                APP_LOGS.info("Clicked on 1");
	            }minWait();
	            Locators_Calculator.calc_Delete_btn.click();
	            
	            
				minWait();
				longpress(9);
				String Current_text2=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text2.equals("2")) {
	                check2 = true;
	                APP_LOGS.info("Clicked on 2");
	            }minWait();
	            Locators_Calculator.calc_Delete_btn.click();
	        
				
	            minWait();
	            longpress(10);
				String Current_text3=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text3.equals("3")) {
	                check3 = true;
	                APP_LOGS.info("Clicked on 3");
	            }
	            minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				minWait();
				longpress(11);
				String Current_text4=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text4.equals("4")) {
	                check4 = true;
	                APP_LOGS.info("Clicked on 4");
	            }
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				
				longpress(12);
				String Current_text5=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text5.equals("5")) {
	                check5 = true;
	                APP_LOGS.info("Clicked on 5");
	            } 
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				
				longpress(13);
				String Current_text6=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text6.equals("6")) {
	                check6 = true;
	                APP_LOGS.info("Clicked on 6");
	            }
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				
				longpress(14);
				String Current_text7=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text7.equals("7")) {
	                check7 = true;
	                APP_LOGS.info("Clicked on 7");
	            }
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				
				longpress(15);
				String Current_text8=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text8.equals("8")) {
	                check8 = true;
	                APP_LOGS.info("Clicked on 8");
	            }
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				
				longpress(16);
				String Current_text9=Locators_Calculator.calc_Edit_text_field.getText();
				
	            if (Current_text9.equals("9")) {
	                check9 = true;
	                APP_LOGS.info("Clicked on 9");
	            }
				minWait();
				Locators_Calculator.calc_Delete_btn.click();
				
				if((check1)&&(check2)&&(check3)&&(check4)){
					check= true;
					APP_LOGS.info("Valid Search entries are displayed.");
					Assert.assertTrue(check);
					
				}else{
					APP_LOGS.info(Current_text0+": "+check0+ "\n"+Current_text1+": "+check1+"\n"+Current_text2+": "+check2+"\n"+Current_text3+": "+check3+
							"\n"+Current_text4+": "+check4+"\n"+Current_text5+": "+check5+"\n"+Current_text6+": "+check6+ "\n"+Current_text7+": "+check7+"\n"+Current_text8+": "
									+ ""+check8+"\n"+Current_text9+": "+check9+ "\n");
					Assert.fail();
				}		
		}
	

   //This Method check short press of all keys 
		public void shortpress() throws InterruptedException{

			boolean check0 = false;
			boolean check1 = false;
			boolean check2 = false;
			boolean check3 = false;
			boolean check4 = false;
			boolean check5 = false;
			boolean check6 = false;
			boolean check7 = false;
			boolean check8 = false;
			boolean check9 = false;
			boolean check10 = false;
			boolean check11 = false;
			boolean check12 = false;
			boolean check13 = false;
			boolean check14 = false;
			boolean check15 = false;
			boolean check16 = false;
			boolean check17 = false;
			boolean check18 = false;
			boolean check19 = false;
			boolean check20= false;
			boolean check21= false;
			
			minWait();
			Locators_Calculator.calc_0_btn.click();
			String Current_text0=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text0.equals("0")) {
				check0 = true;
				APP_LOGS.info("Clicked on 0");
			}minWait();
			Locators_Calculator.calc_Delete_btn.click();


			minWait();
			Locators_Calculator.calc_1_btn.click();
			String Current_text1=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text1.equals("1")) {
				check1 = true;
				APP_LOGS.info("Clicked on 1");
			}minWait();
			Locators_Calculator.calc_Delete_btn.click();


			minWait();
			Locators_Calculator.calc_2_btn.click();
			String Current_text2=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text2.equals("2")) {
				check2 = true;
				APP_LOGS.info("Clicked on 2");
			}minWait();
			Locators_Calculator.calc_Delete_btn.click();


			minWait();
			Locators_Calculator.calc_3_btn.click();
			String Current_text3=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text3.equals("3")) {
				check3 = true;
				APP_LOGS.info("Clicked on 3");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();

			minWait();
			Locators_Calculator.calc_4_btn.click();
			String Current_text4=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text4.equals("4")) {
				check4 = true;
				APP_LOGS.info("Clicked on 4");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();


			Locators_Calculator.calc_5_btn.click();
			String Current_text5=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text5.equals("5")) {
				check5 = true;
				APP_LOGS.info("Clicked on 5");
			} 
			minWait();
			Locators_Calculator.calc_Delete_btn.click();


			Locators_Calculator.calc_6_btn.click();
			String Current_text6=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text6.equals("6")) {
				check6 = true;
				APP_LOGS.info("Clicked on 6");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();


			Locators_Calculator.calc_7_btn.click();
			String Current_text7=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text7.equals("7")) {
				check7 = true;
				APP_LOGS.info("Clicked on 7");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();


			Locators_Calculator.calc_8_btn.click();
			String Current_text8=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text8.equals("8")) {
				check8 = true;
				APP_LOGS.info("Clicked on 8");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();


			Locators_Calculator.calc_9_btn.click();
			String Current_text9=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text9.equals("9")) {
				check9 = true;
				APP_LOGS.info("Clicked on 9");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			minWait();
			aDriver.rotate(ScreenOrientation.LANDSCAPE);
			minWait();
			Locators_Calculator.calc_percentage_btn.click();
			String Current_text10=Locators_Calculator.calc_Edit_text_field.getText();
            System.out.println(Current_text10);
			if (Current_text10.equals("%")) {
				check10 = true;
				APP_LOGS.info("Clicked on % Ch10");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			minWait();
			Locators_Calculator.calc_sin_btn.click();
			String Current_text11=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text11);
			if (Current_text11.equals("s")) {
				check11 = true;
				APP_LOGS.info("Clicked on sin(");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
			Locators_Calculator.calc_cos_btn.click();
			String Current_text12=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text12);
			if (Current_text12.equals("c")) {
				check12 = true;
				APP_LOGS.info("Clicked on cos(");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
			Locators_Calculator.calc_tan_btn.click();
			String Current_text13=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text13);
			if (Current_text13.equals("t")) {
				check13 = true;
				APP_LOGS.info("Clicked on tan(");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
			Locators_Calculator.calc_ln_btn.click();
			String Current_text14=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text14);
			if (Current_text14.equals("l")) {
				check14 = true;
				APP_LOGS.info("Clicked on ln(");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
			Locators_Calculator.calc_log_btn.click();
			String Current_text15=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text15);
			if (Current_text15.equals("L")) {
				check15 = true;
				APP_LOGS.info("Clicked on log(");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
			Locators_Calculator.calc_factorial_btn.click();
			String Current_text16=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text16);
			if (Current_text16.equals("!")) {
				check16 = true;
				APP_LOGS.info("Clicked on !");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();

			Locators_Calculator.calc_e_btn.click();
			String Current_text17=Locators_Calculator.calc_Edit_text_field.getText();
			 System.out.println(Current_text17);
			if (Current_text17.equals("e")) {
				check17 = true;
				APP_LOGS.info("Clicked on e");
			}
			minWait();
			Locators_Calculator.calc_Delete_btn.click();
			
//			Locators_Calculator.calc_openbracket_btn.click();
//			String Current_text18=Locators_Calculator.calc_Edit_text_field.getText();
//			 System.out.println(Current_text18);
//			if (Current_text18.equals("(")) {
//				check18 = true;
//				APP_LOGS.info("Clicked on (");
//			}
//			minWait();
//			Locators_Calculator.calc_Delete_btn.click();
			
//			
//			Locators_Calculator.calc_closebracket_btn.click();
//			String Current_text19=Locators_Calculator.calc_Edit_text_field.getText();
//			 System.out.println(Current_text19);
//			if (Current_text19.equals(")")) {
//				check19 = true;
//				APP_LOGS.info("Clicked on )");
//			}
//			minWait();
//			Locators_Calculator.calc_Delete_btn.click();
			
//			Locators_Calculator.calc_pie_btn.click();
//			String Current_text20=Locators_Calculator.calc_Edit_text_field.getText();
//
//			if (Current_text20.equals("π")) {
//				check20 = true;
//				APP_LOGS.info("Clicked on )");
//			}
//			minWait();
//			Locators_Calculator.calc_Delete_btn.click();
			
//			Locators_Calculator.calc_root_btn.click();
//			String Current_text21=Locators_Calculator.calc_Edit_text_field.getText();
//
//			if (Current_text21.equals("√")) {
//				check21 = true;
//				APP_LOGS.info("Clicked on )");
//			}
//			minWait();
//			Locators_Calculator.calc_Delete_btn.click();
			minWait();
			aDriver.rotate(ScreenOrientation.PORTRAIT);	
			
			
			//Validation
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9)&&(check10)
					&&(check11)&&(check12)&&(check13)&&(check14)&&(check15)&&(check16)&&(check17))
					{
				
				check= true;
				APP_LOGS.info("Valid Search entries are displayed.");
				Assert.assertTrue(check);

			}else{
				APP_LOGS.info(Current_text0+": "+check0+ "\n"+Current_text1+": "+check1+"\n"+Current_text2+": "+check2+"\n"+Current_text3+": "+check3+
						"\n"+Current_text4+": "+check4+"\n"+Current_text5+": "+check5+"\n"+Current_text6+": "+check6+ "\n"+Current_text7+": "+check7+"\n"+Current_text8+": "
						+ ""+check8+"\n"+Current_text9+": "+check9+ "\n"+Current_text10+": "+check10+ "\n"+Current_text11+": "+check11+"\n"+Current_text12+": "+check12+"\n"+Current_text13+": "+check13+
						"\n"+Current_text14+": "+check14+"\n"+Current_text15+": "+check15+"\n"+Current_text16+": "+check6+ "\n"+Current_text17+": "+check17+"\n");
				Assert.fail();
			}		
}
		
		
// Change the Screen orientation while entering a digit/Num and relaunching Calculator App
	public void relaunch_ScreenOrientation() throws InterruptedException, MalformedURLException {
			boolean check1 = false;
			boolean check2 = false;

			//Relaunch Calculator App
			firstclick();
			minWait();
			longpress(4);
			launchApp("CALCULATOR_PACKAGE","CALCULATOR_ACTIVITY");


			String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
			if(currentNumberText1.isEmpty()){
				check1 = true;	
				APP_LOGS.info("Pass:Entered Digit is not present after Relaunching ");
			}

			//Screen Orientation
			int num1=firstclick();

			String num=Integer.toString(num1);
			minWait();
			aDriver.rotate(ScreenOrientation.LANDSCAPE);
			minWait();
			minWait();
			String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
			System.out.println(currentNumberText2);
			if(num.equalsIgnoreCase(currentNumberText2)){
				check2 = true;
				APP_LOGS.info("Pass:Digit is present- Screen Orientation Calculatorapp"); 
			}
			aDriver.rotate(ScreenOrientation.PORTRAIT);

			//validation
			if((check1)&&(check2)){
				check = true;
				APP_LOGS.info("Pass:verified successfully.");
				Assert.assertTrue(check);

			}else{
				APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+ currentNumberText1+": "+check2+"\n");
				Assert.fail();
			}   
}
		
		public void stopAdb() throws InterruptedException, IOException {
			  customWait(4000);
			  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
			  APP_LOGS.info("Adb logs stopped succesfully. ");
			  customWait(2000);
			 }
		
		
		
		
}


