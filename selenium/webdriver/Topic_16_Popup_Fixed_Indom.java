package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Fixed_Indom {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Fixed_popup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		//Verify popup ko hiển thị
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(3);
			
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		//Điền thông tin user và pass
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).isDisplayed());

	}
	@Test
	public void TC_02_Fixed_popup_In_DOM() {
		driver.get("https://skills.kynaenglish.vn/");
		By loginPopup = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		//Nhập thông tin
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("input#user-password");
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(3);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		
		
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