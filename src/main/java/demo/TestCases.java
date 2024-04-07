package demo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import io.github.bonigarcia.wdm.WebDriverManager;



public class TestCases {
    WebDriver driver;
    @SuppressWarnings("deprecation")
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options1 = new ChromeOptions();
       // FirefoxDriver options2=new FirefoxDriver();

        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options1.setCapability("goog:loggingPrefs", logs);
        options1.addArguments("start-l");
        options1.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

         driver = new ChromeDriver(options1);
        // driver=new FirefoxDriver();

        // Set browser to maximize and wait
        driver.manage().window().maximize();
       // driver.manage().window().setSize(new Dimension(1440, 900));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    
    }
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        String url="https://www.makemytrip.com/";
        driver.get(url);
        String curURL=driver.getCurrentUrl();
        System.out.println("Current url is:"+curURL );
        if(curURL.contains("makemytrip")){
            System.out.println("Testcase pass");
        }
        else{
            System.out.println("Testcase Fail");

        }
    }

    public void testCase02() throws InterruptedException{
       
        System.out.println("Start Test Case: testcase02");

        // String url="https://www.makemytrip.com/";
        // driver.get(url);

         WebElement iframe=driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
         driver.switchTo().frame(iframe);
         driver.findElement(By.xpath("//a[@class='close']")).click();;
      
        Thread.sleep(5000);
       

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fromDropdown=driver.findElement(By.xpath("//*[@id='fromCity']"));
        js.executeScript("document.getElementById('fromCity').click();");
        Thread.sleep(1000);

        fromDropdown.sendKeys("blr");
        Thread.sleep(2000);

            // Wait for banglore value to load using FluentWait
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20)) // Set timeout duration
                    .pollingEvery(Duration.ofMillis(500)); // Set polling interval
        WebElement departureLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Bengaluru International Airport')]")));
        departureLocation.click();

        
        Thread.sleep(2000);
       WebElement toDropdown= driver.findElement(By.xpath("//input[@id='toCity']"));
       toDropdown.click();
       Thread.sleep(2000);
       toDropdown.sendKeys("del");

       driver.findElement(By.xpath("//p[contains(text(),'New Delhi, India')]")).click();
       
        WebElement datelist=driver.findElement(By.xpath("//*[@id='top-banner']/div[2]/div/div/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[3]/div/div/div/p[1][contains(text(),'29')]"));
        Actions actions = new Actions(driver);
       actions.moveToElement(datelist).click().build().perform();       

        WebElement searchButton=driver.findElement(By.xpath("//a[contains(@class, 'primaryBtn')]"));
        Thread.sleep(1000);
        searchButton.click();

        FluentWait<WebDriver> wait3 = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(20)) // Set timeout duration
        .pollingEvery(Duration.ofMillis(500)); // Set polling interval

WebElement okOnPopup = null;

try {
    okOnPopup = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div/div[3]/button")));
} catch (TimeoutException e) {
    // If the element is not found within the specified timeout
    System.out.println("Popup didn't appear within 20 seconds.");
}

