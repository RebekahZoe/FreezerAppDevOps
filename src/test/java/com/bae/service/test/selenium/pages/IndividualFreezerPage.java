package com.bae.service.test.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndividualFreezerPage {

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

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/form/div/input[1]")
	private WebElement itemAddInput;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/form/div/input[2]")
	private WebElement quantityAddInput;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/form/div/button")
	private WebElement AddButton;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[2]/div/form/div/input[1]")
	private WebElement itemEditInput;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[2]/div/form/div/input[2]")
	private WebElement quantityEditInput;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[2]/div/form/div/button")
	private WebElement editButton;

	@FindBy(xpath = "/html/body/div/div/div/div[3]/div[3]/div/form/div/input")
	private WebElement itemDeleteInput;

	@FindBy(xpath = "//*[@id=\"delete\"]/div/form/button")
	private WebElement deleteButton;

	@FindBy(xpath = "/html/body/div/div/div/div[2]/table/tr[2]/td[1]")
	private WebElement itemInList;

	@FindBy(xpath = "/html/body/div/div/div/div[2]/table/tr[2]/td[2]")
	private WebElement quantityInList;

	@FindBy(xpath = "/html/body/header/div[2]/div/div/div/h1")
	private WebElement title;

	public void createItem(String name, String quantity) {
		this.itemAddInput.sendKeys(name);
		this.quantityAddInput.sendKeys(quantity);
		this.AddButton.click();
	}

	public void editItem(String name, String quantity) {
		this.itemEditInput.sendKeys(name);
		this.quantityEditInput.sendKeys(quantity);
		this.editButton.click();
	}

	public void deleteItem(String name) {
		this.itemDeleteInput.sendKeys(name);
		this.deleteButton.click();
	}

	public boolean noItems() {
		try {
			itemInList.isDisplayed();
			quantityInList.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean hasItemBeenAdded() {
		try {
			itemInList.isDisplayed();
			quantityInList.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String hasItemBeenAddedCorrectly() {
		return (this.itemInList.getText() + this.quantityInList.getText());
	}
	public String hasItemNotBeenDeleted() {
		return this.itemInList.getText();
	}

	public boolean hasBeenDeletedCorrectly() {
		try {
			itemInList.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTitle() {
		return this.title.getText();
	}

	public void clearAddInput() {
		this.itemAddInput.clear();
		this.quantityAddInput.clear();
	}

	public void clearEditInput() {
		this.itemEditInput.clear();
		this.quantityEditInput.clear();
	}

	public void clearDeleteInput() {
		this.itemDeleteInput.clear();
	}

}
