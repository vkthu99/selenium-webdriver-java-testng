package webdriver;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_Part1 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
    String dragdrophelperPath = projectPath + "Drag&drop\\drag_and_drop_helper.js";
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new  Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		}

	//@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		//Hover chuột vào để hiển thị tooltip
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	
	}
	//@Test
	public void TC_02_Menu() {
		driver.get("https://www.myntra.com/");
		//hover chuột vào menu
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	
	}
	//@Test
		public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(10);
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Kỹ Năng Sống']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Kỹ năng sống']")).isDisplayed());
		}
    
	//@Test
	public void TC_04_Click_and_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//Click vào số đầu tiên
		action.clickAndHold(listNumber.get(0))
		//Di tới số 8
		.moveToElement(listNumber.get(7))
		//Nhả chuột ra
	    .release()
	    //Thực thi câu lệnh
	    .perform();
		sleepInSecond(2);
		//Verify
		List<WebElement> lisSelecttNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(lisSelecttNumber.size(), 8);
	}
	//@Test
	public void TC_05_Click_and_select_random_items() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//Nhấn ctrl xuống 
		action.keyDown(Keys.CONTROL).perform();
		//Chọn số random
		action.click(listNumber.get(0))
		.click(listNumber.get(5))
		.click(listNumber.get(7))
		.click(listNumber.get(10));
		//Nhả phím ctrl
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(5);
		List<WebElement> lisSelecttNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(lisSelecttNumber.size(), 4);
	}
	
	//@Test
	public void TC_06_double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//scroll đến element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(1);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
		
		
	}
	//@Test
	public void TC_07_Right_Click() {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");	
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']")).isDisplayed());
	}
	//@Test
	public void TC_08_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(2);
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
			
	}
	//@Test
	public void TC_09_Drag_And_Drop_HTML5_css() throws IOException {
		String jsHelper = getContentFile(dragdrophelperPath);
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceCss = "div#column-a";
		String targetCss = "div#column-b";
		//drop A to B
		jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		//Drop B to A
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}
	@Test
	public void TC_010_Drag_And_Drop_HTML5_xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		//Drag A to B
		dragAndDropHTML5ByXpath ("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		//drag B to A
		dragAndDropHTML5ByXpath ("//div[@id='column-b']", "//div[@id='column-a']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='b']")).isDisplayed());
	}
	
	
	
	
	
	
	public void sleepInSecond(long timeInSecond)  {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public String getContentFile(String filePath) throws IOException {
			Charset cs = Charset.forName("UTF-8");
			FileInputStream stream = new FileInputStream(filePath);
			try {
				Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
				StringBuilder builder = new StringBuilder();
				char[] buffer = new char[8192];
				int read;
				while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
					builder.append(buffer, 0, read);
				}
				return builder.toString();
			} finally {
				stream.close();
			}
	}	
	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}