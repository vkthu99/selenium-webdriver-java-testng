package webdriver;

import java.util.Random;
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

public class Topic_08_Handle_default_dropdown {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, companyName, password; 

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Vũ Thị Kiều";
		lastName ="Thu";
		emailAddress = "vuthikieuthu" + getRandomNumber() + "@gmail.com";
		companyName = "SpaceX";
		password = "12345678@Abc";
		
		
		}

	@Test
	public void TC_01_Register_new_account() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Female']")).click();
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys(lastName);
		
		//dropdown chọn ngày tháng năm
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText("23");
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText("August");
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText("1999");
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("automation10@gmail.net");
		driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(companyName);
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys(password);
		
		//Click vào register
		driver.findElement(By.xpath("//button[text()='Register']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		//Verify lại thông tin
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("automation10@gmail.net");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='My account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='FirstName']")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='LastName']")).getAttribute("value"), lastName);
		
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), "23");
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), "August");
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), "1999");
	
		
	    driver.findElement(By.xpath("//li[@class='customer-addresses inactive']/a[text()='Addresses']")).click();
	    sleepInSecond(3);
	    driver.findElement(By.xpath("//button[text()='Add new']")).click();
	    driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
	    driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
	    driver.findElement(By.id("Address_Email")).sendKeys("automation10@gmail.net");
	    driver.findElement(By.id("Address_Company")).sendKeys(companyName);
	    new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText("Viet Nam");
	    driver.findElement(By.id("Address_City")).sendKeys("Hà Nội");
	    driver.findElement(By.id("Address_Address1")).sendKeys("Minh Khai");
	    driver.findElement(By.id("Address_Address2")).sendKeys("Xuân Đỉnh");
	    driver.findElement(By.id("Address_ZipPostalCode")).sendKeys("HNVN");
	    driver.findElement(By.id("Address_PhoneNumber")).sendKeys("0787056234");
	    driver.findElement(By.xpath("//button[text()='Save']")).click();
	    sleepInSecond(3);
	    
	    //verify lại thông tin
	    Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='info']/li[@class='name']")).getText(), firstName + " " + lastName);
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='info']/li[@class='email']")).getText().contains("automation10@gmail.net"));
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='info']/li[@class='phone']")).getText().contains("0787056234"));
	    Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='info']/li[@class='company']")).getText(), companyName);
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='info']/li[@class='address1']")).getText().contains("Minh Khai"));
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='info']/li[@class='address2']")).getText().contains("Xuân Đỉnh"));
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='info']/li[@class='city-state-zip']")).getText().contains("HNVN"));
	    
	}
	
	
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
		
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