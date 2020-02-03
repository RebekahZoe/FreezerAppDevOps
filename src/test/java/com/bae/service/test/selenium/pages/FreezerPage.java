package com.bae.service.test.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FreezerPage {
	
	@FindBy(xpath = "/html/body/nav/div/a")
	private WebElement freezerAppNav;
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[1]/a")
	private WebElement homeNav;
	
	@FindBy(xpath = "/html/body/nav/div/div/ul/li[2]/a")
	private WebElement freezersNav;
	
	@FindBy(xpath = "/html/body/footer/div/div/div/ul/li/a/span/i[2]")
	private WebElement gitHubNav;
	
	public void clickHome() {
		this.homeNav.click();
	}
	public void clickFreezers() {
		this.freezersNav.click();
	}
	public void clickGitHub() {
		this.gitHubNav.click();
	}
	public void clickFreezerApp() {
		this.freezerAppNav.click();
		
	}
	
	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/form/div/input")
	private WebElement addInput;
	
	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/form/div/button")
	private WebElement addButton;
	
	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[2]/div/form/div/input")
	private WebElement deleteInput;
	
	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[2]/div/form/div/button")
	private WebElement deleteButton;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/ul/li")
	private WebElement freezer;
	
	@FindBy(xpath = "/html/body/div/div/div/div[2]/ul")
	private WebElement freezers;
	
	public void createFreezer(String name) {
		this.addInput.sendKeys(name);
		this.addButton.click();
		
	}
	
	public void deleteFreezer(String name) {
		this.deleteInput.sendKeys(name);
		this.deleteButton.click();
	}
	
	public void individualFreezer(){
		this.freezer.click();
	}
	
	public boolean emptyFreezer() {
		try {
			freezer.isDisplayed();
			return true;
			} catch (Exception e) {
			return false;
			}
	}
	
	public boolean hasFreezerBeenAdded() {
		try {
			freezer.isDisplayed();
			return true;
			} catch (Exception e) {
			return false;
			}
	}
	public String hasFreezerBeenAddedCorrectly() {
		return this.freezer.getText();
	}
	public boolean hasBeenDeletedCorrectly() {
		try {
			freezer.isDisplayed();
			return true;
			} catch (Exception e) {
			return false;
			}
	}
	public void clearAddInput() {
		this.addInput.clear();
	}
	public void clearDeleteInput() {
		this.deleteInput.clear();
	}
	
	

}
