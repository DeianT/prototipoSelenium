package prueba.selenium.controller;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import prueba.selenium.service.ScraperService;

@RestController
public class APIController {
    @Autowired
    private ScraperService service;
    
    @GetMapping("/lista/{palabra}")
    public List<String> obtenerListaPalabras(@PathVariable String palabra){
        List<WebElement> list = service.scrapeLista(palabra);
        List<String> words = new ArrayList<>();
        for(WebElement word: list){
            words.add(word.getText());
        }
        return words;
    }
    
    @GetMapping("/palabras/{palabra}")
    @ResponseBody
    public String obtenerPalabras(@PathVariable String palabra){
        return service.scrapeString(palabra);
    }
}
