package webdriver;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	
	@Test
	public void TC_01_Firefox() {
		//Firefox
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				driver.get("https://www.facebook.com/");
				driver.quit();
					}

	@Test
	public void TC_02_Chorme() {
		//Chorme
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
			}

	@Test
	public void TC_03_Edge() {
		//Edge
				System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
				driver = new EdgeDriver();
				driver.get("https://www.facebook.com/");
				driver.quit();
					}
	
	
		}