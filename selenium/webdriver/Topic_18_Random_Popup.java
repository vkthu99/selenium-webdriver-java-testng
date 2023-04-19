package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Random_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Random_Popup_Indom() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(20);
		By leLogin = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		//Vì  element này luôn có trong DOM nên có thể dùng hàm chứa display để kiểm tra
		if (driver.findElement(leLogin).isDisplayed()) {
			//Nhập email vào popup
			driver.findElement(By.xpath("//input[@placeholder='Enter your e-mail address']")).sendKeys("vuthithu@gmail.com");
			//Nhấn ok
			driver.findElement(By.cssSelector("a.lepopup-button-zoom-out")).click();
		}
		sleepInSecond(10);
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		sleepInSecond(5);
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title='David Tzemach']/parent::span/parent::span/parent::span/parent::div/parent::div/parent::li")).isDisplayed());
		

	}
	//@Test
	public void TC_02_Random_Popup_Indom() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(40);
		By Popup = By.cssSelector("span.tve_image_frame");
		if (driver.findElement(Popup).isDisplayed()) {
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			
		}
		
	}
	@Test
	public void TC_03_Random_Popup_NotIndom() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(8);
		By Popup = By.cssSelector("div.popup-content");
		if (driver.findElements(Popup).size() > 0 && driver.findElements(Popup).get(0).isDisplayed()) { 
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Mary");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys("mary97@gmail.com");
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0870987823");
			sleepInSecond(4);
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
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