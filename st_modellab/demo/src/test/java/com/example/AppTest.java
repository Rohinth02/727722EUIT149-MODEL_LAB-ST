package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {

    Logger log = Logger.getLogger(AppTest.class);

    @Test
    public void shouldAnswerWithTrue() throws InterruptedException, IOException {
        PropertyConfigurator.configure(
                "C:\\Users\\ROHINTH\\Desktop\\st_modellab\\demo\\src\\test\\java\\com\\example\\resources\\log4j.properties");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.opentable.com");

        log.info("open url");
        Thread.sleep(2000);

        FileInputStream fs = new FileInputStream("D:\\opentable.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet xs = wb.getSheet("sheet1");
        XSSFRow r1 = xs.getRow(0);

        String place = r1.getCell(0).getStringCellValue();

        driver.findElement(By.xpath("//*[@id='home-page-autocomplete-input']")).sendKeys(place);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='mainContent']/header/div/span/div/div/div[2]/div[2]/button")).click();
        Thread.sleep(2000);
        driver.navigate().to(
                "https://www.opentable.com/s?dateTime=2024-05-02T19%3A00%3A00&covers=2&latitude=12.9921566&longitude=77.6017202&term=Bangalore&shouldUseLatLongSearch=true&originCorrelationId=555f93af-dfec-4530-a1b4-619a9bf9e71a&corrid=cec4035b-c876-4d53-8d42-ead24d3c5d4e&metroId=3367&originalTerm=Bangalore&queryUnderstandingType=location&sortBy=web_conversion");
        Thread.sleep(2000);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,600)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='mainContent']/div/section/div[6]/div/label[4]/span[2]")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='mainContent']/div/div/div[2]/div/div[2]/div[1]/div[1]/a/h6")).click();
        Thread.sleep(2000);

        String currTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currTab)) {
                driver.switchTo().window(tab);
            }
        }
        Thread.sleep(4000);
        log.info("select timing");
        Thread.sleep(2000);

        Select dp = new Select(driver.findElement(
                By.xpath("/html/body/div[1]/div/div/main/div/div[2]/div[2]/div/article/div/div[1]/div/select")));
        dp.selectByVisibleText("4 people");
        Thread.sleep(4000);
        driver.findElement(
                By.xpath("/html/body/div[1]/div/div/main/div/div[2]/div[2]/div/article/div/article/ul/li[1]/a"))
                .click();
        Thread.sleep(2000);

        // driver.findElement(By.xpath("//*[@id='home-page-autocomplete-input']")).sendKeys("Bangalore");
        // Thread.sleep(2000);

    }
}
