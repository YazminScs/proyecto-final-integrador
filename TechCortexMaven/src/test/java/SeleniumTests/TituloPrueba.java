package SeleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TituloPrueba {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ysuyb\\Downloads\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/TechCortexMaven/index.jsp");

        String titulo = driver.getTitle();
        System.out.println("Título de la página: " + titulo);

        driver.quit();
    }
}
