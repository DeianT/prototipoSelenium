package prueba.selenium.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GitHubService {
    private static final String URL = "https://stackoverflow.com/";
    private final ChromeDriver driver;
        
    public String scrapeString(){
        driver.get(URL);
        final WebElement title = driver.findElement(By.className("-title"));
        
        return title.getText();
    }
    
    public List<WebElement> search(String busqueda){
        //Esta forma necesita pasar un captcha
//        driver.get(URL);
//        final WebElement input = driver.findElement(By.cssSelector("input.s-input.s-input__search.js-search-field.wmn1"));
//        input.clear();
//        input.sendKeys("java");
//        input.submit();

        driver.get(URL + "questions/tagged/" + busqueda);
        
        final WebElement questionsDiv = driver.findElement(By.id("questions"));
        final List<WebElement> titles = questionsDiv.findElements(By.tagName("h3"));
//        final List<WebElement> links = questionsDiv.findElements(By.cssSelector("a.s-link"));
        final List<WebElement> list = new ArrayList<>();
        for(WebElement h3: titles){
            list.add(h3);
            list.add(h3.findElement(By.cssSelector("a.s-link")));
        }
        
        return list;
    }
}
