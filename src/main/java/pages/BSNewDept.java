package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BSNewDept {

	private WebDriver driver;
		
	By bsDeptName = By.id("department_name");
	By bsParentDept = By.cssSelector("select#department_department_id");
	By bsMinHrIncrement = By.id("department_minimum_hour_increment");
	By bsAltApprover = By.id("department_alternate_approver_id");
	By bsCreateDepartment = By.name("commit");
	By bsErrorMsg = By.cssSelector(".alert.alert-danger.alert-dismissable");
	
	Select selectParentDept;
	Select selectMinHrIncrement;
	Select selectAltApprover;
	
	
	public BSNewDept(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void enterDeptName(String strDeptName) {
		driver.findElement(bsDeptName).sendKeys(strDeptName);
		
		//verify what is entered is actually entered
		checkEnteredText(bsDeptName, strDeptName);
	}
	
	public void updateDeptName(String strDeptName, String strUpdateText) {
		driver.findElement(bsDeptName).sendKeys(strUpdateText);
		
		//verify what is entered is actually entered
		checkEnteredText(bsDeptName, strDeptName + strUpdateText);
	}
	
	public void setParentDept(String strParentDeptVisibleText) {
		selectParentDept = new Select(driver.findElement(bsParentDept));
		selectParentDept.selectByVisibleText(strParentDeptVisibleText);
		
		//verify what is entered is actually selected
		checkSelectedOption(selectParentDept, strParentDeptVisibleText);
	}
	
	public void setMinHrIncrement(String strMinHrIncrementVisibleText) {
		selectMinHrIncrement = new Select(driver.findElement(bsMinHrIncrement));
		selectMinHrIncrement.selectByVisibleText(strMinHrIncrementVisibleText);
		
		//verify what is entered is actually selected
		checkSelectedOption(selectMinHrIncrement, strMinHrIncrementVisibleText);
	}
	
	public void setAltApprover(String strAltApproverVisibleText) {
		selectAltApprover = new Select(driver.findElement(bsAltApprover));
		selectAltApprover.selectByVisibleText(strAltApproverVisibleText);
		
		//verify what is entered is actually selected
		checkSelectedOption(selectAltApprover, strAltApproverVisibleText);
	}
	
	public void clickCreateDept() {
		driver.findElement(bsCreateDepartment).click();
	}
	
	public void addNewDepartment(String strDeptName, String strParentDeptVisibleText, String strMinHrIncrementVisibleText, String strAltApproverVisibleText) {
		//fill name field
		this.enterDeptName(strDeptName);
		
		//fill parent department field
		this.setParentDept(strParentDeptVisibleText);
		
		//fill minimum hour increment field
		this.setMinHrIncrement(strMinHrIncrementVisibleText);
		
		//fill alternate approver field
		this.setAltApprover(strAltApproverVisibleText);
		
		//click create department button
		this.clickCreateDept();
	}
	
	public boolean errorMsg() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(bsErrorMsg));
		}
		catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public void clearDeptName() {
		driver.findElement(bsDeptName).clear();
		
		//assert field is actually cleared
		checkEnteredText(bsDeptName, "");
	}
	
	public void checkEnteredText(By locator, String intendedText) {
		String strEnteredText = driver.findElement(locator).getAttribute("value");
		Assert.assertTrue(strEnteredText.equals(intendedText));
	}
	
	public void checkSelectedOption(Select selectElement, String intendedSelection) {
		String strCurSel = selectElement.getFirstSelectedOption().getText();
		Assert.assertTrue(strCurSel.equals(intendedSelection));
	}
}
