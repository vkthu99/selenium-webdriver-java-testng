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

public class Topic_05_Excercise_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	@Test
	public void TC_01_Url() {
		//Truy cập vào trang techpanda
		driver.get("http://live.techpanda.org/");
		//Click vào nút My account ở dưới footẻ của page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//
		sleepInSecond(3);
		//Verify url của trang login
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		//Click vào nút Create an Acount
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    sleepInSecond(3);
	    //Verify url của trang register
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
	    }
	
		
	@Test
	public void TC_02_Title() {
		//Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		//Click vào nút My account ở dưới footẻ của page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		//Verify title của page
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		//Click vào nút Create an Acount
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    sleepInSecond(3);
	    //Verify url của trang register
	    Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigate() {
		//Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		//Click vào nút My account ở dưới footẻ của page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//Click vào nút Create an Acount
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		//Verify url của trang register
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		//Back lại trang
	    driver.navigate().back();
	    sleepInSecond(3);
	    Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
	    //Forward trang
	    driver.navigate().forward();
	    sleepInSecond(3);
	    //Verify title có tên Create New Customer Account
	    Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_Pagesource() {
		//Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		//Click vào nút My account ở dưới footẻ của page
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(3);
		//Verify login page có chứa Login or create an account
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		//Click vào Create an account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//Verify chứa text Create an account
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
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