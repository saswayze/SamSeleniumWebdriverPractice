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
import pages.BSNav;
import pages.BSLogin;
import pages.BSNewDept;

public class Story1_1 {

	WebDriver driver;
	
	String strIteration = "01";
	String samTestDept = "SamNewTestDept";
	
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
		
		//FIXME Test Suite XML File
		//XXX Cross browser testing?
	}
	
	//TODO add testng descriptions to all testng tests
	
	@Test(priority=1, enabled=true)
	public void addNewDepartmentFailMsg() {
		String strDeptName = "";
		String strParentDeptVisibleText = "bustaRhymes";
		String strMinHrIncrementVisibleText = "4";
		String strAltApproverVisibleText = "Company Admin";
		
		//click departments page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickDeptsLink();
		
		//click add new department link
		objDept = new BSDept(driver);
		objDept.clickAddDept();
		
		//enter/select new department info - leave a field blank
		//and click "Create Department" button
		objNewDept = new BSNewDept(driver);
		objNewDept.addNewDepartment(strDeptName, strParentDeptVisibleText, strMinHrIncrementVisibleText, strAltApproverVisibleText);
		
		//assert for failure message
		Assert.assertTrue(objNewDept.errorMsg());
	}
	
	@Test(priority=2)
	public void addNewDepartmentSuccessMsg() {
		String strDeptName = samTestDept;
		String strParentDeptVisibleText = "";
		String strMinHrIncrementVisibleText = "4";
		String strAltApproverVisibleText = "Company Admin";
		
		//click departments page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickDeptsLink();
		
		//click add new department link
		objDept = new BSDept(driver);
		objDept.clickAddDept();
		
		//enter/select new department info
		//click "Create Department" button
		objNewDept = new BSNewDept(driver);
		objNewDept.addNewDepartment(strDeptName, strParentDeptVisibleText, strMinHrIncrementVisibleText, strAltApproverVisibleText);
		
		//assert for success message
		Assert.assertTrue(objDept.successMsg());
	}
	
	@Test(priority=3, enabled=true)
	public void newDepartmentInList() {
		//click departments page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickDeptsLink();
		
		//verify new department displays in list
		objDept = new BSDept(driver);
		Assert.assertTrue(objDept.deptInList(samTestDept));
	}
	
	@Test(priority=4, enabled=true)
	public void addNewEmployeeNewDepartment() {
		String strEmpUsername = "samNewDeptTest" + strIteration; //must be unique
		String strEmpFirstName = "two";
		String strEmpLastName = "test";
		String strEmpTitle = "Blah5";
		String strEmpRole = "Base";
		String strEmpManager = "123 1234";
		String strEmpStatus = "Permanent";
		String strEmpBridgeTime = "2";
		String strEmpLocation = "Greensboro";
		String strEmpStartDate = "03162017"; //Chrome, Opera, and Safari will have a different format than Firefox and IE
		String strEmpCellPhone = "13364551397";
		String strEmpOfficePhone = "13364324321";
		String strEmpEmail = "aDept"+ strIteration + "@mail.com"; //must be unique
		String strEmpIMName = "two.test";
		String strEmpIMClient = "Skype"; 
		String strEmpDepartment = samTestDept;
		
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
		
		//TODO Check new Employee data
	}
	
	@Test(priority=5)
	public void updateExistingEmployeeNewDepartment() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click employee to update
		objEmployees = new BSEmployees(driver);
		objEmployees.clickEmployeeToUpdate();
		
		//click the edit button on employee page
		objEmployees.clickEditEmployee();
		
		//select new department
		objEmployees.setEmpDepartment(samTestDept);
		
		//click "update employee" button
		objEmployees.clickUpdateEmployee();
		
		//assert for employee updated with newly added department
		Assert.assertTrue(objEmployees.successMsg());
		
		//TODO Check updated Employee data
		
		//remove employee from updated department
		objEmployees.clickEditEmployee();
		objEmployees.setEmpDepartment("");
		objEmployees.clickUpdateEmployee();
		Assert.assertTrue(objEmployees.successMsg());
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
