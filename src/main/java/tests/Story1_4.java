package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BSEmployees;
import pages.BSLogin;
import pages.BSNav;
import pages.BSTitles;

public class Story1_4 {

	WebDriver driver;
	
	String strIteration = "01";
	
	String samTestTitle = "SamTestTitle";
	String strUpdate = " - UPDATED";
	String strUpdatedTitle = samTestTitle + strUpdate;
	
	BSLogin objLogin;
	BSNav objNav;
	BSTitles objTitles;
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
	
	
	@Test(priority=1)
	public void updateTitleFailMsg() {
		
		//click titles page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickTitlesLink();
		
		//click link to add new title
		objTitles = new BSTitles(driver);
		objTitles.clickEditTitle(samTestTitle);
		
		//clear the title
		objTitles.clearTitle();
		
		//click update title btn
		objTitles.clickCreateTitle();
		
		//assert for the failure message
		Assert.assertTrue(objTitles.errorMsg());
	}
	
	@Test(priority=2)
	public void updateTitleSuccessMsg() {
		//click titles page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickTitlesLink();
		
		//click link to add new title
		objTitles = new BSTitles(driver);
		objTitles.clickEditTitle(samTestTitle);
		
		//update the title
		objTitles.updateTitle(samTestTitle, strUpdate);
		
		//assert for the success message
		Assert.assertTrue(objTitles.successMsg());
	}
	
	@Test(priority=3)
	public void updatedTitleAppearsInList() {
		//click titles page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickTitlesLink();
		
		//verify newly added title is in the titles list
		objTitles = new BSTitles(driver);
		Assert.assertTrue(objTitles.titleInList(strUpdatedTitle));
	}
	
	@Test(priority=4)
	public void addNewEmployeeNewTitle() {
		String strEmpUsername = "samUpdateTitleTest" + strIteration; //must be unique
		String strEmpFirstName = "Sam";
		String strEmpLastName = "NewTitle";
		String strEmpTitle = strUpdatedTitle;
		String strEmpRole = "Base";
		String strEmpManager = "123 1234";
		String strEmpStatus = "Permanent";
		String strEmpBridgeTime = "2";
		String strEmpLocation = "Greensboro";
		String strEmpStartDate = "03162017"; //Chrome, Opera, and Safari will have a different format than Firefox and IE
		String strEmpCellPhone = "13364551397";
		String strEmpOfficePhone = "13364324321";
		String strEmpEmail = "aUpTitle"+ strIteration + "@mail.com"; //must be unique
		String strEmpIMName = "two.test";
		String strEmpIMClient = "Skype"; 
		String strEmpDepartment = "";
		
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click "add" button on employees page
		objEmployees = new BSEmployees(driver);
		objEmployees.clickAddBtn();
		
		//fill out "add employee" modal form
		//and click "create employee"
		objEmployees.addNewEmployee(strEmpUsername, strEmpFirstName, strEmpLastName, strEmpTitle, strEmpRole, strEmpManager, strEmpStatus, 
									strEmpBridgeTime, strEmpLocation, strEmpStartDate, strEmpCellPhone, strEmpOfficePhone, strEmpEmail, 
									strEmpIMName, strEmpIMClient, strEmpDepartment);

		//assert for new department used? or success?
		Assert.assertTrue(objEmployees.successMsg());
		
		//TODO Check New Employee Data
	}
	
	@Test(priority=5)
	public void updateExistingEmployeeNewTitle() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click employee to update
		objEmployees = new BSEmployees(driver);
		objEmployees.clickEmployeeToUpdate();
		
		//click the edit button on employee page
		objEmployees.clickEditEmployee();
		
		//select new title
		objEmployees.setEmpTitle(strUpdatedTitle);
		
		//click "update employee" button
		objEmployees.clickUpdateEmployee();
		
		//assert for employee updated with newly added title
		Assert.assertTrue(objEmployees.successMsg());
		
		//TODO Check updated Employee data
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
