package prueba.selenium.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WordsService {
    private static final String URL = "https://relatedwords.org/relatedto/";
    private final ChromeDriver driver;
    
//    @PostConstruct //esto se ejecuta automáticamente
//    void postConstruct(){
//        scrape("car");
//    }
    
    /**
     * Imprime por consola las palabras relacionadas a la búsqueda.
     * @param value La palabra o frase a buscar
     */
    public void scrape(final String value){
        driver.get(URL + value);
        final WebElement words = driver.findElement(By.className("words"));
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        wordList.forEach(word -> System.out.println(word.getText()));
        driver.quit();
    }
    
    /**
     * Obtiene las palabras relacionadas a la búsqueda y devuelve los elementos
     * que la contienen
     * @param value La palabra o frase a buscar
     * @return List&ltWebElement&gt Elementos &lta&gt que contienen las palabras
     */
    public List<WebElement> scrapeLista(String value){
        driver.get(URL + value);
        final WebElement words = driver.findElement(By.className("words"));
        final List<WebElement> wordList = words.findElements(By.tagName("a"));
        
//        driver.quit(); //si descomento esta línea anda una sola vez y se rompe
        return wordList;
    }
    
    /**
     * Obtiene las palabras relacionadas a la búsqueda, las concatena y devuelve 
     * el String resultante.
     * @param value La palabra o frase a buscar
     * @return String Palabras separadas por coma
     */
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
