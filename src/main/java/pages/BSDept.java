package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BSDept {

	WebDriver driver;
	
	By bsAddDepartment = By.cssSelector("a[href='/departments/new']");
	By bsSuccessMsg = By.cssSelector(".alert.alert-success.alert-dismissable");
	
	
	public BSDept(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAddDept() {
		driver.findElement(bsAddDepartment).click();
	}

	public boolean successMsg() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(bsSuccessMsg));
		}
		catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public boolean deptInList(String strNewDeptName) {
		By bsDeptInList = By.xpath("//li[contains(text(), '" + strNewDeptName + "')]");
		
		try {
			driver.findElement(bsDeptInList);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickEditDept(String strDeptToEdit) {
		driver.findElement(By.xpath("//li[contains(text(), '" + strDeptToEdit + "')]/div/a[1]")).click();
	}
	
	public void clickDeleteDept(String strDeptToDelete) {
		driver.findElement(By.xpath("//li[contains(text(), '" + strDeptToDelete + "')]/div/a[2]")).click();
	}
	
	public void acceptDeleteAlert() {
		driver.switchTo().alert().accept();
	}
}