// Check if okOnPopup is not null, indicating the element is found
if (okOnPopup != null) {
    driver.switchTo().activeElement();
    okOnPopup.click();
    Thread.sleep(5000);
    driver.switchTo().defaultContent();
} else {
    // If okOnPopup is null, handle accordingly
    System.out.println("Popup element not found.");
}

        List<String> pricePerAdult=new ArrayList<>();
        List<WebElement> el=driver.findElements(By.xpath("//*[@id='listing-id']/div/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div"));
        Thread.sleep(5000);
        for(int i=0;i<el.size();i++){
            pricePerAdult.add(el.get(i).getText());

        }
         System.out.println(pricePerAdult); 
        System.out.println("Testcase02 pass");
    }

    public void testCase03() throws InterruptedException{
        System.out.println("Start Test Case: testcase03");

        driver.get("https://www.makemytrip.com/");

        Thread.sleep(10000);

        WebElement trainNaviagtionOption=driver.findElement(By.xpath("//ul[contains(@class,'makeFlex')]//li[@class='menu_Trains']"));
        trainNaviagtionOption.click();

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fromDropdown=driver.findElement(By.xpath("//input[@id='fromCity']"));
        js.executeScript("document.getElementById('fromCity').click();");
        fromDropdown.click();

        WebElement departureLocation=driver.findElement(By.xpath("//input[@title='From']"));
        Thread.sleep(2000);
        //departureLocation.click();
        departureLocation.sendKeys("ypr");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[contains(text(),'Bengaluru - All Stations')]")).click();
        Thread.sleep(2000);
       WebElement toDropdown= driver.findElement(By.xpath("//input[@id='toCity']"));
       js.executeScript("document.getElementById('toCity').click();");      
       Thread.sleep(2000);
       //toDropdown.sendKeys("NDLS");

       
       WebElement enterDetails=driver.findElement(By.xpath("//input[@title='To']"));
       enterDetails.sendKeys("ndls");

       Thread.sleep(2000);
       driver.findElement(By.xpath("//span[contains(text(),'Delhi - All Stations')]")).click();

       //driver.findElement(By.xpath("//label[@for='travelDate']")).click();
        Thread.sleep(2000);

        WebElement datelist=driver.findElement(By.xpath("//*[@id='top-banner']/div[2]/div/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[3]/div[5]/div[contains(text(),'29')]"));
        Actions actions = new Actions(driver);
       actions.moveToElement(datelist).click().build().perform(); 

       Thread.sleep(2000);

        WebElement selectClass=driver.findElement(By.xpath("//input[@id='travelClass']"));
        Thread.sleep(2000);
      //  selectClass.click();
       // actions.moveToElement(selectClass).click().build().perform(); 

        Thread.sleep(3000);

        FluentWait<WebDriver> wait1 = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(30)) // Set timeout duration
        .pollingEvery(Duration.ofMillis(500)); // Set polling interval

         WebElement thirdACButton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='travelForPopup']//li[3]")));
         thirdACButton.click();
         Thread.sleep(2000);

        // WebElement thirdACButton=driver.findElement(By.xpath("//ul[@class='travelForPopup']//li[3]"));
        // actions.moveToElement(thirdACButton).click().build().perform(); 

        WebElement searchButton=driver.findElement(By.xpath("//a[contains(@class, 'primaryBtn')]"));
        actions.moveToElement(searchButton).click().build().perform(); 

        Thread.sleep(5000);
       // driver.switchTo(). defaultContent();

       //Click on the 3Ac Text
       WebElement thirdTierAcText=driver.findElement(By.xpath("//label[contains(text(),'3 Tier AC')]"));
       thirdTierAcText.click();

        List<WebElement> price=driver.findElements(By.xpath("//div[starts-with(@class,'ticket-price')]"));
        Thread.sleep(5000);
        int size=price.size();
        for(int i=0;i<size;i++){
            String getPriceValue=price.get(i).getText();

            System.out.println(getPriceValue);

        }

        System.out.println("Testcase03 pass");

    }

    public void testCase04() throws InterruptedException{
        System.out.println("Testcase04 starts");
        driver.get("https://www.makemytrip.com/");
        

        WebElement busNaviagtionOption=driver.findElement(By.xpath("//ul[contains(@class,'makeFlex')]//li[@class='menu_Buses']"));
        busNaviagtionOption.click();

        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fromDropdown=driver.findElement(By.xpath("//input[@id='fromCity']"));
        js.executeScript("document.getElementById('fromCity').click();");
       // fromDropdown.click();

        // WebElement departureLocation=driver.findElement(By.xpath("//input[@title='From']"));
        // Thread.sleep(2000);
        // //departureLocation.click();
        fromDropdown.sendKeys("bangl");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//span[contains(text(),'Bangalore, Karnataka')]")).click();
        Thread.sleep(3000);


       WebElement toDropdown= driver.findElement(By.xpath("//input[@id='toCity']"));
       js.executeScript("document.getElementById('toCity').click();");      
       Thread.sleep(2000);
      // toDropdown.click();

       WebElement enterDetails=driver.findElement(By.xpath("//input[@title='To']"));
       enterDetails.sendKeys("kathma");

       Thread.sleep(2000);
       driver.findElement(By.xpath("//span[contains(text(),'Kathmandu, Nepal')]")).click();
        Thread.sleep(2000);

    //    driver.findElement(By.xpath("//label[@for='travelDate']")).click();
    //     Thread.sleep(2000);

        WebElement datelist=driver.findElement(By.xpath("//*[@id='top-banner']/div[2]/div/div/div[2]/div/div[3]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[3]/div[5]/div[contains(text(),'29')]"));
        Actions actions = new Actions(driver);
       actions.moveToElement(datelist).click().build().perform();

       WebElement searchButton=driver.findElement(By.xpath("//button[contains(@class, 'primaryBtn')]"));
        actions.moveToElement(searchButton).click().build().perform(); 

        WebElement messageElement = driver.findElement(By.xpath("//span[@class='error-title']"));
        String message = messageElement.getText();
        if (message.contains("No buses found for 29 May")) {
            System.out.println("Test Passed: No buses found for the specified date.");
        } else {
            System.out.println("Test Failed: Buses found for the specified date.");
        }



        System.out.println("Testcase04 Ended");

    }
    

}
