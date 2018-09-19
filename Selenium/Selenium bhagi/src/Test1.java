
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;


public class Test1 {
    public static void main(String[] args) throws Exception
{


}

public static void Test1() throws Exception{

 
        FirefoxDriver wd;
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8080/lisabank/home.do;jsessionid=8A4FFAC23A5CB3D9EB838B463ED5C777");
        wd.findElement(By.name("username")).click();
        wd.findElement(By.name("username")).clear();
        wd.findElement(By.name("username")).sendKeys("lisa_simpson");
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys("golisa");
        wd.findElement(By.xpath("//table[@id='t11']/tbody/tr[2]/td/table/tbody/tr[6]/td/input")).click();
        wd.findElement(By.id("new_account")).click();
        wd.findElement(By.name("name")).click();
        wd.findElement(By.name("name")).clear();
        wd.findElement(By.name("name")).sendKeys("Chck");
        wd.findElement(By.name("balance")).click();
        wd.findElement(By.name("balance")).clear();
        wd.findElement(By.name("balance")).sendKeys("5000");
        wd.findElement(By.xpath("//td[@class='secondary']/input")).click();
        wd.findElement(By.xpath("//div[@id='maincontent']/table/tbody/tr[3]/td[2]/table/tbody/tr[2]/td")).click();
        wd.findElement(By.linkText("Log Out")).click();
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
