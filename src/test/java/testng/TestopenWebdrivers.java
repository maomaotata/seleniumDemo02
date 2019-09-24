package testng;

import driver.openWebdrivers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;

import static java.lang.Long.*;

public class TestopenWebdrivers {
    WebDriver driver = openWebdrivers.openwebdriver("chrome");

    @Test
    public void testDemo01() {
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "自动化测试用例");
        openWebdrivers.closed();
    }

    @Test
    public void testDemo02() {
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "界面自动化测试用例");
        Assert.assertNotNull(title);
        Assert.assertNull(title);
    }

    @Test
    public void testDemo03() {
        /*
        * gettext（）的用法
        * getAttribute（）的用法
        * getTagName（）的用法*/
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("http://www.baidu.com");
        boolean e1;
        WebElement e2;
        //e1 = driver.findElement(By.id("kw"));
        //e1 = driver.findElement(By.name("tj_trnews"));
        e1 = driver.findElement(By.id("su")).isDisplayed();
        String su = driver.findElement(By.id("su")).getAttribute("value");
        System.out.println(su);
        Assert.assertTrue(e1);
        /*//获取文本值
        //String text = e1.getText();
        //获取标签
        String tagName = e1.getTagName();
        //获取定位元素的属性值
        String attribute = e1.getAttribute();
        //System.out.println(text);
        System.out.println(tagName);
        System.out.println(attribute);
        Assert.assertEquals(text,"界面自动化测试用例");*/
    }

    @Test
    public void AssertEable() throws InterruptedException {
        /*
        * isenabled（激活）的用法*/
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("http://www.baidu.com/");
        Boolean isEable = driver.findElement(By.id("su")).isEnabled();
        System.out.println(isEable);
        //校验百度首页是否可点击这个元素
        Assert.assertTrue(isEable);
    }

    @Test
    public void testDemo04() {
        /*
        * 屏幕截图功能是用*/
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("http://www.baidu.com/");
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\java\\logs\\baidu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDemo05() throws InterruptedException {
        /*
        * alert的用法
        * conform的用法
        * Prompt的用法*/
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
        WebElement alert = driver.findElement(By.className("alert"));
        alert.click();
        //因为无法获取弹出框确定按钮的定位，所以把driver的控制权交给alert
        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(3000);
        //点击确定按钮
        //alert1.accept();
        //取消确定按钮
        alert1.dismiss();
    }

    @Test
    public void testDemo06() throws InterruptedException {
        /*
        * iframe框架的使用定位*/
        WebDriver driver = openWebdrivers.openwebdriver("chrome");
        driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\index.html");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        WebDriver frame = driver.switchTo().frame(iframe);
        WebElement baidu = frame.findElement(By.className("baidu"));
        baidu.click();
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void testDemo07() throws InterruptedException {
        /*
        * iframe框架的使用定位后，需要将控制权转回主页面*/
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        WebDriver frame = driver.switchTo().frame(iframe);
        WebElement baidu = frame.findElement(By.className("baidu"));
        baidu.click();
        Thread.sleep(3000);
        frame.switchTo().defaultContent();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"link\"]/a"));
        element.click();
    }

    @Test
    public void testDemo08() throws InterruptedException {
        /*
        * 下拉框的定位*/
        WebElement select = driver.findElement(By.name("select"));
        Select select1 = new Select(select);
        select1.selectByIndex(2);
        Thread.sleep(3000);
        // select1.deselectByValue("iphone");
        //select1.selectByVisibleText("huawei");
    }

    @Test
    public void testDemo09() throws InterruptedException {
        /*
        * 多窗口的定位
        * 方法：找到当前窗口的句柄，与之匹配*/
        WebElement linkText = driver.findElement(By.linkText("Open new window"));
        linkText.click();
        Thread.sleep(3000);
        String windowHandle = driver.getWindowHandle();
        for (String Handle : driver.getWindowHandles()) {
            if (windowHandle.equals(Handle)) {
                continue;
            } else {
                driver.switchTo().window(Handle);
            }
        }
        //driver.switchTo().window(windowHandle);

        driver.findElement(By.xpath("//*[@id=\"radio\"]/input[1]")).click();
    }

    @Test
    public void testDemo10() throws InterruptedException {
        /*
        * 等待的操作方法
        * 方法一：thread.sleep()
        * 方法二：全局等待所有的地方起作用，如果20s之内就正常，那就无需等待，否则会等待20s，然后抛超时异常
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  //后面这个参数是表示20s
        方法三：//3.显示等待，如果没有满足until后面的值，会等待5s，然后抛超时异常，如果5s之内就正常了，那就无需等待
        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.className("red")));*/
        driver.findElement(By.xpath("//*[@id=\"wait\"]/input")).click();
        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.className("red")));
        String text = driver.findElement(By.xpath("//*[@id=\"display\"]/div")).getText();
        Assert.assertEquals(text,"wait for display");
    }
    @Test
    public void RightClickTest() throws InterruptedException {
        //右击和左键双击操作
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.id("su"));
        //右键操作用到Action类
        Actions actions=new Actions(driver);
        actions.contextClick(element).perform();   //右击哪个元素，如果不传的话默认左上角元素
        Thread.sleep(3000);
        actions.doubleClick(element).build().perform();   //左键双击，如果不写build也是可以的
    }
    @Test
    public void testDemo11() throws InterruptedException {
        //右击和左键双击操作
        WebElement element = driver.findElement(By.xpath("//*[@id=\"action\"]/input"));
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
        action.doubleClick().perform();
    }
    @Test
    public void RightClickTest2() {
        //右击和左键双击操作
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.id("su"));
        //右键操作用到Action类
        Actions actions=new Actions(driver);
        //actions.contextClick(element).perform();   //右击哪个元素，如果不传的话默认左上角元素
        actions.doubleClick(element).build().perform();   //左键双击，如果不写build也是可以的
    }
    @Test
    public void moveTest() {
        //鼠标移动操作
        driver.get("http://www.baidu.com");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"lg\"]/map/area"));
        Actions actions=new Actions(driver);
        actions.moveToElement(element).perform();
    }
    @Test
    public void dragTest() {
        //鼠标拖拽操作
        driver.get("C:\\Users\\Administrator\\IdeaProjects\\seleniumDemo\\src\\test\\selenium_html\\dragAndDrop.html");
        WebElement element = driver.findElement(By.id("drag"));
        Actions actions=new Actions(driver);
        actions.dragAndDropBy(element,500,800).perform();
    }
    @Test
    public void dropTest1() {
        //鼠标拖拽操作
        driver.get("file:///D:/testing/webdriver_demo/selenium_html/index.html");
        WebElement element1 = driver.findElement(By.id("drag"));
        WebElement element2 = driver.findElement(By.linkText("banban"));
        Actions actions=new Actions(driver);
        //按住元素1，然后拖到到元素2，再释放元素1，执行
        actions.clickAndHold(element1).moveToElement(element2).release(element1).perform();
    }
    @Test
    public void robotTest() throws AWTException {
        driver.get("http://www.baidu.com");
        //robot使用,按住ctrl+S键然后释放
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_S);
    }
    @Test
    public void testDemo12(){
        WebElement select = driver.findElement(By.id("selectWithMultipleEqualsMultiple"));
        List<WebElement> elements = select.findElements(By.tagName("option"));
        Actions actions = new Actions(driver);
        //actions.keyDown(Keys.SHIFT).click(elements.get(0)).click(elements.get(2)).keyUp(Keys.SHIFT).perform();
        actions.keyDown(Keys.CONTROL).click(elements.get(0)).keyUp(Keys.CONTROL).perform();
    }


}
