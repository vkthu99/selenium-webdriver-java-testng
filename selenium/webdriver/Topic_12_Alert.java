package webdriver;

import java.io.IOException;
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

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait expliciWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
    String authenFirefox = projectPath + "\\AutoIT\\authen_firefox.exe";
    String authenChorme = projectPath + "\\AutoIT\\authen_chrome.exe";
    String Username = "admin";
    String Password = "admin";
    
    
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		//Cách 2: Cần wait trước cho đến khi xuất hiện thì mới switch qua và tương tác => NÊn dùng cách 2
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		//Tương tác thực hiện verify
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		//Accept cái alert
		alert.accept();
		//Verify thông báo click thành công
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		//Cách 2: Cần wait trước cho đến khi xuất hiện thì mới switch qua và tương tác => NÊn dùng cách 2
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		//Tương tác thực hiện verify
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		//Cancel cái alert
		alert.dismiss();
		//Verify thông báo click thành công
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
		
	}
	//@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		//Cách 2: Cần wait trước cho đến khi xuất hiện thì mới switch qua và tương tác => NÊn dùng cách 2
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		//Tương tác thực hiện verify
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		//Nhập text vào alert
		alert.sendKeys("daominhdam");
		//Cancel cái alert
		alert.accept();
		sleepInSecond(2);
		//Verify thông báo click thành công
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: daominhdam");
		
	}
	//@Test
	public void TC_04_Authentication_Alert_I() {
		//Truyền trực tiếp user name và password vào chính Url này => Tự động Signin
		// http:// + Username : Password + @ the-internet.herokuapp.com/basic_auth
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	//@Test
	public void TC_04_Authentication_Alert_II() {
		driver.get(passUserAndPassToUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
	}
	@Test
	public void TC_05_Authentication_Alert_Drilldown_sang_Trang_B_từ_trang_A_AutoIT() throws IOException {
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { authenFirefox, Username, Password });
	
		} else if (driver.toString().contains("chorme")) {
			Runtime.getRuntime().exec(new String[] { authenChorme, Username, Password });
		} 
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(), "Congratulations! You must have the proper credentials.");
		}
	
	public String passUserAndPassToUrl (String url, String Username, String Password) {
		//Url: http://admin:admin@the-internet.herokuapp.com/basic_auth
		//Password: admin
		//Username: admin
		String[] arrayUrl = url.split("//");
		return arrayUrl[0] + "//" + Username + ":" + Password + "@" + arrayUrl[1];
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