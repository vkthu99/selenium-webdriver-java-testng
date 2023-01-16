package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Excercise_Element {
	WebDriver driver;
	Random Rand;
	String projectPath = System.getProperty("user.dir");
    String emailAddress;
    
	//Gán vào 1 biến để dùng cho các TCs khác
	By emailTextbox = By.id("mail");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By eduTextarea = By.cssSelector("#edu");
	By nameuser5Text = By.xpath("//h5[text()='Name: User5']");
	By password = By.xpath("//input[@id='disable_password']");
	By Biographytextarea = By.cssSelector("#bio");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		Rand = new Random();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		emailAddress = "automation" + Rand.nextInt(9999) + "@gmail.com";
		
		}

	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//NẾu emailtextbox có hiển thị thì nhập automation testing và in ra console hiển thị EmailTextbox is displayed
		if(driver.findElement(emailTextbox).isDisplayed()) {
		   driver.findElement(emailTextbox).sendKeys("Automation testing");
		   System.out.println("EmailTextbox is displayed");
		} else {
			//Nếu ko thì in ra console hiển thị EmailTextbox is not displayed
			System.out.println("EmailTextbox is not displayed");
		}
		if (driver.findElement(ageUnder18Radio).isDisplayed()) {
			driver.findElement(ageUnder18Radio).click();
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		if (driver.findElement(eduTextarea).isDisplayed()) {
			driver.findElement(eduTextarea).sendKeys("Automation testing");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}
		if (driver.findElement(nameuser5Text).isDisplayed()) {
			System.out.println("Name user 5 is displayed");
		} else {
			System.out.println("Name user 5 is not displayed");
		} 
	}
	    @Test
	    public void TC_02_IsEnabled()  {
	    	driver.get("https://automationfc.github.io/basic-form/index.html");
	    	if (driver.findElement(emailTextbox).isEnabled()) {
				System.out.println("Email is enabled");
			} else {
				System.out.println("Email is not enabled");
			}
	    	if (driver.findElement(ageUnder18Radio).isEnabled()) {
				System.out.println("Age under 18 is enabled");
			} else {
				System.out.println("Age under 18 is not enabled");
			}
	    	if (driver.findElement(eduTextarea).isEnabled()) {
				System.out.println("Education is enabled");
			} else {
				System.out.println("Education is not enabled");
			}
	    	if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled()) {
				System.out.println("Job role 1 is enabled");
			} else {
				System.out.println("Job role 1 is not enabled");
			}
	    	if (driver.findElement(By.xpath("//select[@id='job2']")).isEnabled()) {
				System.out.println("Job role 2 is enabled");
			} else {
				System.out.println("Job role 2 is not enabled");
			}
	    	if (driver.findElement(By.cssSelector("input[id='development']")).isEnabled()) {
				System.out.println("Interests development is enabled");
			} else {
				System.out.println("Interests development is not enabled");
			}
	    	if (driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled()) {
				System.out.println("Slider 01 is enabled");
			} else {
				System.out.println("Slider 01 is not enabled");
			}
	    	if (driver.findElement(password).isEnabled()) {
				System.out.println("Password 01 is enabled");
			} else {
				System.out.println("Password is not enabled");
			}
	    	if (driver.findElement(By.cssSelector("#radio-disabled")).isEnabled()) {
				System.out.println("Radio button is enabled");
			} else {
				System.out.println("Radio button is not enabled");
			}
	    	if (driver.findElement(Biographytextarea).isEnabled()) {
				System.out.println("Biography 01 is enabled");
			} else {
				System.out.println("Biography is not enabled");
			}
	    	if (driver.findElement(By.cssSelector("#check-disbaled")).isEnabled()) {
				System.out.println("Check disable is enabled");
			} else {
				System.out.println("Check disable is not enabled");
			}
	    	if (driver.findElement(By.cssSelector("#slider-2")).isEnabled()) {
				System.out.println("Slider 02 is enabled");
			} else {
				System.out.println("Slider 02 is not enabled");
			}
	    }	
	    	@Test
		    public void TC_03_Selected()  {
	    		//Truy cập vào trang web
	    		driver.get("https://automationfc.github.io/basic-form/index.html");
	    		//click chọn Age under 18
	    		driver.findElement(ageUnder18Radio).click();
	    	    driver.findElement(By.cssSelector("#java")).click();
	    	    //Kiểm tra các element đã được chọn
	    	    Assert.assertTrue(driver.findElement(ageUnder18Radio).isSelected());
	    	    Assert.assertTrue(driver.findElement(By.cssSelector("#java")).isSelected());
	    	    //Click để bỏ chọn Language java
	    	    driver.findElement(By.cssSelector("#java")).click();
	    	    //Kiểm tra Language java đã được bỏ chọn
	    	    Assert.assertFalse(driver.findElement(By.cssSelector("#java")).isSelected());
	    	    //Kiểm tra nếu được chọn thì in ra console
	    	    if (driver.findElement(ageUnder18Radio).isSelected()) {
					System.out.println("Age under 18 is selected");
				} else {
					System.out.println("Age under 18 is not selected");
				}
	    	    if (driver.findElement(By.cssSelector("#java")).isSelected()) {
	    	    	System.out.println("Language java is selected");
				} else {
					System.out.println("Language java is not selected");
				}
	    	}
	     @Test
			    public void TC_04_Kethop()  {
		    	//Truy cạp vào trang web
	    	    driver.get("https://login.mailchimp.com/signup/");
	    	    //Nhập thông tin hợp lệ của email
	    	    driver.findElement(By.cssSelector("#email")).sendKeys("vuthikieuthu1999@gmail.com");
		    	
	    	    By password = By.cssSelector("#new_password");
	    	    By singinbutton = By.cssSelector("#create-account-enabled");
	    	    //Điền password là chữ ko hợp lệ
	    	    driver.findElement(password).sendKeys("abc");
	    	    driver.findElement(singinbutton).click();
	    	    sleepInSecond(3);
	    	    //Verify
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    	    //Điền password là số
	    	    driver.findElement(password).clear();
	    	    driver.findElement(password).sendKeys("1234");
	    	    driver.findElement(singinbutton).click();
	    	    sleepInSecond(3);
	    	    //Verify
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    	    //Điền password là chữ in hoa
	    	    driver.findElement(password).clear();
	    	    driver.findElement(password).sendKeys("AUTO");
	    	    driver.findElement(singinbutton).click();
	    	    sleepInSecond(3);
	    	    //Verify
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    	    //Điền password có ký tự đặc biệt
	    	    driver.findElement(password).clear();
	    	    driver.findElement(password).sendKeys("1aAUTO@");
	    	    driver.findElement(singinbutton).click();
	    	    sleepInSecond(3);
	    	    //Verify
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
	    	    //Điền password đủ 8 ký tự
	    	    driver.findElement(password).clear();
	    	    driver.findElement(password).sendKeys("1aAUTOab");
	    	    driver.findElement(singinbutton).click();
	    	    sleepInSecond(3);
	    	  //Verify
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
	    	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	        }
	     @Test
		    public void TC_05_Login() {
	    	 	driver.get("http://live.techpanda.org/");
	    	    By emailTextbox = By.cssSelector("#email");
	    	    By passwordTextbox = By.cssSelector("#pass");
	    	 //Login empty email and password
	    	    driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	    	    sleepInSecond(3);
	    	    driver.findElement(By.cssSelector("#send2")).click();
	    	 //Verify câu cảnh báo
	    	    Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
	    	    Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
	    	    
	         //Login with invalid email
	    	    driver.findElement(emailTextbox).sendKeys("123456@123456");
	    	    driver.findElement(passwordTextbox).sendKeys("123456");
	    	    driver.findElement(By.cssSelector("#send2")).click();
	    	 //Verify câu cảnh báo 
	    	    Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
	    	    
	    	 //Login with password < 6
	    	    driver.findElement(emailTextbox).clear();
	    	    driver.findElement(emailTextbox).sendKeys("vuthikieuthu1999@gmail.com");
	    	    driver.findElement(passwordTextbox).clear();
	    	    driver.findElement(passwordTextbox).sendKeys("12345");
	    	    driver.findElement(By.cssSelector("#send2")).click();
	    	 //Verify câu cảnh báo
	    	    Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	    	 //Login with incorrect email/password
	    	    driver.findElement(emailTextbox).clear();
	    	    driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
	    	    driver.findElement(passwordTextbox).clear();
	    	    driver.findElement(passwordTextbox).sendKeys("123123123");
	    	    driver.findElement(By.cssSelector("#send2")).click();
	    	 //Verify câu cảnh báo
	    	    Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
	    	    
	    	 //  Create a new account
	    	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    	    sleepInSecond(3);
	    	    driver.findElement(By.cssSelector("#firstname")).sendKeys("Vũ");
	    	    driver.findElement(By.cssSelector("#middlename")).sendKeys("Thị Kiều");
	    	    driver.findElement(By.cssSelector("#lastname")).sendKeys("Thu");
	    	    driver.findElement(By.cssSelector("#email_address")).sendKeys("vuthithu08@gmail.com");
	    	    driver.findElement(By.cssSelector("#password")).sendKeys("123456");
	    	    driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
	    	    driver.findElement(By.cssSelector("#is_subscribed")).click();
	    	    driver.findElement(By.xpath("//button[@title='Register']")).click();
	    	    sleepInSecond(3);
	    	 //Verify câu cảnh báo
	    	    Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
	    	 //Verify lại thông tin hiển thị sau khi đăng ký
	    	   String contactInformation =  driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	    	   System.out.println(contactInformation);
	    	   Assert.assertTrue(contactInformation.contains("Vũ Thị Kiều Thu"));
	    	   Assert.assertTrue(contactInformation.contains("vuthithu08@gmail.com"));
	    	   
	    	   driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
	    	   driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	    	 //Verify log out trở về trang chủ
	    	   Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	    	   
	     }
	     @Test
		    public void TC_06_Login_dky() {
	    	   driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	    	   sleepInSecond(3);  
	    	   driver.findElement(By.cssSelector("#email")).sendKeys("vuthithu08@gmail.com");
	    	   driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
	    	   driver.findElement(By.cssSelector("#send2")).click();
	    	   sleepInSecond(3);
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