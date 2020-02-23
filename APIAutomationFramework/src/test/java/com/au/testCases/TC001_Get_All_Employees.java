package com.au.testCases;

import org.apache.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.au.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TC001_Get_All_Employees extends TestBase {
	
@BeforeClass
void getAllEmployees() throws InterruptedException {
	
	logger.info("***************Started test TC001_Get_All_Employees******************");
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();
	response = httpRequest.request(io.restassured.http.Method.GET, "/employees");
	Thread.sleep(5);
}

@Test
void checkBodyResponse() {
	
	logger.info("***************Started test Checking Body resonse******************");
	String responseBody = response.getBody().asString();
	System.out.println(responseBody);
	logger.info("Response Body ==>" + responseBody);
	Assert.assertTrue(responseBody != null);
}

@Test
void checkStatusCode() {
	
	logger.info("***************Started test Checking Status Code******************");
	int statusCode = response.getStatusCode();
	logger.info("Status code is ==>" + statusCode);
	Assert.assertEquals(statusCode , 200);
}

@Test
void checkResponseTime() {
	
	logger.info("***************Started test Checking Response time******************");
	long responseTime = response.getTime();
	logger.info("Response time  is ==>" + responseTime);
	if(responseTime > 2000) {
		logger.warn("Response time is greater than 2000");
	}
	
	Assert.assertTrue(responseTime < 1500);
	
	
}

@Test
void checkStatusLine() {
	
	logger.info("***************Started test Checking Status Line******************");
	String  statusLine = response.getStatusLine();
	logger.info("Status line is ==>" + statusLine);
	Assert.assertEquals(statusLine , "HTTP/1.1 200 OK");
}

@Test
void checkContentType() {
	
	logger.info("***************Started test Checking Content type******************");
	String  contentType = response.header("Content-Type");
	logger.info("Content type is ==>" + contentType);
	Assert.assertEquals(contentType , "application/json;charset=utf-8");
}

@Test
void checkServerType() {
	
	logger.info("***************Started test Checking Server type******************");
	String  serverType = response.header("Server");
	logger.info("Server type is ==>" + serverType);
	Assert.assertEquals(serverType , "nginx/1.16.0");
}

@Test
void checkContentEncoding() {
	
	logger.info("***************Started test Checking Content Encoding******************");
	String  contentEncoding = response.header("Content-Encoding");
	logger.info("Content Encoding is ==>" + contentEncoding);
	Assert.assertEquals(contentEncoding , "gzip");
}

@Test
void checkContentLength() {
	
	logger.info("***************Started test Checking Content length******************");
	String  contentLength = response.header("Content-Length");
	logger.info("Content Length is ==>" + contentLength);
	
	if(Integer.parseInt(contentLength)<100)
	{
		logger.warn("Content lenght is less than 100");
	}
	Assert.assertTrue(Integer.parseInt(contentLength)>100);
}

@Test
void checkcookies() {
	
	logger.info("***************checking cookies******************");
	String  cookies = response.getCookie("PHPSESSID");
	
}

@AfterClass
void tearDown() {
	logger.info("***************Finished test TC001_Get_All_Employees******************");
}

}
