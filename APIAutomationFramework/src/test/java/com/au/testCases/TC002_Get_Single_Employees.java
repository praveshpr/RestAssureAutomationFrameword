package com.au.testCases;

import org.apache.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.au.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {

		logger.info("***************Started test TC002_Get_Single_Employee******************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		//httpRequest.header("Content-Type","application/json");
		System.out.println("emp id is" + empID);
		response = httpRequest.request(io.restassured.http.Method.GET, "/employee/"+empID);
		//response = httpRequest.request(io.restassured.http.Method.GET);
		System.out.println("+++++++++++++++++"+ response.asString());
		Thread.sleep(5000);
	}

	@Test
	void checkBodyResponse() {

		logger.info("***************Started test Checking Body resonse******************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertTrue(responseBody != null);
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	void checkStatusCode() {

		logger.info("***************Started test Checking Status Code******************");
		int statusCode = response.getStatusCode();
		logger.info("Status code is ==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {

		logger.info("***************Started test Checking Response time******************");
		long responseTime = response.getTime();
		logger.info("Response time  is ==>" + responseTime);
		if (responseTime > 2000) {
			logger.warn("Response time is greater than 2000");
		}

		Assert.assertTrue(responseTime < 700);

	}

	@Test
	void checkStatusLine() {

		logger.info("***************Started test Checking Status Line******************");
		String statusLine = response.getStatusLine();
		logger.info("Status line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {

		logger.info("***************Started test Checking Content type******************");
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {

		logger.info("***************Started test Checking Server type******************");
		String serverType = response.header("Server");
		logger.info("Server type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkContentEncoding() {

		logger.info("***************Started test Checking Content Encoding******************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkContentLength() {

		logger.info("***************Started test Checking Content length******************");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);

		if (Integer.parseInt(contentLength) < 100) {
			logger.warn("Content lenght is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength) > 100);
	}

	@Test
	void checkcookies() {

		logger.info("***************checking cookies******************");
		String cookies = response.getCookie("PHPSESSID");

	}

	@AfterClass
	void tearDown() {
		logger.info("***************Finished  test TC002_Get_Single_Employee******************");
	}

}
