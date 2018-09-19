package com.SeleniumAndARD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NAMS_AddExchange {

	// Global Variables have been declared
	private static WebDriver driver = new FirefoxDriver();
	private static int i = 0;
	private static String result = "";
	private static String remarks = "";

	// Repository Code is the main code used for Automation
	private static String repositorycode(int journeyOrder, String OT)
			throws InterruptedException, BiffException, IOException {

		// Selenium and Manual logs Generation
		Logger logs = Logger.getLogger("repositorycode");
		logs.debug("Getting logs for NAMS_AddExchange");

		// Declaration of result in report if the Test Case fails
		result = "Fail";
		remarks = "Test Case Failed. Please check logs for more details.";

		// Reading input excel sheet
		logs.debug("Fetching Excel Sheet 'NAMS Exchange Input Sheet.xls' ");
		String FilePath = "P:\\tsalat\\Selenium\\\\Input Sheets\\NAMS Exchange Input Sheet.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		logs.debug("Excel sheet fetched successfully");

		// Reading respective workbook from above sheet
		logs.debug("Fetching WorkBook from Excel Sheet 'NAMS Exchange Input Sheet.xls' ");
		Workbook wb = Workbook.getWorkbook(fs);
		Sheet sh = wb.getSheet(OT);
		logs.debug("Workbook fetched successfully");

		int totalNoOfRows = sh.getRows();
		int totalNoOfCols = sh.getColumns();
		String[][] arr = new String[totalNoOfCols][totalNoOfRows];

		// for loop to fetch data from excel into array
		logs.debug("Fetching Data from input excel");
		for (int row = 0; row < totalNoOfRows; row++) {
			for (int col = 0; col < totalNoOfCols; col++) {

				arr[col][row] = sh.getCell(col, row).getContents();
			}
		}
		logs.debug("Data from input excel fetched successfully");
		// String that retrieves FieldType from file
		if (journeyOrder == 1) {
			try {
				for (int row = 1; row < 5; row++) {
					int colft = 5;
					int colval = 7;
					String FieldType = arr[colft][row];

					if (FieldType.equals("url")) {
						logs.debug("Opening url 'portal.talktalkplc.com'");
						Thread.sleep(9000L);
						driver.get((arr[colval][row]) + " ");
						System.out.println("opened url" + arr[colval][row]
								+ "driver:" + driver);
						driver.manage().window().maximize();
						logs.debug("URL opened successfully");

					}

					else if (FieldType.equals("TextField")) {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						driver.findElement(By.xpath(arr[colft - 1][row]))
								.clear();
						driver.findElement(By.xpath(arr[colft - 1][row]))
								.sendKeys(arr[colval][row]);
						System.out
								.println("entered text" + arr[colft - 1][row]);

					}

					else if (FieldType.equals("Button")) {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						driver.findElement(By.xpath(arr[colft - 1][row]))
								.click();
						// System.out.println("clicked"+arr[colft-1][row]);
						// Log.info(driver.findElement(By.xpath(arr[colft-1][row])).getTagName()+" is clicked");
						Thread.sleep(5000L);
					}
				}
			} catch (NoSuchElementException e) {

				System.out.println("Exception occurred:" + e);
				e.printStackTrace();
				return e.toString();
			}
		}

		// Below for loop will help page going back to login page.
		for (int row = 5; row < totalNoOfRows; row++) {
			int colft = 5;
			int colval = 7;

			String FieldType = arr[colft][row];

			if (FieldType.equals("TextField")) {
				try {
					WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
					myWaitVar.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(arr[colft - 1][row])));
					System.out.println(arr[colft][row] + " "
							+ arr[colft - 1][row] + " " + arr[colval][row]
							+ " " + arr[5][row]);
					driver.findElement(By.xpath(arr[colft - 1][row])).clear();
					driver.findElement(By.xpath(arr[colft - 1][row])).sendKeys(
							arr[colval][row]);
					System.out.println("entered text" + arr[colft - 1][row]);
				} catch (WebDriverException we) {
					System.out.println("Textfield Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			}

			else if (FieldType.equals("Button")) {
				try {
					Thread.sleep(1000L);
					WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
					myWaitVar.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(arr[colft - 1][row])));
					System.out.println(arr[colft][row] + " "
							+ arr[colft - 1][row] + " " + arr[colval][row]
							+ " " + arr[5][row]);
					driver.findElement(By.xpath(arr[colft - 1][row])).click();
					System.out.println("clicked" + arr[colft - 1][row]);
				} catch (WebDriverException we) {
					System.out.println("Button Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			} else if (FieldType.equals("Link")) {
				try {
					WebDriverWait myWaitVar = new WebDriverWait(driver, 500);
					myWaitVar.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(arr[colft - 1][row])));
					System.out.println(arr[colft][row] + " "
							+ arr[colft - 1][row] + " " + arr[colval][row]
							+ " " + arr[5][row]);
					driver.findElement(By.xpath(arr[colft - 1][row])).click();
					System.out.println("link" + arr[colft - 1][row]);
					// Thread.sleep(1000L);
				} catch (WebDriverException we) {
					System.out.println("Link Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			}

			else if (FieldType.equals("Thread")) {
				Thread.sleep(5000L);
			}

			else if (FieldType.equals("DropDown")) {
				try {
					WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
					myWaitVar.until(ExpectedConditions
							.visibilityOfElementLocated(By
									.xpath(arr[colft - 1][row])));
					System.out.println(arr[colft][row] + " "
							+ arr[colft - 1][row] + " " + arr[colval][row]
							+ " " + arr[5][row]);
					new Select(
							driver.findElement(By.xpath(arr[colft - 1][row])))
							.selectByVisibleText(arr[colval][row]);
					System.out.println("selected dropdown"
							+ arr[colft - 1][row]);
				} catch (WebDriverException we) {
					System.out.println("Dropdown Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}

			} else if (FieldType.equals("ReadData")) {
				try {
					if (arr[colval][row].equals("NA")) {
						System.out.println("NA");
					} else {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 500);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						String actval = driver.findElement(
								By.xpath(arr[colft - 1][row])).getText();
						System.out.println("value : " + actval);
					}

				} catch (WebDriverException we) {
					System.out.println("ReadData Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			} else if (FieldType.equals("MouseMove")) {
				try {
					if (arr[colval][row].equals("NA")) {
						System.out.println("NA");
					} else {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 100);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						Actions action = new Actions(driver);
						WebElement ele = driver.findElement(By
								.xpath(arr[colft - 1][row]));
						String text = ele.getText();
						System.out.println("text:" + text);
						action.moveToElement(ele).build().perform();
						System.out.println("mouse moved");
					}
				} catch (WebDriverException we) {
					System.out.println("MouseMove Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			} else if (FieldType.equals("Snapshot")) {
				Thread.sleep(5000L);
				File file = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				File file2 = new File(arr[colval][row]);
				FileUtils.copyFile(file, file2);
			}

			else if (FieldType.equalsIgnoreCase("Success")) {
				result = "Pass";
				remarks = "NA";
			} else if (FieldType.equals("ReadValid")) {
				try {
					// Here NA means, required only read data but not
					// validation; so that the data read can be reused by
					// storing locally
					if (arr[8][row].equalsIgnoreCase("NA")) {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 500);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						String actval = driver.findElement(
								By.xpath(arr[colft - 1][row])).getText();
						System.out.println("value : " + actval);
					}

					else {
						WebDriverWait myWaitVar = new WebDriverWait(driver, 500);
						myWaitVar.until(ExpectedConditions
								.visibilityOfElementLocated(By
										.xpath(arr[colft - 1][row])));
						System.out.println(arr[colft][row] + " "
								+ arr[colft - 1][row] + " " + arr[colval][row]
								+ " " + arr[5][row]);
						String actval = driver.findElement(
								By.xpath(arr[colft - 1][row])).getText();
						System.out.println("value : " + actval);

						if (arr[8][row].equalsIgnoreCase(actval)) {
							result = "pass";
							remarks = "NA";
						} else {
							result = "fail";
							remarks = "Actual output: " + actval
									+ "is not as expected: " + arr[8][row];
						}
					}
				} catch (WebDriverException we) {
					System.out.println("ReadValid Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			} else if (FieldType.equals("Form")) {
				try {
					Alert alt = driver.switchTo().alert();
					alt.accept();
					WebDriverWait myWaitVar = new WebDriverWait(driver, 200);
					System.out.println("OK Button clicked");
				} catch (WebDriverException we) {
					System.out.println("ReadValid Exception Occurred:" + we);
					we.printStackTrace();
					return "caught";
				}
			}
		}
		return null;
	}

	// Respective scenario methods have been created
	public static String Add_Exchange(int journeyOrder) throws BiffException,
			InterruptedException, IOException {
		System.out.println("Adding of Exchange in progress");
		NAMS_AddExchange AddEx = new NAMS_AddExchange();
		String Add_POP = AddEx.repositorycode(journeyOrder, "Add Exchange");
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String status = AddEx.reportwriter("Add Exchange", result,
				df.format(dateobj), remarks);
		result = "";
		remarks = "";
		return null;
	}

	// Reportwriter
	public static String reportwriter(String TCName, String result,
			String EDate, String remarks) throws IOException {
		String bgcolor[] = { "red", "green" };

		PrintWriter out = new PrintWriter(
				new BufferedWriter(
						new FileWriter(
								"P:\\tsalat\\Selenium\\TestReport\\NAMS_ExchangeAutomation.txt",
								true)));

		{

			if (i == 0) {
				String border = "1";
				String th = "<table border = "
						+ border
						+ "><tr><th>Test Case Name</th><th>Result</th><th>Execution Date</th><th>Remarks</th></tr>";
				out.flush();
				out.println(th);
				i += 1;

			}
			try {
				System.out.println("It is in report writer");
				String appenddata = "<tr><td>" + TCName + "</td>";
				if (result.equalsIgnoreCase("pass"))
					appenddata += "<td bgcolor = " + bgcolor[1] + ">" + result
							+ "</td>";
				else if (result.equalsIgnoreCase("fail"))
					appenddata += "<td bgcolor = " + bgcolor[0] + ">" + result
							+ "</td>";
				appenddata += "<td>" + EDate + "</td>";
				appenddata += "<td>" + remarks + "</td></tr>";
				out.flush();
				out.println(appenddata);
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println("Excpetion Occured: " + e);
			}
			return "Done";
		}
	}

	// TestReport generation
	public static String testreport() {
		try {
			System.out.println("It is in test report");
			BufferedReader txtfile = new BufferedReader(
					new FileReader(
							"P:\\tsalat\\Selenium\\TestReport\\NAMS_ExchangeAutomation.txt"));
			OutputStream htmlfile = new FileOutputStream(
					new File(
							"P:\\tsalat\\Selenium\\TestReport\\NAMS_ExchangeAutomation.html"));
			PrintStream printhtml = new PrintStream(htmlfile);

			String[] txtbyLine = new String[500];
			String temp = "";
			String txtfiledata = "";

			String htmlheader = "<html><head>";
			htmlheader += "<title>NAMS Exchange Test Execution Report</title>";
			htmlheader += "</head><body>";
			String htmlfooter = "</body></html>";
			int linenum = 0;

			while ((txtfiledata = txtfile.readLine()) != null) {
				txtbyLine[linenum] = txtfiledata;
				linenum++;
			}
			for (int i = 0; i < linenum; i++) {
				if (i == 0) {
					temp = htmlheader + txtbyLine[0];
					txtbyLine[0] = temp;
				}
				if (linenum == i + 1) {
					temp = txtbyLine[i] + htmlfooter;
					txtbyLine[i] = temp;
				}
				printhtml.println(txtbyLine[i]);
			}

			printhtml.close();
			htmlfile.close();
			txtfile.close();
		}

		catch (Exception e) {
			System.out.println("Exception Occured" + e);
		}
		return "Done";
	}

	// Database Verification
	@SuppressWarnings("deprecation")
	public static void DBValidation() throws BiffException, IOException {

		try {
			System.out.println("Executing Database Verification");

			// Fetching the MDFSiteID value from Input Exchange Sheet
			FileInputStream fis = new FileInputStream(
					"P:\\tsalat\\Selenium\\Input Sheets\\NAMS Exchange Input Sheet.xls");
			HSSFWorkbook wb1 = new HSSFWorkbook(fis);
			HSSFCell expectedMDFSiteID = wb1.getSheetAt(0).getRow(9)
					.getCell(CellReference.convertColStringToIndex("H"));

			System.out.println("Input MDFSiteID is " + expectedMDFSiteID);

			// Creating Columns in the output excel
			String filename = "P:\\tsalat\\Selenium\\Database Verification\\DBVerification.xls";
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("ExchangeID");
			rowhead.createCell((short) 1).setCellValue("ExchangeTypeID");
			rowhead.createCell((short) 2).setCellValue("ExchangeName");
			rowhead.createCell((short) 3).setCellValue("Address1");
			rowhead.createCell((short) 4).setCellValue("Address2");
			rowhead.createCell((short) 5).setCellValue("Address3");
			rowhead.createCell((short) 6).setCellValue("Town");
			rowhead.createCell((short) 7).setCellValue("County");
			rowhead.createCell((short) 8).setCellValue("PostCode");
			rowhead.createCell((short) 9).setCellValue("Notes");
			rowhead.createCell((short) 10).setCellValue("MDFSiteID");
			rowhead.createCell((short) 11).setCellValue("ExchangeDistrictCode");
			rowhead.createCell((short) 12).setCellValue("CountryID");
			rowhead.createCell((short) 13).setCellValue("Warnings");
			rowhead.createCell((short) 14).setCellValue("MPFLiveTUReasonID");
			rowhead.createCell((short) 15).setCellValue("SMPFLiveTUReasonID");
			rowhead.createCell((short) 16).setCellValue("ExchangeSecurityID");
			rowhead.createCell((short) 17).setCellValue("UPRN");
			rowhead.createCell((short) 18).setCellValue("FTTPLiveTUReasonID");
			rowhead.createCell((short) 19).setCellValue("OR1141Code");
			rowhead.createCell((short) 20).setCellValue("Easting");
			rowhead.createCell((short) 21).setCellValue("Northing");

			// Connecting NetworkAssetDatabase in SQLServer
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager
					.getConnection(
							"jdbc:sqlserver://11.103.7.48;database=NetworkAssetDatabase",
							"sa", "022xd12k3n");
			Statement st = con.createStatement();
			String SQLQuery = "select * from NADExchange WHERE MDFSiteID=  '"
					+ expectedMDFSiteID + "'";
			ResultSet rs = st.executeQuery(SQLQuery);

			// Printing the values of input MDFSiteID from NetworkAssetDatabase
			int i = 1;
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) i);
				row.createCell((short) 0).setCellValue(
						rs.getString("ExchangeID"));
				row.createCell((short) 1).setCellValue(
						rs.getString("ExchangeTypeID"));
				row.createCell((short) 2).setCellValue(
						rs.getString("ExchangeName"));
				row.createCell((short) 3)
						.setCellValue(rs.getString("Address1"));
				row.createCell((short) 4)
						.setCellValue(rs.getString("Address2"));
				row.createCell((short) 5)
						.setCellValue(rs.getString("Address3"));
				row.createCell((short) 6).setCellValue(rs.getString("Town"));
				row.createCell((short) 7).setCellValue(rs.getString("County"));
				row.createCell((short) 8)
						.setCellValue(rs.getString("PostCode"));
				row.createCell((short) 9).setCellValue(rs.getString("Notes"));
				row.createCell((short) 10).setCellValue(
						rs.getString("MDFSiteID"));
				row.createCell((short) 11).setCellValue(
						rs.getString("ExchangeDistrictCode"));
				row.createCell((short) 12).setCellValue(
						rs.getString("CountryID"));
				row.createCell((short) 13).setCellValue(
						rs.getString("Warnings"));
				row.createCell((short) 14).setCellValue(
						rs.getString("MPFLiveTUReasonID"));
				row.createCell((short) 15).setCellValue(
						rs.getString("SMPFLiveTUReasonID"));
				row.createCell((short) 16).setCellValue(
						rs.getString("ExchangeSecurityID"));
				row.createCell((short) 17).setCellValue(rs.getString("UPRN"));
				row.createCell((short) 18).setCellValue(
						rs.getString("FTTPLiveTUReasonID"));
				row.createCell((short) 19).setCellValue(
						rs.getString("OR1141Code"));
				row.createCell((short) 20)
						.setCellValue(rs.getString("Easting"));
				row.createCell((short) 21).setCellValue(
						rs.getString("Northing"));

				i++;
			}

			// Creating Database result output excel file
			FileOutputStream fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			// Validating Input MDFSiteID and Database MDFSiteID
			System.out.println("Executing Databse Validation");

			// Get MDFSiteID from DBVerification excel sheet
			FileInputStream fis1 = new FileInputStream(
					new File(
							"P:\\tsalat\\Selenium\\Database Verification\\DBVerification.xls"));
			HSSFWorkbook wb2 = new HSSFWorkbook(fis1);
			HSSFCell actualMDFSiteID = wb2.getSheetAt(0).getRow(1)
					.getCell(CellReference.convertColStringToIndex("K"));

			System.out.println("Actual MDFSiteID is " + actualMDFSiteID);
			System.out.println("Expected MDFSiteID is " + expectedMDFSiteID);

			// Converting actual and expected MDFSiteIDs to String
			String actualMDFSiteID_String = actualMDFSiteID.toString();
			String expectedMDFSiteID_String = expectedMDFSiteID.toString();
			System.out.println("String converted Actual MDFSiteID is "
					+ actualMDFSiteID_String);
			System.out.println("String converted Expected MDFSiteID is "
					+ expectedMDFSiteID_String);

			// If the actual and expected values match each other then the
			// validation would be successful
			if (actualMDFSiteID_String.equals(expectedMDFSiteID_String)) {
				System.out.println("Validation is successful");
			} else {
				System.out.println("Validation is unsuccessful");
			}

		} catch (Exception ex) {
			System.out.println(ex);

		}

	}

	// Main Method
	public static void main(String[] args) throws BiffException, IOException,
			InterruptedException {

		NAMS_AddExchange.Add_Exchange(1);
		NAMS_AddExchange.testreport();
		NAMS_AddExchange.DBValidation();

	}
}
