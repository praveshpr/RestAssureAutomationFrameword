package com.au.base;





import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "3";// hard coded to get single emp details and to update
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		//add logger
		logger = Logger.getLogger("RestAPIFramework");
		PropertyConfigurator.configure("Resource/Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
