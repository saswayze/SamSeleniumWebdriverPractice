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

public class Story1_8 {

	WebDriver driver;
	
	String strEmpName = "Kevin Hedgecock";
	String strGenInfoTitle = "General Info";
	String strTimeOffInfoTitle = "Time Off Info";
	
	BSLogin objLogin;
	BSEmployees objEmployees;
	
	
	@BeforeClass
	public void setUp() {
		String cwd = System.getProperty("user.dir"); 
		String ChromeDriverPath = "\\webdrivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", cwd + ChromeDriverPath);

		//User login info
		String username = "kevin.hedgecock";
		String password = "password";
		
		driver = new ChromeDriver();
		System.out.println("Chrome Instantiated");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://bluesourcestaging.herokuapp.com");
		
		//instantiate login page object
		objLogin = new BSLogin(driver);
		
		//login to BlueSource
		objLogin.loginToBlueSource(username, password);
	}
	
	@Test()
	public void employeePageOnEmployeeLogin() {
		objEmployees = new BSEmployees(driver);
		
		//assert correct employee page displayed
		Assert.assertTrue(objEmployees.empPageDisplayed(strEmpName));
		
		//assert time off info is displayed
		Assert.assertTrue(objEmployees.empPageGeneralInfo(strGenInfoTitle));
		
		//assert general info is displayed
		Assert.assertTrue(objEmployees.empPageTimeOffInfo(strTimeOffInfoTitle));
		
		//TODO Check General and Time Off info more thoroughly
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
