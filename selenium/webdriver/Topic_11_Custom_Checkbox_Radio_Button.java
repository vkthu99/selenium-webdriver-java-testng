package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio_Button {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Custom_Checkbox() {
        driver.get("https://material.angular.io/components/radio/examples");
		if (!driver.findElement(By.xpath("//input[@value='Summer']")).isSelected()) {
			driver.findElement(By.xpath("//input[@value='Summer']")).click();
		}
		driver.get("https://material.angular.io/components/checkbox/examples");
		driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
		driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
		
		driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
		driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());
	}
	//@Test
	public void TC_02_Custom_Button() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}
	//@Test
	public void TC_03_Custom_Button_Ham_Javascript() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}
	@Test
	public void TC_04_Custom_Checkbox() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ'][@aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']/parent::div")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ'][@aria-checked='true']")).isDisplayed());
	}
	
	public void sleepInSecond(long timeInSecond)  {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}