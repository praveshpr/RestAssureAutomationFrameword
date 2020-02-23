package com.au.xlUtility;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/Myreport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Testing");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Pravesh");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed is" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());// create new entry in the report
		test.log(Status.FAIL, "Test Case Failed is" + result.getName());// to add name in the report
		test.log(Status.FAIL, "Test Case Failed is" + result.getThrowable());// to add error/exception in the report
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped is" + result.getName());

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

	}

}
