package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_Textarea {
	WebDriver driver;
	Random rand = new Random();
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String employeeID = String.valueOf(rand.nextInt(99999));

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	@Test
	public void TC_01_Textbox_Textarea() {
		//Truy cập vào trang  web
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//Đăng nhập với tài khoản username
		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("Admin");
		//Đăng nhập với tài khoản password
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("admin123");
		//Ấn nút đăng nhập
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		sleepInSecond(6);
		//Chọn vào PIM
		driver.findElement(By.xpath("//li[@class=\"oxd-main-menu-item-wrapper\"]//following-sibling::li")).click();
		driver.findElement(By.xpath("//a[text()=\"Add Employee\"]")).click();
		sleepInSecond(3);
		//Điền thông tin họ và tên
		driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys("Vũ Thị");
		driver.findElement(By.xpath("//input[@name=\"middleName\"]")).sendKeys("Kiều");
		driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys("Thu");
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div//input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(1);
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div//input")).sendKeys(Keys.chord(Keys.DELETE));
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div//input")).sendKeys(employeeID);
		//Click vào nút Create Login Details
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		
		//Điền thông tin user
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID );
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("12345678@Abc");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("12345678@Abc");
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(8);
		//Verify dữ liệu
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),"Vũ Thị");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='middleName']")).getAttribute("value"),"Kiều");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),"Thu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input")).getAttribute("value"), employeeID);
	    //Click vào tab immigration
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		sleepInSecond(5);
		//Click vào button add
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys("40517-402-96-7202");
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys("No comment");
		driver.findElement(By.xpath("//button[text()=' Save ']")).click();
		sleepInSecond(8);
		//CLick biểu tượng pencil
		driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-pencil-fill\"]")).click();
		sleepInSecond(3);
		//Verify dữ liệu đã hiển thị đúng
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"40517-402-96-7202");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),"No comment");
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		//Đăng nhập lại tk đã vừa thêm
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID );
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("12345678@Abc");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		//Vào màn hình my infor
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(5);
		//Verify lại thông tin
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),"Vũ Thị");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='middleName']")).getAttribute("value"),"Kiều");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"),"Thu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input")).getAttribute("value"), employeeID);
		//Vào màn hình immigration
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		driver.findElement(By.xpath("//i[@class=\"oxd-icon bi-pencil-fill\"]")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"),"40517-402-96-7202");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"),"No comment");
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
		//driver.quit();
	}
}