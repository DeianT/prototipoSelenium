package prueba.selenium.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScraperService {
    private static final String URL = "https://relatedwords.org/relatedto/";
    private final ChromeDriver driver;
    
//    @PostConstruct //esto se ejecuta automáticamente
//    void postConstruct(){
//        scrape("car");
//    }
    
    public void scrape(final String value){
        driver.get(URL + value);
        final WebElement words = driver.findElement(By.className("words"));
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        wordList.forEach(word -> System.out.println(word.getText()));
        driver.quit();
    }
    
    public List<WebElement> scrapeLista(String value){
        driver.get(URL + value);
        final WebElement words = driver.findElement(By.className("words"));
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        
//        driver.quit(); //si descomento esta línea anda una sola vez y se rompe
        return wordList;
    }
    
    public String scrapeString(final String value){
        driver.get(URL + value);
        final WebElement words = driver.findElement(By.className("words"));
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        
        String s = value;
        for(WebElement word: wordList){
            s += ", " + word.getText();
        }
//        driver.quit();
        return s;
    }
}
