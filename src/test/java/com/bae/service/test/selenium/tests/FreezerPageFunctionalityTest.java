//package com.bae.service.test.selenium.tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.PageFactory;
//
//import com.bae.service.test.selenium.constants.Constants;
//import com.bae.service.test.selenium.pages.FreezerPage;
//
//public class FreezerPageFunctionalityTest {
//
//	private WebDriver driver;
//	
//	private String validInput = "garage freezer";
//	private String numberInput = "34";
//	private String specialCharacterInput = "£";
//	private String shortCharacterCount = "hello";
//
//	@Before
//	public void setup() {
//		System.setProperty(Constants.PROPERTY, Constants.PATH);
//		ChromeOptions opt = new ChromeOptions();
//		opt.setHeadless(true);
//		driver = new ChromeDriver(opt);
//	}
//	
//	@Test
//	public void test() throws InterruptedException {
//		this.driver.manage().window().setSize(new Dimension(1600,700));
//		this.driver.get(Constants.ip+"/FreezerApplication/Freezer.html");
//		
//		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
//		assertFalse(freezerPage.emptyFreezer());
//		freezerPage.createFreezer(validInput); 
//		Thread.sleep(2000);
//		assertTrue(freezerPage.hasFreezerBeenAdded());
//		assertEquals(freezerPage.hasFreezerBeenAddedCorrectly(),validInput);
//		
//		freezerPage.createFreezer(numberInput);
//		String alert = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name (No numbers)",alert);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearAddInput();
//		Thread.sleep(2000);
//		
//		freezerPage.createFreezer(specialCharacterInput);
//		String alert2 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name (No special characters)",alert2);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearAddInput();
//		Thread.sleep(2000); 
//		
//		freezerPage.createFreezer(shortCharacterCount);
//		String alert3 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name of 6 characters or more",alert3);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearAddInput();
//		Thread.sleep(2000);
//		
//		freezerPage.createFreezer("");
//		String alert4 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name",alert4);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearAddInput();
//		Thread.sleep(2000);
//		System.out.println("end of create tests");
//		
//		freezerPage.deleteFreezer(validInput);
//		this.driver.switchTo().alert().dismiss();
//		freezerPage.hasFreezerBeenAdded();
//		assertEquals(freezerPage.hasFreezerBeenAddedCorrectly(),validInput);
//		freezerPage.clearDeleteInput();
//		
//		
//		freezerPage.deleteFreezer(validInput);
//		this.driver.switchTo().alert().accept();
//		assertFalse(freezerPage.hasBeenDeletedCorrectly());
//		
//		freezerPage.deleteFreezer(numberInput);
//		String alert5 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name (No numbers)",alert5);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearDeleteInput();
//		
//		freezerPage.deleteFreezer(specialCharacterInput);
//		String alert6 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name (No special characters)",alert6);;
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearDeleteInput();
//		
//		freezerPage.deleteFreezer(shortCharacterCount);
//		String alert7 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name of 6 characters or more",alert7);;
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearDeleteInput();
//		
//		freezerPage.deleteFreezer("");
//		String alert8 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid freezer name",alert8);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearDeleteInput();
//		
//		freezerPage.deleteFreezer(validInput);
//		this.driver.switchTo().alert().accept();
//		Thread.sleep(1000);
//		String alert9 = this.driver.switchTo().alert().getText();
//		assertEquals("Freezer name is not in the list",alert9);
//		this.driver.switchTo().alert().accept();
//		freezerPage.clearDeleteInput();
//		System.out.println("end of delete tests");
//		
//	}
//	
//	@After
//	public void tearDown() {
//		
//		this.driver.close();
//	}
//
//}
