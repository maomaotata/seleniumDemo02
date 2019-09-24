package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/*
* 方法一：设置驱动路径时，必须将驱动安装包放入到浏览器所在位置，访问路径也是绝对路径
* 方法二：在电脑中设置好环境变量后，访问相对路径也可以使用
* */
public class openWebdrivers {
    public static WebDriver driver;
    public  static WebDriver  openwebdriver(String  browser){
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","I:\\Users\\Administrator\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
            System.out.println("你打开了"+browser+"浏览器");
        }else if(browser.equals("firefox")){
            System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            driver=new FirefoxDriver();
            driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
            System.out.println("你打开了"+browser+"浏览器");
        }else {
            System.out.println("你输入的"+browser+"浏览器不支持打开");
        }
        return driver;
    }
    public static void closed(){
        driver.quit();
    }
}

