package testng;

import driver.openWebdrivers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Itest {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("http://www.baidu.com");
        WebElement e1,e2;
        //e1 = driver.findElement(By.id("kw"));
        e1 = driver.findElement(By.name("tj_trnews"));
        e1.sendKeys("selenium");
        Thread.sleep(Long.parseLong("2000"));
        e1.clear();
        e1.sendKeys("吃饭");
        Thread.sleep(Long.parseLong("4000"));
        String text = e1.getText();
        System.out.println(text);
        e2= driver.findElement(By.id("su"));
        Thread.sleep(Long.parseLong("2000"));
        e2.click();




    }
}