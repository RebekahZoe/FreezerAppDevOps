//package com.bae.service.test.selenium.tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.concurrent.TimeUnit;
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
//import com.bae.service.test.selenium.pages.IndividualFreezerPage;
//
//public class IndividualFreezerPageFunctionalityTest {
//
//private WebDriver driver;
//	
//	
//	private String freezerName = "Garage Freezer";
//	private String validNameInput = "curry";
//	private String letters = "abc";
//	private String numberInput = "3";
//	private String numberInput2 = "2";
//	private String numberInput3 = "0";
//	private String decimal = "0.5";
//	private String specialCharacterInput = "£";
//	private String shortCharacterCount = "he";
//	
//	
//
//	@Before
//	public void setup() {
//		System.setProperty(Constants.PROPERTY, Constants.PATH);
//		ChromeOptions opt = new ChromeOptions();
//		opt.setHeadless(true);
//		driver = new ChromeDriver(opt);
//		
//	}
//	
//	@Test
//	public void test() throws InterruptedException {
//		this.driver.manage().window().setSize(new Dimension(1920,1080));
//		this.driver.get(Constants.ip+"/FreezerApplication/Freezer.html");
//		
//		FreezerPage freezerPage = PageFactory.initElements(driver, FreezerPage.class);
//		IndividualFreezerPage iFPage = PageFactory.initElements(driver, IndividualFreezerPage.class);
//		
//        freezerPage.createFreezer(freezerName);
//        Thread.sleep(1000);
//        freezerPage.individualFreezer();
//        
//        
//        
//        assertEquals(iFPage.getTitle(), freezerName);
//        
//        iFPage.editItem(validNameInput, numberInput);
//        Thread.sleep(1000);
//        String notInList = this.driver.switchTo().alert().getText();
//        assertEquals("Item is not in this freezer",notInList);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.createItem(validNameInput, numberInput);
//        Thread.sleep(500);
//        assertTrue(iFPage.hasItemBeenAdded());
//        assertEquals(iFPage.hasItemBeenAddedCorrectly(),(validNameInput + numberInput));
//        iFPage.clearAddInput();
//        
//        
//        iFPage.createItem(validNameInput,decimal);
//        String alert = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (must be a whole number)",alert);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(validNameInput, specialCharacterInput);
//        String alert2 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (no special characters)",alert2);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(validNameInput, letters);
//        String alert3 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (no letters)",alert3);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(validNameInput, numberInput3);
//        String alert4 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (must be greater than 0)",alert4);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(numberInput,decimal);
//        String alert5 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be a whole number)",alert5);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(numberInput, specialCharacterInput);
//        String alert6 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no special characters)",alert6);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(numberInput, letters);
//        String alert7 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no letters)",alert7);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(numberInput, numberInput3);
//        String alert8 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be greater than 0)",alert8);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//		
//        iFPage.createItem(specialCharacterInput,decimal);
//        String alert9 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be a whole number)",alert9);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(specialCharacterInput, specialCharacterInput);
//        String alert10 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no special characters)",alert10);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(specialCharacterInput, letters);
//        String alert11 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no letters)",alert11);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(specialCharacterInput, numberInput3);
//        String alert12 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be greater than 0)",alert12);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//		
//        iFPage.createItem(shortCharacterCount,decimal);
//        String alert13 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be a whole number)",alert13);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(shortCharacterCount, specialCharacterInput);
//        String alert14 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no special characters)",alert14);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(shortCharacterCount, letters);
//        String alert15 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no letters)",alert15);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(shortCharacterCount, numberInput3);
//        String alert16 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be greater than 0)",alert16);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//		
//        iFPage.createItem(shortCharacterCount, numberInput);
//        String alert17 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters)",alert17);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(specialCharacterInput, numberInput);
//        String alert18 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters)",alert18);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//        
//        iFPage.createItem(numberInput, numberInput);
//        String alert19 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers)",alert19);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearAddInput();
//
//        System.out.println("end of add testing");
//        
//        iFPage.editItem(validNameInput, numberInput2);
//        Thread.sleep(500);
//        assertEquals(iFPage.hasItemBeenAddedCorrectly(),(validNameInput + numberInput2));
//        iFPage.clearEditInput();
//        
//        
//        iFPage.editItem(validNameInput,decimal);
//        String alert20 = this.driver.switchTo().alert().getText();
//        Thread.sleep(500);
//        assertEquals("Please enter a valid quantity (must be a whole number)",alert20);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(validNameInput, specialCharacterInput);
//        String alert21 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (no special characters)",alert21);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(validNameInput, letters);
//        String alert22 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (no letters)",alert22);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(validNameInput, numberInput3);
//        String alert23 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid quantity (must be greater than 0)",alert23);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(numberInput,decimal);
//        String alert24 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be a whole number)",alert24);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(numberInput, specialCharacterInput);
//        String alert25 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no special characters)",alert25);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(numberInput, letters);
//        String alert26 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (no letters)",alert26);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(numberInput, numberInput3);
//        String alert27 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers) and a valid quantity (must be greater than 0)",alert27);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//		
//        iFPage.editItem(specialCharacterInput,decimal);
//        String alert28 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be a whole number)",alert28);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(specialCharacterInput, specialCharacterInput);
//        String alert29 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no special characters)",alert29);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(specialCharacterInput, letters);
//        String alert30 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (no letters)",alert30);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(specialCharacterInput, numberInput3);
//        String alert31 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters) and a valid quantity (must be greater than 0)",alert31);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//		
//        iFPage.editItem(shortCharacterCount,decimal);
//        String alert32 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be a whole number)",alert32);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(shortCharacterCount, specialCharacterInput);
//        String alert33 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no special characters)",alert33);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(shortCharacterCount, letters);
//        String alert34 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (no letters)",alert34);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(shortCharacterCount, numberInput3);
//        String alert35 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters) and a valid quantity (must be greater than 0)",alert35);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//		
//        iFPage.editItem(shortCharacterCount, numberInput);
//        String alert36 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (must be at least 3 characters)",alert36);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(specialCharacterInput, numberInput);
//        String alert37 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No special characters)",alert37);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//        
//        iFPage.editItem(numberInput, numberInput);
//        String alert38 = this.driver.switchTo().alert().getText();
//        assertEquals("Please enter a valid item name (No numbers)",alert38);
//        this.driver.switchTo().alert().dismiss();
//        iFPage.clearEditInput();
//
//        System.out.println("edit testing finished");
//        
//        Thread.sleep(1000);
//        iFPage.deleteItem(validNameInput);
//		this.driver.switchTo().alert().dismiss();
//		iFPage.hasItemBeenAdded();
//		assertEquals(iFPage.hasItemNotBeenDeleted(), validNameInput);
//		iFPage.clearDeleteInput();
//		
//		Thread.sleep(1000);
//		iFPage.deleteItem(validNameInput);
//		this.driver.switchTo().alert().accept();
//		assertFalse(iFPage.hasBeenDeletedCorrectly());
//		
//		Thread.sleep(1000);
//		iFPage.deleteItem(numberInput);
//		String alert39 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid item name (No numbers)", alert39);
//		this.driver.switchTo().alert().accept();
//		iFPage.clearDeleteInput();
//
//		Thread.sleep(1000);
//		iFPage.deleteItem(specialCharacterInput);
//		String alert40 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid item name (No special characters)", alert40);
//		this.driver.switchTo().alert().accept();
//		iFPage.clearDeleteInput();
//		
//		Thread.sleep(1000);
//		iFPage.deleteItem(shortCharacterCount);
//		String alert41 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid item name (must be at least 3 characters)", alert41);
//		this.driver.switchTo().alert().accept();
//		iFPage.clearDeleteInput();
//
//		Thread.sleep(1000);
//		iFPage.deleteItem("");
//		String alert42 = this.driver.switchTo().alert().getText();
//		assertEquals("Please enter a valid item name", alert42);
//		this.driver.switchTo().alert().accept();
//		iFPage.clearDeleteInput();
//
//		Thread.sleep(1000);
//		iFPage.deleteItem(validNameInput);
//		
//		this.driver.switchTo().alert().accept();
//		Thread.sleep(2000L);
//		String alert43 = this.driver.switchTo().alert().getText();
//		assertEquals("Item is not in this freezer", alert43);
//		this.driver.switchTo().alert().accept();
//		iFPage.clearDeleteInput();
//		System.out.println("end of delete test");
//		
//		this.driver.get("http://35.176.212.133:8181/FreezerApplication/Freezer.html");
//		freezerPage.deleteFreezer(freezerName);
//		this.driver.switchTo().alert().accept();
//	}
//	
//	@After
//	public void tearDown() {
//		
//		this.driver.close();
//	}
//}
