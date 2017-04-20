package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class BSEmployees {

	WebDriver driver;
	
	//By's for "add employee" modal form
		//By's for input fields
		By bsUsername = By.id("employee_username");
		By bsFirstName = By.id("employee_first_name");
		By bsLastName = By.id("employee_last_name");
		By bsBridgeTime = By.id("employee_bridge_time");
		By bsStartDate = By.id("employee_start_date");
		By bsCellPhone = By.id("employee_cell_phone");
		By bsOfficePhone = By.id("employee_office_phone");
		By bsEmail = By.id("employee_email");
		By bsIMName = By.id("employee_im_name");
		//By's for select fields
		By bsEmpTitle = By.id("employee_title_id");
		By bsEmpRole = By.id("employee_role");
		By bsManager = By.id("employee_manager_id");
		By bsStatus = By.id("employee_status");
		By bsLocation = By.id("employee_location");
		By bsIMClient = By.id("employee_im_client");
		By bsDepartment = By.id("employee_department_id");
		//By's for messages
		By bsSuccessMsg = By.cssSelector(".alert.alert-success.alert-dismissable");
		By bsErrorMsg = By.cssSelector(".alert.alert-danger.alert-dismissable");
		By bsBlankFieldErrorMsg = By.cssSelector("input:invalid");
		By bsModalOpen = By.cssSelector("div[aria-hidden='false'");
		By bsModalBG = By.cssSelector("div.modal-backdrop.fade.in");
		//By's for buttons
		By bsAddBtn = By.cssSelector(".btn-group.pull-right .btn.btn-default");
		By bsEditBtn = By.cssSelector("button.btn-xs");
		By bsCreateEmployeeBtn = By.name("commit");
		By bsCloseBtn = By.cssSelector("div.form-group:nth-child(18) > button:nth-child(1)");
		By bsUpdateEmployee = By.name("commit");
		//By's for searching employees
		By bsEmpSearch = By.cssSelector("input#search-bar");
		By bsEmpFilterBtn = By.id("filter_btn");
		By bsEmpFilterDirect = By.linkText("Direct");
		//By's for verifying employee page and employee information
		By bsEmpFullName = By.tagName("h1");
		By bsTimeOffInfo = By.xpath("/html/body/div[1]/div[2]/div/div[3]/h4");
		By bsGeneralInfo = By.xpath("/html/body/div[1]/div[2]/div/div[5]/h4");
		//other By's
		By bsEmpToUpdate = By.cssSelector("tr.ng-scope:nth-child(2) > td:nth-child(1) > a:nth-child(1)");
		
	Select empTitle;
	Select empRole;
	Select empManager;
	Select empStatus;
	Select empLocation;
	Select empIMClient;
	Select empDepartment;
	
	
	public BSEmployees(WebDriver driver) {
		this.driver = driver;
	} 
	
	public void clickAddBtn() {
		WebElement btnAdd = driver.findElement(bsAddBtn);
		btnAdd.click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//methods to set select fields
	public void setEmpTitle(String strEmpTitle) {
		empTitle = new Select(driver.findElement(bsEmpTitle));
		empTitle.selectByVisibleText(strEmpTitle);
		
		//verify correct selection selected
		checkSelectedOption(empTitle, strEmpTitle);
	}
	
	public void setEmpRole(String strEmpRole) {
		empRole = new Select(driver.findElement(bsEmpRole));
		empRole.selectByVisibleText(strEmpRole);
		
		//verify correct selection selected
		checkSelectedOption(empRole, strEmpRole);
	}
	
	public void setEmpManager(String strEmpManager) {
		empManager = new Select(driver.findElement(bsManager));
		empManager.selectByVisibleText(strEmpManager);
		
		//verify correct selection selected
		checkSelectedOption(empManager, strEmpManager);
	}
	
	public void setEmpStatus(String strEmpStatus) {
		empStatus = new Select(driver.findElement(bsStatus));
		empStatus.selectByVisibleText(strEmpStatus);
		
		//verify correct selection selected
		checkSelectedOption(empStatus, strEmpStatus);
	}
	
	public void setEmpLocation(String strEmpLocation) {
		empLocation = new Select(driver.findElement(bsLocation));
		empLocation.selectByVisibleText(strEmpLocation);
		
		//verify correct selection selected
		checkSelectedOption(empLocation, strEmpLocation);
	}
	
	public void setEmpIMClient(String strEmpIMClient) {
		empIMClient = new Select(driver.findElement(bsIMClient));
		empIMClient.selectByVisibleText(strEmpIMClient);
		
		//verify correct selection selected
		checkSelectedOption(empIMClient, strEmpIMClient);
	}
	
	public void setEmpDepartment(String strEmpDepartment) {
		empDepartment = new Select(driver.findElement(bsDepartment));
		empDepartment.selectByVisibleText(strEmpDepartment);
		
		//verify correct selection selected
		checkSelectedOption(empDepartment, strEmpDepartment);
	}
	
	//methods to enter text into input fields
	public void enterEmpUsername(String strEmpUsername) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(bsUsername).sendKeys(strEmpUsername);
		
		//verify what is entered is actually entered
		checkEnteredText(bsUsername, strEmpUsername);
	}
	
	public void enterEmpFirstName(String strEmpFirstName) {
		driver.findElement(bsFirstName).sendKeys(strEmpFirstName);
		
		//verify what is entered is actually entered
		checkEnteredText(bsFirstName, strEmpFirstName);
	}
	
	public void enterEmpLastName(String strEmpLastName) {
		driver.findElement(bsLastName).sendKeys(strEmpLastName);
		
		//verify what is entered is actually entered
		checkEnteredText(bsLastName, strEmpLastName);
	}
	
	public void enterEmpBridgeTime(String strEmpBridgeTime) {
		driver.findElement(bsBridgeTime).sendKeys(strEmpBridgeTime);
		
		//verify what is entered is actually entered
		checkEnteredText(bsBridgeTime, strEmpBridgeTime);
	}
	
	public void enterEmpStartDate(String strEmpStartDate) {
		driver.findElement(bsStartDate).sendKeys(strEmpStartDate);
		
		//verify what is entered is actually entered --had to be custom
		String strEnteredText = driver.findElement(bsStartDate).getAttribute("value");
		String[] parts = strEnteredText.split("-");
		strEnteredText = parts[1] + parts[2] + parts[0];
		
		Assert.assertTrue(strEnteredText.equals(strEmpStartDate));
	}
	
	public void enterEmpCellPhone(String strEmpCellPhone) {
		driver.findElement(bsCellPhone).sendKeys(strEmpCellPhone);
		
		//verify what is entered is actually entered
		checkEnteredText(bsCellPhone, strEmpCellPhone);
	}
	
	public void enterEmpOfficePhone(String strEmpOfficePhone) {
		driver.findElement(bsOfficePhone).sendKeys(strEmpOfficePhone);
		
		//verify what is entered is actually entered
		checkEnteredText(bsOfficePhone, strEmpOfficePhone);
	}
	
	public void enterEmpEmail(String strEmpEmail) {
		driver.findElement(bsEmail).sendKeys(strEmpEmail);
		
		//verify what is entered is actually entered
		checkEnteredText(bsEmail, strEmpEmail);
	}
	
	public void enterEmpIMName(String strEmpIMName) {
		driver.findElement(bsIMName).sendKeys(strEmpIMName);
		
		//verify what is entered is actually entered
		checkEnteredText(bsIMName, strEmpIMName);
	}
	
	public void clickCreateEmployee() {
		driver.findElement(bsCreateEmployeeBtn).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void addNewEmployee(String strEmpUsername, String strEmpFirstName, String strEmpLastName, String strEmpTitle, String strEmpRole, 
								String strEmpManager, String strEmpStatus, String strEmpBridgeTime, String strEmpLocation, String strEmpStartDate, 
								String strEmpCellPhone, String strEmpOfficePhone, String strEmpEmail, String strEmpIMName, String strEmpIMClient,
								String strEmpDepartment
			) {
		//fill out the add new employee form
		enterEmpUsername(strEmpUsername);
		enterEmpFirstName(strEmpFirstName);
		enterEmpLastName(strEmpLastName);
		setEmpTitle(strEmpTitle);
		setEmpRole(strEmpRole);
		setEmpManager(strEmpManager);
		setEmpStatus(strEmpStatus);
		enterEmpBridgeTime(strEmpBridgeTime);
		setEmpLocation(strEmpLocation);
		enterEmpStartDate(strEmpStartDate);
		enterEmpCellPhone(strEmpCellPhone);
		enterEmpOfficePhone(strEmpOfficePhone);
		enterEmpEmail(strEmpEmail);
		enterEmpIMName(strEmpIMName);
		setEmpIMClient(strEmpIMClient);
		setEmpDepartment(strEmpDepartment);
		
		//click "create employee" button
		clickCreateEmployee();
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
	
	public boolean errorMsg() {
		try {
			driver.findElement(bsErrorMsg);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickEmployeeToUpdate() {
		driver.findElement(bsEmpToUpdate).click();
	}
	
	public void clickEditEmployee() {
		driver.findElement(bsEditBtn).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickUpdateEmployee() {
		driver.findElement(bsUpdateEmployee).click();
	}
	
	public void checkSelectedOption(Select selectElement, String intendedSelection) {
		String strCurSel = selectElement.getFirstSelectedOption().getText();
		Assert.assertTrue(strCurSel.equals(intendedSelection));
	}
	
	public void checkEnteredText(By locator, String intendedText) {
		String strEnteredText = driver.findElement(locator).getAttribute("value");
		Assert.assertTrue(strEnteredText.equals(intendedText));
	}
	
	public boolean optionPresent(String selectID, String strDelDeptName) {
		By bsSelOption = By.xpath("//select[@id='" + selectID + "']/option[contains(text(), '" + strDelDeptName + "')]");
		
		try {
			driver.findElement(bsSelOption);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickCloseBtn() {
		driver.findElement(bsCloseBtn).click();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean blankFieldErrorMsg() {
		try {
			driver.findElement(bsBlankFieldErrorMsg);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void employeeSearch(String strSearchTerm) {
		driver.findElement(bsEmpSearch).sendKeys(strSearchTerm);
		
		//verify what is entered is actually entered
		checkEnteredText(bsEmpSearch, strSearchTerm);
	}
	
	public boolean employeeInList(String strEmpLastName) {
		By bsEmpLastNameList = By.linkText(strEmpLastName);
		try {
			driver.findElement(bsEmpLastNameList);
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickEmpFilter() {
		driver.findElement(bsEmpFilterBtn).click();
	}
	
	public void clickEmpFilterDirect() {
		driver.findElement(bsEmpFilterDirect).click();
	}
	
	public boolean empPageDisplayed(String empFullName) {
		String h1Text = driver.findElement(bsEmpFullName).getText();
		
		if (h1Text.equals(empFullName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean empPageGeneralInfo(String strGenInfoTitle) {
		String h4Text = driver.findElement(bsGeneralInfo).getText();
		
		if (h4Text.equals(strGenInfoTitle)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean empPageTimeOffInfo(String strTimeOffInfoTitle) {
		String h4Text = driver.findElement(bsTimeOffInfo).getText();
		
		if (h4Text.equals(strTimeOffInfoTitle)) {
			return true;
		} else {
			return false;
		}
	}
}
