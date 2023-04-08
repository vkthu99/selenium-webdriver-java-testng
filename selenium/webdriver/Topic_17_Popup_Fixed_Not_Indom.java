package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_Fixed_Not_Indom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Popup_Not_Indom_Tiki() {
		driver.get("https://tiki.vn/");
		By loginPopup = By.cssSelector("div.ReactModal__Overlay");
		
		driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']//img")).click();
		sleepInSecond(3);
		//verify popup hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Overlay")).isDisplayed());
		
		driver.findElement(By.xpath("//p[text()='Đăng nhập bằng email']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);
		//Verify popup not indom ko hiển thị => chú ý là elements trong hàm này phải dùng số nhiều
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);
	}
	@Test
	public void TC_02_Popup_Not_Indom_Facebook() {
		driver.get("https://www.facebook.com/");
		By loginPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div/parent::div/parent::div/parent::div");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("0987654321");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("123456789");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("23");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1999");
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img[@class='_8idr img']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
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