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

public class Story1_6 {

	WebDriver driver;
	
	String strDeletedTitle = "SamTestTitle - UPDATED";
	String selectID = "employee_title_id";	
	
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
	public void deleteTitleSuccessMsg() {
		//click titles page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickTitlesLink();
		
		//click link to delete title
		objTitles = new BSTitles(driver);
		objTitles.clickDeleteTitle(strDeletedTitle);
		
		//accept delete alert
		objTitles.acceptDeleteAlert();
		
		//assert for the success message
		Assert.assertTrue(objTitles.successMsg());
	}
	
	@Test(priority=2)
	public void titleNotInList() {
		//click titles page
		objNav = new BSNav(driver);
		objNav.clickAdminMenu();
		objNav.clickTitlesLink();
		
		//verify newly added title is in the titles list
		objTitles = new BSTitles(driver);
		Assert.assertFalse(objTitles.titleInList(strDeletedTitle));
	}
	
	@Test(priority=3)
	public void addNewEmployeeDeletedTitle() {
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click "add" button on employees page
		objEmployees = new BSEmployees(driver);
		objEmployees.clickAddBtn();
		
		//assert deleted department is not an option
		Assert.assertFalse(objEmployees.optionPresent(selectID, strDeletedTitle));
		
		//close modal
		objEmployees.clickCloseBtn();
	}
	
	@Test(priority=4)
	public void updateExistingEmployeeDeletedTitle() {		
		//click employees page
		objNav = new BSNav(driver);
		objNav.clickEmployeesLink();
		
		//click employee to update
		objEmployees = new BSEmployees(driver);
		objEmployees.clickEmployeeToUpdate();
		
		//click the edit button on employee page
		objEmployees.clickEditEmployee();
		
		//assert deleted department is not an option
		Assert.assertFalse(objEmployees.optionPresent(selectID, strDeletedTitle));
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
