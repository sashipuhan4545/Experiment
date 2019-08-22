package com.xp8.util;

import java.lang.reflect.Method;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GetMethods {

	
	
	
	 public static int TotalTestcase(String pattern, Class<?> testNGclass) throws ClassNotFoundException
	 {	
	 		/**
	 		 * This method provides the total number of test methods present in a TestNG class
	 		 */
	    
	    
	        int count = 0;

	        testNGclass.getClass();
	        Class<?> className = Class.forName(testNGclass.getName()); 

	        Method[] methods = className.getMethods();

	        for(int i=0; i<methods.length; i++)
	        {
	            String methodName = methods[i].getName();
	          //  System.out.println("Method Name: "+methodName);

	            if(methodName.contains(pattern))
	            {
	                count++;
	            }
	        }

	        return count;

	    }
	 
	 
	 @SuppressWarnings("rawtypes")
	public static ObservableList TestCasesMethodName(String pattern, Class<?> testNGclass) throws ClassNotFoundException
	 {	
	 		/**
	 		 * This method provides the total number of test methods present in a TestNG class
	 		 */
	    
	    
	      
	        ObservableList<String> value = FXCollections.observableArrayList();
	     
	        testNGclass.getClass();
	        Class<?> className = Class.forName(testNGclass.getName()); 

	        Method[] methods = className.getMethods();

	        for(int i=0; i<methods.length; i++)
	        {
	            String methodName = methods[i].getName();

	            if(methodName.contains(pattern))
	            {
		            
		            value.add(methodName);

	            }
	        }
	        return value;
			
          
	     
	      

	    }
	 
	 
	
		 
		 
		 
	 }
