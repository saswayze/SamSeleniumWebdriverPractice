package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.BSEmployees;
import pages.BSLogin;
import pages.BSNav;

public class Story1_7 {

	WebDriver driver;
	
	String strIteration = "01";
	
	String strInvalidUsername = "samtest";
	String strInvalidEmail = "a@mail.com";
	String strValidUsername = strIteration + "newEmp" + strInvalidUsername;
	String strValidEmail = strIteration + "newEmp" + strInvalidEmail;
	String strEmpFirstName = "Sam";
	String strEmpLastName = "Test" + strIteration;
	String strEmpManager = "Company Admin";
	
	BSLogin objLogin;
	BSNav objNav;
	BSEmployees objEmployees;
	
	
	@BeforeClass
	public void setUp() {
		String cwd = System.getProperty("user.dir"); 
		String ChromeDriverPath = "\\webdrivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", cwd + ChromeDriverPath);

		//User login info
		String username = "company.admin";
		String password = "password";
		
		driver = new ChromeDriver();
		System.out.println("Chrome Instantiated");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://bluesourcestaging.herokuapp.com");
		
		//create login page object
		objLogin = new BSLogin(driver);
		
		//login to BlueSource
		objLogin.loginToBlueSource(username, password);
	}

	
	//fail message when employee not added successfully
	@Test(priority=0, enabled=true)
	public void addNewEmployeeFailMsg() {
		SoftAssert softAssert = new SoftAssert();
		
		//a. required fields left blank
			//click employees page
			objNav = new BSNav(driver);
			objNav.clickEmployeesLink();
			
			//click "add" button on employees page
			objEmployees = new BSEmployees(driver);
			objEmployees.clickAddBtn();
			
			//leave all fields blank and click create employee button			
			objEmployees.clickCreateEmployee();
						
			//assert for failure message
			//weird tooltip-like error message
				/*
				Testing for the modal still being up based on the following two elements
				Modal Open:	div id="modal_1" class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="modal_label_1" aria-hidden="false" style="display: block;"
				Modal Open:	div class="modal-backdrop fade in"
				*/
			softAssert.assertTrue(objEmployees.blankFieldErrorMsg(), "Blank field error message was not displayed");
				//TODO find a way to actually verify the error message, instead of going by the modal's behavior
			
			//close modal
			objEmployees.clickCloseBtn();
			
			
		//b. invalid data
			//click "add" button on employees page
			objEmployees.clickAddBtn();
			
			//enter invalid data
			objEmployees.enterEmpUsername(strInvalidUsername);
			objEmployees.enterEmpEmail(strInvalidEmail);
			objEmployees.enterEmpFirstName(strEmpFirstName);
			objEmployees.enterEmpLastName(strEmpLastName);
			
			//leave all fields blank and click create employee button
			objEmployees.clickCreateEmployee();
			
			//assert for failure message
			softAssert.assertTrue(objEmployees.errorMsg(), "'invalid data' error message was not displayed");
			
		//fail test if any of the soft asserts failed
		softAssert.assertAll();	
	}

	//success message when employee added successfully
	@Test(priority=1, enabled=true)
	public void addNewEmployeeSuccessMsg() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click "add" button on employees page
		objEmployees = new BSEmployees(driver);
		objEmployees.clickAddBtn();
		
		//enter invalid data
		objEmployees.enterEmpUsername(strValidUsername);
		objEmployees.enterEmpEmail(strValidEmail);
		objEmployees.enterEmpFirstName(strEmpFirstName);
		objEmployees.enterEmpLastName(strEmpLastName);
		objEmployees.setEmpManager(strEmpManager);
		
		//leave all fields blank and click create employee button
		objEmployees.clickCreateEmployee();
		
		//assert for new employee added success message
		Assert.assertTrue(objEmployees.successMsg());
		
		//TODO Check New Employee Data
	}
	
	//new employee displayed in employee list
	@Test(priority=2, enabled=true)
	public void newEmployeePresentInEmployeeList() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//use search bar to find list page with newly added employee
		objEmployees = new BSEmployees(driver);
		objEmployees.employeeSearch(strEmpFirstName + " " + strEmpLastName);
		
		//assert for newly added employee displayed in list
		Assert.assertTrue(objEmployees.employeeInList(strEmpLastName));
	}
	
	//new employee displayed in the list of direct employees for the manager
		//Where this is: employees list page, change "All" to "Direct"
	@Test(priority=3, enabled=true)
	public void newEmployeePresentInManagersDirectEmployeeList() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//switch from "all" employees to "direct"
		objEmployees = new BSEmployees(driver);
		objEmployees.clickEmpFilter();
		objEmployees.clickEmpFilterDirect();
		
		//use search bar to find list page with newly added employee
		objEmployees.employeeSearch(strEmpFirstName + " " + strEmpLastName);
		
		//assert for newly added employee displayed in list
		Assert.assertTrue(objEmployees.employeeInList(strEmpLastName));
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
