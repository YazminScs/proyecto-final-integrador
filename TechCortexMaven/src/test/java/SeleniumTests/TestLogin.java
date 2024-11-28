package SeleniumTests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestLogin {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ysuyb\\Downloads\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://localhost:8080/TechCortexMaven/Vista/login.jsp"); 

            WebElement usernameField = driver.findElement(By.id("signin-username"));
            WebElement passwordField = driver.findElement(By.id("signin-password"));
            WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnIngresar']"));

            usernameField.sendKeys("Eduardo");
            passwordField.sendKeys("edu123"); 
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent()); 

            Alert alert = driver.switchTo().alert();
            System.out.println("Mensaje del alert: " + alert.getText()); 
            alert.accept(); 

            wait.until(ExpectedConditions.urlToBe("http://localhost:8080/TechCortexMaven/ControladorCarrusel?accion=carrusel")); 
            System.out.println("Redirección exitosa a: " + driver.getCurrentUrl());

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            driver.quit();
        }
    }
}