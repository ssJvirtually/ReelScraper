package com.example.ReelScraper.Service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;


@Service
public class GetReel {

    public File getReels(String linkofReel){
        // Configuring the system properties of chrome driver

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\ReelScraper\\ReelScraper\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        //System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

        //FirefoxOptions options =   new FirefoxOptions();
        //options.setHeadless(true);
        //options.setBinary("/home/shashi/Downloads/ReelScraper-master/google-chrome-stable_current_amd64.deb");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new FirefoxDriver(options);

        driver.get("https://instafinsta.com/reels");

        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);


        Duration duration = Duration.ofSeconds(10,0);

        WebDriverWait wait = new WebDriverWait(driver, duration);

        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(20,0));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link")));

        WebElement link = driver.findElement(By.id("link"));

        //LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);


        link.clear();

        link.sendKeys(linkofReel);

        wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("get")));

        WebElement search =  driver.findElement(By.id("get"));


        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .elementToBeClickable(By.id("get")));

        search.click();



        By byLocator = By.tagName("video");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .presenceOfElementLocated(byLocator));
        WebElement findElement = driver.findElement(byLocator);
        System.out.println(findElement.getText());
        System.out.println("Src file : " + findElement.getAttribute("src"));

        wait1.until(ExpectedConditions.presenceOfElementLocated(By.tagName("video")));

        WebElement video = driver.findElement(By.tagName("video"));


        WebElement dowloadServer = driver.findElement(By.xpath("//*[@id=\"result\"]/div/div/div[2]/div/a[2]"));

        System.out.println(dowloadServer.getAttribute("href"));

        String videoLink = dowloadServer.getAttribute("href");


        try (BufferedInputStream in = new BufferedInputStream(new URL(videoLink).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("reel.mp4")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            return new File("reel.mp4");
        } catch (IOException e) {
            // handle exception
        }

        return null;
        //driver.quit();
    }

}
