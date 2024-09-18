package prueba.selenium.service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import prueba.selenium.model.Pregunta;

@Service
@AllArgsConstructor
public class StackService {
    private static final String URL = "https://stackoverflow.com/";
    private final ChromeDriver driver;
    private final ChromeDriver driver1;
        
    public String scrapeTitulo(){
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
        final List<WebElement> links = questionsDiv.findElements(By.cssSelector("a.s-link"));
        
        return links;
    }
    
    public List<Pregunta> preguntas(String busqueda){
        /*
        revisar cuando hay más de una palabra
        https://stackoverflow.com/search?q=get+request
        */
        driver.get(URL + "questions/tagged/" + busqueda);
        
        final WebElement questionsDiv = driver.findElement(By.id("questions"));
        final List<WebElement> titles = questionsDiv.findElements(By.tagName("h3"));
        final List<Pregunta> preguntas = new ArrayList<>();
        
        for(WebElement h3: titles){
            Pregunta p = new Pregunta();
            
            p.setTitulo(h3.getText());
            
            String link = h3.findElement(By.cssSelector("a.s-link")).getAttribute("href");
            p.setEnlace(link);
            
            p.setContenido(obtenerParrafo(link));
            
            preguntas.add(p);
        }
        return preguntas;
    }
    
    private List<String> obtenerParrafo(String URL){
        driver1.get(URL);
        WebElement div = driver1.findElement(By.cssSelector("div.s-prose.js-post-body"));
        List<WebElement> parrafos = div.findElements(By.tagName("p"));
        List<String> lista = new ArrayList<>();
        for (WebElement parrafo : parrafos) {
            lista.add(parrafo.getText());
        }
        return lista;
    }
}
