package com.bae.service.test.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {

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

}
