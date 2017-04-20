package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BSDept;
import pages.BSEmployees;
import pages.BSLogin;
import pages.BSNav;
import pages.BSNewDept;

public class Story1_5 {

	WebDriver driver;
	
	String samTestDept = "SamNewTestDept - UPDATED";
	String selectID = "employee_department_id";
	
	BSLogin objLogin;
	BSNav objNav;
	BSDept objDept;
	BSNewDept objNewDept;
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
	public void deleteDepartmentSuccessMsg() {
		//click departments page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickDeptsLink();
		
		//click delete department link
		objDept = new BSDept(driver);
		objDept.clickDeleteDept(samTestDept);
		
		//accept alert to delete department
		objDept.acceptDeleteAlert();
		
		//assert for success message
		Assert.assertTrue(objDept.successMsg());
	}
	
	@Test(priority=2)
	public void departmentNotInList() {
		//click departments page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickDeptsLink();
		
		//verify new department displays in list
		objDept = new BSDept(driver);
		Assert.assertFalse(objDept.deptInList(samTestDept));
	}
	
	@Test(priority=3)
	public void addNewEmployeeDeletedDepartment() {		
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click "add" button on employees page
		objEmployees = new BSEmployees(driver);
		objEmployees.clickAddBtn();
		
		//Assert deleted department is not an option
		Assert.assertFalse(objEmployees.optionPresent(selectID, samTestDept));
		
		//close modal
		objEmployees.clickCloseBtn();
	}
	
	@Test(priority=4)
	public void updateExistingEmployeeDeletedDepartment() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click employee to update
		objEmployees = new BSEmployees(driver);
		objEmployees.clickEmployeeToUpdate();
		
		//click the edit button on employee page
		objEmployees.clickEditEmployee();
		
		//Assert deleted department is not an option
		Assert.assertFalse(objEmployees.optionPresent(selectID, samTestDept));
		
		//close modal
		objEmployees.clickCloseBtn();
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
