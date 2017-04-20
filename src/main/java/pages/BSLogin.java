package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BSLogin {

	WebDriver driver;
	
	By bsName = By.name("employee[username]");
	By bsPassword = By.name("employee[password]");
	By btnLogin = By.name("commit");
	
	
	public BSLogin(WebDriver driver) {
		this.driver = driver;
	}
	
	
	//set user name in textbox
	
	public void setUserName(String strUserName) {
		driver.findElement(bsName).sendKeys(strUserName);
	}
	
	
	//set password in password textbox
	
	public void setPassword(String strPassword) {
		driver.findElement(bsPassword).sendKeys(strPassword);
	}
	
	
	//click on login button
	
	public void clickLogin(){
		driver.findElement(btnLogin).click();
	}

		
	public void loginToBlueSource(String strUserName, String strPassword) {
		//fill user name
		this.setUserName(strUserName);
		
		//fill password
		this.setPassword(strPassword);
		
		//click login button
		this.clickLogin();
	}
	
}
