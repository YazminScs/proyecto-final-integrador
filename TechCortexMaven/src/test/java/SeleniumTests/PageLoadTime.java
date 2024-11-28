package SeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageLoadTime {
     public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ysuyb\\\\Downloads\\\\chromedriver-win64\\\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            long startTime = System.currentTimeMillis();

            driver.get("http://localhost:8080/TechCortexMaven/index.jsp"); 

            long endTime = System.currentTimeMillis(); 

            System.out.println("Tiempo de carga de la página: " + (endTime - startTime) + " ms");
        } finally {
            driver.quit(); 
        }
    }
}
