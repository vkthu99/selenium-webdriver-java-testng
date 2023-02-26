package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");
		//Verify loginbutton is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		//Verify nút login button có màu xám
		String loginButtonbackground = driver.findElement(loginButton).getCssValue("loginButton");
		System.out.println(loginButtonbackground);
		Assert.assertFalse(loginButtonbackground.contains("rgb(224,224,224)"));
		//Điền thông tin email và pass
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987666777");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		sleepInSecond(2); 
		
		//Verify loginbutton is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		
		//Verify nút login button có màu đỏ
		loginButtonbackground = driver.findElement(loginButton).getCssValue("loginButton");
		System.out.println(loginButtonbackground);
		
	}
	@Test
	public void TC_02_Default_checkbox_Radiobutton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		//Verify check box đã được chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		//Bỏ chọn checkbox
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		//Kiểm tra radio được chọn hay chưa, nếu chưa thì click chọn
		if (!driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).click();
		}
	}
	//@Test
	public void TC_03_Default_checkbox_Multipal() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Đưa hết các check box vào 1 danh sách
		List<WebElement> Allcheckboxes = driver.findElements(By.cssSelector("input.form-checkbox"));
		//Dùng vòng lặp để duyệt qua và click tất cả vào các check box này 
		for (WebElement checkbox : Allcheckboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
	    //Verify tất cả các checkbox được chọn thành công 
		for (WebElement checkbox : Allcheckboxes) {
		Assert.assertTrue(checkbox.isSelected());	
		}
	}
		//@Test
		public void TC_04_Default_checkbox_Multipal_Điều_kiện() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> Allcheckboxdk = driver.findElements(By.cssSelector("input.form-checkbox"));
	    //Nếu như gặp check box có tên là X thì mới click
		for (WebElement checkboxdk : Allcheckboxdk) {
			if (checkboxdk.getAttribute("value").equals(" Cancer ")) {
				checkboxdk.click();
			}
		}
		
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