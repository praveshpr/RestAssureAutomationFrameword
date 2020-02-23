package com.au.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.au.base.TestBase;
import com.au.xlUtility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_record extends TestBase {
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void updateEmployee() throws InterruptedException {

		logger.info("***************Started test TC004_Put_Employee_record******************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();
		
		System.out.println(httpRequest + " " + empName + " " +empSalary + " " + empAge);
		JSONObject requestParam = new JSONObject();
		requestParam.put("name", empName);
		requestParam.put("salary", empSalary);
		requestParam.put("age", empAge);
		httpRequest.header("Content-Type","application/json");
		httpRequest.body(requestParam.toJSONString());
		
		response = httpRequest.request(io.restassured.http.Method.PUT, "/update/"+empID);
		System.out.println(response.asString());
		Thread.sleep(5);
	}

	@Test
	void checkBodyResponse() {

		logger.info("***************Started test Checking Body resonse******************");
		String responseBody = response.asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertTrue(responseBody != null);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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

		Assert.assertTrue(responseTime < 8000);

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
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}

	@Test
	void checkServerType() {

		logger.info("***************Started test Checking Server type******************");
		String serverType = response.header("Server");
		logger.info("Server type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}

	

	@Test
	void checkContentLength() {

		logger.info("***************Started test Checking Content length******************");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);

		if (Integer.parseInt(contentLength) < 100) {
			logger.warn("Content lenght is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength) > 50);
	}

	@Test
	void checkcookies() {

		logger.info("***************checking cookies******************");
		String cookies = response.getCookie("PHPSESSID");

	}

	@AfterClass
	void tearDown() {
		logger.info("***************Finished  test TC004_Put_Employee_record******************");
	}

}
