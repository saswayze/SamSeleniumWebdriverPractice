package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BSNav {
	
	// This class holds home page and main navigation page objects
	
	WebDriver driver;
	
	By bsAdminMenu = By.cssSelector("li.dropdown:nth-child(1)");
	By bsDepartmentsLink = By.cssSelector("a[href='/admin/departments']");
	By bsTitlesLink = By.cssSelector("a[href='/admin/titles']");
	By bsEmployeesMenu = By.cssSelector("ul.nav:nth-child(1) > li:nth-child(7)");
	
	
	public BSNav(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickAdminMenu() {
		WebElement adminMenuLink = driver.findElement(bsAdminMenu);
		adminMenuLink.click();
	}
	
	public void clickDeptsLink() {
		WebElement deptsMenuLink = driver.findElement(bsDepartmentsLink);
		deptsMenuLink.click();
	}
	
	public void clickTitlesLink() {
		WebElement titlesMenuLink = driver.findElement(bsTitlesLink);
		titlesMenuLink.click();
	}
	
	public void clickEmployeesLink(){
		WebElement employeesMenuLink = driver.findElement(bsEmployeesMenu);
		employeesMenuLink.click();
	}
}
