package prueba.selenium.config;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {
    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver", "D:\\Users\\Deian\\Documents\\NetBeansProjects\\selenium\\src\\main\\resources\\drivers\\chromedriver-win64\\chromedriver.exe");
    }
        
    @Bean
    public ChromeDriver driver(){
        final ChromeOptions options = new ChromeOptions();
        //para que no se muestre la ventana de chrome
        options.addArguments("--headless");
        
        return new ChromeDriver(options);
    }
}
