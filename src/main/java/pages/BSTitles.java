package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BSTitles {

	WebDriver driver;
	
	By bsNewTitleLink = By.cssSelector("a[href='/admin/titles/new']");
	By bsNewTitleName = By.id("title_name");
	By bsCreateTitle = By.name("commit");
	
	By bsErrorMsg = By.cssSelector(".alert.alert-danger.alert-dismissable");
	By bsSuccessMsg = By.cssSelector(".alert.alert-success.alert-dismissable");
	
	
	public BSTitles(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAddTitle() {
		driver.findElement(bsNewTitleLink).click();
	}
	
	public void createNewTitle(String strNewTitleName) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//enter text
		driver.findElement(bsNewTitleName).sendKeys(strNewTitleName);
		
		//verify entered text was entered correctly
		checkEnteredText(bsNewTitleName, strNewTitleName);
		
		//click the create title button
		clickCreateTitle();
	}
	
	public void updateTitle(String strTitleName, String strUpdateText) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//enter text
		driver.findElement(bsNewTitleName).sendKeys(strUpdateText);
		
		//verify entered text was entered correctly
		checkEnteredText(bsNewTitleName, strTitleName + strUpdateText);
		
		//click the update title button
		clickCreateTitle();
	}
	
	public void checkEnteredText(By locator, String intendedText) {
		String strEnteredText = driver.findElement(locator).getAttribute("value");
		Assert.assertTrue(strEnteredText.equals(intendedText));
	}
	
	public boolean errorMsg() {
		try {
			driver.findElement(bsErrorMsg);
			return true;

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean successMsg() {
		try {
			driver.findElement(bsSuccessMsg);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean titleInList(String strNewTitleName) {
		By bsTitleInList = By.xpath("//td[contains(text(), '" + strNewTitleName + "')]");
		
		try {
			driver.findElement(bsTitleInList);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickEditTitle(String strTitleToEdit) {
		driver.findElement(By.xpath("//td[contains(text(), '" + strTitleToEdit + "')]/div/a[1]")).click();
	}
	
	public void clearTitle() {
		driver.findElement(bsNewTitleName).clear();
	}
	
	public void clickCreateTitle() {
		driver.findElement(bsCreateTitle).click();
	}
	
	public void clickDeleteTitle(String strTitleToDelete) {
		driver.findElement(By.xpath("//td[contains(text(), '" + strTitleToDelete + "')]/div/a[2]")).click();
	}
	
	public void acceptDeleteAlert() {
		driver.switchTo().alert().accept();
	}
}
