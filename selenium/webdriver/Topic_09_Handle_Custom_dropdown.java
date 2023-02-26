package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Handle_Custom_dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}
	
 	
	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemIndropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow")	;
	    sleepInSecond(3);
	    Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Slow");
	    
		selectItemIndropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower")	;
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Slower");
		
		selectItemIndropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster")	;
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Faster");
		
		selectItemIndropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast")	;
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Fast");
	}
	
	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemIndropdown("i.dropdown.icon", "span.text", "Jenny Hess");
	    sleepInSecond(3);
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
	    selectItemIndropdown("i.dropdown.icon", "span.text", "Elliot Fu");
	    sleepInSecond(3);
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Elliot Fu");
	    selectItemIndropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
	    sleepInSecond(3);
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
	}
	
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemIndropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
	    sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().contains("First Option"));
	    selectItemIndropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
	    sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().contains("Second Option"));
	    selectItemIndropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
	    sleepInSecond(3);
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().contains("Third Option"));
	}
	@Test
	public void TC_04_Edittable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterandselectItemIndropdown("input.search", "span.text", "Afghanistan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Afghanistan");
	}
	
	
	public void selectItemIndropdown(String parentCss, String Allitemcss, String expectedTextitem) {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		//Click vào 1 button để xổ ra combo
		driver.findElement(By.cssSelector(parentCss)).click();
		//Chờ để dropdown load ra hết item trong dropdown (dùng hàm wait linh động chứ ko phải wait cứng)
		//Locator phải lấy để đại diện cho tất cả các item
		//LẤy đến thẻ chứa text cần tìm
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(Allitemcss)));
	    //Tìm item xem có đúng cái đang cần ko
		///Nếu nằm trong khoảng nhín thấy thì ko cần kéo scroll xuống
		///Nếu nằm ngoài khoảng nhìn thấy thì cần kéo scroll xuống để xem item
		//Đưa hết tất cả các item vào trong dropdown vào 1 cái list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(Allitemcss));
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.print(itemText);
		//Kiểm tra text có đúng cái mong muốn ko
			if (itemText.trim().equals(expectedTextitem)) {
		//Click vào item đó
		    tempItem.click();
		//Thoát ra khỏi vòng lặp ko xét cho các case còn lại
		    break;
			}
		}
	}
	
	public void enterandselectItemIndropdown(String textboxCss, String Allitemcss, String expectedTextitem) {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		//Nhập expectedTextitem vào -Tự động xổ ra các item mathching
		driver.findElement(By.cssSelector(textboxCss)).clear();
		driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectedTextitem);
		sleepInSecond(1);
		//Chờ để dropdown load ra hết item trong dropdown (dùng hàm wait linh động chứ ko phải wait cứng)
		//Locator phải lấy để đại diện cho tất cả các item
		//LẤy đến thẻ chứa text cần tìm
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(Allitemcss)));
	    //Tìm item xem có đúng cái đang cần ko
		///Nếu nằm trong khoảng nhín thấy thì ko cần kéo scroll xuống
		///Nếu nằm ngoài khoảng nhìn thấy thì cần kéo scroll xuống để xem item
		//Đưa hết tất cả các item vào trong dropdown vào 1 cái list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(Allitemcss));
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.print(itemText);
		//Kiểm tra text có đúng cái mong muốn ko
			if (itemText.trim().equals(expectedTextitem)) {
				sleepInSecond(1);
		//Click vào item đó
		    tempItem.click();
		//Thoát ra khỏi vòng lặp ko xét cho các case còn lại
		    break;
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
		//driver.quit();
	}
}